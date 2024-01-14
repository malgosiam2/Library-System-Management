package pl.edu.pw.mini.zpoif.zespol9.Testy;

import pl.edu.pw.mini.zpoif.zespol9.Book.Book;
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

    public PanelWithScrollPane(String title, Reader myReader){
        this.title = title;
        this.myReader = myReader;
        //createPanel();
    }

    public void createPanel(JPanel checkedOutBooksPanel){
        checkedOutBooksPanel.setBackground(new Color(238, 232, 223, 255));
        JLabel jCheckedOut = new JLabel(this.title);
//                jCheckedOut.setFont(font1);
        checkedOutBooksPanel.add(jCheckedOut);
        checkedOutBooksPanel.setLayout(new FlowLayout());

        Map<Book, LocalDate> mapaReader1 = myReader.getCheckedOutBooks();
        List<PanelWithScrollPane.ClassWithComponentsToBePrinted> listadoPrintowania1 = new ArrayList<>();

        for (Map.Entry<Book, LocalDate> entry : mapaReader1.entrySet()) {
            listadoPrintowania1.add(new PanelWithScrollPane.ClassWithComponentsToBePrinted(entry.getKey(), entry.getValue()));
        }


        DefaultListModel<PanelWithScrollPane.ClassWithComponentsToBePrinted> defaultListModel1 = new DefaultListModel<>();
        for (int i = 0; i < mapaReader1.size(); i ++){
            defaultListModel1.addElement(listadoPrintowania1.get(i));
        }

        JButton jButton1 = new JButton("Return");
        JList<PanelWithScrollPane.ClassWithComponentsToBePrinted> elementList1 = new JList<>(defaultListModel1);

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedIndex = elementList1.getSelectedIndex();
                if (selectedIndex != -1) {
                    PanelWithScrollPane.ClassWithComponentsToBePrinted selectedBook = defaultListModel1.getElementAt(selectedIndex);
                    Book book = selectedBook.book;
                    //librarian.
//                            myReader.postponeReturnDate(book);
//                            LibrarianWindow.ClassWithComponentsToBePrinted x = new ReaderWindow.ClassWithComponentsToBePrinted(book, myReader.getCheckedOutBooks().get(book));
//                            defaultListModel.setElementAt(x, selectedIndex);

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
        scrollPane1.getViewport().setBackground(new Color(238, 232, 223, 255));
        scrollPane1.setViewportBorder(BorderFactory.createLineBorder(new Color(238, 232, 223, 255), 2));
        elementList1.setBackground(new Color(238, 232, 223, 255));
        checkedOutBooksPanel.add(scrollPane1);


        //return checkedOutBooksPanel;
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
