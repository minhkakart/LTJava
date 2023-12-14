package oop.bai6;

public class NhanVien {

	private String maNV;
	private String hoTen;
	private String queQuan;
	private double heSoLuong;
	
	public NhanVien() {
		this.maNV = "";
		this.hoTen = "";
		this.queQuan = "";
		this.heSoLuong = 0;
	}
	
	public NhanVien(String maNV, String hoTen, String queQuan, double heSoLuong) {
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.queQuan = queQuan;
		this.heSoLuong = heSoLuong;
	}
	
	public String getMaNV() {
		return this.maNV;
	}
	
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	
	public String getHoTen() {
		return this.hoTen;
	}

	public String getQueQuan() {
		return queQuan;
	}

	public void setQueQuan(String queQuan) {
		this.queQuan = queQuan;
	}

	public double getHeSoLuong() {
		return heSoLuong;
	}

	public void setHeSoLuong(double heSoLuong) {
		this.heSoLuong = heSoLuong;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	
	@Override
	public String toString() {
		return "NhanVien [maNV = " + maNV + ", hoTen = " + hoTen + ", queQuan = " + queQuan + ", heSoLuong = " + heSoLuong
				+ "]";
	}
	
}
