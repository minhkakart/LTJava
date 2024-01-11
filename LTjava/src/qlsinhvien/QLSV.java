package qlsinhvien;

import java.util.ArrayList;

public class QLSV {
	
	private ArrayList<Object> list;
	
	
	
	public ArrayList<Object> getList() {
		return list;
	}

	public void setList(ArrayList<Object> list) {
		this.list = list;
	}

	public void showSV() {
		for(Object sv : list) {
			System.out.println(sv.toString());
		}
		
	}
	
	public int dem() {
		return 0;
	}

}
