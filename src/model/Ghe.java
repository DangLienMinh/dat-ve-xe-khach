package model;

public class Ghe {
	 private int MaGhe;
	 private String TenGhe; 
	 private LoaiXe MaLoaiXe;
	 
	 public Ghe(){}
	 
	 public Ghe(int MaGhe,String TenGhe){
		 this.MaGhe=MaGhe;
		 this.TenGhe=TenGhe;
	 }

	public int getMaGhe() {
		return MaGhe;
	}

	public void setMaGhe(int maGhe) {
		MaGhe = maGhe;
	}

	public String getTenGhe() {
		return TenGhe;
	}

	public void setTenGhe(String tenGhe) {
		TenGhe = tenGhe;
	}

	public LoaiXe getMaLoaiXe() {
		return MaLoaiXe;
	}

	public void setMaLoaiXe(LoaiXe maLoaiXe) {
		MaLoaiXe = maLoaiXe;
	}
}
