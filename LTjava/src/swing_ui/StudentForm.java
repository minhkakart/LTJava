package swing_ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;

import swing_template.TableModelEditable;
import connectMySQL.ConnectSQL;
import oop.bai2.Student;
import swing_template.ScrollTable;
import validator.Validator;
import drag.Panel;

public class StudentForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ImageIcon successIcon;
	ImageIcon failedIcon;

	ConnectSQL connect;

	Validator validator;
	Map<String, String> rules;

	JPanel upPanel;
	JPanel downPanel;
	JPanel CRUDPanel;
	JPanel infoPanel;
	JPanel westPanel;
	JPanel eastPanel;
	JPanel northPanel;

	JLabel lbID;
	JLabel lbCode;
	JLabel lbName;
	JLabel lbAddress;
	JLabel lbPoint;

	JTextField txtCode;
	JTextField txtName;
	JComboBox<String> txtAddress;
	JTextField txtPoint;

	JButton btnCreate;
	JButton btnEdit;
	JButton btnDelete;
	JButton btnClear;

	Vector<String> tableHeader;
	ScrollTable scrollTable;
	TableModelEditable tableModel;

	private final int WIDTH = 720;
	private final int HEIGHT = 500;

	public StudentForm() {
		this.setIconImage(new ImageIcon("src/ei-ico.jpg").getImage());
		this.setLayout(new GridLayout(0, 1));

		initComponents();

		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setTitle("Student Form");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void initComponents() {
		// Thay đổi thông tin kết nối tại đây
		connect = new ConnectSQL();
		connect.setConnection("swing_ui", "root", "");

		// Tạo icon
		successIcon = new ImageIcon("src/success-green-icon.jpg");
		failedIcon = new ImageIcon("src/failed-icon.png");

		// Tạo validator
		validator = new Validator();
		rules = new HashMap<String, String>();

		// Tạo các panel layout
		upPanel = new JPanel(new BorderLayout(10, 10));
		downPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		CRUDPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 10));
		infoPanel = new JPanel(new GridLayout(0, 2, 10, 15));
		westPanel = new Panel("src/ei.jpg", 200, 170, -50, -20);
		eastPanel = new Panel("src/focalors.jpg", 200, 170, 0, 120);
		northPanel = new JPanel();

		// Tạo các label
		lbID = new JLabel();
		lbID.setVisible(false);
		lbCode = new JLabel("Code");
		lbName = new JLabel("Name");
		lbAddress = new JLabel("Address");
		lbPoint = new JLabel("Point");

		// Tạo các textfield
		txtCode = new JTextField();
		txtName = new JTextField();
		txtAddress = new JComboBox<String>();
		txtPoint = new JTextField("0");

		// Tạo các button
		btnCreate = new JButton("Create");
		btnEdit = new JButton("Edit");
		btnDelete = new JButton("Delete");
		btnClear = new JButton("Clear");
		
		// Tạo table
		tableHeader = new Vector<String>();
		tableModel = new TableModelEditable(tableHeader, 0);
		scrollTable = new ScrollTable();

		///////////////////////////////////////////////

		///////// Tạo rules /////////
		rules.put("code", "required");
		rules.put("name", "required");
		rules.put("address", "required");
		rules.put("point", "required | numeric | min:0 | max:10");

		///////////////////////////////////////////////

		///////// Layout upPanel /////////
		// Center
		infoPanel.add(lbCode);
		infoPanel.add(txtCode);
		infoPanel.add(lbName);
		infoPanel.add(txtName);
		infoPanel.add(lbAddress);
		infoPanel.add(txtAddress);
		infoPanel.add(lbPoint);
		infoPanel.add(txtPoint);

		// North
		northPanel.add(lbID);

		// South
		CRUDPanel.add(btnCreate);
		CRUDPanel.add(btnEdit);
		CRUDPanel.add(btnDelete);
		CRUDPanel.add(btnClear);
		// East
		// West
		// Add to upPanel
		upPanel.add(infoPanel, BorderLayout.CENTER);
		upPanel.add(westPanel, BorderLayout.EAST);
		upPanel.add(eastPanel, BorderLayout.WEST);
		upPanel.add(northPanel, BorderLayout.NORTH);
		upPanel.add(CRUDPanel, BorderLayout.SOUTH);

		///////// Layout downPanel /////////
		// Xây dựng table
		
		
		downPanel.add(scrollTable);

		///////////////////////////////////////////////

		///////// Chỉnh sửa giao diện các components /////////
		// Icons
		successIcon.setImage(successIcon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
		failedIcon.setImage(failedIcon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));

		// Panels
		infoPanel.setPreferredSize(new Dimension(0, 100));
		northPanel.setPreferredSize(new Dimension(0, 10));
		CRUDPanel.setPreferredSize(new Dimension(720, 50));
		eastPanel.setPreferredSize(new Dimension(200, 0));
		westPanel.setPreferredSize(new Dimension(200, 0));

		upPanel.setBackground(Color.RED);
		downPanel.setBackground(Color.BLUE);
		CRUDPanel.setBackground(Color.GREEN);
		infoPanel.setBackground(Color.YELLOW);
		northPanel.setBackground(Color.MAGENTA);

		// Labels
		lbCode.setHorizontalAlignment(JLabel.CENTER);
		lbCode.setVerticalAlignment(JLabel.CENTER);
		lbName.setHorizontalAlignment(JLabel.CENTER);
		lbName.setVerticalAlignment(JLabel.CENTER);
		lbAddress.setHorizontalAlignment(JLabel.CENTER);
		lbAddress.setVerticalAlignment(JLabel.CENTER);
		lbPoint.setHorizontalAlignment(JLabel.CENTER);
		lbPoint.setVerticalAlignment(JLabel.CENTER);

		// Buttons
		btnCreate.setFocusable(false);
		btnEdit.setFocusable(false);
		btnDelete.setFocusable(false);
		btnClear.setFocusable(false);

		// Table
		
		///////////////////////////////////////////////

		// Thêm 2 panel chính vào frame
		this.add(upPanel);
		this.add(downPanel);

		///////////////////////////////////////////////

		///////// Tạo dữ liệu /////////
		// Load data cho table
		initTableHeader();
		scrollTable.setModel(tableModel);
		scrollTable.setDimention(new Dimension(600, 150));
		loadTableData();
		
		// Tạo dữ liệu cho combobox
		initAddress();

		///////////////////////////////////////////////

		///////// Xử lí sự kiện /////////
		// Set table listener
		scrollTable.setListener(e -> {
			listSelectionModelHandler(e);
		});

		// Xử lý sự kiện cho button Create
		btnCreate.addActionListener(e -> {
			createStudent();
		});

		// Xử lý sự kiện cho button Edit
		btnEdit.addActionListener(e -> {
			editStudent();
		});

		// Xử lý sự kiện cho button Delete
		btnDelete.addActionListener(e -> {
			deleteStudent();
		});

		// Xử lý sự kiện cho button Clear
		btnClear.addActionListener(e -> {
			clearTextFields();
		});

	}
	
	// Init table header
		private void initTableHeader() {
			tableHeader.add("ID");
			tableHeader.add("Code");
			tableHeader.add("Name");
			tableHeader.add("Address");
			tableHeader.add("Point");
		}

	// Add address to combobox
	private void initAddress() {
		txtAddress.addItem("Ha Noi");
		txtAddress.addItem("Hai Phong");
		txtAddress.addItem("Da Nang");
		txtAddress.addItem("Hue");
		txtAddress.addItem("Ho Chi Minh");
		txtAddress.addItem("Can Tho");
		txtAddress.addItem("Nha Trang");
		txtAddress.addItem("Vung Tau");
		txtAddress.addItem("Phu Quoc");
		txtAddress.addItem("Da Lat");
		txtAddress.addItem("Quang Ninh");
		txtAddress.addItem("Quang Binh");
		txtAddress.addItem("Quang Tri");
		txtAddress.addItem("Quang Nam");
		txtAddress.addItem("Quang Ngai");
	}

	// Load data from database to table
	private void loadTableData() {
		ResultSet res = connect.getAllDataTable("students");
		try {
			if (res == null) {
				JOptionPane.showMessageDialog(null, "Error: ResultSet is null");
				return;
			} else {
				tableModel.setRowCount(0);
				scrollTable.setDataFromResultSet(res);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}

	// Create new student
	private void createStudent() {
		String code = txtCode.getText();
		String name = txtName.getText();
		String address = txtAddress.getSelectedItem().toString();
		String strPoint = txtPoint.getText();

		// Tạo data
		Map<String, String> data = new HashMap<String, String>();
		data.put("code", code);
		data.put("name", name);
		data.put("address", address);
		data.put("point", String.valueOf(strPoint));

		// Validate data
		if (!validator.validate(rules, data)) {
			String message = validator.getMessage();
			JOptionPane.showMessageDialog(null, message, "Failed", JOptionPane.ERROR_MESSAGE, failedIcon);
			return;
		}

		double point = Double.parseDouble(strPoint);

		Student student = new Student(code, name, address, point);
		if (student.createStudent()) {
			JOptionPane.showMessageDialog(null, "Create success", "Success", JOptionPane.INFORMATION_MESSAGE,
					successIcon);
			clearTextFields();
			loadTableData();
		} else {
			JOptionPane.showMessageDialog(null, "Create failed", "Failed", JOptionPane.ERROR_MESSAGE, failedIcon);
		}

	}

	// Edit student
	private void editStudent() {
		String id = lbID.getText();
		String code = txtCode.getText();
		String name = txtName.getText();
		String address = txtAddress.getSelectedItem().toString();
		String strPoint = txtPoint.getText();

		// Tạo data
		Map<String, String> data = new HashMap<String, String>();
		data.put("code", code);
		data.put("name", name);
		data.put("address", address);
		data.put("point", String.valueOf(strPoint));

		// Validate data
		if (Validator.isBlank(id)) {
			JOptionPane.showMessageDialog(null, "No student selected!", "Failed", JOptionPane.ERROR_MESSAGE,
					failedIcon);
			return;
		}

		if (!validator.validate(rules, data)) {
			String message = validator.getMessage();
			JOptionPane.showMessageDialog(null, message, "Failed", JOptionPane.ERROR_MESSAGE, failedIcon);
			return;
		}

		double point = Double.parseDouble(strPoint);

		Student student = new Student(code, name, address, point);
		if (student.editStudent(id)) {
			JOptionPane.showMessageDialog(null, "Edit success", "Success", JOptionPane.INFORMATION_MESSAGE,
					successIcon);
			clearTextFields();
			loadTableData();
		} else {
			JOptionPane.showMessageDialog(null, "Edit failed", "Failed", JOptionPane.ERROR_MESSAGE, failedIcon);
		}

	}

	// Delete student
	private void deleteStudent() {
		String id = lbID.getText();

		if (Validator.isBlank(id)) {
			JOptionPane.showMessageDialog(null, "Failed! Please select a student to delete.", "Failed",
					JOptionPane.ERROR_MESSAGE, failedIcon);
			return;
		}

		String question = "Are you sure to delete? (id = " + id + ")";

		int option = JOptionPane.showConfirmDialog(null, question, "Confirm", JOptionPane.YES_NO_OPTION);
		if (option != JOptionPane.YES_OPTION) {
			return;
		}

		if (Student.deleteStudent(id)) {
			JOptionPane.showMessageDialog(null, "Delete success", "Success", JOptionPane.INFORMATION_MESSAGE,
					successIcon);
			clearTextFields();
			loadTableData();
		} else {
			JOptionPane.showMessageDialog(null, "Delete failed", "Failed", JOptionPane.ERROR_MESSAGE, failedIcon);
		}
	}

	// Clear text fields
	private void clearTextFields() {
		lbID.setText("");

		txtCode.setText("");
		txtName.setText("");
		txtAddress.setSelectedIndex(0);
		txtPoint.setText("0");

		txtCode.requestFocus();
	}

///////// Xử lí sự kiện cho table /////////
	private void listSelectionModelHandler(ListSelectionEvent e) {
		int row = scrollTable.getSelectedRow();
		if (!e.getValueIsAdjusting() && row != -1) {
			lbID.setText(scrollTable.getValueAt(row, 0).toString());
			txtCode.setText(scrollTable.getValueAt(row, 1).toString());
			txtName.setText(scrollTable.getValueAt(row, 2).toString());
			txtAddress.setSelectedItem(scrollTable.getValueAt(row, 3).toString());
			txtPoint.setText(scrollTable.getValueAt(row, 4).toString());
		}
	}

}
