package controller;

import domain.Book;
import domain.Librarian;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.BookTerraException;
import service.Observable;
import service.ServiceInterface;
import view.ManageBooksView;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;

public class ManageBooksController extends UnicastRemoteObject implements Observable, Initializable {

    private ServiceInterface server;
    private ManageBooksView manageBooksView;
    private Librarian loggedInLibrarian;
    private ObservableList<Book> modelBooks = FXCollections.observableArrayList();

    @FXML
    private TableView<Book> tableViewBorrowedBooks;

    @FXML
    private TextField textFieldTitleAddBook;

    @FXML
    private TextField textFieldAuthorAddBook;

    @FXML
    private TextField textFieldPublicationYearAddBook;

    @FXML
    private TextField textFieldEditionAddBook;

    public ManageBooksController() throws RemoteException {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableViewBorrowedBooks.setItems(modelBooks);
    }

    public void setServer(ServiceInterface server) {
        this.server = server;
    }

    public void setLoggedInLibrarian(Librarian loggedInLibrarian) {
        this.loggedInLibrarian = loggedInLibrarian;
    }

    public void setManageBooksView(ManageBooksView manageBooksView) {
        this.manageBooksView = manageBooksView;
    }

    public ManageBooksView getManageBooksView() {
        return manageBooksView;
    }

    public void reloadBooks() {
        try {
            modelBooks.setAll(server.getBookRegister());
            if (modelBooks.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "There are no books!");
                alert.show();
            }
        } catch (BookTerraException exception) {
            System.err.println("Error at reloading the books model: " + exception);
            Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't reload the table!");
            alert.show();
        }
    }

    public void addBook() {
        String title = textFieldTitleAddBook.getText();
        String author = textFieldAuthorAddBook.getText();
        String publicationYearString = textFieldPublicationYearAddBook.getText();
        String edition = textFieldEditionAddBook.getText();
        if (!title.isBlank() && !author.isBlank() && !publicationYearString.isBlank() && !edition.isBlank()) {
            int publicationYear = Integer.parseInt(publicationYearString);
            try {
                boolean result = server.addBook(loggedInLibrarian, title, author, publicationYear, edition);
                if (!result) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error at adding the new book!");
                    alert.show();
                }
            } catch (BookTerraException exception) {
                System.err.println("Error at adding the new book: " + exception);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error at adding the new book!");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter all fields!");
            alert.show();
        }
    }

    @Override
    public void updateGraphicalInterface() throws BookTerraException, RemoteException {
        Platform.runLater(this::reloadBooks);
    }
}
