package lab05;

import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import connectMySQL.ConnectSQL;
import swing_template.FormSections;
import swing_template.ScrollTable;
import swing_template.TableModelEditable;
import validator.Validator;

public class TimKiemSanPham {

	static FormSections form;
	static Vector<String> columnNames;
	static ScrollTable table;

	static JButton btnTimKiem;
	static JButton btnThoat;

	static JLabel lblTenSanPham;
	static JLabel lblGiaMin;
	static JLabel lblGiaMax;

	static JTextField txtTenSanPham;
	static JTextField txtGiaMin;
	static JTextField txtGiaMax;

	static ButtonGroup radioGroup;
	static JRadioButton radioTen;
	static JRadioButton radioGia;
	
	static boolean isSerchByName = true;
	
	static Validator validator;
	
	static ConnectSQL connectSQL;

	public static void main(String[] args) {
		
		connectSQL = new ConnectSQL("qlsanpham", "root", "");
		
		validator = new Validator();
		validator.addRule("tensp", "required");
		validator.addRule("giamin", "required|numeric|min:1000");
		validator.addRule("giamax", "required|numeric|min:1000");
		
		// TODO Auto-generated method stub
		form = new FormSections();
		columnNames = new Vector<String>();
		columnNames.add("Mã sản phẩm");
		columnNames.add("Tên sản phẩm");
		columnNames.add("Đơn giá");
		columnNames.add("Loại sản phẩm");
		table = new ScrollTable(new TableModelEditable(columnNames, 0));
		table.setBounds(100, 0, 600, 180);
		form.getTablePanel().setPreferredSize(new Dimension(800, 250));
		form.getTablePanel().setLayout(null);
		form.getTablePanel().add(table);
		btnThoat = new JButton("Thoát");
		btnThoat.setBounds(350, 190, 100, 50);
		btnThoat.addActionListener(e -> {
			form.dispose();
		});
		form.getTablePanel().add(btnThoat);

		lblTenSanPham = new JLabel("Tên sản phẩm:");
		lblGiaMin = new JLabel("Giá từ:");
		lblGiaMax = new JLabel("Đến:");
		
		txtTenSanPham = new JTextField();
		txtGiaMin = new JTextField();
		txtGiaMax = new JTextField();
		
		lblTenSanPham.setBounds(80, 70, 100, 30);
		lblGiaMin.setBounds(80, 110, 100, 30);
		lblGiaMax.setBounds(320, 110, 60, 30);
		
		lblTenSanPham.setOpaque(true);
		lblGiaMin.setOpaque(true);
		lblGiaMax.setOpaque(true);
		
//		lblTenSanPham.setBackground(Color.RED);
//		lblGiaMin.setBackground(Color.RED);
//		lblGiaMax.setBackground(Color.RED);
		
		txtTenSanPham.setBounds(200, 70, 300, 30);
		txtGiaMin.setBounds(200, 110, 100, 30);
		txtGiaMax.setBounds(400, 110, 100, 30);
		
		lblTenSanPham.setHorizontalAlignment(JLabel.RIGHT);
		lblGiaMin.setHorizontalAlignment(JLabel.RIGHT);
		lblGiaMax.setHorizontalAlignment(JLabel.RIGHT);

		form.getSection1Panel().add(lblTenSanPham);
		form.getSection1Panel().add(lblGiaMin);
		form.getSection1Panel().add(lblGiaMax);
		form.getSection1Panel().add(txtTenSanPham);
		form.getSection1Panel().add(txtGiaMin);
		form.getSection1Panel().add(txtGiaMax);

		radioGroup = new ButtonGroup();
		radioTen = new JRadioButton("Tìm theo tên");
		radioGia = new JRadioButton("Tìm theo giá");
		radioTen.setBounds(500, 70, 150, 30);
		radioGia.setBounds(500, 110, 150, 30);
		
		radioGroup.add(radioTen);
		radioGroup.add(radioGia);
		
		radioTen.setSelected(true);
		setEnableTen(true);
		setEnableGia(false);
		radioTen.addActionListener(e -> {
			txtTenSanPham.requestFocus();
			setEnableTen(true);
			setEnableGia(false);
		});
		
		radioGia.addActionListener(e -> {
			txtGiaMin.requestFocus();
			setEnableTen(false);
			setEnableGia(true);
		});
		
		form.getSection1Panel().add(radioTen);
		form.getSection1Panel().add(radioGia);
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBounds(290, 160, 120, 30);
		btnTimKiem.addActionListener(e -> {
			search();
		});
		
		
		form.getSection1Panel().add(btnTimKiem);
		
		
		form.getSection1Panel().setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(null, 3), "Thông tin sản phẩm"));
		form.getSection1Panel().setLayout(null);

		form.getTitleLabel().setFont(new Font("Arial", Font.BOLD, 20));
		form.setFormTitle("Tìm kiếm sản phẩm");
		form.setVisible(true);
		form.setLocationRelativeTo(null);

	}
	
	static void setEnableTen(boolean enable) {
		txtTenSanPham.setEnabled(enable);
		isSerchByName = enable;
	}
	
	static void setEnableGia(boolean enable) {
		txtGiaMin.setEnabled(enable);
		txtGiaMax.setEnabled(enable);
		isSerchByName = !enable;
	}
	
	static void search() {
		if (isSerchByName) {
			String name = txtTenSanPham.getText();
			Map<String, String> data = new HashMap<String, String>();
			data.put("tensp", name);
			
			if (validator.validate(data)) {
				String sql = "SELECT * FROM sanpham WHERE tensp LIKE '%" + name + "%'";
				ResultSet rs = connectSQL.executeQuery(sql);
				if (rs == null) {
					JOptionPane.showMessageDialog(null, "Lỗi kết nối CSDL");
					return;
				}
				try {
                    table.setDataFromResultSet(rs);
                } catch (Exception e) {
                	
                }
				
			} else {
				System.out.println(validator.getMessage());
			}
			
		}
		else {
			String min = txtGiaMin.getText();
			String max = txtGiaMax.getText();
			Map<String, String> data = new HashMap<String, String>();
			data.put("giamin", min);
			data.put("giamax", max);

			if (validator.validate(data)) {
				String sql = "SELECT * FROM sanpham WHERE dongia BETWEEN " + min + " AND " + max;
				ResultSet rs = connectSQL.executeQuery(sql);
				if (rs == null) {
					JOptionPane.showMessageDialog(null, "Lỗi kết nối CSDL");
					return;
				}
				try {
					table.setDataFromResultSet(rs);
				} catch (Exception e) {

				}
			} else {
				JOptionPane.showMessageDialog(null, validator.getMessage());
			}
			
		}
	}

}
