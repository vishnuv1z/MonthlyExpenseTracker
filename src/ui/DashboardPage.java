package ui;
import javax.swing.*;

public class DashboardPage extends JFrame {
    private static final long serialVersionUID = 1L;

    public DashboardPage() {
        setTitle("Dashboard");
        setSize(400, 550);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton incomeBtn = new JButton("Income");
        incomeBtn.setBounds(100, 50, 200, 30);
        add(incomeBtn);

        JButton expenseBtn = new JButton("Expense");
        expenseBtn.setBounds(100, 100, 200, 30);
        add(expenseBtn);

        JButton editBtn = new JButton("Edit Income/Expenses");
        editBtn.setBounds(100, 150, 200, 30);
        add(editBtn);

        JButton viewIncBtn = new JButton("View Income");
        viewIncBtn.setBounds(100, 200, 200, 30);
        add(viewIncBtn);

        JButton viewExpBtn = new JButton("View Expenses");
        viewExpBtn.setBounds(100, 250, 200, 30);
        add(viewExpBtn);

        JButton summaryBtn = new JButton("Monthly Summary");
        summaryBtn.setBounds(100, 300, 200, 30);
        add(summaryBtn);

        JButton settingsBtn = new JButton("Settings");
        settingsBtn.setBounds(100, 350, 200, 30);
        add(settingsBtn);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(100, 400, 200, 30);
        add(logoutBtn);

        incomeBtn.addActionListener(e -> { new IncomePage(); dispose(); });
        expenseBtn.addActionListener(e -> { new ExpensePage(); dispose(); });
        editBtn.addActionListener(e -> { new EditDataPage(); dispose(); });
        viewIncBtn.addActionListener(e -> { new ViewIncomePage(); dispose(); });
        viewExpBtn.addActionListener(e -> { new ViewPage(); dispose(); });
        summaryBtn.addActionListener(e -> { new SummaryPage(); dispose(); });
        settingsBtn.addActionListener(e -> { new SettingsPage(); dispose(); });
        logoutBtn.addActionListener(e -> { new LogoutPage(); dispose(); });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}