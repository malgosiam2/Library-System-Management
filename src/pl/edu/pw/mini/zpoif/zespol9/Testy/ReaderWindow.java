package pl.edu.pw.mini.zpoif.zespol9.Testy;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ReaderWindow extends JFrame {

    public ReaderWindow(){
        setTitle("Reader Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1000, 800));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        try {
            BufferedImage image = ImageIO.read(new File("resources/WelcomeBack.png"));
            Graphics2D g2d = image.createGraphics();

            ImageIcon imageIcon = new ImageIcon(image);
            JLabel imageLabel = new JLabel(imageIcon);
            panel.add(imageLabel, BorderLayout.NORTH);

        } catch (IOException e) {
            e.printStackTrace();
        }


        add(panel);
        setVisible(true);

    }

}
