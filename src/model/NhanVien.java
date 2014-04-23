package model;

import java.util.Date;

public class NhanVien {
	   private String MaNV;
	   private TaiKhoan TenDN; 
	   private String CMND;
	   private String DiaChi;  
	   private String Email;  
	   private String DienThoai;  
	   private Date NgaySinh;  
	   
	   public NhanVien() {}
	   
	   public NhanVien(String MaNV,TaiKhoan TenDN,String CMND,String DiaChi,String Email,String DienThoai,Date NgaySinh) {
		   this.MaNV=MaNV;
		   this.TenDN=TenDN;
		   this.CMND=CMND;
		   this.DiaChi=DiaChi;
		   this.Email=Email;
		   this.DienThoai=DienThoai;
		   this.NgaySinh=NgaySinh;
	   }

		public String getMaNV() {
			return MaNV;
		}
	
		public void setMaNV(String maNV) {
			MaNV = maNV;
		}
	
		public TaiKhoan getTenDN() {
			return TenDN;
		}
	
		public void setTenDN(TaiKhoan tenDN) {
			TenDN = tenDN;
		}
	
		public String getCMND() {
			return CMND;
		}
	
		public void setCMND(String cMND) {
			CMND = cMND;
		}
	
		public String getDiaChi() {
			return DiaChi;
		}
	
		public void setDiaChi(String diaChi) {
			DiaChi = diaChi;
		}
	
		public String getEmail() {
			return Email;
		}
	
		public void setEmail(String email) {
			Email = email;
		}
	
		public String getDienThoai() {
			return DienThoai;
		}
	
		public void setDienThoai(String dienThoai) {
			DienThoai = dienThoai;
		}
	
		public Date getNgaySinh() {
			return NgaySinh;
		}
	
		public void setNgaySinh(Date ngaySinh) {
			NgaySinh = ngaySinh;
		}

	
}

