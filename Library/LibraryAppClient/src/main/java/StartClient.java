import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.ServiceInterface;
import view.AvailableBooksView;
import view.LoginView;

public class StartClient extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
            ServiceInterface server = (ServiceInterface) factory.getBean("BookTerraService");
            System.out.println("Obtained a reference to remote chat server");

            AvailableBooksView availableBooksView = new AvailableBooksView(server);
            LoginView loginView = new LoginView(primaryStage, server, availableBooksView.getAvailableBooksController());
        } catch (Exception exception) {
            System.err.println("Client initialization exception: " + exception);
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
