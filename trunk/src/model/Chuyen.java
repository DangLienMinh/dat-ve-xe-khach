package model;

public class Chuyen {
	   private String MaChuyen;
	   private String TenChuyen;   
	   private String GioDi;  
	   private String GioDen;  
	   private int SoGheTrong;  
	   private TaiXe MaTX;  
	   private Xe BienSo;
	   private Tuyen MaTuyen;
	   
	   public Chuyen() {}
	   
	   public Chuyen(String MaChuyen,String TenChuyen, String GioDi, String GioDen,int SoGheTrong,TaiXe MaTX,Xe BienSo,Tuyen MaTuyen) {
		   this.MaChuyen=MaChuyen;
		   this.TenChuyen=TenChuyen;
		   this.GioDi=GioDi;
		   this.GioDen=GioDen;
		   this.SoGheTrong=SoGheTrong;
		   this.MaTX=MaTX;
		   this.BienSo=BienSo;
		   this.MaTuyen=MaTuyen;
	   }

		public String getMaChuyen() {
			return MaChuyen;
		}
	
		public void setMaChuyen(String maChuyen) {
			MaChuyen = maChuyen;
		}
	
		public String getTenChuyen() {
			return TenChuyen;
		}
	
		public void setTenChuyen(String tenChuyen) {
			TenChuyen = tenChuyen;
		}
	
		public String getGioDi() {
			return GioDi;
		}
	
		public void setGioDi(String gioDi) {
			GioDi = gioDi;
		}
	
		public String getGioDen() {
			return GioDen;
		}
	
		public void setGioDen(String gioDen) {
			GioDen = gioDen;
		}
	
		public int getSoGheTrong() {
			return SoGheTrong;
		}
	
		public void setSoGheTrong(int soGheTrong) {
			SoGheTrong = soGheTrong;
		}
	
		public TaiXe getMaTX() {
			return MaTX;
		}
	
		public void setMaTX(TaiXe maTX) {
			MaTX = maTX;
		}
	
		public Xe getBienSo() {
			return BienSo;
		}
	
		public void setBienSo(Xe bienSo) {
			BienSo = bienSo;
		}
	
		public Tuyen getMaTuyen() {
			return MaTuyen;
		}
	
		public void setMaTuyen(Tuyen maTuyen) {
			MaTuyen = maTuyen;
		}

}
