package controller;

import domain.Reader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import service.BookTerraException;
import service.ServiceInterface;
import view.LoginView;
import view.MainMenuReaderView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginViewController extends UnicastRemoteObject {

    private ServiceInterface server;
    private AvailableBooksController availableBooksController;
    private LoginView loginView;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private TextField textFieldPassword;

    public LoginViewController() throws RemoteException {

    }

    // Even though this should have been a Constructor initialization (based on the class diagram)
    // The way Java handles a FXML based Controller is unknown to me.
    public void setAvailableBooksController(AvailableBooksController availableBooksController) {
        this.availableBooksController = availableBooksController;
    }

    public void setServer(ServiceInterface server) {
        this.server = server;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public void loginReader() {
        String username = textFieldUsername.getText();
        String password = textFieldPassword.getText();
        if (!username.isBlank() && !password.isBlank()) {
            try {
                Reader loggedInReader = server.login(username, password, availableBooksController);
                if (loggedInReader != null) {
                    System.out.println("Successful authentication!");
                    MainMenuReaderView mainMenuReaderView = new MainMenuReaderView(server, availableBooksController, loggedInReader);
                    mainMenuReaderView.showMainMenuReaderView();

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
}
