package model;

public class TaiKhoan {
	   private String TenDN;
	   private String MatKhau; 
	   private PhanQuyen MaPQ;
	   
	   public TaiKhoan() {}
	   
	   public TaiKhoan(String TenDN,String MatKhau,PhanQuyen MaPQ) {
		   this.TenDN=TenDN;
		   this.MatKhau=MatKhau;
		   this.MaPQ=MaPQ;
	   }

		public String getTenDN() {
			return TenDN;
		}
	
		public void setTenDN(String tenDN) {
			TenDN = tenDN;
		}
	
		public String getMatKhau() {
			return MatKhau;
		}
	
		public void setMatKhau(String matKhau) {
			MatKhau = matKhau;
		}
	
		public PhanQuyen getMaPQ() {
			return MaPQ;
		}
	
		public void setMaPQ(PhanQuyen maPQ) {
			MaPQ = maPQ;
		}

		
}

