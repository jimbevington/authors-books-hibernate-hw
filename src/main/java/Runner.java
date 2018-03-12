import db.DBHelper;
import models.Author;
import models.Book;

public class Runner {

    public static void main(String[] args) {
        Author author1 = new Author("C.S", "Lewis");
        Author author2 = new Author("Margaret", "Atwood");

        Book book1 = new Book("The Lion, The Witch & The Wardrobe", author1);
        Book book2 = new Book("The Handmaids Tale", author2);

        DBHelper.save(author1);
        DBHelper.save(author2);
        DBHelper.save(book1);
        DBHelper.save(book2);
    }
}
