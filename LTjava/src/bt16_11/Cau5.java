package bt16_11;

import java.util.Scanner;

public class Cau5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Nhập vào số nguyên a và b
        System.out.print("Nhập vào số nguyên a: ");
        int a = sc.nextInt();
        System.out.print("Nhập vào số nguyên b: ");
        int b = sc.nextInt();
        sc.close();
        // solve ax+b=0
        if (a == 0) {
            if (b == 0) {
                System.out.println("Phương trình có vô số nghiệm");
            } else {
                System.out.println("Phương trình vô nghiệm");
            }
        } else {
            System.out.println("Phương trình có nghiệm x = " + (-b / a));
        }
    }
}
