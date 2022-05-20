# Kalzhigitov Nurbol ITSE-1908R
## Topic of Final Exam: Library Management System

###1) 
UML DIAGRAM:

![Library Management System](C:\Users\Nur\Desktop\Java Proj\Var1Kalzhigitov.png "Library Management System")

###2) 
BackUp file exist within this .zip file (as also project itself and postman collection)

- - name of backup .tar file: variant1_Kalzhigitov.tar

###3) 
In this project I am going to implement Library Management System. In this project, two types of user will be implemented: Admin and User. The following functions will be available to the admin: Adding, Updating, Deleting, Processing messages from users and issuing blocks for the user for violations. The user himself has the functions of viewing and adding for consideration to the admin. In total, 34 rest requests were implemented.

Endpoints: 
1) (POST) Register as Admin or User: http://localhost:8081/api/auth/register

- -  raw/json
- - - {
      "username":"test",
      "email":"test@bk.ru",
      "role":["user","admin"],
      "password":"testTest"
      }

2) (POST) Login as Admin or User: http://localhost:8081/api/auth/login

- -  raw/json
- - - {
      "username":"NeNurbol",
      "password":"Steve2002"
      }

3) (POST) Update or Post Record User Info: http://localhost:8081/api/updateUserInfo

- -  raw/json
- - - {
      "firstName":"NeNurbol",
      "secondName":"NeKalzhigitov",
      "middleName":"NeKuvandikovich",
      "dateOfBirth":"2002-04-22",
      "address":"NeSeiffulin 510, 144"
      }

4) (POST) Add Genre: http://localhost:8081/api/admin/addGenre

- -  raw/json
- - - {
      "title":"Realist Literature1",
      "description":"1Realist fiction novels are set in a time and place that could actually happen in the real world. They depict real people, places, and stories in order to be as truthful as possible. Realist works of fiction remain true to everyday life and abide by the laws of nature as we currently understand them."
      }

5) (POST) Add Author: http://localhost:8081/api/admin/addAuthor

- -  raw/json
- - - {
      "firstName":"Herman1",
      "lastName":"Melville1"
      }

6) (POST) Add Category: http://localhost:8081/api/admin/addCategory

- -  raw/json
- - - {
      "title":"nonfiction books1",
      "description":"nonfiction books1"
      }

7) (POST) Add Book: http://localhost:8081/api/admin/addBook

- -  raw/json
- - - {
      "title":"Don Quixote1",
      "description":"Alonso Quixano, a retired country gentleman in his fifties, lives in an unnamed section of La Mancha with his niece and a housekeeper. He has become obsessed with books of chivalry.",
      "count":"10",
      "genres":[
      {"id":"4"}
      ,{"id":"5"}
      ],
      "authors":[
      {"id":"3"}
      ,{"id":"4"}
      ],
      "bookCategory":{
      "id":"3"},
      "cost":"12.99"
      }


8) (POST) Send Message as User to Admin: http://localhost:8081/api/sendMessage

- -  raw/json
- - - {
      "books":[
      {"id":"1"}
      ,{"id":"2"}]
      }

9) (GET) Get all incoming message (unresolved) to Admin as Admin: http://localhost:8081/api/admin/getAllUnResolvedMessage

10) (GET) Get all unresolved and first message: http://localhost:8081/api/admin/getFirstUnResolvedMessage


11) (POST) Send message to user as answer (As Admin): http://localhost:8081/api/admin/sendMessage

- -  raw/json
- - - {
      "id":"28",
      "payment":"89.99",
      "destination": "sdlrowhinkal"
      }


12) (GET) Get Answer from admin to user message as User: http://localhost:8081/api/getMessage/28

13) (GET) Get all Genres: http://localhost:8081/api/getGenres

14) (GET) Get all Authors: http://localhost:8081/api/getAuthors

15) (GET) Get all Books: http://localhost:8081/api/getBooks

16) (GET) Get all Books Categories: http://localhost:8081/api/getCategories

17) (GET) Get specific book: http://localhost:8081/api/getGenre/{id}

18) (GET) Get specific author: http://localhost:8081/api/getAuthor/{id}

19) (GET) Get specific genre: http://localhost:8081/api/getGenre/{id}

20) (GET) Get specific book category: http://localhost:8081/api/getCategory/{id}

21) (DELETE) Delete specific book category (As Admin): http://localhost:8081/api/admin/deleteCategory/{id}

22) (DELETE) Delete specific genre (As Admin): http://localhost:8081/api/admin/deleteGenre/{id}

23) (DELETE) Delete specific author (As Admin): http://localhost:8081/api/admin/deleteAuthor/{id}

24) (DELETE) Delete specific book (As Admin): http://localhost:8081/api/admin/deleteBook/{id}

25) (UPDATE) Update specific genre by id (As Admin): http://localhost:8081/api/admin/updateGenre/{id}

- -  raw/json
- - - {
      "title":"Realist Literature",
      "description":"Realist fiction novels are set in a time and place that could actually happen in the real world. They depict real people, places, and stories in order to be as truthful as possible. Realist works of fiction remain true to everyday life and abide by the laws of nature as we currently understand them."
      }

26) (UPDATE) Update specific author by id (As Admin): http://localhost:8081/api/admin/updateAuthor/{id}

- -  raw/json
- - - {
      "firstName":"Herman",
      "lastName":"Melville"
      }

27) (UPDATE) Update specific book category by id (As Admin): http://localhost:8081/api/admin/updateCategory/{id}

- -  raw/json
- - - {
      "title":"nonfiction books",
      "description":"nonfiction books"
      }


28) (UPDATE) Update specific book by id (As Admin): http://localhost:8081/api/admin/updateBook/{id}

- -  raw/json
- - - {
      "title":"Don Quixote",
      "description":"Alonso Quixano, a retired country gentleman in his fifties, lives in an unnamed section of La Mancha with his niece and a housekeeper. He has become obsessed with books of chivalry.",
      "count":"10",
      "genres":[
      {"id":"4"}
      ,{"id":"5"}
      ],
      "authors":[
      {"id":"3"}
      ,{"id":"4"}
      ],
      "bookCategory":{
      "id":"3"},
      "cost":"12.99"
      }


29) (OPTIONS) Get status: http://localhost:8081/api/getStatus

30) (HEAD) Print header info: http://localhost:8081/api/getHeader

31) (POST) Give user ban or penalty (As Admin): http://localhost:8081/api/admin/addPenaltyToUser

- -  raw/json
- - - {
      "username":"NeNurbol",
      "name":"Ban For Something",
      "description": "Some reason for banning you."
      }

32) (DELETE) Delete penalty from user by id (As Admin): http://localhost:8081/api/admin/deletePenalty/{id}

33) (GET) Get all penalties (As Admin for all user): http://localhost:8081/api/admin/getPenalties

34) (GET) Check your own penalty (As User): http://localhost:8081/api/checkPenalty

###4)
Use different type of beans annotations

- Example: [@Bean](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/config/JmsConfig.java), [@Configuration](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/config), [@Component](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/jms/MessageReciever.java), [@Repository](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/repository), [@Service](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/service), [@RestController](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/controllers), [@Aspect](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/config/ConfLogger.java)

Were used.

###5)
Use different type of Dependency Injections. (ONLY CONSTRUCTOR and
Setter injection. NO FIELD injection)
- Example: [Setter injection](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/controllers) and [constructor](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/service) were used.

###6)
[Write good service logic in service classes. (If your most port of code will consist only calling repository methods, -50% from your grade)](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol)

###7)
Already realized in 4 point

###8)
Already realized in 4 point

###9)
[ConfLogger](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/config/ConfLogger.java)
###10)
Use next annotations: @Before, @Pointcut, @After.
- Example in [ConfLogger](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/config/ConfLogger.java)


###11)
Add real service/business logic in AOP code.
- Example in [ConfLogger](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/config/ConfLogger.java)
```
    @After("allServiceMethods()")
    public void AdviceAfter(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        log.info("Method was initialized" + methodName);
    }

    @After("messageSender()")
    public void AdviceSenderAfter(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        log.info("New Message to Admin!!!!!!!!!!!!!!!!!!!");
    }

    @After("messageSenderAdmin()")
    public void AdviceSenderAdminAfter(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        log.info("Admin send message to user");
    }
```


###12)
Add Jpa repository support.
[@Repository](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/repository)

###13)
Add cache configuration.
- Example @Cacheable("Books"), @Cacheable("Authors"), @Cacheable("Genres"), @Cacheable("Categories") in [UserController](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/controllers/UserController.java)
###14)
Use different type of Query creation
- Example in [Repository](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/repository)

###15)
Use Annotation based named query configuration
[Repository](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/repository)

###16)
Declare query at the query method using @Query
[Repository](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/repository)

###17)
Use SpEL expressions
[Repository](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/repository)

###18)
Use 
- [Transactionality](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/service)
- [Locking](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/repository)
- [Auditing](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/config/AuditConfiguration.java)

###19)
Use JSR-349 Bean Validation
[Models](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/models)
###20)
Use attributes: [All Attributes used in...](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/service)

###21)
[Write scheduled method](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/scheduling/scheduleManager.java). Use @Scheduled annotations with attributes:
- fixedDelay
- fixedRate
- initialDelay

```
    @Async
    @Scheduled(fixedRate = 5000)
    public void fixedRate() {
        log.info("reportFixedRate:" + dateFormat.format(new Date()));
    }

    @Async
    @Scheduled(fixedDelay = 10000)
    public void fixedDelay() {
        log.info("reportFixedDelay:" + dateFormat.format(new Date()));
    }

    @Scheduled(initialDelay = 2500, fixedDelay = 2500)
    public void initialDelay() {
        log.info("reportInitialDelay:" + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "${cron.expression}")
    public void inCronExpressions() {
        log.info("reportInCronExpressions:" + dateFormat.format(new Date()));
    }
```

###22)
Use all next methods: GET, HEAD, PUT, POST, DELETE, OPTIONS 
- used in 3 points (when all endpoints were described)

###23)
Use next annotations:
- GetMapping
- PostMapping
- PutMapping
- DeleteMapping
- -  [Controllerss](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/controllers).

###24)
Use RequestBody and ResponseBody Annotations. Read HTTP Headers in
Spring REST [Controllerss](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/controllers).
- Also was added 
- - [request](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/request).
- - [response](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/response).

###25)
Use Spring @ResponseStatus to Set HTTP Status Code. Use Spring
ResponseEntity to Manipulate the HTTP Response
- Examples in: [Controllerss](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/controllers).

###26)
Add JUnit test with at least 60% code coverage.
[JUnit](src/test/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/service).

###27)
Use different type of Assertions
- Example:
```
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
```
###28)
Use [ReflectionTestUtils](src/test/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/service).

###29)
Write JMS service. 1 method which send data to topic, second method
which listen topic.
- Example of [JMS](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/jms/MessageReciever.java).
```
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
```
###30)
Use Spring Security Basic Authentication:
- [security](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/security).
###31)
Use BasicAuthenticationEntryPoint:
- [security](src/main/java/com/example/finalexamlibraryvar1kalzhigitovnurbol/security).

###32)
Write CURL in README.md for your ALL endpoints, or upload in
project folder POSTMAN collections.
- Answer: In point 3.

#END.
