package model;

public class Xe{
	private String BienSo;
	   private LoaiXe MaLoaiXe; 


	   public Xe() {}
	   
	   public Xe(String BienSo,LoaiXe MaLoaiXe) {
		   this.BienSo=BienSo;
		   this.setMaLoaiXe(MaLoaiXe);
		   
	   }

		public String getBienSo() {
			return BienSo;
		}
	
		public void setBienSo(String bienSo) {
			BienSo = bienSo;
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

