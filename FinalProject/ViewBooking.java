package FinalProject;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

public class ViewBooking extends JFrame implements MouseListener {

    private final String CUSTOMER_FILE = "customer_bookings.txt";

    JPanel framep = new JPanel(null);
    JPanel panel1 = new JPanel(null);
    JPanel hpanel = new JPanel(null);
    JPanel bookingPanel = new JPanel(null);
    JPanel logoutPanel = new JPanel(null);

    JLabel title = new JLabel("Admin Dashboard");
    JLabel pageTitle = new JLabel("View Booking Management");
    JLabel dash = new JLabel("Dashboard");
    JLabel mscedule = new JLabel("Manage Schedule");
    JLabel maccount = new JLabel("Manage Accounts");
    JLabel vbooking = new JLabel("View Bookings");
    JLabel logout = new JLabel("    ⏻     Logout");
    JLabel totalBookings = new JLabel("Total Tickets: 0");

    JTextField searchField = new JTextField();
    JComboBox<String> sortDropdown;

    JTable bookingTable;
    DefaultTableModel tableModel;
    TableRowSorter<DefaultTableModel> sorter;

    // Dashboard Colors
    private final Color windowBg = new Color(8, 6, 25);
    private final Color headerBg = new Color(13, 9, 36);
    private final Color sidebarBg = new Color(13, 9, 36);
    private final Color primaryPurple = new Color(121, 37, 245);
    private final Color activeColor = new Color(169, 112, 255);
    private final Color dashBg = new Color(65, 35, 105);
    private final Color logoutBg = new Color(35, 24, 58);
    private final Color logoutHoverBg = new Color(75, 35, 120);
    private final Color logoutColor = new Color(190, 150, 255);
    private final Color textColor = Color.WHITE;

    // Table and Input Colors
    private final Color tableBg = new Color(23, 19, 35);
    private final Color tableHeaderBg = new Color(55, 29, 88);
    private final Color tableBorder = new Color(75, 48, 105);

    Font regularFont = new Font("Arial", Font.PLAIN, 16);
    Font boldFont = new Font("Arial", Font.BOLD, 18);

    String loggedInRole = "ADMIN";

    public ViewBooking() {
        this("ADMIN");
    }

    public ViewBooking(String loggedInRole) {
        this.loggedInRole = loggedInRole;

        setTitle("CineVibe - View Bookings (" + loggedInRole + ")");
        setLayout(null);
        setSize(1500, 850);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Frame
        framep.setBounds(0, 0, 1500, 850);
        framep.setBackground(windowBg);
        add(framep);

        // Header
        hpanel.setBounds(0, 0, 1500, 80);
        hpanel.setBackground(headerBg);
        hpanel.setLayout(null);
        framep.add(hpanel);

        title.setBounds(50, 20, 500, 40);
        title.setFont(new Font("Arial", Font.BOLD, 34));
        title.setForeground(Color.WHITE);
        hpanel.add(title);

        // Sidebar
        panel1.setBounds(0, 0, 250, 850);
        panel1.setBackground(sidebarBg);
        panel1.setLayout(null);
        framep.add(panel1);

        // Dashboard
        dash.setBounds(30, 110, 190, 40);
        dash.setFont(regularFont);
        dash.setForeground(textColor);
        dash.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel1.add(dash);

        // Manage Schedule
        mscedule.setBounds(30, 170, 190, 40);
        mscedule.setFont(regularFont);
        mscedule.setForeground(textColor);
        mscedule.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel1.add(mscedule);

        // Manage Accounts
        maccount.setBounds(30, 230, 190, 40);
        maccount.setFont(regularFont);
        maccount.setForeground(textColor);
        maccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel1.add(maccount);

        // Active View Bookings
        bookingPanel.setBounds(15, 285, 220, 50);
        bookingPanel.setBackground(dashBg);
        bookingPanel.setLayout(null);
        bookingPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel1.add(bookingPanel);

        vbooking.setBounds(15, 5, 190, 40);
        vbooking.setFont(boldFont);
        vbooking.setForeground(new Color(200, 170, 255));
        vbooking.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bookingPanel.add(vbooking);

        // Logout Panel
        logoutPanel.setBounds(20, 700, 210, 48);
        logoutPanel.setBackground(logoutBg);
        logoutPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutPanel.setBorder(BorderFactory.createLineBorder(primaryPurple, 1));
        panel1.add(logoutPanel);

        logout.setBounds(15, 5, 180, 38);
        logout.setFont(new Font("Arial", Font.BOLD, 16));
        logout.setForeground(logoutColor);
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutPanel.add(logout);

        // Sidebar Background Image
        ImageIcon ico = new ImageIcon("C:\\Users\\Rance\\Downloads\\Gemini_Generated_Image_6wvo1f6wvo1f6wvo.jpg");

        if (ico.getIconWidth() > 0) {
            Image scaled = ico.getImage().getScaledInstance(250, 850, Image.SCALE_SMOOTH);

            JLabel sidebar = new JLabel(new ImageIcon(scaled));
            sidebar.setBounds(0, 0, 250, 850);
            panel1.add(sidebar);

            panel1.setComponentZOrder(sidebar, panel1.getComponentCount() - 1);
        }

        // Page Title
        pageTitle.setBounds(280, 110, 850, 50);
        pageTitle.setFont(new Font("Arial", Font.BOLD, 30));
        pageTitle.setForeground(Color.WHITE);
        framep.add(pageTitle);

        // Total Tickets
        totalBookings.setBounds(1050, 155, 400, 25);
        totalBookings.setFont(new Font("Arial", Font.BOLD, 14));
        totalBookings.setForeground(activeColor);
        totalBookings.setHorizontalAlignment(SwingConstants.RIGHT);
        framep.add(totalBookings);

        // Search Field
        searchField.setBounds(280, 175, 570, 42);
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        searchField.setBackground(tableBg);
        searchField.setForeground(Color.WHITE);
        searchField.setCaretColor(Color.WHITE);
        searchField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(tableBorder, 1),
                        BorderFactory.createEmptyBorder(5, 12, 5, 12)
                )
        );
        framep.add(searchField);

        // Sort Dropdown
        String[] sortOptions = {
            "Sort By: Default",
            "Movie A-Z",
            "Date: Oldest First",
            "Date: Newest First",
            "Time A-Z",
            "Seat A-Z",
            "Customer Name A-Z",
            "Contact A-Z",
            "Email A-Z",
            "Age: Low to High",
            "Age: High to Low",
            "Gender A-Z",
            "Customer Type A-Z",
            "Price: Low to High",
            "Price: High to Low",
            "Discount: Low to High",
            "Discount: High to Low"
        };

        sortDropdown = new JComboBox<>(sortOptions);
        sortDropdown.setBounds(870, 175, 270, 42);
        sortDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        sortDropdown.setBackground(tableBg);
        sortDropdown.setForeground(Color.WHITE);
        sortDropdown.setFocusable(false);
        sortDropdown.setBorder(BorderFactory.createLineBorder(tableBorder, 1));
        framep.add(sortDropdown);

        // Table Columns
        String[] columns = {
            "Movie", "Date", "Time", "Seat",
            "Customer Name", "Contact", "Email",
            "Age", "Gender", "Customer Type",
            "Final Price", "Discount"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        bookingTable = new JTable(tableModel);
        bookingTable.setFont(new Font("Arial", Font.PLAIN, 13));
        bookingTable.setRowHeight(32);
        bookingTable.setBackground(tableBg);
        bookingTable.setForeground(Color.WHITE);
        bookingTable.setGridColor(tableBorder);
        bookingTable.setSelectionBackground(new Color(90, 45, 145));
        bookingTable.setSelectionForeground(Color.WHITE);
        bookingTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        bookingTable.getTableHeader().setBackground(tableHeaderBg);
        bookingTable.getTableHeader().setForeground(Color.WHITE);
        bookingTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 13)
        );
        bookingTable.getTableHeader().setReorderingAllowed(false);

        int[] widths = {
            220, 100, 100, 65, 180, 130,
            200, 55, 100, 130, 100, 100
        };

        for (int i = 0; i < widths.length; i++) {
            bookingTable.getColumnModel()
                    .getColumn(i).setPreferredWidth(widths[i]);
        }

        // Sorting
        sorter = new TableRowSorter<>(tableModel);
        bookingTable.setRowSorter(sorter);

        sorter.setComparator(7, (a, b)
                -> Double.compare(parseNumber(a), parseNumber(b))
        );

        sorter.setComparator(10, (a, b)
                -> Double.compare(parseNumber(a), parseNumber(b))
        );

        sorter.setComparator(11, (a, b)
                -> Double.compare(parseNumber(a), parseNumber(b))
        );

        // Search Filter
        searchField.getDocument().addDocumentListener(new DocumentListener() {

            private void search() {
                String text = searchField.getText().trim();

                if (text.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(
                            RowFilter.regexFilter(
                                    "(?i)" + Pattern.quote(text)
                            )
                    );
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search();
            }
        }
        );

        // Sort Action
        sortDropdown.addActionListener(e -> sortBookings());

        // Table Scroll Pane
        JScrollPane scroll = new JScrollPane(bookingTable);
        scroll.setBounds(280, 235, 1170, 430);
        scroll.getViewport().setBackground(tableBg);
        scroll.setBorder(BorderFactory.createLineBorder(tableBorder, 1));
        framep.add(scroll);

        // Refresh Button
        JButton refreshButton = new JButton("REFRESH");
        refreshButton.setBounds(1320, 690, 130, 42);
        refreshButton.setFont(new Font("Arial", Font.BOLD, 13));
        refreshButton.setBackground(primaryPurple);
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.setBorderPainted(false);
        refreshButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        framep.add(refreshButton);

        refreshButton.addActionListener(e -> {
            loadBookings();
            searchField.setText("");
            sorter.setRowFilter(null);
            sortDropdown.setSelectedIndex(0);
        });

        // Navigation Listeners
        dash.addMouseListener(this);
        mscedule.addMouseListener(this);
        maccount.addMouseListener(this);
        vbooking.addMouseListener(this);
        bookingPanel.addMouseListener(this);
        logout.addMouseListener(this);
        logoutPanel.addMouseListener(this);

        loadBookings();
    }

    private double parseNumber(Object value) {
        if (value == null) {
            return 0;
        }

        try {
            String number = value.toString()
                    .replace("PHP", "")
                    .replace("₱", "")
                    .replace(",", "")
                    .trim();

            return Double.parseDouble(number);

        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void sortBookings() {
        int choice = sortDropdown.getSelectedIndex();

        switch (choice) {
            case 0:
                sorter.setSortKeys(null);
                break;
            case 1:
                sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(0, SortOrder.ASCENDING)));
                break;
            case 2:
                sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(1, SortOrder.ASCENDING)));
                break;
            case 3:
                sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(1, SortOrder.DESCENDING)));
                break;
            case 4:
                sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(2, SortOrder.ASCENDING)));
                break;
            case 5:
                sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(3, SortOrder.ASCENDING)));
                break;
            case 6:
                sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(4, SortOrder.ASCENDING)));
                break;
            case 7:
                sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(5, SortOrder.ASCENDING)));
                break;
            case 8:
                sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(6, SortOrder.ASCENDING)));
                break;
            case 9:
                sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(7, SortOrder.ASCENDING)));
                break;
            case 10:
                sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(7, SortOrder.DESCENDING)));
                break;
            case 11:
                sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(8, SortOrder.ASCENDING)));
                break;
            case 12:
                sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(9, SortOrder.ASCENDING)));
                break;
            case 13:
                sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(10, SortOrder.ASCENDING)));
                break;
            case 14:
                sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(10, SortOrder.DESCENDING)));
                break;
            case 15:
                sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(11, SortOrder.ASCENDING)));
                break;
            case 16:
                sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(11, SortOrder.DESCENDING)));
                break;
        }
    }

    private void loadBookings() {
        tableModel.setRowCount(0);

        File file = new File(CUSTOMER_FILE);

        if (!file.exists()) {
            totalBookings.setText("Total Tickets: 0");
            return;
        }

        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);

                if (data.length >= 14) {
                    String fullName = data[4] + " " + data[5];

                    Booking bookingObj = new Booking(
                        data[0], data[1], data[2], data[3],
                        fullName, data[6], data[7], data[8],
                        data[9], data[10], data[12], data[13]
                    );

                    tableModel.addRow(new Object[]{
                        bookingObj.getMovie(), 
                        bookingObj.getDate(), 
                        bookingObj.getTime(), 
                        bookingObj.getSeat(),
                        bookingObj.getCustomerName(), 
                        bookingObj.getContact(), 
                        bookingObj.getEmail(), 
                        bookingObj.getAge(),
                        bookingObj.getGender(), 
                        bookingObj.getCustomerType(), 
                        "PHP " + bookingObj.getFinalPrice(), 
                        "PHP " + bookingObj.getDiscount()
                    });

                    count++;
                }
            }

            totalBookings.setText("Total Tickets: " + count);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load bookings:\n" + e.getMessage(),
                    "File Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();

        if (source == dash) {
            new AdminDashBoard(loggedInRole).setVisible(true); 
            dispose();
        } else if (source == mscedule) {
            new ManageScedule(loggedInRole).setVisible(true); 
            dispose();
        }else if (source == maccount) {
            new ManageAccount(loggedInRole).setVisible(true); 
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

        if (source == logout || source == logoutPanel) {
            logoutPanel.setBackground(logoutHoverBg);
            logout.setForeground(Color.WHITE);
        } else if (source == dash || source == mscedule || source == maccount) {
            ((JLabel) source).setForeground(activeColor);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object source = e.getSource();

        if (source == logout || source == logoutPanel) {
            logoutPanel.setBackground(logoutBg);
            logout.setForeground(logoutColor);
        } else if (source == dash || source == mscedule || source == maccount) {
            ((JLabel) source).setForeground(textColor);
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
                -> new ViewBooking("SUPERADMIN").setVisible(true)
        );
    }
}
