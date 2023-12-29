package swing_template;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;

public final class ScrollTable extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5843682668123097620L;

	private JTable table;
	private TableModelEditable model;

	private final int DEFAULT_WIDTH = 480;
	private final int DEFAULT_HEIGHT = 200;

	public ScrollTable() {
		super();
		this.model = new TableModelEditable();
		initialize();
	}

	public ScrollTable(TableModelEditable model) {
		super();
		this.model = model;
		initialize();
	}

	private void initialize() {
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		setViewportView(table);
	}

	public void setDimention(Dimension d) {
		table.setPreferredScrollableViewportSize(d);
	}

	public void setModel(TableModelEditable model) {
		this.model = model;
		table.setModel(model);
	}

	public void setDataFromResultSet(ResultSet rs) throws SQLException {
		int col = this.model.getColumnCount();
		this.model.setRowCount(0);
		while (rs.next()) {
			Vector<String> row = new Vector<String>();
			for (int i = 1; i <= col; i++) {
				row.add(rs.getString(i));
			}
			this.model.addRow(row);
		}
	}

	public void setListener(ListSelectionListener e) {
		table.getSelectionModel().addListSelectionListener(e);
	}

	public int getSelectedRow() {
		return this.table.getSelectedRow();
	}

	public int getSelectedColumn() {
		return this.table.getSelectedColumn();
	}

	public Object getValueAt(int row, int column) {
		return this.table.getValueAt(row, column);
	}
	
	public void setSize(Dimension d) {
		this.table.setPreferredScrollableViewportSize(d);
	}
	
	public JTable getTable() {
		return this.table;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.table.getRowCount();
	}

	public void clearSelection() {
		// TODO Auto-generated method stub
		this.table.clearSelection();
	}

}
