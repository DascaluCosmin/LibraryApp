package view;

import controller.MainMenuLibrarianController;
import controller.ManageBooksController;
import domain.Librarian;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ServiceInterface;

import java.io.IOException;

public class MainMenuLibrarianView {

    private static final String PATH_MAIN_MENU_LIBRARIAN_VIEW = "/views/MainMenuLibrarianView.fxml";

    private MainMenuLibrarianController mainMenuLibrarianController;
    private Stage mainMenuLibrarianStage = new Stage();

    public MainMenuLibrarianView(ServiceInterface server, ManageBooksController manageBooksController, Librarian librarian)
            throws IOException {
        FXMLLoader mainMenuLibrarianLoader = new FXMLLoader(getClass().getResource(PATH_MAIN_MENU_LIBRARIAN_VIEW));

        Parent mainMenuLibrarianRoot = mainMenuLibrarianLoader.load();
        mainMenuLibrarianStage.setTitle("BookTerra");
        mainMenuLibrarianStage.setScene(new Scene(mainMenuLibrarianRoot));

        mainMenuLibrarianController = mainMenuLibrarianLoader.getController();
        mainMenuLibrarianController.setLoggedInLibrarian(librarian);
        mainMenuLibrarianController.setServer(server);
        mainMenuLibrarianController.setManageBooksController(manageBooksController);
        mainMenuLibrarianController.setMainMenuLibrarianView(this);
    }

    public MainMenuLibrarianController getMainMenuLibrarianController() {
        return mainMenuLibrarianController;
    }

    public void show() {
        mainMenuLibrarianStage.show();
    }

    public void hide() {
        mainMenuLibrarianStage.hide();
    }
}
