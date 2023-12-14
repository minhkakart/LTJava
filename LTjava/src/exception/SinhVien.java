package exception;

public class SinhVien {
	private String msv;
	private String ten;
	private double diem;
	private String xepLoai;

	public SinhVien() {
		this.msv = "";
		this.ten = "";
		this.diem = 0;
		this.xepLoai = "";
	}

	public SinhVien(String msv, String ten, double diem) {
		this.msv = msv;
		this.ten = ten;
		this.diem = diem;

		if (diem >= 8) {
			this.xepLoai = "Gioi";
		} else if (diem >= 7) {
			this.xepLoai = "Kha";
		} else if (diem >= 5) {
			this.xepLoai = "Trung binh";
		} else {
			this.xepLoai = "Yeu";
		}
	}

	public String getMsv() {
		return msv;
	}

	public void setMsv(String msv) {
		this.msv = msv;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public double getDiem() {
		return diem;
	}

	public void setDiem(double diem) throws Exception {
		this.diem = diem;
		if (diem < 0)
			throw new Exception("Diem phai >= 0");
		else if (diem > 10)
			throw new Exception("Diem phai <= 10");
		else if (diem >= 8) {
			this.xepLoai = "Gioi";
		} else if (diem >= 7) {
			this.xepLoai = "Kha";
		} else if (diem >= 5) {
			this.xepLoai = "Trung binh";
		} else {
			this.xepLoai = "Yeu";
		}
	}
	
	@Override
	public String toString() {
		return this.msv + " - " + this.ten + " - " + this.diem + " - " + this.xepLoai;
	}

//	public void nhap() throws Exception {
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Nhap msv: ");
//		String msv = scanner.nextLine();
//		System.out.println("Nhap ten: ");
//		String ten = scanner.nextLine();
//		System.out.println("Nhap diem: ");
//		try {
//			this.setMsv(msv);
//			this.setTen(ten);
//			double diem = scanner.nextDouble();
//			if(diem < 0 ) throw new Exception("Diem phai >= 0");
//			else if(diem > 10) throw new Exception("Diem phai <= 10");
//			this.setDiem(diem);
//		}
//		catch (Exception e) {
//			throw new Exception("Diem phai la so thuc");
//		} finally {
//			scanner.close();
//		}
//	}
	
}
