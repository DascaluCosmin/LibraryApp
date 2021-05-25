package controller;

import domain.Reader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import service.BookTerraException;
import service.ServiceInterface;
import view.BorrowedBooksView;
import view.MainMenuReaderView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MainMenuReaderController extends UnicastRemoteObject {

    private ServiceInterface server;
    private AvailableBooksController availableBooksController;
    private MainMenuReaderView mainMenuReaderView;
    private Reader loggedInReader;

    @FXML
    private Label labelReader;

    public MainMenuReaderController() throws RemoteException {
    }

    public void setServer(ServiceInterface server) {
        this.server = server;
    }

    public void setAvailableBooksController(AvailableBooksController availableBooksController) {
        this.availableBooksController = availableBooksController;
    }

    public void setMainMenuReaderView(MainMenuReaderView mainMenuReaderView) {
        this.mainMenuReaderView = mainMenuReaderView;
    }

    public MainMenuReaderView getMainMenuReaderView() {
        return mainMenuReaderView;
    }

    public void setLoggedInReader(Reader loggedInReader) {
        this.loggedInReader = loggedInReader;
        availableBooksController.setLoggedInReader(loggedInReader);
        labelReader.setText("Hello, " + loggedInReader.getUsername());
    }

    public void showAvailableBooks() {
        availableBooksController.getAvailableBooksView().show();
        availableBooksController.reloadModelAvailableBooks();
        availableBooksController.getAvailableBooksView().setMainMenuReaderController(this);
        mainMenuReaderView.hide();
    }

    public void showBorrowedBooks() {
        try {
            BorrowedBooksView burrowedBooksView = new BorrowedBooksView(server, this, loggedInReader);
            burrowedBooksView.show();
            mainMenuReaderView.hide();
        } catch (IOException exception) {
            System.err.println("Error at showing the burrowed books: " + exception);
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error at showing the burrowed books!");
            alert.show();
        }
    }
}
