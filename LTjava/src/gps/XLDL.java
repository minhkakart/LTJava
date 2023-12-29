package gps;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectMySQL.ConnectSQL;
import form_interface.IController;
import querry_builder.QuerryBuilder;

public class XLDL implements IController{
	
	private ConnectSQL connectSQL = new ConnectSQL("petshop");
	
	
	public ArrayList<Dog> searchDog(String value) {
		ArrayList<Dog> list = new ArrayList<Dog>();
		
		String sql = new QuerryBuilder().select("*").from("tbdog").where("ma like '%" + value + "%' or ten like '%" + value + "%'").getQuerry();
		
		ResultSet rs = connectSQL.executeQuery(sql);
		
		if (rs == null) {
			System.out.println("Khong co du lieu");
			return list;
		}
		
		try {
			while (rs.next()) {
				Dog dog = new Dog();
				dog.setCode(rs.getString(1));
				dog.setName(rs.getString(2));
				dog.setGender(rs.getString(3));
				dog.setQuocGia(rs.getString(4));
				dog.setBirth(rs.getInt(5));
				list.add(dog);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return this.connectSQL.getConnection();
	}

	@Override
	public ArrayList<Dog> get() {
		// TODO Auto-generated method stub
		ArrayList<Dog> list = new ArrayList<Dog>();
		
		ResultSet rs = connectSQL.getAllDataTable("tbdog");
		
		if (rs == null) {
			System.out.println("Khong co du lieu");
			return list;
		}
		
		try {
			while(rs.next()) {
				Dog dog = new Dog();
				dog.setCode(rs.getString(1));
				dog.setName(rs.getString(2));
				dog.setGender(rs.getString(3));
				dog.setQuocGia(rs.getString(4));
				dog.setBirth(rs.getInt(5));
				list.add(dog);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int intsert(Object obj) {
		// TODO Auto-generated method stub
		Dog dog = (Dog) obj;
		
		String sql = "INSERT INTO tbdog VALUES ('"+dog.getCode()+"','"+dog.getName()+"','"+dog.getGender()+"','"+dog.getQuocGia()+"','"+dog.getBirth()+"')";
//		System.out.println(sql);
		
		return connectSQL.executeUpdate(sql);
	}

	@Override
	public int update(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
