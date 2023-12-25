package exam2022;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;

import swing_template.ScrollTable;
import swing_template.TableModelEditable;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Rectangle;

public class GUI_insertKH extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSTK;
	private JTextField txtHoTen;
	private JTextField txtGT;
	private JTextField txtSoDu;
	
	private JComboBox<String> cbDiaChi;
	
	private XLKH xlkh = new XLKH();
	private ArrayList<KhachHang> listKH = xlkh.getKH();
	
	private Vector<String> header = new Vector<String>();
	private TableModelEditable tableModel;
	private ScrollTable table;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_insertKH frame = new GUI_insertKH();
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
	public GUI_insertKH() {
		setBounds(new Rectangle(0, 0, 800, 600));
		setPreferredSize(new Dimension(800, 600));
//		setBounds(0,0,805,602);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("So TK");
		lblNewLabel.setBounds(88, 74, 70, 14);
		contentPane.add(lblNewLabel);
		
		txtSTK = new JTextField();
		txtSTK.setBounds(172, 71, 138, 20);
		contentPane.add(txtSTK);
		txtSTK.setColumns(10);
		
		txtHoTen = new JTextField();
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(172, 99, 138, 20);
		contentPane.add(txtHoTen);
		
		JLabel lblHoTen = new JLabel("Ho ten");
		lblHoTen.setBounds(88, 102, 70, 14);
		contentPane.add(lblHoTen);
		
		txtGT = new JTextField();
		txtGT.setColumns(10);
		txtGT.setBounds(172, 130, 138, 20);
		contentPane.add(txtGT);
		
		JLabel lblGt = new JLabel("GT");
		lblGt.setBounds(88, 133, 70, 14);
		contentPane.add(lblGt);
		
		txtSoDu = new JTextField();
		txtSoDu.setColumns(10);
		txtSoDu.setBounds(466, 74, 138, 20);
		contentPane.add(txtSoDu);
		
		JLabel lblSoDu = new JLabel("So du");
		lblSoDu.setBounds(382, 77, 70, 14);
		contentPane.add(lblSoDu);
		
		JLabel lblDiaChi = new JLabel("Dia chi");
		lblDiaChi.setBounds(382, 105, 70, 14);
		contentPane.add(lblDiaChi);
		
		cbDiaChi = new JComboBox<String>();
		cbDiaChi.setBounds(466, 98, 138, 22);
		contentPane.add(cbDiaChi);
		
		cbDiaChi.addItem("Hà Nội");
		cbDiaChi.addItem("Hải Phòng");
		cbDiaChi.addItem("Nam Định");
		
		
		JButton btnNewButton = new JButton("Thêm khách hàng");
		btnNewButton.addActionListener(e -> {
			handleInsert();
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(304, 185, 176, 56);
		contentPane.add(btnNewButton);
		
		JPanel panelTable = new JPanel();
		panelTable.setBounds(10, 261, 764, 289);
		contentPane.add(panelTable);
		panelTable.setLayout(new GridLayout(1, 1, 0, 0));
		
		header.add("So TK");
		header.add("Ho ten");
		header.add("GT");
		header.add("Dia chi");
		header.add("So du");
		header.add("Khuyen mai");
		
		tableModel = new TableModelEditable(header);
		table = new ScrollTable(tableModel);
		panelTable.add(table);
		updateTable();
		
		table.setListener(e -> {
			tableSelectionChanged(e);
		});
		
		setLocationRelativeTo(null);
		
	}
	
	private void updateTable() {
		listKH = xlkh.getKH();
		tableModel.setRowCount(0);

		for (KhachHang kh : listKH) {
			Vector<String> row = new Vector<String>();
			row.add(kh.getSotk());
			row.add(kh.getHoten());
			row.add(kh.getGt());
			row.add(kh.getDiachi());
			row.add(String.valueOf(kh.getSodu()));
			row.add(kh.khuyenMai());

			tableModel.addRow(row);
		}
		
	}
	
	private void handleInsert() {
		String sotk = txtSTK.getText();
		String hoten = txtHoTen.getText();
		String gt = txtGT.getText();
		String diachi = cbDiaChi.getSelectedItem().toString();
		double sodu = Double.parseDouble(txtSoDu.getText());

		KhachHang kh = new KhachHang(sotk, hoten, gt, diachi, sodu);
		int result = xlkh.insertKH(kh);

		if (result > 0) {
			updateTable();
		}
		else {
			System.out.println("Insert failed");
		}
	}

	private void tableSelectionChanged(ListSelectionEvent e) {
		int index = table.getSelectedRow();
        if (index >= 0) {
            txtSTK.setText(tableModel.getValueAt(index, 0).toString());
            txtHoTen.setText(tableModel.getValueAt(index, 1).toString());
            txtGT.setText(tableModel.getValueAt(index, 2).toString());
            cbDiaChi.setSelectedItem(tableModel.getValueAt(index, 3).toString());
            txtSoDu.setText(tableModel.getValueAt(index, 4).toString());
        }
    
	}
}
