package oop.bai1;

public class Triangle extends Shape {
	
	private double a;
	private double b;
	private double c;
	
	public Triangle() {
		super();
		a = 0;
		b = 0;
		c = 0;
	}
	
	public Triangle(double a, double b, double c, String color, boolean fillColor) {
		super(color, fillColor);
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public Triangle(double a, double b, double c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return Math.sqrt((a + b + c) * (a + b - c) * (a + c - b) * (b + c - a)) / 4;
	}

	@Override
	public double getPerimeter() {
		// TODO Auto-generated method stub
		return a + b + c;
	}

	@Override
	public boolean checkShape() {
		// TODO Auto-generated method stub
		if (a + b > c && a + c > b && b + c > a) {
			return true;
//			System.out.println("Day la tam giac");
		} else {
			return false;
//			System.out.println("Day khong phai tam giac");
		}
	}

}
