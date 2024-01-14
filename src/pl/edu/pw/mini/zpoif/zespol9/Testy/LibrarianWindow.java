package pl.edu.pw.mini.zpoif.zespol9.Testy;

import pl.edu.pw.mini.zpoif.zespol9.Book.Book;
import pl.edu.pw.mini.zpoif.zespol9.Book.Genre;
import pl.edu.pw.mini.zpoif.zespol9.Catalogue.Catalogue;
import pl.edu.pw.mini.zpoif.zespol9.Exceptions.NoReaderWithThatLoginException;
import pl.edu.pw.mini.zpoif.zespol9.People.Librarian;
import pl.edu.pw.mini.zpoif.zespol9.People.Reader;
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
                implementAddBook(rightPanel);

            }
        });

        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                implementAddUser(rightPanel);

            }
        });

        jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                implementLibraryManagement(rightPanel);

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

    private void implementAddBook(JPanel jPanel){
        jPanel.setLayout(new FlowLayout());

        JPanel jPanelMain = new JPanel();
        JPanel jPanelTitle = new JPanel();
        JPanel jPanelAuthor = new JPanel();
        JPanel jPanelDescription = new JPanel();
        JPanel jPanelBookRating = new JPanel();
        JPanel jPanelGenre = new JPanel();
        JPanel jPanelAddBook = new JPanel();

        jPanelMain.setPreferredSize(new Dimension(jPanel.getWidth(), 100));
        jPanelTitle.setPreferredSize(new Dimension(jPanel.getWidth(), 70));
        jPanelAuthor.setPreferredSize(new Dimension(jPanel.getWidth(), 70));
        jPanelDescription.setPreferredSize(new Dimension(jPanel.getWidth(), 70));
        jPanelBookRating.setPreferredSize(new Dimension(jPanel.getWidth(), 70));
        jPanelGenre.setPreferredSize(new Dimension(jPanel.getWidth(), 70));
        jPanelAddBook.setPreferredSize(new Dimension(jPanel.getWidth(), 80));

        Font font = new Font("Serif", Font.BOLD, 18);
        Font font1 = new Font("Serif", Font.BOLD, 22);

        JLabel main = new JLabel("Add Book:");
        main.setFont(font1);
        jPanelMain.add(main);

        jPanelTitle.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel tekst1 = new JLabel("Title:");
        tekst1.setFont(font);
        JTextField field1 = new JTextField();
        field1.setBackground(new Color(239, 221, 191, 255));

        tekst1.setPreferredSize(new Dimension(200, 50));
        field1.setPreferredSize(new Dimension(200, 50));
        jPanelTitle.add(tekst1);
        jPanelTitle.add(field1);

        jPanelAuthor.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel tekst2 = new JLabel("Author:");
        tekst2.setFont(font);
        JTextField field2 = new JTextField();
        field2.setBackground(new Color(239, 221, 191, 255));

        tekst2.setPreferredSize(new Dimension(200, 50));
        field2.setPreferredSize(new Dimension(200, 50));
        jPanelAuthor.add(tekst2);
        jPanelAuthor.add(field2);

        jPanelDescription.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel tekst3 = new JLabel("Description:");
        tekst3.setFont(font);
        JTextField field3 = new JTextField();
        field3.setBackground(new Color(239, 221, 191, 255));

        tekst3.setPreferredSize(new Dimension(200, 50));
        field3.setPreferredSize(new Dimension(400, 50));
        jPanelDescription.add(tekst3);
        jPanelDescription.add(field3);

        jPanelBookRating.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel tekst4 = new JLabel("Book Rating:");
        tekst4.setFont(font);
        JTextField field4 = new JTextField();
        field4.setBackground(new Color(239, 221, 191, 255));

        tekst4.setPreferredSize(new Dimension(200, 50));
        field4.setPreferredSize(new Dimension(200, 50));
        jPanelBookRating.add(tekst4);
        jPanelBookRating.add(field4);

        jPanelGenre.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel tekst5 = new JLabel("Genre");
        tekst5.setFont(font);

        Genre[] genres = Genre.values();
        JComboBox<Genre> comboBox = new JComboBox<>(genres);

        tekst5.setPreferredSize(new Dimension(200, 50));
        comboBox.setPreferredSize(new Dimension(200, 50));
        comboBox.setBackground(new Color(239, 221, 191, 255));
        jPanelGenre.add(tekst5);
        jPanelGenre.add(comboBox);

        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = field1.getText();
                String author = field2.getText();
                String description = field3.getText();
                double rating = Double.parseDouble(field4.getText());
                Genre genre = (Genre) comboBox.getSelectedItem();

                Book book = new Book(title, author, description, rating, 0, 0, 0, genre);
                Catalogue.getCatalogue().add(book);
            }
        });

        jPanelAddBook.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanelAddBook.add(addButton);
        addButton.setPreferredSize(new Dimension(200, 50));

        jPanel.add(jPanelMain);
        jPanel.add(jPanelTitle);
        jPanel.add(jPanelAuthor);
        jPanel.add(jPanelDescription);
        jPanel.add(jPanelBookRating);
        jPanel.add(jPanelGenre);
        jPanel.add(jPanelAddBook);


        jPanel.revalidate();
        jPanel.repaint();
    }

    private void implementAddUser(JPanel jPanel){

        jPanel.setLayout(new FlowLayout());

        JPanel jPanelMain = new JPanel();
        JPanel jPanelName = new JPanel();
        JPanel jPanelSurname = new JPanel();
        jPanelMain.setPreferredSize(new Dimension(jPanel.getWidth(), 100));
        jPanelName.setPreferredSize(new Dimension(jPanel.getWidth(), 70));
        jPanelSurname.setPreferredSize(new Dimension(jPanel.getWidth(), 70));
        Font font = new Font("Serif", Font.BOLD, 18);
        Font font1 = new Font("Serif", Font.BOLD, 22);

        JLabel main = new JLabel("Add User:");
        main.setFont(font1);
        jPanelMain.add(main);

        jPanelName.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel tekst1 = new JLabel("Name:");
        tekst1.setFont(font);
        JTextField field1 = new JTextField();
        field1.setBackground(new Color(239, 221, 191, 255));

        tekst1.setPreferredSize(new Dimension(200, 50));
        field1.setPreferredSize(new Dimension(200, 50));
        jPanelName.add(tekst1);
        jPanelName.add(field1);

        jPanelSurname.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel tekst2 = new JLabel("Surname:");
        tekst2.setFont(font);
        JTextField field2 = new JTextField();
        field2.setBackground(new Color(239, 221, 191, 255));

        tekst2.setPreferredSize(new Dimension(200, 50));
        field2.setPreferredSize(new Dimension(200, 50));
        jPanelSurname.add(tekst2);
        jPanelSurname.add(field2);

        JPanel addUser = new JPanel();
        JButton addButton = new JButton("Add User");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = field1.getText();
                String surname = field2.getText();

                Reader reader = new Reader(name, surname, librarySystem);
                librarySystem.getReaderList().add(reader);

                JOptionPane.showMessageDialog(LibrarianWindow.this, "New User has been added!");

            }
        });

        addUser.setLayout(new FlowLayout(FlowLayout.CENTER));
        addUser.add(addButton);
        addButton.setPreferredSize(new Dimension(200, 50));


        jPanel.add(jPanelMain);
        jPanel.add(jPanelName);
        jPanel.add(jPanelSurname);
        jPanel.add(addUser);


        jPanel.revalidate();
        jPanel.repaint();

    }

    private void implementLibraryManagement(JPanel jPanel){
        jPanel.setLayout(new GridLayout(3, 1));

        JPanel searchLoginPanel = new JPanel();
        searchLoginPanel.setBackground(new Color(238, 232, 223, 255));
        jPanel.add(searchLoginPanel);
        JPanel reservedBooksPanel = new JPanel();
        reservedBooksPanel.setBackground(new Color(238, 232, 223, 255));
        jPanel.add(reservedBooksPanel);
        JPanel checkedOutBooksPanel = new JPanel();
        checkedOutBooksPanel.setBackground(new Color(238, 232, 223, 255));
        jPanel.add(checkedOutBooksPanel);

        // search login panel:

        JLabel jLabelSearch = new JLabel("Choose User: ");
        JTextField jTextSearch = new JTextField();
        JButton jButtonSearch = new JButton("Search");
        searchLoginPanel.add(jLabelSearch);
        searchLoginPanel.add(jTextSearch);
        searchLoginPanel.add(jButtonSearch);
        jTextSearch.setPreferredSize(new Dimension(200, 70));
        jButtonSearch.setPreferredSize(new Dimension(200, 70));

        jButtonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservedBooksPanel.removeAll();
                checkedOutBooksPanel.removeAll();

                String readerLogin = jTextSearch.getText();
                Reader myReader = null;
                try {
                    myReader = librarySystem.getReader(readerLogin);
                } catch (NoReaderWithThatLoginException ex) {
                    throw new RuntimeException(ex);
                }

                PanelWithScrollPane panelWithScrollPane = new PanelWithScrollPane("Reserved Books", myReader);
                PanelWithScrollPane panelWithScrollPane1 = new PanelWithScrollPane("Checked Out Books", myReader);
                panelWithScrollPane.createPanel(reservedBooksPanel);
                panelWithScrollPane1.createPanel(checkedOutBooksPanel);

                reservedBooksPanel.revalidate();
                //reservedBooksPanel.repaint();
                checkedOutBooksPanel.revalidate();
                //checkedOutBooksPanel.repaint();

            }
        });


        jPanel.revalidate();
        jPanel.repaint();

    }

}
