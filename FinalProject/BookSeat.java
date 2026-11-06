package FinalProject;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BookSeat extends JFrame {

    String movieTitle;
    String movieGenre;
    String movieRating;
    String movieDescription;
    String imagePath;
    int ticketPrice;

    JComboBox<String> datePicker = new JComboBox<>();
    JComboBox<String> timePicker = new JComboBox<>();

    JPanel seatPanel = new JPanel();
    JPanel ticketPanel = new JPanel();

    JLabel movieTitleLabel;
    JLabel genreLabel;
    JLabel ratingLabel;
    JLabel priceLabel;
    JLabel posterLabel;

    JTextArea descriptionArea;
    JTextArea selectedSeatArea;

    JLabel totalLabel;

    JButton confirmButton = new JButton("CONFIRM BOOKING");
    JButton backButton = new JButton("BACK TO DASHBOARD");

    ArrayList<String> selectedSeats = new ArrayList<>();
    Set<String> bookedSeats = new HashSet<>();

    final String SCHEDULE_FILE = "movie_schedules.txt";
    final String BOOKINGS_FILE = "booked_seats.txt";

    final Color BACKGROUND = new Color(8, 6, 25);
    final Color PANEL_COLOR = new Color(15, 18, 42);
    final Color PURPLE = new Color(100, 65, 190);

    final Color AVAILABLE_COLOR = new Color(55, 45, 85);
    final Color SELECTED_COLOR = new Color(145, 95, 225);
    final Color BOOKED_COLOR = new Color(75, 70, 90);

    public BookSeat(
            String imagePath,
            String title,
            String genre,
            String rating,
            String descriptions,
            int price) {

        this.imagePath = imagePath;
        this.movieTitle = title;
        this.movieGenre = genre;
        this.movieRating = rating;
        this.movieDescription = descriptions;
        this.ticketPrice = price;

        setTitle("Book Your Seats");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(BACKGROUND);

        createMovieDetails();
        createScheduleControls();
        createSeatLayout();
        createTicketPanel();

        loadMovieSchedule();

        if (datePicker.getItemCount() == 0
                || timePicker.getItemCount() == 0) {
            return;
        }

        loadBookedSeats();
        refreshSeats();

        setVisible(true);

    }

    // MOVIE DETAILS
    private void createMovieDetails() {

        JPanel moviePanel = new JPanel();
        moviePanel.setBounds(20, 20, 350, 720);
        moviePanel.setLayout(null);
        moviePanel.setBackground(PANEL_COLOR);
        moviePanel.setBorder(
                BorderFactory.createLineBorder(PURPLE)
        );
        add(moviePanel);

        posterLabel = new JLabel();
        posterLabel.setBounds(25, 20, 300, 250);
        posterLabel.setHorizontalAlignment(SwingConstants.CENTER);

        if (imagePath != null && !imagePath.isEmpty()) {

            ImageIcon icon = new ImageIcon(imagePath);

            if (icon.getIconWidth() > 0) {

                Image image = icon.getImage().getScaledInstance(
                        300, 250, Image.SCALE_SMOOTH
                );

                posterLabel.setIcon(new ImageIcon(image));

            } else {

                posterLabel.setText("Movie Poster");
                posterLabel.setForeground(Color.WHITE);
            }

        } else {

            posterLabel.setText("Movie Poster");
            posterLabel.setForeground(Color.WHITE);
        }

        moviePanel.add(posterLabel);

        movieTitleLabel = new JLabel(movieTitle);
        movieTitleLabel.setBounds(20, 285, 310, 45);
        movieTitleLabel.setFont(new Font("Arial", Font.BOLD, 19));
        movieTitleLabel.setForeground(Color.WHITE);
        moviePanel.add(movieTitleLabel);

        genreLabel = new JLabel("Genre: " + movieGenre);
        genreLabel.setBounds(20, 335, 310, 25);
        genreLabel.setForeground(Color.WHITE);
        moviePanel.add(genreLabel);

        ratingLabel = new JLabel("Rating: " + movieRating);
        ratingLabel.setBounds(20, 365, 310, 25);
        ratingLabel.setForeground(Color.WHITE);
        moviePanel.add(ratingLabel);

        priceLabel = new JLabel("Ticket Price: ₱" + ticketPrice);
        priceLabel.setBounds(20, 395, 310, 25);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        priceLabel.setForeground(new Color(210, 185, 255));
        moviePanel.add(priceLabel);

        JLabel descriptionLabel = new JLabel("Movie Description");
        descriptionLabel.setBounds(20, 435, 300, 25);
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        descriptionLabel.setForeground(Color.WHITE);
        moviePanel.add(descriptionLabel);

        descriptionArea = new JTextArea(movieDescription);
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionArea.setBackground(PANEL_COLOR);
        descriptionArea.setForeground(Color.LIGHT_GRAY);

        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);

        descriptionScroll.setBounds(20, 465, 305, 220);
        descriptionScroll.setBorder(null);
        descriptionScroll.getViewport().setBackground(PANEL_COLOR);

        moviePanel.add(descriptionScroll);
    }

    // DATE AND TIME DROPDOWNS
    private void createScheduleControls() {

        JLabel scheduleTitle = new JLabel("SELECT SCHEDULE");
        scheduleTitle.setBounds(390, 20, 350, 35);
        scheduleTitle.setFont(new Font("Arial", Font.BOLD, 23));
        scheduleTitle.setForeground(Color.WHITE);
        add(scheduleTitle);

        JLabel dateLabel = new JLabel("Select Date:");
        dateLabel.setBounds(390, 65, 130, 25);
        dateLabel.setForeground(Color.WHITE);
        add(dateLabel);

        datePicker.setBounds(390, 95, 200, 35);
        datePicker.setFont(new Font("Arial", Font.PLAIN, 14));
        add(datePicker);

        JLabel timeLabel = new JLabel("Select Time:");
        timeLabel.setBounds(620, 65, 130, 25);
        timeLabel.setForeground(Color.WHITE);
        add(timeLabel);

        timePicker.setBounds(620, 95, 200, 35);
        timePicker.setFont(new Font("Arial", Font.PLAIN, 14));
        add(timePicker);

        datePicker.addActionListener(e -> {
            loadBookedSeats();
            refreshSeats();
        });

        timePicker.addActionListener(e -> {
            loadBookedSeats();
            refreshSeats();
        });
    }

   
    private void loadMovieSchedule() {

        datePicker.removeAllItems();
        timePicker.removeAllItems();

        File file = new File(SCHEDULE_FILE);

        if (!file.exists()) {
            JOptionPane.showMessageDialog(
                    this,
                    "No movie schedules found. Please contact the admin."
            );
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length != 4) {
                    continue;
                }

                if (!data[0].equals(movieTitle)) {
                    continue;
                }

                LocalDate start = LocalDate.parse(data[1]);
                LocalDate end = LocalDate.parse(data[2]);

                for (LocalDate date = start;
                        !date.isAfter(end);
                        date = date.plusDays(1)) {

                    datePicker.addItem(date.toString());
                }

                String[] times = data[3].split(",");

                for (String time : times) {

                    String cleanTime = time.trim();

                    if (!cleanTime.isEmpty()) {
                        timePicker.addItem(cleanTime);
                    }
                }

                return;
            }

            JOptionPane.showMessageDialog(
                    this,
                    "No schedule is available for " + movieTitle + "."
            );
            

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load the movie schedule."
            );
        }
    }

    // SEAT LAYOUT
    private void createSeatLayout() {

        JLabel seatTitle = new JLabel("SELECT YOUR SEATS");
        seatTitle.setBounds(390, 150, 350, 30);
        seatTitle.setFont(new Font("Arial", Font.BOLD, 20));
        seatTitle.setForeground(Color.WHITE);
        add(seatTitle);

        JLabel screenLabel = new JLabel("SCREEN");
        screenLabel.setBounds(390, 190, 470, 30);
        screenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        screenLabel.setFont(new Font("Arial", Font.BOLD, 16));
        screenLabel.setForeground(Color.WHITE);
        screenLabel.setOpaque(true);
        screenLabel.setBackground(new Color(65, 48, 100));
        add(screenLabel);

        seatPanel.setBounds(390, 235, 470, 345);
        seatPanel.setLayout(new GridLayout(6, 8, 10, 10));
        seatPanel.setBackground(BACKGROUND);
        add(seatPanel);

 
        addLegend("Available", AVAILABLE_COLOR, 390, 600);
        addLegend("Selected", SELECTED_COLOR, 535, 600);
        addLegend("Booked", BOOKED_COLOR, 675, 600);
    }

    private void addLegend(
            String text,
            Color color,
            int x,
            int y) {

        JPanel colorBox = new JPanel();
        colorBox.setBounds(x, y + 3, 18, 18);
        colorBox.setBackground(color);
        add(colorBox);

        JLabel label = new JLabel(text);
        label.setBounds(x + 25, y, 100, 25);
        label.setForeground(Color.WHITE);
        add(label);
    }

    // CREATE SEAT BUTTONS
    private void refreshSeats() {

        seatPanel.removeAll();

        String selectedDate = (String) datePicker.getSelectedItem();

        String selectedTime = (String) timePicker.getSelectedItem();

        for (char row = 'A'; row <= 'F'; row++) {
            for (int number = 1; number <= 8; number++) {

                String seatNumber = row + String.valueOf(number);

                JButton seatButton = new JButton(seatNumber);

                seatButton.setFont(new Font("Arial", Font.BOLD, 15));
                seatButton.setPreferredSize(new Dimension(48, 45));

                seatButton.setMargin(new Insets(4, 4, 4, 4));

                seatButton.setFocusPainted(false);
                seatButton.setOpaque(true);

                boolean isBooked = bookedSeats.contains(seatNumber);

                boolean isSelected = selectedSeats.contains(seatNumber);

                if (isBooked) {

                    seatButton.setBackground(BOOKED_COLOR);
                    seatButton.setForeground(Color.WHITE);
                    seatButton.setEnabled(false);

                } else if (isSelected) {

                    seatButton.setBackground(SELECTED_COLOR);
                    seatButton.setForeground(Color.WHITE);

                } else {

                    seatButton.setBackground(AVAILABLE_COLOR);
                    seatButton.setForeground(Color.WHITE);
                }
                seatButton.addActionListener(e -> {

                    if (selectedDate == null || selectedTime == null) {

                        JOptionPane.showMessageDialog(this,"Please select an available date and time.");
                        return;
                    }

                    if (selectedSeats.contains(seatNumber)) {
                        selectedSeats.remove(seatNumber);
                    } else {
                        selectedSeats.add(seatNumber);
                    }

                    updateTicketPanel();
                    refreshSeats();
                });
                seatPanel.add(seatButton);
            }
        }

        seatPanel.revalidate();
        seatPanel.repaint();
    }

    // TICKET PANEL
    private void createTicketPanel() {
        ticketPanel.setBounds(880, 150, 280, 450);
        ticketPanel.setLayout(null);
        ticketPanel.setBackground(PANEL_COLOR);
        ticketPanel.setBorder(BorderFactory.createLineBorder(PURPLE));
        add(ticketPanel);

        JLabel ticketTitle = new JLabel("CHOOSED SEATS");
        ticketTitle.setBounds(20, 15, 240, 30);
        ticketTitle.setFont(new Font("Arial", Font.BOLD, 19));
        ticketTitle.setForeground(Color.WHITE);
        ticketPanel.add(ticketTitle);

        selectedSeatArea = new JTextArea();
        selectedSeatArea.setEditable(false);
        selectedSeatArea.setLineWrap(true);
        selectedSeatArea.setWrapStyleWord(true);
        selectedSeatArea.setFont(new Font("Arial", Font.PLAIN, 14));
        selectedSeatArea.setBackground(PANEL_COLOR);
        selectedSeatArea.setForeground(Color.WHITE);
        selectedSeatArea.setText("No seats selected.");

        JScrollPane ticketScroll = new JScrollPane(selectedSeatArea);

        ticketScroll.setBounds(20, 55, 240, 220);
        ticketScroll.setBorder(
                BorderFactory.createLineBorder(
                        new Color(65, 48, 125)
                )
        );
        ticketScroll.getViewport().setBackground(PANEL_COLOR);
        ticketPanel.add(ticketScroll);

        totalLabel = new JLabel("Total: ₱0");
        totalLabel.setBounds(20, 285, 240, 30);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setForeground(Color.WHITE);
        ticketPanel.add(totalLabel);

        confirmButton.setBounds(20, 325, 240, 40);
        confirmButton.setBackground(PURPLE);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(new Font("Arial", Font.BOLD, 13));
        confirmButton.setFocusPainted(false);     
        ticketPanel.add(confirmButton);
        
        backButton.setBounds(20, 375, 240, 35);
        backButton.setBackground(new Color(35, 28, 60));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 12));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createLineBorder(PURPLE));
        ticketPanel.add(backButton);
        
        confirmButton.addActionListener(e -> confirmBooking());
        backButton.addActionListener(e -> goBackToDashboard());
    }

    // UPDATE SELECTED TICKETS AND TOTAL
    private void updateTicketPanel() {

        if (selectedSeats.isEmpty()) {
            selectedSeatArea.setText("No seats selected.");

        } else {
            StringBuilder text = new StringBuilder();

            for (String seat : selectedSeats) {
                text.append("Seat: ")
                        .append(seat)
                        .append(" - ₱")
                        .append(ticketPrice)
                        .append("\n");
            }

            selectedSeatArea.setText(text.toString());
        }

        int total = selectedSeats.size() * ticketPrice;

        totalLabel.setText("Total: ₱" + total);
    }

    // LOAD BOOKED SEATS FOR SELECTED SCREENING
    private void loadBookedSeats() {

        bookedSeats.clear();

        String selectedDate = (String) datePicker.getSelectedItem();

        String selectedTime = (String) timePicker.getSelectedItem();

        if (selectedDate == null || selectedTime == null) {
            selectedSeats.clear();
            updateTicketPanel();
            return;
        }

        File file = new File(BOOKINGS_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|", -1);
  
                if (data.length == 4
                        && data[0].equals(movieTitle)
                        && data[1].equals(selectedDate)
                        && data[2].equals(selectedTime)) {

                    bookedSeats.add(data[3]);
                }
            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load booked seats."
            );
        }

        selectedSeats.removeIf(seat -> bookedSeats.contains(seat));

        updateTicketPanel();
    }

    // CONFIRM BOOKING
    private void confirmBooking() {

        String selectedDate= (String) datePicker.getSelectedItem();

        String selectedTime = (String) timePicker.getSelectedItem();

        if (selectedDate == null || selectedTime == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "No schedule available for this movie."
            );
            return;
        }

        if (selectedSeats.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select at least one seat."
            );
            return;
        }

        loadBookedSeats();

        for (String seat : selectedSeats) {
            if (bookedSeats.contains(seat)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Seat " + seat
                        + " is already booked. Please select another seat."
                );

                refreshSeats();
                return;
            }
        }
        

        UserBooking booking = new UserBooking(
                movieTitle,
                ticketPrice,
                new java.util.LinkedHashSet<>(selectedSeats),
                selectedDate,
                selectedTime
        );

        booking.setVisible(true);
        dispose();
    }

    // BACK TO CUSTOMER DASHBOARD
    private void goBackToDashboard() {

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to return to the dashboard?",
                "Back to Dashboard",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            dispose();
            new DashBoard();

        }
    }
}
