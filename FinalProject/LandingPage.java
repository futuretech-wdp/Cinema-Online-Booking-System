
package FinalProject;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class LandingPage extends JFrame implements ActionListener {

    JPanel panel = new JPanel();

    JButton cbtn = new JButton("Continue --->");
    JButton adminBtn = new JButton("ADMIN LOGIN");

    LandingPage() {
        setTitle("CineVibe");
        setSize(1500, 1080);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background image
        ImageIcon image = new ImageIcon("C:\\Users\\Rance\\Downloads\\837948313_1906125997224804_8372270992661686723_n.jpg");

        Image scaledImage = image.getImage().getScaledInstance(1500, 1010, Image.SCALE_SMOOTH);

        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JLabel background = new JLabel(resizedIcon);
        background.setBounds(0, 0, 1500, 1010);

        // Layered pane
        JLayeredPane layeredPane = getLayeredPane();

        background.setBounds(0, 0, 1500, 1010);
        layeredPane.add(background, JLayeredPane.DEFAULT_LAYER);

        // Top panel
        panel.setBackground(new Color(13, 9, 36));
        panel.setBounds(0, 0, 1500, 70);
        panel.setLayout(null);
        layeredPane.add(panel, JLayeredPane.PALETTE_LAYER);

        // Continue Button - Customer
        cbtn.setBounds(120, 550, 200, 50);
        cbtn.setBackground(new Color(121, 37, 245));
        cbtn.setForeground(Color.WHITE);
        cbtn.setFont(new Font("Arial", Font.BOLD, 20));
        cbtn.setFocusPainted(false);
        cbtn.setBorderPainted(false);
        cbtn.setOpaque(true);
        cbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cbtn.addActionListener(this);

        layeredPane.add(cbtn, JLayeredPane.PALETTE_LAYER);

        // Admin Login Button - Top Right
        adminBtn.setBounds(1280, 15, 160, 40);
        adminBtn.setBackground(new Color(121, 37, 245));
        adminBtn.setForeground(Color.WHITE);
        adminBtn.setFont(new Font("Arial", Font.BOLD, 15));
        adminBtn.setFocusPainted(false);
        adminBtn.setBorderPainted(false);
        adminBtn.setOpaque(true);
        adminBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        adminBtn.addActionListener(this);

        layeredPane.add(adminBtn, JLayeredPane.MODAL_LAYER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == cbtn) {
            DashBoard movies = new DashBoard();
            movies.setVisible(true);
            dispose();
        }

        if (e.getSource() == adminBtn) {
            Login login = new Login();
            login.setVisible(true);
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LandingPage());
    }
}
