package bt16_11;

import java.util.Scanner;

public class Cau10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Nhập vào số nguyên n
        System.out.print("Nhập vào số nguyên n: ");
        int n = sc.nextInt();
        // nhập vào mảng n số nguyên
        int[] arr = new int[n];
        System.out.println("Nhập vào mảng n số nguyên: ");
        for (int i = 0; i < n; i++) {
            System.out.print("arr[" + i + "] = ");
            arr[i] = sc.nextInt();
        }
        sc.close();
        // Hiển thị mảng vừa nhập
        System.out.println("Mảng vừa nhập là: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        // Tìm số lớn nhất trong mảng và hiển thị vị trí của nó
        int max = arr[0];
        int indexMax = 0;
        for (int i = 1; i < n; i++) {
            if (max < arr[i]) {
                max = arr[i];
                indexMax = i;
            }
        }
        System.out.println("\nSố lớn nhất trong mảng là: " + max + " tại vị trí " + indexMax);

        // sắp xếp mảng theo thứ tự tăng dần
        for (int i = 0; i < n - 1; i++) {
            // tìm vị trí phần tử nhỏ nhất trong đoạn từ i+1 đến n-1
            int min = i + 1;
            for (int j = i + 2; j < n; j++) {
                if (arr[min] > arr[j])
                    min = j;
            }
            // đổi chỗ phần tử nhỏ nhất với phần tử thứ i
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
        // Hiển thị mảng sau khi sắp xếp
        System.out.println("Mảng sau khi sắp xếp là: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        
    }
}
