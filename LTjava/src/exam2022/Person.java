package exam2022;

public abstract class Person {
	private String sotk;
	private String hoten;
	private String gt;
	
	public Person() {
		this.sotk = "";
		this.hoten = "";
		this.gt = "";
	}
	
	public Person(String sotk, String hoten, String gt) {
		this.sotk = sotk;
		this.hoten = hoten;
		this.gt = gt;
	}
	
	public abstract String khuyenMai();

	public String getSotk() {
		return sotk;
	}

	public void setSotk(String sotk) {
		this.sotk = sotk;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getGt() {
		return gt;
	}

	public void setGt(String gt) {
		this.gt = gt;
	}
	
	
	
}
