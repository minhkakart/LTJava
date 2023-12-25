package lab05;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
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

public class Bai1LoaiSP {

	static ConnectSQL connector = new ConnectSQL();
	static Connection con;
	static Validator validator = new Validator();
	static Map<String, String> mappedRules = new HashMap<String, String>();
	
	static boolean formEditable = false;
	static boolean isCreate = false;

	static FormSections form;
	static Vector<String> columnNames;
	static TableModelEditable model;
	static ScrollTable table;
	static JLabel lblMaLoai;
	static JLabel lblTenLoai;
	static JTextField txtMaLoai;
	static JTextField txtTenLoai;
	static JButton btnThem;
	static JButton btnSua;
	static JButton btnXoa;
	static JButton btnLuu;
	static JButton btnHuy;
	static JButton btnThoat;
	static JPanel sectionPanel;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		connector.setConnection("qlsanpham", "root", "");
		con = connector.getConnection();
		mappedRules.put("maloai", "required|max:10");
		mappedRules.put("tenloai", "required|max:50");

		form = new FormSections();

		columnNames = new Vector<String>();
		columnNames.add("Mã loại");
		columnNames.add("Tên loại");
		model = new TableModelEditable(columnNames, 0);
		table = new ScrollTable(model);
		table.setSize(new Dimension(600, 200));
		loadTable();

		lblMaLoai = new JLabel("Mã loại:");
		lblTenLoai = new JLabel("Tên loại:");

		txtMaLoai = new JTextField();
		txtTenLoai = new JTextField();

		lblMaLoai.setBounds(100, 50, 100, 30);
		lblTenLoai.setBounds(100, 100, 100, 30);

		txtMaLoai.setBounds(220, 50, 200, 30);
		txtTenLoai.setBounds(220, 100, 200, 30);

		btnThem = new JButton("Thêm");
		btnSua = new JButton("Sửa");
		btnXoa = new JButton("Xóa");
		btnLuu = new JButton("Lưu");
		btnHuy = new JButton("Hủy");
		btnThoat = new JButton("Thoát");

		sectionPanel = form.getSection1Panel();
		sectionPanel.setLayout(null);
		sectionPanel.add(lblMaLoai);
		sectionPanel.add(lblTenLoai);
		sectionPanel.add(txtMaLoai);
		sectionPanel.add(txtTenLoai);
		
		form.getTablePanel().setLayout(new FlowLayout());

		form.addButtons(btnThem, btnSua, btnXoa, btnLuu, btnHuy, btnThoat);
//		form.getButtonsPanel().setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		form.getTablePanel().add(table);
//		form.addSection(new JPanel());

		form.getTitleLabel().setFont(new Font("Arial", Font.BOLD, 20));
		form.setFormTitle("Danh mục loại sản phẩm");
		form.setSize(800, 600);
		form.setVisible(true);
		form.setLocationRelativeTo(null);
		
		setFormEditable(false);
		btnLuu.setEnabled(false);

		btnThem.addActionListener(e -> {
			System.out.println("Thêm");
			isCreate = true;
			setFormEditable(true);
			btnLuu.setEnabled(true);
		});

		btnSua.addActionListener(e -> {
			System.out.println("Sửa");
			if (table.getSelectedRow() < 0) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng trong bảng");
				return;
			}
			isCreate = false;
//			setFormEditable(true);
			txtTenLoai.setEditable(true);
			btnLuu.setEnabled(true);
		});

		btnXoa.addActionListener(e -> {
			System.out.println("Xóa");
			xoa();
		});

		btnLuu.addActionListener(e -> {
			System.out.println("Lưu");
			if (isCreate) {
				them();
			} else {
				sua();
			}
			setFormEditable(false);
			btnLuu.setEnabled(false);
		});

		btnHuy.addActionListener(e -> {
			System.out.println("Hủy");
			setFormEditable(false);
			btnLuu.setEnabled(false);
			
		});

		btnThoat.addActionListener(e -> {
			System.out.println("Thoát");
			System.exit(0);
		});

		table.setListener(e -> {
			tableSelection(e);
		});
			
	}

	static void loadTable() {
		ResultSet rs = connector.getAllDataTable("loaisp");
		try {
			table.setDataFromResultSet(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static void clearForm() {
		txtMaLoai.setText("");
		txtTenLoai.setText("");
	}

	static void setFormEditable(boolean editable) {
		formEditable = editable;
		txtMaLoai.setEditable(formEditable);
		txtTenLoai.setEditable(formEditable);
	}
	
	static boolean getFormEditable() {
        return formEditable;
    }

	static void them() {
		String maLoai = txtMaLoai.getText();
		String tenLoai = txtTenLoai.getText();
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("maloai", maLoai);
		data.put("tenloai", tenLoai);
		
		if (validator.validate(mappedRules, data)) {
			String sql = "INSERT INTO loaisp VALUES ('" + maLoai + "', '" + tenLoai + "')";
			try {
				con.createStatement().executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Thêm thành công");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			loadTable();
			clearForm();
		} else {
			JOptionPane.showMessageDialog(null, validator.getMessage());
		}
	}
	
	static void sua() {
		String maLoai = txtMaLoai.getText();
		String tenLoai = txtTenLoai.getText();

		Map<String, String> data = new HashMap<String, String>();
		data.put("maloai", maLoai);
		data.put("tenloai", tenLoai);
		
		
		if (validator.validate(mappedRules, data)) {
			String sql = "UPDATE loaisp SET tenloai = '" + tenLoai + "' WHERE maloai = '" + maLoai + "'";
			try {
				con.createStatement().executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Sửa thành công");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			loadTable();
			clearForm();
		} else {
			JOptionPane.showMessageDialog(null, validator.getMessage());
		}
	}
	
	static void xoa() {
		int answer = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Xóa", JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.NO_OPTION) {
			return;
		}
		
		String maLoai = txtMaLoai.getText();
		String tenLoai = txtTenLoai.getText();

		Map<String, String> data = new HashMap<String, String>();
		data.put("maloai", maLoai);
		data.put("tenloai", tenLoai);
		
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng trong bảng");
			return;
		}

		if (validator.validate(mappedRules, data)) {
			String sql = "DELETE FROM loaisp WHERE maloai = '" + maLoai + "'";
			try {
				con.createStatement().executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Xóa thành công");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			loadTable();
			clearForm();
		} else {
			JOptionPane.showMessageDialog(null, validator.getMessage());
		}
	}
	
	static void tableSelection(ListSelectionEvent e) {
		int row = table.getSelectedRow();
		if (e.getValueIsAdjusting() || row < 0) {
			return;
		}
		txtMaLoai.setText(table.getValueAt(row, 0).toString());
		txtTenLoai.setText(table.getValueAt(row, 1).toString());
	}

}
