package FinalProject;

import java.awt.*;
import java.io.*;
import javax.swing.*;

public class EditAccount extends JFrame {

    JPanel header = new JPanel(null);
    JPanel formPanel = new JPanel(null);

    JLabel title = new JLabel("CINEVIBE");
    JLabel subtitle = new JLabel("Edit Admin account information");

    JTextField firstNameField = new JTextField();
    JTextField lastNameField = new JTextField();
    JTextField usernameField = new JTextField();
    JTextField emailField = new JTextField();
    JTextField contactField = new JTextField();

    JComboBox<String> roleBox = new JComboBox<>(
        new String[]{"ADMIN"}
    );

    JComboBox<String> statusBox = new JComboBox<>(
        new String[]{"ACTIVE", "DISABLED"}
    );

    JButton saveButton = new JButton("SAVE CHANGES");
    JButton cancelButton = new JButton("CANCEL");

    Color darkPurple = new Color(30, 25, 45);
    Color violet = new Color(109, 40, 217);
    Color lightPurple = new Color(240, 232, 255);

    String USERS_FILE = "users.txt";
    String originalUsername;

    public EditAccount(String username) {

        originalUsername = username;

        setTitle("CineVibe - Edit Account");
        setSize(450, 780);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        createHeader();
        createForm();
        loadAccount();

        setVisible(true);
    }

    private void createHeader() {

        header.setBounds(0, 0, 450, 85);
        header.setBackground(darkPurple);
        header.setLayout(null);
        add(header);

        title.setBounds(30, 10, 300, 35);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(new Color(169, 112, 255));
        header.add(title);

        subtitle.setBounds(32, 47, 380, 25);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 13));
        subtitle.setForeground(Color.WHITE);
        header.add(subtitle);
    }

    private void createForm() {

        formPanel.setBounds(0, 85, 450, 660);
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(null);
        add(formPanel);

        addField("First Name", firstNameField, 32);
        addField("Last Name", lastNameField, 90);
        addField("Username", usernameField, 148);
        addField("Email", emailField, 206);
        addField("Contact Number", contactField, 264);

        JLabel roleLabel = new JLabel("Account Role");
        roleLabel.setBounds(35, 322, 380, 20);
        roleLabel.setFont(new Font("Arial", Font.BOLD, 13));
        roleLabel.setForeground(darkPurple);
        formPanel.add(roleLabel);

        roleBox.setBounds(35, 343, 370, 32);
        formPanel.add(roleBox);

        JLabel statusLabel = new JLabel("Account Status");
        statusLabel.setBounds(35, 380, 380, 20);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 13));
        statusLabel.setForeground(darkPurple);
        formPanel.add(statusLabel);

        statusBox.setBounds(35, 401, 370, 32);
        formPanel.add(statusBox);

        saveButton.setBounds(35, 460, 370, 42);
        saveButton.setBackground(violet);
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setFocusPainted(false);
        saveButton.setBorderPainted(false);
        formPanel.add(saveButton);

        cancelButton.setBounds(35, 510, 370, 42);
        cancelButton.setBackground(lightPurple);
        cancelButton.setForeground(darkPurple);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setFocusPainted(false);
        cancelButton.setBorderPainted(false);
        formPanel.add(cancelButton);

        saveButton.addActionListener(e -> saveAccount());
        cancelButton.addActionListener(e -> dispose());
    }

    private void addField(String labelText, JTextField field, int y) {

        JLabel label = new JLabel(labelText);
        label.setBounds(35, y, 380, 20);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setForeground(darkPurple);
        formPanel.add(label);

        field.setBounds(35, y + 21, 370, 32);
        field.setFont(new Font("Arial", Font.PLAIN, 13));
        field.setBorder(
            BorderFactory.createLineBorder(new Color(210, 200, 225))
        );
        formPanel.add(field);
    }

    private void loadAccount() {

        File file = new File(USERS_FILE);

        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "users.txt not found.");
            dispose();
            return;
        }

        boolean found = false;

        try (BufferedReader reader =
                new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length >= 6 &&
                    data[2].equalsIgnoreCase(originalUsername)) {

                    firstNameField.setText(data[0]);
                    lastNameField.setText(data[1]);
                    usernameField.setText(data[2]);
                    emailField.setText(data[3]);
                    contactField.setText(data[4]);

                    if (data.length > 8) {
                        roleBox.setSelectedItem(data[8]);
                    }

                    if (data.length > 6) {
                        statusBox.setSelectedItem(data[6]);
                    }

                    found = true;
                    break;
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading account.");
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Account not found.");
            dispose();
        }
    }

    private void saveAccount() {

        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String contact = contactField.getText().trim();
        String role = roleBox.getSelectedItem().toString();
        String status = statusBox.getSelectedItem().toString();

        if (firstName.isEmpty() || lastName.isEmpty()
                || username.isEmpty() || email.isEmpty()
                || contact.isEmpty()) {

            JOptionPane.showMessageDialog(
                this, "Please complete all fields."
            );
            return;
        }

        File file = new File(USERS_FILE);
        File temp = new File("users_temp.txt");
        boolean found = false;

        try (BufferedReader reader =
                    new BufferedReader(new FileReader(file));
             BufferedWriter writer =
                    new BufferedWriter(new FileWriter(temp))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length >= 3 &&
                    data[2].equalsIgnoreCase(originalUsername)) {

                    found = true;

                    data[0] = firstName;
                    data[1] = lastName;
                    data[2] = username;
                    data[3] = email;
                    data[4] = contact;

                    if (data.length < 9) {
                        String[] newData = new String[9];

                        System.arraycopy(
                            data, 0, newData, 0, data.length
                        );

                        for (int i = data.length; i < 9; i++) {
                            newData[i] = "";
                        }

                        data = newData;
                    }

                    data[6] = status;
                    data[8] = role;

                    line = String.join("|", data);
                }

                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                this, "Error saving account."
            );
            return;
        }

        if (!found) {
            temp.delete();
            JOptionPane.showMessageDialog(this, "Account not found.");
            return;
        }

        if (!file.delete() || !temp.renameTo(file)) {
            JOptionPane.showMessageDialog(
                this, "Could not update users.txt."
            );
            return;
        }

        JOptionPane.showMessageDialog(
            this, "Account updated successfully!"
        );

        dispose();
    }
}