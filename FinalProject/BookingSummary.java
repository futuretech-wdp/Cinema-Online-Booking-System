package FinalProject;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class BookingSummary extends JFrame {

    private final Color backgroundColor = new Color(247, 245, 250);
    private final Color cardColor = Color.WHITE;
    private final Color darkPurple = new Color(69, 48, 91);
    private final Color purple = new Color(112, 75, 158);
    private final Color textColor = new Color(48, 39, 58);
    private final Color secondaryText = new Color(112, 101, 123);
    private final Color borderColor = new Color(222, 214, 232);

    public BookingSummary(
            String movieTitle,
            String selectedDate,
            String selectedTime,
            Set<String> selectedSeats,
            List<String> customerNames,
            List<String> customerTypes,
            int ticketPrice,
            long subtotal,
            long totalDiscount,
            long total,
            String paymentMethod) {

        setTitle("CineVibe - Booking Summary");
        setSize(750, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(backgroundColor);

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(darkPurple);
        header.setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel title = new JLabel("BOOKING CONFIRMED");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel(
                "Thank you for choosing CineVibe!");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(new Color(225, 216, 239));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(title);
        header.add(Box.createVerticalStrut(8));
        header.add(subtitle);

        add(header, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(backgroundColor);
        content.setBorder(new EmptyBorder(15, 25, 15, 25));

        JPanel movieCard = createCard();
        movieCard.setLayout(new BoxLayout(movieCard, BoxLayout.Y_AXIS));

        JLabel movieHeading = new JLabel("MOVIE DETAILS");
        movieHeading.setFont(new Font("Arial", Font.BOLD, 17));
        movieHeading.setForeground(purple);

        movieCard.add(movieHeading);
        movieCard.add(Box.createVerticalStrut(12));

        addDetail(movieCard, "Movie", movieTitle);
        addDetail(movieCard, "Date", selectedDate);
        addDetail(movieCard, "Time", selectedTime);
        addDetail(movieCard, "Selected Seats",String.join(", ", selectedSeats));
        addDetail(movieCard, "Ticket Price","PHP " + ticketPrice + " each");
        addDetail(movieCard, "Payment Method", paymentMethod);

        content.add(movieCard);
        content.add(Box.createVerticalStrut(15));

        JPanel customerCard = createCard();
        customerCard.setLayout(new BoxLayout(customerCard, BoxLayout.Y_AXIS));

        JLabel customerHeading = new JLabel("CUSTOMER DETAILS");
        customerHeading.setFont(new Font("Arial", Font.BOLD, 17));
        customerHeading.setForeground(purple);

        customerCard.add(customerHeading);
        customerCard.add(Box.createVerticalStrut(10));

        int index = 0;

        for (String seat : selectedSeats) {
            String customerName = index < customerNames.size()
                    ? customerNames.get(index) : "N/A";

            String customerType = index < customerTypes.size()
                    ? customerTypes.get(index) : "N/A";

            JLabel customerLabel = new JLabel(
                    "Seat " + seat + "  -  " + customerName
                    + " (" + customerType + ")");

            customerLabel.setFont(new Font("Arial", Font.PLAIN, 13));
            customerLabel.setForeground(textColor);
            customerLabel.setBorder(new EmptyBorder(5, 0, 5, 0));

            customerCard.add(customerLabel);
            index++;
        }

        content.add(customerCard);
        content.add(Box.createVerticalStrut(15));

        JPanel totalCard = createCard();
        totalCard.setLayout(new BoxLayout(totalCard, BoxLayout.Y_AXIS));

        JLabel totalHeading = new JLabel("PAYMENT SUMMARY");
        totalHeading.setFont(new Font("Arial", Font.BOLD, 17));
        totalHeading.setForeground(purple);

        totalCard.add(totalHeading);
        totalCard.add(Box.createVerticalStrut(10));

        addDetail(totalCard, "Subtotal", "PHP " + subtotal);
        addDetail(totalCard, "Total Discount", "PHP " + totalDiscount);

        JSeparator separator = new JSeparator();
        separator.setForeground(borderColor);
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        totalCard.add(Box.createVerticalStrut(8));
        totalCard.add(separator);
        totalCard.add(Box.createVerticalStrut(8));

        JPanel finalTotalPanel = new JPanel(new BorderLayout());
        finalTotalPanel.setOpaque(false);

        JLabel finalTotalLabel = new JLabel("TOTAL AMOUNT");
        finalTotalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        finalTotalLabel.setForeground(darkPurple);

        JLabel finalAmount = new JLabel("PHP " + total);
        finalAmount.setFont(new Font("Arial", Font.BOLD, 22));
        finalAmount.setForeground(purple);

        finalTotalPanel.add(finalTotalLabel, BorderLayout.WEST);
        finalTotalPanel.add(finalAmount, BorderLayout.EAST);

        totalCard.add(finalTotalPanel);

        content.add(totalCard);

        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(backgroundColor);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(backgroundColor);
        bottomPanel.setBorder(new EmptyBorder(0, 15, 15, 15));

        JButton closeButton = new JButton("DONE");
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setBackground(purple);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setOpaque(true);
        closeButton.setBorder(new EmptyBorder(12, 35, 12, 35));
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeButton.addActionListener(e -> dispose());

        bottomPanel.add(closeButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createCard() {
        JPanel panel = new JPanel();
        panel.setBackground(cardColor);
        panel.setBorder(new CompoundBorder(
                new LineBorder(borderColor, 1, true),
                new EmptyBorder(18, 18, 18, 18)
        ));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        return panel;
    }

    private void addDetail(
            JPanel panel,
            String labelText,
            String valueText) {

        JPanel row = new JPanel(new BorderLayout(15, 5));
        row.setOpaque(false);
        row.setBorder(new EmptyBorder(5, 0, 5, 0));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        label.setForeground(secondaryText);

        JLabel value = new JLabel(
                "<html><div style='text-align:right; width:300px'>"
                + escapeHtml(valueText)
                + "</div></html>");

        value.setFont(new Font("Arial", Font.BOLD, 13));
        value.setForeground(textColor);
        value.setHorizontalAlignment(SwingConstants.RIGHT);

        row.add(label, BorderLayout.WEST);
        row.add(value, BorderLayout.EAST);

        panel.add(row);
    }

    private String escapeHtml(String value) {
        if (value == null) {
            return "";
        }

        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}