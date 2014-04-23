package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "KiemTra")
public class KiemTra {
 @Id 
 @Column(name = "MaTX")
   private String MaTX;
 @Column(name = "HoTen")
   private String HoTen; 
 @Column(name = "NgaySinh")
 private Date NgaySinh;
 public KiemTra() {}
 public KiemTra(String MaTX,String HoTen,Date NgaySinh){
	 this.MaTX=MaTX;
	   this.HoTen=HoTen;
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
	public Date getNgaySinh() {
		return NgaySinh;
	}
	public void setNgaySinh(Date ngaySinh2) {
		NgaySinh = ngaySinh2;
	}
}