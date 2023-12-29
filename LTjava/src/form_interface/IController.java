package form_interface;

import java.sql.Connection;
import java.util.ArrayList;

public interface IController {
	
	public Connection getConnection();
	
	public ArrayList<?> get();
	
	public int intsert(Object obj);
	
	public int update(Object obj);
	
	public int delete(Object obj);
	
	
}
