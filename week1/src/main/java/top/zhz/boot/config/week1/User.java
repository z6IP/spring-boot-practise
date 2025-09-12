package top.zhz.boot.config.week1;

public class User {
    private Long id;
    private String name;
    private int age;
    // Constructors, getters, and setters
    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
}
