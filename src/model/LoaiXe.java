package model;

public class LoaiXe {
	private int MaLoaiXe;
	private String 	TenLoaiXe;
	private int GiuongNam;
	public LoaiXe(){
	}
	public LoaiXe(int MaLoaiXe,String TenLoaiXe,int GiuongNam){
		this.MaLoaiXe=MaLoaiXe;
		this.TenLoaiXe=TenLoaiXe;
		this.GiuongNam=GiuongNam;
	}
	public int getMaLoaiXe() {
		return MaLoaiXe;
	}
	public void setMaLoaiXe(int maLoaiXe) {
		MaLoaiXe = maLoaiXe;
	}
	public String getTenLoaiXe() {
		return TenLoaiXe;
	}
	public void setTenLoaiXe(String tenLoaiXe) {
		TenLoaiXe = tenLoaiXe;
	}
	public int getGiuongNam() {
		return GiuongNam;
	}
	public void setGiuongNam(int giuongNam) {
		GiuongNam = giuongNam;
	}
}
