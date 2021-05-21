package view;

import controller.ManageBooksController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ServiceInterface;

import java.io.IOException;

public class ManageBooksView {

    private static final String PATH_MANAGE_BOOKS_VIEW = "/views/ManageBooksView.fxml";

    private ManageBooksController manageBooksController;
    private Stage manageBooksStage = new Stage();


    public ManageBooksView(ServiceInterface server) throws IOException {
        FXMLLoader manageBooksViewLoader = new FXMLLoader(getClass().getResource(PATH_MANAGE_BOOKS_VIEW));

        Parent manageBooksRoot = manageBooksViewLoader.load();
        manageBooksStage.setTitle("BookTerra");
        manageBooksStage.setScene(new Scene(manageBooksRoot));

        manageBooksController = manageBooksViewLoader.getController();
        manageBooksController.setServer(server);
        manageBooksController.setManageBooksView(this);
    }

    public void show() {
        manageBooksStage.show();
    }

    public ManageBooksController getManageBooksController() {
        return manageBooksController;
    }
}
