package bt16_11;

import java.awt.Color;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class TestConnection {
	public static void main(String[] args) throws SQLException {
		ResultSet rs;
//		Object data[][] = new Object[100][3];
//		Object column[] = {"ID", "Name", "Address"};
//		int currentRow = 0;
		Vector<String> rowHeader = new Vector<String>();
		rowHeader.add("ID");
		rowHeader.add("Name");
		rowHeader.add("Address");
		rowHeader.add("Salary");
		DefaultTableModel model = new DefaultTableModel(rowHeader, 0);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems", "root", "");
			System.out.println("Connection Success");
			String sql = "select * from employees";
			Statement st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
//				System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
//				data[currentRow][0] = rs.getInt(1);
//				data[currentRow][1] = rs.getString(2);
//				data[currentRow][2] = rs.getString(3);
//				currentRow++;
				Vector<String> row = new Vector<String>();
				row.add(rs.getInt(1) + "");
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getString(4));
				model.addRow(row);
			}
			st.close();
			con.close();
			System.out.println("Connection Closed");
		}
		catch (ClassNotFoundException e) {
			System.out.println("Class not found " + e);
		}
		
		
		JTable table = new JTable();
		table.setModel(model);
		JScrollPane sp = new JScrollPane(table);
		JFrame frame = new JFrame();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel(new GridLayout(1,1));
		
		
		panel1.setBackground(Color.red);
		panel2.setBackground(Color.blue);

		panel2.add(sp);
		
		frame.setLayout(new GridLayout(2,1));
		frame.setSize(1280, 720);
		frame.add(panel1);
		frame.add(panel2);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
	}
}
