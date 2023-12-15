package swing_ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectSQL {

	Connection con = null;

	public ConnectSQL() {
	}
	public ConnectSQL(String databaseName, String user, String pass) {
		setConnection(databaseName, user, pass);
	}
	
	public void setConnection(String databaseName, String user, String pass) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(databaseName, user, pass);
			System.out.println("Connection Success");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found " + e);
		} catch (SQLException e) {
			System.out.println("SQL Exception " + e);
		}
	}

	public Connection getConnection() {
		return this.con;
	}

	public ResultSet getAllData(String table) {
		ResultSet res = null;
		String sql = "SELECT * FROM " + table;
		try {
			Statement stmt = con.createStatement();
			res = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("SQL Exception " + e);
		}
		return res;
	}
	
	public ResultSet getDataByID(String table, String id) {
		ResultSet res = null;
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM ? WHERE code = ?");
			stmt.setString(1, table);
			stmt.setString(2, id);
			res = stmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("SQL Exception " + e);
		}
		return res;
	}
	
	public ResultSet findDataByField(String table, String field, String value) {
		ResultSet res = null;
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM ? WHERE ? like %?%");
			stmt.setString(1, table);
			stmt.setString(2, field);
			stmt.setString(3, value);
			res = stmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("SQL Exception " + e);
		}
		return res;
	}
}
