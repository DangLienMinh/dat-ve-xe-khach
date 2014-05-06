package model;

public class Xe{
	private String BienSo;
	   private LoaiXe MaLoaiXe; 
	   private int SoGhe;   

	   public Xe() {}
	   
	   public Xe(String BienSo,LoaiXe MaLoaiXe, int SoGhe) {
		   this.BienSo=BienSo;
		   this.setMaLoaiXe(MaLoaiXe);
		   this.SoGhe=SoGhe;
	   }

		public String getBienSo() {
			return BienSo;
		}
	
		public void setBienSo(String bienSo) {
			BienSo = bienSo;
		}
	
		
	
		public int getSoGhe() {
			return SoGhe;
		}
	
		public void setSoGhe(int soGhe) {
			SoGhe = soGhe;
		}

		public LoaiXe getMaLoaiXe() {
			return MaLoaiXe;
		}

		public void setMaLoaiXe(LoaiXe maLoaiXe) {
			MaLoaiXe = maLoaiXe;
		}
		
		@Override
		public String toString(){
			return BienSo;
			
		}
}

