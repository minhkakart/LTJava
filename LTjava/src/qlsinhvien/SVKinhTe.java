package qlsinhvien;

public class SVKinhTe extends SinhVien {
	
	private String LinhVuc;

	public SVKinhTe() {
		super();
		// TODO Auto-generated constructor stub
		this.LinhVuc = "";
	}

	public SVKinhTe(String ma, String hoTen, String queQuan, String birthday, double toan, double van, double anh, String LinhVuc) {
		super(ma, hoTen, queQuan, birthday, toan, van, anh);
		// TODO Auto-generated constructor stub
		this.LinhVuc = LinhVuc;
	}

	public String getLinhVuc() {
		return LinhVuc;
	}

	public void setLinhVuc(String LinhVuc) {
		this.LinhVuc = LinhVuc;
	}

	@Override
	public double DTB() {
		// TODO Auto-generated method stub
		return (super.getToan() + super.getVan() + super.getAnh()) / 3;
	}
	
	@Override
	public String toString() {
		return "Sinh vien: " + getHoTen() + " - Khoa: Kinh te - Linh vuc: " + LinhVuc + " - Diem TB: " + DTB();
	}

}
