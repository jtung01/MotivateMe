package ui;


import javax.swing.*;
import java.awt.*;

// Produces the image for reward
public class ImageGUI extends JFrame {
    private static final int width = 400;
    private static final int height = 400;
    private JPanel picPanel;
    private JDesktopPane deskPanel;
    private ImageIcon img;

    // EFFECTS: Constructs the panel, desktop, and loads image
    public ImageGUI() {
        deskPanel = new JDesktopPane();

        picPanel = new JPanel();
        picPanel.setPreferredSize(new Dimension(width, height));

        String sep = System.getProperty("file.separator");
        img = new ImageIcon(System.getProperty("user.dir") + sep + "images" + sep + "homer.jpg");
        JLabel label = new JLabel(img);
        picPanel.add(label);

        setContentPane(deskPanel);
        setTitle("Reward Screen");
        setSize(img.getIconWidth(), img.getIconHeight());

        deskPanel.add(picPanel);
        picPanel.setSize(img.getIconWidth(), img.getIconHeight());
        picPanel.setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}



