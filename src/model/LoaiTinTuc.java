package model;

public class LoaiTinTuc {
	 private String MaLTT;
	 private String TenLTT; 
	 
	 public LoaiTinTuc(){}
	 
	 public LoaiTinTuc(String MaLTT,String TenLTT){
		 this.MaLTT=MaLTT;
		 this.TenLTT=TenLTT;
	 }

	public String getMaLTT() {
		return MaLTT;
	}

	public void setMaLTT(String maLTT) {
		MaLTT = maLTT;
	}

	public String getTenLTT() {
		return TenLTT;
	}

	public void setTenLTT(String tenLTT) {
		TenLTT = tenLTT;
	}
}
