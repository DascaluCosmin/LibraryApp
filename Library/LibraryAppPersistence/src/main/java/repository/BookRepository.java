package repository;

import domain.Book;

public interface BookRepository extends Repository<Integer, Book> {

    Iterable<Book> findAllByIsAvailable(boolean isAvailable);
}
