package model;
import java.util.Date;

public class TinTuc {
	   private String MaTT;
	   private TinTuc MaLTT; 
	   private String TieuDe;   
	   private String NoiDung;  
	   private String HinhAnh;  
	   private Date NgayDang;  
	   private String TomTat;  

	   public TinTuc() {}
	   
	   public TinTuc(String MaTT,TinTuc MaLTT, String TieuDe, String NoiDung,String HinhAnh,Date NgayDang,String TomTat) {
		   this.TomTat=TomTat;
		   this.MaLTT=MaLTT;
		   this.TieuDe=TieuDe;
		   this.NoiDung=NoiDung;
		   this.HinhAnh=HinhAnh;
		   this.NgayDang=NgayDang;
		   this.TomTat=TomTat;
	   }

		public String getMaTT() {
			return MaTT;
		}
	
		public void setMaTT(String maTT) {
			MaTT = maTT;
		}
	
		public TinTuc getMaLTT() {
			return MaLTT;
		}
	
		public void setMaLTT(TinTuc maLTT) {
			MaLTT = maLTT;
		}
	
		public String getTieuDe() {
			return TieuDe;
		}
	
		public void setTieuDe(String tieuDe) {
			TieuDe = tieuDe;
		}
	
		public String getNoiDung() {
			return NoiDung;
		}
	
		public void setNoiDung(String noiDung) {
			NoiDung = noiDung;
		}
	
		public String getHinhAnh() {
			return HinhAnh;
		}
	
		public void setHinhAnh(String hinhAnh) {
			HinhAnh = hinhAnh;
		}
	
		public Date getNgayDang() {
			return NgayDang;
		}
	
		public void setNgayDang(Date ngayDang) {
			NgayDang = ngayDang;
		}
	
		public String getTomTat() {
			return TomTat;
		}
	
		public void setTomTat(String tomTat) {
			TomTat = tomTat;
		}
		
}
