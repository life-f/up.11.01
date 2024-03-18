import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library implements Externalizable {
    private String title;
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public Library(String title, List<Book> books) {
        this.title = title;
        this.books = books;
    }

    @Override
    public String toString() {
        return "Library{" +
                "title='" + title + '\'' +
                ", books=" + books +
                '}';
    }

    public void save(String path) {
//        File file = new File("lib.txt");
//        try {
//            FileWriter fos = new FileWriter(file);
//            fos.write(this.title);
//            for (Book book : books) {
//                fos.write(book.getId() + ";" +
//                        book.getTitle() + ";" +
//                        book.getAuthor());
//            }
//            fos.flush();
//            fos.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            for (Book book : books) {
                oos.writeObject(book);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(String path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            for (int i = 0; i < 10; i++) {
                books.add((Book) ois.readObject());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(title);
        out.writeObject(books);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        title = (String) in.readObject();
        books = (List<Book>) in.readObject();
    }
}
