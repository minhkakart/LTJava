package exception;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SinhVien sv = new SinhVien();
		while(true) {
			try {
				System.out.print("Nhap ma sinh vien: ");
				sv.setMsv(sc.nextLine());
				System.out.print("Nhap ten sinh vien: ");
				sv.setTen(sc.nextLine());
				System.out.print("Nhap diem sinh vien: ");
				sv.setDiem(Double.parseDouble(sc.nextLine()));

				System.out.println(sv.toString());
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		sc.close();
		
	}

}
