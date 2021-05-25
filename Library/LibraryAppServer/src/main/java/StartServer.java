import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.BookTerraException;

public class StartServer {

    public static void main(String[] args) throws BookTerraException {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-server.xml");
        System.out.println("The server is up and running!");
    }
}
