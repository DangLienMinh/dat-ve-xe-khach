package model;

public class VanPhong {
	 public VanPhong(int maVP, String tenVP, String diaChi, String dienThoai,
			 KhuVuc maKV) {
		super();
		MaVP = maVP;
		TenVP = tenVP;
		DiaChi = diaChi;
		DienThoai = dienThoai;
		MaKV = maKV;
	}
	private int MaVP;
	 private String TenVP;
	 private String DiaChi;
	 private String DienThoai;
	 private KhuVuc MaKV; 
	 public VanPhong(){}
	public int getMaVP() {
		return MaVP;
	}
	public void setMaVP(int maVP) {
		MaVP = maVP;
	}
	public String getTenVP() {
		return TenVP;
	}
	public void setTenVP(String tenVP) {
		TenVP = tenVP;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	public String getDienThoai() {
		return DienThoai;
	}
	public void setDienThoai(String dienThoai) {
		DienThoai = dienThoai;
	}
	public KhuVuc getMaKV() {
		return MaKV;
	}
	public void setMaKV(KhuVuc maKV) {
		MaKV = maKV;
	}


}
