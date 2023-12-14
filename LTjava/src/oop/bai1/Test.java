package oop.bai1;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Shape shape = new Triangle(3, 4, 5);
		if(shape.checkShape()) {
			System.out.println("Là tam giác.");
			System.out.print("Diện tích : ");
			System.out.println(shape.getArea());
			System.out.print("Chu vi : ");
			System.out.println(shape.getPerimeter());
		} else {
			System.out.println("Không phải tam giác.");
		}
	}

}
