package oop.bai6;

import java.util.Scanner;

public class DLNhanVien {

	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = 100;
		int k = 0;
		NhanVien[] dsNV = new NhanVien[n];
		
		do {
			nhapNV(dsNV, k);
			k++;
			System.out.println("Ban co muon nhap nua khong? (y/n)");
			String answer = scanner.nextLine();
			if (answer.equalsIgnoreCase("n")) {
				break;
			}
		} while (k < n);
		
		hienThiNV(dsNV, k);
		
		timNV(dsNV, k);

		scanner.close();
	}

	static void nhapNV(NhanVien[] dsNV, int k) {
		System.out.println("Nhap ma nhan vien: ");
		String maNV = scanner.nextLine();
		System.out.println("Nhap ho ten nhan vien: ");
		String hoTen = scanner.nextLine();
		System.out.println("Nhap que quan: ");
		String queQuan = scanner.nextLine();
		System.out.println("Nhap he so luong: ");
		double heSoLuong = scanner.nextDouble();
		scanner.nextLine();
		dsNV[k] = new NhanVien(maNV, hoTen, queQuan, heSoLuong);

	}
	
	static void hienThiNV(NhanVien[] dsNV, int k) {
		for (int i = 0; i < k; i++) {
			System.out.println(dsNV[i].toString());
		}
	}
	
	static void timNV(NhanVien[] dsNV, int k) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nhap ten nhan vien can tim: ");
		String ten = scanner.nextLine();
		for (int i = 0; i < k; i++) {
			if (dsNV[i].getHoTen().indexOf(ten) != -1) {
				System.out.println("Nhan vien tim duoc la: ");
				System.out.println(dsNV[i].toString());
			}
		}
		scanner.close();
	}

}
