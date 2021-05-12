package view;

import controller.SummaryBorrowedBooksController;
import domain.Book;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ServiceInterface;

import java.io.IOException;
import java.util.List;

public class SummaryBorrowedBooksView {

    private static final String PATH_SUMMARY_BORROWED_BOOKS_VIEW = "/views/SummaryBorrowedBooks.fxml";

    private final Stage summaryBorrowedBooksStage = new Stage();

    public SummaryBorrowedBooksView(AvailableBooksView availableBooksView, List<Book> borrowedBooks) throws IOException {
        FXMLLoader summaryBorrowedBooksViewLoader = new FXMLLoader(getClass().getResource(PATH_SUMMARY_BORROWED_BOOKS_VIEW));

        Parent summaryBorrowedBooksRoot = summaryBorrowedBooksViewLoader.load();
        summaryBorrowedBooksStage.setTitle("BookTerra");
        summaryBorrowedBooksStage.setScene(new Scene(summaryBorrowedBooksRoot));

        SummaryBorrowedBooksController summaryBorrowedBooksController = summaryBorrowedBooksViewLoader.getController();
        summaryBorrowedBooksController.setSummaryBorrowedBooksView(this);
        summaryBorrowedBooksController.setAvailableBooksView(availableBooksView);
        summaryBorrowedBooksController.setModelBurrowedBooks(borrowedBooks);
    }

    public void show() {
        summaryBorrowedBooksStage.show();
    }

    public void hide() {
        summaryBorrowedBooksStage.hide();
    }
}
