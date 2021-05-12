package view;

import controller.AvailableBooksController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ServiceInterface;

import java.io.IOException;

public class AvailableBooksView {

    private static final String PATH_AVAILABLE_BOOKS_VIEW = "/views/AvailableBooksView.fxml";

    private AvailableBooksController availableBooksController;
    private Stage availableBooksStage = new Stage();

    public AvailableBooksView(ServiceInterface server) throws IOException {
        FXMLLoader availableBooksViewLoader = new FXMLLoader(getClass().getResource(PATH_AVAILABLE_BOOKS_VIEW));

        Parent availableBooksRoot = availableBooksViewLoader.load();
        availableBooksStage.setTitle("BookTerra");
        availableBooksStage.setScene(new Scene(availableBooksRoot));

        availableBooksController = availableBooksViewLoader.getController();
        availableBooksController.setAvailableBooksView(this);
        availableBooksController.setServer(server);
    }

    public void show()  {
        availableBooksStage.show();
    }

    public AvailableBooksController getAvailableBooksController() {
        return availableBooksController;
    }
}
