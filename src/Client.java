import java.net.*;
import java.io.*;

public class Client implements Runnable {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private DataInputStream inputSocket;
    private String username;

    // constructor to put ip address and port
    public Client(String address, int port) {
        // establish a connection 
        try {
            socket = new Socket(address, port);
        } catch(UnknownHostException u){
                System.out.println(u);
            } catch(IOException i){
                System.out.println(i);
            }
        }
        @Override
        public void run() {
        try {
            System.out.println("Connected");
            // takes input from terminal
            input = new DataInputStream(System.in);
            // sends output to the socket
            output = new DataOutputStream(socket.getOutputStream());
            // takes input from the client socket
            inputSocket = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            System.out.println(inputSocket.readUTF());
        } catch (IOException e){
            e.printStackTrace();
        }

            // string to read message from input
            String line = "";

        // keep reading until "Over" is input 
        while (!line.equals("Over"))
        {
            try
            {
                line = input.readLine();
                output.writeUTF(line);
                System.out.println(inputSocket.readUTF());
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }
        // close the connection 
        try
        {
            input.close();
            output.close();
            socket.close();
        } catch(IOException i)
        {
            System.out.println(i);
        }
    }
}