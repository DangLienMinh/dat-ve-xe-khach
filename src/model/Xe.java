package model;

public class Xe {
	   private String BienSo;
	   private LoaiXe MaLoaiXe; 
	   private int SoGhe;   

	   public Xe() {}
	   
	   public Xe(String BienSo,LoaiXe MaLoaiXe, int SoGhe) {
		   this.BienSo=BienSo;
		   this.MaLoaiXe=MaLoaiXe;
		   this.SoGhe=SoGhe;
	   }

		public String getBienSo() {
			return BienSo;
		}
	
		public void setBienSo(String bienSo) {
			BienSo = bienSo;
		}
	
		public LoaiXe getLoaiXe() {
			return MaLoaiXe;
		}
	
		public void setLoaiXe(LoaiXe loaiXe) {
			MaLoaiXe = loaiXe;
		}
	
		public int getSoGhe() {
			return SoGhe;
		}
	
		public void setSoGhe(int soGhe) {
			SoGhe = soGhe;
		}
}

