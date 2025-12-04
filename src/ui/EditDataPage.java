package ui;
import javax.swing.*;
import java.sql.*;

public class EditDataPage extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField nameField;

    public EditDataPage() {
        setTitle("Edit Income/Expenses");
        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel nameLabel = new JLabel("Enter Name/Category:");
        nameLabel.setBounds(100, 20, 200, 25);
        getContentPane().add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 45, 180, 25);
        getContentPane().add(nameField);

        JButton deleteIncBtn = new JButton("Delete Monthly Income");
        deleteIncBtn.setBounds(101, 80, 180, 30);
        getContentPane().add(deleteIncBtn);

        JButton deleteExpBtn = new JButton("Delete Monthly Expense");
        deleteExpBtn.setBounds(101, 125, 180, 30);
        getContentPane().add(deleteExpBtn);
        
        JButton backBtn = new JButton("Back");
        backBtn.setBounds(149, 189, 89, 23);
        getContentPane().add(backBtn);
        deleteIncBtn.addActionListener(e -> deleteRecord("mon_income", "inc_source"));
        deleteExpBtn.addActionListener(e -> deleteRecord("mon_expenses", "category"));

        backBtn.addActionListener(e -> {
            new DashboardPage();
            dispose();
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void deleteRecord(String tableName, String columnName) {
        String value = nameField.getText().trim();
        if (value.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a value!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this ?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            Connection conn = db.DBConnection.getConnection();
            String query = "DELETE FROM " + tableName + " WHERE " + columnName + "=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, value);
            int result = ps.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Deleted successfully!");
                nameField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "No record found with this value!");
            }
            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}