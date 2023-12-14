package oop.bai2;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = 100;
		int k = 0;
		Student[] students;
		students = new Student[n];
		
		do {
			System.out.println("Nhap thong tin sinh vien thu " + (k + 1) + ":");
			System.out.print("Nhap ma sinh vien: ");
			String code = sc.nextLine();
			System.out.print("Nhap ten sinh vien: ");
			String name = sc.nextLine();
			System.out.print("Nhap diem trung binh: ");
			double point = sc.nextDouble();
			sc.nextLine();
			System.out.print("Nhap dia chi: ");
			String birthPlace = sc.nextLine();

			Student student = new Student(code, name, birthPlace, point);

			insertStudent(students, student, n, k);

			k++;

			System.out.print("Ban co muon nhap tiep khong? (Y/N): ");
			String choice = sc.nextLine();
			if (choice.equalsIgnoreCase("N")) {
				break;
			}
		} while (true);
		sc.close();
		
		System.out.println("Danh sach sinh vien:");
		displayStudent(students, k);
		
		System.out.print("Nhap ten sinh vien can tim: ");
		String name = sc.nextLine();
		searchStudent(students, name, k);
		
		System.out.println("Ket thuc chuong trinh!");
	}
	
	static void insertStudent(Student[] students, Student student, int n, int k) {
		if (k == n) {
			System.out.println("Khong the them sinh vien!");
		} else {
			students[k] = student;
		}
	}
	
	static void displayStudent(Student[] students, int k) {
		for (int i = 0; i < k; i++) {
			System.out.println(students[i].toString());
		}
	}
	
	static void searchStudent(Student[] students, String name, int k) {
		for (int i = 0; i < k; i++) {
			if (students[i].getName().indexOf(name) != -1) {
				System.out.println(students[i].toString());
			}
		}
	}

}
