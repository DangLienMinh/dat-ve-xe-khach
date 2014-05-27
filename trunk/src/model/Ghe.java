package model;

public class Ghe {
	 private String MaGhe;
	 private String TenGhe; 
	 
	 public Ghe(){}
	 
	 public Ghe(String MaGhe,String TenGhe){
		 this.MaGhe=MaGhe;
		 this.TenGhe=TenGhe;
	 }

	public String getMaGhe() {
		return MaGhe;
	}

	public void setMaGhe(String maGhe) {
		MaGhe = maGhe;
	}

	public String getTenGhe() {
		return TenGhe;
	}

	public void setTenGhe(String tenGhe) {
		TenGhe = tenGhe;
	}

	
	@Override
	public String toString(){
		return MaGhe;
		
	}
}
