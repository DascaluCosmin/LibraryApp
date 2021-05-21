package repository;

import domain.Book;
import domain.Reader;

public interface BookRepository extends Repository<Integer, Book> {

    Iterable<Book> findAllByIsAvailable(boolean isAvailable);

    Iterable<Book> findAllBorrowedByReader(Reader reader);
}
