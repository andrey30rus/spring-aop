package event.manager.springaop;

import event.manager.springaop.entity.Book;
import event.manager.springaop.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAopApplication implements CommandLineRunner {
    @Autowired
    private final BookRepository bookRepository;

    public SpringAopApplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringAopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Book book1 = new Book("Война и Мир", "Лев Николаевич Толстой");
        Book book2 = new Book("Капитанская дочка", "А.C. Пушкин");
        bookRepository.save(book1);
        bookRepository.save(book2);

    }
}
