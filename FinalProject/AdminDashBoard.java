package FinalProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class AdminDashBoard extends JFrame implements MouseListener {

    JPanel framep = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel hpanel = new JPanel();

    JPanel dashPanel = new JPanel();
    JPanel logoutPanel = new JPanel(null);

    JLabel title = new JLabel("Admin Dashboard");

    JLabel dash = new JLabel("Dashboard");
    JLabel mscedule = new JLabel("Manage Schedule");
    JLabel maccount = new JLabel("Manage Accounts");
    JLabel vbooking = new JLabel("View Bookings");
    JLabel logout = new JLabel("    ⏻     Logout");

    Color textColor = Color.WHITE;
    Color activeColor = new Color(169, 112, 255);
    Color dashBg = new Color(65, 35, 105);
    Color logoutColor = new Color(190, 150, 255);
    Color logoutBg = new Color(35, 24, 58);
    Color cardBg = new Color(23, 19, 35);
    Color cardBorder = new Color(75, 55, 105);

    Font regularFont = new Font("Arial", Font.PLAIN, 16);
    Font boldFont = new Font("Arial", Font.BOLD, 18);

    String loggedInRole = "SUPERADMIN";

    public AdminDashBoard() {
        this("SUPERADMIN");
    }

    public AdminDashBoard(String loggedInRole) {
        this.loggedInRole = loggedInRole;

        setTitle("CineVibe - Admin Dashboard (" + loggedInRole + ")");
        setLayout(null);
        setSize(1500, 850);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Panel
        framep.setBounds(0, 0, 1500, 850);
        framep.setLayout(null);
        framep.setBackground(new Color(8, 6, 25));
        add(framep);

        // Header
        hpanel.setBackground(new Color(13, 9, 36));
        hpanel.setBounds(0, 0, 1500, 80);
        hpanel.setLayout(null);
        framep.add(hpanel);

        title.setFont(new Font("Arial", Font.BOLD, 34));
        title.setForeground(Color.WHITE);
        title.setBounds(50, 20, 400, 40);
        hpanel.add(title);

        // Header Admin Greeting
        JLabel adminUser = new JLabel("Welcome, " + loggedInRole + "  👤");
        adminUser.setFont(new Font("Arial", Font.BOLD, 16));
        adminUser.setForeground(new Color(200, 170, 255));
        adminUser.setBounds(1200, 25, 260, 30);
        hpanel.add(adminUser);

        // Sidebar
        panel1.setBackground(new Color(13, 9, 36));
        panel1.setBounds(0, 0, 250, 850);
        panel1.setLayout(null);
        framep.add(panel1);

        // Dashboard Violet Panel
        dashPanel.setBounds(15, 105, 220, 50);
        dashPanel.setBackground(dashBg);
        dashPanel.setLayout(null);
        dashPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel1.add(dashPanel);

        // Dashboard Label
        dash.setForeground(new Color(200, 170, 255));
        dash.setFont(boldFont);
        dash.setBounds(15, 5, 190, 40);
        dash.setCursor(new Cursor(Cursor.HAND_CURSOR));
        dashPanel.add(dash);

        // Manage Schedule
        mscedule.setForeground(textColor);
        mscedule.setFont(regularFont);
        mscedule.setBounds(30, 170, 190, 40);
        panel1.add(mscedule);

        // Manage Accounts
        maccount.setForeground(textColor);
        maccount.setFont(regularFont);
        maccount.setBounds(30, 230, 190, 40);
        panel1.add(maccount);

        // View Bookings
        vbooking.setForeground(textColor);
        vbooking.setFont(regularFont);
        vbooking.setBounds(30, 290, 190, 40);
        panel1.add(vbooking);

        // Logout Panel
        logoutPanel.setBounds(20, 700, 210, 48);
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

        // Click Listeners
        dash.addMouseListener(this);
        dashPanel.addMouseListener(this);
        mscedule.addMouseListener(this);
        maccount.addMouseListener(this);
        vbooking.addMouseListener(this);
        logout.addMouseListener(this);
        logoutPanel.addMouseListener(this);

        // Pointer Cursor
        mscedule.setCursor(new Cursor(Cursor.HAND_CURSOR));
        maccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vbooking.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Sidebar Background Image
        ImageIcon ico = new ImageIcon("C:\\Users\\Rance\\Downloads\\Gemini_Generated_Image_6wvo1f6wvo1f6wvo.jpg");
        if (ico.getIconWidth() > 0) {
            Image scal = ico.getImage().getScaledInstance(250, 850, Image.SCALE_SMOOTH);
            JLabel sidebar = new JLabel(new ImageIcon(scal));
            sidebar.setBounds(0, 0, 250, 850);
            panel1.add(sidebar);
            panel1.setComponentZOrder(sidebar, panel1.getComponentCount() - 1);
        }

        // Top Stat Cards
        createCard("TOTAL BOOKINGS", "1,248", 290, 110, 265, 110);
        createCard("ACTIVE MOVIES", "12", 580, 110, 265, 110);
        createCard("REGISTERED USERS", "350", 870, 110, 265, 110);
        createCard("TODAY'S REVENUE", "₱ 45,200", 1160, 110, 270, 110);

        // Statistics Chart Panel sa Gitna
        JPanel chartPanel = new JPanel(null);
        chartPanel.setBounds(290, 245, 1140, 520);
        chartPanel.setBackground(cardBg);
        chartPanel.setBorder(BorderFactory.createLineBorder(cardBorder, 1));
        framep.add(chartPanel);

        JLabel chartTitle = new JLabel("TICKET SALES & BOOKINGS ANALYTICS");
        chartTitle.setFont(new Font("Arial", Font.BOLD, 22));
        chartTitle.setForeground(Color.WHITE);
        chartTitle.setBounds(30, 20, 500, 30);
        chartPanel.add(chartTitle);

        JLabel chartSub = new JLabel("Monthly booking overview and revenue performance graph");
        chartSub.setFont(new Font("Arial", Font.PLAIN, 14));
        chartSub.setForeground(new Color(169, 112, 255));
        chartSub.setBounds(30, 50, 500, 25);
        chartPanel.add(chartSub);

        ImageIcon contentImg = new ImageIcon("C:\\Users\\Rance\\OneDrive\\Pictures\\Screenshots\\Screenshot 2026-10-20 202748.png");

        Image scaledContent = contentImg.getImage().getScaledInstance(1080, 410, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledContent));
        imageLabel.setBounds(30, 85, 1080, 410);
        imageLabel.setBorder(BorderFactory.createLineBorder(cardBorder, 1));
        chartPanel.add(imageLabel);

        panel1.revalidate();
        panel1.repaint();
    }

    private void createCard(String cardTitle, String cardValue, int x, int y, int width, int height) {
        JPanel card = new JPanel(null);
        card.setBounds(x, y, width, height);
        card.setBackground(cardBg);
        card.setBorder(BorderFactory.createLineBorder(cardBorder, 1));

        JLabel lblTitle = new JLabel( cardTitle);
        lblTitle.setBounds(18, 15, width - 30, 20);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 12));
        lblTitle.setForeground(new Color(169, 112, 255));
        card.add(lblTitle);

        JLabel lblVal = new JLabel(cardValue);
        lblVal.setBounds(18, 42, width - 30, 45);
        lblVal.setFont(new Font("Arial", Font.BOLD, 26));
        lblVal.setForeground(Color.WHITE);
        card.add(lblVal);

        framep.add(card);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();

        if (source == mscedule) {
            new ManageScedule().setVisible(true);
            dispose();
        } else if (source == maccount) {
            new ManageAccount(loggedInRole).setVisible(true);
            dispose();

        } else if (source == vbooking) {
            new ViewBooking().setVisible(true);
            dispose();
        } else if (source == logout || source == logoutPanel) {
            int choice = JOptionPane.showConfirmDialog(
                    this, "Are you sure you want to log out?",
                    "Confirm Logout", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (choice == JOptionPane.YES_OPTION) {
                dispose();
                new Login().setVisible(true);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object source = e.getSource();
        if (source == logout || source == logoutPanel) {
            logoutPanel.setBackground(new Color(75, 35, 120));
            logout.setForeground(Color.WHITE);
        } else if (source instanceof JLabel) {
            ((JLabel) source).setForeground(activeColor);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object source = e.getSource();
        if (source == logout || source == logoutPanel) {
            logoutPanel.setBackground(logoutBg);
            logout.setForeground(logoutColor);
        } else if (source instanceof JLabel label) {
            if (label != dash && label != logout) {
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
}
