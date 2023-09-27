import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main {

    public static void main(String[] args){

        Library library = new Library();

        library.addNewBook(new Book("title 1.1", 2022, "author1"));
        library.addNewBook(new Book("title 1.1", 2022, "author1"));
        library.addNewBook(new Book("title 1.2", 2023, "author1"));
        library.addNewBook(new Book("title 2.1", 2022, "author2"));
        library.addNewBook(new Book("title 2.2", 2023, "author2"));
        library.addNewBook(new Book("title 2.2", 2023, "author2"));

        UUID bookToRemove = library.addNewBook(new Book("Book to be removed", 2023, "author2"));

        library.getList();

        library.removeBook(bookToRemove);

        library.getList();

        System.out.println("\n   --- Book Search ---");
        System.out.println(library.search(null, 2022, null));
        System.out.println(library.search("title 1.1", null, "author1"));
    }
}
