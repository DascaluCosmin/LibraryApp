package controller;

import domain.Reader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import service.BookTerraException;
import service.ServiceInterface;
import view.RegisterView;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RegisterController extends UnicastRemoteObject {

    private RegisterView registerView;
    private ServiceInterface server;
    private LoginController loginController;

    @FXML
    private TextField textFieldNameLogin;
    @FXML
    private TextField textFieldCNPLogin;
    @FXML
    private TextField textFieldEmailAddressLogin;
    @FXML
    private TextField textFieldPhoneNumberLogin;
    @FXML
    private TextField textFieldUsernameLogin;
    @FXML
    private TextField textFieldPasswordLogin;

    public RegisterController() throws RemoteException {

    }

    public void setRegisterView(RegisterView registerView) {
        this.registerView = registerView;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void setServer(ServiceInterface server) {
        this.server = server;
    }

    public void confirmReaderRegistration() {
        String fullName = textFieldNameLogin.getText();
        String CNP = textFieldCNPLogin.getText();
        String email = textFieldEmailAddressLogin.getText();
        String phoneNumber = textFieldPhoneNumberLogin.getText();
        String username = textFieldUsernameLogin.getText();
        String password = textFieldPasswordLogin.getText();
        if (fullName.isEmpty() || CNP.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all fields!");
            alert.show();
        } else {
            Reader reader = new Reader(fullName, username, password, CNP, email, phoneNumber);
            try {
                server.registerReader(reader);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You have been registered successfully!");
                alert.show();

                loginController.getLoginView().show();
                registerView.hide();
            } catch (BookTerraException exception) {
                System.err.println("Error at registering the reader: " + exception);
                Alert alert = new Alert(Alert.AlertType.ERROR, "We couldn't register you! Please try again!");
                alert.show();
            }
        }
    }
}
