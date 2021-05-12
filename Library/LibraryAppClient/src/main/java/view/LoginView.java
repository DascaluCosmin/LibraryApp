package view;

import controller.AvailableBooksController;
import controller.LoginViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ServiceInterface;

import java.io.IOException;

public class LoginView {

    private static final String PATH_LOGIN_VIEW_FXML = "/views/LoginView.fxml";
    private Stage loginViewStage;

    public LoginView(Stage primaryStage, ServiceInterface server, AvailableBooksController availableBooksController) throws IOException {
        FXMLLoader loginViewLoader = new FXMLLoader(getClass().getResource(PATH_LOGIN_VIEW_FXML));

        Parent loginViewRoot = loginViewLoader.load();
        loginViewStage = primaryStage;
        primaryStage.setTitle("BookTerra");
        primaryStage.setScene(new Scene(loginViewRoot));

        LoginViewController loginViewController = loginViewLoader.getController();
        loginViewController.setLoginView(this);
        loginViewController.setServer(server);
        loginViewController.setAvailableBooksController(availableBooksController);

        primaryStage.show();
    }

    public void hide() {
        loginViewStage.hide();
    }
}
