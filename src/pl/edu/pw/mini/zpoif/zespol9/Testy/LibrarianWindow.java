package pl.edu.pw.mini.zpoif.zespol9.Testy;

import pl.edu.pw.mini.zpoif.zespol9.People.Librarian;
import pl.edu.pw.mini.zpoif.zespol9.System.LibrarySystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LibrarianWindow extends JFrame {

    private LibrarySystem librarySystem;
    private Librarian librarian;

    public LibrarianWindow(LibrarySystem librarySystem, Librarian librarian) {
        this.librarian = librarian;
        this.librarySystem = librarySystem;

        setTitle("Librarian Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1000, 800));

        //dodajemy panel górny z obrazkiem:
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(1000, 100));
        try {
            BufferedImage image = ImageIO.read(new File("resources/WelcomeBack.png"));

            ImageIcon imageIcon = new ImageIcon(image);
            JLabel imageLabel = new JLabel(imageIcon);
            imagePanel.add(imageLabel);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // dodajemy lewy panel z zakładkami:
        JPanel leftPanel = new JPanel(new FlowLayout());
        leftPanel.setBackground(new Color(206, 190, 170, 255));
        leftPanel.setPreferredSize(new Dimension(180, 0));

        Font fontTab = new Font("Serif", Font.BOLD, 18);
        JButton jButton1 = new JButton("Catalogue");
        jButton1.setFont(fontTab);
        jButton1.setForeground(new Color(239, 221, 191, 255));
        JButton jButton2 = new JButton("Add Book");
        jButton2.setFont(fontTab);
        jButton2.setForeground(new Color(239, 221, 191, 255));
        JButton jButton3 = new JButton("Add User");
        jButton3.setFont(fontTab);
        jButton3.setForeground(new Color(239, 221, 191, 255));
        JButton jButton4 = new JButton("Library Management");
        jButton4.setFont(fontTab);
        jButton4.setForeground(new Color(239, 221, 191, 255));

        JButton logOutButton = new JButton("Log Out");
        logOutButton.setFont(fontTab);
        logOutButton.setForeground(new Color(239, 221, 191, 255));






        JPanel rightPanel = new JPanel();

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                implementCatalogue();

            }
        });

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                implementAddBook();

            }
        });

        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                implementAddUser();

            }
        });

        jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                implementLibraryManagement();

            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LokigGui lokigGui = new LokigGui(librarySystem);
                dispose();
            }
        });

        jButton2.setPreferredSize(new Dimension(160, 50));
        jButton1.setPreferredSize(new Dimension(160, 50));
        jButton3.setPreferredSize(new Dimension(160, 50));
        jButton4.setPreferredSize(new Dimension(160, 50));
        logOutButton.setPreferredSize(new Dimension(160, 50));
        jButton1.setBackground(new Color(107, 79, 51));
        jButton2.setBackground(new Color(107, 79, 51));
        jButton3.setBackground(new Color(107, 79, 51));
        jButton4.setBackground(new Color(107, 79, 51));
        logOutButton.setBackground(new Color(107, 79, 51));

        leftPanel.add(jButton1);
        leftPanel.add(jButton2);
        leftPanel.add(jButton3);
        leftPanel.add(jButton4);
        leftPanel.add(logOutButton);
        rightPanel.setLayout(new BorderLayout());

        setLayout(new BorderLayout());
        add(imagePanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);

    }

    private void implementCatalogue(){
        // to do
    }

    private void implementAddUser(){
        // to do
    }

    private void implementAddBook(){
        // to do
    }

    private void implementLibraryManagement(){
        // to do
    }

}
