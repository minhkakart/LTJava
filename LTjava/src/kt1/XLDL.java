package kt1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import connectMySQL.ConnectSQL;
import form_interface.IController;
import querry_builder.QuerryBuilder;

public class XLDL implements IController {

	private ConnectSQL connectSQL = new ConnectSQL("benhvien", "root", "");

	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return this.connectSQL.getConnection();
	}

	@Override
	public ArrayList<BacSi> get() {
		ArrayList<BacSi> list = new ArrayList<BacSi>();

		String sql = "SELECT * FROM tbbacsi";

		ResultSet rs = this.connectSQL.executeQuery(sql);
		
		if (rs == null) {
			return list;
		}

		try {
			while (rs.next()) {
				BacSi bacsi = new BacSi();
				bacsi.setMa(rs.getString("ma"));
				bacsi.setHoten(rs.getString("hoten"));
				bacsi.setGt(rs.getBoolean("gt"));
				bacsi.setKhoa(rs.getString("khoa"));
				bacsi.setLuong(rs.getDouble("luong"));

				list.add(bacsi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int intsert(Object obj) {

		BacSi bacsi = (BacSi) obj;

		String sql = new QuerryBuilder().insertInto("tbbacsi").prepareValues(5).getQuerry();

		try {
			PreparedStatement ps = this.getConnection().prepareStatement(sql);
			ps.setString(1, bacsi.getMa());
			ps.setString(2, bacsi.getHoten());
			ps.setBoolean(3, bacsi.isMale());
			ps.setString(4, bacsi.getKhoa());
			ps.setDouble(5, bacsi.getLuong());

			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int update(Object obj) {
		// TODO Auto-generated method stub
		
		BacSi bacsi = (BacSi) obj;
		
		Map<String, String> data = new HashMap<String, String>();
//		data.put("ma", bacsi.getMa());
		data.put("hoten", bacsi.getHoten());
		data.put("gt", bacsi.isMale() ? "1" : "0");
		data.put("khoa", bacsi.getKhoa());
		data.put("luong", String.valueOf(bacsi.getLuong()));
		
		
		String sql = new QuerryBuilder().update("tbbacsi").set(data).where("ma = " + bacsi.getMa()).getQuerry();
		
//		System.out.println(sql);
		
		return this.connectSQL.executeUpdate(sql);
	}

	@Override
	public int delete(Object obj) {
		// TODO Auto-generated method stub
		
		BacSi bacsi = (BacSi) obj;
		
		String sql = new QuerryBuilder().deleteFrom("tbbacsi").where("ma = " + bacsi.getMa()).getQuerry();
		
		return this.connectSQL.executeUpdate(sql);
		
	}

}
