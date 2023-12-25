package lab05bai3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import connectMySQL.ConnectSQL;
import swing_template.ScrollTable;
import swing_template.TableModelEditable;
import validator.Validator;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.event.InputEvent;

public class QLSP extends JFrame implements Printable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6915729387400971708L;
	private JPanel contentPane;

	private JComboBox<String> comboBox;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtPrice;
	private JTextField txtQuantity;
	private JTextArea txtDescription;

	JList<String> listCategories;
	Map<String, String> mapCategories = new HashMap<String, String>();

	Vector<String> header = new Vector<String>();
	ScrollTable scrollTable;

	private ArrayList<String> listProductId = new ArrayList<String>();

	private ConnectSQL connectSQL = new ConnectSQL("lab05bai3", "root", "");

	private Validator productValidator = new Validator();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLSP frame = new QLSP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QLSP() {
		setLocationByPlatform(true);
		setTitle("Quản lý sản phẩm");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 644);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Print");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Exit");
		mntmNewMenuItem_1.addActionListener(e -> {
			int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thoát không?", "Thông báo",
					JOptionPane.OK_CANCEL_OPTION);
			if (confirm == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		});
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Quản lý sản phẩm");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 11, 764, 45);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 67, 293, 503);
		contentPane.add(panel);
		panel.setLayout(null);

		listCategories = new JList<String>();
		listCategories.addListSelectionListener(e -> {
			listSelectionChanged(e);
		});
		listCategories.setBorder(new TitledBorder(null, "Danh m\u1EE5c s\u1EA3n ph\u1EA9m", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		listCategories.setBounds(10, 11, 273, 435);
		panel.add(listCategories);

		JButton btnNewCate = new JButton("New");
		btnNewCate.setBounds(10, 457, 80, 35);
		panel.add(btnNewCate);
		btnNewCate.addActionListener(e -> {
			handleNewCate();
		});

		JButton btnUpdateCate = new JButton("Update");
		btnUpdateCate.setBounds(104, 457, 80, 35);
		panel.add(btnUpdateCate);
		btnUpdateCate.addActionListener(e -> {
			handleUpdateCate();
		});

		JButton btnRemoveCate = new JButton("Remove");
		btnRemoveCate.setBounds(202, 457, 80, 35);
		panel.add(btnRemoveCate);
		btnRemoveCate.addActionListener(e -> {
			handleRemoveCate();
		});

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(new Color(0, 0, 128));
		separator.setForeground(new Color(255, 0, 128));
		separator.setBounds(313, 67, 2, 503);
		contentPane.add(separator);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(325, 67, 449, 221);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Thông tin chi tiết");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 11, 429, 14);
		panel_1.add(lblNewLabel_1);

		JPanel panel_table = new JPanel();
		panel_table.setBounds(10, 36, 429, 174);
		panel_1.add(panel_table);
		panel_table.setLayout(new GridLayout(1, 1, 0, 0));

		header.add("Product ID");
		header.add("Product Name");
		header.add("Unit Price");
		header.add("Quantity");
		header.add("Description");
		scrollTable = new ScrollTable(new TableModelEditable(header));
		panel_table.add(scrollTable);
		scrollTable.setListener(e -> {
			tableSelectionChanged(e);
		});

		comboBox = new JComboBox<String>();
		comboBox.setBounds(481, 299, 244, 22);
		contentPane.add(comboBox);

		txtId = new JTextField();
		txtId.setBounds(481, 332, 244, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(481, 363, 244, 20);
		contentPane.add(txtName);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(481, 394, 244, 20);
		contentPane.add(txtPrice);

		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(481, 425, 244, 20);
		contentPane.add(txtQuantity);

		JLabel lblNewLabel_2 = new JLabel("Category");
		lblNewLabel_2.setBounds(384, 299, 87, 22);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Product ID");
		lblNewLabel_2_1.setBounds(384, 329, 87, 22);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("Product Name");
		lblNewLabel_2_2.setBounds(384, 362, 87, 22);
		contentPane.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel("Unit Price");
		lblNewLabel_2_3.setBounds(384, 392, 87, 22);
		contentPane.add(lblNewLabel_2_3);

		JLabel lblNewLabel_2_4 = new JLabel("Quantity");
		lblNewLabel_2_4.setBounds(384, 423, 87, 22);
		contentPane.add(lblNewLabel_2_4);

		JLabel lblNewLabel_2_5 = new JLabel("Description");
		lblNewLabel_2_5.setBounds(384, 456, 87, 61);
		contentPane.add(lblNewLabel_2_5);

		JButton btnNewProduct = new JButton("New");
		btnNewProduct.setBounds(417, 528, 80, 35);
		contentPane.add(btnNewProduct);
		btnNewProduct.addActionListener(e -> {
			clearText();
		});

		JButton btnSaveProduct = new JButton("Save");
		btnSaveProduct.addActionListener(e -> {
			handleNewProduct();
		});
		btnSaveProduct.setBounds(515, 528, 80, 35);
		contentPane.add(btnSaveProduct);

		JButton btnRemoveProduct = new JButton("Remove");
		btnRemoveProduct.addActionListener(e -> {
			handleRemoveProduct();
		});
		btnRemoveProduct.setBounds(613, 528, 80, 35);
		contentPane.add(btnRemoveProduct);

		txtDescription = new JTextArea();
		txtDescription.setBorder(UIManager.getBorder("TextField.border"));
		txtDescription.setWrapStyleWord(true);
		txtDescription.setLineWrap(true);
		txtDescription.setColumns(10);
		txtDescription.setBounds(481, 456, 244, 61);
		contentPane.add(txtDescription);

		updateList();
		mapCategories.forEach((k, v) -> {
			comboBox.addItem(k);
		});
		listCategories.setSelectedIndex(0);

		loadAllProductId();

		productValidator.addRule("cateID", "required");
		productValidator.addRule("productID", "required");
		productValidator.addRule("productName", "required");
		productValidator.addRule("unitPrice", "required|numeric|min:1000");
		productValidator.addRule("quantity", "required|numeric|min:1");
		productValidator.addRule("description", "required");

		setLocationRelativeTo(null);
	}

	private void updateList() {
		String sql = "SELECT * FROM categories";
		ResultSet rs = connectSQL.executeQuery(sql);
		mapCategories.clear();
		try {
			while (rs.next()) {
				mapCategories.put(rs.getString("name"), rs.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		listCategories.setListData(mapCategories.keySet().toArray(new String[0]));
		listCategories.setSelectedIndex(0);
		loadAllProductId();
	}

	private void updateTable(String cateID) {
		String sql = "SELECT * FROM products WHERE category_id = '" + cateID + "'";
		ResultSet rs = connectSQL.executeQuery(sql);
		try {
			scrollTable.setDataFromResultSet(rs);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void clearText() {
		txtId.setText("");
		txtName.setText("");
		txtPrice.setText("");
		txtQuantity.setText("");
		txtDescription.setText("");
	}

	private void listSelectionChanged(ListSelectionEvent e) {
		int index = listCategories.getSelectedIndex();
		if (!e.getValueIsAdjusting() && index >= 0) {
			comboBox.setSelectedItem(listCategories.getSelectedValue());
			String cateID = mapCategories.get(listCategories.getSelectedValue());
			updateTable(cateID);
			clearText();
		}
	}

	private void tableSelectionChanged(ListSelectionEvent e) {
		int row = scrollTable.getSelectedRow();
		if (!e.getValueIsAdjusting() && row >= 0) {
			txtId.setText(scrollTable.getValueAt(row, 0).toString());
			txtName.setText(scrollTable.getValueAt(row, 1).toString());
			txtPrice.setText(scrollTable.getValueAt(row, 2).toString());
			txtQuantity.setText(scrollTable.getValueAt(row, 3).toString());
			txtDescription.setText(scrollTable.getValueAt(row, 4).toString());
		}
	}

	private void handleNewCate() {
		JDialog dialog = new JDialog(this, "New Category", true);
		Category newCate = new Category(this.connectSQL, Category.CREATE);

		dialog.setContentPane(newCate);
		dialog.pack();
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);

		System.out.println("reload category list");
		updateList();
	}

	private void handleUpdateCate() {
		String cateID = mapCategories.get(listCategories.getSelectedValue());
		String cateName = listCategories.getSelectedValue();

		JDialog dialog = new JDialog(this, "Update Category", true);
		Category newCate = new Category(this.connectSQL, Category.EDIT);

		newCate.setInitValues(cateID, cateName);

		dialog.setContentPane(newCate);
		dialog.pack();
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);

		System.out.println("reload category list");
		updateList();
	}

	private void handleRemoveCate() {
		int index = listCategories.getSelectedIndex();
		if (index < 0) {
			JOptionPane.showMessageDialog(this, "Please select a category!");
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to delete this category?", "Confirm",
				JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION) {
			return;
		}

		String cateID = mapCategories.get(listCategories.getSelectedValue());

		String sql = "DELETE FROM categories WHERE id = '" + cateID + "'";

		if (connectSQL.executeUpdate(sql) > 0) {
			JOptionPane.showMessageDialog(this, "Xóa thành công!");
			clearText();
		} else {
			JOptionPane.showMessageDialog(this, "Xóa thất bại!");
		}

		System.out.println("reload category list");
		updateList();
	}

	private void loadAllProductId() {
		listProductId.clear();
		ResultSet rs = connectSQL.getAllDataTable("products");
		if (rs != null) {
			try {
				while (rs.next()) {
					listProductId.add(rs.getString("id"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleNewProduct() {
		String cateID = mapCategories.get(comboBox.getSelectedItem().toString());
		String productID = txtId.getText();
		String productName = txtName.getText();
		String unitPrice = txtPrice.getText();
		String quantity = txtQuantity.getText();
		String description = txtDescription.getText();

		if (listProductId.contains(productID)) {
			JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại. Vui lòng điền mã khác.");
			return;
		}

		Map<String, String> data = new HashMap<String, String>();
		data.put("cateID", cateID);
		data.put("productID", productID);
		data.put("productName", productName);
		data.put("unitPrice", unitPrice);
		data.put("quantity", quantity);
		data.put("description", description);

		if (!productValidator.validate(data)) {
			JOptionPane.showMessageDialog(this, productValidator.getMessage());
			return;
		}

		String sql = "INSERT INTO products VALUES ('" + productID + "', '" + productName + "', '" + unitPrice + "', '"
				+ quantity + "', '" + description + "', '" + cateID + "')";
		if (connectSQL.executeUpdate(sql) > 0) {
			JOptionPane.showMessageDialog(this, "Thêm thành công!");
			String currCateID = mapCategories.get(listCategories.getSelectedValue());
			if (cateID.equals(currCateID)) {
				updateTable(cateID);
			}
			clearText();
			loadAllProductId();
		} else {
			JOptionPane.showMessageDialog(this, "Thêm thất bại!");
		}

	}

	private void handleRemoveProduct() {
		int rowSelected = scrollTable.getSelectedRow();
		if (rowSelected < 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa!");
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sản phẩm này?", "Xác nhận",
				JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION) {
			return;
		}

		String productID = scrollTable.getValueAt(rowSelected, 0).toString();
		String sql = "DELETE FROM products WHERE id = '" + productID + "'";
		if (connectSQL.executeUpdate(sql) > 0) {
			JOptionPane.showMessageDialog(this, "Xóa thành công!");
			String currCateID = mapCategories.get(listCategories.getSelectedValue());
			updateTable(currCateID);
			clearText();
			loadAllProductId();
		} else {
			JOptionPane.showMessageDialog(this, "Xóa thất bại!");
		}
		
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		return 0;
	}
}
