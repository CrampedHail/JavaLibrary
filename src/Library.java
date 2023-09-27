import java.util.*;
import java.util.stream.Collectors;

public class Library {

    private final Map<UUID, Book> books;
    private final Map<UUID, String> booksLent;

    public Library() {
        this.books = new HashMap<>();
        this.booksLent = new HashMap<>();
    }

    UUID addNewBook(Book book){
        UUID uuid = UUID.randomUUID();
        books.put(uuid, book);
        return uuid;
    }

    void removeBook(UUID uuid){
        if(books.containsKey(uuid) && !booksLent.keySet().contains(uuid))
            books.remove(uuid);
        else System.out.println("This book does not exist in Library");
    }

    void getList(){
        Set<Book> bookSet = new HashSet<>(books.values());
        Map<Book, Integer> booksAvailableNumber = new HashMap<>();
        Map<Book, Integer> booksLentNumber = new HashMap<>();
        for(Map.Entry<UUID, Book> entry : books.entrySet()){
            UUID uuid = entry.getKey();
            Book book = entry.getValue();

            if(booksLent.containsKey(uuid)) {
                int number = booksLentNumber.get(book) != null ?
                        booksLentNumber.get(book) : 0;
                booksLentNumber.put(book, number + 1);
            }
            else{
                List<Book> booksFound = booksAvailableNumber.keySet().stream().filter(b -> b.equals(book)).collect(Collectors.toList());
                if(booksFound.size()>0){
                    booksAvailableNumber.put(booksFound.get(0), booksAvailableNumber.get(book)+1);
                }
                else{
                    booksAvailableNumber.put(book, 1);
                }
            }
            booksAvailableNumber.put(book, booksAvailableNumber.get(book)==null? 0 : booksAvailableNumber.get(book));
            booksLentNumber.put(book, booksLentNumber.get(book)==null? 0 : booksLentNumber.get(book));
        }
        System.out.println("\n   --- Book List ---");
        bookSet.forEach(b -> System.out.println(b.toString()+" - "+booksAvailableNumber.get(b)+" available | "+booksLentNumber.get(b)+" lent"));
    }

    List<Book> search(String title, Integer year, String author){
        return books.values().stream()
                .filter(b -> {if(title!=null && !title.isEmpty())return title.equals(b.getTitle()); else return true;})
                .filter(b -> {if(year!=null)return year.equals(b.getYear()); else return true;})
                .filter(b -> {if(author!=null && !author.isEmpty())return author.equals(b.getAuthor()); else return true;})
                .collect(Collectors.toList());
    }

    void lendABook(UUID uuid, String name){
        if(!booksLent.containsKey(uuid))
            booksLent.put(uuid, name);
        else System.out.println("This book is already lent");
    }

    void returnABook(UUID uuid){
        if(booksLent.containsKey(uuid))
            booksLent.remove(uuid);
    }

    void printBooksDetails(UUID uuid){
        String printString = books.get(uuid).toString();
        if(booksLent.containsKey(uuid)) printString+=" is currently lent by "+booksLent.get(uuid);
        else printString+=" is available";
        System.out.println(printString);
    }
}
