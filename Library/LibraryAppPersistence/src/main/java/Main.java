import domain.Book;
import domain.Reader;
import domain.Review;
import repository.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepositoryDB();
        LibraryRepository libraryRepository = new LibraryRepositoryDB();
        LibrarianRepository librarianRepository = new LibrarianRepositoryDB();
        ReaderRepository readerRepository = new ReaderRepositoryDB();
        ReviewRepository reviewRepository = new ReviewRepositoryDB();

//
//        int id = reviewRepository.saveReturningID(review);
//        System.out.println(id);

//        book.addReview(review);
//
//
//        bookRepository.modify(book);
//        System.out.println(bookRepository.findOne(2));
//        System.out.println(bookRepository.findOne(3));
//
//        Book book = bookRepository.findOne(3);
//        System.out.println(book);
//
//        Reader reader = readerRepository.findOne(1);
//        reader.getBooks().forEach(System.out::println);
//
//        reader.addBook(book);
//
//        reader.getBooks().forEach(System.out::println);
//
//        book.setBookingDate(new Date());
//        book.setIsAvailable(false);
//        readerRepository.modify(reader);
//        reader.addBook(book);
//
//        reader.removeBook(book);
//        book.setBookingDate(null);
//        book.setIsAvailable(true);

//        reader.getBooks().forEach(System.out::println);

//        readerRepository.save(reader);
//        bookRepository.modify(book);
//        readerRepository.modify(reader);


//
//        System.out.println(libraryRepository.findOne(1));
//        System.out.println(libraryRepository.findOne(2));

//        Book book = new Book();
////        book.setTitle("Becoming");
////        book.setPublicationYear(2019);
////        book.setEdition("first edition");
////        book.setIsAvailable(false);
////        book.setAuthor("Michelle Obama");
////        book.setLibrary(libraryRepository.findOne(1));
////        book.setBookingDate(new Date());
//
//        Reader reader = readerRepository.findOne(1);
//        book = bookRepository.findOne(3);
////        book.addReader(reader);
////        bookRepository.save(book);
////        bookRepository.save(book);
//
//        reader.addBook(book);
//        readerRepository.modify(reader);

//        System.out.println(book);
//        book.getReaders().forEach(System.out::println);
//        book.removeReader(reader);
//        book.getReaders().forEach(System.out::println);
//        bookRepository.modify(book);

//        System.out.println(bookRepository.findOne(3));

//        bookRepository.findAll().forEach(book -> {
//            System.out.println(book.getLibrary().getAddress());
//        });

//        ReaderRepository readerRepository = new ReaderRepositoryDB();
//        readerRepository.findAll().forEach(System.out::println);
    }
}
