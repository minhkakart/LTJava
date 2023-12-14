package bt16_11;

import java.util.Scanner;

public class Cau2 {

	public static void main(String[] args) {
		int n;
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập n: ");
		n = sc.nextInt();
		sc.close();
		if(n <= 0) {
			System.out.println("n < 0!");
		}
		else {
			System.out.println("n! = " + Integer.toString(giaiThua(n)));
		}
	}
	static int giaiThua(int n) {
		int result = 1;
		for(int i = 2; i <= n; i++) {
			result *= i;
		}
		return result;
	}
}
