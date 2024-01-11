package qlsinhvien;

public abstract class SinhVien {

	private String ma;
	private String hoTen;
	private String queQuan;
	private String birthday;
	private double toan;
	private double van;
	private double anh;

	public SinhVien() {
		super();
		// TODO Auto-generated constructor stub
		this.ma = "";
		this.hoTen = "";
		this.queQuan = "";
		this.birthday = null;
		this.toan = 0;
		this.van = 0;
		this.anh = 0;
	}

	public SinhVien(String ma, String hoTen, String queQuan, String birthday, double toan, double van, double anh) {
		super();
		this.ma = ma;
		this.hoTen = hoTen;
		this.queQuan = queQuan;
		this.birthday = birthday;
		this.toan = toan;
		this.van = van;
		this.anh = anh;
	}

	public String getMa() {
		return ma;
	}

	public void setMa(String ma) {
		this.ma = ma;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getQueQuan() {
		return queQuan;
	}

	public void setQueQuan(String queQuan) {
		this.queQuan = queQuan;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public double getToan() {
		return toan;
	}

	public void setToan(double toan) {
		this.toan = toan;
	}

	public double getVan() {
		return van;
	}

	public void setVan(double van) {
		this.van = van;
	}

	public double getAnh() {
		return anh;
	}

	public void setAnh(double anh) {
		this.anh = anh;
	}

	public abstract double DTB();

}
