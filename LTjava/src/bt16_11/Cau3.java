package bt16_11;

import java.util.Scanner;

public class Cau3 {

	public static void main(String[] args) {
		double a, b;
		String pheptoan;
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập a, b: ");
		a = sc.nextDouble();
		b = sc.nextDouble();
		System.out.println("Nhập phép toán: ");
		pheptoan = sc.next();
		sc.close();
		switch (pheptoan.charAt(0)) {
		case '+':
			System.out.println("a + b = " + Double.toString(a + b));
			break;
		case '-':
			System.out.println("a - b = " + Double.toString(a - b));
			break;
		case '*':
			System.out.println("a * b = " + Double.toString(a * b));
			break;
		case '/':
			if(b != 0) System.out.println("a / b = " + Double.toString(a / b));
			else System.out.println("Lỗi chia cho 0!");
			break;

		default:
			System.out.println("Lỗi phép toán!");
			break;
		}
	}

}
