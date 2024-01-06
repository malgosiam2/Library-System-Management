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
        //cale okno
        setTitle("Reader Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1000, 800));
        getContentPane().setBackground(new Color(0, 0, 0));
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        //JPanel rightpanel = new JPanel();
        //rightpanel.setLayout(new BorderLayout());

        //dodajemy panel górny z obrazkiem:
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(getWidth(), 100));
        try {
            BufferedImage image = ImageIO.read(new File("resources/WelcomeBack.png"));
            Graphics2D g2d = image.createGraphics();

            ImageIcon imageIcon = new ImageIcon(image);
            JLabel imageLabel = new JLabel(imageIcon);
            imagePanel.add(imageLabel);

        } catch (IOException e) {
            e.printStackTrace();
        }
        add(imagePanel, BorderLayout.NORTH);

        //dodajemy prawy (glowny panel):
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(108, 169, 223));
        rightPanel.setPreferredSize(new Dimension(getWidth() - 190, getHeight() - 100));
        add(rightPanel, BorderLayout.EAST);

        // dodajemy lewy panel z zakładkami:
        JPanel leftPanel = new JPanel(new FlowLayout());
        leftPanel.setBackground(new Color(108, 169, 223));
        leftPanel.setPreferredSize(new Dimension(170, getHeight() - 100));


        JButton jButton1 = new JButton("Catalogue");
        JButton jButton2 = new JButton("My Account");
        jButton2.setPreferredSize(new Dimension(160, 50));
        jButton1.setPreferredSize(new Dimension(160, 50));
        jButton1.setBackground(new Color(108, 169, 223));
        jButton2.setBackground(new Color(108, 169, 223));


        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                implementCatalogue(rightPanel);

            }
        });


        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                implementAccount(rightPanel);

            }
        });

        leftPanel.add(jButton1);
        leftPanel.add(jButton2);

        //zakladki:
        //JTabbedPane jTabbedPane = new JTabbedPane();
        //jTabbedPane.addTab("Zakładka 2", createPanel(""));
        //jTabbedPane.addTab("Zakładka 1", createPanel(""));

        //jTabbedPane.addChangeListener(el -> {
          //  int index = jTabbedPane.getSelectedIndex();
            //updateRightPanel(rightPanel, index);

        //});

        //leftPanel.add(jTabbedPane);


        add(leftPanel, BorderLayout.WEST);


        //JTabbedPane tabbedPane = new JTabbedPane();
        //tabbedPane.add("Zakładka 1", createPanel("Zawartość zakłądki 1"));
        //tabbedPane.add("Zakładka 2", createPanel("Zawartość zakłądki 2"));

        //tabbedPane.add("Zakładka1", createPanel("s"));

        //leftPanel.add(tabbedPane);
        setVisible(true);

    }

    private void implementCatalogue(JPanel jPanel){
        jPanel.setBackground(Color.yellow);
    }

    private void implementAccount(JPanel jPanel){
        jPanel.setBackground(Color.white);

    }

    private JPanel createPanel(String s){
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel(s));
        return jPanel;
    }

    public static void main(String[] args) {
        ReaderWindow r = new ReaderWindow();
    }

}
