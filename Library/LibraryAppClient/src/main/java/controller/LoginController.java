package controller;

import domain.Librarian;
import domain.Reader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import service.BookTerraException;
import service.ServiceInterface;
import view.LoginView;
import view.MainMenuLibrarianView;
import view.MainMenuReaderView;
import view.RegisterView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginController extends UnicastRemoteObject {

    private ServiceInterface server;
    private AvailableBooksController availableBooksController;
    private ManageBooksController manageBooksController;
    private LoginView loginView;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private TextField textFieldPassword;

    public LoginController() throws RemoteException {

    }

    // Even though this should have been a Constructor initialization (based on the class diagram)
    // The way Java handles a FXML based Controller is unknown to me.
    public void setAvailableBooksController(AvailableBooksController availableBooksController) {
        this.availableBooksController = availableBooksController;
    }

    public void setManageBooksController(ManageBooksController manageBooksController) {
        this.manageBooksController = manageBooksController;
    }

    public void setServer(ServiceInterface server) {
        this.server = server;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public void loginReader() {
        String username = textFieldUsername.getText();
        String password = textFieldPassword.getText();
        if (!username.isBlank() && !password.isBlank()) {
            try {
                Reader loggedInReader = server.loginReader(username, password, availableBooksController);
                if (loggedInReader != null) {
                    System.out.println("Successful authentication!");
                    MainMenuReaderView mainMenuReaderView = new MainMenuReaderView(server, availableBooksController, loggedInReader);
                    mainMenuReaderView.show();

                    loginView.hide();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "The credentials are incorrect!");
                    alert.show();
                }
            } catch (BookTerraException | IOException exception) {
                System.err.println("Failed authentication: " + exception);
                Alert alert = new Alert(Alert.AlertType.ERROR, "The authentication has failed!");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter the credentials!");
            alert.show();
        }
    }

    public void loginLibrarian() {
        String username = textFieldUsername.getText();
        String password = textFieldPassword.getText();
        if (!username.isBlank() && !password.isBlank()) {
            try {
                Reader loggedInReader = server.loginReader(username, password, manageBooksController);
                if (loggedInReader != null) {
                    System.out.println("Successful authentication!");
                    MainMenuLibrarianView mainMenuLibrarianView = new MainMenuLibrarianView(server, manageBooksController, new Librarian()); // TODO
                    mainMenuLibrarianView.show();

                    loginView.hide();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "The credentials are incorrect!");
                    alert.show();
                }
            } catch (BookTerraException | IOException exception) {
                System.err.println("Failed authentication: " + exception);
                Alert alert = new Alert(Alert.AlertType.ERROR, "The authentication has failed!");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter the credentials!");
            alert.show();
        }
    }

    public void registerReader() {
        try {
            RegisterView registerView = new RegisterView(server, this);
            registerView.show();
            loginView.hide();
        } catch (IOException exception) {
            System.err.println("Error at loading the registration form: " + exception);
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error at showing the registration form");
            alert.show();
        }
    }
}
