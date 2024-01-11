package qlsinhvien;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {

	private static QLSV qlsv = new QLSV();
	private static int countCNTT = 0;
	private static int countKT = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DSSV();
//		qlsv.showSV();
		for(Object sv : qlsv.getList()) {
			if(sv.getClass().getSimpleName().equalsIgnoreCase("SVCNTT")) {
				countCNTT++;
			}
			else if (sv.getClass().getSimpleName().equalsIgnoreCase("SVKinhTe")) {
				countKT++;
			}
		}
		
		System.out.println("So luong sinh vien CNTT: " + countCNTT);
		System.out.println("So luong sinh vien KT: " + countKT);
		
	}

	static void DSSV() {

		ArrayList<Object> list = new ArrayList<>();

		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap danh sach 5 sinh vien:");

		for (int i = 1; i <= 2; i++) {
			System.out.println("Nhap sinh vien thu " + i + ":");
			System.out.println("Ban muon them sinh vien lop nao? (CNTT or KT):");
			String lop = sc.nextLine();
			if (lop.equalsIgnoreCase("CNTT")) {
				SVCNTT sv = new SVCNTT();
				System.out.println("Nhap ma: ");
				sv.setMa(sc.nextLine());
				System.out.println("Nhap ten: ");
				sv.setHoTen(sc.nextLine());
				System.out.println("Nhap que quan: ");
				sv.setQueQuan(sc.nextLine());
				System.out.println("Nhap ngay sinh: ");
				sv.setBirthday(sc.nextLine());
				System.out.println("Nhap diem toan: ");
				sv.setToan(Double.parseDouble(sc.nextLine()));
				System.out.println("Nhap dien van: ");
				sv.setVan(Double.parseDouble(sc.nextLine()));
				System.out.println("Nhap diem anh: ");
				sv.setAnh(Double.parseDouble(sc.nextLine()));
				System.out.println("Nhap ky nang(C#, Java, Nodejs): ");
				sv.setKyNangDev(sc.nextLine());
				list.add(sv);
			} else if (lop.equalsIgnoreCase("KT")) {
				SVKinhTe sv = new SVKinhTe();
				System.out.println("Nhap ma: ");
				sv.setMa(sc.nextLine());
				System.out.println("Nhap ten: ");
				sv.setHoTen(sc.nextLine());
				System.out.println("Nhap que quan: ");
				sv.setQueQuan(sc.nextLine());
				System.out.println("Nhap ngay sinh: ");
				sv.setBirthday(sc.nextLine());
				System.out.println("Nhap diem toan: ");
				sv.setToan(Double.parseDouble(sc.nextLine()));
				System.out.println("Nhap dien van: ");
				sv.setVan(Double.parseDouble(sc.nextLine()));
				System.out.println("Nhap diem anh: ");
				sv.setAnh(Double.parseDouble(sc.nextLine()));
				System.out.println("Nhap linh vuc(Kinh te, Tai chinh, Logistic): ");
				sv.setLinhVuc(sc.nextLine());
				list.add(sv);
			} else {
				System.out.println("Khong hop le!");
				i--;
				continue;
			}
		}
		sc.close();
		qlsv.setList(list);
		
	}

}
