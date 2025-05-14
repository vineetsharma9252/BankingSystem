import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// Database class to handle database connections and operations
class Database {
    Connection conn = null;

    Database(String database_url, String username, String password) throws SQLException, ClassNotFoundException {
        this.conn = DriverManager.getConnection(database_url, username, password);
        System.out.println("Connected to the database successfully!");
    }

    public void database_creation(String DBname) throws SQLException {
        try (Statement statement = conn.createStatement()) {
            String createDBQuery = "CREATE DATABASE IF NOT EXISTS " + DBname;
            statement.executeUpdate(createDBQuery);
            System.out.println("Database Created Successfully");
        }
    }

    public void table_creation(String query) throws SQLException {
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Table created successfully");
        }
    }

    public boolean find(HashMap<String, String> map, String table) throws SQLException {
        try (Statement st = conn.createStatement()) {
            int count = 0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                try {
                    String query = "SELECT * FROM " + table + " WHERE " + entry.getKey() + " = '" + entry.getValue() + "'";
                    ResultSet rs = st.executeQuery(query);
                    if (rs.next()) {
                        String cn = rs.getString(entry.getKey());
                        if (cn != null && !cn.isEmpty()) {
                            count++;
                        }
                    }
                    rs.close();
                } catch (SQLException s) {
                    System.out.println(s);
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                }
            }
            if (count == 2) {
                JOptionPane.showMessageDialog(null, "Welcome sir/madam");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Card Number or Pin is wrong. Please try again.");
            }
            return false;
        }
    }
}

// Class to handle account type selection and services
class Account_type {
    public static void main(String args[]) {
        JFrame frame = new JFrame("Background Image Example");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("BankingSystem/Assets/ATM_UI_Background.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(null);
        JLabel label = new JLabel("Account Services", SwingConstants.CENTER);
        label.setBounds(300, 300, 200, 20);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.WHITE);

        JButton deposit = new JButton("Deposit");
        JButton fastCash = new JButton("FAST Cash");
        JButton pinChange = new JButton("Pin Change");
        JButton cashWithdrawal = new JButton("Cash Withdrawal");
        JButton miniStatement = new JButton("Mini Statement");
        JButton balanceEnquiry = new JButton("Balance Enquiry");
        JButton exit = new JButton("Exit");

        deposit.setBounds(240, 366, 100, 20);
        fastCash.setBounds(240, 396, 100, 20);
        pinChange.setBounds(240, 426, 100, 20);
        cashWithdrawal.setBounds(450, 366, 100, 20);
        miniStatement.setBounds(450, 396, 100, 20);
        balanceEnquiry.setBounds(450, 426, 100, 20);
        exit.setBounds(450, 456, 100, 20);

        backgroundPanel.add(label);
        backgroundPanel.add(deposit);
        backgroundPanel.add(fastCash);
        backgroundPanel.add(pinChange);
        backgroundPanel.add(cashWithdrawal);
        backgroundPanel.add(miniStatement);
        backgroundPanel.add(balanceEnquiry);
        backgroundPanel.add(exit);

        frame.add(backgroundPanel);
        frame.setVisible(true);
    }
}

public class Main {
    static TextField textfieldmaker(int y) {
        TextField tf = new TextField();
        tf.setBounds(250, y, 300, 20);
        return tf;
    }

    static void Account_Services() {
        JFrame frame = new JFrame("Background Image Example");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("BankingSystem/Assets/ATM_UI_Background.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(null);
        JLabel label = new JLabel("Account Services", SwingConstants.CENTER);
        label.setBounds(300, 300, 200, 20);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.WHITE);

        frame.add(backgroundPanel);
        frame.setVisible(true);
    }

    static void Account_type(String cardnumber) {
        JFrame frame = new JFrame("ATM");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMaximumSize(new Dimension(800, 800));

        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("BankingSystem/Assets/ATM_UI_Background.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(null);
        JLabel label = new JLabel("Account Type ", SwingConstants.CENTER);
        label.setBounds(300, 300, 200, 30);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(Color.WHITE);
        JButton saving = new JButton("Saving");
        JButton current = new JButton("Current");
        JButton fd = new JButton("Fixed Deposit");
        saving.setBounds(450, 366, 90, 20);
        current.setBounds(450, 396, 90, 20);
        fd.setBounds(450, 426, 90, 20);
        backgroundPanel.add(current);
        backgroundPanel.add(fd);
        backgroundPanel.add(saving);
        backgroundPanel.add(label, BorderLayout.CENTER);

        frame.add(backgroundPanel);
        saving.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saving.setVisible(false);
                current.setVisible(false);
                fd.setVisible(false);
                label.setText("Services ");
                JButton deposit = new JButton("Deposit");
                JButton fastCash = new JButton("FAST Cash");
                JButton pinChange = new JButton("Pin Change");
                JButton cashWithdrawal = new JButton("Cash Withdrawal");
                JButton miniStatement = new JButton("Mini Statement");
                JButton balanceEnquiry = new JButton("Balance Enquiry");
                JButton exit = new JButton("Exit");

                deposit.setBounds(240, 366, 100, 20);
                fastCash.setBounds(240, 396, 100, 20);
                pinChange.setBounds(240, 426, 100, 20);
                cashWithdrawal.setBounds(450, 366, 100, 20);
                miniStatement.setBounds(450, 396, 100, 20);
                balanceEnquiry.setBounds(450, 426, 100, 20);
                exit.setBounds(450, 456, 100, 20);

                backgroundPanel.add(label);
                backgroundPanel.add(deposit);
                backgroundPanel.add(fastCash);
                backgroundPanel.add(pinChange);
                backgroundPanel.add(cashWithdrawal);
                backgroundPanel.add(miniStatement);
                backgroundPanel.add(balanceEnquiry);
                backgroundPanel.add(exit);

                pinChange.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        label.setText("Change Your Pin");
                        deposit.setVisible(false);
                        fastCash.setVisible(false);
                        cashWithdrawal.setVisible(false);
                        miniStatement.setVisible(false);
                        balanceEnquiry.setVisible(false);
                        exit.setVisible(false);
                        pinChange.setVisible(false);
                        JLabel currentPinLabel = new JLabel("Current Pin");
                        JTextField currentPin = new JTextField();
                        JLabel newPinLabel = new JLabel("New Pin");
                        JTextField newPin = new JTextField();
                        currentPinLabel.setBounds(300, 320, 100, 30);
                        currentPin.setBounds(300, 350, 200, 30);
                        newPinLabel.setBounds(300, 380, 100, 30);
                        newPin.setBounds(300, 400, 200, 30);
                        backgroundPanel.add(currentPinLabel);
                        backgroundPanel.add(currentPin);
                        backgroundPanel.add(newPinLabel);
                        backgroundPanel.add(newPin);
                        JButton submit = new JButton("Submit");
                        submit.setBounds(300, 440, 100, 20);
                        backgroundPanel.add(submit);
                        JButton back = new JButton("Back");
                        back.setBounds(400, 440, 70, 20);
                        backgroundPanel.add(back);
                        back.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                label.setText("Services");
                                deposit.setVisible(true);
                                fastCash.setVisible(true);
                                cashWithdrawal.setVisible(true);
                                miniStatement.setVisible(true);
                                balanceEnquiry.setVisible(true);
                                exit.setVisible(true);
                                newPinLabel.setVisible(false);
                                newPin.setVisible(false);
                                currentPin.setVisible(false);
                                currentPinLabel.setVisible(false);
                                submit.setVisible(false);
                                pinChange.setVisible(true);
                                back.setVisible(false);
                            }
                        });
                        submit.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    Database db = new Database("jdbc:mysql://localhost:3306/", "root", "supersaiyan1000");
                                    try (Statement st = db.conn.createStatement()) {
                                        db.table_creation("use user_account");
                                        ResultSet rs = st.executeQuery("SELECT Pin FROM user WHERE CardNumber = '" + cardnumber + "'");
                                        if (rs.next()) {
                                            if (currentPin.getText().equals(Integer.toString(rs.getInt("Pin")))) {
                                                st.executeUpdate("UPDATE user SET Pin = " + newPin.getText() + " WHERE Pin = " + currentPin.getText());
                                                JOptionPane.showMessageDialog(null, "Pin changed successfully");
                                                label.setText("Services");
                                                deposit.setVisible(true);
                                                fastCash.setVisible(true);
                                                cashWithdrawal.setVisible(true);
                                                miniStatement.setVisible(true);
                                                balanceEnquiry.setVisible(true);
                                                exit.setVisible(true);
                                                pinChange.setVisible(true);
                                                currentPin.setVisible(false);
                                                currentPinLabel.setVisible(false);
                                                newPinLabel.setVisible(false);
                                                newPin.setVisible(false);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Invalid Pin, try again");
                                            }
                                        }
                                        rs.close();
                                    }
                                } catch (SQLException | ClassNotFoundException c) {
                                    System.out.println("Something went wrong: " + c);
                                    JOptionPane.showMessageDialog(null, "Something went wrong");
                                }
                            }
                        });
                    }
                });
                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                    }
                });
                miniStatement.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        label.setText("Your Transaction Detail");
                        deposit.setVisible(false);
                        fastCash.setVisible(false);
                        cashWithdrawal.setVisible(false);
                        miniStatement.setVisible(false);
                        balanceEnquiry.setVisible(false);
                        exit.setVisible(false);
                        pinChange.setVisible(false);
                        JButton back = new JButton("Back");
                        back.setBounds(450, 455, 70, 20);
                        backgroundPanel.add(back);

                        try {
                            Database db = new Database("jdbc:mysql://localhost:3306/", "root", "supersaiyan1000");
                            db.table_creation("use user_account");
                            try (Statement st = db.conn.createStatement()) {
                                ResultSet rs = st.executeQuery("SELECT cardNumber, Amount, Time_deposited, Transaction_type FROM user_amount_info");
                                DefaultTableModel tableModel = new DefaultTableModel();
                                ResultSetMetaData metaData = rs.getMetaData();
                                int columnCount = metaData.getColumnCount();

                                for (int i = 1; i <= columnCount; i++) {
                                    tableModel.addColumn(metaData.getColumnName(i));
                                }
                                while (rs.next()) {
                                    Object[] row = new Object[columnCount];
                                    for (int i = 1; i <= columnCount; i++) {
                                        row[i - 1] = rs.getObject(i);
                                    }
                                    tableModel.addRow(row);
                                }
                                JTable table = new JTable(tableModel);
                                JScrollPane scrollPane = new JScrollPane(table);
                                scrollPane.setBounds(240, 340, 308, 110);
                                backgroundPanel.add(scrollPane);
                                back.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        label.setText("Services");
                                        deposit.setVisible(true);
                                        fastCash.setVisible(true);
                                        cashWithdrawal.setVisible(true);
                                        miniStatement.setVisible(true);
                                        balanceEnquiry.setVisible(true);
                                        exit.setVisible(true);
                                        pinChange.setVisible(true);
                                        back.setVisible(false);
                                        scrollPane.setVisible(false);
                                    }
                                });
                                rs.close();
                            }
                        } catch (SQLException | ClassNotFoundException c) {
                            System.out.println(c);
                            JOptionPane.showMessageDialog(null, "Something went wrong");
                        }
                    }
                });
                balanceEnquiry.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Database db = new Database("jdbc:mysql://localhost:3306/", "root", "supersaiyan1000");
                            db.table_creation("use user_account");
                            try (Statement st = db.conn.createStatement()) {
                                ResultSet rs = st.executeQuery("SELECT user_id FROM user WHERE CardNumber = '" + cardnumber + "'");
                                int user_id = 0;
                                if (rs.next()) {
                                    user_id = rs.getInt("user_id");
                                }
                                rs.close();
                                int totalamount_dep = 0;
                                int totalamount_with = 0;
                                ResultSet rs_deposited = st.executeQuery("SELECT SUM(Amount) AS Amount FROM user_amount_info WHERE Transaction_type = 'Credited'");
                                if (rs_deposited.next()) {
                                    totalamount_dep = rs_deposited.getInt("Amount");
                                }
                                rs_deposited.close();
                                ResultSet rs_withdrawn = st.executeQuery("SELECT SUM(Amount) AS Amount FROM user_amount_info WHERE Transaction_type = 'Debited'");
                                if (rs_withdrawn.next()) {
                                    totalamount_with = rs_withdrawn.getInt("Amount");
                                }
                                rs_withdrawn.close();
                                int totalamount_rem = totalamount_dep - totalamount_with;
                                JLabel amount = new JLabel("Total Amount Remaining is " + totalamount_rem);
                                label.setText("Balance Enquiry");
                                deposit.setVisible(false);
                                fastCash.setVisible(false);
                                cashWithdrawal.setVisible(false);
                                miniStatement.setVisible(false);
                                balanceEnquiry.setVisible(false);
                                exit.setVisible(false);
                                pinChange.setVisible(false);
                                JButton back = new JButton("Back");
                                back.setBounds(300, 405, 100, 30);
                                backgroundPanel.add(back);
                                back.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        amount.setVisible(false);
                                        back.setVisible(false);
                                        deposit.setVisible(true);
                                        fastCash.setVisible(true);
                                        cashWithdrawal.setVisible(true);
                                        miniStatement.setVisible(true);
                                        balanceEnquiry.setVisible(true);
                                        exit.setVisible(true);
                                        pinChange.setVisible(true);
                                        label.setText("Services");
                                    }
                                });
                                amount.setBounds(280, 350, 300, 40);
                                amount.setFont(new Font("Serif", Font.ITALIC, 15));
                                amount.setForeground(Color.WHITE);
                                backgroundPanel.add(amount);
                            }
                        } catch (SQLException | ClassNotFoundException c) {
                            System.out.println("Something went wrong: " + c);
                            JOptionPane.showMessageDialog(null, "Something went wrong");
                        }
                    }
                });
                deposit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        deposit.setVisible(false);
                        fastCash.setVisible(false);
                        cashWithdrawal.setVisible(false);
                        miniStatement.setVisible(false);
                        balanceEnquiry.setVisible(false);
                        exit.setVisible(false);
                        pinChange.setVisible(false);
                        label.setSize(300, 20);
                        label.setFont(new Font("Serif", Font.BOLD, 13));
                        label.setText("*Minimum amount should be 500");
                        JTextField depositField = new JTextField();
                        depositField.setBackground(Color.YELLOW);
                        depositField.setBounds(300, 340, 200, 30);
                        JButton depositAmount = new JButton("Deposit");
                        depositAmount.setBounds(300, 400, 100, 25);
                        backgroundPanel.add(depositAmount);
                        JButton back = new JButton("Back");
                        back.setBounds(425, 400, 70, 25);
                        backgroundPanel.add(back);
                        back.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                label.setText("Services");
                                label.setFont(new Font("Serif", Font.BOLD, 20));
                                label.setBounds(300, 300, 200, 40);
                                depositAmount.setVisible(false);
                                depositField.setVisible(false);
                                back.setVisible(false);
                                deposit.setVisible(true);
                                fastCash.setVisible(true);
                                cashWithdrawal.setVisible(true);
                                miniStatement.setVisible(true);
                                balanceEnquiry.setVisible(true);
                                exit.setVisible(true);
                                pinChange.setVisible(true);
                            }
                        });
                        depositAmount.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    int amount = Integer.parseInt(depositField.getText());
                                    if (amount < 500) {
                                        JOptionPane.showMessageDialog(null, "Amount should be greater than 500");
                                    } else {
                                        Database db = new Database("jdbc:mysql://localhost:3306/", "root", "supersaiyan1000");
                                        db.table_creation("use user_account");
                                        try (Statement st = db.conn.createStatement()) {
                                            ResultSet rs = st.executeQuery("SELECT user_id FROM user WHERE CardNumber = '" + cardnumber + "'");
                                            int user_id = 0;
                                            if (rs.next()) {
                                                user_id = rs.getInt("user_id");
                                            }
                                            rs.close();
                                            LocalDateTime now = LocalDateTime.now();
                                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                                            String formattedDate = now.format(formatter);
                                            db.table_creation(
                                                    "CREATE TABLE IF NOT EXISTS User_amount_info (" +
                                                            "user_id INT, cardNumber VARCHAR(30), Amount INT, " +
                                                            "Time_deposited VARCHAR(50), Transaction_type VARCHAR(10), " +
                                                            "CONSTRAINT fk_user_amount_user FOREIGN KEY (user_id) REFERENCES user(user_id))");
                                            db.table_creation(
                                                    "INSERT INTO User_amount_info(user_id, cardNumber, Amount, Time_deposited, Transaction_type) VALUES (" +
                                                            user_id + ", '" + cardnumber + "', " + amount + ", '" + formattedDate + "', 'Credited')");
                                            JOptionPane.showMessageDialog(null, amount + " has been deposited to your account");
                                            System.out.println("Amount_info table has been created");
                                        }
                                    }
                                } catch (SQLException | ClassNotFoundException | NumberFormatException c) {
                                    System.out.println("Something went wrong: " + c);
                                    JOptionPane.showMessageDialog(null, "Something went wrong");
                                }
                            }
                        });
                        backgroundPanel.add(depositField);
                    }
                });
                cashWithdrawal.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        label.setVisible(false);
                        deposit.setVisible(false);
                        fastCash.setVisible(false);
                        cashWithdrawal.setVisible(false);
                        miniStatement.setVisible(false);
                        balanceEnquiry.setVisible(false);
                        exit.setVisible(false);
                        pinChange.setVisible(false);
                        JLabel maxWithdrawalAmount = new JLabel("Maximum 10000 can be withdrawn at once");
                        JTextField amountField = new JTextField();
                        JButton withdrawButton = new JButton("Withdraw");
                        withdrawButton.setBounds(300, 425, 100, 25);
                        JButton back = new JButton("Back");
                        back.setBounds(425, 425, 70, 25);
                        backgroundPanel.add(back);
                        back.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                maxWithdrawalAmount.setVisible(false);
                                label.setText("Services");
                                label.setFont(new Font("Serif", Font.BOLD, 20));
                                label.setBounds(300, 300, 200, 40);
                                label.setVisible(true);
                                withdrawButton.setVisible(false);
                                amountField.setVisible(false);
                                back.setVisible(false);
                                deposit.setVisible(true);
                                fastCash.setVisible(true);
                                cashWithdrawal.setVisible(true);
                                miniStatement.setVisible(true);
                                balanceEnquiry.setVisible(true);
                                exit.setVisible(true);
                                pinChange.setVisible(true);
                            }
                        });
                        withdrawButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    int amount = Integer.parseInt(amountField.getText());
                                    if (amount > 10000) {
                                        JOptionPane.showMessageDialog(null, "Amount should be less than 10000");
                                    } else {
                                        Database db = new Database("jdbc:mysql://localhost:3306/", "root", "supersaiyan1000");
                                        db.table_creation("use user_account");
                                        try (Statement st = db.conn.createStatement()) {
                                            ResultSet rs = st.executeQuery("SELECT user_id FROM user WHERE CardNumber = '" + cardnumber + "'");
                                            int user_id = 0;
                                            if (rs.next()) {
                                                user_id = rs.getInt("user_id");
                                            }
                                            rs.close();
                                            int totalamount_dep = 0;
                                            int totalamount_with = 0;
                                            ResultSet rs_deposited = st.executeQuery("SELECT SUM(Amount) AS Amount FROM user_amount_info WHERE Transaction_type = 'Credited'");
                                            if (rs_deposited.next()) {
                                                totalamount_dep = rs_deposited.getInt("Amount");
                                            }
                                            rs_deposited.close();
                                            ResultSet rs_withdrawn = st.executeQuery("SELECT SUM(Amount) AS Amount FROM user_amount_info WHERE Transaction_type = 'Debited'");
                                            if (rs_withdrawn.next()) {
                                                totalamount_with = rs_withdrawn.getInt("Amount");
                                            }
                                            rs_withdrawn.close();
                                            int totalamount_rem = totalamount_dep - totalamount_with;
                                            if (amount > totalamount_rem) {
                                                JOptionPane.showMessageDialog(null,
                                                        "You don't have sufficient balance\nYour current balance is " + totalamount_rem);
                                            } else {
                                                LocalDateTime now = LocalDateTime.now();
                                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                                                String formattedDate = now.format(formatter);
                                                db.table_creation(
                                                        "INSERT INTO user_amount_info(user_id, cardNumber, Amount, Time_deposited, Transaction_type) VALUES (" +
                                                                user_id + ", '" + cardnumber + "', " + amount + ", '" + formattedDate + "', 'Debited')");
                                                JOptionPane.showMessageDialog(null, amount + " Amount withdrawn");
                                                JOptionPane.showMessageDialog(null, (totalamount_rem - amount) + " Amount Remaining");
                                            }
                                        }
                                    }
                                } catch (SQLException | ClassNotFoundException | NumberFormatException c) {
                                    System.out.println("Something went wrong: " + c);
                                    JOptionPane.showMessageDialog(null, "Something went wrong");
                                }
                            }
                        });
                        maxWithdrawalAmount.setForeground(Color.YELLOW);
                        maxWithdrawalAmount.setBounds(320, 300, 250, 20);
                        maxWithdrawalAmount.setFont(new Font("Serif", Font.BOLD, 11));
                        amountField.setBounds(300, 350, 200, 25);
                        backgroundPanel.add(maxWithdrawalAmount);
                        backgroundPanel.add(amountField);
                        backgroundPanel.add(withdrawButton);
                    }
                });
            }
        });
        frame.setVisible(true);
    }

    static void New_Account_interface(JFrame f) {
        JFrame jf = new JFrame("Create New SBI Account");
        jf.setSize(800, 800);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - jf.getWidth()) / 2;
        int y = (screenSize.height - jf.getHeight()) / 2;
        jf.setLocation(x, y);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(12, 200, 250));
        panel.setLayout(null);

        JLabel newAcc = new JLabel("Create New SBI Account", SwingConstants.CENTER);
        newAcc.setForeground(Color.WHITE);
        newAcc.setFont(new Font("Serif", Font.BOLD, 20));
        newAcc.setBounds(300, 2, 250, 100);

        JButton goBack = new JButton("Go Back");
        goBack.setBounds(100, 1, 100, 30);
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(true);
                jf.setVisible(false);
            }
        });

        JLabel name = new JLabel("Name");
        JLabel surname = new JLabel("Last Name");
        JLabel father = new JLabel("Father Name");
        JLabel address = new JLabel("Address");
        JLabel dob = new JLabel("Date of Birth");
        JLabel gender = new JLabel("Gender");
        JLabel email = new JLabel("Email");
        JLabel maritalStatus = new JLabel("Marital Status");
        JLabel city = new JLabel("City");
        JLabel phone = new JLabel("Phone Number");
        JLabel state = new JLabel("State");
        JLabel pin = new JLabel("Pin");

        name.setBounds(100, 70, 100, 80);
        surname.setBounds(100, 120, 100, 80);
        father.setBounds(100, 170, 100, 80);
        address.setBounds(100, 220, 100, 80);
        dob.setBounds(100, 270, 100, 80);
        gender.setBounds(100, 320, 100, 80);
        email.setBounds(100, 370, 100, 80);
        maritalStatus.setBounds(100, 420, 100, 80);
        city.setBounds(100, 470, 100, 80);
        phone.setBounds(100, 520, 100, 80);
        state.setBounds(100, 570, 100, 80);
        pin.setBounds(100, 620, 100, 80);

        name.setFont(new Font("Serif", Font.PLAIN, 18));
        surname.setFont(new Font("Serif", Font.PLAIN, 18));
        father.setFont(new Font("Serif", Font.PLAIN, 18));
        address.setFont(new Font("Serif", Font.PLAIN, 18));
        dob.setFont(new Font("Serif", Font.PLAIN, 18));
        gender.setFont(new Font("Serif", Font.PLAIN, 18));
        email.setFont(new Font("Serif", Font.PLAIN, 18));
        maritalStatus.setFont(new Font("Serif", Font.PLAIN, 18));
        city.setFont(new Font("Serif", Font.PLAIN, 18));
        phone.setFont(new Font("Serif", Font.PLAIN, 18));
        state.setFont(new Font("Serif", Font.PLAIN, 18));
        pin.setFont(new Font("Serif", Font.PLAIN, 18));

        TextField nameTextfield = textfieldmaker(100);
        TextField surnameTextfield = textfieldmaker(150);
        TextField fatherTextfield = textfieldmaker(200);
        TextField addressTextfield = textfieldmaker(250);
        TextField dobTextfield = textfieldmaker(300);
        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        JRadioButton others = new JRadioButton("Rather Not Say");
        male.setBounds(250, 350, 100, 20);
        female.setBounds(350, 350, 100, 20);
        others.setBounds(450, 350, 100, 20);
        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);
        bg.add(others);
        panel.add(male);
        panel.add(female);
        panel.add(others);
        TextField emailTextfield = textfieldmaker(400);
        JRadioButton married = new JRadioButton("Married");
        JRadioButton unmarried = new JRadioButton("Unmarried");
        married.setBounds(250, 450, 100, 20);
        unmarried.setBounds(450, 450, 100, 20);
        ButtonGroup maritalStatusGroup = new ButtonGroup();
        maritalStatusGroup.add(married);
        maritalStatusGroup.add(unmarried);
        panel.add(married);
        panel.add(unmarried);
        TextField cityTextfield = textfieldmaker(500);
        TextField pincodeTextfield = textfieldmaker(550);
        TextField stateTextfield = textfieldmaker(600);
        TextField pinTextfield = textfieldmaker(650);

        JButton submit = new JButton("Submit");
        submit.setBounds(300, 700, 200, 30);
        submit.addActionListener(new ActionListener() {
            private static final HashSet<String> generatedCards = new HashSet<>();
            private static final Random random = new Random();

            public static String generateUniqueCreditCard() {
                String cardNumber;
                do {
                    StringBuilder card = new StringBuilder();
                    for (int i = 0; i < 16; i++) {
                        card.append(random.nextInt(10));
                    }
                    cardNumber = card.toString();
                } while (generatedCards.contains(cardNumber));
                generatedCards.add(cardNumber);
                return cardNumber;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameTextfield.getText();
                    String surname = surnameTextfield.getText();
                    String address = addressTextfield.getText();
                    String dob = dobTextfield.getText();
                    String gender = male.isSelected() ? "Male" : female.isSelected() ? "Female" : "Rather Not Say";
                    String email = emailTextfield.getText();
                    String marital = married.isSelected() ? "Married" : "Unmarried";
                    String city = cityTextfield.getText().trim();
                    String phoneNumber = pincodeTextfield.getText();
                    String state = stateTextfield.getText();
                    String userCardNumber = generateUniqueCreditCard();
                    int pin = Integer.parseInt(pinTextfield.getText());

                    try {
                        Database db = new Database("jdbc:mysql://localhost:3306/", "root", "supersaiyan1000");
                        db.database_creation("User_Account");
                        db.table_creation("USE User_Account");
                        db.table_creation(
                                "CREATE TABLE IF NOT EXISTS user (user_id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(30), surname VARCHAR(30), Address VARCHAR(50), Dob VARCHAR(50), Gender VARCHAR(30), Email VARCHAR(40), Marital_status VARCHAR(20), city VARCHAR(20), PhoneNumber VARCHAR(20), State VARCHAR(20), CardNumber VARCHAR(20), Pin INT)");
                        db.table_creation(
                                "INSERT INTO user(name, surname, Address, Dob, Gender, Email, Marital_status, city, PhoneNumber, State, CardNumber, Pin) VALUES ('" +
                                        name + "', '" + surname + "', '" + address + "', '" + dob + "', '" + gender + "', '" + email + "', '" + marital + "', '" +
                                        city + "', '" + phoneNumber + "', '" + state + "', '" + userCardNumber + "', " + pin + ")");
                        try (Statement statement = db.conn.createStatement()) {
                            ResultSet rs = statement.executeQuery("SELECT user_id FROM user WHERE CardNumber = '" + userCardNumber + "'");
                            int userId = 0;
                            if (rs.next()) {
                                userId = rs.getInt("user_id");
                            }
                            rs.close();
                            jf.setVisible(false);
                            New_Account_interface_2(userId, f);
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        System.out.println("Something went wrong: " + ex);
                        JOptionPane.showMessageDialog(null, "Something went wrong");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid PIN");
                }
            }
        });

        panel.add(pin);
        panel.add(submit);
        panel.add(nameTextfield);
        panel.add(surnameTextfield);
        panel.add(fatherTextfield);
        panel.add(addressTextfield);
        panel.add(dobTextfield);
        panel.add(emailTextfield);
        panel.add(cityTextfield);
        panel.add(pincodeTextfield);
        panel.add(stateTextfield);
        panel.add(name);
        panel.add(surname);
        panel.add(father);
        panel.add(address);
        panel.add(dob);
        panel.add(gender);
        panel.add(email);
        panel.add(maritalStatus);
        panel.add(city);
        panel.add(phone);
        panel.add(state);
        panel.add(goBack);
        panel.add(newAcc);
        jf.add(panel);
        jf.setVisible(true);
    }

    public static void New_Account_interface_2(int userId, JFrame f) {
        JFrame additionalForm = new JFrame("Additional Information");
        additionalForm.setSize(800, 800);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - additionalForm.getWidth()) / 2;
        int y = (screenSize.height - additionalForm.getHeight()) / 2;
        additionalForm.setLocation(x, y);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(150, 200, 250));
        panel.setLayout(null);

        JLabel title = new JLabel("Additional Information", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setBounds(250, 20, 300, 40);
        panel.add(title);

        JLabel aadhaarLabel = new JLabel("Aadhaar Card:");
        aadhaarLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        aadhaarLabel.setBounds(100, 100, 150, 30);
        panel.add(aadhaarLabel);
        JTextField aadhaarField = new JTextField();
        aadhaarField.setBounds(250, 100, 300, 30);
        panel.add(aadhaarField);

        JLabel panLabel = new JLabel("PAN Card:");
        panLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        panLabel.setBounds(100, 150, 150, 30);
        panel.add(panLabel);
        JTextField panField = new JTextField();
        panField.setBounds(250, 150, 300, 30);
        panel.add(panField);

        JLabel incomeLabel = new JLabel("Income:");
        incomeLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        incomeLabel.setBounds(100, 200, 150, 30);
        panel.add(incomeLabel);
        JTextField incomeField = new JTextField();
        incomeField.setBounds(250, 200, 300, 30);
        panel.add(incomeField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        categoryLabel.setBounds(100, 250, 150, 30);
        panel.add(categoryLabel);
        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"General", "OBC", "SC", "ST", "Other"});
        categoryComboBox.setBounds(250, 250, 300, 30);
        panel.add(categoryComboBox);

        JLabel seniorCitizenLabel = new JLabel("Senior Citizen:");
        seniorCitizenLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        seniorCitizenLabel.setBounds(100, 300, 150, 30);
        panel.add(seniorCitizenLabel);
        JComboBox<String> seniorCitizenComboBox = new JComboBox<>(new String[]{"Yes", "No"});
        seniorCitizenComboBox.setBounds(250, 300, 300, 30);
        panel.add(seniorCitizenComboBox);

        JLabel existingAccountLabel = new JLabel("Existing Account:");
        existingAccountLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        existingAccountLabel.setBounds(100, 350, 150, 30);
        panel.add(existingAccountLabel);
        JComboBox<String> existingAccountComboBox = new JComboBox<>(new String[]{"Yes", "No"});
        existingAccountComboBox.setBounds(250, 350, 300, 30);
        panel.add(existingAccountComboBox);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(300, 450, 150, 30);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String aadhaar = aadhaarField.getText();
                    String pan = panField.getText();
                    String income = incomeField.getText();
                    String category = (String) categoryComboBox.getSelectedItem();
                    String seniorCitizen = (String) seniorCitizenComboBox.getSelectedItem();
                    String existingAccount = (String) existingAccountComboBox.getSelectedItem();

                    if (aadhaar.length() != 12 || !aadhaar.matches("\\d+")) {
                        JOptionPane.showMessageDialog(null, "Invalid Aadhaar number!");
                        return;
                    }
                    if (pan.length() != 10 || !pan.matches("[A-Z]{5}\\d{4}[A-Z]")) {
                        JOptionPane.showMessageDialog(null, "Invalid PAN number!");
                        return;
                    }

                    try {
                        Database db = new Database("jdbc:mysql://localhost:3306/", "root", "supersaiyan1000");
                        db.database_creation("User_Account");
                        db.table_creation("USE User_Account");
                        db.table_creation(
                                "CREATE TABLE IF NOT EXISTS additional_info (" +
                                        "aadhaar_card VARCHAR(12), pan_card VARCHAR(10), income VARCHAR(20), " +
                                        "category VARCHAR(20), senior_citizen VARCHAR(3), existing_account VARCHAR(3), " +
                                        "user_id INT, CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE)");
                        db.table_creation(
                                "INSERT INTO additional_info (aadhaar_card, pan_card, income, category, senior_citizen, existing_account, user_id) VALUES ('" +
                                        aadhaar + "', '" + pan + "', '" + income + "', '" + category + "', '" + seniorCitizen + "', '" + existingAccount + "', " + userId + ")");
                        try (Statement st = db.conn.createStatement()) {
                            ResultSet rs = st.executeQuery("SELECT CardNumber FROM user WHERE user_id = " + userId);
                            if (rs.next()) {
                                String cardNumber = rs.getString("CardNumber");
                                JOptionPane.showMessageDialog(null, "Information saved successfully!\nYou can login now with Card Number\n" + cardNumber);
                                additionalForm.setVisible(false);
                                New_Account_interface3(cardNumber, 0, userId); // PIN not retrieved here
                            }
                            rs.close();
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        System.out.println("Error saving to database: " + ex.getMessage());
                        JOptionPane.showMessageDialog(null, "Something went wrong");
                    }
                } catch (Throwable ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 450, 100, 30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                additionalForm.setVisible(false);
                f.setVisible(true);
            }
        });

        panel.add(submitButton);
        panel.add(backButton);
        additionalForm.add(panel);
        additionalForm.setVisible(true);
    }

    public static void New_Account_interface3(String cardNumber, int pin, int userId) {
        JFrame frame = new JFrame("User Account Preferences");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(null);

        JLabel accountTypeLabel = new JLabel("Account Type:");
        accountTypeLabel.setBounds(50, 50, 200, 30);
        String[] accountTypes = {"Savings", "Current", "Fixed Deposit"};
        JComboBox<String> accountTypeComboBox = new JComboBox<>(accountTypes);
        accountTypeComboBox.setBounds(250, 50, 200, 30);

        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberLabel.setBounds(50, 100, 200, 30);
        JLabel cardNumberField = new JLabel(cardNumber);
        cardNumberField.setBounds(250, 100, 200, 30);

        JLabel pinLabel = new JLabel("PIN Number:");
        pinLabel.setBounds(50, 150, 200, 30);
        JLabel pinField = new JLabel(String.valueOf(pin));
        pinField.setBounds(250, 150, 200, 30);

        JLabel servicesLabel = new JLabel("Services:");
        servicesLabel.setBounds(50, 200, 200, 30);
        JCheckBox onlineBankingCheckBox = new JCheckBox("Online Banking");
        onlineBankingCheckBox.setBounds(250, 200, 200, 30);
        JCheckBox atmCheckBox = new JCheckBox("ATM Access");
        atmCheckBox.setBounds(250, 240, 200, 30);
        JCheckBox mobileBankingCheckBox = new JCheckBox("Mobile Banking");
        mobileBankingCheckBox.setBounds(250, 280, 200, 30);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(300, 350, 100, 30);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountType = (String) accountTypeComboBox.getSelectedItem();
                StringBuilder services = new StringBuilder();
                if (onlineBankingCheckBox.isSelected()) services.append("Online_Banking_");
                if (atmCheckBox.isSelected()) services.append("ATM_Access_");
                if (mobileBankingCheckBox.isSelected()) services.append("Mobile_Banking");

                try {
                    Database db = new Database("jdbc:mysql://localhost:3306/", "root", "supersaiyan1000");
                    db.table_creation("USE user_account");
                    db.table_creation(
                            "CREATE TABLE IF NOT EXISTS Account_info (user_id INT, Account_type VARCHAR(20), Services VARCHAR(50), " +
                                    "CONSTRAINT uk_user FOREIGN KEY (user_id) REFERENCES additional_info(user_id) ON DELETE CASCADE)");
                    String query = "INSERT INTO Account_info(user_id, Account_type, Services) VALUES (?, ?, ?)";
                    try (PreparedStatement preparedStatement = db.conn.prepareStatement(query)) {
                        preparedStatement.setInt(1, userId);
                        preparedStatement.setString(2, accountType);
                        preparedStatement.setString(3, services.toString());
                        preparedStatement.executeUpdate();
                    }
                    JOptionPane.showMessageDialog(frame,
                            "Account Type: " + accountType + "\n" +
                                    "Card Number: " + cardNumber + "\n" +
                                    "PIN Number: " + pin + "\n" +
                                    "Services: " + services.toString(),
                            "Form Data",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException | ClassNotFoundException ex) {
                    System.out.println("Something went wrong: " + ex);
                    JOptionPane.showMessageDialog(null, "Something went wrong");
                }
            }
        });

        frame.add(accountTypeLabel);
        frame.add(accountTypeComboBox);
        frame.add(cardNumberLabel);
        frame.add(cardNumberField);
        frame.add(pinLabel);
        frame.add(pinField);
        frame.add(servicesLabel);
        frame.add(onlineBankingCheckBox);
        frame.add(atmCheckBox);
        frame.add(mobileBankingCheckBox);
        frame.add(submitButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void Forget_pin() {
        JFrame jf = new JFrame("Forget Pin");
        jf.setSize(new Dimension(800, 400));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - jf.getWidth()) / 2;
        int y = (screenSize.height - jf.getHeight()) / 2;
        jf.setLocation(x, y);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        jf.add(panel);

        JLabel forget = new JLabel("Enter Phone Number:");
        forget.setFont(new Font("Serif", Font.BOLD, 20));
        forget.setBounds(100, 40, 200, 30);
        JTextField forgetTextField = new JTextField();
        forgetTextField.setBounds(350, 43, 200, 28);
        JButton submit = new JButton("Submit");
        submit.setBounds(350, 100, 100, 30);
        JButton back = new JButton("Back");
        back.setBounds(500, 200, 80, 30);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.setVisible(false);
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Database db = new Database("jdbc:mysql://localhost:3306/", "root", "supersaiyan1000");
                    try (Statement st = db.conn.createStatement()) {
                        ResultSet rs = st.executeQuery("SELECT Pin, CardNumber FROM user WHERE PhoneNumber = '" + forgetTextField.getText() + "'");
                        if (rs.next()) {
                            JOptionPane.showMessageDialog(null, "Your Card Number and Pin is: " + rs.getString("CardNumber") + ", " + rs.getString("Pin"));
                        } else {
                            JOptionPane.showMessageDialog(null, "User doesn't exist");
                        }
                        rs.close();
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    System.out.println("Something went wrong: " + ex);
                    JOptionPane.showMessageDialog(null, "Something went wrong");
                }
            }
        });

        panel.add(submit);
        panel.add(forgetTextField);
        panel.add(forget);
        panel.add(back);
        jf.setVisible(true);
    }

    public static void signin(JTextField cardNumber, JTextField pinNumber, String table, JFrame f) throws SQLException, ClassNotFoundException {
        Database db = new Database("jdbc:mysql://localhost:3306/", "root", "supersaiyan1000");
        HashMap<String, String> map = new HashMap<>();
        map.put("CardNumber", cardNumber.getText());
        map.put("Pin", pinNumber.getText());

        boolean findingUser = db.find(map, "user");
        if (findingUser) {
            f.setVisible(false);
            Account_type(cardNumber.getText());
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("SBI Bank");
        f.setSize(1000, 800);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setMaximumSize(new Dimension(800, 800));

        JPanel jp = new JPanel();
        jp.setBackground(Color.BLACK);
        jp.setLayout(null);
        jp.setBounds(200, 100, 600, 600);

        ImageIcon imageIcon = new ImageIcon("BankingSystem/Assets/sbi-bank-logo-clipart.png");
        Image originalImage = imageIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setBounds(250, 20, 100, 100);
        jp.add(imageLabel);

        JLabel jta = new JLabel("Welcome to SBI Bank", SwingConstants.CENTER);
        jta.setFont(new Font("Serif", Font.BOLD, 24));
        jta.setForeground(Color.BLUE);
        jta.setBounds(150, 150, 300, 50);
        jp.add(jta);

        ImageIcon imageIcon2 = new ImageIcon("BankingSystem/Assets/Debit_card_image.jpg");
        Image cardImage = imageIcon2.getImage();
        Image resizedImage2 = cardImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
        JLabel imageLabel2 = new JLabel(resizedIcon2);
        imageLabel2.setBounds(200, 50, 100, 100);
        jp.add(imageLabel2);

        JTextField cardField = new JTextField();
        cardField.setBounds(200, 250, 250, 30);
        cardField.setEnabled(true);
        cardField.setEditable(true);
        cardField.setBackground(new Color(0, 0, 0));
        jp.add(cardField);
        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        cardNumberLabel.setForeground(Color.WHITE);
        cardNumberLabel.setBounds(50, 250, 200, 30);
        jp.add(cardNumberLabel);

        JTextField pinField = new JTextField();
        pinField.setBounds(200, 300, 250, 30);
        pinField.setEnabled(true);
        pinField.setEditable(true);
        pinField.setBackground(new Color(0, 0, 0));
        jp.add(pinField);
        JLabel pinLabel = new JLabel("Pin Code:");
        pinLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        pinLabel.setForeground(Color.WHITE);
        pinLabel.setBounds(50, 300, 200, 30);
        jp.add(pinLabel);

        JButton signIn = new JButton("Sign-In");
        signIn.setBounds(200, 375, 100, 30);
        signIn.setBackground(new Color(200, 200, 200));
        jp.add(signIn);
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tableName = "user";
                try {
                    signin(cardField, pinField, tableName, f);
                } catch (SQLException | ClassNotFoundException ex) {
                    System.out.println("Something went wrong: " + ex);
                    JOptionPane.showMessageDialog(null, "Something went wrong");
                }
            }
        });

        JButton forgetPin = new JButton("Forget Pin");
        forgetPin.setBackground(Color.GRAY);
        forgetPin.setBounds(350, 375, 100, 30);
        jp.add(forgetPin);
        forgetPin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Forget_pin();
            }
        });

        JButton newAcc = new JButton("Create New Account");
        newAcc.setBounds(270, 450, 120, 30);
        newAcc.setBackground(new Color(200, 200, 200));
        jp.add(newAcc);
        newAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                New_Account_interface(f);
                f.setVisible(false);
            }
        });

        int frameWidth = f.getWidth();
        int frameHeight = f.getHeight();
        int panelWidth = jp.getWidth();
        int panelHeight = jp.getHeight();
        int centerX = (frameWidth - panelWidth) / 2;
        int centerY = (frameHeight - panelHeight) / 2;
        jp.setBounds(centerX, centerY, 600, 600);

        f.add(jp);
        f.setVisible(true);
    }
}