package FinalProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.*;
import java.util.ArrayList;

public class Login extends JFrame implements ActionListener, MouseListener {

    JLabel lblusername = new JLabel("Username");
    JLabel lblpassword = new JLabel("Password");

    JTextField txtusername = new JTextField();
    JTextField txtpassword = new JTextField();

    JButton btnlogin = new JButton("LOGIN");

    JPanel header = new JPanel();
    JPanel mainPanel = new JPanel();

    JLabel lblTitle = new JLabel("CINEVIBE");
    JLabel lblSubtitle = new JLabel("One of the Favorite Cinema of People");

    JLabel changePassword = new JLabel("<html><u>Change Password?</u></html>");

    String defaultUser = "superadmin";
    String defaultPass = "12345678";

    Color darkPurple = new Color(30, 27, 46);
    Color violet = new Color(109, 40, 217);
    Color textColor = new Color(45, 45, 55);
    Color borderColor = new Color(215, 215, 225);

    public Login() {

        setTitle("CINEVIBE - Login");
        setSize(450, 450);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(Color.WHITE);

        header.setBounds(0, 0, 450, 85);
        header.setBackground(darkPurple);
        header.setLayout(null);

        lblTitle.setBounds(25, 13, 300, 30);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        header.add(lblTitle);

        lblSubtitle.setBounds(27, 45, 300, 20);
        lblSubtitle.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSubtitle.setForeground(new Color(205, 200, 220));
        header.add(lblSubtitle);

        add(header);

        mainPanel.setBounds(0, 85, 450, 330);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);

        JLabel welcome = new JLabel("Welcome Back!");
        welcome.setBounds(35, 15, 350, 30);
        welcome.setFont(new Font("Arial", Font.BOLD, 21));
        welcome.setForeground(darkPurple);
        mainPanel.add(welcome);

        JLabel instruction = new JLabel("Login to your account");
        instruction.setBounds(36, 45, 300, 20);
        instruction.setFont(new Font("Arial", Font.PLAIN, 13));
        instruction.setForeground(Color.GRAY);
        mainPanel.add(instruction);

        lblusername.setBounds(35, 80, 350, 20);
        lblusername.setFont(new Font("Arial", Font.BOLD, 13));
        lblusername.setForeground(textColor);
        mainPanel.add(lblusername);

        txtusername.setBounds(35, 103, 365, 35);
        styleField(txtusername);
        mainPanel.add(txtusername);

        lblpassword.setBounds(35, 150, 350, 20);
        lblpassword.setFont(new Font("Arial", Font.BOLD, 13));
        lblpassword.setForeground(textColor);
        mainPanel.add(lblpassword);

        txtpassword.setBounds(35, 173, 365, 35);
        styleField(txtpassword);
        mainPanel.add(txtpassword);

        changePassword.setBounds(255, 211, 145, 25);
        changePassword.setFont(new Font("Arial", Font.PLAIN, 12));
        changePassword.setForeground(violet);
        changePassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        changePassword.addMouseListener(this);
        mainPanel.add(changePassword);

        btnlogin.setBounds(35, 250, 365, 40);
        styleButton(btnlogin, violet, Color.WHITE);
        btnlogin.addActionListener(this);
        mainPanel.add(btnlogin);

        add(mainPanel);

        setVisible(true);
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

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() != btnlogin) {
            return;
        }

        String inputUser = txtusername.getText().trim();
        String inputPass = txtpassword.getText();

        if (inputUser.isEmpty() || inputPass.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter your Username and Password!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

 
        if (inputUser.equals(defaultUser) && inputPass.equals(defaultPass)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Welcome Superadmin!",
                    "Login Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            
            new AdminDashBoard().setVisible(true);
            dispose();
            return;
        }

       
        File file = new File("users.txt");

        if (!file.exists()) {

            JOptionPane.showMessageDialog(
                    this,
                    "users.txt was not found!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        ArrayList<String> records = new ArrayList<>();

        boolean found = false;
        boolean loginSuccess = false;
        String message = "";
        String loggedInRole = "ADMIN"; 

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length >= 8 & data[2].equals(inputUser)) {
                    found = true;

                    if (data[6].equals("DISABLED")) {
                        message = "Your account is disabled. Contact Super Admin.";

                    } else if (data[5].equals(inputPass)) {

                        data[7] = "0";
                        message = "Login successful! Welcome, " + data[0] + "!";

                        loginSuccess = true;
                       
//                        if (data.length > 8 && !data[8].isEmpty()) {
//                            loggedInRole = data[8];
//                        }

                    } else {

                        int attempts;

                        try {
                            attempts = Integer.parseInt(data[7]);
                        } catch (NumberFormatException ex) {
                            attempts = 0;
                        }

                        attempts++;

                        if (attempts >= 3) {
                            data[6] = "DISABLED";
                            data[7] = "3";

                            message = "3 failed attempts! Account disabled.";

                        } else {
                            data[7] = String.valueOf(attempts);
                            message = "Wrong password! Attempts: " + attempts + " of 3";
                        }
                    }

                    line = String.join("|", data);
                }

                records.add(line);
            }

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error reading users.txt!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (!found) {
            JOptionPane.showMessageDialog(
                    this,
                    "Username not found!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // SAVE UPDATED ACCOUNT STATUS AND ATTEMPTS
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {

            for (String record : records) {
                writer.write(record);
                writer.newLine();
            }

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error updating users.txt!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                message,
                "Login",
                loginSuccess
                        ? JOptionPane.INFORMATION_MESSAGE
                        : JOptionPane.ERROR_MESSAGE);

        if (loginSuccess) {
            if (loggedInRole.equalsIgnoreCase("ADMIN")) {
                new AdminDashBoard("ADMIN").setVisible(true);
            } else if (loggedInRole.equalsIgnoreCase("SUPERADMIN")) {
                new AdminDashBoard("SUPERADMIN").setVisible(true);
            } else {
                new DashBoard().setVisible(true);
            }
            dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == changePassword) {

            new ChangePassword(txtusername.getText().trim());
            dispose();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        changePassword.setForeground(new Color(139, 92, 246));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        changePassword.setForeground(violet);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}