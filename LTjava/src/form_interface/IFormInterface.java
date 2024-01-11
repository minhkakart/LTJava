/**
 * 
 */
package form_interface;

import javax.swing.event.ListSelectionEvent;

/**
 * 
 */
public interface IFormInterface {
	
	public void updateTable();
	
	public void TableSelectionChanged(ListSelectionEvent e);
	
	public void handleInsert();
	
	public void handleUpdate();
	
	public void handleDelete();
	
	public void handleSearch();
	
	public void handleExit();
	
	public void clear();
	
}
