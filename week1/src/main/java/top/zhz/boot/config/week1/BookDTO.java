package top.zhz.boot.config.week1;

public record BookDTO(Long id, String title, String author, double price) {
    public BookDTO {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
}
