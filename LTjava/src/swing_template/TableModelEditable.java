package swing_template;

import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

public class TableModelEditable extends DefaultTableModel {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1654102606913859810L;
	
	private boolean editable = false;
	
	private static <E> Vector<E> newVector(int size) {
        Vector<E> v = new Vector<>(size);
        v.setSize(size);
        return v;
    }
	
	public TableModelEditable(Vector<?> columnNames, int rowCount) {
        super.setDataVector(newVector(rowCount), columnNames);
    }
	
	public TableModelEditable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TableModelEditable(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		// TODO Auto-generated constructor stub
	}

	public TableModelEditable(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		// TODO Auto-generated constructor stub
	}

	public TableModelEditable(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("rawtypes")
	public TableModelEditable(Vector<? extends Vector> data, Vector<?> columnNames) {
		super(data, columnNames);
		// TODO Auto-generated constructor stub
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
	public void setDataVector(Object[][] dataVector, Object[] columnIdentifiers) {
		// TODO Auto-generated method stub
		super.setDataVector(dataVector, columnIdentifiers);
	}

	@Override
	public void newDataAvailable(TableModelEvent event) {
		// TODO Auto-generated method stub
		super.newDataAvailable(event);
	}

	@Override
	public void newRowsAdded(TableModelEvent e) {
		// TODO Auto-generated method stub
		super.newRowsAdded(e);
	}

	@Override
	public void rowsRemoved(TableModelEvent event) {
		// TODO Auto-generated method stub
		super.rowsRemoved(event);
	}

	@Override
	public void setNumRows(int rowCount) {
		// TODO Auto-generated method stub
		super.setNumRows(rowCount);
	}

	@Override
	public void setRowCount(int rowCount) {
		// TODO Auto-generated method stub
		super.setRowCount(rowCount);
	}

	@Override
	public void addRow(Object[] rowData) {
		// TODO Auto-generated method stub
		super.addRow(rowData);
	}

	@Override
	public void insertRow(int row, Object[] rowData) {
		// TODO Auto-generated method stub
		super.insertRow(row, rowData);
	}

	@Override
	public void moveRow(int start, int end, int to) {
		// TODO Auto-generated method stub
		super.moveRow(start, end, to);
	}

	@Override
	public void removeRow(int row) {
		// TODO Auto-generated method stub
		super.removeRow(row);
	}

	@Override
	public void setColumnIdentifiers(Object[] newIdentifiers) {
		// TODO Auto-generated method stub
		super.setColumnIdentifiers(newIdentifiers);
	}

	@Override
	public void setColumnCount(int columnCount) {
		// TODO Auto-generated method stub
		super.setColumnCount(columnCount);
	}

	@Override
	public void addColumn(Object columnName) {
		// TODO Auto-generated method stub
		super.addColumn(columnName);
	}

	@Override
	public void addColumn(Object columnName, Object[] columnData) {
		// TODO Auto-generated method stub
		super.addColumn(columnName, columnData);
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return super.getRowCount();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return super.getColumnCount();
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return super.getColumnName(column);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return editable;
	}

	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return super.getValueAt(row, column);
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, row, column);
	}
	
	

}
