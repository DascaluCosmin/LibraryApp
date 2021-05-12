package controller;

import domain.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import service.Observable;
import view.AvailableBooksView;
import view.SummaryBorrowedBooksView;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ResourceBundle;

public class SummaryBorrowedBooksController extends UnicastRemoteObject implements Initializable {

    private SummaryBorrowedBooksView summaryBorrowedBooksView;
    private AvailableBooksView availableBooksView;
    private ObservableList<Book> modelBurrowedBooks = FXCollections.observableArrayList();

    @FXML
    private TableView<Book> tableViewBorrowedBooksSummary;

    public SummaryBorrowedBooksController() throws RemoteException {
    }

    public void setSummaryBorrowedBooksView(SummaryBorrowedBooksView summaryBorrowedBooksView) {
        this.summaryBorrowedBooksView = summaryBorrowedBooksView;
    }

    public void setAvailableBooksView(AvailableBooksView availableBooksView) {
        this.availableBooksView = availableBooksView;
    }

    public void setModelBurrowedBooks(List<Book> burrowedBooks) {
        modelBurrowedBooks.setAll(burrowedBooks);
    }

    public void confirmBookBurrow() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "The books have been borrowed successfully! Happy reading!");
        alert.show();
        summaryBorrowedBooksView.hide();
        availableBooksView.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableViewBorrowedBooksSummary.setItems(modelBurrowedBooks);
    }
}
