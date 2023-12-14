package oop.bai3;

public abstract class MatHang {
	
	private String maMH;
	private String ten;
	private double donGia;
	
	public MatHang() {
		this.maMH = "";
		this.ten = "";
		this.donGia = 0;
	}
	
	public MatHang(String maMH, String ten, double donGia) {
		this.maMH = maMH;
		this.ten = ten;
		this.donGia = donGia;
	}
	
	abstract void nhap();
	abstract void hienThi();

	public String getMaMH() {
		return maMH;
	}

	public void setMaMH(String maMH) {
		this.maMH = maMH;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	
}
