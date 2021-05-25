import domain.Book;
import domain.Librarian;
import domain.Reader;
import repository.*;
import service.BookTerraException;
import service.Observable;
import service.ServiceInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service implements ServiceInterface {

    private static final int NUMBER_OF_THREADS = 5;

    private ReaderRepository readerRepository;
    private BookRepository bookRepository;
    private LibrarianRepository librarianRepository;
    private LibraryRepository libraryRepository;
    private ReviewRepository reviewRepository;
    private Map<Integer, Observable> connectedClients = new ConcurrentHashMap<>();

    public Service() {

    }

    public Service(ReaderRepository readerRepository, BookRepository bookRepository, LibrarianRepository librarianRepository,
                   LibraryRepository libraryRepository, ReviewRepository reviewRepository) {
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;
        this.librarianRepository = librarianRepository;
        this.libraryRepository = libraryRepository;
        this.reviewRepository = reviewRepository;
    }

    public ReaderRepository getReaderRepository() {
        return readerRepository;
    }

    public void setReaderRepository(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public LibrarianRepository getLibrarianRepository() {
        return librarianRepository;
    }

    public void setLibrarianRepository(LibrarianRepository librarianRepository) {
        this.librarianRepository = librarianRepository;
    }

    public LibraryRepository getLibraryRepository() {
        return libraryRepository;
    }

    public void setLibraryRepository(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public ReviewRepository getReviewRepository() {
        return reviewRepository;
    }

    public void setReviewRepository(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public synchronized Reader loginReader(String username, String password, Observable observableClient) throws BookTerraException {
        System.out.println("[Server]: Solving a login request for reader...");
        Reader reader = readerRepository.findReaderByUsernameAndPassword(username, password);
        if (reader != null) {
            if (connectedClients.get(reader.getID()) != null) {
                throw new BookTerraException("The user is already logged in!");
            }
            connectedClients.put(reader.getID(), observableClient);
        }
        System.out.println("The number of connected clients: " + connectedClients.size());
        return reader;
    }

    @Override
    public Librarian loginLibrarian(String username, String password, Observable observableClient) throws BookTerraException {
        System.out.println("[Server]: Solving a login request for librarian...");
        Librarian librarian = librarianRepository.findLibrarianByUsernameAndPassword(username, password);
        if (librarian != null) {
            if (connectedClients.get(librarian.getID()) != null) {
                throw new BookTerraException("The librarian is already logged in!");
            }
            connectedClients.put(librarian.getID(), observableClient);
        }
        System.out.println("The number of connected clients: " + connectedClients.size());
        return librarian;
    }

    @Override
    public List<Book> getAvailableBooks() throws BookTerraException {
        System.out.println("[Server]: Solving a getAvailableBooks request...");
        List<Book> books = new ArrayList<>();
        bookRepository.findAllByIsAvailable(true).forEach(books::add);

        System.out.println("[Server]: The list of available books is:");
        books.forEach(System.out::println);
        return books;
    }

    @Override
    public void borrowBooks(Reader reader, List<Book> booksToBorrow) throws BookTerraException {
        System.out.println("[Server]: Solving a borrowBooks request...");
        System.out.println("[Server]: The books to borrow are:");
        booksToBorrow.forEach(System.out::println);

        booksToBorrow.forEach(book -> {
            book.setIsAvailable(false);
            book.setBookingDate(new Date());
        });
        booksToBorrow.forEach(reader::addBook);
        readerRepository.modify(reader);
        notifyClientsBorrowedBooks();
    }

    @Override
    public void registerReader(Reader reader) throws BookTerraException {
        System.out.println("[Server]: Solving a registerReader request...");
        readerRepository.save(reader);
    }

    @Override
    public List<Book> getBorrowedBooksByReader(Reader reader) throws BookTerraException {
        System.out.println("[Server]: Solving a getBorrowedBooksByReader...");
        List<Book> borrowedBooks = new ArrayList<>();
        bookRepository.findAllBorrowedByReader(reader).forEach(borrowedBooks::add);
        return borrowedBooks;
    }

    private void notifyClientsBorrowedBooks() {
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        connectedClients.forEach((id, client) -> {
            if (client != null) {
                System.out.println("[Server]: Notifying client with id = " + id);
                try {
                    client.updateGraphicalInterface();
                } catch (BookTerraException | RemoteException exception) {
                    System.err.println("Error at updating the client for borrowed books: " + exception);
                }
            }
        });
        executorService.shutdown();
    }
}
