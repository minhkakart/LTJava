package th1;

import java.util.Scanner;

public class Bai3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String str;
		System.out.print("Nhap chuoi: ");
		str = sc.nextLine();
		sc.close();
		for(int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				System.out.print("Co");
				return;
			}
		}
		System.out.print("Khong");
	}

}
