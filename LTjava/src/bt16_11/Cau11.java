package bt16_11;

import java.util.Scanner;

public class Cau11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Nhập vào từ bàn phím một số nguyên n (0<n<100) và một mảng gồm n số nguyên
        System.out.print("Nhập vào số nguyên n (0<n<100): ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Nhập vào mảng gồm n số nguyên: ");
        for (int i = 0; i < n; i++) {
            System.out.print("arr[" + i + "] = ");
            arr[i] = sc.nextInt();
        }
        sc.close();
        // Tìm số lớn nhất trong mảng và in vị trí của nó ra màn hình
        int max = arr[0];
        int index = 0;
        for (int i = 1; i < n; i++) {
            if (max < arr[i]) {
                max = arr[i];
                index = i;
            }
        }
        System.out.println("Số lớn nhất trong mảng là: " + max);
        System.out.println("Vị trí của nó là: " + index);
        
        // sắp xếp mảng theo thứ tự giảm dần
        for (int i = 0; i < n - 1; i++) {
            // tìm vị trí phần tử lớn nhất trong đoạn từ i+1 đến n-1
            int maxIndex = i + 1;
            for (int j = i + 2; j < n; j++) {
                if (arr[maxIndex] < arr[j])
                    maxIndex = j;
            }
            // đổi chỗ phần tử lớn nhất với phần tử thứ i
            int temp = arr[maxIndex];
            arr[maxIndex] = arr[i];
            arr[i] = temp;
        }
        // Hiển thị mảng sau khi sắp xếp
        System.out.println("Mảng sau khi sắp xếp là: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
