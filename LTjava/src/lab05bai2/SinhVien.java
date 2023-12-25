package lab05bai2;

//import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;

import connectMySQL.ConnectSQL;
import swing_template.ScrollTable;
import swing_template.TableModelEditable;
import validator.Validator;

public class SinhVien extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1204452110167482126L;

	private String maSoLop;
	private ConnectSQL connectSQL;

//	private JPanel this;
	private JTextField txtMsv;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtEmail;

	private JLabel lblCurrent;

	private JButton btnFirst;
	private JButton btnPrev;
	private JButton btnNext;
	private JButton btnLast;

	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnSua;
	private JButton btnXoa;

	private JPanel tablePanel;
	private Vector<String> header;
	private ScrollTable table;

	private boolean isAdd = false;
	private boolean isEdit = false;
	private boolean isCancel = false;

	private int index = 0;
	private int size = 0;

	private Validator validator;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SinhVien frame = new SinhVien("TH2");
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public SinhVien(String maSoLop) {
		this.maSoLop = maSoLop;
		connectSQL = new ConnectSQL("qlsv", "root", "");
		validator = new Validator();
		validator.addRule("msv", "required");
		validator.addRule("name", "required");

//		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		setBounds(100, 100, 681, 500);
		this.setPreferredSize(new Dimension(680, 500));
//		contentPane = new JPanel();
		this.setBorder(new EmptyBorder(5, 5, 5, 5));

//		setContentPane(contentPane);
		this.setLayout(null);

		JLabel lblNewLabel = new JLabel("THÔNG TIN SINH VIÊN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(10, 11, 645, 36);
		this.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã số SV");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(31, 58, 80, 14);
		this.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Họ và tên");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(31, 92, 80, 14);
		this.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(31, 117, 80, 14);
		this.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Địa chỉ");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(31, 151, 80, 14);
		this.add(lblNewLabel_4);

		txtMsv = new JTextField();
		txtMsv.setBounds(121, 58, 502, 20);
		this.add(txtMsv);
		txtMsv.setColumns(10);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(121, 89, 502, 20);
		this.add(txtName);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(121, 148, 502, 20);
		this.add(txtAddress);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(121, 117, 502, 20);
		this.add(txtEmail);

		btnFirst = new JButton("|<");
		btnFirst.setBounds(59, 176, 50, 23);
		this.add(btnFirst);

		btnPrev = new JButton("<");
		btnPrev.setBounds(108, 176, 50, 23);
		this.add(btnPrev);

		btnNext = new JButton(">");
		btnNext.setBounds(198, 176, 50, 23);
		this.add(btnNext);

		btnLast = new JButton(">|");
		btnLast.setBounds(248, 176, 50, 23);
		this.add(btnLast);

		lblCurrent = new JLabel("0/0");
		lblCurrent.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrent.setBounds(155, 180, 46, 14);
		this.add(lblCurrent);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(344, 176, 70, 23);
		this.add(btnThem);

		btnLuu = new JButton("Lưu");
		btnLuu.setBounds(414, 176, 70, 23);
		btnLuu.setEnabled(false);
		this.add(btnLuu);

		btnSua = new JButton("Sửa");
		btnSua.setBounds(484, 176, 70, 23);
		this.add(btnSua);

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(553, 176, 70, 23);
		this.add(btnXoa);

		tablePanel = new JPanel();
		tablePanel.setBorder(new TitledBorder(null, "Danh s\u00E1ch l\u1EDBp h\u1ECDc " + maSoLop, TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		tablePanel.setBounds(42, 216, 581, 223);
		this.add(tablePanel);
		tablePanel.setLayout(new GridLayout(0, 1, 0, 0));
		header = new Vector<String>();
		header.add("Mã số SV");
		header.add("Họ và tên");
		header.add("Email");
		header.add("Địa chỉ");

		table = new ScrollTable(new TableModelEditable(header, 0));
		loadTable();

		tablePanel.add(table);

		setEnable(false);

		// Add btn action
		btnThem.addActionListener(e -> {
			if (isCancel) {
				setEnable(false);
				clear();
				cancelStudent();
				btnThem.setText("Thêm");
				btnSua.setEnabled(true);
				isCancel = false;
				table.clearSelection();
			} else {
				setEnable(true);
				clear();
				isAdd = true;
				btnThem.setText("Hủy");
				btnSua.setEnabled(false);
				isCancel = true;
				btnLuu.setEnabled(true);
			}

		});

		btnSua.addActionListener(e -> {

			if (isCancel) {
				setEnable(false);
				clear();
				cancelStudent();
				btnSua.setText("Sửa");
				btnThem.setEnabled(true);
				isCancel = false;
				table.clearSelection();
			} else {
				if (txtMsv.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Chưa chọn sinh viên để sửa");
					return;
				}
				setEnable(true);
				txtMsv.setEnabled(false);
				isEdit = true;
				btnSua.setText("Hủy");
				btnThem.setEnabled(false);
				isCancel = true;
				btnLuu.setEnabled(true);
			}

		});
		
		btnXoa.addActionListener(e -> {
			
			deleteStudent();
		});

		btnLuu.addActionListener(e -> {
			saveStudent();
		});
		
		table.setListener(e -> {
            tableClick(e);
        });

//		this.setLocationRelativeTo(null);
	}

	void loadTable() {
		String sql = "SELECT * FROM sinhvien WHERE mslop = '" + maSoLop + "'";
		ResultSet rs = connectSQL.executeQuery(sql);
		if (rs == null) {
			System.out.println("Load table failed");
			return;
		}
		try {
			table.setDataFromResultSet(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		size = table.getRowCount();
		lblCurrent.setText((index) + "/" + size);
	}

	void addStudent() {
		String msv = txtMsv.getText();
		String name = txtName.getText();
		String email = txtEmail.getText();
		String address = txtAddress.getText();

		Map<String, String> data = new HashMap<String, String>();
		data.put("msv", msv);
		data.put("name", name);

		if (!validator.validate(data)) {
			JOptionPane.showMessageDialog(this, validator.getMessage());
			return;
		}

		String sql = "INSERT INTO sinhvien VALUES ('" + msv + "', '" + name + "', '" + email + "', '" + address + "', '"
				+ maSoLop + "')";
		if (connectSQL.executeUpdate(sql) > 0) {
			loadTable();
			JOptionPane.showMessageDialog(this, "Thêm thành công");
		} else {
			JOptionPane.showMessageDialog(this, "Thêm thất bại");
		}

		loadTable();

	}

	void editStudent() {
		String msv = txtMsv.getText();
		String name = txtName.getText();
		String email = txtEmail.getText();
		String address = txtAddress.getText();

		Map<String, String> data = new HashMap<String, String>();
		data.put("msv", msv);
		data.put("name", name);

		if (!validator.validate(data)) {
			JOptionPane.showMessageDialog(this, validator.getMessage());
			return;
		}

		String sql = "UPDATE sinhvien SET hoten = '" + name + "', email = '" + email + "', diachi = '" + address
				+ "' WHERE msv = '" + msv + "'";
		if (connectSQL.executeUpdate(sql) > 0) {
			loadTable();
			JOptionPane.showMessageDialog(this, "Sửa thành công");
		} else {
			JOptionPane.showMessageDialog(this, "Sửa thất bại");
		}

		loadTable();

	}
	
	void deleteStudent() {
		String msv = txtMsv.getText();
		Map<String, String> data = new HashMap<String, String>();
		data.put("msv", msv);
		if (!validator.validate(data)) {
			JOptionPane.showMessageDialog(this, "Chưa chọn sinh viên để xóa");
			return;
		}
		int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION) {
			return;
		}
		String sql = "DELETE FROM sinhvien WHERE msv = '" + msv + "'";
		if (connectSQL.executeUpdate(sql) > 0) {
			loadTable();
			JOptionPane.showMessageDialog(this, "Xóa thành công");
			clear();
		} else {
			JOptionPane.showMessageDialog(this, "Xóa thất bại");
		}

		loadTable();
	}

	void saveStudent() {
		if (isAdd) {
			addStudent();
			clear();
			cancelStudent();
			setEnable(false);
			return;
		}
		if (isEdit) {
			editStudent();
			clear();
			cancelStudent();
			setEnable(false);
			return;
		}

	}

	void cancelStudent() {
		isCancel = false;
		isAdd = false;
		isEdit = false;
		btnThem.setText("Thêm");
		btnSua.setText("Sửa");
		btnThem.setEnabled(true);
		btnSua.setEnabled(true);
		btnLuu.setEnabled(false);
	}

	void clear() {
		txtMsv.setText("");
		txtName.setText("");
		txtEmail.setText("");
		txtAddress.setText("");

		txtMsv.requestFocus();
	}

	void setEnable(boolean isEnable) {
		txtMsv.setEnabled(isEnable);
		txtName.setEnabled(isEnable);
		txtEmail.setEnabled(isEnable);
		txtAddress.setEnabled(isEnable);
	}

	void tableClick(ListSelectionEvent e) {
		int row = table.getSelectedRow();
		if (row == -1 || e.getValueIsAdjusting()) {
			return;
		}
		txtMsv.setText(table.getValueAt(row, 0).toString());
		txtName.setText(table.getValueAt(row, 1).toString());
		txtEmail.setText(table.getValueAt(row, 2).toString());
		txtAddress.setText(table.getValueAt(row, 3).toString());
	}
}
