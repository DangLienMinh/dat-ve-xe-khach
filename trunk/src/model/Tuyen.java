package model;

public class Tuyen {
	   private String MaTuyen;
	   private String TenTuyen;   
	   private String BenDau;  
	   private String BenCuoi;  
	   private int Gia;  

	   public Tuyen() {}
	   
	   public Tuyen(String MaTuyen,String TenTuyen, String BenDau, String BenCuoi,int Gia) {
		   this.MaTuyen=MaTuyen;
		   this.TenTuyen=TenTuyen;
		   this.BenDau=BenDau;
		   this.BenCuoi=BenCuoi;
		   this.Gia=Gia;
	   }

		public String getMaTuyen() {
			return MaTuyen;
		}
	
		public void setMaTuyen(String maTuyen) {
			MaTuyen = maTuyen;
		}
	
		public String getTenTuyen() {
			return TenTuyen;
		}
	
		public void setTenTuyen(String tenTuyen) {
			TenTuyen = tenTuyen;
		}
	
		public String getBenDau() {
			return BenDau;
		}
	
		public void setBenDau(String benDau) {
			BenDau = benDau;
		}
	
		public String getBenCuoi() {
			return BenCuoi;
		}
	
		public void setBenCuoi(String benCuoi) {
			BenCuoi = benCuoi;
		}
	
		public int getGia() {
			return Gia;
		}
	
		public void setGia(int gia) {
			Gia = gia;
		}
		
		@Override
		public String toString(){
			return MaTuyen;
			
		}
	
}
