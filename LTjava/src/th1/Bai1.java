package th1;

import java.util.Scanner;

public class Bai1 {
	public static void main(String[] args) {
		int n;
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap n: ");
		n = sc.nextInt();
		int[] arr = new int[n];
		System.out.println("Nhap mang: ");
		for (int i = 0; i < n; i++) {
			System.out.print("arr[" + i + "] = ");
			arr[i] = sc.nextInt();
		}
		sc.close();
		
		System.out.print("Cac so nguyen to trong mang: ");
		for (int i = 0; i < n; i++)
			if (isPrime(arr[i]))
				System.out.print(arr[i] + " ");
		System.out.println();
		
		int max = maxVal(arr);
		System.out.println("So lon nhat trong mang: " + max);
		
		sortArr(arr);
		System.out.print("Mảng sau khi sap xep: ");
		for (int i = 0; i < n; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	static void sortArr(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++)
			for (int j = i; j < arr.length; j++)
				if (arr[i] > arr[j]) {
					int tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
	}

	static int maxVal(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++)
			if (arr[i] > max)
				max = arr[i];
		return max;
	}

	static boolean isPrime(int n) {
		if (n < 2)
			return false;
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
}
