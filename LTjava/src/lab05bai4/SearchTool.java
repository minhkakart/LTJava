package lab05bai4;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import connectMySQL.ConnectSQL;
import querry_builder.QuerryBuilder;
import swing_template.ScrollTable;
import swing_template.TableModelEditable;
import validator.Validator;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchTool extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtSearch;
	
	private ConnectSQL conn;
	
	private Vector<String> columnName = new Vector<String>();
	private ScrollTable scrollTable;
	
	private Validator validator = new Validator();

	/**
	 * Create the panel.
	 */
	public SearchTool(ConnectSQL conn) {
		setLayout(null);
		setPreferredSize(new java.awt.Dimension(620, 700));
		
		this.conn = conn;
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0), 2), "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 600, 140);
		
		add(panel);
		panel.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(206, 62, 217, 20);
		panel.add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nhập hoặc chọn mã NXB:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(48, 65, 148, 14);
		panel.add(lblNewLabel);
		
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnSearch.setBounds(451, 53, 89, 38);
		panel.add(btnSearch);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 162, 600, 527);
		add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		columnName.add("Mã Sách");
		columnName.add("Tên Sách");
		columnName.add("Nhà Xuất Bản");
		scrollTable = new ScrollTable(new TableModelEditable(columnName));
		panel_1.add(scrollTable);

		validator.addRule("search", "required");
		
	}
	
	public void updateTable(ResultSet rs) {
		try {
			scrollTable.setDataFromResultSet(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void search() {
		String search = txtSearch.getText();
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("search", search);
		
		if(!validator.validate(data)) {
			JOptionPane.showMessageDialog(null, validator.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String query = new QuerryBuilder().select("*").from("tblbook").where("publishercode", "like", search).getQuerry();
		
		updateTable(conn.executeQuery(query));
		
	}
}
