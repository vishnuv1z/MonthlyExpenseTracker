package ui;
import javax.swing.*;
import java.sql.*;

public class ExpensePage extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField amountField;
    private JTextField categoryField;
    private JTextField dateField;

    public ExpensePage() {
        setTitle("Add Expense");
        setSize(400, 350);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(50, 50, 120, 30);
        getContentPane().add(categoryLabel);

        categoryField = new JTextField();
        categoryField.setBounds(180, 50, 150, 30);
        getContentPane().add(categoryField);

        JLabel amountLabel = new JLabel("Expense Amount:");
        amountLabel.setBounds(50, 100, 120, 30);
        getContentPane().add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(180, 100, 150, 30);
        getContentPane().add(amountField);

        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateLabel.setBounds(50, 150, 150, 30);
        getContentPane().add(dateLabel);

        dateField = new JTextField();
        dateField.setBounds(180, 150, 150, 30);
        getContentPane().add(dateField);

        JButton saveBtn = new JButton("Save");
        saveBtn.setBounds(50, 200, 100, 30);
        getContentPane().add(saveBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(200, 200, 100, 30);
        getContentPane().add(backBtn);

        saveBtn.addActionListener(e -> saveExpense());
        
        backBtn.addActionListener(e -> {
            new DashboardPage();
            dispose();
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void saveExpense() {
        String amountText = amountField.getText();
        String category = categoryField.getText();
        String dateText = dateField.getText();

        if (amountText.isEmpty() || category.isEmpty() || dateText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            int user_id = 1;

            Connection conn = db.DBConnection.getConnection();
            java.sql.Date date = java.sql.Date.valueOf(dateText);
            String sql = "INSERT INTO mon_expenses (category, amount, date_of_spent, user_id) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, category);
            pst.setDouble(2, amount);
            pst.setDate(3, date);
            pst.setInt(4, user_id);

            int row = pst.executeUpdate();
            if (row > 0) {
                JOptionPane.showMessageDialog(this, "Expense added successfully!");
                amountField.setText("");
                categoryField.setText("");
                dateField.setText("");
            }

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Amount must be a valid number!");
        } catch (IllegalArgumentException iae) {
            JOptionPane.showMessageDialog(this, "Date must be in YYYY-MM-DD format!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new ExpensePage();
    }
}