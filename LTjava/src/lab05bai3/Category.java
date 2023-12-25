package lab05bai3;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import connectMySQL.ConnectSQL;
import validator.Validator;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.Dimension;

public class Category extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCateID;
	private JTextField txtCateName;
	
	JButton btnNewButton;
	
	private Validator validator = new Validator();
	private ConnectSQL connectSQL;
	
	public static final int CREATE = 0;
	public static final int EDIT = 1;
	
	private int mode;

	/**
	 * Create the panel.
	 */
	public Category(ConnectSQL connectSQL, int categoryMode) {
		this.connectSQL = connectSQL;
		this.mode = categoryMode;
		
		setPreferredSize(new Dimension(450, 220));
		setBounds(new Rectangle(0, 0, 450, 220));
		setLayout(null);
		validator.addRule("categoryID", "required");
		validator.addRule("categoryName", "required");
		
		JLabel lblNewLabel = new JLabel("Category ID:");
		lblNewLabel.setBounds(48, 43, 80, 14);
		add(lblNewLabel);
		
		JLabel lblCategoryName = new JLabel("Category Name:");
		lblCategoryName.setBounds(48, 89, 80, 14);
		add(lblCategoryName);
		
		txtCateID = new JTextField();
		txtCateID.setBounds(138, 40, 265, 20);
		add(txtCateID);
		txtCateID.setColumns(10);
		
		txtCateName = new JTextField();
		txtCateName.setColumns(10);
		txtCateName.setBounds(138, 86, 265, 20);
		add(txtCateName);
		
		btnNewButton = new JButton("OK");
		btnNewButton.setBounds(176, 138, 90, 45);
		add(btnNewButton);
		
		setMode(mode);
		
		btnNewButton.addActionListener(e -> {
			if (mode == CREATE) {
				saveCategory();
			} else if (mode == EDIT) {
				updateCategory();
			}
		});

	}
	
	public void setMode(int mode) {
		if (mode == CREATE) {
			txtCateID.setEditable(true);
			txtCateName.setEditable(true);
		}
		else if (mode == EDIT) {
			txtCateID.setEditable(false);
		}
		else {
			JOptionPane.showMessageDialog(this, "Invalid mode!");
		}
	}
	
	
	public void setInitValues(String id, String name) {
		txtCateID.setText(id);
		txtCateName.setText(name);
	}
	
	private void saveCategory() {
		String cateID = txtCateID.getText();
		String cateName = txtCateName.getText();
		Map<String, String> data = new HashMap<String, String>();
		data.put("categoryID", cateID);
		data.put("categoryName", cateName);
		if (!validator.validate(data)) {
			JOptionPane.showMessageDialog(this, validator.getMessage());
			return;
		}
		
		String sql = "INSERT INTO categories(id, name) VALUES ('" + cateID + "', '" + cateName + "')";
		if (connectSQL.executeUpdate(sql) > 0) {
			JOptionPane.showMessageDialog(this, "Thêm thành công!");
			clearForm();
		}
		else {
			JOptionPane.showMessageDialog(this, "Thêm thất bại!");
		}
	}
	
	private void updateCategory() {
		String cateID = txtCateID.getText();
		String cateName = txtCateName.getText();
		Map<String, String> data = new HashMap<String, String>();
		data.put("categoryID", cateID);
		data.put("categoryName", cateName);
		if (!validator.validate(data)) {
			JOptionPane.showMessageDialog(this, validator.getMessage());
			return;
		}

		String sql = "UPDATE categories SET name = '" + cateName + "' WHERE id = '" + cateID + "'";
		if (connectSQL.executeUpdate(sql) > 0) {
			JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
		} else {
			JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
		}
	}
	
	private void clearForm() {
		txtCateID.setText("");
		txtCateName.setText("");
	}
}
