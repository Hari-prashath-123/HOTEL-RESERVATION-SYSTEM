import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OYOReservationSystem {
    private static ArrayList<String[]> customerDetails = new ArrayList<>(); // To store customer details

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("OYO Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout());

        // Navigation Panel
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(Color.CYAN);
        navPanel.setPreferredSize(new Dimension(200, 600));

        JLabel logoLabel = new JLabel("<html><h1>OYO</h1><p>Hotels & Resorts</p></html>", SwingConstants.CENTER);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        navPanel.add(logoLabel);

        JButton customerDetailsButton = new JButton("Customer Details");
        JButton bookingButton = new JButton("Hotel Booking");
        JButton reservationButton = new JButton("Reservation");
        JButton signOutButton = new JButton("Sign Out");

        navPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        navPanel.add(customerDetailsButton);
        navPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        navPanel.add(bookingButton);
        navPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        navPanel.add(reservationButton);
        navPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        navPanel.add(signOutButton);

        frame.add(navPanel, BorderLayout.WEST);

        // Panels for each page
        JPanel customerDetailsPanel = createCustomerDetailsPanel();
        JPanel bookingPanel = createBookingPanel();
        JPanel reservationPanel = createReservationPanel();

        // Default center panel
        frame.add(customerDetailsPanel, BorderLayout.CENTER);

        // Navigation button actions
        customerDetailsButton.addActionListener(e -> switchPanel(frame, customerDetailsPanel));
        bookingButton.addActionListener(e -> switchPanel(frame, bookingPanel));
        reservationButton.addActionListener(e -> {
            updateReservationPanel(reservationPanel);
            switchPanel(frame, reservationPanel);
        });

        signOutButton.addActionListener(e -> frame.dispose());

        // Make frame visible
        frame.setVisible(true);
    }

    private static JPanel createCustomerDetailsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel nameLabel = new JLabel("Customer Name:");
        JTextField nameField = new JTextField();

        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField();

        JLabel peopleLabel = new JLabel("Number of People:");
        JTextField peopleField = new JTextField();

        JButton saveButton = new JButton("Save Details");

        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String people = peopleField.getText();

            if (!name.isEmpty() && !phone.isEmpty() && !people.isEmpty()) {
                customerDetails.add(new String[]{name, phone, people, "Not Reserved"}); // Add default hotel status
                JOptionPane.showMessageDialog(panel, "Customer details saved successfully!");
                nameField.setText("");
                phoneField.setText("");
                peopleField.setText("");
            } else {
                JOptionPane.showMessageDialog(panel, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(peopleLabel);
        panel.add(peopleField);
        panel.add(new JLabel());
        panel.add(saveButton);

        return panel;
    }

    private static JPanel createBookingPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel headerLabel = new JLabel("Hotels Near You", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(headerLabel, BorderLayout.NORTH);

        String[] columns = {"No", "Hotel Name", "Location", "Price (INR)", "Rating"};
        Object[][] data = {
            {"1", "Hotel Paradise", "Mumbai", "3000", "4.5"},
            {"2", "Sea View Inn", "Goa", "4500", "4.2"},
            {"3", "Green Valley", "Manali", "4000", "4.6"},
            {"4", "Royal Orchid", "Bangalore", "6000", "4.8"},
            {"5", "The Grand Hyatt", "Delhi", "8500", "4.9"},
            {"6", "Le Meridien", "Chennai", "7000", "4.7"},
            {"7", "Taj Palace", "Jaipur", "7500", "4.9"},
            {"8", "Radisson Blu", "Pune", "5500", "4.6"},
            {"9", "The Oberoi", "Kolkata", "9000", "4.8"},
            {"10", "Fairmont", "Udaipur", "9500", "4.9"},
            {"11", "The Gateway Resort", "Agra", "4000", "4.7"},
            {"12", "ITC Maurya", "Delhi", "8000", "4.9"},
            {"13", "Marriott Resort", "Goa", "10000", "5.0"},
            {"14", "The Fern Residency", "Ahmedabad", "3500", "4.6"},
            {"15", "Novotel", "Hyderabad", "6500", "4.7"},
            {"16", "Trident", "Mumbai", "11000", "4.9"},
            {"17", "Lemon Tree Hotel", "Chandigarh", "5000", "4.5"},
            {"18", "The Ritz-Carlton", "Bangalore", "13000", "5.0"},
            {"19", "JW Marriott", "Pune", "9000", "4.8"},
            {"20", "Park Hyatt", "Chennai", "8500", "4.8"},
            {"21", "The Lalit", "New Delhi", "8000", "4.6"},
            {"22", "Vivanta", "Kochi", "6000", "4.7"},
            {"23", "Hotel Crown Plaza", "Amritsar", "7500", "4.7"},
            {"24", "The Golden Retreat", "Darjeeling", "4000", "4.8"},
            {"25", "Windflower Resort", "Ooty", "5500", "4.7"},
            {"26", "Neemrana Fort Palace", "Rajasthan", "7000", "4.8"},
            {"27", "The Tamara", "Coorg", "9500", "4.9"},
            {"28", "Alila Fort Bishangarh", "Rajasthan", "12000", "5.0"},
            {"29", "Club Mahindra", "Mahabaleshwar", "5000", "4.6"},
            {"30", "Comfort Stay", "Delhi", "3500", "4.3"}
        };

        JTable hotelTable = new JTable(new DefaultTableModel(data, columns));
        JScrollPane tableScrollPane = new JScrollPane(hotelTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        JButton bookButton = new JButton("Book Hotel");
        bookButton.addActionListener(e -> {
            int selectedRow = hotelTable.getSelectedRow();
            if (selectedRow != -1 && !customerDetails.isEmpty()) {
                String hotelName = hotelTable.getValueAt(selectedRow, 1).toString();
                String[] lastCustomer = customerDetails.get(customerDetails.size() - 1);
                lastCustomer[3] = hotelName; // Update the hotel name for the last customer
                JOptionPane.showMessageDialog(panel, "Room reserved successfully at " + hotelName + "!");
            } else if (customerDetails.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter customer details first.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panel, "Please select a hotel to book.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(bookButton, BorderLayout.SOUTH);

        return panel;
    }

    private static JPanel createReservationPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel headerLabel = new JLabel("Recently Booked Customers", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(headerLabel, BorderLayout.NORTH);

        String[] columns = {"No", "Name", "Phone Number", "Number of People", "Hotel Reserved"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable reservationTable = new JTable(model);
        JScrollPane tableScrollPane = new JScrollPane(reservationTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        return panel;
    }

    private static void updateReservationPanel(JPanel panel) {
        JScrollPane tableScrollPane = (JScrollPane) panel.getComponent(1);
        JTable reservationTable = (JTable) tableScrollPane.getViewport().getView();
        DefaultTableModel model = (DefaultTableModel) reservationTable.getModel();

        // Clear existing rows
        model.setRowCount(0);

        // Add updated customer details
        int count = 1;
        for (String[] customer : customerDetails) {
            model.addRow(new Object[]{count++, customer[0], customer[1], customer[2], customer[3]});
        }
    }

    private static void switchPanel(JFrame frame, JPanel newPanel) {
        frame.getContentPane().remove(1);
        frame.add(newPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }
}
