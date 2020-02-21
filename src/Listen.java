import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Listen implements Runnable {
    private Socket socket;
    private DataInputStream inputSocket;

    public Listen(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            inputSocket = new DataInputStream(new BufferedInputStream(socket.getInputStream())); // takes input from the server socket
            readMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessages() throws IOException {
        String line = "";  // reads message from client until "Quit" is sent
        while (!line.equalsIgnoreCase("Quit")) {
            try {
                line = inputSocket.readUTF();
                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

        }
    }
}