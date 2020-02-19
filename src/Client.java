import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client implements Runnable {
    private Socket socket;
    private DataOutputStream output;
    private DataInputStream inputSocket;
    private String username = "bob";
    private Scanner scanner;
    private GUI gui;
    private String ip;
    private int port;
    private Protocol protocol = new Protocol();

    // constructor to put ip address and port
    public Client(String ip, int port, GUI gui) {
        try {
            this.socket = new Socket(ip, port); // establish a connection
            this.gui = gui;
            this.ip = ip;
            this.port = port;
        } catch (UnknownHostException u) {
            u.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
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

    @Override
    public void run() {
        try {
            scanner = new Scanner(System.in); // takes input from terminal
            output = new DataOutputStream(socket.getOutputStream());  // sends output to the socket
            inputSocket = new DataInputStream(new BufferedInputStream(socket.getInputStream())); // takes input from the server socket
            sendMessage(protocol.join(this.username, this.ip, this.port));
         //   setUsername();
         //   output.writeUTF("JOIN<<"+this.username+">>, <<"+socket.getInetAddress()+">>:<<"+socket.getPort()+">>");
        //    sendMessages();
        } catch(IOException i) {
            i.printStackTrace();
        }
    }

    public void sendMessage(String message) throws IOException {
        output.writeUTF(message);
    }

    public void setUsername(){
        gui.getjTextArea().append("Enter username: \n");
        System.out.println("Enter username: ");
        this.username = gui.getTextField().getText();
      //  this.username = scanner.next();
        while (this.username.length()>12){
            System.out.println("Username has to be less than 12 characters. Try again.\nEnter username: ");
            gui.getjTextArea().append("Username has to be less than 12 characters. Try again.\nEnter username: ");
            this.username = gui.getTextField().getText();
        }
    }

    public void sendMessages() throws IOException {
        String line = "";
        while (!line.equalsIgnoreCase("Quit")){ // keep reading until "Quit" is input
            try {
                line = scanner.nextLine();
                output.writeUTF(this.username+": "+line);
            }
            catch(IOException i){
                i.printStackTrace();
                break;
            }
        }
        output.close(); // close the connection
        socket.close();
        scanner.close();
    }
}