package service;

import domain.Book;
import domain.Reader;

import java.util.List;

public interface ServiceInterface {

    Reader loginReader(String username, String password, Observable observableClient) throws BookTerraException;

    List<Book> getAvailableBooks() throws BookTerraException;

    void borrowBooks(Reader reader, List<Book> booksToBorrow) throws BookTerraException;

    void registerReader(Reader reader) throws BookTerraException;
}
