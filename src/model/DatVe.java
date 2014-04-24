package model;

import java.util.*;

public class DatVe {
	 private String MaDV;
	 private Date NgayGD; 
	 private int TongTien; 
	 private Date NgayDi; 
	 private Chuyen MaChuyen; 
	 private String HoTen; 
	 private String DiaChi;  
	 private String Email;  
	 private String DienThoai;  
     private int HinhThucThanhToan;  
     private Set<Ghe> Ghe;
	 
	 public DatVe(){}
	 
	 public DatVe(String MaDV,Date NgayGD,int TongTien,Date NgayDi,Chuyen MaChuyen,String HoTen,String DiaChi,String Email,String DienThoai,int HinhThucThanhToan){
		 this.MaDV=MaDV;
		 this.NgayGD=NgayGD;
		 this.TongTien=TongTien;
		 this.NgayDi=NgayDi;
		 this.MaChuyen=MaChuyen;
		 this.HoTen=HoTen;
		 this.DiaChi=DiaChi;
		 this.Email=Email;
		 this.DienThoai=DienThoai;
		 this.HinhThucThanhToan=HinhThucThanhToan;

	 }

	public String getMaDV() {
		return MaDV;
	}

	public void setMaDV(String maDV) {
		MaDV = maDV;
	}

	public Date getNgayGD() {
		return NgayGD;
	}

	public void setNgayGD(Date ngayGD) {
		NgayGD = ngayGD;
	}

	public int getTongTien() {
		return TongTien;
	}

	public void setTongTien(int tongTien) {
		TongTien = tongTien;
	}

	public Date getNgayDi() {
		return NgayDi;
	}

	public void setNgayDi(Date ngayDi) {
		NgayDi = ngayDi;
	}

	public Chuyen getMaChuyen() {
		return MaChuyen;
	}

	public void setMaChuyen(Chuyen maChuyen) {
		MaChuyen = maChuyen;
	}

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
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

	public int getHinhThucThanhToan() {
		return HinhThucThanhToan;
	}

	public void setHinhThucThanhToan(int hinhThucThanhToan) {
		HinhThucThanhToan = hinhThucThanhToan;
	}

	public Set<Ghe> getGhe() {
		return Ghe;
	}

	public void setGhe(Set<Ghe> ghe) {
		Ghe = ghe;
	}
	

}
