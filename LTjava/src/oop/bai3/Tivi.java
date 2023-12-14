package oop.bai3;

import java.util.Scanner;

public class Tivi extends MatHang {
	
	private String loaiTV;
	private double manHinh;
	
	
	public Tivi() {
		super();
		this.loaiTV = "";
		this.manHinh = 0;
	}
	
	public Tivi(String maMH, String ten, double donGia, String loaiTV, double manHinh) {
		super(maMH, ten, donGia);
		this.loaiTV = loaiTV;
		this.manHinh = manHinh;
	}

	public String getLoaiTV() {
		return loaiTV;
	}

	public void setLoaiTV(String loaiTV) {
		this.loaiTV = loaiTV;
	}

	public double getManHinh() {
		return manHinh;
	}

	public void setManHinh(double manHinh) {
		this.manHinh = manHinh;
	}

	@Override
	void nhap() {
		Scanner input = new Scanner(System.in);
		System.out.print("Nhap ma mat hang: ");
		setMaMH(input.nextLine());
		System.out.print("Nhap ten mat hang: ");
		setTen(input.nextLine());
		System.out.print("Nhap don gia: ");
		setDonGia(input.nextDouble());
		input.nextLine();
		System.out.print("Nhap loai tivi: ");
		setLoaiTV(input.nextLine());
		System.out.println("Nhap do lon man hinh: ");
		setManHinh(input.nextDouble());
		input.nextLine();
		input.close();
	}

	@Override
	void hienThi() {
		StringBuilder str = new StringBuilder();
		str.append("Mã MH: ")
			.append(this.getMaMH())
			.append(" Tên: ")
			.append(this.getTen())
			.append(" Đơn giá: ")
			.append(this.getDonGia())
			.append(" Loại: ")
			.append(this.getLoaiTV())
			.append(" Độ lớn MH: ")
			.append(this.getManHinh());
		System.out.println(str);
		
	}

}
