package event.manager.springaop.controller;

import event.manager.springaop.entity.Book;
import event.manager.springaop.service.BookService;
import event.manager.springaop.util.CustomResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/books")
    public CustomResponse<Book> getAll(){
        return bookService.getAll();
    }
    @GetMapping("/books/{title}")
    public CustomResponse<Book> getBookByTitle(@PathVariable("title") String title){
        return bookService.getBookByTitle(title);
    }
    @PostMapping("/books")
    public CustomResponse<Book> addBook(Book book){
        return bookService.addBook(book);
    }
}
