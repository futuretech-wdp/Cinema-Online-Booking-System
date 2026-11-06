package FinalProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Register extends JFrame implements ActionListener {

    JLabel lblFrmLogo = new JLabel("Create an Account");
    JLabel lblSubtitle = new JLabel("An account for an Admin");

    JLabel lblfirstname = new JLabel("First Name");
    JLabel lbllastname = new JLabel("Last Name");
    JLabel lblusername = new JLabel("Username");
    JLabel lblemail = new JLabel("Email");
    JLabel lblcontact = new JLabel("Contact Number");
    JLabel lblpassword = new JLabel("Password");
    JLabel lblconfirmpassword = new JLabel("Confirm Password");

    JTextField txtfirstname = new JTextField();
    JTextField txtlastname = new JTextField();
    JTextField txtusername = new JTextField();
    JTextField txtemail = new JTextField();
    JTextField txtcontact = new JTextField();
    JTextField txtpassword = new JTextField();
    JTextField txtconfirmpassword = new JTextField();

    JButton btnregister = new JButton("CONFIRM");
    JButton btncancel = new JButton("CANCEL");

    JPanel header = new JPanel();
    JPanel mainPanel = new JPanel();

    public static String registeredUsername = "";
    public static String registeredPassword = "";
    public static String fname = "";
    public static String lname = "";
    public static String email = "";
    public static String contactno = "";

    private final String USERS_FILE = "users.txt";
    private final boolean openedFromManageAccount;

    Color darkPurple = new Color(30, 27, 46);
    Color violet = new Color(109, 40, 217);
    Color textColor = new Color(45, 45, 55);
    Color borderColor = new Color(215, 215, 225);

    public Register(boolean fromManageAccount) {
        this.openedFromManageAccount = fromManageAccount;

        setTitle("CINEVIBE - Register");
        setSize(450, 700);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(Color.WHITE);

        header.setBounds(0, 0, 450, 85);
        header.setBackground(darkPurple);
        header.setLayout(null);

        lblFrmLogo.setBounds(25, 13, 350, 30);
        lblFrmLogo.setFont(new Font("Arial", Font.BOLD, 22));
        lblFrmLogo.setForeground(Color.WHITE);
        header.add(lblFrmLogo);

        lblSubtitle.setBounds(27, 45, 350, 20);
        lblSubtitle.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSubtitle.setForeground(new Color(205, 200, 220));
        header.add(lblSubtitle);

        add(header);

        mainPanel.setBounds(0, 85, 450, 615);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);

        lblfirstname.setBounds(35, 10, 350, 20);
        styleLabel(lblfirstname);
        mainPanel.add(lblfirstname);

        txtfirstname.setBounds(35, 32, 365, 30);
        styleField(txtfirstname);
        mainPanel.add(txtfirstname);

        lbllastname.setBounds(35, 68, 350, 20);
        styleLabel(lbllastname);
        mainPanel.add(lbllastname);

        txtlastname.setBounds(35, 90, 365, 30);
        styleField(txtlastname);
        mainPanel.add(txtlastname);

        lblusername.setBounds(35, 126, 350, 20);
        styleLabel(lblusername);
        mainPanel.add(lblusername);

        txtusername.setBounds(35, 148, 365, 30);
        styleField(txtusername);
        mainPanel.add(txtusername);

        lblemail.setBounds(35, 184, 350, 20);
        styleLabel(lblemail);
        mainPanel.add(lblemail);

        txtemail.setBounds(35, 206, 365, 30);
        styleField(txtemail);
        mainPanel.add(txtemail);

        lblcontact.setBounds(35, 242, 350, 20);
        styleLabel(lblcontact);
        mainPanel.add(lblcontact);

        txtcontact.setBounds(35, 264, 365, 30);
        styleField(txtcontact);
        mainPanel.add(txtcontact);

        lblpassword.setBounds(35, 300, 350, 20);
        styleLabel(lblpassword);
        mainPanel.add(lblpassword);

        txtpassword.setBounds(35, 322, 365, 30);
        styleField(txtpassword);
        mainPanel.add(txtpassword);

        lblconfirmpassword.setBounds(35, 358, 350, 20);
        styleLabel(lblconfirmpassword);
        mainPanel.add(lblconfirmpassword);

        txtconfirmpassword.setBounds(35, 380, 365, 30);
        styleField(txtconfirmpassword);
        mainPanel.add(txtconfirmpassword);

        btnregister.setBounds(35, 430, 365, 40);
        styleButton(btnregister, violet, Color.WHITE);
        mainPanel.add(btnregister);

        btncancel.setBounds(35, 480, 365, 35);
        styleButton(btncancel, Color.WHITE, violet);
        btncancel.setBorder(BorderFactory.createLineBorder(violet));
        mainPanel.add(btncancel);

        add(mainPanel);

        btnregister.addActionListener(this);
        btncancel.addActionListener(this);
    }

    private void styleLabel(JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setForeground(textColor);
    }

    private void styleField(JTextField field) {
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setForeground(textColor);
        field.setBackground(Color.WHITE);

        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor),
                new EmptyBorder(5, 10, 5, 10)
        ));
    }

    private void styleButton(JButton button, Color background, Color foreground) {
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private boolean isValidName(String name) {
        return name.matches("[\\p{L}]+([ '\\u002D][\\p{L}]+)*");
    }

    private boolean isValidUsername(String username) {
        return username.matches("[a-zA-Z0-9_]{4,20}");
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    
    
    
    private boolean isValidContact(String contact) {
        return contact.matches("(09\\d{9}|\\+639\\d{9})");
    }

    private boolean usernameExists(String username) {
        File file = new File(USERS_FILE);
        if (!file.exists()) {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|", -1);
                if (data.length >= 3 && data[2].equalsIgnoreCase(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read user database!\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

    private boolean saveUser(
            String firstName,
            String lastName,
            String username,
            String userEmail,
            String contact,
            String password,
            String role) {

        try (FileWriter writer = new FileWriter(USERS_FILE, true)) {
            writer.write(firstName + "|"
                    + lastName + "|"
                    + username + "|"
                    + userEmail + "|"
                    + contact + "|"
                    + password + "|"
                    + "ACTIVE|0|"
                    + role
                    + System.lineSeparator());
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving account!\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnregister) {

            String firstName = txtfirstname.getText().trim();
            String lastName = txtlastname.getText().trim();
            String user = txtusername.getText().trim();
            String userEmail = txtemail.getText().trim();
            String contact = txtcontact.getText().trim();
            String pass = txtpassword.getText();
            String confirmPass = txtconfirmpassword.getText();
            
            
            String role = "ADMIN"; 

            if (firstName.isEmpty()
                    || lastName.isEmpty()
                    || user.isEmpty()
                    || userEmail.isEmpty()
                    || contact.isEmpty()
                    || pass.isEmpty()
                    || confirmPass.isEmpty()) {
                showError("Please fill in all fields!");
                return;
            }

            if (!isValidName(firstName)) {
                showError("First name must contain letters only.\nSpaces, hyphens, and apostrophes are allowed.");
                txtfirstname.requestFocus();
                return;
            }

            if (!isValidName(lastName)) {
                showError("Last name must contain letters only.\nSpaces, hyphens, and apostrophes are allowed.");
                txtlastname.requestFocus();
                return;
            }

            if (!isValidUsername(user)) {
                showError("Username must be 4-20 characters long.\nUse only letters, numbers, and underscores.");
                txtusername.requestFocus();
                return;
            }

            if (!isValidEmail(userEmail)) {
                showError("Please enter a valid email address.\nExample: sample@gmail.com");
                txtemail.requestFocus();
                return;
            }

            if (!isValidContact(contact)) {
                showError("Enter a valid Philippine mobile number.\nExample: 09123456789 or +639123456789");
                txtcontact.requestFocus();
                return;
            }

            if (pass.length() < 8) {
                showError("Password must be at least 8 characters long!");
                txtpassword.requestFocus();
                return;
            }

            if (pass.contains(" ") || pass.contains("|")) {
                showError("Password cannot contain spaces or the | symbol.");
                txtpassword.requestFocus();
                return;
            }

            if (!pass.equals(confirmPass)) {
                showError("Passwords do not match!");
                txtconfirmpassword.requestFocus();
                return;
            }

            if (usernameExists(user)) {
                showError("Username already exists!");
                txtusername.requestFocus();
                return;
            }

            boolean saved = saveUser(
                    firstName,
                    lastName,
                    user,
                    userEmail,
                    contact,
                    pass,
                    role
            );

            if (saved) {
                registeredUsername = user;
                registeredPassword = pass;
                fname = firstName;
                lname = lastName;
                email = userEmail;
                contactno = contact;

                JOptionPane.showMessageDialog(
                        this,
                        "Account registered successfully!\nRole: " + role,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                dispose();

                if (!openedFromManageAccount) {
                    new Login();
                }
            }

        } else if (e.getSource() == btncancel) {
            dispose();
            if (!openedFromManageAccount) {
                new Login();
            }
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE
        );
    }
}