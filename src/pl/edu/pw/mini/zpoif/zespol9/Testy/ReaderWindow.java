package pl.edu.pw.mini.zpoif.zespol9.Testy;

import pl.edu.pw.mini.zpoif.zespol9.Book.Genre;
import pl.edu.pw.mini.zpoif.zespol9.System.LibrarySystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;




public class ReaderWindow extends JFrame {

    public ReaderWindow(LibrarySystem librarySystem){

        setTitle("Reader Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1000, 800));


        //dodajemy panel górny z obrazkiem:
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(1000, 100));
        try {
            BufferedImage image = ImageIO.read(new File("resources/WelcomeBack.png"));
            Graphics2D g2d = image.createGraphics();

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
        JButton jButton2 = new JButton("My Account");
        jButton2.setFont(fontTab);
        jButton2.setForeground(new Color(239, 221, 191, 255));


        JPanel rightPanel = new JPanel();

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                implementCatalogue();

                // implement
            }
        });

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                implementAccount();

            }
        });

        jButton2.setPreferredSize(new Dimension(160, 50));
        jButton1.setPreferredSize(new Dimension(160, 50));
        jButton1.setBackground(new Color(107, 79, 51));
        jButton2.setBackground(new Color(107, 79, 51));

        leftPanel.add(jButton1);
        leftPanel.add(jButton2);
        rightPanel.setLayout(new BorderLayout());

        setLayout(new BorderLayout());
        add(imagePanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void implementCatalogue() {
        JPanel rightPanel = (JPanel) getContentPane().getComponent(2);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setBackground(new Color(206, 190, 170, 255));
        JPanel upperPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane();

        upperPanel.setSize(new Dimension(920, 200));
        upperPanel.setBackground(new Color(238, 232, 223, 255));
        upperPanel.setLayout(null);

        scrollPane.setSize(new Dimension(920, 600));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // upperPanel with search option
        JLabel labelTextCatalogue = new JLabel();
        labelTextCatalogue.setSize(new Dimension(910, 50));
        labelTextCatalogue.setText("<HTML>Explore our library catalog to discover our extensive collection of books.<br>  Select a category to browse and find the literary treasures housed in our library.</HTML>");
        Font font = new Font("Serif", Font.BOLD, 15);
        labelTextCatalogue.setFont(font);
        labelTextCatalogue.setBounds(5, 4, 910, 60);
        upperPanel.add(labelTextCatalogue);

        // search by title
        JLabel titleSearchLabel = new JLabel("Search by title: ");
        JTextField titleSearchField = new JTextField("");
        JButton titleSearchButton = new JButton("Search");

        titleSearchLabel.setBounds(5, 70, 120, 25);
        titleSearchField.setBounds(135, 70, 230, 25);
        titleSearchButton.setBounds(375, 70, 80, 25 );

        titleSearchLabel.setFont(font);
        titleSearchButton.setFont(font);

        upperPanel.add(titleSearchLabel);
        upperPanel.add(titleSearchField);
        upperPanel.add(titleSearchButton);
        // end search by title

        // search by author
        JLabel authorSearchLabel = new JLabel("Search by author: ");
        JTextField authorSearchField = new JTextField("");
        JButton authorSearchButton = new JButton("Search");

        authorSearchLabel.setBounds(5, 100, 120, 25);
        authorSearchField.setBounds(135, 100, 230, 25);
        authorSearchButton.setBounds(375, 100, 80, 25 );

        authorSearchLabel.setFont(font);
        authorSearchField.setFont(font);

        upperPanel.add(authorSearchLabel);
        upperPanel.add(authorSearchField);
        upperPanel.add(authorSearchButton);
        // end search by author

        // search by genre
        Genre[] options = new Genre[Genre.values().length + 1];
        options[0] = null;
        System.arraycopy(Genre.values(), 0, options, 1, Genre.values().length);

        JLabel genreSearchLabel = new JLabel("Search by genre: ");
        JComboBox<Genre> genreJComboBox = new JComboBox<>(options);
        JButton genreSearchButton = new JButton("Search");

        genreSearchLabel.setBounds(5, 130, 120, 25);
        genreJComboBox.setBounds(135, 130, 230, 25);
        genreSearchButton.setBounds(375, 130, 80, 25 );

        genreSearchLabel.setFont(font);
        authorSearchField.setFont(font);

        upperPanel.add(genreSearchLabel);
        upperPanel.add(genreJComboBox);
        upperPanel.add(genreSearchButton);
        // end search by genre


        splitPane.setTopComponent(upperPanel);
        splitPane.setBottomComponent(scrollPane);

        splitPane.getTopComponent().setMinimumSize(new Dimension(0, 200));
        splitPane.getTopComponent().setMaximumSize(new Dimension(920, 200));

        splitPane.setEnabled(false);
        splitPane.setResizeWeight(0.0);
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerSize(5);

        rightPanel.add(splitPane, BorderLayout.CENTER);
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void implementAccount() {
        JPanel rightPanel = (JPanel) getContentPane().getComponent(2);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JPanel upperPanel = new JPanel();
        JPanel lowerPanel = new JPanel();

        upperPanel.setBackground(new Color(232, 169, 61));
        upperPanel.setSize(new Dimension(920, 500));
        lowerPanel.setLayout(new GridLayout(1, 3));
        lowerPanel.setBackground(Color.CYAN);

        JPanel reservedBooksPanel = new JPanel();
        reservedBooksPanel.setBackground(new Color(255, 210, 131));
        lowerPanel.add(reservedBooksPanel);
        JPanel checkedOutBooksPanel = new JPanel();
        checkedOutBooksPanel.setBackground(new Color(255, 210, 131));
        lowerPanel.add(checkedOutBooksPanel);
        JPanel toReadBooksPanel = new JPanel();
        toReadBooksPanel.setBackground(new Color(255, 210, 131));
        lowerPanel.add(toReadBooksPanel);

        Font font = new Font("MV Boli", Font.BOLD, 20);

        // panel na gorze:
        upperPanel.setLayout(new GridLayout(4, 2));
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(font);
        upperPanel.add(nameLabel);
        upperPanel.add(new JLabel());

        JLabel surnameLabel = new JLabel("Surname:");
        surnameLabel.setFont(font);
        upperPanel.add(surnameLabel);
        upperPanel.add(new JLabel());

        JLabel loginLabel = new JLabel("Login:");
        loginLabel.setFont(font);
        upperPanel.add(loginLabel);
        upperPanel.add(new JLabel());

        JLabel fineLabel = new JLabel("Fine:");
        fineLabel.setFont(font);
        upperPanel.add(fineLabel);
        upperPanel.add(new JLabel());

        // reserved books:


        //

        splitPane.setTopComponent(upperPanel);
        splitPane.setBottomComponent(lowerPanel);

        splitPane.getTopComponent().setMinimumSize(new Dimension(0, 200));
        splitPane.getTopComponent().setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        splitPane.setResizeWeight(0.0);
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerSize(0);

        rightPanel.add(splitPane, BorderLayout.CENTER);
        rightPanel.revalidate();
        rightPanel.repaint();

    }

}
