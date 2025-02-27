
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

// class checking {
//     public static void main(String args[]) {
//         JFrame jf = new JFrame("ATM");
//         JPanel jp = new JPanel();
//         // Center the JWindow on the screen
//         jf.setSize(800, 800);
//         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//         int x = (screenSize.width - jf.getWidth()) / 2;
//         int y = (screenSize.height - jf.getHeight()) / 2;
//         jf.setLocation(x, y);
//         jp.setBounds(200, 200, 500, 500);
//         jp.setBackground(new Color(0, 0, 0));
//         jf.add(jp);
//         jf.setLayout(null);
//         jf.setVisible(true);
//         JLabel title = new JLabel("Select Account Type ", SwingConstants.CENTER);
//         title.setBounds(50, 3, 400, 30);
//         title.setForeground(Color.WHITE);
//         JButton Saving = new JButton("Saving ");
//         JButton Current = new JButton("Current ");
//         JButton FD = new JButton("Fixed Deposit ");
//         Saving.setBounds(350, 200, 100, 30);
//         Current.setBounds(350, 250, 100, 30);
//         FD.setBounds(350, 300, 100, 30);
//         jp.add(Saving);
//         jp.add(Current);
//         jp.add(FD);
//         title.setFont(new Font("Roman", Font.BOLD, 25));
//         jp.setLayout(null);
//         jp.add(title);
//     }
// }

class Account_type {
    public static void main(String args[]) {
        // Create a JFrame
        JFrame frame = new JFrame("Background Image Example");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Custom JPanel to set background image
        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("BankingSystem/Assets/ATM_UI_Background.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Set layout to null for absolute positioning
        backgroundPanel.setLayout(null);

        // Label for heading
        JLabel label = new JLabel("Account Services", SwingConstants.CENTER);
        label.setBounds(300, 300, 200, 20);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.WHITE);

        // Buttons for services
        JButton deposit = new JButton("Deposit");
        JButton fastCash = new JButton("FAST Cash");
        JButton pinChange = new JButton("Pin Change");
        JButton cashWithdrawl = new JButton("Cash Withdrawal");
        JButton miniStatement = new JButton("Mini Statement");
        JButton balanceEnquiry = new JButton("Balance Enquiry");
        JButton exit = new JButton("Exit");

        // Left-side buttons
        deposit.setBounds(240, 366, 100, 20);
        fastCash.setBounds(240, 396, 100, 20);
        pinChange.setBounds(240, 426, 100, 20);

        // Right-side buttons
        cashWithdrawl.setBounds(450, 366, 100, 20);
        miniStatement.setBounds(450, 396, 100, 20);
        balanceEnquiry.setBounds(450, 426, 100, 20);
        exit.setBounds(450, 456, 100, 20);

        // Add components to the panel
        backgroundPanel.add(label);
        backgroundPanel.add(deposit);
        backgroundPanel.add(fastCash);
        backgroundPanel.add(pinChange);
        backgroundPanel.add(cashWithdrawl);
        backgroundPanel.add(miniStatement);
        backgroundPanel.add(balanceEnquiry);
        backgroundPanel.add(exit);

        // Add the custom panel to the frame
        frame.add(backgroundPanel);

        // Make the frame visible
        frame.setVisible(true);
    }
}

class Database {
    Connection conn = null;

    Database(String database_url, String username, String password) throws SQLException, ClassNotFoundException {
        this.conn = DriverManager.getConnection(database_url, username, password); // Correctly assign the connection
        System.out.println("Connected to the database successfully!");
    }

    public void database_creation(String DBname) throws SQLException {
        Statement statement = conn.createStatement();
        String createDBQuery = "CREATE DATABASE IF NOT EXISTS " + DBname;
        statement.executeUpdate(createDBQuery);
        System.out.println("Database Created Successfully");
    }

    public void table_creation(String query) throws SQLException {
        Statement statement = conn.createStatement();
        String createTable = query;
        statement.executeUpdate(createTable);
        System.out.println("table created successfully ");
    }

    public boolean find(HashMap<String, String> map, String table) throws SQLException {
        String query;
        Statement st = conn.createStatement();
        int count = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            try {
                query = "SELECT * FROM " + table + " WHERE " + entry.getKey() + " = '" + entry.getValue() + "'";
                ResultSet rs = st.executeQuery(query);

                if (rs.next()) { // Check if there is a result
                    String cn = rs.getString(entry.getKey());

                    if (cn != null && !cn.isEmpty()) {
                        count++;
                    }
                }
            } catch (SQLException s) {
                System.out.println(s);
                JOptionPane.showMessageDialog(null, "Plz fill all the fields ");
            }
        }
        if (count == 2) {
            JOptionPane.showMessageDialog(null, "Welcome sir/madam");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Card Number or Pin is wrong. Please try again.");
        }
        // Throw the key
        return false;
    }
}

public class App {
    static TextField textfieldmaker(int y) {
        TextField tf = new TextField();
        tf.setBounds(250, y, 300, 20);
        return tf;
    }

    static void Account_Services() {
        JFrame frame = new JFrame("Background Image Example");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Custom JPanel to set background image
        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("BankingSystem/Assets/ATM_UI_Background.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Set layout to null for absolute positioning
        backgroundPanel.setLayout(null);

        // Label for heading
        JLabel label = new JLabel("Account Services", SwingConstants.CENTER);
        label.setBounds(300, 300, 200, 20);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.WHITE);

        // Buttons for services

        // Add the custom panel to the frame
        frame.add(backgroundPanel);

        // Make the frame visible
        frame.setVisible(true);
    }

    static void Account_type(String cardnumber) {

        JFrame frame = new JFrame("ATM");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMaximumSize(new Dimension(800, 800));
        // Custom JPanel to set background image
        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("BankingSystem/Assets/ATM_UI_Background.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Set layout and add components if needed
        backgroundPanel.setLayout(null);
        JLabel label = new JLabel("Account Type ", SwingConstants.CENTER);
        label.setBounds(300, 300, 200, 30);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(Color.WHITE);
        JButton Saving = new JButton("Saving");
        JButton Current = new JButton("Current");
        JButton FD = new JButton("Fixed Deposit");
        Saving.setBounds(450, 366, 90, 20);
        Current.setBounds(450, 396, 90, 20);
        FD.setBounds(450, 426, 90, 20);
        backgroundPanel.add(Current);
        backgroundPanel.add(FD);
        backgroundPanel.add(Saving);
        backgroundPanel.add(label, BorderLayout.CENTER);

        // Add the custom panel to the frame
        frame.add(backgroundPanel);
        Saving.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Saving.setVisible(false);
                Current.setVisible(false);
                FD.setVisible(false);
                label.setText("Services ");
                JButton deposit = new JButton("Deposit");
                JButton fastCash = new JButton("FAST Cash");
                JButton pinChange = new JButton("Pin Change");
                JButton cashWithdrawl = new JButton("Cash Withdrawal");
                JButton miniStatement = new JButton("Mini Statement");
                JButton balanceEnquiry = new JButton("Balance Enquiry");
                JButton exit = new JButton("Exit");

                // Left-side buttons
                deposit.setBounds(240, 366, 100, 20);
                fastCash.setBounds(240, 396, 100, 20);
                pinChange.setBounds(240, 426, 100, 20);

                // Right-side buttons
                cashWithdrawl.setBounds(450, 366, 100, 20);
                miniStatement.setBounds(450, 396, 100, 20);
                balanceEnquiry.setBounds(450, 426, 100, 20);
                exit.setBounds(450, 456, 100, 20);

                // Add components to the panel
                backgroundPanel.add(label);
                backgroundPanel.add(deposit);
                backgroundPanel.add(fastCash);
                backgroundPanel.add(pinChange);
                backgroundPanel.add(cashWithdrawl);
                backgroundPanel.add(miniStatement);
                backgroundPanel.add(balanceEnquiry);
                backgroundPanel.add(exit);
                pinChange.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        label.setText("Change Your Pin");
                        deposit.setVisible(false);
                        fastCash.setVisible(false);
                        cashWithdrawl.setVisible(false);
                        miniStatement.setVisible(false);
                        balanceEnquiry.setVisible(false);
                        exit.setVisible(false);
                        pinChange.setVisible(false);
                        JLabel Current_pin_label = new JLabel("Current Pin");
                        JTextField current_pin = new JTextField();
                        JLabel new_pin_label = new JLabel("New Pin");
                        JTextField new_pin = new JTextField();
                        Current_pin_label.setBounds(300, 320, 100, 30);
                        current_pin.setBounds(300, 350, 200, 30);
                        new_pin_label.setBounds(300, 380, 100, 30);
                        new_pin.setBounds(300, 400, 200, 30);
                        backgroundPanel.add(Current_pin_label);
                        backgroundPanel.add(current_pin);
                        backgroundPanel.add(new_pin_label);
                        backgroundPanel.add(new_pin);
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
                                cashWithdrawl.setVisible(true);
                                miniStatement.setVisible(true);
                                balanceEnquiry.setVisible(true);
                                exit.setVisible(true);
                                new_pin_label.setVisible(false);
                                new_pin.setVisible(false);
                                current_pin.setVisible(false);
                                Current_pin_label.setVisible(false);
                                submit.setVisible(false);
                                pinChange.setVisible(true);
                                back.setVisible(false);
                            }
                        });
                        submit.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    Database db = new Database("jdbc:mysql://localhost:3306/", "root",
                                            "supersaiyan1000");
                                    db.table_creation("use user_account");
                                    Statement st = db.conn.createStatement();
                                    ResultSet rs = st
                                            .executeQuery(
                                                    "Select Pin from user where CardNumber = '" + cardnumber + "'");
                                    if (rs.next()) {

                                        if (current_pin.getText().equals(Integer.toString(rs.getInt("Pin")))) {

                                            st.executeUpdate(
                                                    "Update user set Pin = " + new_pin.getText() + " where Pin = "
                                                            + current_pin.getText());
                                            JOptionPane.showMessageDialog(null, "Pin changed Successfully");
                                            label.setText("Services");
                                            deposit.setVisible(true);
                                            fastCash.setVisible(true);
                                            cashWithdrawl.setVisible(true);
                                            miniStatement.setVisible(true);
                                            balanceEnquiry.setVisible(true);
                                            exit.setVisible(true);
                                            pinChange.setVisible(true);
                                            current_pin.setVisible(false);
                                            Current_pin_label.setVisible(false);
                                            new_pin_label.setVisible(false);
                                            new_pin.setVisible(false);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Invalid Pin , try again");
                                        }

                                    }
                                } catch (SQLException | ClassNotFoundException c) {
                                    System.out.println("Something went wrong " + c);
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
                        label.setText("Your TranSaction Detail");
                        deposit.setVisible(false);
                        fastCash.setVisible(false);
                        cashWithdrawl.setVisible(false);
                        miniStatement.setVisible(false);
                        balanceEnquiry.setVisible(false);
                        exit.setVisible(false);
                        pinChange.setVisible(false);
                        JButton back = new JButton("Back");
                        back.setBounds(450, 455, 70, 20);
                        backgroundPanel.add(back);

                        try {
                            Database db = new Database("jdbc:mysql://localhost:3306/", "root", "supersaiyan1000");
                            db.table_creation("use user_account ");
                            Statement st = db.conn.createStatement();
                            ResultSet rs = st.executeQuery(
                                    " Select cardNumber , Amount , Time_deposited , Transaction_type from user_amount_info ");
                            DefaultTableModel tableModel = new DefaultTableModel();
                            ResultSetMetaData metaData = rs.getMetaData();
                            int columnCount = metaData.getColumnCount();

                            for (int i = 1; i <= columnCount; i++) {
                                tableModel.addColumn(metaData.getColumnName(columnCount));
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
                                    cashWithdrawl.setVisible(true);
                                    miniStatement.setVisible(true);
                                    balanceEnquiry.setVisible(true);
                                    exit.setVisible(true);
                                    pinChange.setVisible(true);
                                    back.setVisible(false);
                                    scrollPane.setVisible(false);
                                }
                            });
                        } catch (SQLException | ClassNotFoundException c) {
                            System.out.println(c);
                        }

                    }

                });
                balanceEnquiry.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Database db = new Database("jdbc:mysql://localhost:3306/", "root",
                                    "supersaiyan1000");
                            db.table_creation("use user_account");
                            Statement st = db.conn.createStatement();
                            ResultSet rs = st.executeQuery(
                                    "Select user_id from user where CardNumber = '" + cardnumber + "'");
                            int user_id = 0;
                            while (rs.next()) {
                                user_id = rs.getInt("user_id");
                            }
                            int totalamount_rem = 0;
                            int totalamount_dep = 0;
                            int totalamount_with = 0;
                            rs.close();
                            ResultSet rs_deposited = st.executeQuery(
                                    "SELECT SUM(Amount) AS Amount FROM user_amount_info WHERE Transaction_type = 'Credited'");
                            if (rs_deposited.next()) {
                                totalamount_dep = rs_deposited.getInt("Amount");
                            }
                            rs_deposited.close(); // Close ResultSet
                            // Fetch total withdrawn amount
                            ResultSet rs_withdrawen = st.executeQuery(
                                    "SELECT SUM(Amount) AS Amount FROM user_amount_info WHERE Transaction_type = 'Debited'");
                            if (rs_withdrawen.next()) {
                                totalamount_with = rs_withdrawen.getInt("Amount");
                            }
                            rs_withdrawen.close();
                            totalamount_rem = totalamount_dep - totalamount_with;
                            JLabel amount = new JLabel();
                            amount.setText("Total Amount Remaining is \n" + totalamount_rem);
                            label.setText("Balance Enquiry");
                            deposit.setVisible(false);
                            fastCash.setVisible(false);
                            cashWithdrawl.setVisible(false);
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
                                    cashWithdrawl.setVisible(true);
                                    miniStatement.setVisible(true);
                                    balanceEnquiry.setVisible(true);
                                    exit.setVisible(true);
                                    pinChange.setVisible(true);
                                }

                            });
                            amount.setBounds(280, 350, 300, 40);
                            amount.setFont(new Font("Roman", Font.ITALIC, 15));
                            amount.setForeground(Color.WHITE);
                            backgroundPanel.add(amount);
                        } catch (SQLException | ClassNotFoundException c) {
                            System.out.println("Something went wrong " + c);
                            JOptionPane.showMessageDialog(null, "something went wrong ");
                        }
                    }

                });
                deposit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        deposit.setVisible(false);
                        fastCash.setVisible(false);
                        cashWithdrawl.setVisible(false);
                        miniStatement.setVisible(false);
                        balanceEnquiry.setVisible(false);
                        exit.setVisible(false);
                        pinChange.setVisible(false);
                        label.setSize(300, 20);
                        label.setFont(new Font("Serif", Font.BOLD, 13));
                        label.setText("*Minimum amount should be 500");
                        // JLabel min_amount = new JLabel("Minimum amount should be 500",
                        // SwingConstants.CENTER);
                        // min_amount.setBounds(300, 200, 300, 20);
                        // min_amount.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
                        // backgroundPanel.add(min_amount);
                        JTextField Deposit_field = new JTextField();
                        Deposit_field.setBackground(Color.yellow);
                        Deposit_field.setBounds(300, 340, 200, 30);
                        JButton Deposit_amount = new JButton("Deposit");
                        Deposit_amount.setBounds(300, 400, 100, 25);
                        backgroundPanel.add(Deposit_amount);
                        JButton back = new JButton("Back");
                        back.setBounds(425, 400, 70, 25);
                        backgroundPanel.add(back);
                        back.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                label.setText("Services ");
                                label.setFont(new Font("Roman", Font.BOLD, 20));
                                label.setBounds(300, 300, 200, 40);
                                label.setAlignmentX(SwingConstants.CENTER);
                                Deposit_amount.setVisible(false);
                                Deposit_field.setVisible(false);
                                back.setVisible(false);
                                deposit.setVisible(true);
                                fastCash.setVisible(true);
                                cashWithdrawl.setVisible(true);
                                miniStatement.setVisible(true);
                                balanceEnquiry.setVisible(true);
                                exit.setVisible(true);
                                pinChange.setVisible(true);
                            }

                        });
                        Deposit_amount.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int amount = Integer.parseInt(Deposit_field.getText());
                                if (amount < 500) {
                                    JOptionPane.showMessageDialog(null, "Amount should be greater than 500");
                                } else {
                                    try {
                                        Database db = new Database("jdbc:mysql://localhost:3306/", "root",
                                                "supersaiyan1000");
                                        db.table_creation("use user_account");
                                        Statement st = db.conn.createStatement();
                                        ResultSet rs = st.executeQuery(
                                                "Select user_id from user where CardNumber = '" + cardnumber + "'");
                                        int user_id = 0;
                                        while (rs.next()) {
                                            user_id = rs.getInt("user_id");
                                        }

                                        LocalDateTime now = LocalDateTime.now();
                                        DateTimeFormatter formatter = DateTimeFormatter
                                                .ofPattern("yyyy/MM/dd HH:mm:ss");
                                        String formattedDate = now.format(formatter);
                                        System.out.println("Current Date and Time: " + now.toString());
                                        db.table_creation(
                                                "CREATE TABLE IF NOT EXISTS User_amount_info (" +
                                                        "    user_id INT," +
                                                        "    cardNumber VARCHAR(30)," +
                                                        "    Amount INT," +
                                                        "    Time_deposited VARCHAR(50), Transaction_type varchar(10),"
                                                        +
                                                        "    CONSTRAINT fk_user_amount_user FOREIGN KEY (user_id) REFERENCES user(user_id));");
                                        db.table_creation(
                                                "insert into User_amount_info(user_id , cardNumber,Amount , Time_deposited,Transaction_type) values("
                                                        + user_id + ",'" + cardnumber + "'," + amount + ",'"
                                                        + formattedDate
                                                        + "','Credited')");
                                        JOptionPane.showMessageDialog(null,
                                                amount + "has been deposited to your account ");
                                        System.out.println("Amount_info table has been created");
                                    } catch (SQLException | ClassNotFoundException c) {
                                        System.out.println("Something went wrong . Try again : " + c);
                                    }
                                }
                            }
                        });
                        backgroundPanel.add(Deposit_field);

                    }
                });
                cashWithdrawl.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        label.setVisible(false);
                        deposit.setVisible(false);
                        fastCash.setVisible(false);
                        cashWithdrawl.setVisible(false);
                        miniStatement.setVisible(false);
                        balanceEnquiry.setVisible(false);
                        exit.setVisible(false);
                        pinChange.setVisible(false);
                        JLabel max_withdrawal_amount = new JLabel("Maximum 10000 can be withdrawen at once");
                        JTextField amount_field = new JTextField();
                        JButton withdraw_Button = new JButton("Withdraw");
                        withdraw_Button.setBounds(300, 425, 100, 25);
                        JButton back = new JButton("Back");
                        back.setBounds(425, 425, 70, 25);
                        backgroundPanel.add(back);
                        back.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                max_withdrawal_amount.setVisible(false);
                                label.setText("Services ");
                                label.setFont(new Font("Roman", Font.BOLD, 20));
                                label.setBounds(300, 300, 200, 40);
                                label.setAlignmentX(SwingConstants.CENTER);
                                label.setVisible(true);
                                withdraw_Button.setVisible(false);
                                amount_field.setVisible(false);
                                back.setVisible(false);
                                deposit.setVisible(true);
                                fastCash.setVisible(true);
                                cashWithdrawl.setVisible(true);
                                miniStatement.setVisible(true);
                                balanceEnquiry.setVisible(true);
                                exit.setVisible(true);
                                pinChange.setVisible(true);
                            }

                        });
                        withdraw_Button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (Integer.parseInt(amount_field.getText()) > 10000) {
                                    JOptionPane.showMessageDialog(null, "Amount should be less than 10000");
                                } else {
                                    try {
                                        Database db = new Database("jdbc:mysql://localhost:3306/", "root",
                                                "supersaiyan1000");
                                        db.table_creation("use user_account");
                                        LocalDateTime now = LocalDateTime.now();
                                        DateTimeFormatter formatter = DateTimeFormatter
                                                .ofPattern("yyyy/MM/dd HH:mm:ss");
                                        String formattedDate = now.format(formatter);
                                        Statement st = db.conn.createStatement();
                                        ResultSet rs = st.executeQuery(
                                                "Select user_id from user where CardNumber = '" + cardnumber + "'");

                                        int user_id = 0;
                                        while (rs.next()) {
                                            user_id = rs.getInt("user_id");
                                        }
                                        int totalamount_rem = 0;
                                        int totalamount_dep = 0;
                                        int totalamount_with = 0;
                                        rs.close();
                                        ResultSet rs_deposited = st.executeQuery(
                                                "SELECT SUM(Amount) AS Amount FROM user_amount_info WHERE Transaction_type = 'Credited'");
                                        if (rs_deposited.next()) {
                                            totalamount_dep = rs_deposited.getInt("Amount");
                                        }
                                        rs_deposited.close(); // Close ResultSet
                                        // Fetch total withdrawn amount
                                        ResultSet rs_withdrawen = st.executeQuery(
                                                "SELECT SUM(Amount) AS Amount FROM user_amount_info WHERE Transaction_type = 'Debited'");
                                        if (rs_withdrawen.next()) {
                                            totalamount_with = rs_withdrawen.getInt("Amount");
                                        }
                                        rs_withdrawen.close();
                                        totalamount_rem = totalamount_dep - totalamount_with;
                                        int amount = Integer.parseInt(amount_field.getText());
                                        if (amount > totalamount_rem) {
                                            JOptionPane.showMessageDialog(null,
                                                    "You don't have suffient balance \n your current Balance is "
                                                            + totalamount_rem);
                                        } else {
                                            db.table_creation(
                                                    "Insert into user_amount_info(user_id ,cardNumber ,  Amount , Time_deposited ,Transaction_type) values ("
                                                            + user_id + ",'" + cardnumber + "'," + amount + ",'"
                                                            + formattedDate
                                                            + "','Debited')");
                                            System.out.println("withdraw completed ");
                                            JOptionPane.showMessageDialog(null, amount + " Amount withdrawn");
                                            JOptionPane.showMessageDialog(null,
                                                    totalamount_rem - amount + "Amount Remaining");
                                        }
                                    } catch (SQLException | ClassNotFoundException c) {
                                        System.out.println(c);
                                    }

                                }
                            }
                        });
                        max_withdrawal_amount.setForeground(Color.yellow);
                        max_withdrawal_amount.setBounds(320, 300, 250, 20);
                        max_withdrawal_amount.setFont(new Font("Serif", Font.BOLD, 11));
                        amount_field.setBounds(300, 350, 200, 25);

                        backgroundPanel.add(max_withdrawal_amount);
                        backgroundPanel.add(amount_field);
                        backgroundPanel.add(withdraw_Button);
                    }
                });
            }
        });
        // Make the frame visible
        frame.setVisible(true);
    }

    static void UI_interface() {
        // JFrame
    }

    static void New_Account_interface(JFrame f) {
        JFrame jf = new JFrame();

        jf.setSize(800, 800);
        // Removed jf.setMaximizedBounds(new Rectangle(800, 800));
        // jf.setBounds(null);
        // Center the JWindow on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - jf.getWidth()) / 2;
        int y = (screenSize.height - jf.getHeight()) / 2;
        jf.setLocation(x, y);
        // Add content to the new JWindow
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        JLabel newAcc = new JLabel("Create New SBI Account", SwingConstants.CENTER);
        newAcc.setForeground(Color.WHITE);
        newAcc.setFont(new Font("Serif", Font.BOLD, 20));
        newAcc.setBounds(300, 2, 250, 100);
        // go back button
        JButton go_back = new JButton("GO back");
        go_back.setBounds(100, 1, 100, 30);
        go_back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(true);
                jf.setVisible(false);
            }
        });
        JLabel name = new JLabel("Name");
        JLabel surname = new JLabel("LastName");
        JLabel father = new JLabel("Father Name ");
        JLabel Address = new JLabel("Address");
        JLabel Dob = new JLabel("Date of Birth");
        JLabel Gender = new JLabel("Gender");
        JLabel Email = new JLabel("Email");
        JLabel Marital_status = new JLabel("Marital Status");
        JLabel city = new JLabel("City");
        JLabel Phone = new JLabel("Phone Num.");
        JLabel State = new JLabel("State ");
        JLabel Pin = new JLabel("Pin");
        name.setBounds(100, 70, 100, 80);
        surname.setBounds(100, 120, 100, 80);
        father.setBounds(100, 170, 100, 80);
        Address.setBounds(100, 220, 100, 80);
        Dob.setBounds(100, 270, 100, 80);
        Gender.setBounds(100, 320, 100, 80);
        Email.setBounds(100, 370, 100, 80);
        Marital_status.setBounds(100, 420, 100, 80);
        city.setBounds(100, 470, 100, 80);
        Phone.setBounds(100, 520, 100, 80);
        State.setBounds(100, 570, 100, 80);
        Pin.setBounds(100, 620, 100, 80);
        name.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
        surname.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
        father.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
        Address.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
        Dob.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
        Gender.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
        Email.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
        Marital_status.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
        city.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
        Phone.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
        State.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
        Pin.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));
        TextField name_textfield = textfieldmaker(100);
        TextField surname_textfield = textfieldmaker(150);
        TextField Father_textfield = textfieldmaker(200);
        TextField Address_textfield = textfieldmaker(250);

        TextField Dob_textfield = textfieldmaker(300);

        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        JRadioButton others = new JRadioButton("Rather Not say");
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
        TextField Email_textfield = textfieldmaker(400);
        JRadioButton married = new JRadioButton("Married");
        JRadioButton nmarried = new JRadioButton("Unmarried");
        married.setBounds(250, 450, 100, 20);
        nmarried.setBounds(450, 450, 100, 20);
        ButtonGroup maritalStatus = new ButtonGroup();
        maritalStatus.add(married);
        maritalStatus.add(nmarried);
        panel.add(married);
        panel.add(nmarried);
        TextField city_textfield = textfieldmaker(500);
        TextField pincode_textfield = textfieldmaker(550);
        TextField state_textfield = textfieldmaker(600);
        TextField Pin_textfield = textfieldmaker(650);
        // Submit Button
        JButton submit = new JButton("Submit");
        panel.add(Pin_textfield);
        submit.setBounds(300, 700, 200, 30);
        submit.addActionListener(new ActionListener() {
            private static final HashSet<String> generatedCards = new HashSet<>(); // Store unique cards
            private static final Random random = new Random();

            // Generate a unique 16-digit credit card number
            public static String generateUniqueCreditCard() {
                String cardNumber;
                do {
                    StringBuilder card = new StringBuilder();
                    for (int i = 0; i < 16; i++) {
                        card.append(random.nextInt(10)); // Append a random digit (0-9)
                    }
                    cardNumber = card.toString();
                } while (generatedCards.contains(cardNumber)); // Ensure it's unique
                generatedCards.add(cardNumber);
                return cardNumber;
            }

            boolean add = false;
            int userId = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                // taking Data of the user
                try {
                    String name = name_textfield.getText();
                    String surname = surname_textfield.getText();
                    String Address = Address_textfield.getText();
                    String Dob = Dob_textfield.getText();
                    String Gender;
                    if (male.isSelected()) {
                        Gender = "Male";
                    } else if (female.isSelected()) {
                        Gender = "Female";
                    } else {
                        Gender = "Rather Not say";
                    }
                    String Email = Email_textfield.getText();
                    String Ms;
                    if (married.isSelected()) {
                        Ms = "Married";
                    } else {
                        Ms = "Unmarried";
                    }
                    String city = city_textfield.getText().trim();
                    String PinCode = pincode_textfield.getText();
                    String state = state_textfield.getText();
                    String userCardNumber = generateUniqueCreditCard();
                    int pin = Integer.parseInt(Pin_textfield.getText());
                    // database addition
                    try {
                        Database creating_db = new Database(
                                "jdbc:mysql://localhost:3306/",
                                "root",
                                "supersaiyan1000");
                        creating_db.database_creation("User_Account");
                        creating_db.table_creation("use User_Account");
                        creating_db.table_creation(
                                "create table if not exists user (user_id INT AUTO_INCREMENT primary key,name varchar(30) ,surname varchar(30) , Address varchar(50) ,Dob varchar(50) , Gender varchar(30) ,Email varchar(40),Marital_status varchar(20) , city varchar(20) , PhoneNumber varchar(20) , State varchar(20) , CardNumber varchar(20) , Pin int(6))");
                        creating_db.table_creation(
                                "insert into user(name , surname ,Address , Dob , Gender , Email , Marital_status , city , PhoneNumber , State , CardNumber,Pin) values('"
                                        + name + "','" + surname + "','"
                                        + Address + "','" + Dob + "','" + Gender + "','" + Email + "','" + Ms + "','"
                                        + city
                                        + "','"
                                        + PinCode + "','" + state + "','" + userCardNumber + "'," + pin + ");");
                        // JOptionPane.showMessageDialog(null, "User has been Created , Go and Login
                        // ,Out ");
                        Statement statement = creating_db.conn.createStatement();
                        ResultSet rs = statement.executeQuery("Select user_id from user");
                        while (rs.next()) {
                            userId = rs.getInt("user_id");
                        }
                        jf.setVisible(false);
                        add = true;

                    } catch (SQLException v) {
                        System.out.println("Something went wrong " + v);
                    } catch (ClassNotFoundException c) {
                        System.out.println("Something went wrong " + c);
                    }
                } catch (NumberFormatException z) {
                    JOptionPane.showMessageDialog(null, "Something went wrong try again");
                }
                if (add) {
                    New_Account_interface_2(userId, f);
                }
            }
        });
        panel.add(Pin);
        panel.add(submit);
        panel.add(name_textfield);
        panel.add(surname_textfield);
        panel.add(Father_textfield);
        panel.add(Address_textfield);
        panel.add(Dob_textfield);
        panel.add(Email_textfield);
        panel.add(city_textfield);
        panel.add(pincode_textfield);
        panel.add(state_textfield);
        panel.add(name);
        panel.add(surname);
        panel.add(father);
        panel.add(Address);
        panel.add(Dob);
        panel.add(Gender);
        panel.add(Email);
        panel.add(Marital_status);
        panel.add(city);
        panel.add(Phone);
        panel.add(State);
        panel.add(go_back);
        panel.add(newAcc);
        panel.setBackground(new Color(12, 200, 250));
        panel.setSize(400, 500);
        panel.setLayout(null);
        jf.add(panel);
        // Display the new JWindow
        jf.setVisible(true);
    }

    public static void New_Account_interface_2(int userId, JFrame f) {
        JFrame additionalForm = new JFrame("Additional Information");
        additionalForm.setSize(800, 800);

        // Center the new form
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - additionalForm.getWidth()) / 2;
        int y = (screenSize.height - additionalForm.getHeight()) / 2;
        additionalForm.setLocation(x, y);

        // Panel for the form
        JPanel panel = new JPanel();
        panel.setBackground(new Color(150, 200, 250));
        panel.setLayout(null);

        // Title Label
        JLabel title = new JLabel("Additional Information", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setBounds(250, 20, 300, 40);
        panel.add(title);

        // Labels and Fields for Additional Information
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

        JComboBox<String> categoryComboBox = new JComboBox<>(new String[] { "General", "OBC", "SC", "ST", "Other" });
        categoryComboBox.setBounds(250, 250, 300, 30);
        panel.add(categoryComboBox);

        JLabel seniorCitizenLabel = new JLabel("Senior Citizen:");
        seniorCitizenLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        seniorCitizenLabel.setBounds(100, 300, 150, 30);
        panel.add(seniorCitizenLabel);

        JComboBox<String> seniorCitizenComboBox = new JComboBox<>(new String[] { "Yes", "No" });
        seniorCitizenComboBox.setBounds(250, 300, 300, 30);
        panel.add(seniorCitizenComboBox);

        JLabel existingAccountLabel = new JLabel("Existing Account:");
        existingAccountLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        existingAccountLabel.setBounds(100, 350, 150, 30);
        panel.add(existingAccountLabel);

        JComboBox<String> existingAccountComboBox = new JComboBox<>(new String[] { "Yes", "No" });
        existingAccountComboBox.setBounds(250, 350, 300, 30);
        panel.add(existingAccountComboBox);

        // Submit Button
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

                    // Validation for Aadhaar and PAN (example logic)
                    if (aadhaar.length() != 12 || !aadhaar.matches("\\d+")) {
                        JOptionPane.showMessageDialog(null, "Invalid Aadhaar number!");
                        return;
                    }
                    if (pan.length() != 10 || !pan.matches("[A-Z]{5}\\d{4}[A-Z]")) {
                        JOptionPane.showMessageDialog(null, "Invalid PAN number!");
                        return;
                    }

                    // Save data to the database
                    try {
                        Database db = new Database(
                                "jdbc:mysql://localhost:3306/",
                                "root",
                                "supersaiyan1000");
                        db.database_creation("User_Account");
                        db.table_creation("use User_Account");
                        db.table_creation(
                                "create table if not exists additional_info (" +
                                        "aadhaar_card varchar(12), " +
                                        "pan_card varchar(10), " +
                                        "income varchar(20), " +
                                        "category varchar(20), " +
                                        "senior_citizen varchar(3), " +
                                        "existing_account varchar(3)," +
                                        "user_id int,"
                                        + "CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE)");
                        db.table_creation(
                                "insert into additional_info (aadhaar_card, pan_card, income, category, senior_citizen, existing_account,user_id) values ('"
                                        +
                                        aadhaar + "', '" + pan + "', '" + income + "', '" + category + "', '"
                                        + seniorCitizen + "', '" + existingAccount + "'," + userId + ")");
                        Statement st = db.conn.createStatement();
                        ResultSet rs = st.executeQuery("select CardNumber from user where CardNumber = " + userId);
                        String card_number;
                        while (rs.next()) {
                            card_number = rs.getString("CardNumber");
                            JOptionPane.showMessageDialog(null,
                                    " information saved successfully!,\n You can login Now with Card Numeber\n"
                                            + card_number);
                            break;
                        }
                        additionalForm.setVisible(false);
                    } catch (SQLException ex) {
                        System.out.println("Error saving to database: " + ex.getMessage());
                    } catch (ClassNotFoundException ex) {
                        System.out.println("Database connection error: " + ex.getMessage());
                    }
                } catch (Throwable ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
                // f.setVisible(true);
                try {
                    Database db = new Database("jdbc:mysql://localhost:3306/",
                            "root",
                            "supersaiyan1000");
                    db.table_creation("use user_account");
                    Statement st = db.conn.createStatement();
                    ResultSet rs = st.executeQuery("select CardNumber from user where CardNumber = " + userId);
                    String card_number = "";
                    int pin = 0;
                    while (rs.next()) {
                        card_number = rs.getString("CardNumber");
                        pin = rs.getInt("Pin");
                        JOptionPane.showMessageDialog(null,
                                " information saved successfully!,\n You can login Now with Card Numeber\n"
                                        + card_number);
                        break;
                    }
                    New_Account_interface3(card_number, pin, userId);
                } catch (SQLException s) {
                    JOptionPane.showMessageDialog(null, "Something went wrong");
                    System.out.print("Error " + s);

                } catch (ClassNotFoundException c) {
                    JOptionPane.showMessageDialog(null, "Something went wrong");
                    System.out.println("Error " + c);
                }
            }
        });
        panel.add(submitButton);
        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 450, 100, 30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // f.setVisible(true);
                additionalForm.setVisible(false);
            }
        });
        panel.add(backButton);
        additionalForm.add(panel);
        additionalForm.setVisible(true);
    }

    public static void New_Account_interface3(String card_number, int Pin, int user_id) {
        // Create the frame
        JFrame frame = new JFrame("User Account Preferences");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800); // Set frame size
        frame.setLayout(null); // Set layout to null

        // Components for Account Type
        JLabel accountTypeLabel = new JLabel("Account Type:");
        accountTypeLabel.setBounds(50, 50, 200, 30);
        String[] accountTypes = { "Savings", "Current", "Fixed Deposit" };
        JComboBox<String> accountTypeComboBox = new JComboBox<>(accountTypes);
        accountTypeComboBox.setBounds(250, 50, 200, 30);

        // Components for Card Number
        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberLabel.setBounds(50, 100, 200, 30);
        JLabel cardNumberField = new JLabel();
        cardNumberField.setBounds(250, 100, 200, 30);

        // Components for PIN Number
        JLabel pinLabel = new JLabel("PIN Number:");
        pinLabel.setBounds(50, 150, 200, 30);
        JLabel pinField = new JLabel();
        pinField.setBounds(250, 150, 200, 30);

        // Components for Services Selection
        JLabel servicesLabel = new JLabel("Services:");
        servicesLabel.setBounds(50, 200, 200, 30);
        JCheckBox onlineBankingCheckBox = new JCheckBox("Online Banking");
        onlineBankingCheckBox.setBounds(250, 200, 200, 30);
        JCheckBox atmCheckBox = new JCheckBox("ATM Access");
        atmCheckBox.setBounds(250, 240, 200, 30);
        JCheckBox mobileBankingCheckBox = new JCheckBox("Mobile Banking");
        mobileBankingCheckBox.setBounds(250, 280, 200, 30);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(300, 350, 100, 30);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Collecting data from form components
                String accountType = (String) accountTypeComboBox.getSelectedItem();
                String cardNumber = cardNumberField.getText();
                String pin = new String(pinField.getText());
                StringBuilder services = new StringBuilder();
                if (onlineBankingCheckBox.isSelected())
                    services.append("Online_Banking_");
                if (atmCheckBox.isSelected())
                    services.append("ATM_Access_");
                if (mobileBankingCheckBox.isSelected())
                    services.append("Mobile_Banking");
                try {
                    Database db = new Database("jdbc:mysql://localhost:3306/", "root", "supersaiyan1000");
                    db.table_creation("use user_account");
                    db.table_creation(
                            "create table if not exists Account_info (user_id int, Account_type varchar(20), Services varchar(50), CONSTRAINT uk_user FOREIGN KEY (user_id) REFERENCES additional_info(user_id) ON DELETE CASCADE)");

                    String query = "INSERT INTO Account_info(user_id, Account_type, Services) VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement = db.conn.prepareStatement(query);
                    preparedStatement.setInt(1, user_id);
                    preparedStatement.setString(2, accountType);
                    preparedStatement.setString(3, services.toString());
                    preparedStatement.executeUpdate();

                } catch (SQLException s) {
                    JOptionPane.showMessageDialog(null, "Something went wrong");
                } catch (ClassNotFoundException t) {
                    JOptionPane.showMessageDialog(null, "Something went wrong");
                }
                JOptionPane.showMessageDialog(frame,
                        "Account Type: " + accountType + "\n" +
                                "Card Number: " + cardNumber + "\n" +
                                "PIN Number: " + pin + "\n" +
                                "Services: " + services.toString(),
                        "Form Data",
                        JOptionPane.INFORMATION_MESSAGE);
                frame.setVisible(true);
            }
        });

        // Add components to the frame
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

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }

    public static void Forget_pin() {
        //
        JFrame jf = new JFrame("Forget Pin ");
        jf.setSize(new Dimension(800, 400));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - jf.getWidth()) / 2;
        int y = (screenSize.height - jf.getHeight()) / 2;
        jf.setLocation(x, y);
        JPanel panel = new JPanel();
        jf.add(panel);
        JLabel forget = new JLabel("Enter Phone Number : ");
        forget.setFont(new Font("Serif", Font.BOLD, 20));
        forget.setBounds(100, 40, 200, 30);
        JTextField forget_textField = new JTextField();
        forget_textField.setBounds(350, 43, 200, 28);
        JButton submit = new JButton("Submit");
        submit.setBounds(350, 100, 100, 30);
        JButton back = new JButton("Back");
        back.setBounds(500, 200, 80, 30);
        panel.add(back);
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
                    Database db = new Database("jdbc:mysql://localhost:3306/user_account",
                            "root",
                            "supersaiyan1000");
                    Statement st = db.conn.createStatement();
                    ResultSet rs = st
                            .executeQuery(
                                    "Select Pin,CardNumber from user where PhoneNumber = '" + forget_textField.getText()
                                            + "'");
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null,
                                "Your CardNumber and Pin is :" + rs.getString("CardNumber") + " , "
                                        + rs.getString("Pin"));
                    } else {
                        JOptionPane.showMessageDialog(null, "User doens't exist ");
                    }
                } catch (SQLException sql) {
                    System.out.println("Something went wrong . Try again");
                } catch (ClassNotFoundException cnfe) {
                    System.out.println("Something went wrong , Try again");
                }

            }
        });

        panel.add(submit);
        panel.add(forget_textField);
        panel.add(forget);
        panel.setLayout(null);
        jf.setVisible(true);
    }

    public static void signin(JTextField card_number, JTextField PinNumber, String table, JFrame f)
            throws SQLException, ClassNotFoundException {

        Database db = new Database("jdbc:mysql://localhost:3306/user_account",
                "root",
                "supersaiyan1000");
        HashMap<String, String> map = new HashMap<>();
        map.put("CardNumber", card_number.getText());
        map.put("Pin", PinNumber.getText());

        boolean finding_user = db.find(map, "user");
        if (finding_user) {
            f.setVisible(false);
            Account_type(card_number.getText());

        }
    }

    public static void main(String[] args) {
        // Creating frame object

        JFrame f = new JFrame();
        f.setSize(1000, 800);
        f.setLayout(null); // Keep using null layout
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setMaximumSize(new Dimension(800, 800));

        // ImageIcon object
        ImageIcon imageIcon = new ImageIcon("BankingSystem/Assets/sbi-bank-logo-clipart.png");

        // Creating panel object
        JPanel jp = new JPanel();
        jp.setBackground(Color.BLACK);
        jp.setLayout(null); // Use null layout for precise positioning of components
        jp.setBounds(200, 100, 600, 600); // Set initial size and position for the panel

        // Resize the image
        Image originalImage = imageIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // JLabel for the image
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setBounds(250, 20, 100, 100); // Position image at the top center of the panel
        jp.add(imageLabel);

        // JLabel for the welcome text
        JLabel jta = new JLabel("Welcome to SBI Bank", SwingConstants.CENTER);
        jta.setFont(new Font("Serif", Font.BOLD, 24));
        jta.setForeground(Color.BLUE);
        jta.setBounds(150, 150, 300, 50); // Position text below the image
        jp.add(jta);

        // Add the Card Number area
        ImageIcon imageicon2 = new ImageIcon("BankingSystem/Assets/Debit_card_image.jpg");
        Image card_image = imageicon2.getImage();
        Image resizedImage2 = card_image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
        JLabel imageLabel_2 = new JLabel(resizedIcon2);
        imageLabel_2.setBounds(200, 50, 10, 10); // Position image at the top center of the panel
        jp.add(imageLabel_2);
        // Card Number
        JTextField card_field = new JTextField();
        card_field.setBounds(200, 250, 250, 30);
        card_field.setEnabled(true);
        card_field.setEditable(true);
        card_field.setBackground(new Color(0, 0, 0));
        jp.add(card_field);
        JLabel cardNumber = new JLabel("Card Number: ");
        cardNumber.setFont(new Font("SansSerif", Font.PLAIN, 18));
        cardNumber.setForeground(Color.WHITE);
        cardNumber.setBounds(50, 250, 200, 30); // Position card number label
        jp.add(cardNumber);
        // Pin Code
        JTextField card_field_2 = new JTextField();
        card_field_2.setBounds(200, 300, 250, 30);
        card_field_2.setEnabled(true);
        card_field_2.setEditable(true);
        card_field_2.setBackground(new Color(0, 0, 0));
        jp.add(card_field_2);
        JLabel cardNumber_2 = new JLabel("Pin Code: ");
        cardNumber_2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        cardNumber_2.setForeground(Color.WHITE);
        cardNumber_2.setBounds(50, 300, 200, 30); // Position card number label
        jp.add(cardNumber_2);

        // Buttons
        // Sign in button
        JButton signin = new JButton("Sign-In");
        signin.setBounds(200, 375, 100, 30);
        signin.setBackground(new Color(200, 200, 200));
        jp.add(signin);
        signin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String table_name = "user";
                try {
                    signin(card_field, card_field_2, table_name, f);
                } catch (SQLException t) {
                    System.out.println("Something went wrong : " + t);
                } catch (ClassNotFoundException c) {
                    System.out.println("Something went wrong :" + c);
                }
            }
        });
        // Forget Pin
        Button forgetpin = new Button("Forget-pin");
        forgetpin.setBackground(Color.gray);
        forgetpin.setBounds(350, 375, 100, 30);
        jp.add(forgetpin);

        // Create new Account
        Button newAcc = new Button("Create new Account");
        newAcc.setBounds(270, 450, 120, 30);
        newAcc.setBackground(new Color(200, 200, 200));
        newAcc.setActionCommand("Command");
        jp.add(newAcc);
        newAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                New_Account_interface(f);
                f.setVisible(false);
            }
        });

        forgetpin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Forget_pin();
            }
        });
        // Center the panel manually
        int frameWidth = f.getWidth();
        int frameHeight = f.getHeight();
        int panelWidth = jp.getWidth();
        int panelHeight = jp.getHeight();

        int centerX = (frameWidth - panelWidth) / 2;
        int centerY = (frameHeight - panelHeight) / 2;

        jp.setBounds(centerX, centerY, 600, 600); // Update bounds to center the panel
        // Add the panel to the frame
        f.add(jp);
        // Make the frame visible
        f.setVisible(true);
    }
}
