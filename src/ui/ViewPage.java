package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ViewPage extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTable table;
    private DefaultTableModel model;

    public ViewPage() {
        setTitle("View Expenses");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        model = new DefaultTableModel(new String[]{"Category", "Amount", "Date"}, 0);
        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 550, 250);
        add(sp);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(240, 300, 100, 30);
        add(backBtn);

        backBtn.addActionListener(e -> {
            new DashboardPage();
            dispose();
        });
        loadExpenses();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadExpenses() {
        try {
            Connection conn = db.DBConnection.getConnection();
            String sql = "SELECT category, amount, date_of_spent FROM mon_expenses";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            model.setRowCount(0);

            while (rs.next()) {
                String category = rs.getString("category");
                double amount = rs.getDouble("amount");
                String date = rs.getString("date_of_spent");

                model.addRow(new Object[]{ category, amount, date});
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading expenses: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ViewPage();
    }
}