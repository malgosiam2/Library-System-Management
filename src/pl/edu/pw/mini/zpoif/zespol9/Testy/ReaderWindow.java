package pl.edu.pw.mini.zpoif.zespol9.Testy;

import pl.edu.pw.mini.zpoif.zespol9.Book.Book;

import pl.edu.pw.mini.zpoif.zespol9.Book.BookFormat;
import pl.edu.pw.mini.zpoif.zespol9.Book.Genre;

import pl.edu.pw.mini.zpoif.zespol9.Book.Status;
import pl.edu.pw.mini.zpoif.zespol9.People.Reader;

import pl.edu.pw.mini.zpoif.zespol9.System.LibrarySystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ReaderWindow extends JFrame {

    private Reader myReader;

    public ReaderWindow(LibrarySystem librarySystem, Reader reader){
        this.myReader = reader;


        setTitle("Reader Window");
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
        JButton jButton2 = new JButton("My Account");
        jButton2.setFont(fontTab);
        jButton2.setForeground(new Color(239, 221, 191, 255));

        JButton logOutButton = new JButton("Log Out");
        logOutButton.setFont(fontTab);
        logOutButton.setForeground(new Color(239, 221, 191, 255));


        JPanel rightPanel = new JPanel();

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                implementCatalogue(librarySystem);

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

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LokigGui lokigGui = new LokigGui(librarySystem);
                dispose();
            }
        });

        jButton2.setPreferredSize(new Dimension(160, 50));
        jButton1.setPreferredSize(new Dimension(160, 50));
        logOutButton.setPreferredSize(new Dimension(160, 50));
        jButton1.setBackground(new Color(107, 79, 51));
        jButton2.setBackground(new Color(107, 79, 51));
        logOutButton.setBackground(new Color(107, 79, 51));

        leftPanel.add(jButton1);
        leftPanel.add(jButton2);
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


        // upperPanel with search option
        JLabel labelTextCatalogue = new JLabel();
        labelTextCatalogue.setSize(new Dimension(910, 50));
        labelTextCatalogue.setText("<HTML>Explore our library catalog to discover our extensive collection of books.<br> " +
                "Select a category to browse and find the literary treasures housed in our library.</HTML>");
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


        titleSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titleText = titleSearchField.getText();
                titleSearchField.setText("");
                columnpanel.removeAll();

                int i = 0;
                for (Book book : librarySystem.getCatalogue().searchByTitle(titleText)) {
                    JPanel rowPanel = new JPanel();
                    rowPanel.setPreferredSize(new Dimension(1100,100));
                    columnpanel.add(rowPanel);
                    rowPanel.setLayout(null);

                    JLabel bookLabel = new JLabel(book.toStringCatalogue());
                    bookLabel.setFont(font);
                    bookLabel.setBounds(5, 5, 1100, 50);
                    rowPanel.add(bookLabel);

                    addCatalogueButton(rowPanel, book);

                    i ++;
                    if(i%2==0)
                        rowPanel.setBackground(SystemColor.inactiveCaptionBorder);
                }
                columnpanel.revalidate();
            }
        });
        // end search by title


        // search by author
        JLabel authorSearchLabel = new JLabel("Search by author: ");
        JTextField authorSearchField = new JTextField("");
        JButton authorSearchButton = new JButton("Search");

        authorSearchLabel.setBounds(5, 100, 120, 25);
        authorSearchField.setBounds(135, 100, 230, 25);
        authorSearchButton.setBounds(375, 100, 80, 25 );

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

                columnpanel.removeAll();

                int i = 0;

                for (Book book : librarySystem.getCatalogue().searchByAuthor(authorText)) {
                    JPanel rowPanel = new JPanel();
                    rowPanel.setPreferredSize(new Dimension(1100,100));
                    columnpanel.add(rowPanel);
                    rowPanel.setLayout(null);

                    JLabel bookLabel = new JLabel(book.toStringCatalogue());
                    bookLabel.setFont(font);
                    bookLabel.setBounds(5, 5, 1100, 50);
                    rowPanel.add(bookLabel);

                    addCatalogueButton(rowPanel, book);

                    i ++;
                    if(i%2==0)
                        rowPanel.setBackground(SystemColor.inactiveCaptionBorder);
                }
                columnpanel.revalidate();
            }
        });
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
        genreSearchButton.setFont(font);

        upperPanel.add(genreSearchLabel);
        upperPanel.add(genreJComboBox);
        upperPanel.add(genreSearchButton);

        genreSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                columnpanel.removeAll();

                Genre selectedGenre = (Genre) genreJComboBox.getSelectedItem();
                genreJComboBox.setSelectedIndex(0);

                int i = 0;

                for (Book book : librarySystem.getCatalogue().searchByGenre(selectedGenre)) {
                    JPanel rowPanel = new JPanel();
                    rowPanel.setPreferredSize(new Dimension(1100,100));
                    columnpanel.add(rowPanel);
                    rowPanel.setLayout(null);

                    JLabel bookLabel = new JLabel(book.toStringCatalogue());
                    bookLabel.setFont(font);
                    bookLabel.setBounds(5, 5, 1100, 50);
                    rowPanel.add(bookLabel);

                    addCatalogueButton(rowPanel, book);

                    i ++;
                    if(i%2==0)
                        rowPanel.setBackground(SystemColor.inactiveCaptionBorder);
                }
                columnpanel.revalidate();
            }
        });
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

        //add to read button
        JButton addToReadButton = new JButton("Add to read");
        addToReadButton.setBounds(170, 60, 150, 30);
        addToReadButton.setFont(font);
        addToReadButton.setBackground(new Color(204, 131, 141));
        addToReadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myReader.addToReadBook(book);
            }
        });
        rowPanel.add(addToReadButton);
        //end add to read


        if(book.bookFormat == BookFormat.Ebook) {
            JButton downloadButton = new JButton("Download");
            downloadButton.setBounds(335, 60, 150, 30);
            downloadButton.setFont(font);
            downloadButton.setBackground(new Color(157, 154, 151));

            downloadButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(rowPanel, "Thank you from downloading our book ;)", "Downloading", JOptionPane.PLAIN_MESSAGE);
                }
            });
            rowPanel.add(downloadButton);

        } else {
            JButton reserveButton = new JButton("Reserve");
            reserveButton.setBounds(335, 60, 150, 30);
            if (book.status == Status.Available){
                reserveButton.setBackground(new Color(130, 164, 114));
                reserveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        myReader.reserveBook(book);
                        reserveButton.setBackground(new Color(215, 99, 83));
                        rowPanel.removeAll();
                        rowPanel.setLayout(null);

                        JLabel label = new JLabel("This book has been successfully reserved for you. Happy reading!");
                        label.setFont(font);
                        label.setBounds(15,25, 500, 40);
                        label.setVisible(true);
                        rowPanel.add(label);

                        rowPanel.revalidate();
                        rowPanel.repaint();

                    }
                });
            } else {
                reserveButton.setBackground(new Color(215, 99, 83));
                reserveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(rowPanel, "<html><div style='text-align: center;'>The book " +
                                "is not available for reservation at the moment. It is either reserved or on loan.<br>Please " +
                                "choose another book or check back later.<br>Thank you for your understanding.</html>",
                                "Currently unavailable for reservation", JOptionPane.PLAIN_MESSAGE);
                    }
                });
            }
            rowPanel.add(reserveButton);
        }
    }

    private void implementAccount() {
        JPanel rightPanel = (JPanel) getContentPane().getComponent(2);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JPanel upperPanel = new JPanel();
        JPanel lowerPanel = new JPanel();

        upperPanel.setBackground(new Color(206, 190, 170, 255));
        upperPanel.setSize(new Dimension(620, 200));
        lowerPanel.setLayout(new GridLayout(3, 1));

        JPanel reservedBooksPanel = new JPanel();
        reservedBooksPanel.setBackground(new Color(238, 232, 223, 255));
        lowerPanel.add(reservedBooksPanel);
        JPanel checkedOutBooksPanel = new JPanel();
        checkedOutBooksPanel.setBackground(new Color(238, 232, 223, 255));
        lowerPanel.add(checkedOutBooksPanel);
        JPanel toReadBooksPanel = new JPanel();
        toReadBooksPanel.setBackground(new Color(238, 232, 223, 255));
        lowerPanel.add(toReadBooksPanel);

        Font font = new Font("MV Boli", Font.BOLD, 18);
        Font font1 = new Font("Serif", Font.BOLD, 16);

        // panel na gorze:
        upperPanel.setLayout(new GridLayout(4, 2));
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(font);
        upperPanel.add(nameLabel);
        JLabel j1 = new JLabel(this.myReader.getName());
        j1.setFont(font1);
        upperPanel.add(j1);

        JLabel surnameLabel = new JLabel("Surname:");
        surnameLabel.setFont(font);
        upperPanel.add(surnameLabel);
        JLabel j2 = new JLabel(this.myReader.getSurname());
        j2.setFont(font1);
        upperPanel.add(j2);

        JLabel loginLabel = new JLabel("Login:");
        loginLabel.setFont(font);
        upperPanel.add(loginLabel);
        JLabel j3 = new JLabel(this.myReader.getSignInData().getLogin());
        j3.setFont(font1);
        upperPanel.add(j3);

        JLabel fineLabel = new JLabel("Fine:");
        fineLabel.setFont(font);
        upperPanel.add(fineLabel);
        JLabel j4 = new JLabel(String.valueOf(this.myReader.getFine()));
        j4.setFont(font1);
        upperPanel.add(j4);

        // reserved books:

        reservedBooksPanel.setLayout(new FlowLayout());
        reservedBooksPanel.setBackground(new Color(238, 232, 223, 255));
        JLabel jReserved = new JLabel("Reserved Books:");
        jReserved.setFont(font1);
        reservedBooksPanel.add(jReserved);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Book el: myReader.getReservedBooks()){
            listModel.addElement("Title:    " + el.title);
            listModel.addElement("Author:   " + el.author);
            listModel.addElement(" ");
        }
        JList<String> elementList = new JList<>(listModel);
        elementList.setSelectionBackground(null);
        JScrollPane scrollPane1 = new JScrollPane(elementList);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1.setPreferredSize(new Dimension(800, 100));
        scrollPane1.getViewport().setBackground(new Color(238, 232, 223, 255));
        scrollPane1.setViewportBorder(BorderFactory.createEmptyBorder());
        elementList.setBackground(new Color(238, 232, 223, 255));
        reservedBooksPanel.add(scrollPane1);


        //checked out books:
        JLabel jCheckedOut = new JLabel("Checked Out Books: ");
        jCheckedOut.setFont(font1);
        checkedOutBooksPanel.add(jCheckedOut);
        checkedOutBooksPanel.setLayout(new FlowLayout());

        Map<Book, LocalDate> mapaReader = myReader.getCheckedOutBooks();
        List<ClassWithComponentsToBePrinted> listadoPrintowania = new ArrayList<>();

        for (Map.Entry<Book, LocalDate> entry : mapaReader.entrySet()) {
            listadoPrintowania.add(new ClassWithComponentsToBePrinted(entry.getKey(), entry.getValue()));
        }


        DefaultListModel<ClassWithComponentsToBePrinted> defaultListModel = new DefaultListModel<>();
        for (int i = 0; i < mapaReader.size(); i ++){
            defaultListModel.addElement(listadoPrintowania.get(i));
        }

        JButton jButton = new JButton("Postpone");
        JList<ClassWithComponentsToBePrinted> elementList3 = new JList<>(defaultListModel);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedIndex = elementList3.getSelectedIndex();
                if (selectedIndex != -1) {
                    ClassWithComponentsToBePrinted selectedBook = defaultListModel.getElementAt(selectedIndex);
                    Book book = selectedBook.book;
                    myReader.postponeReturnDate(book);
                    ClassWithComponentsToBePrinted x = new ClassWithComponentsToBePrinted(book, myReader.getCheckedOutBooks().get(book));
                    defaultListModel.setElementAt(x, selectedIndex);

                }

            }
        });
        jButton.setEnabled(false);
        checkedOutBooksPanel.add(jButton);


        elementList3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        elementList3.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean isSelectionEmpty = elementList3.isSelectionEmpty();
                jButton.setEnabled(!isSelectionEmpty);
            }
        });

        elementList3.setCellRenderer(new PrintInFourRows());
        JScrollPane scrollPane3 = new JScrollPane(elementList3);
        scrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane3.setPreferredSize(new Dimension(800, 100));
        scrollPane3.getViewport().setBackground(new Color(238, 232, 223, 255));
        scrollPane3.setViewportBorder(BorderFactory.createLineBorder(new Color(238, 232, 223, 255), 2));
        elementList3.setBackground(new Color(238, 232, 223, 255));
        checkedOutBooksPanel.add(scrollPane3);


        //to read:
        JLabel jRead = new JLabel("To Read Books: ");
        jRead.setFont(font1);
        toReadBooksPanel.add(jRead);
        toReadBooksPanel.setLayout(new FlowLayout());
        List<Book> toReadList = this.myReader.getToReadBooks();
        DefaultListModel<String> listModel1 = new DefaultListModel<>();
        for (Book el: toReadList){
            listModel1.addElement("Title:    " + el.title);
            listModel1.addElement("Author:   " + el.author);
            listModel1.addElement(" ");
        }
        JList<String> elementList2 = new JList<>(listModel1);
        elementList2.setSelectionBackground(null);
        JScrollPane scrollPane2 = new JScrollPane(elementList2);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.setPreferredSize(new Dimension(800, 100));
        scrollPane2.getViewport().setBackground(new Color(238, 232, 223, 255));
        scrollPane2.setViewportBorder(BorderFactory.createLineBorder(new Color(238, 232, 223, 255), 2));
        elementList2.setBackground(new Color(238, 232, 223, 255));
        toReadBooksPanel.add(scrollPane2);


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

    public class ClassWithComponentsToBePrinted{

        public Book book;

        public String tytul;
        public String autor;
        public String data;
        public String pusty;

        public ClassWithComponentsToBePrinted(Book book, LocalDate localDate){
            this.book = book;
            this.tytul = book.title;
            this.autor = book.author;
            this.data = String.valueOf(localDate);
            this.pusty = " ";
        }

    }

    class  PrintInFourRows extends JPanel implements ListCellRenderer<ClassWithComponentsToBePrinted>{
        private JLabel titleLabel;
        private JLabel authorLabel;
        private JLabel dataLabel;
        private JLabel emptuLabel;

        public PrintInFourRows() {
            setLayout(new GridLayout(4, 1));
            titleLabel = new JLabel();
            authorLabel = new JLabel();
            dataLabel = new JLabel();
            emptuLabel = new JLabel();

            // Dodanie etykiet do panelu
            add(titleLabel);
            add(authorLabel);
            add(dataLabel);
            add(emptuLabel);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends ClassWithComponentsToBePrinted> list, ClassWithComponentsToBePrinted value, int index, boolean isSelected, boolean cellHasFocus) {
            titleLabel.setText(value.tytul);
            authorLabel.setText(value.autor);
            dataLabel.setText(value.data);
            emptuLabel.setText(value.pusty);

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            return this;
        }
    }


}
