package lab05;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;

import connectMySQL.ConnectSQL;
import swing_template.FormSections;
import swing_template.ScrollTable;
import swing_template.TableModelEditable;
import validator.Validator;

public class Bai1SanPham {

	static ConnectSQL connector;
	static ResultSet rsLoaiSP;

	static boolean formEditable = false;
	static boolean isCreate = false;

	static FormSections form;
	static ScrollTable scrollTable;
	static Vector<String> columnNames;
	static TableModelEditable model;

	static JPanel sectionPanel1;
	static JPanel btnsPanel;

	static JLabel lblMaSP;
	static JLabel lblTenSP;
	static JLabel lblDonGia;
	static JLabel lblMaLoai;

	static JTextField txtMaSP;
	static JTextField txtTenSP;
	static JTextField txtDonGia;
	static JComboBox<String> cbMaLoai;

	static JButton btnThem;
	static JButton btnSua;
	static JButton btnXoa;
	static JButton btnLuu;
	static JButton btnHuy;
	static JButton btnThoat;

	static Map<String, String> maLoaiSanPham;
	static Validator validator;

	public static void main(String[] args) {

		connector = new ConnectSQL("qlsanpham", "root", "");

		validator = new Validator();
		validator.addRule("masp", "required|max:10");
		validator.addRule("tensp", "required|max:50");
		validator.addRule("dongia", "required|numeric|min:1000");
		validator.addRule("maloai", "required");

		form = new FormSections();
		scrollTable = new ScrollTable();
		columnNames = new Vector<String>();
		columnNames.add("Mã SP");
		columnNames.add("Tên SP");
		columnNames.add("Đơn giá");
		columnNames.add("Mã loại");
		model = new TableModelEditable(columnNames, 0);
		scrollTable.setModel(model);
		form.getTablePanel().add(scrollTable);

		sectionPanel1 = form.getSection1Panel();
		lblMaSP = new JLabel("Mã SP");
		lblTenSP = new JLabel("Tên SP");
		lblDonGia = new JLabel("Đơn giá");
		lblMaLoai = new JLabel("Loại");

		lblMaSP.setHorizontalAlignment(JLabel.CENTER);
		lblTenSP.setHorizontalAlignment(JLabel.CENTER);
		lblDonGia.setHorizontalAlignment(JLabel.CENTER);
		lblMaLoai.setHorizontalAlignment(JLabel.CENTER);

		txtMaSP = new JTextField(20);
		txtTenSP = new JTextField(20);
		txtDonGia = new JTextField(20);
		cbMaLoai = new JComboBox<String>();
		maLoaiSanPham = new HashMap<String, String>();
		rsLoaiSP = connector.getAllDataTable("loaisp");
		while (rsLoaiSP != null) {
			try {
				if (!rsLoaiSP.next())
					break;
				cbMaLoai.addItem(rsLoaiSP.getString("tenloai"));
				maLoaiSanPham.put(rsLoaiSP.getString("tenloai"), rsLoaiSP.getString("maloai"));
			} catch (Exception e) {
				System.out.println("Error: " + e);
				e.printStackTrace();
			}
		}

		sectionPanel1.add(lblMaSP);
		sectionPanel1.add(txtMaSP);
		sectionPanel1.add(lblTenSP);
		sectionPanel1.add(txtTenSP);
		sectionPanel1.add(lblDonGia);
		sectionPanel1.add(txtDonGia);
		sectionPanel1.add(lblMaLoai);
		sectionPanel1.add(cbMaLoai);

		btnThem = new JButton("Thêm");
		btnSua = new JButton("Sửa");
		btnXoa = new JButton("Xóa");
		btnLuu = new JButton("Lưu");
		btnHuy = new JButton("Hủy");
		btnThoat = new JButton("Thoát");

		btnsPanel = form.getButtonsPanel();
		btnsPanel.add(btnThem);
		btnsPanel.add(btnSua);
		btnsPanel.add(btnXoa);
		btnsPanel.add(btnLuu);
		btnsPanel.add(btnHuy);
		btnsPanel.add(btnThoat);

		btnThem.addActionListener(e -> {
			setFormEditable(true);
			btnLuu.setEnabled(true);
			isCreate = true;
			txtMaSP.requestFocus();
		});

		btnSua.addActionListener(e -> {
			int rowSelected = scrollTable.getSelectedRow();
			if (rowSelected < 0) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng trong bảng");
				return;
			}
			
			setFormEditable(true);
			btnLuu.setEnabled(true);
			isCreate = false;
			txtMaSP.setEditable(false);
			txtTenSP.requestFocus();
		});

		btnXoa.addActionListener(e -> {
			xoa();
		});

		btnLuu.addActionListener(e -> {
			if (isCreate) {
				them();
			} else {
				sua();
			}
			setFormEditable(false);
			btnLuu.setEnabled(false);
			clearForm();
		});

		btnHuy.addActionListener(e -> {
			setFormEditable(false);
			btnLuu.setEnabled(false);
		});

		btnThoat.addActionListener(e -> {
			System.exit(0);
		});
		
		scrollTable.setListener(e -> {
            tableSelection(e);
        });

		setFormEditable(false);
		loadTable();
		
		form.setFormTitle("Quản lý danh mục sản phẩm");
		form.getTitlePanel().setPreferredSize(new Dimension(0, 50));
		form.getTitlePanel().setLayout(new GridLayout(1, 1));
		form.getTitleLabel().setFont(new Font("Arial", Font.BOLD, 24));
		form.getTitleLabel().setHorizontalAlignment(JLabel.CENTER);
		form.setVisible(true);
		form.setLocationRelativeTo(null);

	}

	private static void tableSelection(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		int rowSelected = scrollTable.getSelectedRow();
		if (rowSelected < 0 || e.getValueIsAdjusting()) {
			return;
		}
		txtMaSP.setText((String) scrollTable.getValueAt(rowSelected, 0));
		txtTenSP.setText((String) scrollTable.getValueAt(rowSelected, 1));
		txtDonGia.setText((String) scrollTable.getValueAt(rowSelected, 2));
		cbMaLoai.setSelectedIndex(0);
		for (String key : maLoaiSanPham.keySet()) {
			if (maLoaiSanPham.get(key).equals((String) scrollTable.getValueAt(rowSelected, 3))) {
				cbMaLoai.setSelectedItem(key);
				break;
			}
		}
	}

	private static void sua() {
		// TODO Auto-generated method stub
		System.out.println("Sửa");
		String maSP = txtMaSP.getText();
		String tenSP = txtTenSP.getText();
		String donGia = txtDonGia.getText();
		String maLoai = maLoaiSanPham.get(cbMaLoai.getSelectedItem());

		Map<String, String> data = new HashMap<String, String>();
		data.put("masp", maSP);
		data.put("tensp", tenSP);
		data.put("dongia", donGia);
		data.put("maloai", maLoai);

		if (!validator.validate(data)) {
			System.out.println(validator.getMessage());
			JOptionPane.showMessageDialog(null, validator.getMessage());
			return;
		}
		
		String sql = "UPDATE sanpham SET tensp = '" + tenSP + "', dongia = " + donGia + ", maloai = '" + maLoai + "' WHERE masp = '" + maSP + "'";
		int res = connector.executeUpdate(sql);
		if (res > 0) {
			System.out.println("Sửa thành công");
			loadTable();
			JOptionPane.showMessageDialog(null, "Sửa thành công");
		} else {
			System.out.println("Sửa thất bại");
			JOptionPane.showMessageDialog(null, "Sửa thất bại. Vui lòng kiểm tra lại dữ liệu");
		}
	}

	private static void them() {
		// TODO Auto-generated method stub
		System.out.println("Thêm");

		String maSP = txtMaSP.getText();
		String tenSP = txtTenSP.getText();
		String donGia = txtDonGia.getText();
		String maLoai = maLoaiSanPham.get(cbMaLoai.getSelectedItem());

		Map<String, String> data = new HashMap<String, String>();
		data.put("masp", maSP);
		data.put("tensp", tenSP);
		data.put("dongia", donGia);
		data.put("maloai", maLoai);

		if (!validator.validate(data)) {
			System.out.println(validator.getMessage());
			JOptionPane.showMessageDialog(null, validator.getMessage());
			return;
		}

		String sql = "INSERT INTO sanpham VALUES ('" + maSP + "', '" + tenSP + "', " + donGia + ", '" + maLoai + "')";
		int res = connector.executeUpdate(sql);
		if (res > 0) {
			System.out.println("Thêm thành công");
			loadTable();
			JOptionPane.showMessageDialog(null, "Thêm thành công");
		} else {
			System.out.println("Thêm thất bại");
			JOptionPane.showMessageDialog(null, "Thêm thất bại. Vui lòng kiểm tra lại dữ liệu");
		}
	}

	static void xoa() {
		System.out.println("Xóa");

		int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không?", "Xóa",
				JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION) {
			return;
		}

		int rowSelected = scrollTable.getSelectedRow();
		if (rowSelected < 0) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng trong bảng");
			return;
		}
		String maSP = (String) scrollTable.getValueAt(rowSelected, 0);
		String sql = "DELETE FROM sanpham WHERE masp = '" + maSP + "'";
		int res = connector.executeUpdate(sql);
		if (res > 0) {
			System.out.println("Xóa thành công");
			loadTable();
			JOptionPane.showMessageDialog(null, "Xóa thành công");
		} else {
			System.out.println("Xóa thất bại");
			JOptionPane.showMessageDialog(null, "Xóa thất bại. Vui lòng kiểm tra lại dữ liệu");
		}
	}

	static void loadTable() {
		ResultSet rs = connector.getAllDataTable("sanpham");
		try {
			scrollTable.setDataFromResultSet(rs);
		} catch (Exception e) {
			System.out.println("Error: " + e);
			e.printStackTrace();
		}
	}
	
	static void clearForm() {
		txtMaSP.setText("");
		txtTenSP.setText("");
		txtDonGia.setText("");
		cbMaLoai.setSelectedIndex(0);
	}
	
	static void setFormEditable(boolean editable) {
		formEditable = editable;
		txtMaSP.setEditable(editable);
		txtTenSP.setEditable(editable);
		txtDonGia.setEditable(editable);
		cbMaLoai.setEnabled(editable);
	}

}
