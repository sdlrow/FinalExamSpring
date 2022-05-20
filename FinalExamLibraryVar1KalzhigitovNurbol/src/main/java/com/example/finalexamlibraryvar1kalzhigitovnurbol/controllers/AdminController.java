package com.example.finalexamlibraryvar1kalzhigitovnurbol.controllers;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.*;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.request.*;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.response.GenreResponse;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.response.MessageResponse;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/")
@EnableJms
public class AdminController {

    AuthorService authorService;

    BookCategoryService bookCategoryService;

    BookService bookService;

    GenreService genreService;

    PenaltyService penaltyService;

    RecordService recordService;

    RoleService roleService;

    UserService userService;

    MessageService messageService;

    JmsTemplate jmsTemplate;


    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }
    @Autowired
    public void setBookCategoryService(BookCategoryService bookCategoryService) {
        this.bookCategoryService = bookCategoryService;
    }
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }
    @Autowired
    public void setPenaltyService(PenaltyService penaltyService) {
        this.penaltyService = penaltyService;
    }
    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }
    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }



    @GetMapping("getAllUnResolvedMessage")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public ResponseEntity<?> getAllUnResolvedMessage(){
        List<Message> message = messageService.findMessageByStatusAndDestination("unresolved", "admin");

        return ResponseEntity.ok(message);
    }

    @GetMapping("getFirstUnResolvedMessage")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public ResponseEntity<?> getFirstUnResolvedMessage(){
        List<Message> message = messageService.findMessageByStatusAndDestination("unresolved", "admin");
        return ResponseEntity.ok(message.get(0));
    }

    @PostMapping("sendMessage")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> sendMessageToUser(@RequestBody MessageAdmin messageAdmin){
        if(messageAdmin.getId().isPresent()){
            Optional<Message> messageOptional = messageService.findById(messageAdmin.getId().get());
            messageOptional.get().setStatus("resolved");
            messageService.save(messageOptional.get());
            if(userService.existsByUsername(messageAdmin.getDestination().get())){
                Message message = new Message(messageAdmin.getDestination().get(), "verified", messageAdmin.getPayment().get(), messageAdmin.getDescription().get(), messageAdmin.getId().get());
                jmsTemplate.convertAndSend("VerifyReview", message);
                return new ResponseEntity<>("Message was send to " + messageAdmin.getDestination().get(), HttpStatus.OK);
            }
            return ResponseEntity.badRequest().body("Enter valid username");
        }
        return ResponseEntity.badRequest().body("Enter user message ID");
    }


    @PostMapping("addPenaltyToUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addPenaltyToUser(@RequestBody PenaltyRequest penaltyRequest){
        Optional<User> user = userService.findByUsername(penaltyRequest.getUsername().get());
        if(user.isPresent()){
            Set<Role> roles = user.get().getRoles();
            if(roles.size()>1){
                return ResponseEntity.badRequest().body(new MessageResponse("You cant give admin penalty"));
            }
            Penalty penalty = new Penalty(penaltyRequest.getName().get(), penaltyRequest.getDescription().get(), user.get());
            penaltyService.save(penalty);
            return ResponseEntity.ok().body(penaltyRequest.getUsername().get() + " get deserved penalty \n" + penalty);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Enter Valid Username"));
    }

    @DeleteMapping("deletePenalty/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deletePenalty(@PathVariable Long id){
        Optional<Penalty> penalty = penaltyService.findById(id);
        penalty.ifPresent(value -> penaltyService.delete(value));
        return ResponseEntity.ok().body("Successfully deleted:" + penalty);
    }

    @GetMapping("getPenalties")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getPenalties(){
        List<Penalty> penalties = penaltyService.findAll();
        return ResponseEntity.ok(penalties);
    }

    @PostMapping("/addGenre")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addGenre(@RequestBody AddGenreRequest addGenreRequest ) {
        if(!(addGenreRequest.getTitle().isPresent() || addGenreRequest.getDescription().isPresent())){
            return ResponseEntity.badRequest().body(new MessageResponse("Please Enter Valid Information"));
        }
        if (genreService.existsByTitle(addGenreRequest.getTitle().get())){
            return ResponseEntity.badRequest().body(new MessageResponse("This Genre is already exist"));
        }
        Genre genre = new Genre(addGenreRequest.getTitle().get(),addGenreRequest.getDescription().get());
        genreService.save(genre);
        return ResponseEntity.ok().body(genre);
    }

    @PostMapping("/addAuthor")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addAuthor(@RequestBody AddAuthorRequest addAuthorRequest ) {
        if(!(addAuthorRequest.getFirstName().isPresent() || addAuthorRequest.getLastName().isPresent())){
            return ResponseEntity.badRequest().body(new MessageResponse("Please fulfill all requirements"));
        }
        if (authorService.existsByFirstNameAndLastName(addAuthorRequest.getFirstName().get(), addAuthorRequest.getLastName().get())){
            return ResponseEntity.badRequest().body(new MessageResponse("This Author already exists"));
        }
        Author author = new Author(addAuthorRequest.getFirstName().get(),addAuthorRequest.getLastName().get());
        authorService.save(author);

        return ResponseEntity.ok().body(author);
    }

    @PostMapping("/addCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addCategory(@RequestBody AddCategoryRequest addCategoryRequest ) {
        if(!(addCategoryRequest.getTitle().isPresent() || addCategoryRequest.getDescription().isPresent())){
            return ResponseEntity.badRequest().body(new MessageResponse("Please Enter Valid Information"));
        }
        if (bookCategoryService.existsByTitle(addCategoryRequest.getTitle().get())){
            return ResponseEntity.badRequest().body(new MessageResponse("This Category already exists"));
        }
        BookCategory bookCategory = new BookCategory(addCategoryRequest.getTitle().get(),addCategoryRequest.getDescription().get());
        bookCategoryService.save(bookCategory);
        return ResponseEntity.ok().body(bookCategory);
    }

    @PostMapping("/addBook")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addBook(@RequestBody AddBookRequest addBookRequest) {
        if(!(addBookRequest.getTitle().isPresent() || addBookRequest.getDescription().isPresent()
                || addBookRequest.getCount().isPresent() || addBookRequest.getGenres().isPresent()
                || addBookRequest.getAuthors().isPresent()
                || addBookRequest.getBookCategory().isPresent() || addBookRequest.getCost().isPresent())){
            return ResponseEntity.badRequest().body(new MessageResponse("Please Enter Valid Information"));
        }

        if (bookService.existsByTitle(addBookRequest.getTitle().get())){
            return ResponseEntity.badRequest().body(new MessageResponse("book with this title already exists"));
        }

        Set<Genre> genreSet = new HashSet<>();
        for (Genre g : addBookRequest.getGenres().get()){
            Optional<Genre> genre = genreService.findById(g.getId());
            genre.ifPresent(genreSet::add);
        }

        Set<Author> authorSet = new HashSet<>();
        for (Author a : addBookRequest.getAuthors().get()){
            Optional<Author> author = authorService.findById(a.getId());
            author.ifPresent(authorSet::add);
        }

        Optional<BookCategory> bc = bookCategoryService.findById(addBookRequest.getBookCategory().get().getId());
        BookCategory bookCategory = null;
        if(bc.isPresent()){
            bookCategory = bc.get();
        }

        if(authorSet.isEmpty() || genreSet.isEmpty() || bookCategory == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Please Enter Valid Genres, Authors and bookCategory"));
        }

        Book book = new Book(addBookRequest.getTitle().get(), addBookRequest.getDescription().get(),
                addBookRequest.getImageURL().get(), addBookRequest.getPublication().get(),
                addBookRequest.getCount().get(),addBookRequest.getBinding().get(),
                genreSet, authorSet, bookCategory, addBookRequest.getCost().get());

        bookService.save(book);
        return ResponseEntity.ok().body(book);
    }


    @DeleteMapping("deleteGenre/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteGenre(@PathVariable Long id){
        Optional<Genre> genre = genreService.findById(id);
        genre.ifPresent(value -> genreService.delete(value));
        return ResponseEntity.ok().body("Successfully deleted:" + genre);
    }

    @DeleteMapping("deleteAuthor/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id){
        Optional<Author> author = authorService.findById(id);
        author.ifPresent(value -> authorService.delete(value));
        return ResponseEntity.ok().body("Successfully deleted:" + author);
    }

    @DeleteMapping("deleteCategory/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        Optional<BookCategory> bookCategory= bookCategoryService.findById(id);
        bookCategory.ifPresent(value -> bookCategoryService.delete(value));
        return ResponseEntity.ok().body("Successfully deleted:" + bookCategory);
    }

    @DeleteMapping("deleteBook/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        Optional<Book> book = bookService.findById(id);
        book.ifPresent(value -> bookService.delete(value));
        return ResponseEntity.ok().body("Successfully deleted:" + book);
    }


    @PutMapping("/updateGenre/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateGenre(@PathVariable Long id,@RequestBody AddGenreRequest addGenreRequest ) {
        Optional<Genre> genre = genreService.findById(id);
        if(genre.isPresent()){
            if (addGenreRequest.getTitle().isPresent()){
                genre.get().setTitle(addGenreRequest.getTitle().get());
            }
            if (addGenreRequest.getDescription().isPresent()){
                genre.get().setDescription(addGenreRequest.getDescription().get());
            }
            genreService.save(genre.get());
            return ResponseEntity.ok().body(genre);
        }
        else{
            return ResponseEntity.badRequest().body("no such genre present");
        }
    }

    @PutMapping("/updateAuthor/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id,@RequestBody AddAuthorRequest addAuthorRequest ) {
        Optional<Author> author = authorService.findById(id);
        if(author.isPresent()){
            if (addAuthorRequest.getFirstName().isPresent()){
                author.get().setFirstName(addAuthorRequest.getFirstName().get());
            }
            if (addAuthorRequest.getLastName().isPresent()){
                author.get().setLastName(addAuthorRequest.getLastName().get());
            }
            authorService.save(author.get());
            return ResponseEntity.ok().body(author);
        }
        else{
            return ResponseEntity.badRequest().body("no such author present");
        }
    }

    @PutMapping("/updateCategory/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> updateCategory(@PathVariable Long id,@RequestBody AddCategoryRequest addCategoryRequest) {
        Optional<BookCategory> bookCategory = bookCategoryService.findById(id);
        if(bookCategory.isPresent()){
            if (addCategoryRequest.getTitle().isPresent()){
                bookCategory.get().setTitle(addCategoryRequest.getTitle().get());
            }
            if (addCategoryRequest.getDescription().isPresent()){
                bookCategory.get().setDescription(addCategoryRequest.getDescription().get());
            }
            bookCategoryService.save(bookCategory.get());
            return ResponseEntity.ok().body(bookCategory);
        }
        else{
            return ResponseEntity.badRequest().body("no such category present");
        }
    }

    @PutMapping("/updateBook/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> editBook(@PathVariable Long id, @RequestBody AddBookRequest addBookRequest) {
        Optional<Book> book = bookService.findById(id);
        if(!book.isPresent()){
            return ResponseEntity.badRequest().body("there is no book with this id");
        }
        if(addBookRequest.getTitle().isPresent()){
            book.get().setTitle(addBookRequest.getTitle().get());
        }
        if(addBookRequest.getDescription().isPresent()){
            book.get().setDescription(addBookRequest.getDescription().get());
        }
        if(addBookRequest.getImageURL().isPresent()){
            book.get().setImageURL(addBookRequest.getImageURL().get());
        }
        if(addBookRequest.getPublication().isPresent()){
            book.get().setPublication(addBookRequest.getPublication().get());
        }
        if(addBookRequest.getCount().isPresent()){
            book.get().setCount(addBookRequest.getCount().get());
        }
        if(addBookRequest.getBinding().isPresent()){
            book.get().setBinding(addBookRequest.getBinding().get());
        }
        if(addBookRequest.getCost().isPresent()){
            book.get().setCost(addBookRequest.getCost().get());
        }
        if(addBookRequest.getGenres().isPresent()){
            Set<Genre> genreSet = new HashSet<>();
            for (Genre g : addBookRequest.getGenres().get()){
                Optional<Genre> genre = genreService.findById(g.getId());
                genre.ifPresent(genreSet::add);
            }
            book.get().setGenres(genreSet);
        }
        if(addBookRequest.getAuthors().isPresent()){
            Set<Author> authorSet = new HashSet<>();
            for (Author a : addBookRequest.getAuthors().get()){
                Optional<Author> author = authorService.findById(a.getId());
                author.ifPresent(authorSet::add);
            }
            book.get().setAuthors(authorSet);
        }
        if (addBookRequest.getBookCategory().isPresent()){
            Optional<BookCategory> bc = bookCategoryService.findById(addBookRequest.getBookCategory().get().getId());
            BookCategory bookCategory = null;
            if(bc.isPresent()){
                bookCategory = bc.get();
            }
            book.get().setBookCategory(bookCategory);
        }
        bookService.save(book.get());
        return ResponseEntity.ok().body(book.get());
    }







}
