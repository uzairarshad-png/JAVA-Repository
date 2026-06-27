/**
 *
 * @author DANISH LAPTOP
 */
// ===============================
// COMPLETE JAVA PROJECT (SIMPLIFIED)
// ===============================

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.event.*;

public class MainApp {
    public static void main(String[] args) {
        new LoginForm();
    }
}

// ---------- 1. DB CONNECTION ----------
class DBConnection {
    public static Connection getConnection() {
        try {
            String url = "jdbc:ucanaccess://C:/StudentEnrollment.accdb";
            return DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

// ---------- 2. LOGIN FORM ----------
class LoginForm extends JFrame {
    JTextField userField;
    JPasswordField passField;
    JButton loginBtn;

    LoginForm() {
        setTitle("Admin Login");
        setSize(300,200);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(20,30,80,25);
        add(userLabel);

        userField = new JTextField();
        userField.setBounds(100,30,150,25);
        add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(20,70,80,25);
        add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(100,70,150,25);
        add(passField);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(100,110,100,30);
        add(loginBtn);

        loginBtn.addActionListener(e -> login());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    void login() {
        String user = userField.getText();
        String pass = new String(passField.getPassword());

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM Admin WHERE username=? AND password=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, user);
            pst.setString(2, pass);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Success");
                new Dashboard();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Login");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

// ---------- 3. DASHBOARD ----------
class Dashboard extends JFrame {
    Dashboard() {
        setTitle("Dashboard");
        setSize(300,200);
        setLayout(null);

        JButton manageBtn = new JButton("Manage Students");
        manageBtn.setBounds(50,50,200,40);
        add(manageBtn);

        manageBtn.addActionListener(e -> new StudentForm());

        setVisible(true);
    }
}

// ---------- 4. STUDENT FORM (CRUD) ----------
class StudentForm extends JFrame {
    JTextField nameField, courseField, idField;
    JTable table;
    DefaultTableModel model;

    StudentForm() {
        setTitle("Student Management");
        setSize(600,400);
        setLayout(null);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(20,20,50,25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(80,20,100,25);
        add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20,60,50,25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(80,60,150,25);
        add(nameField);

        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setBounds(20,100,60,25);
        add(courseLabel);

        courseField = new JTextField();
        courseField.setBounds(80,100,150,25);
        add(courseField);

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(250,20,100,30);
        add(addBtn);

        JButton updateBtn = new JButton("Update");
        updateBtn.setBounds(250,60,100,30);
        add(updateBtn);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(250,100,100,30);
        add(deleteBtn);

        model = new DefaultTableModel(new String[]{"ID","Name","Course"},0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20,150,550,200);
        add(sp);

        loadData();

        addBtn.addActionListener(e -> addStudent());
        updateBtn.addActionListener(e -> updateStudent());
        deleteBtn.addActionListener(e -> deleteStudent());

        setVisible(true);
    }

    void loadData() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Students");

            model.setRowCount(0);
            while(rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("student_id"),
                    rs.getString("first_name"),
                    rs.getString("course")
                });
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    void addStudent() {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO Students(first_name,course) VALUES(?,?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, nameField.getText());
            pst.setString(2, courseField.getText());

            pst.executeUpdate();
            loadData();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    void updateStudent() {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE Students SET first_name=?, course=? WHERE student_id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, nameField.getText());
            pst.setString(2, courseField.getText());
            pst.setInt(3, Integer.parseInt(idField.getText()));

            pst.executeUpdate();
            loadData();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    void deleteStudent() {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "DELETE FROM Students WHERE student_id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, Integer.parseInt(idField.getText()));
            pst.executeUpdate();

            loadData();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
