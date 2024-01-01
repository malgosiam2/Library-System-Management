package pl.edu.pw.mini.zpoif.zespol9.Testy;

import javax.swing.*;
import java.awt.*;

public class LokigGui extends JFrame {

    private JTextField loginField;
    private JPasswordField passwordField;

    public LokigGui() {
        setTitle("Sign In Panel");
        setVisible(true);
        setSize(new Dimension(350, 150));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        //panel.setSize(new Dimension(200, 100));
        panel.setLayout(new GridLayout(3, 3));

        JLabel loginLabel = new JLabel("Login:");
        loginField = new JTextField("");
        passwordField = new JPasswordField("");
        panel.add(loginLabel);
        panel.add(loginField);
        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);
        panel.add(passwordField);

        JButton loginButton = new JButton("Sign In");
        //loginButton.setBounds(200, 20, 10, 2);
        //panel.add(loginButton, GroupLayout.DEFAULT_SIZE);
        panel.add(loginButton);
        add(panel);
        setLocationRelativeTo(null);
        setResizable(false);

    }

    public static void main(String[] args) {

        LokigGui lokigGui = new LokigGui();

    }
}

