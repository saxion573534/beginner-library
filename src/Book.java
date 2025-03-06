import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Book {
    //id;title;author;isbn;version
    private final int id;
    private final String title;
    private final String author;
    private final long isbn;
    private final String version;

    public Book(int id, String title, String author, long isbn, String version) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.version = version;
    }

    public static ArrayList<Book> readData() {
        ArrayList<Book> books = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("resources/books.csv"));
            scanner.nextLine();

            while (scanner.hasNext()) {
                String[] result = scanner.nextLine().split(";");
                Book book = new Book(Integer.parseInt(result[0]), result[1], result[2], Long.parseLong(result[3]), result[4]);
                books.add(book);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        return books;
    }
}
