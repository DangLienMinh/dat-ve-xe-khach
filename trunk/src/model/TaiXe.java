package model;
import java.util.Date;

	public class TaiXe {
	   private String MaTX;
	   private String HoTen; 
	   private String CMND;   
	   private String DiaChi;  
	   private String Email;  
	   private String DienThoai;  
	   private Date NgaySinh;  

	   public TaiXe() {}
	   public TaiXe(String MaTX,String HoTen, String CMND, String DiaChi,String Email,String DienThoai,Date NgaySinh) {
		   this.MaTX=MaTX;
		   this.HoTen=HoTen;
		   this.CMND=CMND;
		   this.DiaChi=DiaChi;
		   this.Email=Email;
		   this.DienThoai=DienThoai;
		   this.NgaySinh=NgaySinh;
	   }
		public String getMaTX() {
			return MaTX;
		}

		public void setMaTX(String maTX) {
			MaTX = maTX;
		}
		public String getHoTen() {
			return HoTen;
		}
		public void setHoTen(String hoTen) {
			HoTen = hoTen;
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
