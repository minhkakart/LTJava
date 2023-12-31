package connectMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectSQL {
	
	private static final String DATABASE_NAME = "jdbc:mysql://localhost:3306/";
	
	Connection con = null;

	public ConnectSQL() {
	}
	
	public ConnectSQL(String databaseName) {
		
		setConnection(databaseName, "root", "");
	}


	public ConnectSQL(String databaseName, String user, String pass) {
		
		setConnection(databaseName, user, pass);
	}

	public void setConnection(String databaseName, String user, String pass) {
		StringBuilder databaseUrl = new StringBuilder(DATABASE_NAME);
		databaseUrl.append(databaseName);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(databaseUrl.toString(), user, pass);
			System.out.println("Connection Success");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return this.con;
	}
	
	public int executeUpdate(String sql) {
		int res = 0;
		try {
			Statement stmt = con.createStatement();
			res = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public ResultSet getAllDataTable(String table) {
		ResultSet res = null;
		String sql = "SELECT * FROM " + table;
		try {
			Statement stmt = con.createStatement();
			res = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return res;
	}

	public ResultSet executeQuery(String string) {
		// TODO Auto-generated method stub
		ResultSet res = null;
		try {
			Statement stmt = con.createStatement();
			res = stmt.executeQuery(string);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
}
