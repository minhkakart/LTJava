package bt16_11;

import java.util.Scanner;

public class Cau4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tử số, mẫu số: ");
        int tu = sc.nextInt();
        int mau = sc.nextInt();
        sc.close();

        // Tối giản phân số
        int ucln = UCLN(tu, mau);
        tu /= ucln;
        mau /= ucln;

        // Xuất kết quả
        System.out.println("Phân số tối giản: " + tu + "/" + mau);
        
    }
    // Hàm tìm ước chung lớn nhất của 2 số nguyên
    public static int UCLN(int a, int b) {
        if(a == 0 || b == 0) return a + b;
        while(a != b) {
            if(a > b) a -= b;
            else b -= a;
        }
        return a;
    }
}
