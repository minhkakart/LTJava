package kt1;

public abstract class NhanVien {
	protected String ma;
	protected String hoten;
	protected boolean gt;
	protected double luong;
	
	public NhanVien() {
		ma = "";
		hoten = "";
		gt = true;
		luong = 0;
	}
	
	public NhanVien(String ma, String hoten, boolean gt, double luong) {
		this.ma = ma;
		this.hoten = hoten;
		this.gt = gt;
		this.luong = luong;
	}
	
	public abstract double tinhLuongThuong();

	public String getMa() {
		return ma;
	}

	public void setMa(String ma) {
		this.ma = ma;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getGt() {
		return gt ? "Nam" : "Nữ";
	}
	
	public boolean isMale() {
		return gt;
	}

	public void setGt(boolean gt) {
		this.gt = gt;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}
	
	
	
}
