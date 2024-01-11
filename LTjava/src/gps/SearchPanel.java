package gps;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;

import form_interface.IFormInterface;
import swing_template.ScrollTable;
import swing_template.TableModelEditable;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchPanel extends JPanel implements IFormInterface {

	private static final long serialVersionUID = 1L;
	private JTextField txtSearch;
	
	private Vector<String> columnNames = new Vector<String>();
	private TableModelEditable model;
	private ScrollTable scrollTable;
	
	private XLDL xldl;
	
	private ArrayList<Dog> list;

	/**
	 * Create the panel.
	 */
	public SearchPanel(XLDL xldl) {
		setPreferredSize(new Dimension(600, 420));
		setLayout(null);
		this.xldl = xldl;
		
		JLabel lblNewLabel = new JLabel("Nhập tên hoặc mã");
		lblNewLabel.setBounds(84, 51, 120, 14);
		add(lblNewLabel);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(214, 48, 290, 20);
		add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleSearch();
				updateTable();
			}
		});
		btnSearch.setBounds(240, 79, 89, 23);
		add(btnSearch);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 112, 580, 297);
		add(panel);
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

	}

	@Override
	public void updateTable() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
		
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
		
//		txtSearch.setText("");
		
	}

	@Override
	public void TableSelectionChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleInsert() {
		// TODO Auto-generated method stub
		
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
		
		String search = txtSearch.getText();
		if (search.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên hoặc mã");
			return;
		}
		
		list = xldl.searchDog(search);
		
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
