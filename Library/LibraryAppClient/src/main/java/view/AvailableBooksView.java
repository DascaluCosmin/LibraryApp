package view;

import controller.AvailableBooksController;
import controller.MainMenuReaderController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ServiceInterface;

import java.io.IOException;

public class AvailableBooksView {

    private static final String PATH_AVAILABLE_BOOKS_VIEW = "/views/AvailableBooksView.fxml";

    private AvailableBooksController availableBooksController;
    private MainMenuReaderController mainMenuReaderController;
    private Stage availableBooksStage = new Stage();

    public AvailableBooksView(ServiceInterface server) throws IOException {
        FXMLLoader availableBooksViewLoader = new FXMLLoader(getClass().getResource(PATH_AVAILABLE_BOOKS_VIEW));

        Parent availableBooksRoot = availableBooksViewLoader.load();
        availableBooksStage.setTitle("BookTerra");
        availableBooksStage.setScene(new Scene(availableBooksRoot));

        availableBooksStage.setOnCloseRequest((event) -> {
            hide();
            mainMenuReaderController.getMainMenuReaderView().show();
        });

        availableBooksController = availableBooksViewLoader.getController();
        availableBooksController.setAvailableBooksView(this);
        availableBooksController.setServer(server);
    }

    public void setMainMenuReaderController(MainMenuReaderController mainMenuReaderController) {
        this.mainMenuReaderController = mainMenuReaderController;
    }

    public AvailableBooksController getAvailableBooksController() {
        return availableBooksController;
    }

    public void show()  {
        availableBooksStage.show();
    }

    public void hide() {
        availableBooksStage.hide();
    }
}
