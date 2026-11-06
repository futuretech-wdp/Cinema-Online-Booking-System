package FinalProject;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class UserBooking extends JFrame {

    private final String movieTitle;
    private final int ticketPrice;
    private final Set<String> selectedSeats;
    private final String selectedDate;
    private final String selectedTime;
    private final String CUSTOMER_FILE = "customer_bookings.txt";
    private final String BOOKINGS_FILE = "booked_seats.txt";

    private final Color headerBgColor = new Color(32, 28, 44);      
    private final Color windowBgColor = Color.WHITE;                 
    private final Color cardBgColor = new Color(248, 248, 250);       
    private final Color fieldBgColor = new Color(252, 252, 254);      
    private final Color disabledFieldColor = new Color(238, 238, 242); 
    
    private final Color primaryPurple = new Color(118, 43, 226);     
    private final Color darkText = new Color(35, 35, 35);            
    private final Color secondaryText = new Color(130, 130, 140);    
    private final Color lightBorderColor = new Color(220, 220, 228);  

    private final String fontStyle = "Segoe UI";

    private JPanel formsContainer;
    private JPanel ticketsContainer;
    private JPanel paymentPanel;

    private JLabel subtotalLabel;
    private JLabel discountLabel;
    private JLabel totalLabel;

    private JComboBox<String> paymentMethod;

    private final List<JTextField> firstNames = new ArrayList<>();
    private final List<JTextField> lastNames = new ArrayList<>();
    private final List<JTextField> contacts = new ArrayList<>();
    private final List<JTextField> emails = new ArrayList<>();
    private final List<JTextField> ages = new ArrayList<>();
    private final List<JTextField> discountIDs = new ArrayList<>();

    private final List<JComboBox<String>> customerTypes = new ArrayList<>();
    private final List<JComboBox<String>> genders = new ArrayList<>();

    private final List<JLabel> ticketCustomerNames = new ArrayList<>();
    private final List<JLabel> ticketCustomerTypes = new ArrayList<>();
    private final List<JLabel> ticketPrices = new ArrayList<>();

    public UserBooking(
            String movieTitle,
            int ticketPrice,
            Set<String> selectedSeats,
            String selectedDate,
            String selectedTime) {

        this.movieTitle = movieTitle;
        this.ticketPrice = ticketPrice;
        this.selectedSeats = new LinkedHashSet<>(selectedSeats);
        this.selectedDate = selectedDate;
        this.selectedTime = selectedTime;

        setTitle("CineVibe - Customers Information");
        setSize(1250, 850);
        setMinimumSize(new Dimension(1050, 700));
        setResizable(false); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(12, 12));

        getContentPane().setBackground(windowBgColor);

        // HEADER (MATCHED WITH LOGIN CINEVIBE HEADER)
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(headerBgColor);
        header.setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel titleLabel = new JLabel("CUSTOMER INFORMATION");
        titleLabel.setFont(new Font(fontStyle, Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Complete the details for each selected ticket.");
        subtitle.setFont(new Font(fontStyle, Font.PLAIN, 13));
        subtitle.setForeground(new Color(185, 180, 200));

        JPanel headerText = new JPanel();
        headerText.setOpaque(false);
        headerText.setLayout(new BoxLayout(headerText, BoxLayout.Y_AXIS));

        headerText.add(titleLabel);
        headerText.add(Box.createVerticalStrut(4));
        headerText.add(subtitle);

        header.add(headerText, BorderLayout.WEST);

        add(header, BorderLayout.NORTH);

        // MAIN PANEL
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(new EmptyBorder(0, 20, 10, 20));

        // LEFT PANEL
        JPanel leftPanel = new JPanel(new BorderLayout(10, 10));
        leftPanel.setOpaque(false);

        JPanel moviePanel = createCard();
        moviePanel.setLayout(new GridLayout(0, 1, 5, 5));

        JLabel movieLabel = new JLabel("MOVIE DETAILS");
        movieLabel.setFont(new Font(fontStyle, Font.BOLD, 16));
        movieLabel.setForeground(darkText);

        JLabel movieName = new JLabel(movieTitle);
        movieName.setFont(new Font(fontStyle, Font.BOLD, 14));
        movieName.setForeground(darkText);

        JLabel dateLabel = new JLabel("Date: " + selectedDate);
        JLabel timeLabel = new JLabel("Time: " + selectedTime);

        JLabel seatsLabel = new JLabel("Selected Seats: " + String.join(", ", this.selectedSeats));

        JLabel priceLabel = new JLabel("Ticket Price: PHP " + ticketPrice + " each");

        styleLabel(dateLabel);
        styleLabel(timeLabel);
        styleLabel(seatsLabel);
        styleLabel(priceLabel);

        moviePanel.add(movieLabel);
        moviePanel.add(movieName);
        moviePanel.add(dateLabel);
        moviePanel.add(timeLabel);
        moviePanel.add(seatsLabel);
        moviePanel.add(priceLabel);

        leftPanel.add(moviePanel, BorderLayout.NORTH);

        // CUSTOMER FORMS
        formsContainer = new JPanel();
        formsContainer.setLayout(
                new BoxLayout(formsContainer, BoxLayout.Y_AXIS));
        formsContainer.setBackground(windowBgColor);

        for (String seat : this.selectedSeats) {
            formsContainer.add(createCustomerForm(seat));
            formsContainer.add(Box.createVerticalStrut(12));
        }

        JScrollPane formScroll = new JScrollPane(formsContainer);
        formScroll.setBorder(null);
        formScroll.getViewport().setBackground(windowBgColor);
        formScroll.setHorizontalScrollBarPolicy(
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        formScroll.getVerticalScrollBar().setUnitIncrement(16);

        leftPanel.add(formScroll, BorderLayout.CENTER);

        // RIGHT PANEL
        JPanel rightPanel = createCard();
        rightPanel.setPreferredSize(new Dimension(340, 500));
        rightPanel.setLayout(new BorderLayout(8, 10));

        JLabel ticketHeading = new JLabel("YOUR TICKETS");
        ticketHeading.setFont(new Font(fontStyle, Font.BOLD, 18));
        ticketHeading.setForeground(darkText);

        JLabel ticketSubtitle = new JLabel(this.selectedSeats.size() + " selected seat(s)");
        ticketSubtitle.setFont(new Font(fontStyle, Font.PLAIN, 12));
        ticketSubtitle.setForeground(secondaryText);

        JPanel ticketHeader = new JPanel();
        ticketHeader.setOpaque(false);
        ticketHeader.setLayout(new BoxLayout(ticketHeader, BoxLayout.Y_AXIS));

        ticketHeader.add(ticketHeading);
        ticketHeader.add(Box.createVerticalStrut(4));
        ticketHeader.add(ticketSubtitle);

        rightPanel.add(ticketHeader, BorderLayout.NORTH);

        ticketsContainer = new JPanel();
        ticketsContainer.setLayout(new BoxLayout(ticketsContainer, BoxLayout.Y_AXIS));
        ticketsContainer.setBackground(cardBgColor);

        for (String seat : this.selectedSeats) {
            ticketsContainer.add(createTicket(seat));
            ticketsContainer.add(Box.createVerticalStrut(12));
        }

        JScrollPane ticketScroll = new JScrollPane(ticketsContainer);
        ticketScroll.setBorder(null);
        ticketScroll.getViewport().setBackground(cardBgColor);
        ticketScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ticketScroll.getVerticalScrollBar().setUnitIncrement(16);
        ticketScroll.getHorizontalScrollBar().setUnitIncrement(16);

        rightPanel.add(ticketScroll, BorderLayout.CENTER);

        // PAYMENT PANEL
        paymentPanel = new JPanel();
        paymentPanel.setBackground(cardBgColor);
        paymentPanel.setLayout(new BoxLayout(paymentPanel, BoxLayout.Y_AXIS));

        paymentPanel.setBorder(new CompoundBorder(
                new MatteBorder(1, 0, 0, 0, lightBorderColor),
                new EmptyBorder(12, 0, 0, 0)
        ));

        JLabel paymentTitle = new JLabel("PAYMENT");
        paymentTitle.setFont(new Font(fontStyle, Font.BOLD, 16));
        paymentTitle.setForeground(darkText);
        paymentTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel paymentLabel = new JLabel("Payment Method");
        styleLabel(paymentLabel);
        paymentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        paymentMethod = new JComboBox<>(new String[]{
            "Select Payment Method",
            "Cash",
            "GCash",
            "Credit/Debit Card"
        });

        styleComboBox(paymentMethod);
        paymentMethod.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        paymentMethod.setAlignmentX(Component.LEFT_ALIGNMENT);

        subtotalLabel = new JLabel();
        styleLabel(subtotalLabel);
        subtotalLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        discountLabel = new JLabel("Discount: PHP 0");
        styleLabel(discountLabel);
        discountLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        totalLabel = new JLabel();
        totalLabel.setFont(new Font(fontStyle, Font.BOLD, 18));
        totalLabel.setForeground(primaryPurple);
        totalLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        paymentPanel.add(paymentTitle);
        paymentPanel.add(Box.createVerticalStrut(10));
        paymentPanel.add(paymentLabel);
        paymentPanel.add(Box.createVerticalStrut(5));
        paymentPanel.add(paymentMethod);
        paymentPanel.add(Box.createVerticalStrut(12));
        paymentPanel.add(subtotalLabel);
        paymentPanel.add(Box.createVerticalStrut(5));
        paymentPanel.add(discountLabel);
        paymentPanel.add(Box.createVerticalStrut(8));
        paymentPanel.add(totalLabel);

        rightPanel.add(paymentPanel, BorderLayout.SOUTH);

        updateTicketSummary();

        mainPanel.add(leftPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        add(mainPanel, BorderLayout.CENTER);

        // BOTTOM BUTTONS
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 12));
        bottomPanel.setBackground(windowBgColor);
        bottomPanel.setBorder(new EmptyBorder(0, 15, 5, 20));

        JButton cancelButton = new JButton("CANCEL");
        styleButton(cancelButton, false);
        cancelButton.addActionListener(e -> new DashBoard());

        JButton confirmButton = new JButton("CONFIRM BOOKING");
        styleButton(confirmButton, true);
        confirmButton.addActionListener(e -> saveBooking());

        bottomPanel.add(cancelButton);
        bottomPanel.add(confirmButton);

        add(bottomPanel, BorderLayout.SOUTH);

        updateTicketNames();
    }

    // CARD DESIGN
    private JPanel createCard() {
        JPanel panel = new JPanel();

        panel.setBackground(cardBgColor);
        panel.setBorder(new CompoundBorder(
                new LineBorder(lightBorderColor, 1, true),
                new EmptyBorder(15, 15, 15, 15)
        ));

        return panel;
    }

    // TICKET DESIGN
    private JPanel createTicket(String seat) {

        JPanel ticket = new JPanel(new BorderLayout(8, 8));
        ticket.setBackground(new Color(245, 240, 255));

        ticket.setBorder(new CompoundBorder(
                new LineBorder(lightBorderColor, 1, true),
                new EmptyBorder(12, 12, 12, 12)
        ));

        ticket.setAlignmentX(Component.LEFT_ALIGNMENT);
        ticket.setMaximumSize(new Dimension(Integer.MAX_VALUE, 220));

        // TICKET HEADER
        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);

        JLabel brand = new JLabel("CINEVIBE");
        brand.setFont(new Font(fontStyle, Font.BOLD, 14));
        brand.setForeground(primaryPurple);

        JLabel seatLabel = new JLabel("SEAT " + seat);
        seatLabel.setFont(new Font(fontStyle, Font.BOLD, 13));
        seatLabel.setForeground(darkText);

        top.add(brand, BorderLayout.WEST);
        top.add(seatLabel, BorderLayout.EAST);

        // TICKET DETAILS
        JPanel details = new JPanel();
        details.setOpaque(false);
        details.setLayout(
                new BoxLayout(details, BoxLayout.Y_AXIS));

        JLabel movie = new JLabel();
        movie.setFont(new Font(fontStyle, Font.BOLD, 13));
        movie.setForeground(darkText);

        JLabel date = new JLabel("Date: " + selectedDate);
        JLabel time = new JLabel("Time: " + selectedTime);

        JLabel customerName = new JLabel("Customer: Not entered");
        JLabel customerType = new JLabel("Type: Regular");

        date.setForeground(secondaryText);
        time.setForeground(secondaryText);
        customerName.setForeground(darkText);
        customerType.setForeground(secondaryText);

        date.setFont(new Font(fontStyle, Font.PLAIN, 12));
        time.setFont(new Font(fontStyle, Font.PLAIN, 12));
        customerName.setFont(new Font(fontStyle, Font.PLAIN, 12));
        customerType.setFont(new Font(fontStyle, Font.PLAIN, 12));

        ticketCustomerNames.add(customerName);
        ticketCustomerTypes.add(customerType);

        details.add(movie);
        details.add(Box.createVerticalStrut(8));
        details.add(date);
        details.add(Box.createVerticalStrut(3));
        details.add(time);
        details.add(Box.createVerticalStrut(8));
        details.add(customerName);
        details.add(Box.createVerticalStrut(3));
        details.add(customerType);

        // TICKET FOOTER
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);

        JSeparator separator = new JSeparator();
        separator.setForeground(lightBorderColor);

        JLabel price = new JLabel("PHP " + ticketPrice);
        price.setFont(new Font(fontStyle, Font.BOLD, 15));
        price.setForeground(primaryPurple);

        ticketPrices.add(price);

        bottom.add(separator, BorderLayout.NORTH);
        bottom.add(price, BorderLayout.SOUTH);

        ticket.add(top, BorderLayout.NORTH);
        ticket.add(details, BorderLayout.CENTER);
        ticket.add(bottom, BorderLayout.SOUTH);

        return ticket;
    }

    // CUSTOMER FORM
    private JPanel createCustomerForm(String seat) {

        JPanel panel = createCard();
        panel.setLayout(new GridBagLayout());
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 450));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel heading = new JLabel("CUSTOMER - SEAT " + seat);
        heading.setFont(new Font(fontStyle, Font.BOLD, 15));
        heading.setForeground(darkText);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        panel.add(heading, gbc);

        gbc.gridwidth = 1;

        JTextField firstName = createTextField();
        JTextField lastName = createTextField();
        JTextField contact = createTextField();
        JTextField email = createTextField();
        JTextField age = createTextField();
        JTextField discountID = createTextField();

        JComboBox<String> gender = new JComboBox<>(
                new String[]{
                    "Select Gender",
                    "Male",
                    "Female",
                    "Other"});

        JComboBox<String> customerType = new JComboBox<>(
                new String[]{
                    "Regular",
                    "Student",
                    "Senior Citizen",
                    "PWD"
                });

        styleComboBox(gender);
        styleComboBox(customerType);

        firstNames.add(firstName);
        lastNames.add(lastName);
        contacts.add(contact);
        emails.add(email);
        ages.add(age);
        discountIDs.add(discountID);
        genders.add(gender);
        customerTypes.add(customerType);

        int row = 1;

        addFormField(panel, gbc, row++, "First Name", firstName);
        addFormField(panel, gbc, row++, "Last Name", lastName);
        addFormField(panel, gbc, row++, "Contact Number", contact);
        addFormField(panel, gbc, row++, "Email", email);
        addFormField(panel, gbc, row++, "Age", age);
        addFormField(panel, gbc, row++, "Gender", gender);
        addFormField(panel, gbc, row++, "Customer Type", customerType);
        addFormField(panel, gbc, row++, "Discount ID", discountID);

        customerType.addActionListener(e -> {

            String type = (String) customerType.getSelectedItem();

            boolean needsID = !"Regular".equals(type);

            discountID.setEnabled(needsID);

            if (!needsID) {
                discountID.setText("");
            }

            discountID.setBackground(
                    needsID
                            ? fieldBgColor
                            : disabledFieldColor);

            updateTicketNames();
            updateTicketSummary();
        });

        javax.swing.event.DocumentListener nameListener = new javax.swing.event.DocumentListener() {

            @Override
            public void insertUpdate(
                    javax.swing.event.DocumentEvent e) {
                updateTicketNames();
            }

            @Override
            public void removeUpdate(
                    javax.swing.event.DocumentEvent e) {
                updateTicketNames();
            }

            @Override
            public void changedUpdate(
                    javax.swing.event.DocumentEvent e) {
                updateTicketNames();
            }
        };

        firstName.getDocument().addDocumentListener(nameListener);
        lastName.getDocument().addDocumentListener(nameListener);

        discountID.setEnabled(false);
        discountID.setBackground(disabledFieldColor);

        return panel;
    }

    private JTextField createTextField() {

        JTextField field = new JTextField();

        field.setBackground(fieldBgColor);
        field.setForeground(darkText);
        field.setCaretColor(darkText);
        field.setFont(new Font(fontStyle, Font.PLAIN, 13));

        field.setBorder(new CompoundBorder(
                new LineBorder(lightBorderColor, 1, true),
                new EmptyBorder(5, 8, 5, 8)
        ));

        field.setPreferredSize(new Dimension(180, 30));

        return field;
    }

    private void styleComboBox(JComboBox<String> combo) {

        combo.setBackground(fieldBgColor);
        combo.setForeground(darkText);
        combo.setFont(new Font(fontStyle, Font.PLAIN, 13));
        combo.setPreferredSize(new Dimension(180, 30));
        combo.setBorder(new LineBorder(lightBorderColor, 1, true));
    }

    private void addFormField(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String labelText,
            JComponent field) {

        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.weightx = 0.35;

        JLabel label = new JLabel(labelText);
        label.setForeground(darkText);
        label.setFont(new Font(fontStyle, Font.BOLD, 12));

        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.65;

        field.setPreferredSize(new Dimension(180, 30));

        panel.add(field, gbc);
    }

    // UPDATE TICKET CUSTOMER DETAILS
    private void updateTicketNames() {

        for (int i = 0; i < ticketCustomerNames.size(); i++) {

            String first = firstNames.get(i).getText().trim();
            String last = lastNames.get(i).getText().trim();

            String name = (first + " " + last).trim();

            ticketCustomerNames.get(i).setText(
                    "Customer: "
                    + (name.isEmpty() ? "Not entered" : name));

            ticketCustomerTypes.get(i).setText(
                    "Type: " + customerTypes.get(i).getSelectedItem());
        }
    }

    // UPDATE SUBTOTAL AND DISCOUNT
    private void updateTicketSummary() {

        if (subtotalLabel == null) {
            return;
        }

        long subtotal = (long) ticketPrice * selectedSeats.size();
        long discount = 0;

        for (JComboBox<String> typeBox : customerTypes) {

            String type = (String) typeBox.getSelectedItem();

            if ("Student".equals(type)) {
                discount += Math.round(ticketPrice * 0.10);

            } else if ("Senior Citizen".equals(type)
                    || "PWD".equals(type)) {

                discount += Math.round(ticketPrice * 0.20);
            }
        }

        subtotalLabel.setText("Subtotal: PHP " + subtotal);
        discountLabel.setText("Discount: PHP " + discount);
        totalLabel.setText("Total: PHP " + (subtotal - discount));
    }

    private void styleLabel(JLabel label) {
        label.setForeground(darkText);
        label.setFont(new Font(fontStyle, Font.PLAIN, 12));
    }

    private void styleButton(JButton button, boolean primary) {

        button.setFont(new Font(fontStyle, Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new EmptyBorder(12, 18, 12, 18));
        button.setOpaque(true);
        button.setBorderPainted(false);

        if (primary) {
            button.setBackground(primaryPurple);
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(disabledFieldColor);
            button.setForeground(darkText);
        }
    }

    // SAVE BOOKING
    private void saveBooking() {

        if (selectedSeats.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this, "Please select at least one seat.");
            return;
        }

        String payment = (String) paymentMethod.getSelectedItem();

        if (payment == null || payment.equals("Select Payment Method")) {

            JOptionPane.showMessageDialog(this, "Please select a payment method.");
            return;
        }

        List<String> customerNames = new ArrayList<>();
        List<String> customerTypesList = new ArrayList<>();
        List<String> records = new ArrayList<>();
        List<String> seatRecords = new ArrayList<>();

        long subtotal = 0;
        long totalDiscount = 0;
        long total = 0;

        int index = 0;

        for (String seat : selectedSeats) {

            String first = firstNames.get(index).getText().trim();
            String last = lastNames.get(index).getText().trim();
            String contact = contacts.get(index).getText().trim();
            String email = emails.get(index).getText().trim();
            String ageText = ages.get(index).getText().trim();
            String gender = (String) genders.get(index).getSelectedItem();
            String type = (String) customerTypes.get(index).getSelectedItem();
            String discountID = discountIDs.get(index).getText().trim();

            if (first.isEmpty() || last.isEmpty()
                    || contact.isEmpty() || email.isEmpty()
                    || ageText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Complete all fields for seat " + seat + ".");
                return;
            }

            if (!contact.matches("^09\\d{9}$")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter a valid contact number for seat " + seat + ".");
                return;
            }
            
            if (contact.length() != 11) {
            JOptionPane.showMessageDialog(
                        this,
                        "The contact number must be exactly 11 digits long in " + seat + ".");
                return;
            
            }

            if (!email.matches("^[\\w.+-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {

                JOptionPane.showMessageDialog(this,"Enter a valid email for seat " + seat + ".");
                return;
            }
           
            int age;

            try {

                age = Integer.parseInt(ageText);

                if (age < 1 || age > 120) {
                    throw new NumberFormatException();
                }
                
                if (age < 18) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Customer for seat " + seat + " must be 18 years old or above.");
                    return;
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter a valid age for seat " + seat + ".");
                return;
            }

            if (gender == null || gender.equals("Select Gender")) {

                JOptionPane.showMessageDialog(this,"Select gender for seat " + seat + ".");
                return;
            }

            if (!"Regular".equals(type) && discountID.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter the discount ID for seat " + seat + ".");
                return;
            }

            long discount = 0;

            if ("Student".equals(type)) {
                discount = Math.round(ticketPrice * 0.10);

            } else if ("Senior Citizen".equals(type)
                    || "PWD".equals(type)) {

                discount = Math.round(ticketPrice * 0.20);
            }

            long finalPrice = ticketPrice - discount;

            subtotal += ticketPrice;
            totalDiscount += discount;
            total += finalPrice;

            customerNames.add(first + " " + last);
            customerTypesList.add(type);

            records.add(
                    clean(movieTitle) + "|"
                    + clean(selectedDate) + "|"
                    + clean(selectedTime) + "|"
                    + clean(seat) + "|"
                    + clean(first) + "|"
                    + clean(last) + "|"
                    + clean(contact) + "|"
                    + clean(email) + "|"
                    + age + "|"
                    + clean(gender) + "|"
                    + clean(type) + "|"
                    + clean(discountID) + "|"
                    + finalPrice + "|"
                    + discount + "|"
                    + clean(payment)
            );

            seatRecords.add(
                    clean(movieTitle) + "|"
                    + clean(selectedDate) + "|"
                    + clean(selectedTime) + "|"
                    + clean(seat)
            );

            index++;
        }

        if (hasDuplicateSeats()) {

            JOptionPane.showMessageDialog(
                    this,
                    "One or more seats have already been booked "
                    + "for this movie, date, and time.");

            return;
        }

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Movie: " + movieTitle
                + "\nSeats: " + String.join(", ", selectedSeats)
                + "\nPayment: " + payment
                + "\nSubtotal: PHP " + subtotal
                + "\nDiscount: PHP " + totalDiscount
                + "\nTotal: PHP " + total
                + "\n\nConfirm booking?",
                "Confirm Booking",
                JOptionPane.YES_NO_OPTION
        );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            appendRecords(CUSTOMER_FILE, records);
            appendRecords(BOOKINGS_FILE, seatRecords);

            JOptionPane.showMessageDialog(
                    this, "Booking saved successfully!");

            new BookingSummary(
                    movieTitle,
                    selectedDate,
                    selectedTime,
                    new LinkedHashSet<>(selectedSeats),
                    customerNames,
                    customerTypesList,
                    ticketPrice,
                    subtotal,
                    totalDiscount,
                    total,
                    payment
            ).setVisible(true);

            dispose();

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to save booking: " + ex.getMessage(),
                    "File Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private String clean(String value) {

        if (value == null) {
            return "";
        }
        

        return value.replace("|", "/")
                .replace("\n", " ")
                .replace("\r", " ");
    }

    private void appendRecords(
            String fileName,
            List<String> records) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(fileName, true))) {

            for (String record : records) {
                writer.write(record);
                writer.newLine();
            }
        }
    }

    // CHECK IF SEAT IS ALREADY BOOKED
    private boolean hasDuplicateSeats() {

        File file = new File(BOOKINGS_FILE);

        if (!file.exists()) {
            return false;
        }

        Set<String> existingBookings = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length >= 4) {

                    existingBookings.add(
                            data[0] + "|"
                            + data[1] + "|"
                            + data[2] + "|"
                            + data[3]);
                }
            }

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to check existing bookings.");

            return true;
        }

        for (String seat : selectedSeats) {

            String key = movieTitle + "|"
                    + selectedDate + "|"
                    + selectedTime + "|"
                    + seat;

            if (existingBookings.contains(key)) {
                return true;
            }
        }

        return false;
    }
}