import domain.Book;
import domain.Reader;
import repository.BookRepository;
import repository.ReaderRepository;
import service.BookTerraException;
import service.Observable;
import service.ServiceInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service implements ServiceInterface {

    private static final int NUMBER_OF_THREADS = 5;

    private ReaderRepository readerRepository;
    private BookRepository bookRepository;
    private Map<Integer, Observable> connectedClients = new ConcurrentHashMap<>();

    public Service() {

    }

    public Service(ReaderRepository readerRepository, BookRepository bookRepository) {
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;
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

    @Override
    public synchronized Reader login(String username, String password, Observable observableClient) throws BookTerraException {
        System.out.println("[Server]: Solving a login request...");
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
    public List<Book> getAvailableBooks() throws BookTerraException {
        System.out.println("[Server]: Solving a getAvailableBooks request...");
        List<Book> books = new ArrayList<>();
        bookRepository.findAllByIsAvailable(true).forEach(books::add);
        return books;
    }

    @Override
    public void borrowBooks(Reader reader, List<Book> booksToBorrow) throws BookTerraException {
        System.out.println("[Server]: Solving a borrowBooks request...");
        booksToBorrow.forEach(book -> book.setIsAvailable(false));
        booksToBorrow.forEach(reader::addBook);
        readerRepository.modify(reader);
        notifyClientsBorrowedBooks();
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
