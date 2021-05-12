package view;

import controller.AvailableBooksController;
import controller.MainMenuReaderController;
import domain.Reader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ServiceInterface;

import java.io.IOException;

public class MainMenuReaderView {

    private static final String PATH_MAIN_MENU_READER_VIEW = "/views/MainMenuReaderView.fxml";

    private MainMenuReaderController mainMenuReaderController;
    private Stage mainMenuReaderStage = new Stage();

    public MainMenuReaderView(ServiceInterface server, AvailableBooksController availableBooksController, Reader loggedInReader)
            throws IOException {
        FXMLLoader mainMenuReaderLoader = new FXMLLoader(getClass().getResource(PATH_MAIN_MENU_READER_VIEW));

        Parent mainMenuReaderRoot = mainMenuReaderLoader.load();
        mainMenuReaderStage.setScene(new Scene(mainMenuReaderRoot));
        mainMenuReaderStage.setTitle("BookTerra");

        mainMenuReaderController = mainMenuReaderLoader.getController();
        mainMenuReaderController.setMainMenuReaderView(this);
        mainMenuReaderController.setAvailableBooksController(availableBooksController);
        mainMenuReaderController.setServer(server);
        mainMenuReaderController.setLoggedInReader(loggedInReader);
    }

    public void showMainMenuReaderView() {
        mainMenuReaderStage.show();
    }

    public MainMenuReaderController getMainMenuReaderController() {
        return mainMenuReaderController;
    }

    public void hide() {
        mainMenuReaderStage.hide();
    }
}
