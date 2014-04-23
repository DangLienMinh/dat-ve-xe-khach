package model;

public class Xe {
	   private int BienSo;
	   private String LoaiXe; 
	   private int SoGhe;   

	   public Xe() {}
	   
	   public Xe(int BienSo,String LoaiXe, int SoGhe) {
		   this.BienSo=BienSo;
		   this.LoaiXe=LoaiXe;
		   this.SoGhe=SoGhe;
	   }

		public int getBienSo() {
			return BienSo;
		}
	
		public void setBienSo(int bienSo) {
			BienSo = bienSo;
		}
	
		public String getLoaiXe() {
			return LoaiXe;
		}
	
		public void setLoaiXe(String loaiXe) {
			LoaiXe = loaiXe;
		}
	
		public int getSoGhe() {
			return SoGhe;
		}
	
		public void setSoGhe(int soGhe) {
			SoGhe = soGhe;
		}
}

