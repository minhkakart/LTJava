package swing_ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import oop.bai2.Student;
import drag.Panel;

public class StudentForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ImageIcon successIcon;
	ImageIcon failedIcon;

	ConnectSQL connect = new ConnectSQL();
	
	Vector<String> tableHeader;
	DefaultTableModel tableModel;

	JPanel upPanel;
	JPanel downPanel;
	JPanel CRUDPanel;
	JPanel infoPanel;
	JPanel westPanel;
	JPanel eastPanel;
	JPanel northPanel;

	JScrollPane scrollPane;
	JTable table;

	JButton btnCreate;
	JButton btnEdit;
	JButton btnDelete;
	JButton btnClear;

	JLabel lbCode;
	JLabel lbName;
	JLabel lbAddress;
	JLabel lbPoint;

	JTextField txtCode;
	JTextField txtName;
	JComboBox<String> txtAddress;
	JTextField txtPoint;

	private final int WIDTH = 720;
	private final int HEIGHT = 500;

	public StudentForm() {
		this.setIconImage(new ImageIcon("src/ei-ico.jpg").getImage());
		this.setLayout(new GridLayout(0, 1, 10, 10));

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
		connect.setConnection("jdbc:mysql://localhost:3306/swing_ui", "root", "");
		
		// Tạo icon
		successIcon = new ImageIcon("src/success-green-icon.jpg");
		failedIcon = new ImageIcon("src/failed-icon.png");
		successIcon.setImage(successIcon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
		failedIcon.setImage(failedIcon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));

		// Tạo table header
		tableHeader = new Vector<String>();
		tableHeader.add("Code");
		tableHeader.add("Name");
		tableHeader.add("Address");
		tableHeader.add("Point");
		
		// Tạo table model
		tableModel = new DefaultTableModel(tableHeader, 0);
		loadData();
		
		// Tạo các panel layout
		upPanel = new JPanel(new BorderLayout(10, 10));
		downPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		CRUDPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 10));
		infoPanel = new JPanel(new GridLayout(0, 2, 10, 15));
		westPanel = new Panel("src/ei.jpg", 200, 165);
		eastPanel = new Panel("src/ei.jpg", 200, 165);
		northPanel = new JPanel();
		scrollPane = new JScrollPane();
		table = new JTable(tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(600, 150));

		// Tạo các button
		btnCreate = new JButton("Create");
		btnEdit = new JButton("Edit");
		btnDelete = new JButton("Delete");
		btnClear = new JButton("Clear");
		btnCreate.setFocusable(false);
		btnEdit.setFocusable(false);
		btnDelete.setFocusable(false);
		btnClear.setFocusable(false);

		// Thêm các button vào CRUDPanel
		CRUDPanel.setPreferredSize(new Dimension(720, 50));
		CRUDPanel.add(btnCreate);
		CRUDPanel.add(btnEdit);
		CRUDPanel.add(btnDelete);
		CRUDPanel.add(btnClear);
		upPanel.add(CRUDPanel, BorderLayout.SOUTH);

		// Tạo các label và textfield
		lbCode = new JLabel("Code");
		lbName = new JLabel("Name");
		lbAddress = new JLabel("Address");
		lbPoint = new JLabel("Point");

		txtCode = new JTextField();
		txtName = new JTextField();
		txtAddress = new JComboBox<String>();
		txtPoint = new JTextField("0");
		
		initAddress();

		infoPanel.add(lbCode);
		infoPanel.add(txtCode);
		infoPanel.add(lbName);
		infoPanel.add(txtName);
		infoPanel.add(lbAddress);
		infoPanel.add(txtAddress);
		infoPanel.add(lbPoint);
		infoPanel.add(txtPoint);
		
		// Căn chỉnh vị trí của các label
		lbCode.setHorizontalAlignment(JLabel.CENTER);
		lbCode.setVerticalAlignment(JLabel.CENTER);
		lbName.setHorizontalAlignment(JLabel.CENTER);
		lbName.setVerticalAlignment(JLabel.CENTER);
		lbAddress.setHorizontalAlignment(JLabel.CENTER);
		lbAddress.setVerticalAlignment(JLabel.CENTER);
		lbPoint.setHorizontalAlignment(JLabel.CENTER);
		lbPoint.setVerticalAlignment(JLabel.CENTER);
		
		// Thêm infoPanel vào upPanel ở vị trí CENTER
		infoPanel.setPreferredSize(new Dimension(0, 100));
		upPanel.add(infoPanel, BorderLayout.CENTER);

		// Thêm các panel phụ vào upPanel
		westPanel.setPreferredSize(new Dimension(200, 0));
		eastPanel.setPreferredSize(new Dimension(200, 0));
		northPanel.setPreferredSize(new Dimension(0, 10));
		upPanel.add(westPanel, BorderLayout.EAST);
		upPanel.add(eastPanel, BorderLayout.WEST);
		upPanel.add(northPanel, BorderLayout.NORTH);

		// Thêm table vào scrollPane và scrollPane vào downPanel
		scrollPane.setViewportView(table);
		downPanel.add(scrollPane);
		
		// Set background cho các panel
		upPanel.setBackground(Color.RED);
		downPanel.setBackground(Color.BLUE);
		CRUDPanel.setBackground(Color.GREEN);
		infoPanel.setBackground(Color.YELLOW);
		northPanel.setBackground(Color.MAGENTA);

		// Xử lý sự kiện cho button Clear
		btnClear.addActionListener(e -> {
			clearTextFields();
		});

		// Xử lý sự kiện cho button Create
		btnCreate.addActionListener(e -> {
			String code = txtCode.getText();
			String name = txtName.getText();
			String address = txtAddress.getSelectedItem().toString();
			double point = Double.parseDouble(txtPoint.getText());

			Student student = new Student(code, name, address, point);
			if (student.createStudent()) {
				JOptionPane.showMessageDialog(null, "Create success", "Success", JOptionPane.INFORMATION_MESSAGE,
						successIcon);
				clearTableModel();
				loadData();
				clearTextFields();
			} else {
				JOptionPane.showMessageDialog(null, "Create failed", "Failed", JOptionPane.ERROR_MESSAGE, failedIcon);
			}
		});
		
		// Xử lý sự kiện cho button Edit
		btnEdit.addActionListener(e -> {
			String code = txtCode.getText();
			String name = txtName.getText();
			String address = txtAddress.getSelectedItem().toString();
			double point = Double.parseDouble(txtPoint.getText());

			Student student = new Student(code, name, address, point);
			if (student.createStudent()) {
				JOptionPane.showMessageDialog(null, "Edit success", "Success", JOptionPane.INFORMATION_MESSAGE,
                        successIcon);
				clearTableModel();
				loadData();
				clearTextFields();
			} else {
				JOptionPane.showMessageDialog(null, "Edit failed", "Failed", JOptionPane.ERROR_MESSAGE, failedIcon);
			}
		});
		
		// Xử lý sự kiện cho button Delete
		btnDelete.addActionListener(e -> {
			int option = JOptionPane.showConfirmDialog(null, "Are you sure to delete?", "Confirm", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				deleteStudent();
			}
		});
		
		// Thêm 2 panel chính vào frame
		this.add(upPanel);
		this.add(downPanel);

	}
	
	// Load data from database to table
	private void loadData() {
		ResultSet res = connect.getAllData("students");
		try {
			if (res == null) {
				JOptionPane.showMessageDialog(null, "Error: ResultSet is null");
				return;
			} else {
				while (res.next()) {
					Vector<String> row = new Vector<String>();
					row.add(res.getString(2));
					row.add(res.getString(3));
					row.add(res.getString(4));
					row.add(res.getString(5));
					tableModel.addRow(row);
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}
	
	// Clear table model
	private void clearTableModel() {
		tableModel.setRowCount(0);
	}

	// Clear text fields
	private void clearTextFields() {
		txtCode.setText("");
		txtName.setText("");
		txtAddress.setSelectedIndex(0);
		txtPoint.setText("0");

		txtCode.requestFocus();
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
	
	// Delete student
	private void deleteStudent() {
		String code = txtCode.getText();
		String name = txtName.getText();
		String address = txtAddress.getSelectedItem().toString();
		double point = Double.parseDouble(txtPoint.getText());

		Student student = new Student(code, name, address, point);
		if (student.deleteStudent()) {
			JOptionPane.showMessageDialog(null, "Delete success", "Success", JOptionPane.INFORMATION_MESSAGE,
                    successIcon	);
			clearTableModel();
			loadData();
			clearTextFields();
		} else {
			JOptionPane.showMessageDialog(null, "Delete failed", "Failed", JOptionPane.ERROR_MESSAGE, failedIcon);
		}
	}

}
