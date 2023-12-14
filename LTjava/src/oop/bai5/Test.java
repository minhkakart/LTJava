package oop.bai5;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		GiaiThua gt;
		int n;
		do {
			System.out.print("Nhap n: ");
			n = scanner.nextInt();
			gt = new GiaiThua(n);
			if (gt.kiemTra()) {
				break;
			}
			System.out.println("Nhap sai!");
		} while (true);
		scanner.close();
		int giaiThua = gt.tinhGiaiThua();
		System.out.println("Giai thua cua " + n + " la: " + giaiThua);
	}

}
