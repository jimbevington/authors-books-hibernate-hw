import db.DBHelper;
import models.Author;
import models.Book;

import java.util.List;

public class Runner {

    public static void main(String[] args) {
        Author author1 = new Author("C.S", "Lewis");
        Author author2 = new Author("Margaret", "Atwood");

        Book book1 = new Book("The Lion, The Witch & The Wardrobe", author1);
        Book book3 = new Book("The Voyage of the Dawn Treader", author1);
        Book book2 = new Book("The Handmaids Tale", author2);

        DBHelper.save(author1);
        DBHelper.save(author2);
        DBHelper.save(book1);
        DBHelper.save(book2);
        DBHelper.save(book3);

        author1.setFirstName("Arnold");
        DBHelper.update(author1);

        List<Book> books = DBHelper.getAll("Book");
        List<Author> authors = DBHelper.getAll("Author");

        // for testing
//        DBHelper.delete(book1);
//        NOTE:-  found problem with Cascade Delete, couldn't delete the author without causing an error


    }
}
