package pl.edu.pw.mini.zpoif.zespol9.Testy;

import pl.edu.pw.mini.zpoif.zespol9.People.Librarian;
import pl.edu.pw.mini.zpoif.zespol9.People.Reader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loginUsedForSigning = loginField.getText();
                String passwordUsedForSigning = String.valueOf(passwordField.getPassword());

                if (loginUsedForSigning.startsWith("r")){
                    List<Reader> tmpList = Reader.getReaderList();

                    for (Reader el: tmpList){
                        if (el.getSignInData().getLogin().equals(loginUsedForSigning)){
                            if (el.getSignInData().getPassword().equals(passwordUsedForSigning)){
                                addNewWindow("reader");
                                dispose();

                            }else {
                                //odmowa dostepu
                                JOptionPane.showMessageDialog(LokigGui.this, "SO SORRY CANNOT SIGN IN BYE");
                            }
                            break;
                        }
                    }

                }else if (loginUsedForSigning.startsWith("l")){
                    List<Librarian> tmpList = Librarian.getLibrarianList();

                    for (Librarian el: tmpList){
                        if (el.getSignInData().getLogin().equals(loginUsedForSigning)){
                            if (el.getSignInData().getPassword().equals(passwordUsedForSigning)){
                                addNewWindow("librarian");
                                dispose();

                            }else {
                                //odmowa dostepu
                                JOptionPane.showMessageDialog(LokigGui.this, "SO SORRY CANNOT SIGN IN BYE");
                            }
                            break;
                        }
                    }

                }else {
                    JOptionPane.showMessageDialog(LokigGui.this, "SO SORRY CANNOT SIGN IN BYE");
                }
            }
        });
        panel.add(loginButton);
        add(panel);
        setLocationRelativeTo(null);
        setResizable(false);

    }

    private void addNewWindow(String user){
        JFrame window;
        if (user.equalsIgnoreCase("librarian")){
            window = new JFrame("Librarian window");
        }else {
            window = new JFrame("Reader window");
        }

        window.setVisible(true);
        window.setSize(new Dimension(1000, 600));
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 7; i ++){
            Reader reader = new Reader();
            //Librarian librarian = new Librarian();
            System.out.println(reader.getSignInData().getLogin());
            System.out.println(reader.getSignInData().getPassword());
        }

        LokigGui lokigGui = new LokigGui();
    }
}

