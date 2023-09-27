import java.util.Objects;

public class Book {
    private final String title;
    private final int year;
    private final String author;

    Book(String title, int year, String author) {
        this.title = title;
        this.year = year;
        this.author = author;
    }

    String getTitle() {
        return title;
    }

    int getYear() {
        return year;
    }

    String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", author='" + author + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return year == book.year && title.equals(book.title) && author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year, author);
    }
}
