package FinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DashBoard extends JFrame
        implements ActionListener, MouseListener {

    JPanel framep = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();

    JPanel dashPanel = new JPanel();
    JPanel logoutPanel = new JPanel(null);

    JLabel dashboardMenu = new JLabel("Dashboard");
    JLabel moviesMenu = new JLabel("View All Movies");
    JLabel logout = new JLabel("Back");

    JLabel lbl1 = new JLabel("Popular Now");

    JButton book1 = new JButton("BOOK NOW");
    JButton book2 = new JButton("BOOK NOW");
    JButton book3 = new JButton("BOOK NOW");
    JButton book4 = new JButton("BOOK NOW");
    JButton book5 = new JButton("BOOK NOW");

    JPanel[] pbook = new JPanel[5];
    JPanel[] panel3 = new JPanel[5];

    Color textColor = Color.WHITE;
    Color activeColor = new Color(169, 112, 255);
    Color dashBg = new Color(65, 35, 105);
    Color logoutColor = new Color(190, 150, 255);
    Color logoutBg = new Color(35, 24, 58);
    Color darkpurple = new Color(121, 37, 245);

    Font regularFont = new Font("Arial", Font.PLAIN, 16);
    Font boldFont = new Font("Arial", Font.BOLD, 18);

    DashBoard() {

        setTitle("CineVibe - Customer Dashboard");
        setLayout(null);
        setSize(1500, 850);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Panel
        framep.setBounds(0, 0, 1500, 1000);
        framep.setLayout(null);
        framep.setBackground(new Color(8, 6, 25));
        add(framep);

        ImageIcon logoIcon = new ImageIcon("C:\\Users\\Rance\\Downloads\\cinevibe_logo_crop.png");

        Image logoImage = logoIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);

        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setBounds(20, 80, 200, 100);

        panel1.add(logoLabel);

        panel1.setComponentZOrder(logoLabel, 0);

        // Sidebar
        panel1.setBackground(new Color(13, 9, 36));
        panel1.setBounds(0, 0, 250, 850);
        panel1.setLayout(null);
        framep.add(panel1);

        // Sidebar Background Image
        ImageIcon ico = new ImageIcon("C:\\Users\\Rance\\Downloads\\Gemini_Generated_Image_6wvo1f6wvo1f6wvo.jpg");

        Image scal = ico.getImage().getScaledInstance(250, 850, Image.SCALE_SMOOTH);

        JLabel sidebar = new JLabel(new ImageIcon(scal));
        sidebar.setBounds(0, 0, 250, 850);
        panel1.add(sidebar);

        // Dashboard Violet Panel
        dashPanel.setBounds(15, 220, 220, 50);
        dashPanel.setBackground(dashBg);
        dashPanel.setLayout(null);
        panel1.add(dashPanel);

        // Dashboard Label
        dashboardMenu.setForeground(new Color(200, 170, 255));
        dashboardMenu.setFont(boldFont);
        dashboardMenu.setBounds(15, 5, 190, 40);
        dashPanel.add(dashboardMenu);

        // View All Movies
        moviesMenu.setForeground(textColor);
        moviesMenu.setFont(regularFont);
        moviesMenu.setBounds(30, 285, 190, 40);
        moviesMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel1.add(moviesMenu);

        // Logout Panel
        logoutPanel.setBounds(20, 750, 210, 48);
        logoutPanel.setBackground(logoutBg);
        logoutPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutPanel.setBorder(BorderFactory.createLineBorder(new Color(121, 37, 245), 1));
        panel1.add(logoutPanel);

        // Logout Label
        logout.setFont(new Font("Arial", Font.BOLD, 16));
        logout.setForeground(logoutColor);
        logout.setBounds(15, 5, 180, 38);
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutPanel.add(logout);

        // Sidebar Mouse Listeners
        moviesMenu.addMouseListener(this);
        logout.addMouseListener(this);
        logoutPanel.addMouseListener(this);

        panel1.setComponentZOrder(sidebar, panel1.getComponentCount() - 1);

        panel1.revalidate();
        panel1.repaint();

        // Banner Panel
        panel2.setBounds(300, 80, 1090, 330);
        panel2.setBackground(new Color(16, 11, 43));
        panel2.setLayout(null);
        framep.add(panel2);

        ImageIcon bannerIcon = new ImageIcon("C:\\Users\\Rance\\Downloads\\Gemini_Generated_Image_teb3hateb3hateb3.jpg");

        Image bannerImage = bannerIcon.getImage().getScaledInstance(1090, 330, Image.SCALE_SMOOTH);

        JLabel bannerLabel = new JLabel(new ImageIcon(bannerImage));
        bannerLabel.setBounds(0, 0, 1090, 330);
        panel2.add(bannerLabel);

        // Book Now Buttons
        book1.setBounds(330, 730, 150, 40);
        book1.setForeground(Color.WHITE);
        book1.setBackground(darkpurple);
        framep.add(book1);

        book2.setBounds(550, 730, 150, 40);
        book2.setForeground(Color.WHITE);
        book2.setBackground(darkpurple);
        framep.add(book2);

        book3.setBounds(765, 730, 150, 40);
        book3.setForeground(Color.WHITE);
        book3.setBackground(darkpurple);
        framep.add(book3);

        book4.setBounds(985, 730, 150, 40);
        book4.setForeground(Color.WHITE);
        book4.setBackground(darkpurple);
        framep.add(book4);

        book5.setBounds(1210, 730, 150, 40);
        book5.setForeground(Color.WHITE);
        book5.setBackground(darkpurple);
        framep.add(book5);

        // Popular Now Label
        lbl1.setFont(new Font("Arial", Font.BOLD, 20));
        lbl1.setBounds(320, 435, 200, 30);
        lbl1.setForeground(Color.WHITE);
        framep.add(lbl1);

        // Movie Posters
        String[] images = {
            "C:\\Users\\Rance\\Downloads\\836569823_1416877663884935_3107284890057034110_n.jpg",
            "C:\\Users\\Rance\\Downloads\\838892416_1642879143860002_7264880252662044330_n.jpg",
            "C:\\Users\\Rance\\Downloads\\834977550_1804670110874526_1324191464776511631_n.jpg",
            "C:\\Users\\Rance\\Downloads\\837836773_1767772461140470_5644633886512425935_n.jpg",
            "C:\\Users\\Rance\\Downloads\\842055205_2033896403956288_3559486743135215157_n.jpg"
        };

        for (int i = 0; i < pbook.length; i++) {

            pbook[i] = new JPanel();
            pbook[i].setLayout(null);
            pbook[i].setBackground(new Color(16, 11, 43));
            pbook[i].setBounds(300 + (i * 220), 465, 200, 420);

            panel3[i] = new JPanel();
            panel3[i].setLayout(null);
            panel3[i].setBackground(Color.WHITE);
            panel3[i].setBounds(15, 10, 170, 250);

            ImageIcon icon = new ImageIcon(images[i]);

            Image scaled = icon.getImage().getScaledInstance(170, 250, Image.SCALE_SMOOTH);

            JLabel poster = new JLabel(new ImageIcon(scaled));
            poster.setBounds(0, 0, 170, 250);

            panel3[i].add(poster);
            pbook[i].add(panel3[i]);

            framep.add(pbook[i]);
        }

        // Button Listeners
        book1.addActionListener(this);
        book2.addActionListener(this);
        book3.addActionListener(this);
        book4.addActionListener(this);
        book5.addActionListener(this);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        Object source = e.getSource();
        if (source == moviesMenu) {
            new ViewAllMovies().setVisible(true);
            dispose();
        } else if (source == logout || source == logoutPanel) {
            dispose();
            new LandingPage().setVisible(true);

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        Object source = e.getSource();

        // Dashboard Hover
        if (source == dashboardMenu || source == dashPanel) {
            dashPanel.setBackground(new Color(90, 45, 145));
            dashboardMenu.setForeground(Color.WHITE);
        } // Logout Hover
        else if (source == logout || source == logoutPanel) {
            logoutPanel.setBackground(new Color(75, 35, 120));
            logout.setForeground(Color.WHITE);
        } // Other Labels Hover
        else if (source instanceof JLabel) {
            JLabel label = (JLabel) source;
            label.setForeground(activeColor);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        Object source = e.getSource();

        // Dashboard Default
        if (source == dashboardMenu || source == dashPanel) {
            dashPanel.setBackground(dashBg);
            dashboardMenu.setForeground(new Color(200, 170, 255));
        } // Logout Default
        else if (source == logout || source == logoutPanel) {
            logoutPanel.setBackground(logoutBg);
            logout.setForeground(logoutColor);
        } // Other Labels Default
        else if (source instanceof JLabel label) {

            if (label != dashboardMenu && label != logout) {
                label.setForeground(textColor);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == book1) {

            new BookSeat(
                    "C:\\Users\\Rance\\Downloads\\Gemini_Generated_Image_4l956f4l956f4l95.jpg",
                    "Demon Slayer: Infinity Castle Movie",
                    "2025 ‧ Action / Animation | 2h 35m",
                    "9.5 / 10",
                    "Tanjiro and the Demon Slayer Corps enter the Infinity Castle to face Muzan Kibutsuji and his powerful demons. Together with the Hashira, they fight to protect humanity and end the threat of demons.",
                    250
            );
            setVisible(false);

        } else if (e.getSource() == book2) {

            new BookSeat(
                    "C:\\Users\\Rance\\Downloads\\836806461_1135594892259624_3995803387842524777_n.jpg",
                    "Avengers: Endgame Encore",
                    "2019 ‧ Action / Sci-fi | 3h 5m",
                    "8.4 / 10",
                    "The Avengers reunite for one final mission to reverse the damage caused by Thanos and bring back the people who disappeared. They must work together, face great sacrifices, and fight to save the universe.",
                    300
            );
            

        } else if (e.getSource() == book3) {

            new BookSeat(
                    "C:\\Users\\Rance\\Downloads\\839999399_1070276262489290_1135975094415710158_n.jpg",
                    "Other Mommy",
                    "2026 ‧ Horror / Thriller | 1h 33m",
                    "5.9 / 10",
                    "A mysterious creature takes the appearance of Bela's mother and begins haunting her home. Bela must face the frightening truth behind the entity before it threatens her family.",
                    200
            );
            
            setVisible(false);
                    

        } else if (e.getSource() == book4) {

            new BookSeat(
                    "C:\\Users\\Rance\\Downloads\\840133069_3464518343706012_3946572089338619474_n.jpg",
                    "Verity",
                    "2026 ‧ Mystery / Thriller / Drama | 1h 54m",
                    "8.7 / 10",
                    "Lowen Ashleigh accepts a job to finish a successful author's book series, only to discover disturbing secrets hidden inside the author's home.",
                    280
            );
            

        } else if (e.getSource() == book5) {

            new BookSeat(
                    "C:\\Users\\Rance\\Downloads\\841139221_2224286718140401_4716361561376387909_n.jpg",
                    "Always Yours,Never Mine",
                    "2026 ‧ Romance / Drama | 1h 55m",
                    "9.2 / 10",
                    "A heartfelt love story about fate, missed chances, and the courage to choose between the love they dream of and the lives they must live.",
                    350
            );
            
            
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DashBoard();
        });
    }
}
