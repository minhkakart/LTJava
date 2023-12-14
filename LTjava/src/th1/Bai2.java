package th1;

import java.util.Scanner;

public class Bai2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String str;
		char c;
		System.out.print("Nhap chuoi: ");
		str = sc.nextLine();
		System.out.print("Nhap ky tu: ");
		c = sc.next().charAt(0);
		sc.close();
		if (str.indexOf(c) == -1)
			System.out.println("Khong");
		else {
			System.out.println("Co");
		}
	}

}
