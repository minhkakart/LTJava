package qlsinhvien;

public class SVCNTT extends SinhVien {
	private String KyNangDev;

	public SVCNTT() {
		super();
		// TODO Auto-generated constructor stub
		this.KyNangDev = "";
	}

	public SVCNTT(String ma, String hoTen, String queQuan, String birthday, double toan, double van, double anh,
			String KyNangDev) {
		super(ma, hoTen, queQuan, birthday, toan, van, anh);
		// TODO Auto-generated constructor stub
		this.KyNangDev = KyNangDev;
	}

	public String getKyNangDev() {
		return KyNangDev;
	}

	public void setKyNangDev(String KyNangDev) {
		this.KyNangDev = KyNangDev;
	}

	@Override
	public double DTB() {
		// TODO Auto-generated method stub
		return (super.getToan()*2 + super.getVan() + super.getAnh()) / 4;
	}
	
	@Override
	public String toString() {
		return "Sinh vien: " + getHoTen() + " - Khoa: CNTT - Ky nang dev: " + KyNangDev + " - Diem TB: " + DTB();
	}

}
