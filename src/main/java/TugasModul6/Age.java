package TugasModul6;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;

public class Age extends JFrame {

    // Components
    private JLabel lblTitle;
    private JLabel lblBirthDay;
    private JLabel lblBirthMonth;
    private JLabel lblBirthYear;
    private JTextField txtBirthDay;
    private JTextField txtBirthMonth;
    private JTextField txtBirthYear;
    private JButton btnCalculate;
    private JLabel lblResult;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public Age() {
        // Set up the frame
        setTitle("Age Calculator");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialize components for input
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        lblTitle = new JLabel("Calculate Your Age", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblBirthDay = new JLabel("Enter your birth day:");
        lblBirthMonth = new JLabel("Enter your birth month:");
        lblBirthYear = new JLabel("Enter your birth year:");
        txtBirthDay = new JTextField();
        txtBirthMonth = new JTextField();
        txtBirthYear = new JTextField();
        btnCalculate = new JButton("Calculate Age");
        lblResult = new JLabel("", SwingConstants.CENTER);

        // Set colors for the components
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBackground(Color.BLUE);
        lblTitle.setOpaque(true);

        lblBirthDay.setForeground(Color.DARK_GRAY);
        lblBirthMonth.setForeground(Color.DARK_GRAY);
        lblBirthYear.setForeground(Color.DARK_GRAY);

        txtBirthDay.setBackground(Color.LIGHT_GRAY);
        txtBirthDay.setForeground(Color.BLACK);
        txtBirthMonth.setBackground(Color.LIGHT_GRAY);
        txtBirthMonth.setForeground(Color.BLACK);
        txtBirthYear.setBackground(Color.LIGHT_GRAY);
        txtBirthYear.setForeground(Color.BLACK);

        btnCalculate.setBackground(Color.GREEN);
        btnCalculate.setForeground(Color.BLACK);

        lblResult.setForeground(Color.RED);

        // Add components to input panel
        inputPanel.add(lblTitle);
        inputPanel.add(new JLabel("")); // Placeholder
        inputPanel.add(lblBirthDay);
        inputPanel.add(txtBirthDay);
        inputPanel.add(lblBirthMonth);
        inputPanel.add(txtBirthMonth);
        inputPanel.add(lblBirthYear);
        inputPanel.add(txtBirthYear);
        inputPanel.add(btnCalculate);
        inputPanel.add(lblResult);

        // Initialize the table
        String[] columnNames = {"Birth Day", "Birth Month", "Birth Year", "Age"};
        tableModel = new DefaultTableModel(columnNames, 0);
        resultTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(resultTable);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        // Action listener for the button
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAge();
            }
        });
    }

    // Method to calculate the age
    private void calculateAge() {
        String birthDayText = txtBirthDay.getText().trim();
        String birthMonthText = txtBirthMonth.getText().trim();
        String birthYearText = txtBirthYear.getText().trim();

        if (birthDayText.isEmpty() || birthMonthText.isEmpty() || birthYearText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your full birth date!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                int birthDay = Integer.parseInt(birthDayText);
                int birthMonth = Integer.parseInt(birthMonthText);
                int birthYear = Integer.parseInt(birthYearText);

                // Create LocalDate object from input
                LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
                LocalDate currentDate = LocalDate.now();

                // Check if birthDate is valid and in the past
                if (birthDate.isAfter(currentDate)) {
                    lblResult.setText("Invalid birth date!");
                } else {
                    // Calculate the period between birth date and current date
                    Period age = Period.between(birthDate, currentDate);
                    lblResult.setText("Your age is: " + age.getYears() + " years, " +
                            age.getMonths() + " months, and " + age.getDays() + " days.");

                    // Add the result to the table
                    tableModel.addRow(new Object[]{birthDay, birthMonth, birthYear, age.getYears() + " years"});
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid date. Please check your input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Age().setVisible(true);
            }
        });
    }
}
