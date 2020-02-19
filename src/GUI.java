import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GUI extends JFrame implements ActionListener {
    private JFrame frame = new JFrame("Chat");
    private JButton send = new JButton("Send");
    private JButton connect = new JButton("Connect");
    private JTextField textField = new JTextField(40);
    private JTextArea jTextArea = new JTextArea(10,40);
    private JPanel jPanel = new JPanel();
    private MigLayout migLayout = new MigLayout();
    JScrollPane scroll = new JScrollPane(jTextArea);
    private Server server;
    private Client client;
    private ExecutorService executor;

    public GUI() {
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jPanel.setLayout(migLayout);
        jPanel.add(jTextArea, "split");
        jPanel.add(connect, "wrap 2");
        jPanel.add(textField, "span 2");
        jPanel.add(send);
        frame.getContentPane().add(jPanel);
        frame.setSize(500,250);
        frame.setLocation(400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        send.setBackground(Color.GRAY);
        connect.setBackground(Color.GRAY);
        send.addActionListener(this::actionPerformed);
        connect.addActionListener(this::actionPerformed);

        executor = Executors.newFixedThreadPool(2);
        server = new Server(5000, this);
        executor.execute(server);
    }

    public JTextArea getjTextArea() {
        return jTextArea;
    }

    public JTextField getTextField() { return textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == connect){
            client = new Client("127.0.0.1", 6000, this);
            executor.execute(client);
        }
        if (e.getSource() == send){
            try {
                client.sendMessage(textField.getText());
                jTextArea.append("Me: "+textField.getText()+"\n");
                textField.setText("");
             } catch (IOException ex) {
                   ex.printStackTrace();
             }
        }
    }
}
