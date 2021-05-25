package domain;

import java.io.Serial;
import java.util.Date;
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
    private Library library;
    private Date bookingDate;
    private Set<Reader> readers = new HashSet<>(0);
    private Set<Review> reviews = new HashSet<>(0);

    public Book() {

    }

    public Book(String title, Integer publicationYear, String edition, boolean isAvailable, String author, Library library, Date bookingDate, Set<Reader> readers, Set<Review> reviews) {
        this.title = title;
        this.publicationYear = publicationYear;
        this.edition = edition;
        this.isAvailable = isAvailable;
        this.author = author;
        this.library = library;
        this.bookingDate = bookingDate;
        this.readers = readers;
        this.reviews = reviews;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Library getLibrary() {
        return library;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review) {
        reviews.add(review);
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
                ", publicationYear = " + this.publicationYear + ", isAvailable = " + this.isAvailable + ", booking date = " + this.bookingDate +
                ", library = " + this.library + ", number of review = " + this.reviews.size();
    }
}
