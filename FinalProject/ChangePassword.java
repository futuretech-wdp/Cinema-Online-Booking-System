package FinalProject;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;

public class ChangePassword extends JFrame implements ActionListener {

    JLabel curentp = new JLabel("Current Password");
    JLabel newp = new JLabel("New Password");
    JLabel confirmnp = new JLabel("Confirm New Password");

    JTextField cp = new JTextField();
    JTextField np = new JTextField();
    JTextField cnp = new JTextField();

    JPanel header = new JPanel();
    JPanel mainPanel = new JPanel();

    JLabel title = new JLabel("Change Password");
    JLabel subtitle = new JLabel("Update your account credentials");

    JButton updateBtn = new JButton("Update Password");
    JButton cancelBtn = new JButton("Cancel");

    Color darkPurple = new Color(30, 27, 46);
    Color violet = new Color(109, 40, 217);
    Color textColor = new Color(45, 45, 55);
    Color borderColor = new Color(215, 215, 225);

    String username;

    public ChangePassword(String username) {

        this.username = username;

        setTitle("Change Password");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(Color.WHITE);

        header.setBackground(darkPurple);
        header.setBounds(0, 0, 450, 75);
        header.setLayout(null);

        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setBounds(25, 12, 300, 28);
        header.add(title);

        subtitle.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitle.setForeground(new Color(205, 200, 220));
        subtitle.setBounds(26, 42, 300, 20);
        header.add(subtitle);

        add(header);

        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(0, 75, 450, 390);
        mainPanel.setLayout(null);

        curentp.setFont(new Font("Arial", Font.BOLD, 13));
        curentp.setForeground(textColor);
        curentp.setBounds(35, 25, 250, 20);
        mainPanel.add(curentp);

        cp.setBounds(35, 50, 365, 35);
        styleField(cp);
        mainPanel.add(cp);

        newp.setFont(new Font("Arial", Font.BOLD, 13));
        newp.setForeground(textColor);
        newp.setBounds(35, 100, 250, 20);
        mainPanel.add(newp);

        np.setBounds(35, 125, 365, 35);
        styleField(np);
        mainPanel.add(np);

        confirmnp.setFont(new Font("Arial", Font.BOLD, 13));
        confirmnp.setForeground(textColor);
        confirmnp.setBounds(35, 175, 250, 20);
        mainPanel.add(confirmnp);

        cnp.setBounds(35, 200, 365, 35);
        styleField(cnp);
        mainPanel.add(cnp);

        updateBtn.setBounds(35, 260, 365, 40);
        updateBtn.setBackground(violet);
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setFont(new Font("Arial", Font.BOLD, 14));
        updateBtn.setFocusPainted(false);
        updateBtn.setBorderPainted(false);
        updateBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateBtn.addActionListener(this);
        mainPanel.add(updateBtn);

        cancelBtn.setBounds(150, 315, 130, 30);
        cancelBtn.setForeground(violet);
        cancelBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        cancelBtn.setBorderPainted(false);
        cancelBtn.setContentAreaFilled(false);
        cancelBtn.setFocusPainted(false);
        cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelBtn.addActionListener(this);
        mainPanel.add(cancelBtn);

        add(mainPanel);

        setVisible(true);
    }

    private void styleField(JTextField field) {

        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBackground(Color.WHITE);
        field.setForeground(textColor);
        field.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(borderColor),new EmptyBorder(5, 10, 5, 10)));
    }

    private void changePassword() {

        String currentPassword = cp.getText();
        String newPassword = np.getText();
        String confirmPassword = cnp.getText();

        if (currentPassword.isEmpty()
                || newPassword.isEmpty()
                || confirmPassword.isEmpty()) {

            JOptionPane.showMessageDialog(this,"Please fill in all fields!");
            return;
        }

        if (newPassword.length() < 8) {

            JOptionPane.showMessageDialog(this,"Password must be at least 8 characters!");
            return;
        }

        if (newPassword.contains(" ")
                || newPassword.contains("|")
                || newPassword.contains("\n")
                || newPassword.contains("\r")) {

            JOptionPane.showMessageDialog(this,"Password cannot contain spaces or | symbol!");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {

            JOptionPane.showMessageDialog(this,"New passwords do not match!");
            return;
        }

        if (currentPassword.equals(newPassword)) {

            JOptionPane.showMessageDialog(this,"Use a different new password!");
            return;
        }

        File file = new File("users.txt");

        if (!file.exists()) {

            JOptionPane.showMessageDialog(this,"User database not found!");
            return;
        }

        ArrayList<String> lines = new ArrayList<>();
        boolean userFound = false;
        boolean passwordCorrect = false;

        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length >= 6 && data[2].equalsIgnoreCase(username)) {

                    userFound = true;

                    if (data[5].equals(currentPassword)) {

                        passwordCorrect = true;
                        data[5] = newPassword;

                        line = String.join("|", data);
                    }
                }

                lines.add(line);
            }

            reader.close();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(this, "Cannot read users.txt!");
            return;
        }

        if (!userFound) {
            JOptionPane.showMessageDialog(this, "Account not found!");
            return;
        }

        if (!passwordCorrect) {

            JOptionPane.showMessageDialog(this,"Incorrect current password!");
            return;
        }

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < lines.size(); i++) {

                writer.write(lines.get(i));
                writer.newLine();
            }

            writer.close();

            JOptionPane.showMessageDialog(this,"Password changed successfully!");
            dispose();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(this, "Cannot update password!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == updateBtn) {

            changePassword();

        } else if (e.getSource() == cancelBtn) {
            new Login();

            dispose();
        }
    }
}
