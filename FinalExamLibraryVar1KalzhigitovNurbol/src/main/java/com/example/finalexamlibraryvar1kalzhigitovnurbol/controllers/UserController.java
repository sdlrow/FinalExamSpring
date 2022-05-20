package com.example.finalexamlibraryvar1kalzhigitovnurbol.controllers;


import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.*;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.request.MessageRequest;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.request.RecordRequest;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.response.AuthorResponse;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.response.BookCategoryResponse;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.response.BookResponse;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.response.GenreResponse;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.security.jwt.JwtUtils;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.jms.annotation.EnableJms;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
@EnableJms
public class UserController {
    UserService userService;

    RoleService roleService;

    RecordService recordService;

    JmsTemplate jmsTemplate;

    JwtUtils jwtUtils;

    BookService bookService;

    MessageService messageService;

    GenreService genreService;

    BookCategoryService bookCategoryService;

    AuthorService authorService;

    PenaltyService penaltyService;





    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

    @Autowired
    public void setJwtUtils(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @Autowired
    public void setBookCategoryService(BookCategoryService bookCategoryService) {
        this.bookCategoryService = bookCategoryService;
    }

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setPenaltyService(PenaltyService penaltyService) {
        this.penaltyService = penaltyService;
    }

    @PermitAll
    @RequestMapping(value="/getStatus", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> collectionOptions()
    {
        return ResponseEntity
                .ok().body("HttpMethod.GET: " + HttpMethod.GET +
                        " HttpMethod.POST: " + HttpMethod.POST +
                        " HttpMethod.OPTIONS: " + HttpMethod.OPTIONS
                );
    }


    @PermitAll
    @RequestMapping(value="/getHeader", method = RequestMethod.HEAD)
    public ResponseEntity<?> handleRequestHeader (@RequestHeader Map<String, String> mapValues) {
        System.out.println("Headers: "+mapValues);
        return ResponseEntity.ok().body("Headers: "+mapValues);
    }

    @PostMapping("/updateUserInfo")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateUserInfo(@RequestBody RecordRequest recordRequest, HttpServletRequest httpServletRequest){
        String headerAuth = httpServletRequest.getHeader("Authorization");
        String jwt = null;
        String username = null;
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            jwt =  headerAuth.substring(7, headerAuth.length());
        }
        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            username = jwtUtils.getUserNameFromJwtToken(jwt);
        }
        Optional<User> user = userService.findByUsername(username);
            if (user.isPresent()) {
                Optional<Record> record = recordService.findRecordByUser(user.get());
                if (record.isPresent()) {
                    recordService.delete(recordService.findRecordByUser(user.get()).get());
                }
                System.out.println("TEST+++");
                recordService.save(new Record(user.get(), recordRequest.getFirstName().get(), recordRequest.getSecondName().get(), recordRequest.getMiddleName().get(), recordRequest.getDateOfBirth().get(), recordRequest.getAddress().get()));
                return ResponseEntity.ok("Record data updated!");
            }
            return ResponseEntity.badRequest().body("not authorized");
    }


    @PostMapping("/sendMessage")
    @PreAuthorize("hasRole('USER')")
    @Transactional(propagation = Propagation.REQUIRED, timeout=10)
    public ResponseEntity<?> sendMessageTopic(@RequestBody MessageRequest messageRequest, HttpServletRequest httpServletRequest) throws JMSException {
        String headerAuth = httpServletRequest.getHeader("Authorization");
        String jwt = null;
        String username = null;
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            jwt =  headerAuth.substring(7, headerAuth.length());
        }
        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            username = jwtUtils.getUserNameFromJwtToken(jwt);
        }
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            Set<Book> bookSet = new HashSet<>();
            for (Book book : messageRequest.getBooks().get()) {
                Optional<Book> bookOptional = bookService.findById(book.getId());
                bookOptional.ifPresent(bookSet::add);
            }
            Message message = new Message(user.get(), bookSet, messageRequest.getDestination().get(), "unresolved");
            jmsTemplate.convertAndSend("PaymentReview", message);
            return new ResponseEntity<>("Message was send to Admin!", HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("not authorized");
    }

    @GetMapping("getMessage/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getMessage(@PathVariable Long id, HttpServletRequest httpServletRequest){
        String headerAuth = httpServletRequest.getHeader("Authorization");
        String jwt = null;
        String username = null;
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            jwt =  headerAuth.substring(7, headerAuth.length());
        }
        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            username = jwtUtils.getUserNameFromJwtToken(jwt);
        }
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            Optional<Message> message = messageService.findMessageByAnswerAndDestination(id, username);
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.badRequest().body("not authorized");
    }

    @GetMapping("checkPenalty")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> checkPenalty(HttpServletRequest httpServletRequest){
        String headerAuth = httpServletRequest.getHeader("Authorization");
        String jwt = null;
        String username = null;
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            jwt =  headerAuth.substring(7, headerAuth.length());
        }
        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            username = jwtUtils.getUserNameFromJwtToken(jwt);
        }
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            List<Penalty> penalties = penaltyService.getByUser(user.get());
            if(penalties.isEmpty()){
                return ResponseEntity.ok("Dont have any penalties");
            }
            return ResponseEntity.ok(penalties);
        }
        return ResponseEntity.badRequest().body("not authorized");
    }

    @GetMapping("getGenres")
    @ResponseBody
    @PermitAll
    @Cacheable("Genres")
    public ResponseEntity<?> getGenres(){
        List<Genre> genres = genreService.findAll();
        List<GenreResponse> genreResponseList = new ArrayList();
        for(Genre genre:genres){
            genreResponseList.add(new GenreResponse(genre.getId(), genre.getTitle(), genre.getDescription()));
        }
        return ResponseEntity.ok(genreResponseList);
    }

    @GetMapping("getGenre/{id}")
    @ResponseBody
    @PermitAll
    public ResponseEntity<?> getGenre(@PathVariable Long id){
        Optional<Genre> genre = genreService.findById(id);
        return ResponseEntity.ok(new GenreResponse(genre.get().getId(), genre.get().getTitle(), genre.get().getDescription()));
    }

    @GetMapping("getAuthors")
    @ResponseBody
    @PermitAll
    @Cacheable("Authors")
    public ResponseEntity<?> getAuthors(){
        List<Author> authors = authorService.findAll();
        List<AuthorResponse> authorResponseList = new ArrayList();
        for(Author author:authors){
            authorResponseList.add(new AuthorResponse(author.getId(), author.getFirstName(), author.getLastName()));
        }
        return ResponseEntity.ok(authorResponseList);
    }

    @GetMapping("getAuthor/{id}")
    @ResponseBody
    @PermitAll
    public ResponseEntity<?> getAuthor(@PathVariable Long id){
        Optional<Author> author = authorService.findById(id);
        return ResponseEntity.ok(new AuthorResponse(author.get().getId(), author.get().getFirstName(), author.get().getLastName()));
    }

    @GetMapping("getCategories")
    @ResponseBody
    @PermitAll
    @Cacheable("Categories")
    public ResponseEntity<?> getCategories(){
        List<BookCategory> bookCategories = bookCategoryService.findAll();
        List<BookCategoryResponse> bookCategoryResponseList = new ArrayList();
        for(BookCategory bookCategory:bookCategories){
            bookCategoryResponseList.add(new BookCategoryResponse(bookCategory.getId(), bookCategory.getTitle(), bookCategory.getDescription()));
        }
        return ResponseEntity.ok(bookCategoryResponseList);
    }

    @GetMapping("getCategory/{id}")
    @ResponseBody
    @PermitAll
    public ResponseEntity<?> getCategory(@PathVariable Long id){
        Optional<BookCategory> bookCategory = bookCategoryService.findById(id);
        return ResponseEntity.ok(new BookCategoryResponse(bookCategory.get().getId(), bookCategory.get().getTitle(), bookCategory.get().getDescription()));
    }

    @GetMapping("getBooks")
    @ResponseBody
    @PermitAll
    @Cacheable("Books")
    public ResponseEntity<?> getBooks(){
        List<Book> books = bookService.findAll();
        List<BookResponse> bookResponseList = new ArrayList();
        for(Book book:books){
            Set<Genre> genres = book.getGenres();
            Set<GenreResponse> genreResponseList = new HashSet<GenreResponse>();
            for(Genre genre:genres){
                genreResponseList.add(new GenreResponse(genre.getId(), genre.getTitle(), genre.getDescription()));
            }

            Set<Author> authors = book.getAuthors();
            Set<AuthorResponse> authorResponseList = new HashSet<AuthorResponse>();
            for(Author author:authors){
                authorResponseList.add(new AuthorResponse(author.getId(), author.getFirstName(), author.getLastName()));
            }

            BookCategory bookCategory = book.getBookCategory();
            BookCategoryResponse bookCategoryResponse = new BookCategoryResponse(bookCategory.getId(), bookCategory.getTitle(), bookCategory.getDescription());

            bookResponseList.add(new BookResponse(book.getId(), book.getTitle(), book.getDescription(), book.getImageURL(), book.getPublication(), book.getCount(), book.getBinding(), genreResponseList, authorResponseList, bookCategoryResponse, book.getCost()));
        }
        return ResponseEntity.ok(bookResponseList);
    }

    @GetMapping("getBook/{id}")
    @ResponseBody
    @PermitAll
    public ResponseEntity<?> getBook(@PathVariable Long id){
        Optional<Book> book = bookService.findById(id);

        Set<Genre> genres = book.get().getGenres();
        Set<GenreResponse> genreResponseList = new HashSet<GenreResponse>();
        for(Genre genre:genres){
            genreResponseList.add(new GenreResponse(genre.getId(), genre.getTitle(), genre.getDescription()));
        }

        Set<Author> authors = book.get().getAuthors();
        Set<AuthorResponse> authorResponseList = new HashSet<AuthorResponse>();
        for(Author author:authors){
            authorResponseList.add(new AuthorResponse(author.getId(), author.getFirstName(), author.getLastName()));
        }

        BookCategory bookCategory = book.get().getBookCategory();
        BookCategoryResponse bookCategoryResponse = new BookCategoryResponse(bookCategory.getId(), bookCategory.getTitle(), bookCategory.getDescription());

        return ResponseEntity.ok(new BookResponse(book.get().getId(), book.get().getTitle(), book.get().getDescription(), book.get().getImageURL(), book.get().getPublication(), book.get().getCount(), book.get().getBinding(), genreResponseList, authorResponseList, bookCategoryResponse, book.get().getCost()));
    }

}
