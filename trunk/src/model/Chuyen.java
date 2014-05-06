package model;

import java.util.Date;

public class Chuyen {
	   private int MaChuyen;
	   private int KhuyenMai;   
	   private Date GioDi;  
	   private Date GioDen;  
	   private int SoGheTrong;  
	   private TaiXe MaTX;  
	   private Xe BienSo;
	   private Tuyen MaTuyen;
	   
	   public Chuyen() {}
	   
	   public Chuyen(int MaChuyen,int KhuyenMai, Date GioDi, Date GioDen,int SoGheTrong,TaiXe MaTX,Xe BienSo,Tuyen MaTuyen) {
		   this.MaChuyen=MaChuyen;
		   this.setKhuyenMai(KhuyenMai);
		   this.setGioDi(GioDi);
		   this.setGioDen(GioDen);
		   this.SoGheTrong=SoGheTrong;
		   this.MaTX=MaTX;
		   this.BienSo=BienSo;
		   this.MaTuyen=MaTuyen;
	   }

		public int getMaChuyen() {
			return MaChuyen;
		}
	
		public void setMaChuyen(int maChuyen) {
			MaChuyen = maChuyen;
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

		public Date getGioDi() {
			return GioDi;
		}

		public void setGioDi(Date gioDi) {
			GioDi = gioDi;
		}

		public Date getGioDen() {
			return GioDen;
		}

		public void setGioDen(Date gioDen) {
			GioDen = gioDen;
		}

		public int getKhuyenMai() {
			return KhuyenMai;
		}

		public void setKhuyenMai(int khuyenMai) {
			KhuyenMai = khuyenMai;
		}

}
