package controller;

import domain.Librarian;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import service.ServiceInterface;
import view.MainMenuLibrarianView;

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
    }

    public void setServer(ServiceInterface server) {
        this.server = server;
    }

    public void setMainMenuLibrarianView(MainMenuLibrarianView mainMenuLibrarianView) {
        this.mainMenuLibrarianView = mainMenuLibrarianView;
    }

    public void showManageBooksView() {
        manageBooksController.getManageBooksView().show();
        manageBooksController.setLoggedInLibrarian(loggedInLibrarian);
        mainMenuLibrarianView.hide();
    }
}
