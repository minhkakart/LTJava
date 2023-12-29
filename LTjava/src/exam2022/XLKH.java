package exam2022;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import connectMySQL.ConnectSQL;
import querry_builder.QuerryBuilder;

public class XLKH {
	
	private ConnectSQL connectSQL = new ConnectSQL("dlkh", "root", "");
	
	public Connection getCon() {
		return this.connectSQL.getConnection();
	}
	
	public ArrayList<KhachHang> getKH(){
		ArrayList<KhachHang> list = new ArrayList<KhachHang>();
		String sql = "SELECT * FROM tbkhachhang";
		
		try {
			PreparedStatement ps = this.getCon().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				KhachHang kh = new KhachHang();
				kh.setSotk(rs.getString("sotk"));
				kh.setHoten(rs.getString("hoten"));
				kh.setGt(rs.getString("gt"));
				kh.setDiachi(rs.getString("diachi"));
				kh.setSodu(rs.getDouble("sodu"));
				list.add(kh);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int insertKH(KhachHang kh) {
//		String sql = "INSERT INTO tbkhachhang(sotk, hoten, gt, diachi, sodu) VALUES(?, ?, ?, ?, ?)";
		String sql = new QuerryBuilder().insertInto("tbkhachhang", "sotk", "hoten", "gt", "diachi", "sodu").prepareValues(5).getQuerry();
		
		try {
			PreparedStatement ps = this.getCon().prepareStatement(sql);
			ps.setString(1, kh.getSotk());
			ps.setString(2, kh.getHoten());
			ps.setString(3, kh.getGt());
			ps.setString(4, kh.getDiachi());
			ps.setDouble(5, kh.getSodu());
			
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	

}
