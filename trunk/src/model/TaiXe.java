package model;
import java.util.Date;

	public class TaiXe {
	   private int MaTX;
	   private String Ho;
	   private String Ten; 
	   private String CMND;   
	   private String DiaChi;  
	   private String Email;  
	   private String DienThoai;  
	   private Date NgaySinh;  

	   public TaiXe() {}
	   public TaiXe(int MaTX,String Ho,String Ten, String CMND, String DiaChi,String Email,String DienThoai,Date NgaySinh) {
		   this.MaTX=MaTX;
		   this.Ho=Ho;
		   this.setTen(Ten);
		   this.CMND=CMND;
		   this.DiaChi=DiaChi;
		   this.Email=Email;
		   this.DienThoai=DienThoai;
		   this.NgaySinh=NgaySinh;
	   }
		public int getMaTX() {
			return MaTX;
		}

		public void setMaTX(int maTX) {
			MaTX = maTX;
		}
		public String getHo() {
			return Ho;
		}
		public void setHo(String ho) {
			Ho = ho;
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
		public String getTen() {
			return Ten;
		}
		public void setTen(String ten) {
			Ten = ten;
		}
		
		@Override
		public String toString(){
			return Integer.toString(MaTX);
			
		}
		
}
