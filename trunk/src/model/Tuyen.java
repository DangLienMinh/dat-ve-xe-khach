package model;

public class Tuyen {
	   private int MaTuyen;
	   private String TenTuyen;   
	   private String BenDau;  
	   private String BenCuoi;  
	   private int Gia;  

	   public Tuyen() {}
	   
	   public Tuyen(int MaTuyen,String TenTuyen, String BenDau, String BenCuoi,int Gia) {
		   this.MaTuyen=MaTuyen;
		   this.TenTuyen=TenTuyen;
		   this.BenDau=BenDau;
		   this.BenCuoi=BenCuoi;
		   this.Gia=Gia;
	   }

		public int getMaTuyen() {
			return MaTuyen;
		}
	
		public void setMaTuyen(int maTuyen) {
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
	
		public int getBangGia() {
			return Gia;
		}
	
		public void setBangGia(int gia) {
			Gia = gia;
		}
	
}
