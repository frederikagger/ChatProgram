import java.io.*;
import java.net.*;

public class Server implements Runnable {
    private Socket socket;
    private ServerSocket serverSocket;
    private DataInputStream in;
    private DataOutputStream out;

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
            System.out.println("Client accepted");
            // sends output to socket
            out = new DataOutputStream(socket.getOutputStream());
            // takes input from the client socket
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out.writeUTF("J_OK");
            // takes input from terminal
            // inputstream = new DataInputStream(System.in);

            String line = "";
            // reads message from client until "Over" is sent
            while (!line.equals("Quit")) {
                try {
                    line = in.readUTF();
                    System.out.println(line);
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
            System.out.println("Closing connection");
            // close connection
            socket.close();
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}