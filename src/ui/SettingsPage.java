package ui;
import javax.swing.*;
import java.sql.*;

public class SettingsPage extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField oldUserField, newUserField;
    private JPasswordField oldPassField, newPassField;

    public SettingsPage() {
        setTitle("Settings - Update Credentials");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel oldUserLabel = new JLabel("Old Username:");
        oldUserLabel.setBounds(50, 40, 120, 30);
        add(oldUserLabel);

        oldUserField = new JTextField();
        oldUserField.setBounds(180, 40, 150, 30);
        add(oldUserField);

        JLabel oldPassLabel = new JLabel("Old Password:");
        oldPassLabel.setBounds(50, 80, 120, 30);
        add(oldPassLabel);

        oldPassField = new JPasswordField();
        oldPassField.setBounds(180, 80, 150, 30);
        add(oldPassField);

        JLabel newUserLabel = new JLabel("New Username:");
        newUserLabel.setBounds(50, 120, 120, 30);
        add(newUserLabel);

        newUserField = new JTextField();
        newUserField.setBounds(180, 120, 150, 30);
        add(newUserField);

        JLabel newPassLabel = new JLabel("New Password:");
        newPassLabel.setBounds(50, 160, 120, 30);
        add(newPassLabel);

        newPassField = new JPasswordField();
        newPassField.setBounds(180, 160, 150, 30);
        add(newPassField);

        JButton updateBtn = new JButton("Update");
        updateBtn.setBounds(70, 210, 100, 30);
        add(updateBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(200, 210, 100, 30);
        add(backBtn);

        updateBtn.addActionListener(e -> updateCredentials());
        backBtn.addActionListener(e -> {
            new DashboardPage();
            dispose();
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void updateCredentials() {
        String oldUser = oldUserField.getText();
        String oldPass = new String(oldPassField.getPassword());
        String newUser = newUserField.getText();
        String newPass = new String(newPassField.getPassword());

        if (oldUser.isEmpty() || oldPass.isEmpty() || newUser.isEmpty() || newPass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        try {
            Connection conn = db.DBConnection.getConnection();
            String checkSql = "SELECT * FROM users WHERE username = ? AND pass_word = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, oldUser);
            checkStmt.setString(2, oldPass);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                String updateSql = "UPDATE users SET username = ?, pass_word = ? WHERE username = ? AND pass_word = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setString(1, newUser);
                updateStmt.setString(2, newPass);
                updateStmt.setString(3, oldUser);
                updateStmt.setString(4, oldPass);

                int rows = updateStmt.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Credentials updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Update failed!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Old username or password is incorrect!");
            }
            
            oldUserField.setText("");
            oldPassField.setText("");
            newUserField.setText("");
            newPassField.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}