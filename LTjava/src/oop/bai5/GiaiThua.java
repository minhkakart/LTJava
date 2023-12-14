package oop.bai5;

public class GiaiThua {
	private int n;
	
	public GiaiThua(int n) {
		this.n = n;
	}
	
	public int tinhGiaiThua() {
		int giaiThua = 1;
		for (int i = 1; i <= n; i++) {
			giaiThua *= i;
		}
		return giaiThua;
	}
	
	public boolean kiemTra() {
		if (n <= 0) {
			return false;
		}
		return true;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
	
	
}
