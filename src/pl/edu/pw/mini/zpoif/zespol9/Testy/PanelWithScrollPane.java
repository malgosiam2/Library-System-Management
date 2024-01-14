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

    private String title;
    private Reader myReader;
    private Librarian librarian;
    private String buttonTitle;

    public PanelWithScrollPane(String title, Reader myReader, String buttonTitle, Librarian librarian){
        this.title = title;
        this.myReader = myReader;
        this.buttonTitle = buttonTitle;
        this.librarian = librarian;
    }

    public void createPanel(JPanel jPanel){
        jPanel.setBackground(new Color(238, 232, 223, 255));
        JLabel jCheckedOut = new JLabel(this.title);
//                jCheckedOut.setFont(font1);
        jPanel.add(jCheckedOut);
        jPanel.setLayout(new FlowLayout());

        DefaultListModel<PanelWithScrollPane.ClassWithComponentsToBePrinted> defaultListModel = new DefaultListModel<>();
        if (buttonTitle.equals("Return Book")){
            Map<Book, LocalDate> mapReader = myReader.getCheckedOutBooks();
            List<PanelWithScrollPane.ClassWithComponentsToBePrinted> toBePrinted = new ArrayList<>();
            for (Map.Entry<Book, LocalDate> entry : mapReader.entrySet()) {
                toBePrinted.add(new PanelWithScrollPane.ClassWithComponentsToBePrinted(entry.getKey(), entry.getValue()));
            }

            for (int i = 0; i < mapReader.size(); i ++){
                defaultListModel.addElement(toBePrinted.get(i));
            }

        }

        else if (buttonTitle.equals("Check Out Book")){
            List<Book> listReader = myReader.getReservedBooks();
            List<PanelWithScrollPane.ClassWithComponentsToBePrinted> toBePrinted = new ArrayList<>();
            for (Book book: listReader) {
                toBePrinted.add(new PanelWithScrollPane.ClassWithComponentsToBePrinted(book));
            }

            for (int i = 0; i < listReader.size(); i ++){
                defaultListModel.addElement(toBePrinted.get(i));
            }
        }


        JButton jButton1 = new JButton(buttonTitle);
        JList<PanelWithScrollPane.ClassWithComponentsToBePrinted> elementList = new JList<>(defaultListModel);

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedIndex = elementList.getSelectedIndex();
                if (selectedIndex != -1) {
                    PanelWithScrollPane.ClassWithComponentsToBePrinted selectedBook = defaultListModel.getElementAt(selectedIndex);
                    Book book = selectedBook.book;
                    if (buttonTitle.equals("Check Out Book")){
                        try {
                            librarian.CheckOutBookFromReservedBooks(myReader.getSignInData().getLogin(), book);
                            defaultListModel.remove(selectedIndex);
                            revalidate();
                            repaint();
                        } catch (NoReaderWithThatLoginException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                    else if (buttonTitle.equals("Return Book")){
                        try {
                            librarian.acceptBookReturn(myReader.getSignInData().getLogin(), book, book.bookCondition);
                        } catch (NoReaderWithThatLoginException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
//                            LibrarianWindow.ClassWithComponentsToBePrinted x = new ReaderWindow.ClassWithComponentsToBePrinted(book, myReader.getCheckedOutBooks().get(book));
//                            defaultListModel.setElementAt(x, selectedIndex);

                }

            }
        });
        jButton1.setEnabled(false);
        jPanel.add(jButton1);


        elementList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        elementList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean isSelectionEmpty = elementList.isSelectionEmpty();
                jButton1.setEnabled(!isSelectionEmpty);
            }
        });

        elementList.setCellRenderer(new PanelWithScrollPane.PrintInFourRows());
        JScrollPane scrollPane1 = new JScrollPane(elementList);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1.setPreferredSize(new Dimension(800, 100));
        scrollPane1.getViewport().setBackground(new Color(238, 232, 223, 255));
        scrollPane1.setViewportBorder(BorderFactory.createLineBorder(new Color(238, 232, 223, 255), 2));
        elementList.setBackground(new Color(238, 232, 223, 255));
        jPanel.add(scrollPane1);

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
