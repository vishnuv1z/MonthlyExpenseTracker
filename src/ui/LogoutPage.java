package ui;
import javax.swing.*;
import java.sql.Connection;

public class LogoutPage extends JFrame {
    private static final long serialVersionUID = 1L;

    public LogoutPage() {
        setTitle("Logout");
        setSize(330, 250);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(40, 87, 111, 30);
        getContentPane().add(logoutBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(167, 87, 105, 30);
        getContentPane().add(backBtn);

        logoutBtn.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to log out?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                try {
                    Connection conn = db.DBConnection.getConnection();
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });

        backBtn.addActionListener(e -> {
            new DashboardPage();
            dispose();
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}