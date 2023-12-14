package bt16_11;

import java.util.Scanner;

public class Cau7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Nhập vào số nguyên n
        System.out.print("Nhập vào số nguyên n: ");
        int n = sc.nextInt();
        sc.close();
        // tính tổng 1/2 + 1/4 + ... + 1/2n
        double sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += 1.0 / (2 * i);
        }
        System.out.println("Tổng 1/2 + 1/4 + ... + 1/2n = " + sum);
    }
}
