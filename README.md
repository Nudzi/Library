# Library
Simple java application for library management.

When starting the application, Data Base will automatically be created on localhost, something like this:
> http://localhost:8080/h2-console

Login with URL, UserName and Password from the resource file:
> [application.properties](https://github.com/Nudzi/Library/new/main?readme=1#:~:text=resources-,application,-.properties)

Available Functionalities:
  * Fetching all of the library books. [BooksController = GET /books](https://github.com/Nudzi/Library/new/main?readme=1#:~:text=BooksController.java-,BooksController,-.java)
  * Fetching specific library book. [BookController = GET /book/bookId](https://github.com/Nudzi/Library/new/main?readme=1#:~:text=BookController.java-,BookController,-.java)
  * Adding a new book to the library. [BooksController = POST /books](https://github.com/Nudzi/Library/new/main?readme=1#:~:text=BooksController.java-,BooksController,-.java)
  * Editing eixstin library book. [BookController = PUT /book/bookId](https://github.com/Nudzi/Library/new/main?readme=1#:~:text=BookController.java-,BookController,-.java)

**No cookies added or Authorisation for now.**
