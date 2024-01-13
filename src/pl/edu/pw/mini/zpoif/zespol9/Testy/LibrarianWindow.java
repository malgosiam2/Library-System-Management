package pl.edu.pw.mini.zpoif.zespol9.Testy;

import pl.edu.pw.mini.zpoif.zespol9.Book.Book;
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
import java.util.Comparator;
import java.util.List;

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
                implementCatalogue(librarySystem);

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

    private void implementCatalogue(LibrarySystem librarySystem) {
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

        //Scroll Panel - Layout
        JPanel borderlaoutpanel = new JPanel();
        scrollPane.setViewportView(borderlaoutpanel);
        borderlaoutpanel.setLayout(new BorderLayout(0, 0));

        JPanel columnpanel = new JPanel();
        borderlaoutpanel.add(columnpanel, BorderLayout.NORTH);
        columnpanel.setLayout(new GridLayout(0, 1, 0, 1));
        columnpanel.setBackground(Color.gray);

        JLabel labelTextCatalogue = new JLabel();
        labelTextCatalogue.setSize(new Dimension(910, 50));
        labelTextCatalogue.setText("<HTML>Search</HTML>");
        Font font = new Font("Serif", Font.BOLD, 15);
        labelTextCatalogue.setFont(font);
        labelTextCatalogue.setBounds(5, 5, 910, 20);
        upperPanel.add(labelTextCatalogue);

        // search by title
        JLabel titleSearchLabel = new JLabel("Search by title: ");
        JTextField titleSearchField = new JTextField("");
        JButton titleSearchButton = new JButton("Search");

        titleSearchLabel.setBounds(5, 50, 120, 25);
        titleSearchField.setBounds(135, 50, 230, 25);
        titleSearchButton.setBounds(375, 50, 80, 25);

        titleSearchLabel.setFont(font);
        titleSearchButton.setFont(font);

        upperPanel.add(titleSearchLabel);
        upperPanel.add(titleSearchField);
        upperPanel.add(titleSearchButton);


        titleSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titleText = titleSearchField.getText();
                titleSearchField.setText("");
                java.util.List<Book> booksList = librarySystem.getCatalogue().searchByTitle(titleText);

                columnpanel.removeAll();
                printCatalogue(columnpanel, booksList);

            }
        });
        // end search by title


        // search by author
        JLabel authorSearchLabel = new JLabel("Search by author: ");
        JTextField authorSearchField = new JTextField("");
        JButton authorSearchButton = new JButton("Search");

        authorSearchLabel.setBounds(5, 80, 120, 25);
        authorSearchField.setBounds(135, 80, 230, 25);
        authorSearchButton.setBounds(375, 80, 80, 25);

        authorSearchLabel.setFont(font);
        authorSearchButton.setFont(font);

        upperPanel.add(authorSearchLabel);
        upperPanel.add(authorSearchField);
        upperPanel.add(authorSearchButton);

        authorSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String authorText = authorSearchField.getText();
                authorSearchField.setText("");
                List<Book> booksList = librarySystem.getCatalogue().searchByAuthor(authorText);
                booksList.sort(Comparator.comparing(book -> book.title.toLowerCase()));

                columnpanel.removeAll();
                printCatalogue(columnpanel, booksList);

            }
        });
        // end search by id

        // search by id
        JLabel idSearchLabel = new JLabel("Search by id: ");
        JTextField idSearchField = new JTextField("");
        JButton idSearchButton = new JButton("Search");

        idSearchLabel.setBounds(5, 110, 120, 25);
        idSearchField.setBounds(135, 110, 230, 25);
        idSearchButton.setBounds(375, 110, 80, 25);

        idSearchLabel.setFont(font);
        idSearchField.setFont(font);
        idSearchButton.setFont(font);

        upperPanel.add(idSearchLabel);
        upperPanel.add(idSearchField);
        upperPanel.add(idSearchButton);

        idSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idSearchField.getText());
                authorSearchField.setText("");
                List<Book> booksList = librarySystem.getCatalogue().searchById(id);
                booksList.sort(Comparator.comparing(book -> book.title.toLowerCase()));

                columnpanel.removeAll();
                printCatalogue(columnpanel, booksList);

            }
        });
        // end search by author

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

    private void printCatalogue(JPanel columnpanel, List<Book> booksList) {
        Font font = new Font("Serif", Font.BOLD, 15);

        int i = 0;
        for (Book book : booksList) {
            JPanel rowPanel = new JPanel();
            rowPanel.setPreferredSize(new Dimension(1100, 100));
            columnpanel.add(rowPanel);
            rowPanel.setLayout(null);

            JLabel bookLabel = new JLabel(book.toStringCatalogueForLibrarian());
            bookLabel.setFont(font);
            bookLabel.setBounds(5, 5, 1100, 50);
            rowPanel.add(bookLabel);

            addCatalogueButton(rowPanel, book);

            i++;
            if (i % 2 == 0)
                rowPanel.setBackground(SystemColor.inactiveCaptionBorder);
        }
        columnpanel.revalidate();
    }

    private void addCatalogueButton(JPanel rowPanel, Book book) {
        Font font = new Font("Serif", Font.BOLD, 15);

        // description button
        JButton readDesriptionButton = new JButton("Read Description");
        readDesriptionButton.setBounds(5, 60, 150, 30);
        readDesriptionButton.setFont(font);
        readDesriptionButton.setBackground(new Color(161, 148, 137));
        readDesriptionButton.addActionListener(new ReadDescriptionListener(book));
        rowPanel.add(readDesriptionButton);
        //end descriptionbutton

        //delete button
        JButton addDeleteButton = new JButton("Delete");
        addDeleteButton.setBounds(170, 60, 150, 30);
        addDeleteButton.setFont(font);
        addDeleteButton.setBackground(new Color(128, 125, 123));
        addDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        rowPanel.add(addDeleteButton);
        //end delete read

    }

    private void implementAddUser() {
        // to do
    }

    private void implementAddBook() {
        // to do
    }

    private void implementLibraryManagement() {
        // to do
    }

}
