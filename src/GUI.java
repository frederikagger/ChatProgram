import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

    public GUI() {
        JFrame frame = new JFrame("Chat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        JButton button1 = new JButton("Send");
        JTextField textField = new JTextField(25);
        JTextArea jTextArea = new JTextArea(10,40);
        JPanel jPanel = new JPanel();
        jPanel.add(textField);
        jPanel.add(button1);
        jPanel.add(jTextArea);
        frame.getContentPane().add(jPanel);
        frame.setVisible(true);
        button1.addActionListener(this::actionPerformed);
    }

    public void actionPerformed(ActionEvent e) {

    }

}
