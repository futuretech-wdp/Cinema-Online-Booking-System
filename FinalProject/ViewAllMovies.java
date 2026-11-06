package FinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ViewAllMovies extends JFrame
        implements ActionListener, MouseListener {

    JPanel framep = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel moviesMenuPanel = new JPanel(); // Panel para sa View All Movies highlight
    JPanel logoutPanel = new JPanel(null);
    JPanel categoryPanel = new JPanel();
    JPanel moviesPanel = new JPanel();

    JLabel dashboardMenu = new JLabel("Dashboard");
    JLabel moviesMenu = new JLabel("View All Movies");
    JLabel logout = new JLabel("Back");

    JLabel pageTitle = new JLabel("EXPLORE ALL MOVIES");
    JLabel subtitle = new JLabel("Find your next favorite movie");

    JTextField searchField = new JTextField();

    Color textColor = Color.WHITE;
    Color activeColor = new Color(169, 112, 255);
    Color dashBg = new Color(65, 35, 105);
    Color logoutColor = new Color(190, 150, 255);
    Color logoutBg = new Color(35, 24, 58);
    Color darkpurple = new Color(121, 37, 245);
    Color bg = new Color(8, 6, 25);
    Color cardColor = new Color(16, 11, 43);

    Font regularFont = new Font("Arial", Font.PLAIN, 16);
    Font boldFont = new Font("Arial", Font.BOLD, 18);

    String selectedCategory = "ALL MOVIES";

    String[] categories = {
        "ALL MOVIES", "ACTION", "HORROR", "ROMANCE",
        "ANIMATION", "SCI-FI", "DRAMA", "COMING SOON"
    };

    String[] titles = {
        "Demon Slayer: Infinity Castle Movie",
        "Avengers: Endgame Encore",
        "Other Mommy",
        "Verity",
        "Always Yours, Never Mine",
        "Chainsaw Man: Reze Arc",
        "Suzume",
        "Your Name",
        "Jujutsu Kaisen 0",
        "Spider-Man: No Way Home",
        "Doctor Strange in the Multiverse of Madness",
        "Interstellar",
        "Dune: Part Two",
        "Five Nights at Freddy's",
        "The Conjuring",
        "Insidious",
        "Smile",
        "A Quiet Place",
        "The Notebook",
        "Titanic"
    };

    String[] genres = {
        "Animation / Action",
        "Action / Sci-Fi",
        "Horror / Thriller",
        "Thriller / Drama",
        "Romance / Drama",
        "Animation / Action",
        "Animation / Fantasy",
        "Romance / Fantasy",
        "Animation / Action",
        "Action / Adventure",
        "Action / Fantasy",
        "Sci-Fi / Drama",
        "Sci-Fi / Adventure",
        "Horror",
        "Horror",
        "Horror",
        "Horror / Thriller",
        "Horror / Thriller",
        "Romance / Drama",
        "Romance / Drama"
    };

    String[] descriptions = {
        "Tanjiro and the Demon Slayer Corps enter the Infinity Castle to face powerful demons.",
        "The Avengers reunite for one final mission to save the universe.",
        "A mysterious presence brings fear into a family's home.",
        "A woman becomes involved in a disturbing mystery.",
        "A heartfelt love story about fate and difficult choices.",
        "Denji faces a new enemy and discovers unexpected feelings.",
        "A girl travels through mysterious doors across Japan.",
        "Two strangers become connected through a strange phenomenon.",
        "Yuta enters a world of curses and dangerous battles.",
        "Peter Parker faces challenges after his identity is revealed.",
        "Doctor Strange explores dangerous alternate realities.",
        "A team travels through space to search for a new home.",
        "A young heir faces danger on the desert planet Arrakis.",
        "A security guard discovers terrifying animatronics.",
        "A family experiences frightening supernatural events.",
        "A woman experiences mysterious supernatural events.",
        "A disturbing curse follows its victims.",
        "A family struggles to survive in a world of dangerous creatures.",
        "Two people discover a love that changes their lives.",
        "A romance develops between two people from different backgrounds."
    };

    int[] prices = {
        250, 300, 200, 280, 350,
        500, 450, 400, 450, 500,
        500, 450, 500, 450, 450,
        450, 450, 450, 400, 400
    };

    String[] posterPaths = {
        "C:\\Users\\Rance\\Downloads\\Gemini_Generated_Image_4l956f4l956f4l95.jpg",
        "C:\\Users\\Rance\\Downloads\\836806461_1135594892259624_3995803387842524777_n.jpg",
        "C:\\Users\\Rance\\Downloads\\839999399_1070276262489290_1135975094415710158_n.jpg",
        "C:\\Users\\Rance\\Downloads\\840133069_3464518343706012_3946572089338619474_n.jpg",
        "C:\\Users\\Rance\\Downloads\\841139221_2224286718140401_4716361561376387909_n.jpg",
        "C:\\Users\\Rance\\Downloads\\849476708_1317728507034644_4147198608372482573_n.jpg",
        "C:\\Users\\Rance\\Downloads\\846427451_1130000693053892_4856660570076342298_n.jpg",
        "C:\\Users\\Rance\\Downloads\\848831424_1112106514649114_4191746931709985966_n.jpg",
        "C:\\Users\\Rance\\Downloads\\854181360_28661651693443941_2328845126794239763_n.jpg",
        "C:\\Users\\Rance\\Downloads\\852865650_1557333246151944_26261537280486031_n.jpg",
        "C:\\Users\\Rance\\Downloads\\848510238_1109863638544804_4391303776902137139_n.jpg",
        "C:\\Users\\Rance\\Downloads\\846391543_1427308899533950_7823727584898684730_n.jpg",
        "C:\\Users\\Rance\\Downloads\\845879917_1692869902468392_8872547906300482551_n.jpg",
        "C:\\Users\\Rance\\Downloads\\845551938_1780580380741891_2557826424304002919_n.jpg",
        "C:\\Users\\Rance\\Downloads\\848971797_891875240680331_3244437641958452945_n.jpg",
        "C:\\Users\\Rance\\Downloads\\850370663_1444348330954100_8973964912202356877_n.jpg",
        "C:\\Users\\Rance\\Downloads\\847584196_2559628634548907_2293133789499966970_n.jpg",
        "C:\\Users\\Rance\\Downloads\\846085182_29409751151942168_2970503868444264818_n.jpg",
        "C:\\Users\\Rance\\Downloads\\847828096_1047122905042510_7935455681243735201_n.jpg",
        "C:\\Users\\Rance\\Downloads\\849193688_971374046027511_6477770896134859389_n.jpg"
    };

    String[] comingSoonMovies = {
        "Doctor Strange in the Multiverse of Madness",
        "Insidious",
        "Smile",
        "The Notebook",
        "Dune: Part Two"
    };

    ViewAllMovies() {

        setTitle("CineVibe - View All Movies");
        setLayout(null);
        setSize(1500, 850);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // MAIN PANEL
        framep.setBounds(0, 0, 1500, 850);
        framep.setLayout(null);
        framep.setBackground(bg);
        add(framep);

        // SIDEBAR
        panel1.setBackground(new Color(13, 9, 36));
        panel1.setBounds(0, 0, 250, 850);
        panel1.setLayout(null);
        framep.add(panel1);

        // SIDEBAR BACKGROUND IMAGE
        ImageIcon ico = new ImageIcon("C:\\Users\\Rance\\Downloads\\Gemini_Generated_Image_6wvo1f6wvo1f6wvo.jpg");

        Image scal = ico.getImage().getScaledInstance(250, 850, Image.SCALE_SMOOTH);

        JLabel sidebar = new JLabel(new ImageIcon(scal));
        sidebar.setBounds(0, 0, 250, 850);
        panel1.add(sidebar);

        // LOGO
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\Rance\\Downloads\\cinevibe_logo_crop.png");

        Image logoImage = logoIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);

        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setBounds(20, 80, 200, 100);
        panel1.add(logoLabel);

        // DASHBOARD (Plain Text na lang kasi nasa View All Movies tayo)
        dashboardMenu.setBounds(30, 220, 190, 40);
        dashboardMenu.setFont(regularFont);
        dashboardMenu.setForeground(textColor);
        dashboardMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel1.add(dashboardMenu);
      
        moviesMenuPanel.setBounds(15, 275, 220, 50);
        moviesMenuPanel.setBackground(dashBg);
        moviesMenuPanel.setLayout(null);
        panel1.add(moviesMenuPanel);

        moviesMenu.setForeground(new Color(200, 170, 255));
        moviesMenu.setFont(boldFont);
        moviesMenu.setBounds(15, 5, 190, 40);
        moviesMenuPanel.add(moviesMenu);

        // LOGOUT PANEL
        logoutPanel.setBounds(20, 750, 210, 48);
        logoutPanel.setBackground(logoutBg);
        logoutPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutPanel.setBorder(BorderFactory.createLineBorder(new Color(121, 37, 245), 1));
        panel1.add(logoutPanel);

        logout.setFont(new Font("Arial", Font.BOLD, 16));
        logout.setForeground(logoutColor);
        logout.setBounds(15, 5, 180, 38);
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutPanel.add(logout);

        // SIDEBAR MOUSE LISTENERS
        dashboardMenu.addMouseListener(this);
        logout.addMouseListener(this);
        logoutPanel.addMouseListener(this);

        // KEEP BACKGROUND BEHIND SIDEBAR COMPONENTS
        panel1.setComponentZOrder(sidebar, panel1.getComponentCount() - 1);

        // KEEP LOGO ABOVE BACKGROUND
        panel1.setComponentZOrder(logoLabel, 0);

        // PAGE TITLE
        pageTitle.setBounds(280, 20, 500, 50);
        pageTitle.setForeground(Color.WHITE);
        pageTitle.setFont(new Font("Arial", Font.BOLD, 28));
        framep.add(pageTitle);

        subtitle.setBounds(280, 65, 500, 25);
        subtitle.setForeground(new Color(180, 170, 200));
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        framep.add(subtitle);

        // SEARCH FIELD
        searchField.setBounds(1050, 30, 370, 40);
        searchField.setFont(new Font("Arial", Font.PLAIN, 15));
        searchField.setBackground(new Color(25, 19, 48));
        searchField.setForeground(Color.WHITE);
        searchField.setCaretColor(Color.WHITE);
        searchField.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(activeColor),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
            )
        );
        framep.add(searchField);

        searchField.getDocument().addDocumentListener(
            new javax.swing.event.DocumentListener() {

                public void insertUpdate(
                    javax.swing.event.DocumentEvent e
                ) {
                    showMovies();
                }

                public void removeUpdate(
                    javax.swing.event.DocumentEvent e
                ) {
                    showMovies();
                }

                public void changedUpdate(
                    javax.swing.event.DocumentEvent e
                ) {
                    showMovies();
                }
            }
        );

        // CATEGORY PANEL
        categoryPanel.setBounds(280, 105, 1150, 45);
        categoryPanel.setLayout(
            new FlowLayout(FlowLayout.LEFT, 10, 5)
        );
        categoryPanel.setBackground(bg);

        for (String category : categories) {

            JButton categoryBtn = new JButton(category);
            categoryBtn.setFont(new Font("Arial", Font.BOLD, 11));
            categoryBtn.setForeground(Color.WHITE);
            categoryBtn.setBackground(cardColor);
            categoryBtn.setFocusPainted(false);
            categoryBtn.setBorderPainted(false);

            categoryBtn.addActionListener(e -> {
                selectedCategory = category;
                showMovies();
            });

            categoryPanel.add(categoryBtn);
        }

        framep.add(categoryPanel);

        // MOVIES PANEL - FIVE MOVIES PER ROW
        moviesPanel.setBackground(bg);
        moviesPanel.setLayout(
            new GridLayout(0, 5, 15, 15)
        );

        JScrollPane scrollPane = new JScrollPane(moviesPanel);
        scrollPane.setBounds(280, 160, 1150, 650);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPane.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );
        scrollPane.getViewport().setBackground(bg);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        framep.add(scrollPane);

        showMovies();

        setVisible(true);
    }

    // CHECK IF MOVIE IS COMING SOON
    public boolean isComingSoon(String movieName) {

        for (String movie : comingSoonMovies) {

            if (movie.equals(movieName)) {
                return true;
            }
        }

        return false;
    }

    // DISPLAY MOVIES
    public void showMovies() {

        moviesPanel.removeAll();

        String searchText = searchField.getText().toLowerCase();

        for (int i = 0; i < titles.length; i++) {

            boolean matchesSearch = titles[i].toLowerCase().contains(searchText);

            boolean matchesCategory = false;

            if (selectedCategory.equals("ALL MOVIES")) {

                matchesCategory = true;

            } else if (selectedCategory.equals("COMING SOON")) {

                matchesCategory = isComingSoon(titles[i]);

            } else {

                matchesCategory = genres[i].toUpperCase().contains(selectedCategory);
            }

            if (matchesSearch && matchesCategory) {

                final int movieIndex = i;

                // MOVIE CARD
                JPanel movieCard = new JPanel();
                movieCard.setLayout(
                    new BoxLayout(movieCard, BoxLayout.Y_AXIS)
                );
                movieCard.setBackground(cardColor);
                movieCard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                movieCard.setPreferredSize(new Dimension(205, 300));

                // POSTER
                ImageIcon posterIcon = new ImageIcon(posterPaths[i]);

                Image posterImage = posterIcon.getImage()
                    .getScaledInstance(
                        180, 175, Image.SCALE_SMOOTH
                    );

                JLabel posterLabel = new JLabel(new ImageIcon(posterImage));
                posterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                movieCard.add(posterLabel);

                movieCard.add(Box.createVerticalStrut(8));

                // MOVIE TITLE
                JLabel titleLabel = new JLabel("<html><center>" + titles[i] + "</center></html>");
                titleLabel.setForeground(Color.WHITE);
                titleLabel.setFont(new Font("Arial", Font.BOLD, 13));
                titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
                titleLabel.setPreferredSize(new Dimension(180, 40));
                titleLabel.setMaximumSize(new Dimension(180, 40));
                titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                movieCard.add(titleLabel);

                // GENRE
                JLabel genreLabel = new JLabel(genres[i]);
                genreLabel.setForeground(activeColor);
                genreLabel.setFont(new Font("Arial", Font.PLAIN, 10));
                genreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                movieCard.add(genreLabel);
                movieCard.add(Box.createVerticalStrut(8));

                // BOOK NOW BUTTON
                JButton bookBtn;

                if (isComingSoon(titles[i])) {

                    bookBtn = new JButton("COMING SOON");
                    bookBtn.setEnabled(false);
                    bookBtn.setBackground(new Color(55, 45, 70));
                    bookBtn.setForeground(Color.LIGHT_GRAY);

                } else {

                    bookBtn = new JButton("BOOK NOW");
                    bookBtn.setBackground(darkpurple);
                    bookBtn.setForeground(Color.WHITE);

                    bookBtn.addActionListener(e -> {

                        new BookSeat(
                            posterPaths[movieIndex],
                            titles[movieIndex],
                            genres[movieIndex],
                            "N/A",
                            descriptions[movieIndex],
                            prices[movieIndex]
                        );

                        dispose();
                    });
                }

                bookBtn.setFont(
                    new Font("Arial", Font.BOLD, 11)
                );
                bookBtn.setFocusPainted(false);
                bookBtn.setBorderPainted(false);
                bookBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                bookBtn.setMaximumSize(new Dimension(180, 30));
                bookBtn.setPreferredSize(new Dimension(180, 30));

                movieCard.add(bookBtn);

                moviesPanel.add(movieCard);
            }
        }

        moviesPanel.revalidate();
        moviesPanel.repaint();
    }

    // SIDEBAR CLICK ACTIONS
    @Override
    public void mouseClicked(MouseEvent e) {

        Object source = e.getSource();

        if (source == dashboardMenu) {

            new DashBoard();
            dispose();

        } else if (source == logout || source == logoutPanel) {

                dispose();
                new LandingPage().setVisible(true);
            
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        Object source = e.getSource();

        if (source == dashboardMenu) {

            dashboardMenu.setForeground(activeColor);

        } else if (source == logout || source == logoutPanel) {

            logoutPanel.setBackground(new Color(75, 35, 120));
            logout.setForeground(Color.WHITE);

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        Object source = e.getSource();

        if (source == dashboardMenu) {

            dashboardMenu.setForeground(textColor);

        } else if (source == logout || source == logoutPanel) {

            logoutPanel.setBackground(logoutBg);
            logout.setForeground(logoutColor);

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new ViewAllMovies();
        });
    }
}