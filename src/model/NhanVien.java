package model;

import java.util.Date;

public class NhanVien {
	   private String MaNV;
	   private String Ho;
	   private String Ten;
	   private String CMND;
	   private String DiaChi;  
	   private String Email;  
	   private String DienThoai;  
	   private Date NgaySinh;  
	   private String MatKhau; 
	   private PhanQuyen MaPQ;
	   
	   public NhanVien() {}
	   
	   public NhanVien(String MaNV,String Ho,String Ten,String MatKhau,String CMND,String DiaChi,String Email,String DienThoai,Date NgaySinh,PhanQuyen MaPQ) {
		   this.MaNV=MaNV;
		   this.setMatKhau(MatKhau);
		   this.CMND=CMND;
		   this.DiaChi=DiaChi;
		   this.Email=Email;
		   this.DienThoai=DienThoai;
		   this.NgaySinh=NgaySinh;
		   this.Ho=Ho;
		   this.Ten=Ten;
		   this.MaPQ=MaPQ;
		   
	   }

		public String getMaNV() {
			return MaNV;
		}
	
		public void setMaNV(String maNV) {
			MaNV = maNV;
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

		public String getHo() {
			return Ho;
		}

		public void setHo(String ho) {
			Ho = ho;
		}

		public String getTen() {
			return Ten;
		}

		public void setTen(String ten) {
			Ten = ten;
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

