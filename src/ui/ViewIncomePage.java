package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ViewIncomePage extends JFrame {
    private static final long serialVersionUID = 1L;

    public ViewIncomePage() {
        setTitle("View Income");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        String[] columns = {"Source", "Amount", "Date"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 550, 250);
        add(sp);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(230, 300, 120, 30);
        add(backBtn);

        backBtn.addActionListener(e -> {
            new DashboardPage();
            dispose();
        });

        loadIncomeData(model);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadIncomeData(DefaultTableModel model) {
        try {
            Connection conn = db.DBConnection.getConnection();
            String sql = "SELECT inc_source, amount, date_of_income FROM mon_income";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String source = rs.getString("inc_source");
                double amount = rs.getDouble("amount");
                Date date = rs.getDate("date_of_income");

                model.addRow(new Object[]{source, String.format("%.2f", amount), date.toString()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading income: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new ViewIncomePage();
    }
}