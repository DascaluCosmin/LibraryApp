package view;

import controller.LoginController;
import controller.RegisterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ServiceInterface;

import java.io.IOException;

public class RegisterView {

    private static final String PATH_REGISTER_VIEW = "/views/RegisterView.fxml";

    private RegisterController registerController;
    private Stage registerStage = new Stage();

    public RegisterView(ServiceInterface server, LoginController loginController) throws IOException {
        FXMLLoader registerViewLoader = new FXMLLoader(getClass().getResource(PATH_REGISTER_VIEW));

        Parent registerRoot = registerViewLoader.load();
        registerStage.setTitle("BookTerra");
        registerStage.setScene(new Scene(registerRoot));

        registerController = registerViewLoader.getController();
        registerController.setServer(server);
        registerController.setLoginController(loginController);
        registerController.setRegisterView(this);
    }

    public void show() {
        registerStage.show();
    }

    public void hide() {
        registerStage.hide();
    }
}
