package model;

import java.io.Serializable;
import java.util.Date;

public class LienHe implements Serializable{
	public LienHe(){}
	public LienHe(int id, String email, String noiDung, Date ngayGui,
			int tinhTrang) {
		super();
		Id = id;
		Email = email;
		NoiDung = noiDung;
		NgayGui = ngayGui;
		TinhTrang = tinhTrang;
	}
	private static final long serialVersionUID = 1L;
	private int Id;
	 private String Email;
	 private String NoiDung;
	 private Date NgayGui;
	 private int TinhTrang;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getNoiDung() {
		return NoiDung;
	}
	public void setNoiDung(String noiDung) {
		NoiDung = noiDung;
	}
	public Date getNgayGui() {
		return NgayGui;
	}
	public void setNgayGui(Date ngayGui) {
		NgayGui = ngayGui;
	}
	public int getTinhTrang() {
		return TinhTrang;
	}
	public void setTinhTrang(int tinhTrang) {
		TinhTrang = tinhTrang;
	}
	@Override
	public String toString() {
		return Integer.toString(Id);
	}
}
