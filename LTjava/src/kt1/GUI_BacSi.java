package kt1;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;

import form_interface.IFormInterface;
import swing_template.ScrollTable;
import swing_template.TableModelEditable;
import validator.Validator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_BacSi extends JFrame implements IFormInterface {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCode;
	private JTextField txtName;
	private JTextField txtSalary;
	
	private Vector<String> tableHeader = new Vector<String>();
	private TableModelEditable tableModel;
	private ScrollTable scrollTable;
	
	JRadioButton rbMale;
	JRadioButton rbFemale;
	JComboBox<String> comboBox;
	
	private Validator validator = new Validator();
	
	private XLDL xldl = new XLDL();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_BacSi frame = new GUI_BacSi();
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
	public GUI_BacSi() {
		setTitle("Quản lý bác sĩ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(52, 44, 80, 14);
		contentPane.add(lblNewLabel);
		
		txtCode = new JTextField();
		txtCode.setBounds(142, 41, 155, 20);
		contentPane.add(txtCode);
		txtCode.setColumns(10);
		
		JLabel lblHTn = new JLabel("Họ tên");
		lblHTn.setHorizontalAlignment(SwingConstants.TRAILING);
		lblHTn.setBounds(52, 72, 80, 14);
		contentPane.add(lblHTn);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(142, 69, 155, 20);
		contentPane.add(txtName);
		
		JLabel lblLng = new JLabel("Lương");
		lblLng.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLng.setBounds(52, 100, 80, 14);
		contentPane.add(lblLng);
		
		txtSalary = new JTextField();
		txtSalary.setColumns(10);
		txtSalary.setBounds(142, 97, 155, 20);
		contentPane.add(txtSalary);
		
		rbMale = new JRadioButton("Nam");
		rbMale.setSelected(true);
		rbMale.setBounds(351, 40, 109, 23);
		contentPane.add(rbMale);
		
		rbFemale = new JRadioButton("Nữ");
		rbFemale.setBounds(462, 40, 109, 23);
		contentPane.add(rbFemale);
		setLocationRelativeTo(null);
		
		ButtonGroup gender = new ButtonGroup();
		gender.add(rbFemale);
		gender.add(rbMale);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(351, 96, 226, 22);
		contentPane.add(comboBox);
		comboBox.addItem("Chỉnh hình");
		comboBox.addItem("Tim mạch");
		comboBox.addItem("Xương");
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleInsert();
			}
		});
		btnAdd.setBounds(142, 149, 100, 40);
		contentPane.add(btnAdd);
		
		JPanel panelTable = new JPanel();
		panelTable.setBounds(10, 200, 714, 370);
		contentPane.add(panelTable);
		panelTable.setLayout(new GridLayout(1, 0, 0, 0));
		
		tableHeader.add("Mã BS");
		tableHeader.add("Họ tên");
		tableHeader.add("Giới tính");
		tableHeader.add("Khoa");
		tableHeader.add("Lương");
		tableHeader.add("Lương thưởng");
		tableModel = new TableModelEditable(tableHeader);
		scrollTable = new ScrollTable(tableModel);
		panelTable.add(scrollTable);
		
		updateTable();
		
		// Add event listener
		scrollTable.setListener(e -> TableSelectionChanged(e));
		
		JButton btnEdit = new JButton("Sửa");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleUpdate();
			}
		});
		btnEdit.setBounds(257, 149, 100, 40);
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleDelete();
			}
		});
		btnDelete.setBounds(367, 149, 100, 40);
		contentPane.add(btnDelete);
		
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.setBounds(477, 149, 100, 40);
		contentPane.add(btnSearch);
		
		validator.addRule("ma", "required");
		validator.addRule("hoten", "required|max:50");
		validator.addRule("khoa", "required|max:50");
		validator.addRule("luong", "required|numeric");
		
	}
	
	public void clearForm() {
		txtCode.setText("");
		txtName.setText("");
		txtSalary.setText("");
		rbMale.setSelected(true);
		comboBox.setSelectedIndex(0);
	}

	@Override
	public void updateTable() {
		// TODO Auto-generated method stub
		ArrayList<BacSi> list = xldl.get();
		
		tableModel.setRowCount(0);
		
		for (BacSi bacsi : list) {
			Vector<String> row = new Vector<String>();
			row.add(bacsi.getMa());
			row.add(bacsi.getHoten());
			row.add(bacsi.getGt());
			row.add(bacsi.getKhoa());
			row.add(String.valueOf(bacsi.getLuong()));
			row.add(String.valueOf(bacsi.tinhLuongThuong()));

			tableModel.addRow(row);
		}
		clearForm();
		
	}

	@Override
	public void TableSelectionChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		int row = scrollTable.getSelectedRow();
		
		if (!e.getValueIsAdjusting() && row >= 0) {
			
			txtCode.setText(scrollTable.getValueAt(row, 0).toString());
			txtName.setText(scrollTable.getValueAt(row, 1).toString());
			txtSalary.setText(scrollTable.getValueAt(row, 4).toString());

			if (scrollTable.getValueAt(row, 2).toString().equalsIgnoreCase("Nam")) {
				rbMale.setSelected(true);
			} else {
				rbFemale.setSelected(true);
			}
			
			comboBox.setSelectedItem(scrollTable.getValueAt(row, 3).toString());
		}
	}

	@Override
	public void handleInsert() {
		// TODO Auto-generated method stub
		
		String ma = txtCode.getText();
		String hoten = txtName.getText();
		boolean gt = rbMale.isSelected();
		String khoa = comboBox.getSelectedItem().toString();
		String luong = txtSalary.getText();
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("ma", ma);
		data.put("hoten", hoten);
		data.put("khoa", khoa);
		data.put("luong", luong);
		
		if (validator.validate(data)) {
			BacSi bacsi = new BacSi(ma, hoten, gt, khoa, Double.parseDouble(luong));
			xldl.intsert(bacsi);

			updateTable();
		} else {
			System.out.println(validator.getMessage());
			JOptionPane.showMessageDialog(this, validator.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	@Override
	public void handleUpdate() {
		// TODO Auto-generated method stub
		String ma = txtCode.getText();
		String hoten = txtName.getText();
		boolean gt = rbMale.isSelected();
		String khoa = comboBox.getSelectedItem().toString();
		String luong = txtSalary.getText();
		
		if (ma.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn bác sĩ cần sửa", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("ma", ma);
		data.put("hoten", hoten);
		data.put("khoa", khoa);
		data.put("luong", luong);
		
		if (validator.validate(data)) {
			BacSi bacsi = new BacSi(ma, hoten, gt, khoa, Double.parseDouble(luong));
			int res = xldl.update(bacsi);
			
			if (res > 0) {
				updateTable();
				JOptionPane.showMessageDialog(this, "Cập nhật thành công", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Cập nhật thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}

		} else {
			System.out.println(validator.getMessage());
			JOptionPane.showMessageDialog(this, validator.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	@Override
	public void handleDelete() {
		// TODO Auto-generated method stub
		int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa bác sĩ này không?", "Xác nhận",
				JOptionPane.YES_NO_OPTION);
		
		if (confirm != JOptionPane.YES_OPTION) {
			return;
		}
		
		if (scrollTable.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn bác sĩ cần xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String ma = txtCode.getText();
		BacSi bacsi = new BacSi();
		bacsi.setMa(ma);
		
		int res = xldl.delete(bacsi);
		
		if (res > 0) {
			updateTable();
			JOptionPane.showMessageDialog(this, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Xóa thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void handleSearch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleExit() {
		// TODO Auto-generated method stub
		
	}
}
