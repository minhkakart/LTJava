package oop.bai2;

public class Student {
	private String code;
	private String name;
	private String birthPlace;
	private double point;
	
	public Student() {
		code = "";
		name = "";
		birthPlace = "";
		point = 0;
	}
	
	public Student(String code, String name, String birthPlace, double point) {
		this.code = code;
		this.name = name;
		this.birthPlace = birthPlace;
		this.point = point;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}
	
	public String toString() {
		return "Ma sinh vien: " + code + "\nHo ten: " + name + "\nNoi sinh: " + birthPlace + "\nDiem: " + point;
	}

	public boolean createStudent() {
		System.out.println("Create student");
		System.out.println(this.toString());
		return true;
	}

	public boolean editStudent(String code) {
		System.out.println("Edit student");
		System.out.println(this.toString());
		return true;
	}

	public boolean deleteStudent() {
		System.out.println("Delete student");
		System.out.println(this.toString());
		return true;
	}
	
}
