import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            books.add(new Book(i, "title " + i, "author " + i));
        }
//        Library library = new Library("Lenina", books);
//        library.save("lib.dat");
        Library library = new Library();
        library.setTitle("Lenina");
        library.load("lib.dat");

        System.out.println(library.toString());
    }
}
