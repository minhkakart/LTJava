package lab05bai4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;

import connectMySQL.ConnectSQL;
import form_interface.IFormInterface;
import querry_builder.QuerryBuilder;
import swing_template.ScrollTable;
import swing_template.TableModelEditable;
import validator.Validator;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame implements IFormInterface {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCode;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtPhone;

	private JLabel lblIndex;

	private JButton btnSave;
	private JButton btnAdd;
	private JButton btnEdit;

	private boolean isInserting = false;
	private boolean isEditing = false;

	private Vector<String> columnNames = new Vector<String>();
	private ScrollTable scrollTable;
	private int selectedRow = -1;

	private ConnectSQL connectSQL = new ConnectSQL("dlkh", "root", "");

	private Validator validator = new Validator();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Th\u00F4ng tin chi ti\u1EBFt", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel.setBounds(10, 11, 540, 280);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("THÔNG TIN NHÀ XUẤT BẢN");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 520, 42);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã nhà xuất bản:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(70, 64, 96, 14);
		panel.add(lblNewLabel_1);

		txtCode = new JTextField();
		txtCode.setBounds(176, 61, 290, 20);
		panel.add(txtCode);
		txtCode.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Tên nhà xuất bản:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1.setBounds(70, 106, 96, 14);
		panel.add(lblNewLabel_1_1);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(176, 103, 290, 20);
		panel.add(txtName);

		JLabel lblNewLabel_1_2 = new JLabel("Địa chỉ:");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_2.setBounds(70, 150, 96, 14);
		panel.add(lblNewLabel_1_2);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(176, 147, 290, 20);
		panel.add(txtAddress);

		JLabel lblNewLabel_1_3 = new JLabel("Điện thoại:");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_3.setBounds(70, 194, 96, 14);
		panel.add(lblNewLabel_1_3);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(176, 191, 290, 20);
		panel.add(txtPhone);

		JButton btnPrev = new JButton("Về trước");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedRow > 0) {
					selectedRow--;
					scrollTable.getTable().setRowSelectionInterval(selectedRow, selectedRow);
				}
			}
		});
		btnPrev.setBounds(134, 219, 112, 42);
		panel.add(btnPrev);

		JButton btnNext = new JButton("Về sau");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedRow < scrollTable.getTable().getRowCount() - 1) {
					selectedRow++;
					scrollTable.getTable().setRowSelectionInterval(selectedRow, selectedRow);
				}
			}
		});
		btnNext.setBounds(320, 219, 112, 42);
		panel.add(btnNext);

		lblIndex = new JLabel("0/0");
		lblIndex.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndex.setBounds(256, 233, 54, 14);
		panel.add(lblIndex);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Th\u1EF1c hi\u1EC7n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(560, 11, 134, 280);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(e -> {
			isInserting = true;
			isEditing = false;
			btnEdit.setEnabled(false);
			setEnableForm(true);
			clearForm();

		});
		panel_1.add(btnAdd);

		btnSave = new JButton("Lưu");
		btnSave.addActionListener(e -> {
			handleSave();
		});
		panel_1.add(btnSave);

		btnEdit = new JButton("Sửa");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Please select a row");
					return;
				}
				
				isInserting = false;
				isEditing = true;
				btnAdd.setEnabled(false);
				setEnableForm(true);

				txtCode.setEnabled(false);
			}
		});
		panel_1.add(btnEdit);

		JButton btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleDelete();
			}
		});
		panel_1.add(btnDelete);

		JPanel panelTable = new JPanel();
		panelTable.setBorder(new TitledBorder(null, "Danh s\u00E1ch nh\u00E0 xu\u1EA5t b\u1EA3n", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelTable.setBounds(10, 302, 684, 250);
		contentPane.add(panelTable);
		panelTable.setLayout(new GridLayout(1, 1, 0, 0));

		columnNames.add("Mã nhà xuất bản");
		columnNames.add("Tên nhà xuất bản");
		columnNames.add("Địa chỉ");
		columnNames.add("Điện thoại");

		scrollTable = new ScrollTable(new TableModelEditable(columnNames));
		panelTable.add(scrollTable);
		updateTable();
		scrollTable.setListener(e -> {
			TableSelectionChanged(e);
		});

		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleSearch();
			}
		});
		btnSearch.setBounds(10, 563, 120, 47);
		contentPane.add(btnSearch);

		setEnableForm(false);

		validator.addRule("publishercode", "required");
		validator.addRule("publishername", "required");
		validator.addRule("address", "required");
		validator.addRule("phone", "required");

		setLocationRelativeTo(null);
	}
	
	public void clearForm() {
		txtCode.setText("");
		txtName.setText("");
		txtAddress.setText("");
		txtPhone.setText("");
	}

	public void updateIndexLable() {
		lblIndex.setText((selectedRow + 1) + "/" + scrollTable.getTable().getRowCount());
	}

	public void setEnableForm(boolean enable) {
		txtCode.setEnabled(enable);
		txtName.setEnabled(enable);
		txtAddress.setEnabled(enable);
		txtPhone.setEnabled(enable);

		btnSave.setEnabled(enable);
	}

	public void handleSave() {
		if (isInserting) {
			handleInsert();
		} else if (isEditing) {
			handleUpdate();
		}
		btnAdd.setEnabled(true);
		btnEdit.setEnabled(true);
		setEnableForm(false);
		clearForm();
	}

	@Override
	public void updateTable() {
		// TODO Auto-generated method stub
		try {
			scrollTable.setDataFromResultSet(connectSQL.getAllDataTable("tblpublisher"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void TableSelectionChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		selectedRow = scrollTable.getSelectedRow();
		if (!e.getValueIsAdjusting() && selectedRow != -1) {
			txtCode.setText((String) scrollTable.getValueAt(selectedRow, 0));
			txtName.setText((String) scrollTable.getValueAt(selectedRow, 1));
			txtAddress.setText((String) scrollTable.getValueAt(selectedRow, 2));
			txtPhone.setText((String) scrollTable.getValueAt(selectedRow, 3));
			updateIndexLable();
		}
	}

	@Override
	public void handleInsert() {
		// TODO Auto-generated method stub
		System.out.println("Inserting");

		String publisherCode = txtCode.getText();
		String publisherName = txtName.getText();
		String address = txtAddress.getText();
		String phone = txtPhone.getText();

		Map<String, String> data = new HashMap<String, String>();
		data.put("publishercode", publisherCode);
		data.put("publishername", publisherName);
		data.put("address", address);
		data.put("phone", phone);

		if (validator.validate(data)) {
			String sql = new QuerryBuilder().buildInsert("tblpublisher", data).getQuerry();
			int result = connectSQL.executeUpdate(sql);
			if (result > 0) {
				System.out.println("Insert successfully");
				JOptionPane.showMessageDialog(null, "Insert successfully");
			} else {
				System.out.println("Insert failed");
				JOptionPane.showMessageDialog(null, "Insert failed");
			}
			updateTable();
		} else {
			System.out.println(validator.getMessage());
			JOptionPane.showMessageDialog(null, validator.getMessage());
		}

	}

	@Override
	public void handleUpdate() {
		// TODO Auto-generated method stub
		System.out.println("Updating");

		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(null, "Please select a row");
			return;
		}

		String publisherCode = txtCode.getText();
		String publisherName = txtName.getText();
		String address = txtAddress.getText();
		String phone = txtPhone.getText();
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("publishername", publisherName);
		data.put("address", address);
		data.put("phone", phone);
		
		if (validator.validate(data)) {
			String sql = new QuerryBuilder().update("tblpublisher").set(data).where("publishercode = '" + publisherCode + "'").getQuerry();
			int result = connectSQL.executeUpdate(sql);
			if (result > 0) {
				System.out.println("Update successfully");
				JOptionPane.showMessageDialog(null, "Update successfully");
			} else {
				System.out.println("Update failed");
				JOptionPane.showMessageDialog(null, "Update failed");
			}
			updateTable();
		} else {
			System.out.println(validator.getMessage());
			JOptionPane.showMessageDialog(null, validator.getMessage());
		}
	}

	@Override
	public void handleDelete() {
		// TODO Auto-generated method stub
		System.out.println("Deleting");
		
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(null, "Please select a row");
			return;
		}
		
		int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận xóa?", "Xóa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if (confirm != JOptionPane.YES_OPTION) {
			return;
		}
		
		String publisherCode = txtCode.getText();
		
		String sql = new QuerryBuilder().deleteFrom("tblpublisher").where("publishercode = '" + publisherCode + "'").getQuerry();
		int result = connectSQL.executeUpdate(sql);
		if (result > 0) {
			System.out.println("Delete successfully");
			updateTable();
			JOptionPane.showMessageDialog(null, "Delete successfully");
		} else {
			System.out.println("Delete failed");
			JOptionPane.showMessageDialog(null, "Delete failed");
		}
		
	}

	@Override
	public void handleSearch() {
		// TODO Auto-generated method stub
		System.out.println("Searching");
		this.setVisible(false);
		JDialog searchDialog = new JDialog(this, "Tìm kiếm", true);
		searchDialog.setContentPane(new SearchTool(this.connectSQL));
		searchDialog.pack();
		searchDialog.setLocationRelativeTo(this);
		searchDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		searchDialog.setVisible(true);
		this.setVisible(true);
	}

	@Override
	public void handleExit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
