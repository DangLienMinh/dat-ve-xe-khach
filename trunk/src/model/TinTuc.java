package model;
import java.io.Serializable;
import java.util.Date;

public class TinTuc implements Serializable{
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int MaTT;
	   private LoaiTinTuc MaLTT; 
	   private String TieuDe;   
	   private String HinhAnh;  
	   private Date NgayDang;  
	   private String TomTat;  
	   private String NoiDung;

	   public TinTuc() {}
	   
	   public TinTuc(int maTT, LoaiTinTuc maLTT, String tieuDe, String hinhAnh,
				Date ngayDang, String tomTat, String noiDung) {
			MaTT = maTT;
			MaLTT = maLTT;
			TieuDe = tieuDe;
			HinhAnh = hinhAnh;
			NgayDang = ngayDang;
			TomTat = tomTat;
			NoiDung = noiDung;
		}
	   	  
		public int getMaTT() {
			return MaTT;
		}
	
		public void setMaTT(int maTT) {
			MaTT = maTT;
		}
	
		public LoaiTinTuc getMaLTT() {
			return MaLTT;
		}
	
		public void setMaLTT(LoaiTinTuc maLTT) {
			MaLTT = maLTT;
		}
	
		public String getTieuDe() {
			return TieuDe;
		}
	
		public void setTieuDe(String tieuDe) {
			TieuDe = tieuDe;
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


		public String getNoiDung() {
			return NoiDung;
		}


		public void setNoiDung(String noiDung) {
			NoiDung = noiDung;
		}
		
}
