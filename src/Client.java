import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private Listen listen;
    private Send send;
    private Socket socket;
    private String username = "bob";
    private String ip;
    private int port;
    private GUI gui;
    private ExecutorService executor;

    public Client(String ip, int port) {
        try {
            this.socket = new Socket(ip, port); // establish a connection
            this.send = new Send(this.socket);
            this.listen = new Listen(this.socket);
            this.ip = ip;
            this.port = port;
            executor = Executors.newFixedThreadPool(2);
            executor.execute(send);
            executor.execute(listen);
        } catch (IOException e){
            e.printStackTrace();
        }
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

    public Send getSend() {
        return send;
    }

/* public void setUsername(){
//        gui.getjTextArea().append("Enter username: \n");
        System.out.println("Enter username: ");
//        this.username = gui.getTextField().getText();
        this.username = scanner.next();
        while (this.username.length()>12){
            System.out.println("Username has to be less than 12 characters. Try again.\nEnter username: ");
            gui.getjTextArea().append("Username has to be less than 12 characters. Try again.\nEnter username: ");
            this.username = gui.getTextField().getText();
        }
    }*/
}