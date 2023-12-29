package gps;

import java.util.Date;

public class Dog extends Animal {
	
	private String quocGia;
	
	public Dog() {
		super();
		quocGia = "";
	}
	
	

	public Dog(String code, String name, String gender, int birth, String quocGia) {
		super(code, name, gender, birth);
		// TODO Auto-generated constructor stub
		this.quocGia = quocGia;
	}



	public String getQuocGia() {
		return quocGia;
	}



	public void setQuocGia(String quocGia) {
		this.quocGia = quocGia;
	}



	@Override
	public int getRemainingYears() {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		int year = new Date().getYear() + 1900;
		
		return 10 - ( year - getBirth());
	}
	
}
