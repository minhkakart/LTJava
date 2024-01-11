package gps;

import java.awt.EventQueue;

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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_insertDog extends JFrame implements IFormInterface {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCode;
	private JTextField txtName;
	private JTextField txtBirthYear;
	private JTextField txtGender;
	private JLabel lblNewLabel_3;
	private JComboBox<String> comboBox;
	
	private Vector<String> columnNames = new Vector<String>();
	private TableModelEditable model;
	private ScrollTable scrollTable;
	
	private XLDL xldl = new XLDL();
	
	private Validator validator = new Validator();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_insertDog frame = new GUI_insertDog();
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
	public GUI_insertDog() {
		setTitle("Pet shop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã");
		lblNewLabel.setBounds(102, 37, 80, 14);
		contentPane.add(lblNewLabel);
		
		txtCode = new JTextField();
		txtCode.setBounds(192, 34, 150, 20);
		contentPane.add(txtCode);
		txtCode.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tên");
		lblNewLabel_1.setBounds(102, 65, 80, 14);
		contentPane.add(lblNewLabel_1);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(192, 62, 150, 20);
		contentPane.add(txtName);
		
		JLabel lblNewLabel_1_1 = new JLabel("Năm sinh");
		lblNewLabel_1_1.setBounds(377, 65, 80, 14);
		contentPane.add(lblNewLabel_1_1);
		
		txtBirthYear = new JTextField();
		txtBirthYear.setColumns(10);
		txtBirthYear.setBounds(467, 62, 150, 20);
		contentPane.add(txtBirthYear);
		
		txtGender = new JTextField();
		txtGender.setColumns(10);
		txtGender.setBounds(467, 34, 150, 20);
		contentPane.add(txtGender);
		
		JLabel lblNewLabel_2 = new JLabel("Giới tính");
		lblNewLabel_2.setBounds(377, 37, 80, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Quốc gia");
		lblNewLabel_3.setBounds(102, 90, 80, 14);
		contentPane.add(lblNewLabel_3);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(192, 86, 150, 22);
		contentPane.add(comboBox);
		
		comboBox.addItem("Việt Nam");
		comboBox.addItem("Đức");
		comboBox.addItem("Trung Quốc");
		comboBox.addItem("Anh");
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleInsert();
			}
		});
		btnAdd.setBounds(528, 135, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleSearch();
			}
		});
		btnSearch.setBounds(414, 135, 89, 23);
		contentPane.add(btnSearch);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 195, 684, 295);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		columnNames.add("Mã");
		columnNames.add("Tên");
		columnNames.add("Giới tính");
		columnNames.add("Năm sinh");
		columnNames.add("Quốc gia");
		columnNames.add("Tuổi còn lại");
		
		model = new TableModelEditable(columnNames);
		scrollTable = new ScrollTable(model);
		panel.add(scrollTable);
		
		updateTable();
		
		scrollTable.setListener(e -> TableSelectionChanged(e));
		
		validator.addRule("ma", "required");
		validator.addRule("ten", "required");
		validator.addRule("gioitinh", "required");
		validator.addRule("namsinh", "required|integer|min:2013");
		validator.addRule("quocgia", "required");
		
		
	}

	@Override
	public void updateTable() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
		
		ArrayList<Dog> list = xldl.get();
		
		for (Dog dog : list) {
			Vector<String> row = new Vector<String>();
			row.add(dog.getCode());
			row.add(dog.getName());
			row.add(dog.getGender());
			row.add(dog.getBirth() + "");
			row.add(dog.getQuocGia());
			row.add(dog.getRemainingYears() + "");

			model.addRow(row);
		}
		
		clearForm();
		
	}

	@Override
	public void TableSelectionChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		int row = scrollTable.getTable().getSelectedRow();
		
		if (!e.getValueIsAdjusting() && row != -1) {
			txtCode.setText(scrollTable.getTable().getValueAt(row, 0).toString());
			txtName.setText(scrollTable.getTable().getValueAt(row, 1).toString());
			txtGender.setText(scrollTable.getTable().getValueAt(row, 2).toString());
			txtBirthYear.setText(scrollTable.getTable().getValueAt(row, 3).toString());
			comboBox.setSelectedItem(scrollTable.getTable().getValueAt(row, 4).toString());
		}
	}

	@Override
	public void handleInsert() {
		// TODO Auto-generated method stub
		String code = txtCode.getText();
		String name = txtName.getText();
		String gender = txtGender.getText();
		String birthYear = txtBirthYear.getText();
		String quocGia = comboBox.getSelectedItem().toString();
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("ma", code);
		data.put("ten", name);
		data.put("gioitinh", gender);
		data.put("namsinh", birthYear);
		data.put("quocgia", quocGia);
		
		if (!validator.validate(data)) {
			JOptionPane.showMessageDialog(this, validator.getMessage());
			return;
		}
		
		Dog dog = new Dog(code, name, gender, Integer.parseInt(birthYear), quocGia);
		
		if (xldl.intsert(dog) > 0) {
			JOptionPane.showMessageDialog(this, "Thêm thành công");
			updateTable();
//			clearForm();
		} else {
			JOptionPane.showMessageDialog(this, "Thêm thất bại");
		}
		
	}

	@Override
	public void handleUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleDelete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleSearch() {
		// TODO Auto-generated method stub
		JDialog dialog = new JDialog(this, "Tìm kiếm", true);
		dialog.setContentPane(new SearchPanel(this.xldl));
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.pack();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
		
	}

	@Override
	public void handleExit() {
		// TODO Auto-generated method stub
		
	}
	
	public void clearForm() {
		txtCode.setText("");
		txtName.setText("");
		txtGender.setText("");
		txtBirthYear.setText("");
		comboBox.setSelectedIndex(0);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
