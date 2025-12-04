package ui;
import javax.swing.*;
import java.sql.*;

public class SummaryPage extends JFrame {
    private static final long serialVersionUID = 1L;

    private JLabel incomeLabel, expenseLabel, balanceLabel;

    public SummaryPage() {
        setTitle("Monthly Summary");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        incomeLabel = new JLabel("Total Income: 0");
        incomeLabel.setBounds(100, 50, 200, 30);
        add(incomeLabel);

        expenseLabel = new JLabel("Total Expense: 0");
        expenseLabel.setBounds(100, 100, 200, 30);
        add(expenseLabel);

        balanceLabel = new JLabel("Remaining Balance: 0");
        balanceLabel.setBounds(100, 150, 250, 30);
        add(balanceLabel);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(150, 200, 100, 30);
        add(backBtn);

        backBtn.addActionListener(e -> {
            new DashboardPage();
            dispose();
        });

        loadSummary();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadSummary() {
        double totalIncome = 0, totalExpense = 0;

        try (Connection conn = db.DBConnection.getConnection()) {
            String incomeSql = "SELECT IFNULL(SUM(amount), 0) AS total_income FROM mon_income WHERE user_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(incomeSql);
            ps1.setInt(1, 1);
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
                totalIncome = rs1.getDouble("total_income");
            }

            String expenseSql = "SELECT IFNULL(SUM(amount), 0) AS total_expense FROM mon_expenses WHERE user_id = ?";
            PreparedStatement ps2 = conn.prepareStatement(expenseSql);
            ps2.setInt(1, 1);
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) {
                totalExpense = rs2.getDouble("total_expense");
            }

            incomeLabel.setText("Total Income: " + String.format("%.2f", totalIncome));
            expenseLabel.setText("Total Expense: " + String.format("%.2f", totalExpense));
            balanceLabel.setText("Remaining Balance: " + String.format("%.2f", (totalIncome - totalExpense)));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SummaryPage();
    }
}