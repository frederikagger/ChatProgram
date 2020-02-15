import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server implements Runnable {
    private Socket socket;
    private ServerSocket serverSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner scanner;
    private String username;
    private String IP;
    private int port;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run () {
        try {
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            socket = serverSocket.accept();
            System.out.println("Connected");
            out = new DataOutputStream(socket.getOutputStream());  // sends output to socket
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream())); // takes input from the client socket
            scanner = new Scanner(System.in); // takes input from terminal
            out.writeUTF("J_OK");
            readMessages(); //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that keeps receving messages until "Quit" is send
     */
    public void readMessages() throws IOException {
        String line = "";  // reads message from client until "Quit" is sent
        while (!line.equalsIgnoreCase("Quit")) {
            try {
                line = in.readUTF();
                check(line);
                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        System.out.println("Closing connection");
        socket.close(); // close connection
        in.close();
    }

    public void check(String line){
        if (line.startsWith("JOIN")){

        }
    }
}