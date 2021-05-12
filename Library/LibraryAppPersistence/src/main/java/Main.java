import domain.Book;
import domain.Reader;
import repository.BookRepository;
import repository.BookRepositoryDB;
import repository.ReaderRepository;
import repository.ReaderRepositoryDB;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepositoryDB();
//        bookRepository.findAll().forEach(System.out::println);

        ReaderRepository readerRepository = new ReaderRepositoryDB();
//        readerRepository.findAll().forEach(System.out::println);
    }
}
