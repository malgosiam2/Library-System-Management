package pl.edu.pw.mini.zpoif.zespol9.Testy;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class ReaderWindow extends JFrame {

    public ReaderWindow(){

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

    public static void main(String[] args) {
        ReaderWindow readerWindow = new ReaderWindow();
    }
}
