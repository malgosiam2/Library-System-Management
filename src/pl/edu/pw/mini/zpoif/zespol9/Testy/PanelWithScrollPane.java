package pl.edu.pw.mini.zpoif.zespol9.Testy;

import pl.edu.pw.mini.zpoif.zespol9.Book.Book;
import pl.edu.pw.mini.zpoif.zespol9.Exceptions.NoReaderWithThatLoginException;
import pl.edu.pw.mini.zpoif.zespol9.People.Librarian;
import pl.edu.pw.mini.zpoif.zespol9.People.Reader;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PanelWithScrollPane extends JPanel {

    private Reader myReader;
    private Librarian librarian;
    private JPanel reservedBooksPanel;
    private JPanel checkedOutBooksPanel;

    public PanelWithScrollPane(JPanel reservedBookaPanel, JPanel checkedOutBooksPanel, Reader myReader, Librarian librarian){

        this.myReader = myReader;
        this.checkedOutBooksPanel = checkedOutBooksPanel;
        this.reservedBooksPanel = reservedBookaPanel;
        this.librarian = librarian;
    }

    public void createPanel(){
        DefaultListModel<PanelWithScrollPane.ClassWithComponentsToBePrinted> defaultListModel = new DefaultListModel<>();
        DefaultListModel<PanelWithScrollPane.ClassWithComponentsToBePrinted> defaultListModel1 = new DefaultListModel<>();

        reservedBooksPanel.setBackground(new Color(238, 232, 223, 255));
        checkedOutBooksPanel.setBackground(new Color(238, 232, 223, 255));

        JLabel jReserved = new JLabel("Reserved Books");
////                jCheckedOut.setFont(font1);
        reservedBooksPanel.add(jReserved);
        reservedBooksPanel.setLayout(new FlowLayout());
        List<Book> listReader = myReader.getReservedBooks();
        List<PanelWithScrollPane.ClassWithComponentsToBePrinted> toBePrinted = new ArrayList<>();
        for (Book book: listReader) {
            toBePrinted.add(new PanelWithScrollPane.ClassWithComponentsToBePrinted(book));
        }

        for (int i = 0; i < listReader.size(); i ++){
            defaultListModel.addElement(toBePrinted.get(i));
        }

        JButton jButton = new JButton("Check Out Book");
        JList<PanelWithScrollPane.ClassWithComponentsToBePrinted> elementList = new JList<>(defaultListModel);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedIndex = elementList.getSelectedIndex();
                if (selectedIndex != -1) {
                    PanelWithScrollPane.ClassWithComponentsToBePrinted selectedBook = defaultListModel.getElementAt(selectedIndex);
                    Book book = selectedBook.book;
                    try {
                        librarian.CheckOutBookFromReservedBooks(myReader.getSignInData().getLogin(), book);
                        JOptionPane.showMessageDialog(PanelWithScrollPane.this, "The book has been checked out");
                        defaultListModel.remove(selectedIndex);
                        defaultListModel1.addElement(selectedBook);
                        checkedOutBooksPanel.revalidate();
                        checkedOutBooksPanel.repaint();
                        reservedBooksPanel.revalidate();
                        reservedBooksPanel.repaint();

                    } catch (NoReaderWithThatLoginException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            }
        });
        jButton.setEnabled(false);
        reservedBooksPanel.add(jButton);
        elementList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        elementList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean isSelectionEmpty = elementList.isSelectionEmpty();
                jButton.setEnabled(!isSelectionEmpty);
            }
        });

        elementList.setCellRenderer(new PanelWithScrollPane.PrintInFourRows());
        JScrollPane scrollPane = new JScrollPane(elementList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(800, 100));
        scrollPane.getViewport().setBackground(new Color(238, 232, 223, 255));
        scrollPane.setViewportBorder(BorderFactory.createLineBorder(new Color(238, 232, 223, 255), 2));
        elementList.setBackground(new Color(238, 232, 223, 255));
        reservedBooksPanel.add(scrollPane);

        JLabel jCheckedOut = new JLabel("Checked Out Books");
////                jCheckedOut.setFont(font1);
        checkedOutBooksPanel.add(jCheckedOut);
        checkedOutBooksPanel.setLayout(new FlowLayout());

        Map<Book, LocalDate> mapReader = myReader.getCheckedOutBooks();
        List<PanelWithScrollPane.ClassWithComponentsToBePrinted> toBePrinted1 = new ArrayList<>();
        for (Map.Entry<Book, LocalDate> entry : mapReader.entrySet()) {
            toBePrinted1.add(new PanelWithScrollPane.ClassWithComponentsToBePrinted(entry.getKey(), entry.getValue()));
        }

        for (int i = 0; i < mapReader.size(); i ++){
            defaultListModel1.addElement(toBePrinted1.get(i));
        }


        JButton jButton1 = new JButton("Return Book");
        JList<PanelWithScrollPane.ClassWithComponentsToBePrinted> elementList1 = new JList<>(defaultListModel1);

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedIndex = elementList1.getSelectedIndex();
                if (selectedIndex != -1) {
                    PanelWithScrollPane.ClassWithComponentsToBePrinted selectedBook = defaultListModel1.getElementAt(selectedIndex);
                    Book book = selectedBook.book;
                        try {
                            librarian.acceptBookReturn(myReader.getSignInData().getLogin(), book, book.bookCondition);
                            defaultListModel1.remove(selectedIndex);
                            JOptionPane.showMessageDialog(PanelWithScrollPane.this, "The book has been returned");
                            revalidate();
                            repaint();
                        } catch (NoReaderWithThatLoginException ex) {
                            throw new RuntimeException(ex);
                        }
                }

            }
        });
        jButton1.setEnabled(false);
        checkedOutBooksPanel.add(jButton1);

        elementList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        elementList1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean isSelectionEmpty = elementList1.isSelectionEmpty();
                jButton1.setEnabled(!isSelectionEmpty);
            }
        });

        elementList1.setCellRenderer(new PanelWithScrollPane.PrintInFourRows());
        JScrollPane scrollPane1 = new JScrollPane(elementList1);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1.setPreferredSize(new Dimension(800, 100));
        scrollPane1.setBackground(new Color(238, 232, 223, 255));
        scrollPane1.getViewport().setBackground(new Color(238, 232, 223, 255));
        scrollPane1.setViewportBorder(BorderFactory.createLineBorder(new Color(238, 232, 223, 255), 2));
        elementList1.setBackground(new Color(238, 232, 223, 255));
        checkedOutBooksPanel.add(scrollPane1);
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

        public ClassWithComponentsToBePrinted(Book book){
            this.book = book;
            this.tytul = book.title;
            this.autor = book.author;
            this.data = " ";
            this.pusty = " ";
        }

    }

    class  PrintInFourRows extends JPanel implements ListCellRenderer<PanelWithScrollPane.ClassWithComponentsToBePrinted>{
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
        public Component getListCellRendererComponent(JList<? extends PanelWithScrollPane.ClassWithComponentsToBePrinted> list, PanelWithScrollPane.ClassWithComponentsToBePrinted value, int index, boolean isSelected, boolean cellHasFocus) {
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
