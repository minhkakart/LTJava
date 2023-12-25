package exam2022;

public class KhachHang extends Person {
	
	private String diachi;
	private double sodu;
	
	public KhachHang() {
		super();
		this.diachi = "";
		this.sodu = 0;
	}
	
	public KhachHang(String sotk, String hoten, String gt, String diachi, double sodu) {
		super(sotk, hoten, gt);
		this.diachi = diachi;
		this.sodu = sodu;
	}

	@Override
	public String khuyenMai() {
	    return getGt().equals("Nam") ? "" : "Khuyến mại";
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public double getSodu() {
		return sodu;
	}

	public void setSodu(double sodu) {
		this.sodu = sodu;
	}
	
	

}
