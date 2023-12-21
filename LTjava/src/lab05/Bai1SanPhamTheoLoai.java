package lab05;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import connectMySQL.ConnectSQL;
import swing_template.FormSections;
import swing_template.ScrollTable;
import swing_template.TableModelEditable;

public class Bai1SanPhamTheoLoai {
	
	static ConnectSQL connector = new ConnectSQL("jdbc:mysql://localhost:3306/qlsanpham", "root", "");

	static FormSections form;
	static Vector<String> columnNames;
//	static TableModelEditable model;
	static ScrollTable scrollTable;

	static JPanel sectionPanel1;
	
	static JLabel lblMaSP;
	static JComboBox<String> cbMaLoai;
	static JButton btnThoat;
	
	static Map<String, String> maLoaiSanPham;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		form = new FormSections();
		columnNames = new Vector<String>();
		columnNames.add("Mã SP");
		columnNames.add("Tên SP");
		columnNames.add("Đơn giá");
		columnNames.add("Loại SP");
//		model = new TableModelEditable(columnNames, 0);
		scrollTable = new ScrollTable(new TableModelEditable(columnNames, 0));
		form.getTablePanel().add(scrollTable);

		sectionPanel1 = form.getSection1Panel();
		sectionPanel1.setLayout(null);
		
		lblMaSP = new JLabel("Chọn loại sản phẩm:");
		lblMaSP.setHorizontalAlignment(JLabel.CENTER);
		lblMaSP.setBounds(40, 40, 200, 35);
		cbMaLoai = new JComboBox<String>();
		cbMaLoai.setBounds(240, 40, 200, 35);
		maLoaiSanPham = new HashMap<String, String>();
		ResultSet rs = connector.getAllDataTable("loaisp");
		cbMaLoai.addItem("Tất cả");
		try {
            while (rs.next()) {
                cbMaLoai.addItem(rs.getString("tenloai"));
                maLoaiSanPham.put(rs.getString("tenloai"), rs.getString("maloai"));
            }
        } catch (Exception e) {
        	
        }
		
		refreshTable();
		
		cbMaLoai.addActionListener(e -> {
			refreshTable();
		});
		
		sectionPanel1.add(lblMaSP);
		sectionPanel1.add(cbMaLoai);
		
		btnThoat = new JButton("Thoát");
		form.getButtonsPanel().add(btnThoat);
		
		btnThoat.addActionListener(e -> {
			System.exit(0);
		});

		form.getTitlePanel().setPreferredSize(new Dimension(0, 50));
		form.getTitlePanel().setLayout(new GridLayout(1, 1));
		form.getTitleLabel().setFont(new Font("Arial", Font.BOLD, 20));
		form.getTitleLabel().setHorizontalAlignment(JLabel.CENTER);
		form.setFormTitle("Sản phẩm theo loại");
		form.setSize(600, 500);
		form.setVisible(true);
		form.setLocationRelativeTo(null);
	}
	
	static void refreshTable() {
//		model.setRowCount(0);
		String sql = "SELECT * FROM sanpham";
		if (cbMaLoai.getSelectedIndex() > 0) {
			sql += " WHERE maloai = '" + maLoaiSanPham.get(cbMaLoai.getSelectedItem()) + "'";
		}
		ResultSet rs = connector.executeQuery(sql);
		try {
			scrollTable.setDataFromResultSet(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
