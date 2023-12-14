package bt16_11;

import java.util.Scanner;

public class Cau6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Nhập vào số nguyên a, b, c
        System.out.print("Nhập vào số nguyên a: ");
        int a = sc.nextInt();
        System.out.print("Nhập vào số nguyên b: ");
        int b = sc.nextInt();
        System.out.print("Nhập vào số nguyên c: ");
        int c = sc.nextInt();
        sc.close();
        // solve ax^2+bx+c=0
        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    System.out.println("Phương trình có vô số nghiệm");
                } else {
                    System.out.println("Phương trình vô nghiệm");
                }
            } else {
                System.out.println("Phương trình có nghiệm x = " + (-c / b));
            }
        } else {
            double delta = b * b - 4 * a * c;
            if (delta < 0) {
                System.out.println("Phương trình vô nghiệm");
            } else if (delta == 0) {
                System.out.println("Phương trình có nghiệm kép x = " + (-b / (2 * a)));
            } else {
                System.out.println("Phương trình có 2 nghiệm phân biệt:");
                System.out.println("x1 = " + ((-b + Math.sqrt(delta)) / (2 * a)));
                System.out.println("x2 = " + ((-b - Math.sqrt(delta)) / (2 * a)));
            }
        }
    }
}
