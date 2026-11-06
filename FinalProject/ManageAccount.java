package FinalProject;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ManageAccount extends JFrame implements MouseListener {

    private final String USERS_FILE = "users.txt";

    JPanel framep = new JPanel(null);
    JPanel panel1 = new JPanel(null);
    JPanel hpanel = new JPanel(null);
    JPanel accountPanel = new JPanel(null);
    JPanel logoutPanel = new JPanel(null);

    JLabel title = new JLabel("Admin Dashboard");
    JLabel pageTitle = new JLabel("Account Management");
    JLabel dash = new JLabel("Dashboard");
    JLabel mscedule = new JLabel("Manage Schedule");
    JLabel maccount = new JLabel("Manage Accounts");
    JLabel vbooking = new JLabel("View Bookings");
    JLabel logout = new JLabel("    ⏻      Logout");
    JLabel totalAccounts = new JLabel("Total Accounts: 0");

    JTextField searchField = new JTextField();
    JComboBox<String> statusFilter = new JComboBox<>(new String[]{"All Accounts", "ACTIVE", "DISABLED"});
    
    JButton btnAdd = new JButton("ADD ACCOUNT");
    JButton btnEdit = new JButton("EDIT ACCOUNT");
    JButton btnDelete = new JButton("DELETE ACCOUNT");
    JButton btnEnable = new JButton("ENABLE");
    JButton btnDisable = new JButton("DISABLE");
    JButton btnRefresh = new JButton("REFRESH");

    JTable accountTable;
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

    public ManageAccount() {
        this("ADMIN");
    }

    public ManageAccount(String loggedInRole) {
        this.loggedInRole = loggedInRole;

        setTitle("CineVibe - Manage Accounts (" + loggedInRole + ")");
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

        // Active Manage Accounts
        accountPanel.setBounds(15, 225, 220, 50);
        accountPanel.setBackground(dashBg);
        accountPanel.setLayout(null);
        accountPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel1.add(accountPanel);

        maccount.setBounds(15, 5, 190, 40);
        maccount.setFont(boldFont);
        maccount.setForeground(new Color(200, 170, 255));
        maccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
        accountPanel.add(maccount);

        // View Bookings
        vbooking.setBounds(30, 290, 190, 40);
        vbooking.setFont(regularFont);
        vbooking.setForeground(textColor);
        vbooking.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel1.add(vbooking);

        // Logout Panel
        logoutPanel.setBounds(20, 700, 210, 48);
        logoutPanel.setBackground(logoutBg);
        logoutPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutPanel.setBorder(
            BorderFactory.createLineBorder(primaryPurple, 1)
        );
        panel1.add(logoutPanel);

        logout.setBounds(15, 5, 180, 38);
        logout.setFont(new Font("Arial", Font.BOLD, 16));
        logout.setForeground(logoutColor);
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutPanel.add(logout);

        // Sidebar Background Image
        ImageIcon ico = new ImageIcon("C:\\Users\\Rance\\Downloads\\Gemini_Generated_Image_6wvo1f6wvo1f6wvo.jpg");

        if (ico.getIconWidth() > 0) {
            Image scaled = ico.getImage().getScaledInstance(
                250, 850, Image.SCALE_SMOOTH
            );

            JLabel sidebar = new JLabel(new ImageIcon(scaled));
            sidebar.setBounds(0, 0, 250, 850);
            panel1.add(sidebar);

            panel1.setComponentZOrder(
                sidebar, panel1.getComponentCount() - 1
            );
        }

        // Page Title
        pageTitle.setBounds(280, 110, 850, 50);
        pageTitle.setFont(new Font("Arial", Font.BOLD, 30));
        pageTitle.setForeground(Color.WHITE);
        framep.add(pageTitle);

        // Total Accounts Label
        totalAccounts.setBounds(1050, 155, 400, 25);
        totalAccounts.setFont(new Font("Arial", Font.BOLD, 14));
        totalAccounts.setForeground(activeColor);
        totalAccounts.setHorizontalAlignment(SwingConstants.RIGHT);
        framep.add(totalAccounts);

        // Search Field
        searchField.setBounds(280, 175, 420, 42);
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

        // Status Filter Dropdown
        statusFilter.setBounds(715, 175, 180, 42);
        statusFilter.setBackground(tableBg);
        statusFilter.setForeground(Color.WHITE);
        statusFilter.setFont(new Font("Arial", Font.PLAIN, 14));
        statusFilter.setFocusable(false);
        framep.add(statusFilter);

        // Table Columns
        String[] columns = {
            "First Name", "Last Name", "Username", "Email",
            "Contact Number", "Status", "Failed Logins", "Role"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        accountTable = new JTable(tableModel);
        accountTable.setFont(new Font("Arial", Font.PLAIN, 13));
        accountTable.setRowHeight(32);
        accountTable.setBackground(tableBg);
        accountTable.setForeground(Color.WHITE);
        accountTable.setGridColor(tableBorder);
        accountTable.setSelectionBackground(new Color(90, 45, 145));
        accountTable.setSelectionForeground(Color.WHITE);
        accountTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

        accountTable.getTableHeader().setBackground(tableHeaderBg);
        accountTable.getTableHeader().setForeground(Color.WHITE);
        accountTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        accountTable.getTableHeader().setReorderingAllowed(false);

        // Sorting & Filtering
        sorter = new TableRowSorter<>(tableModel);
        accountTable.setRowSorter(sorter);

        DocumentListener searchListener = new DocumentListener() {
            private void filter() {
                applyFilters();
            }
            @Override public void insertUpdate(DocumentEvent e) { filter(); }
            @Override public void removeUpdate(DocumentEvent e) { filter(); }
            @Override public void changedUpdate(DocumentEvent e) { filter(); }
        };
        searchField.getDocument().addDocumentListener(searchListener);
        statusFilter.addActionListener(e -> applyFilters());

        // Table Scroll Pane
        JScrollPane scroll = new JScrollPane(accountTable);
        scroll.setBounds(280, 235, 1170, 430);
        scroll.getViewport().setBackground(tableBg);
        scroll.setBorder(
            BorderFactory.createLineBorder(tableBorder, 1)
        );
        framep.add(scroll);

        // Action Buttons Setup
        btnAdd.setBounds(280, 690, 140, 42);
        styleActionButton(btnAdd, primaryPurple);
        framep.add(btnAdd);

        // Role restriction: Disable Add Button if role is ADMIN
        if (loggedInRole != null && loggedInRole.equalsIgnoreCase("ADMIN")) {
            btnAdd.setEnabled(false);
            btnAdd.setBackground(new Color(80, 70, 100));
            btnAdd.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }

        btnEdit.setBounds(430, 690, 140, 42);
        styleActionButton(btnEdit, primaryPurple);
        framep.add(btnEdit);

        btnDelete.setBounds(580, 690, 140, 42);
        styleActionButton(btnDelete, primaryPurple);
        framep.add(btnDelete);

        btnEnable.setBounds(730, 690, 110, 42);
        styleActionButton(btnEnable, primaryPurple);
        framep.add(btnEnable);

        btnDisable.setBounds(850, 690, 110, 42);
        styleActionButton(btnDisable, primaryPurple);
        framep.add(btnDisable);

        btnRefresh.setBounds(1320, 690, 130, 42);
        styleActionButton(btnRefresh, primaryPurple);
        framep.add(btnRefresh);

        // Button Listeners
        btnAdd.addActionListener(e -> addAccount());
        btnEdit.addActionListener(e -> editAccount());
        btnDelete.addActionListener(e -> deleteAccount());
        btnEnable.addActionListener(e -> changeAccountStatus("ACTIVE"));
        btnDisable.addActionListener(e -> changeAccountStatus("DISABLED"));
        btnRefresh.addActionListener(e -> {
            loadAccounts();
            searchField.setText("");
            statusFilter.setSelectedIndex(0);
            sorter.setRowFilter(null);
        });

        // Navigation Listeners
        dash.addMouseListener(this);
        mscedule.addMouseListener(this);
        maccount.addMouseListener(this);
        accountPanel.addMouseListener(this);
        vbooking.addMouseListener(this);
        logout.addMouseListener(this);
        logoutPanel.addMouseListener(this);

        loadAccounts();
    }

    private void styleActionButton(JButton btn, Color bg) {
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void applyFilters() {
        String text = searchField.getText().trim();
        String status = statusFilter.getSelectedItem().toString();

        List<RowFilter<DefaultTableModel, Object>> filters = new ArrayList<>();

        if (!text.isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(text)));
        }

        if (!status.equals("All Accounts")) {
            filters.add(RowFilter.regexFilter("^" + status + "$", 5));
        }

        if (filters.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.andFilter(filters));
        }
    }

    private void addAccount() {
        if (loggedInRole != null && loggedInRole.equalsIgnoreCase("ADMIN")) {
            JOptionPane.showMessageDialog(this, "Access Denied. Only Superadmins can add accounts.");
            return;
        }

        Register regForm = new Register(true);
        regForm.setVisible(true);
        regForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadAccounts();
            }
        });
    }

    private void editAccount() {
        int selectedRow = accountTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an account to edit.");
            return;
        }

        int modelRow = accountTable.convertRowIndexToModel(selectedRow);
        String username = tableModel.getValueAt(modelRow, 2).toString();

        new EditAccount(username);
    }

    private void deleteAccount() {
        int selectedRow = accountTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an account to delete.");
            return;
        }

        int modelRow = accountTable.convertRowIndexToModel(selectedRow);
        String username = tableModel.getValueAt(modelRow, 2).toString();

        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "Are you sure you want to delete account: " + username + "?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            boolean deleted = updateStatusOrDeleteInFile(username, "DELETE");
            if (deleted) {
                JOptionPane.showMessageDialog(this, "Account successfully deleted.");
                loadAccounts();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete account.");
            }
        }
    }

    private void changeAccountStatus(String newStatus) {
        int selectedRow = accountTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an account.");
            return;
        }

        int modelRow = accountTable.convertRowIndexToModel(selectedRow);
        String username = tableModel.getValueAt(modelRow, 2).toString();

        boolean updated = updateStatusOrDeleteInFile(username, newStatus);
        if (updated) {
            JOptionPane.showMessageDialog(this, "Account status updated to " + newStatus + ".");
            loadAccounts();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update status.");
        }
    }

    private boolean updateStatusOrDeleteInFile(String targetUsername, String action) {
        File file = new File(USERS_FILE);
        if (!file.exists()) 
            return false;

        List<String> updatedLines = new ArrayList<>();
        boolean modified = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) 
                    continue;

                String[] data = line.split("\\|", -1);
                
                if (data.length >= 3 && data[2].equalsIgnoreCase(targetUsername)) {
                    if (action.equals("DELETE")) {
                        modified = true;
                        continue;
                    } else {
                        if (data.length >= 7) {
                            data[6] = action;
                        } else {
                            String[] newData = new String[9];
                            System.arraycopy(data, 0, newData, 0, data.length);
                            for (int i = data.length; i < 9; i++) newData[i] = "";
                            newData[6] = action;
                            data = newData;
                        }
                        line = String.join("|", data);
                        modified = true;
                    }
                }
                updatedLines.add(line);
            }
        } catch (IOException e) {
            return false;
        }

        if (!modified) 
            return false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (String l : updatedLines) {
                writer.write(l);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private void loadAccounts() {
        tableModel.setRowCount(0);
        File file = new File(USERS_FILE);

        if (!file.exists()) {
            totalAccounts.setText("Total Accounts: 0");
            return;
        }

        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) 
                    continue;

                String[] data = line.split("\\|", -1);
                if (data.length >= 9) {
                    tableModel.addRow(new Object[]{
                        data[0], data[1], data[2], data[3],
                        data[4], data[6], data[7], data[8]
                    });
                    count++;
                } else if (data.length >= 7) {
                    tableModel.addRow(new Object[]{
                        data[0], data[1], data[2], data[3],
                        data[4], data[6], "0", data[data.length - 1]
                    });
                    count++;
                }
            }
            totalAccounts.setText("Total Accounts: " + count);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to load accounts.");
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
        } else if (source == vbooking) {
            new ViewBooking(loggedInRole).setVisible(true);
            dispose();
        } else if (source == logout || source == logoutPanel) {
            int choice = JOptionPane.showConfirmDialog(
                this, "Are you sure you want to log out?",
                "Confirm Logout", JOptionPane.YES_NO_OPTION
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
        } else if (source == dash || source == mscedule || source == vbooking) {
            ((JLabel) source).setForeground(activeColor);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object source = e.getSource();
        if (source == logout || source == logoutPanel) {
            logoutPanel.setBackground(logoutBg);
            logout.setForeground(logoutColor);
        } else if (source == dash || source == mscedule || source == vbooking) {
            ((JLabel) source).setForeground(textColor);
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new ManageAccount("ADMIN").setVisible(true)
        );
    }
}