package view;

import controller.BorrowedBooksController;
import controller.MainMenuReaderController;
import domain.Book;
import domain.Reader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ServiceInterface;

import java.io.IOException;
import java.util.List;

public class BorrowedBooksView {

    private static final String PATH_BORROWED_BOOKS_VIEW = "/views/BorrowedBooksView.fxml";

    private BorrowedBooksController borrowedBooksController;
    private Stage borrowedBooksStage = new Stage();

    public BorrowedBooksView(ServiceInterface server, MainMenuReaderController mainMenuReaderController, Reader loggedInReader) throws IOException {
        FXMLLoader burrowedBooksViewLoader = new FXMLLoader(getClass().getResource(PATH_BORROWED_BOOKS_VIEW));

        Parent burrowedBooksRoot = burrowedBooksViewLoader.load();
        borrowedBooksController = burrowedBooksViewLoader.getController();
        borrowedBooksStage.setScene(new Scene(burrowedBooksRoot));
        borrowedBooksStage.setTitle("BookTerra");
        borrowedBooksStage.setOnCloseRequest((event) -> {
            hide();
            mainMenuReaderController.getMainMenuReaderView().show();
        });

        borrowedBooksController.setServer(server);
        borrowedBooksController.setBorrowedBooksView(this);
        borrowedBooksController.setLoggedInReader(loggedInReader);
        borrowedBooksController.showBorrowedBooks();
    }

    public void show() {
        borrowedBooksStage.show();
    }

    public void hide() {
        borrowedBooksStage.hide();
    }
}
