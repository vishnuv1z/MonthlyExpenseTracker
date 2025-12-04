package ui;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class IncomePage extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField sourceField, amountField, dateField;

    public IncomePage() {
        setTitle("Add Income");
        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel sourceLabel = new JLabel("Source:");
        sourceLabel.setBounds(50, 30, 100, 30);
        getContentPane().add(sourceLabel);

        sourceField = new JTextField();
        sourceField.setBounds(150, 30, 150, 30);
        getContentPane().add(sourceField);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(50, 70, 100, 30);
        getContentPane().add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(150, 70, 150, 30);
        getContentPane().add(amountField);

        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateLabel.setBounds(20, 110, 150, 30);
        getContentPane().add(dateLabel);

        dateField = new JTextField();
        dateField.setBounds(150, 110, 150, 30);
        getContentPane().add(dateField);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(60, 170, 100, 30);
        getContentPane().add(submitBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(187, 170, 100, 30);
        getContentPane().add(backBtn);

        backBtn.addActionListener(e -> {
            new DashboardPage();
            dispose();
        });

        submitBtn.addActionListener(e -> {
            String source = sourceField.getText();
            String amountText = amountField.getText();
            String date = dateField.getText();

            if (source.isEmpty() || amountText.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            try {
                double amount = Double.parseDouble(amountText);

                Connection conn = db.DBConnection.getConnection();
                String sql = "INSERT INTO mon_income(user_id, inc_source, amount, date_of_income) VALUES(?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, 1);
                ps.setString(2, source);
                ps.setDouble(3, amount);
                ps.setString(4, date);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Income Added!");
                sourceField.setText("");
                amountField.setText("");
                dateField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Amount must be a number!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}