package FinalProject;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ManageScedule extends JFrame implements MouseListener {

    JPanel framep = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel hpanel = new JPanel();

    JPanel formPanel = new JPanel();
    JPanel tablePanel = new JPanel();
    JPanel schedulePanel = new JPanel(null);
    JPanel logoutPanel = new JPanel(null);

    JDateChooser sdate = new JDateChooser();
    JDateChooser edate = new JDateChooser();

    JLabel atxt = new JLabel("Select Movie");
    JLabel btxt = new JLabel("Start Date");
    JLabel ctxt = new JLabel("End Date");
    JLabel dtxt = new JLabel("Available Time");

    JLabel title = new JLabel("Admin Dashboard");
    JLabel title2 = new JLabel("Movie Schedule Management");

    JLabel dash = new JLabel("Dashboard");
    JLabel mscedule = new JLabel("Manage Schedule");
    JLabel maccount = new JLabel("Manage Accounts");
    JLabel vbooking = new JLabel("View Bookings");
    JLabel logout = new JLabel("    ⏻     Logout");

    JCheckBox t1 = new JCheckBox("10:00 AM");
    JCheckBox t2 = new JCheckBox("1:00 PM");
    JCheckBox t3 = new JCheckBox("2:30 PM");
    JCheckBox t4 = new JCheckBox("4:00 PM");
    JCheckBox t5 = new JCheckBox("6:00 PM");
    JCheckBox t6 = new JCheckBox("7:30 PM");
    JCheckBox t7 = new JCheckBox("9:00 PM");
    JCheckBox t8 = new JCheckBox("10:30 PM");

    JComboBox<String> ajb;
    JButton saveButton = new JButton("SAVE SCHEDULE");

    DefaultTableModel tableModel;

    final String FILE_NAME = "movie_schedules.txt";
    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    Color textColor = Color.WHITE;
    Color activeColor = new Color(169, 112, 255);
    Color dashBg = new Color(65, 35, 105);
    Color logoutColor = new Color(190, 150, 255);
    Color logoutBg = new Color(35, 24, 58);

    Font regularFont = new Font("Arial", Font.PLAIN, 16);
    Font boldFont = new Font("Arial", Font.BOLD, 18);

    
    String loggedInRole = "ADMIN";

    public ManageScedule() {
        this("ADMIN");
    }

    
    ManageScedule(String loggedInRole) {
        this.loggedInRole = loggedInRole;

        setTitle("CineVibe - Manage Schedule (" + loggedInRole + ")");
        setLayout(null);
        setSize(1500, 850);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // MAIN PANEL
        framep.setBounds(0, 0, 1500, 850);
        framep.setLayout(null);
        framep.setBackground(new Color(8, 6, 25));
        add(framep);

        // HEADER
        hpanel.setBackground(new Color(13, 9, 36));
        hpanel.setBounds(0, 0, 1500, 80);
        hpanel.setLayout(null);
        framep.add(hpanel);

        title.setFont(new Font("Arial", Font.BOLD, 34));
        title.setForeground(Color.WHITE);
        title.setBounds(50, 20, 400, 40);
        hpanel.add(title);

        // PAGE TITLE
        title2.setFont(new Font("Arial", Font.BOLD, 26));
        title2.setForeground(Color.WHITE);
        title2.setBounds(290, 100, 600, 35);
        framep.add(title2);

        // FORM PANEL
        formPanel.setBounds(290, 150, 1150, 280);
        formPanel.setLayout(null);
        formPanel.setBackground(new Color(23, 19, 35));
        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        new javax.swing.border.LineBorder(
                                new Color(75, 55, 105), 1, true
                        ),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                )
        );
        framep.add(formPanel);

        // TABLE PANEL
        tablePanel.setBounds(290, 450, 1150, 340);
        tablePanel.setLayout(null);
        tablePanel.setBackground(new Color(23, 19, 35));
        tablePanel.setBorder(
                BorderFactory.createCompoundBorder(
                        new javax.swing.border.LineBorder(
                                new Color(75, 55, 105), 1, true
                        ),BorderFactory.createEmptyBorder(10, 10, 10, 10)
)
        );
        framep.add(tablePanel);

        // SIDEBAR
        panel1.setBackground(new Color(13, 9, 36));
        panel1.setBounds(0, 0, 250, 850);
        panel1.setLayout(null);
        framep.add(panel1);

        // Dashboard Normal Label in Sidebar
        dash.setForeground(textColor);
        dash.setFont(regularFont);
        dash.setBounds(30, 110, 190, 40);
        dash.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel1.add(dash);

        // Manage Schedule Violet Panel 
        schedulePanel.setBounds(15, 165, 220, 50);
        schedulePanel.setBackground(dashBg);
        schedulePanel.setLayout(null);
        schedulePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel1.add(schedulePanel);

        mscedule.setForeground(new Color(200, 170, 255));
        mscedule.setFont(boldFont);
        mscedule.setBounds(15, 5, 190, 40);
        mscedule.setCursor(new Cursor(Cursor.HAND_CURSOR));
        schedulePanel.add(mscedule);

        // MANAGE ACCOUNTS
        maccount.setForeground(textColor);
        maccount.setFont(regularFont);
        maccount.setBounds(30, 230, 190, 40);
        maccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel1.add(maccount);

        // VIEW BOOKINGS
        vbooking.setForeground(textColor);
        vbooking.setFont(regularFont);
        vbooking.setBounds(30, 290, 190, 40);
        vbooking.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel1.add(vbooking);

        // LOGOUT PANEL
        logoutPanel.setBounds(20, 700, 210, 48);
        logoutPanel.setBackground(logoutBg);
        logoutPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutPanel.setBorder(BorderFactory.createLineBorder(new Color(121, 37, 245), 1));
        panel1.add(logoutPanel);

        // LOGOUT LABEL
        logout.setFont(new Font("Arial", Font.BOLD, 16));
        logout.setForeground(logoutColor);
        logout.setBounds(15, 5, 180, 38);
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutPanel.add(logout);

        // SIDEBAR BACKGROUND IMAGE
        ImageIcon ico = new ImageIcon("C:\\Users\\Rance\\Downloads\\Gemini_Generated_Image_6wvo1f6wvo1f6wvo.jpg");
        if (ico.getIconWidth() > 0) {
            Image scal = ico.getImage().getScaledInstance(250, 850, Image.SCALE_SMOOTH);
            JLabel sidebar = new JLabel(new ImageIcon(scal));
            sidebar.setBounds(0, 0, 250, 850);
            panel1.add(sidebar);
            panel1.setComponentZOrder(sidebar, panel1.getComponentCount() - 1);
        }

        // CLICK LISTENERS
        dash.addMouseListener(this);
        mscedule.addMouseListener(this);
        schedulePanel.addMouseListener(this);
        maccount.addMouseListener(this);
        vbooking.addMouseListener(this);
        logout.addMouseListener(this);
        logoutPanel.addMouseListener(this);

        // SELECT MOVIE LABEL
        atxt.setFont(new Font("Arial", Font.BOLD, 14));
        atxt.setForeground(new Color(169, 112, 255));
        atxt.setBounds(25, 20, 250, 25);
        formPanel.add(atxt);

        // MOVIE DROPDOWN
        String[] list = {
            "Select",
            "Demon Slayer: Infinity Castle Movie",
            "Avengers: Endgame Encore",
            "Other Mommy",
            "Verity",
            "Always Yours, Never Mine",
            "Chainsaw Man: Reze Arc",
            "Suzume",
            "Your Name",
            "Jujutsu Kaisen 0",
            "Spider-Man: No Way Home",
            "Doctor Strange in the Multiverse of Madness",
            "Interstellar",
            "Dune: Part Two",
            "Five Nights at Freddy's",
            "The Conjuring",
            "Insidious",
            "Smile",
            "A Quiet Place",
            "The Notebook",
            "Titanic"
        };

        ajb = new JComboBox<>(list);
        ajb.setFont(new Font("Arial", Font.PLAIN, 15));
        ajb.setForeground(Color.WHITE);
        ajb.setBackground(new Color(15, 12, 28));
        ajb.setBounds(25, 50, 550, 38);
        ajb.setBorder(BorderFactory.createLineBorder(new Color(75, 55, 105), 1));
        formPanel.add(ajb);

        // START DATE
        btxt.setFont(new Font("Arial", Font.BOLD, 14));
        btxt.setForeground(new Color(169, 112, 255));
        btxt.setBounds(25, 105, 200, 25);
        formPanel.add(btxt);

        sdate.setBounds(25, 135, 260, 38);
        sdate.setDateFormatString("yyyy-MM-dd");
        formPanel.add(sdate);

        // END DATE
        ctxt.setFont(new Font("Arial", Font.BOLD, 14));
        ctxt.setForeground(new Color(169, 112, 255));
        ctxt.setBounds(315, 105, 200, 25);
        formPanel.add(ctxt);

        edate.setBounds(315, 135, 260, 38);
        edate.setDateFormatString("yyyy-MM-dd");
        formPanel.add(edate);

        // AVAILABLE TIME
        dtxt.setFont(new Font("Arial", Font.BOLD, 14));
        dtxt.setForeground(new Color(169, 112, 255));
        dtxt.setBounds(620, 20, 250, 25);
        formPanel.add(dtxt);

        JCheckBox[] times = {
            t1, t2, t3, t4, t5, t6, t7, t8
        };

        int x = 620;
        int y = 50;

        for (int i = 0; i < times.length; i++) {
            times[i].setBounds(x, y, 230, 30);
            times[i].setFont(new Font("Arial", Font.PLAIN, 14));
            times[i].setForeground(Color.WHITE);
            times[i].setBackground(new Color(23, 19, 35));
            times[i].setFocusPainted(false);

            formPanel.add(times[i]);

            y += 35;

            if (i == 3) {
                x = 870;
                y = 50;
            }
        }

        // SAVE BUTTON
        saveButton.setBounds(25, 205, 200, 40);
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setForeground(Color.WHITE);
        saveButton.setBackground(new Color(121, 37, 245));
        saveButton.setFocusPainted(false);
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        formPanel.add(saveButton);
        
        

        saveButton.addActionListener(e -> saveSchedule());

        tableform();
        loadSchedules();
    }

    private void saveSchedule() {
        String movie = (String) ajb.getSelectedItem();
        Date startDate = sdate.getDate();
        Date endDate = edate.getDate();

        if (movie == null || movie.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a movie.");
            return;
        }

        if (startDate == null || endDate == null) {
            JOptionPane.showMessageDialog(this, "Please select both start and end dates.");
            return;
        }

        if (endDate.before(startDate)) {
            JOptionPane.showMessageDialog(this, "End date must not be earlier than start date.");
            return;
        }

        List<String> selectedTimes = new ArrayList<>();
        JCheckBox[] times = {t1, t2, t3, t4, t5, t6, t7, t8};

        for (JCheckBox time : times) {
            if (time.isSelected()) {
                selectedTimes.add(time.getText());
            }
        }

        if (selectedTimes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one available time.");
            return;
        }

        String start = dateFormat.format(startDate);
        String end = dateFormat.format(endDate);
        String availableTimes = String.join(",", selectedTimes);

        String newRecord = movie + "|" + start + "|" + end + "|" + availableTimes;

        List<String> records = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split("\\|", -1);
                    if (data.length > 0 && !data[0].equals(movie)) {
                        records.add(line);
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Unable to read schedule file.");
                return;
            }
        }

        records.add(newRecord);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String record : records) {
                writer.write(record);
                writer.newLine();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Unable to save schedule.");
            return;
        }

        loadSchedules();
        JOptionPane.showMessageDialog(this, "Movie schedule saved successfully!");

        for (JCheckBox time : times) {
            time.setSelected(false);
        }
    }

    public void tableform() {
        JLabel tableTitle = new JLabel("Current Movie Schedules");
        tableTitle.setFont(new Font("Arial", Font.BOLD, 18));
        tableTitle.setForeground(Color.WHITE);
        tableTitle.setBounds(20, 10, 400, 25);
        tablePanel.add(tableTitle);

        String[] columns = {
            "Movie Title",
            "Start Date",
            "End Date",
            "Available Time"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable scheduleTable = new JTable(tableModel);

        scheduleTable.setRowHeight(32);
        scheduleTable.setFont(new Font("Arial", Font.PLAIN, 13));
        scheduleTable.setBackground(new Color(23, 19, 35));
        scheduleTable.setForeground(Color.WHITE);
        scheduleTable.setSelectionBackground(new Color(65, 35, 105));
        scheduleTable.setSelectionForeground(Color.WHITE);
        scheduleTable.setGridColor(new Color(75, 55, 105));

        scheduleTable.getTableHeader().setBackground(new Color(65, 35, 105));
        scheduleTable.getTableHeader().setForeground(Color.WHITE);
        scheduleTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        scheduleTable.getTableHeader().setPreferredSize(new Dimension(0, 32));
        scheduleTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(scheduleTable);
        scrollPane.setBounds(20, 45, 1110, 275);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(75, 55, 105), 1));
        scrollPane.getViewport().setBackground(new Color(23, 19, 35));

        tablePanel.add(scrollPane);
    }

    private void loadSchedules() {
        tableModel.setRowCount(0);
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|", -1);
                if (data.length == 4) {
                    tableModel.addRow(data);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Unable to load movie schedules.");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();

        if (source == dash) {
            new AdminDashBoard(loggedInRole).setVisible(true); 
            dispose();
        }
        else if (source == maccount) {
            new ManageAccount(loggedInRole).setVisible(true); 
            dispose();
        } else if (source == vbooking) {
            new ViewBooking(loggedInRole).setVisible(true); 
            dispose();
        } else if (source == logout || source == logoutPanel) {
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to log out?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION,
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
        if (source == dash) {
            dash.setForeground(activeColor);
        } else if (source == logout || source == logoutPanel) {
            logoutPanel.setBackground(new Color(75, 35, 120));
            logout.setForeground(Color.WHITE);
        } else if (source instanceof JLabel) {
            JLabel label = (JLabel) source;
            label.setForeground(activeColor);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object source = e.getSource();

        if (source == dash) {
            dash.setForeground(textColor);
        } else if (source == logout || source == logoutPanel) {
            logoutPanel.setBackground(logoutBg);
            logout.setForeground(logoutColor);
        } else if (source instanceof JLabel label) {
            if (label != mscedule && label != logout) {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()
                -> new ManageScedule("SUPERADMIN").setVisible(true)
        );
    }
}
