package event.manager.springaop.service;

import event.manager.springaop.entity.Book;
import event.manager.springaop.repository.BookRepository;
import event.manager.springaop.util.CustomResponse;
import event.manager.springaop.util.CustomStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public CustomResponse<Book> getAll() {
        List<Book> books;
        try {
            log.info("Попытка получить все книги");
            books = bookRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new CustomResponse<>(null, CustomStatus.EXCEPTION);
        }
        log.info("Все книги получены");
        return new CustomResponse<>(books, CustomStatus.SUCCESS);
    }

    public CustomResponse<Book> getBookByTitle(String title) {
        log.info("Пытается получить книгу с названием {}", title);
        Book book;
        try {
            book = bookRepository.findBookByTitle(title).orElseThrow();
        } catch (NoSuchElementException e) {
            log.error(e.getMessage(), e);
            return new CustomResponse(null, CustomStatus.NOT_FOUND);
        } catch (Exception e) {
            return new CustomResponse<>(null, CustomStatus.EXCEPTION);
        }
        log.info("Книга с названием {}", title);
        return new CustomResponse<>(Stream.of(book).collect(Collectors.toList()), CustomStatus.SUCCESS);
    }

    public CustomResponse<Book> addBook(Book book) {
//        w/o aspects
//        Book newBook = null;
//        try {
//            log.info("Попытка добавить книгу с нзванием {}", book.getTitle());
//            newBook = bookRepository.save(book);
//        } catch (Exception e) {
//            return new CustomResponse<>(null, CustomStatus.EXCEPTION);
//        }
//
//        log.info("Книга с названием {} успешно сохранена", book.getTitle());
//        return new CustomResponse<>(Stream.of(newBook).collect(Collectors.toList()), CustomStatus.SUCCESS);
//        after AOP
        Book newBook = bookRepository.save(book);
        return new CustomResponse<>(Stream.of(newBook).collect(Collectors.toList()), CustomStatus.SUCCESS);

    }
}
