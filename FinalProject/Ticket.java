package FinalProject;

import javax.swing.*;
import java.awt.*;

public class Ticket extends JPanel {

    private String movieTitle;
    private String seatNumber;
    private String customerName;

    public Ticket(String movieTitle, String seatNumber, String customerName) {
        this.movieTitle = movieTitle;
        this.seatNumber = seatNumber;
        this.customerName = customerName;

        setLayout(null);
        setPreferredSize(new Dimension(700, 400));
        setMaximumSize(new Dimension(1000, 200));
        setBackground(new Color(24, 16, 48)); 
        
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(139, 92, 246), 2, true),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        JLabel lblTitle = new JLabel("🎬 " + this.movieTitle);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitle.setForeground(new Color(240, 235, 255));
        lblTitle.setBounds(12, 10, 280, 22);
        add(lblTitle);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(90, 60, 140));
        separator.setBounds(12, 38, 280, 5);
        add(separator);

        JLabel lblCustomerTitle = new JLabel("TICKET HOLDER");
        lblCustomerTitle.setFont(new Font("Arial", Font.PLAIN, 9));
        lblCustomerTitle.setForeground(new Color(160, 150, 190));
        lblCustomerTitle.setBounds(12, 45, 150, 12);
        add(lblCustomerTitle);

        JLabel lblCustomer = new JLabel(this.customerName);
        lblCustomer.setFont(new Font("Arial", Font.BOLD, 12));
        lblCustomer.setForeground(Color.WHITE);
        lblCustomer.setBounds(12, 57, 200, 18);
        add(lblCustomer);

        JPanel seatBox = new JPanel(new BorderLayout());
        seatBox.setBackground(new Color(45, 30, 85));
        seatBox.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 80), 1));
        seatBox.setBounds(215, 48, 77, 45);

        JLabel lblSeatTitle = new JLabel("SEAT", SwingConstants.CENTER);
        lblSeatTitle.setFont(new Font("Arial", Font.BOLD, 9));
        lblSeatTitle.setForeground(new Color(255, 204, 80));
        
        JLabel lblSeatVal = new JLabel(this.seatNumber, SwingConstants.CENTER);
        lblSeatVal.setFont(new Font("Arial", Font.BOLD, 16));
        lblSeatVal.setForeground(Color.WHITE);

        seatBox.add(lblSeatTitle, BorderLayout.NORTH);
        seatBox.add(lblSeatVal, BorderLayout.CENTER);
        add(seatBox);


    }
}