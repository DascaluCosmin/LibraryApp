package service;

import domain.Book;
import domain.Librarian;
import domain.Reader;

import java.util.List;

public interface ServiceInterface {

    Reader loginReader(String username, String password, Observable observableClient) throws BookTerraException;

    Librarian loginLibrarian(String username, String password, Observable observableClient) throws BookTerraException;

    List<Book> getAvailableBooks() throws BookTerraException;

    void borrowBooks(Reader reader, List<Book> booksToBorrow) throws BookTerraException;

    void registerReader(Reader reader) throws BookTerraException;

    List<Book> getBorrowedBooksByReader(Reader reader) throws BookTerraException ;

    List<Book> getUnreturnedBooks(Reader reader) throws BookTerraException;

    List<Book> getBookRegister() throws BookTerraException;

    boolean addBook(Librarian librarian, String title, String author, Integer publicationYear, String edition) throws BookTerraException;
}
