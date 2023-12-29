package kt1;

public class BacSi extends NhanVien {
	private String khoa;
	
	public BacSi() {
		super();
	}
	
	public BacSi(String ma, String hoten, boolean gt, String khoa, double luong) {
		super(ma, hoten, gt, luong);
		this.khoa = khoa;
	}
	
	public String getKhoa() {
		return khoa;
	}
	
	public void setKhoa(String khoa) {
		this.khoa = khoa;
	}

	@Override
	public double tinhLuongThuong() {
		// TODO Auto-generated method stub
		return getLuong() * 0.8;
	}

}
