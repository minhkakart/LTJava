package lab05bai2;

import java.awt.Font;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;

import connectMySQL.ConnectSQL;
import swing_template.ScrollTable;
import swing_template.TableModelEditable;
import validator.Validator;

public class LopHoc extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel mainPanel;
	
	JLabel lbl;
	JLabel lblMaLop;
	JLabel lblTenLop;
	JLabel lblGVCN;
	
	JTextField txtMaLop;
	JTextField txtTenLop;
	JTextField txtGVCN;
	
	JButton btnFirst;
	JButton btnPrevious;
	JLabel lblPage;
	JButton btnNext;
	JButton btnLast;
	JButton btnAdd;
	JButton btnEdit;
	JButton btnDelete;
	JButton btnSave;
	
	Vector<String> header;
	ScrollTable scrollTable;
	
	JButton btnCurrentList;
	String currentMSLop;
	
	ConnectSQL connectSQL;
	
	Validator validator;
	
	boolean isAdd = false;
	boolean isEdit = false;
	boolean isCancel = false;
	
	public LopHoc() {
		initComponent();
		
	}
	
	private void initComponent() {
		
		connectSQL = new ConnectSQL("qlsv", "root", "");
		
		validator = new Validator();
		validator.addRule("mslop", "required");
		validator.addRule("tenlop", "required");
		validator.addRule("gvcn", "required");
		
		mainPanel = (JPanel) this.getContentPane();
		mainPanel.setLayout(null);
		
		lbl = new JLabel("Thông tin lớp học");
		lbl.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbl.setHorizontalAlignment(JLabel.CENTER);
		lbl.setBounds(0, 10, 800, 30);
		mainPanel.add(lbl);
		
		lblMaLop = new JLabel("Mã lớp:");
		lblMaLop.setBounds(100, 100, 100, 30);
		mainPanel.add(lblMaLop);
		
		lblTenLop = new JLabel("Tên lớp:");
		lblTenLop.setBounds(100, 150, 100, 30);
		mainPanel.add(lblTenLop);
		
		lblGVCN = new JLabel("Giáo viên CN:");
		lblGVCN.setBounds(100, 200, 100, 30);
		mainPanel.add(lblGVCN);
		
		txtMaLop = new JTextField();
		txtMaLop.setBounds(200, 100, 500, 30);
		mainPanel.add(txtMaLop);
		
		txtTenLop = new JTextField();
		txtTenLop.setBounds(200, 150, 500, 30);
		mainPanel.add(txtTenLop);
		
		txtGVCN = new JTextField();
		txtGVCN.setBounds(200, 200, 500, 30);
		mainPanel.add(txtGVCN);
		
		btnFirst = new JButton("<<");
		btnFirst.setBounds(100, 250, 50, 30);
		mainPanel.add(btnFirst);
		
		btnPrevious = new JButton("<");
		btnPrevious.setBounds(150, 250, 50, 30);
		mainPanel.add(btnPrevious);
		
		lblPage = new JLabel("0/0");
		lblPage.setHorizontalAlignment(JLabel.CENTER);
		lblPage.setBounds(200, 250, 50, 30);
		mainPanel.add(lblPage);
		
		btnNext = new JButton(">");
		btnNext.setBounds(250, 250, 50, 30);
		mainPanel.add(btnNext);
		
		btnLast = new JButton(">>");
		btnLast.setBounds(300, 250, 50, 30);
		mainPanel.add(btnLast);
		
		btnAdd = new JButton("Thêm");
		btnAdd.setBounds(390, 250, 70, 30);
		mainPanel.add(btnAdd);
		
		btnEdit = new JButton("Sửa");
		btnEdit.setBounds(470, 250, 70, 30);
		mainPanel.add(btnEdit);
		
		btnDelete = new JButton("Xóa");
		btnDelete.setBounds(550, 250, 70, 30);
		mainPanel.add(btnDelete);
		
		btnSave = new JButton("Lưu");
		btnSave.setBounds(630, 250, 70, 30);
		mainPanel.add(btnSave);
		
		header = new Vector<String>();
		header.add("Mã lớp");
		header.add("Tên lớp");
		header.add("Giáo viên CN");
		
		scrollTable = new ScrollTable(new TableModelEditable(header, 0));
		scrollTable.setBounds(100, 300, 600, 200);
		mainPanel.add(scrollTable);
		loadTable();
		
		setEnable(false);
		
		btnCurrentList = new JButton("Xem danh sách sinh viên lớp hiện tại");
		btnCurrentList.setBounds(400, 510, 300, 30);
		mainPanel.add(btnCurrentList);
		
		btnSave.setEnabled(isAdd && isEdit);
		
		btnAdd.addActionListener(e -> {
			if (isCancel) {
				setEnable(false);
				clear();
				cancel();
				btnAdd.setText("Thêm");
				btnEdit.setEnabled(true);
				isCancel = false;
			} else {
				setEnable(true);
				clear();
				isAdd = true;
				btnAdd.setText("Hủy");
				btnEdit.setEnabled(false);
				isCancel = true;
				btnSave.setEnabled(true);
			}
			
		});
		
		btnEdit.addActionListener(e -> {
			Map<String, String> data = new HashMap<String, String>();
			data.put("mslop", txtMaLop.getText());
			if (!validator.validate(data)) {
				JOptionPane.showMessageDialog(this, validator.getMessage());
				return;
			}
			
			if (isCancel) {
				clear();
				cancel();
				btnEdit.setText("Sửa");
				btnAdd.setEnabled(true);
				isCancel = false;
			} else {
				setEnable(true);
				txtMaLop.setEnabled(false);
				txtTenLop.requestFocus();
				isEdit = true;
				btnEdit.setText("Hủy");
				btnAdd.setEnabled(false);
				isCancel = true;
				btnSave.setEnabled(true);
			}
		});
		
		btnDelete.addActionListener(e -> {
			delete();
		});
		
		btnSave.addActionListener(e -> {
			save();
		});
		
		scrollTable.setListener(e -> {
			tableClick(e);
		});
		
		btnCurrentList.addActionListener(e -> {
//			new SinhVien(currentMSLop).setVisible(true);
			this.setVisible(false);
			JDialog dssv = new JDialog(this, "Sinh viên", true);
			dssv.setContentPane(new SinhVien(currentMSLop));
			dssv.pack();
			dssv.setResizable(false);
			dssv.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dssv.setLocationRelativeTo(this);
			dssv.setVisible(true);
			this.setVisible(true);
			
		});
		
		setTitle("Lớp học");
		setSize(850, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	void loadTable() {
		try {
			scrollTable.setDataFromResultSet(connectSQL.executeQuery("SELECT * FROM lophoc"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void add() {
		String mslop = txtMaLop.getText();
		String tenlop = txtTenLop.getText();
		String gvcn = txtGVCN.getText();
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("mslop", mslop);
		data.put("tenlop", tenlop);
		data.put("gvcn", gvcn);
		
		if (validator.validate(data)) {
			connectSQL
					.executeUpdate("INSERT INTO lophoc VALUES ('" + mslop + "', '" + tenlop + "', '" + gvcn + "')");
			loadTable();
		} else {
			JOptionPane.showMessageDialog(this, validator.getMessage());
		}
		
		System.out.println("add");
	}
	
	void edit() {
		System.out.println("edit");
	}
	
	void delete() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("mslop", txtMaLop.getText());
		if (!validator.validate(data)) {
			JOptionPane.showMessageDialog(this, validator.getMessage());
			return;
		}
		int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			String mslop = txtMaLop.getText();
			connectSQL.executeUpdate("DELETE FROM lophoc WHERE mslop = '" + mslop + "'");
			loadTable();
		}
	}
	
	void save() {
		if(isAdd) {
			add();
			clear();
			cancel();
			setEnable(false);
			return;
		}
		if (isEdit) {
			edit();
			clear();
			cancel();
			setEnable(false);
			return;
		}

	}
	
	void cancel() {
		isCancel = false;
		isAdd = false;
		isEdit = false;
		btnAdd.setText("Thêm");
		btnEdit.setText("Sửa");
		btnAdd.setEnabled(true);
		btnEdit.setEnabled(true);
		btnSave.setEnabled(false);
	}
	
	void clear() {
		txtMaLop.setText("");
		txtTenLop.setText("");
		txtGVCN.setText("");
		
		txtMaLop.requestFocus();
    }
	
	void setEnable(boolean isEnable) {
		txtMaLop.setEnabled(isEnable);
		txtTenLop.setEnabled(isEnable);
		txtGVCN.setEnabled(isEnable);
	}
	
	void tableClick(ListSelectionEvent e) {
		int row = scrollTable.getSelectedRow();
		if(!e.getValueIsAdjusting() && row != -1) {
			currentMSLop = scrollTable.getValueAt(row, 0).toString();
			lblPage.setText((row + 1) + "/" + scrollTable.getRowCount());
			txtMaLop.setText(scrollTable.getValueAt(row, 0).toString());
			txtTenLop.setText(scrollTable.getValueAt(row, 1).toString());
			txtGVCN.setText(scrollTable.getValueAt(row, 2).toString());
		}
		
	}

}
