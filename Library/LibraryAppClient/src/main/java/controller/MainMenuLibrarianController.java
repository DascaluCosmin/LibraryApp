package controller;

import domain.Librarian;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import service.ServiceInterface;
import view.MainMenuLibrarianView;
import view.ReturnBookView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MainMenuLibrarianController extends UnicastRemoteObject {

    private Librarian loggedInLibrarian;
    private ServiceInterface server;
    private ManageBooksController manageBooksController;
    private MainMenuLibrarianView mainMenuLibrarianView;

    @FXML
    private Label labelLibrarian;

    public MainMenuLibrarianController() throws RemoteException {

    }

    public void setLoggedInLibrarian(Librarian loggedInLibrarian) {
        this.loggedInLibrarian = loggedInLibrarian;
        labelLibrarian.setText("Hello, " + loggedInLibrarian.getName());
    }

    public void setManageBooksController(ManageBooksController manageBooksController) {
        this.manageBooksController = manageBooksController;
        this.manageBooksController.getManageBooksView().setMainMenuLibrarianController(this);
    }

    public void setServer(ServiceInterface server) {
        this.server = server;
    }

    public void setMainMenuLibrarianView(MainMenuLibrarianView mainMenuLibrarianView) {
        this.mainMenuLibrarianView = mainMenuLibrarianView;
    }

    public MainMenuLibrarianView getMainMenuLibrarianView() {
        return mainMenuLibrarianView;
    }

    public void showBookRegister() {
        manageBooksController.setLoggedInLibrarian(loggedInLibrarian);
        manageBooksController.reloadBooks();
        manageBooksController.getManageBooksView().show();
        mainMenuLibrarianView.hide();
    }

    public void returnBook() {
        try {
            ReturnBookView returnBookView = new ReturnBookView(server, this);
            mainMenuLibrarianView.hide();
        } catch (IOException e) {
            System.err.println("Error at showing the return book view!");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error at showing the return book form!");
            alert.show();
        }
    }
}
