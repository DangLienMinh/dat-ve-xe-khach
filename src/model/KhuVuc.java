package model;

public class KhuVuc {
	private int MaKV;
	private String TenKV;
	public KhuVuc(){}
	public KhuVuc(int MaKV,String TenKV){
		this.MaKV=MaKV;
		this.TenKV=TenKV;
	}
	public int getMaKV() {
		return MaKV;
	}
	public void setMaKV(int maKV) {
		MaKV = maKV;
	}
	public String getTenKV() {
		return TenKV;
	}
	public void setTenKV(String tenKV) {
		TenKV = tenKV;
	}
	@Override
	public String toString(){
		return Integer.toString(MaKV);
		
	}
}
