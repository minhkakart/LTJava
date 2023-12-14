package th1;

import java.util.ArrayList;
import java.util.Scanner;

public class Bai4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str;
		ArrayList<Character> chars;
		
		System.out.print("Nhap chuoi: ");
		str = sc.nextLine();
		sc.close();
		
		chars = getChar(str);
		for (int i = 0; i < chars.size(); i++) {
			char c = chars.get(i);
			System.out.println(c + ": " + countChar(str, c));
		}
		
	}

	static ArrayList<Character> getChar(String str) {
		ArrayList<Character> arr = new ArrayList<Character>();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ' ' && !arr.contains(str.charAt(i)))
				arr.add(str.charAt(i));
		}
		return arr;
	}
	
	static int countChar(String str, char c) {
		int count = 0;
		for (int i = 0; i < str.length(); i++)
			if (str.charAt(i) == c)
				count++;
		return count;
	}
}
