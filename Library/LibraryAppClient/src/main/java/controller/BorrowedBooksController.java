package controller;

import domain.Book;
import domain.Reader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import service.BookTerraException;
import service.ServiceInterface;
import view.BorrowedBooksView;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ResourceBundle;

public class BorrowedBooksController extends UnicastRemoteObject implements Initializable {

    private static final String UNRETURNED_BOOKS_LABEL_TEXT = "Unreturned books";
    private static final String BORROWED_BOOKS_LABEL_TEXT = "Borrowed books";

    private ServiceInterface server;
    private BorrowedBooksView borrowedBooksView;
    private Reader loggedInReader;
    private ObservableList<Book> modelBurrowedBooks = FXCollections.observableArrayList();

    @FXML
    private Label borrowedBooksLabel;

    @FXML
    private TableView<Book> tableViewBorrowedBooks;

    public BorrowedBooksController() throws RemoteException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            tableViewBorrowedBooks.setItems(modelBurrowedBooks);
    }

    public void setServer(ServiceInterface server) {
        this.server = server;
    }

    public void setBorrowedBooksView(BorrowedBooksView borrowedBooksView) {
        this.borrowedBooksView = borrowedBooksView;
    }

    public void setLoggedInReader(Reader loggedInReader) {
        this.loggedInReader = loggedInReader;
    }

    public void showBorrowedBooks() {
        try {
            modelBurrowedBooks.setAll(server.getBorrowedBooksByReader(loggedInReader));
            borrowedBooksLabel.setText(BORROWED_BOOKS_LABEL_TEXT);
        } catch (BookTerraException exception) {
            System.err.println("Error at getting the borrowed books by reader: " + exception);
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error at getting the borrowed books!");
            alert.show();
        }
    }

    public void showUnreturnedBooks() {
        try {
            List<Book> unreturnedBooks = server.getUnreturnedBooks(loggedInReader);
            modelBurrowedBooks.setAll(unreturnedBooks);
            borrowedBooksLabel.setText(UNRETURNED_BOOKS_LABEL_TEXT);
            if (unreturnedBooks.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "You have no unreturned books!");
                alert.show();
            }
        } catch (BookTerraException exception) {
            System.err.println("Error at getting the unreturned books by reader: " + exception);
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error at getting the unreturned books!");
            alert.show();
        }
    }
}
