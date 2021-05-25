package controller;

import domain.Book;
import domain.Reader;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import service.BookTerraException;
import service.Observable;
import service.ServiceInterface;
import view.AvailableBooksView;
import view.SummaryBorrowedBooksView;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AvailableBooksController extends UnicastRemoteObject implements Observable, Initializable, Serializable {

    private ServiceInterface server;
    private AvailableBooksView availableBooksView;
    private ObservableList<Book> modelAvailableBooks = FXCollections.observableArrayList();
    private Reader loggedInReader;
    private List<Book> booksAddedToCart = new ArrayList<>();

    @FXML
    private TableView<Book> tableViewAvailableBooks;

    public AvailableBooksController() throws RemoteException {

    }

    public void setAvailableBooksView(AvailableBooksView availableBooksView) {
        this.availableBooksView = availableBooksView;
    }

    public AvailableBooksView getAvailableBooksView() {
        return availableBooksView;
    }

    public void setServer(ServiceInterface server) {
        this.server = server;
    }

    public void setLoggedInReader(Reader loggedInReader) {
        this.loggedInReader = loggedInReader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableViewAvailableBooks.setItems(modelAvailableBooks);
    }

    @Override
    public void updateGraphicalInterface() throws BookTerraException, RemoteException {
        Platform.runLater(this::reloadModelAvailableBooks);
    }

    public void reloadModelAvailableBooks() {
        try {
            modelAvailableBooks.setAll(server.getAvailableBooks());
            if (modelAvailableBooks.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "There are no available books at this time!");
                alert.show();
            }
        } catch (BookTerraException e) {
            System.err.println("Error at reloading the available books model");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't reload the table!");
            alert.show();
        }
    }

    public void addToCart() {
        Book book = tableViewAvailableBooks.getSelectionModel().getSelectedItem();
        if (book != null) {
            booksAddedToCart.add(book);
            tableViewAvailableBooks.getSelectionModel().clearSelection();
        }
    }

    public void removeBookFromCart() {
        Book book = tableViewAvailableBooks.getSelectionModel().getSelectedItem();
        if (book != null) {
            booksAddedToCart.remove(book);
            tableViewAvailableBooks.getSelectionModel().clearSelection();
        }
    }

    public void borrowBooks() {
        if (booksAddedToCart.size() > 0) {
            try {
                booksAddedToCart.forEach(book -> {
                    book.setIsAvailable(false);
                    book.setBookingDate(new Date());
                    loggedInReader.addBook(book);
                });
                server.borrowBooks(loggedInReader, booksAddedToCart);

                SummaryBorrowedBooksView summaryBorrowedBooksView = new SummaryBorrowedBooksView(availableBooksView, booksAddedToCart);
                summaryBorrowedBooksView.show();
                booksAddedToCart.clear();
            } catch (BookTerraException exception) {
                System.err.println("Error at borrowing the books: " + exception);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't borrow the books!");
                alert.show();
            } catch (IOException exception) {
                System.err.println("Error at creating the Summary Borrowed Books View: " + exception);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error at showing the confirmation for the borrowed books!");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please add a book to cart!");
            alert.show();
        }
    }
}
