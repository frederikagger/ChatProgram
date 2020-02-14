import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Server server = new Server(5000);
        Server server1 = new Server(6000);
        executor.execute(server);
        executor.execute(server1);
        Client client = new Client("127.0.0.1", 5000);
        Client client1 = new Client("127.0.0.1", 6000);
        executor.execute(client);
        executor.execute(client1);
    }
}
