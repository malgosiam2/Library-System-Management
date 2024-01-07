package pl.edu.pw.mini.zpoif.zespol9.Testy;

import pl.edu.pw.mini.zpoif.zespol9.Book.Book;
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
import java.util.Arrays;
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
            Graphics2D g2d = image.createGraphics();

            ImageIcon imageIcon = new ImageIcon(image);
            JLabel imageLabel = new JLabel(imageIcon);
            imagePanel.add(imageLabel);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // dodajemy lewy panel z zakładkami:
        JPanel leftPanel = new JPanel(new FlowLayout());
        leftPanel.setBackground(new Color(188, 122, 47));
        leftPanel.setPreferredSize(new Dimension(180, 0));


        JButton jButton1 = new JButton("Catalogue");
        JButton jButton2 = new JButton("My Account");

        JPanel rightPanel = new JPanel();

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                implementCatalogue(leftPanel);

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
        jButton1.setBackground(new Color(188, 122, 47));
        jButton2.setBackground(new Color(188, 122, 47));

        leftPanel.add(jButton1);
        leftPanel.add(jButton2);
        rightPanel.setLayout(new BorderLayout());

        setLayout(new BorderLayout());
        add(imagePanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void implementCatalogue(JPanel jPanel) {
        jPanel.setBackground(Color.yellow);
    }

    private void implementAccount() {
        JPanel rightPanel = (JPanel) getContentPane().getComponent(2);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JPanel upperPanel = new JPanel();
        JPanel lowerPanel = new JPanel();

        upperPanel.setBackground(new Color(232, 169, 61));
        upperPanel.setSize(new Dimension(920, 250));
        lowerPanel.setLayout(new GridLayout(3, 1));

        JPanel reservedBooksPanel = new JPanel();
        reservedBooksPanel.setBackground(new Color(255, 210, 131));
        lowerPanel.add(reservedBooksPanel);
        JPanel checkedOutBooksPanel = new JPanel();
        checkedOutBooksPanel.setBackground(new Color(255, 210, 131));
        lowerPanel.add(checkedOutBooksPanel);
        JPanel toReadBooksPanel = new JPanel();
        toReadBooksPanel.setBackground(new Color(255, 210, 131));
        lowerPanel.add(toReadBooksPanel);

        Font font = new Font("MV Boli", Font.BOLD, 18);
        Font font1 = new Font("Ariel", Font.BOLD, 16);

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
        reservedBooksPanel.setBackground(new Color(255, 210, 131));
        reservedBooksPanel.add(new JLabel("Reserved Books: "));

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
        scrollPane1.getViewport().setBackground(new Color(255, 210, 131));
        scrollPane1.setViewportBorder(BorderFactory.createEmptyBorder());
        elementList.setBackground(new Color(255, 210, 131));
        reservedBooksPanel.add(scrollPane1);


        //checked out books:
        checkedOutBooksPanel.add(new JLabel("Checked Out Books: "));
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
        scrollPane3.getViewport().setBackground(new Color(255, 210, 131));
        scrollPane3.setViewportBorder(BorderFactory.createLineBorder(new Color(255, 210, 131), 2));
        elementList3.setBackground(new Color(255, 210, 131));
        checkedOutBooksPanel.add(scrollPane3);


        //to read:
        toReadBooksPanel.add(new JLabel("To Read Books: "));
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
        scrollPane2.getViewport().setBackground(new Color(255, 210, 131));
        scrollPane2.setViewportBorder(BorderFactory.createLineBorder(new Color(255, 210, 131), 2));
        elementList2.setBackground(new Color(255, 210, 131));
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
