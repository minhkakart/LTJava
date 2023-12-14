package bt16_11;

import java.util.Scanner;

public class Cau1 {

	public static void main(String[] args) {
		int a, b, c;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.print("Nhập 3 số nguyên dương: ");
			a = sc.nextInt();
			b = sc.nextInt();
			c = sc.nextInt();
		} while (a <= 0 || b <= 0 || c <= 0);
		sc.close();
		if (a+b <= c || a+c <= b || b+c <= a) {
			System.out.println("Không phải 3 cạnh tam giác.");
		}
		else {
			double chuvi = a+b+c;
			double nuachuvi = chuvi/2;
			double dientich = Math.sqrt((nuachuvi*(nuachuvi-a)*(nuachuvi-b)*(nuachuvi-c)));
			System.out.println("Diện tích tam giác: " + Double.toString(dientich));
			System.out.println("Chu vi tam giác: " + Double.toString(chuvi));
			
		}
	}

}
