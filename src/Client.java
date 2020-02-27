import java.net.*;
import java.io.*;

public class Client {
    private Listen listen;
    // private Send send;
    private Socket socket;
    private String username;
    private String ip;
    private int port;
    private GUI gui;

    public Client(String ip, int port, GUI gui) {
        try {
            this.gui = gui;
            this.socket = new Socket(ip, port); // establish a connection
            System.out.println("Connected");
            // this.send = new Send(this.socket, this);
            this.listen = new Listen(this.socket, this.gui);
            this.ip = ip;
            this.port = port;
            this.listen.run();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public String getUsername() {
        return username;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public Listen getListen() {
        return listen;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}