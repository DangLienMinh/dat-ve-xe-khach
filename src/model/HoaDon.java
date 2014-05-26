package model;

import java.util.Calendar;
import java.util.Date;

public class HoaDon {
	 private String MaHD;
	 private Date NgayGD; 
	 private Date NgayNhanVe;
	 private int TongTien; 
	 private Chuyen MaChuyen; 
	 private String Ho; 
	 private String CMND;
	 private String Ten;
	 private String DiaChi;  
	 private String Email;  
	 private String DienThoai;  
     private String HinhThucTT;  
     private Ghe MaGhe;
     
	 public HoaDon(){
		 Calendar cal =Calendar.getInstance();
		 cal.add(Calendar.DATE, 1);
		 NgayGD=cal.getTime();
	 }
	 
	 public HoaDon(String MaHD,Date NgayGD,Date NgayNhanVe,int TongTien,Chuyen MaChuyen,String Ho,String Ten,String CMND,String DiaChi,String Email,String DienThoai,String HinhThucThanhToan,Ghe maGhe){
		 this.MaHD=MaHD;
		 this.NgayGD=NgayGD;
		 this.TongTien=TongTien;
		 this.NgayNhanVe=NgayNhanVe;
		 this.MaChuyen=MaChuyen;
		 this.Ho=Ho;
		 this.Ten=Ten;
		 this.CMND=CMND;
		 this.DiaChi=DiaChi;
		 this.Email=Email;
		 this.DienThoai=DienThoai;
		 this.HinhThucTT=HinhThucThanhToan;
		 this.setMaGhe(maGhe);
	 }

	public String getMaHD() {
		return MaHD;
	}

	public void setMaHD(String maHD) {
		MaHD = maHD;
	}

	public Date getNgayGD() {
		return NgayGD;
	}

	public void setNgayGD(Date ngayGD) {
		NgayGD = ngayGD;
	}

	public Date getNgayNhanVe() {
		return NgayNhanVe;
	}

	public void setNgayNhanVe(Date ngayNhanVe) {
		NgayNhanVe = ngayNhanVe;
	}

	public int getTongTien() {
		return TongTien;
	}

	public void setTongTien(int tongTien) {
		TongTien = tongTien;
	}

	public Chuyen getMaChuyen() {
		return MaChuyen;
	}

	public void setMaChuyen(Chuyen maChuyen) {
		MaChuyen = maChuyen;
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

	public String getTen() {
		return Ten;
	}

	public void setTen(String ten) {
		Ten = ten;
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

	public String getHinhThucTT() {
		return HinhThucTT;
	}

	public void setHinhThucTT(String hinhThucThanhToan) {
		HinhThucTT = hinhThucThanhToan;
	}

	public Ghe getMaGhe() {
		return MaGhe;
	}

	public void setMaGhe(Ghe maGhe) {
		MaGhe = maGhe;
	}

	
}
