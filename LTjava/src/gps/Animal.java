package gps;

public abstract class Animal {
	
	private String code;
	private String name;
	private String gender;
	private int birthYear;
	
	public Animal() {
		code = "";
		name = "";
		gender = "";
		birthYear = 0;
	}
	
	public Animal(String code, String name, String gender, int birth) {
		super();
		this.code = code;
		this.name = name;
		this.gender = gender;
		this.birthYear = birth;
	}
	
	public abstract int getRemainingYears();

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getBirth() {
		return birthYear;
	}

	public void setBirth(int birth) {
		this.birthYear = birth;
	}
	
	
	

}
