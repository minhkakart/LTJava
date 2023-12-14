package oop.bai1;

public abstract class Shape {
	protected String color;
	protected boolean fillColor;
	
	public Shape() {
		color = "";
		fillColor = false;
	}
	
	public Shape(String color, boolean fillColor) {
		this.color = color;
		this.fillColor = fillColor;
	}
	
	public abstract double getArea();
	
	public abstract double getPerimeter();
	
	public abstract boolean checkShape();

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isFillColor() {
		return fillColor;
	}

	public void setFillColor(boolean fillColor) {
		this.fillColor = fillColor;
	}
	
	
}
