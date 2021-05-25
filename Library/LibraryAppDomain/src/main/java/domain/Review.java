package domain;

import java.util.Objects;

public class Review implements Identifiable<Integer> {

    private int ID;
    private String text;
    private Book book;

    public Review() {

    }

    public Review(String text, Book book) {
        this.text = text;
        this.book = book;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public void setID(Integer id) {
        this.ID = id;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return ID == review.ID && Objects.equals(text, review.text) && Objects.equals(book, review.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, text, book);
    }

    @Override
    public String toString() {
        return "Review: ID = " + this.ID + ", text = " + this.text + ", book = " + book.getTitle();
    }
}
