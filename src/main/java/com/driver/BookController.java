package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    @Autowired
    BookService bookService;

    // One example controller, make the rest by yourself
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book newBook= bookService.createBook(book);
        return new ResponseEntity<>(newBook,HttpStatus.CREATED);
    }
    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity<Book> findBookByID(@PathVariable("id") int id){
        Book book=  bookService.findBookById(id);
        return new ResponseEntity<>(book,HttpStatus.FOUND);
    }
    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> findAllBooks(){
        List<Book> bookList=bookService.findAllBooks();
        return new ResponseEntity<>(bookList,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-book-by-author")
    public ResponseEntity<List<Book>> findBooksByAuthor(@RequestParam("author") String author){
        List<Book>bookList=bookService.findBooksByAuthor(author);
        return new ResponseEntity<>(bookList,HttpStatus.GATEWAY_TIMEOUT);
    }
    @GetMapping("/get-book-by-genre")
    public ResponseEntity<List<Book>> findBooksByGenre(@RequestParam("genre") String genre){
        List<Book>bookList=bookService.findBooksByGenre(genre);
        return new ResponseEntity<>(bookList,HttpStatus.GATEWAY_TIMEOUT);
    }
    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") String id){
       String response= bookService.deleteBookById(id);
        return new ResponseEntity<>(response,HttpStatus.REQUEST_TIMEOUT);
    }

    @DeleteMapping("/delete-all-books")
    public ResponseEntity<String> deleteAllBooks(){
        String response= bookService.deleteAllBooks();
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
}
