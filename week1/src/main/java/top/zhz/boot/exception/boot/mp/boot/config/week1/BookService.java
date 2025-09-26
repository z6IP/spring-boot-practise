package top.zhz.boot.exception.boot.mp.boot.config.week1;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    public List<BookDTO> getAllBooks() {
        return List.of(
                new BookDTO(1L, "Java Programming", "Alice", 29.99),
                new BookDTO(2L, "Spring Boot in Action", "Bob", 34.99)
        );
    }
}
