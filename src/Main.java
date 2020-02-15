import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Server server = new Server(5000);
        executor.execute(server);
       Client client = new Client("127.0.0.1", 6000);
       executor.execute(client);
    }
}
