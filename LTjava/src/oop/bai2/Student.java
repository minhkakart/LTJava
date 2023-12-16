package oop.bai2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connectMySQL.ConnectSQL;

public class Student {
	private String code;
	private String name;
	private String birthPlace;
	private double point;

	private static ConnectSQL connection = new ConnectSQL("jdbc:mysql://localhost:3306/swing_ui", "root", "");

	public Student() {
		code = "";
		name = "";
		birthPlace = "";
		point = 0;
	}

	public Student(String code, String name, String birthPlace, double point) {
		this.code = code;
		this.name = name;
		this.birthPlace = birthPlace;
		this.point = point;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public String toString() {
		return "Ma sinh vien: " + code + "\nHo ten: " + name + "\nNoi sinh: " + birthPlace + "\nDiem: " + point;
	}

	public boolean createStudent() {
		Connection con = connection.getConnection();
		try {
			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO students(code, name, birthPlace, point) VALUES (?, ?, ?, ?)");
			stmt.setString(1, this.code);
			stmt.setString(2, this.name);
			stmt.setString(3, this.birthPlace);
			stmt.setDouble(4, this.point);
			int res = stmt.executeUpdate();
			if (res > 0) {
				System.out.println("Insert success");
				return true;
			} else {
				System.out.println("Insert failed");
				return false;
			}
		} catch (SQLException e) {
//			System.out.println("SQL Exception " + e);
			e.printStackTrace();
			return false;
		}

	}

	public boolean editStudent(String id) {
		Connection con = connection.getConnection();
		try {
			PreparedStatement stmt = con
					.prepareStatement("UPDATE students SET code = ?, name = ?, birthPlace = ?, point = ? WHERE id = ?");
			stmt.setString(1, this.code);
			stmt.setString(2, this.name);
			stmt.setString(3, this.birthPlace);
			stmt.setDouble(4, this.point);
			stmt.setString(5, id);
			int res = stmt.executeUpdate();
			if (res > 0) {
				System.out.println("Update success");
				return true;
			} else {
				System.out.println("Update failed");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception " + e);
			return false;
		}
	}

	public static boolean deleteStudent(String id) {
		Connection con = connection.getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM students WHERE id = ?");
			stmt.setString(1, id);
			int res = stmt.executeUpdate();
			if (res > 0) {
				System.out.println("Delete success");
				return true;
			} else {
				System.out.println("Delete failed");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception " + e);
			return false;
		}
	}

}
