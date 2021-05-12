package domain;

import java.io.Serial;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Book implements Identifiable<Integer> {

    @Serial
    private static final long serialVersionUID = 12334547428888L;

    private int ISBN;
    private String title;
    private Integer publicationYear;
    private String edition;
    private boolean isAvailable;
    private String author;
    private String library; // TODO
    private Set<Reader> readers = new HashSet<>(0);

    public Book() {

    }

    public Book(int ISBN, String title, Integer publicationYear, String edition, boolean isAvailable, String author, Set<Reader> readers) {
        this.ISBN = ISBN;
        this.title = title;
        this.publicationYear = publicationYear;
        this.edition = edition;
        this.isAvailable = isAvailable;
        this.author = author;
        this.readers = readers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean available) {
        isAvailable = available;
    }

    public Set<Reader> getReaders() {
        return readers;
    }

    public void setReaders(Set<Reader> readers) {
        this.readers = readers;
    }

    public void addReader(Reader reader) {
        readers.add(reader);
    }

    public void removeReader(Reader reader) {
        readers.remove(reader);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getISBN() {
        return getID();
    }

    public void setISBN(int ISBN) {
        setID(ISBN);
    }

    public String getLibrary() {
        return "BCU";
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    @Override
    public void setID(Integer id) {
        this.ISBN = id;
    }

    @Override
    public Integer getID() {
        return this.ISBN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return ISBN == book.ISBN && isAvailable == book.isAvailable && Objects.equals(title, book.title) && Objects.equals(publicationYear, book.publicationYear) && Objects.equals(edition, book.edition) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN, title, publicationYear, edition, isAvailable, author);
    }

    @Override
    public String toString() {
        return "Book = " + this.title + ", ISBN = " + this.ISBN + ", author = " + this.author + ", edition = " + this.edition +
                ", publicationYear = " + this.publicationYear + ", isAvailable = " + this.isAvailable;
    }
}
