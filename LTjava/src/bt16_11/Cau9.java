package bt16_11;

import java.util.Scanner;

public class Cau9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Nhập n
        System.out.print("Nhập vào số nguyên n: ");
        int n = sc.nextInt();
        sc.close();
        // tính tổng 1 + 1/2 - 1/3 + ... + (-1)^(n)/n
        double sum = 1;
        for (int i = 2; i <= n; i++) {
            sum += Math.pow(-1, i) / i;
        }
        System.out.println("Tổng 1 + 1/2 - 1/3 + ... + (-1)^(n)/n = " + sum);

    }
}
