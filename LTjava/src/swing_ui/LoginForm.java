package swing_ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connectMySQL.ConnectSQL;

public class LoginForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ConnectSQL connect = new ConnectSQL();
	JPanel upPanel;
	JPanel upPanel1;
	JPanel downPanel;
	JPanel downPanel1;

	JLabel usernameLabel;
	JLabel passwordLabel;

	JTextField usernameField;
	JPasswordField passwordField;
	JButton loginButton;

	public LoginForm() {
		initComponents();
	}

	private void initComponents() {
		connect.setConnection("swing_ui", "root", "");

		this.setIconImage(new ImageIcon("src/ei-ico.jpg").getImage());
		this.setLayout(new GridLayout(0, 1));

		upPanel = new JPanel(new BorderLayout());
		upPanel1 = new JPanel(new FlowLayout());
		downPanel = new JPanel(new FlowLayout());
		downPanel1 = new JPanel(new FlowLayout());

		usernameLabel = new JLabel("Username");
		passwordLabel = new JLabel("Password");

		usernameField = new JTextField(20);
		passwordField = new JPasswordField(20);
		loginButton = new JButton("Login");

		upPanel1.setSize(0, 50);
		upPanel1.add(usernameLabel);
		upPanel1.add(usernameField);
		upPanel.add(upPanel1, BorderLayout.SOUTH);

		downPanel.add(passwordLabel);
		downPanel.add(passwordField);

		downPanel1.add(loginButton);

		loginButton.addActionListener(e -> {
			try {
				handleLogin();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		loginButton.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						handleLogin();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		this.add(upPanel);
		this.add(downPanel);
		this.add(downPanel1);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Login");
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void handleLogin() throws SQLException {
		Connection con = connect.getConnection();
		if (con != null) {
			System.out.println("Connected");

			String username = usernameField.getText();
			String password = String.valueOf(passwordField.getPassword());

			PreparedStatement stmt = con.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
			stmt.setString(1, username);
			stmt.setString(2, password);

			ResultSet res = stmt.executeQuery();

			if (res.next()) {
				this.dispose();
				new StudentForm();
			} else {
				JOptionPane.showMessageDialog(this, "Login failed!");
			}

		} else {
			System.out.println("Not connected");
		}

		con.close();
	}

}
