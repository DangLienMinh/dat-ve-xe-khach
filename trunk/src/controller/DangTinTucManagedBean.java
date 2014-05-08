package controller;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.LoaiTinTuc;
import model.TinTuc;
import dao.LoaiTinTucDao;
import dao.TinTucDao;

@ManagedBean(name= "dangTinTucMBean")
@SessionScoped
public class DangTinTucManagedBean implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private TinTuc tinTuc=new TinTuc();
		private TinTucDao tinTucDao=new TinTucDao();
		private LoaiTinTuc loaiTinTuc=new LoaiTinTuc();
		private LoaiTinTucDao loaiTinTucDao=new LoaiTinTucDao();
		//Trả về danh sách nhân viên trên giao diện xhtml
		private List<TinTuc> DanhSach;
		//Trả về danh sách nhân viên theo kiểu lọc thuộc tính
		private List<TinTuc> filteredDanhSach;  
		//đối tượng nhân viên được chọn để cập nhật thông tin
		private TinTuc selectedTinTuc=new TinTuc();
		//đối tượng phân quyền được chọn để cập nhật thông tin
		private LoaiTinTuc selectedLoaiTinTuc=new LoaiTinTuc();


		public DangTinTucManagedBean(){
			DanhSach = new ArrayList<TinTuc>();
			DanhSach = tinTucDao.danhSachTinTuc();
		}
		
		public TinTuc getTinTuc() {
			return tinTuc;
		}

		public void setTinTuc(TinTuc tinTuc) {
			this.tinTuc = tinTuc;
		}

		public TinTucDao getTinTucDao() {
			return tinTucDao;
		}

		public void setTinTucDao(TinTucDao tinTucDao) {
			this.tinTucDao = tinTucDao;
		}

		public LoaiTinTuc getLoaiTinTuc() {
			return loaiTinTuc;
		}

		public void setLoaiTinTuc(LoaiTinTuc loaiTinTuc) {
			this.loaiTinTuc = loaiTinTuc;
		}

		public LoaiTinTucDao getLoaiTinTucDao() {
			return loaiTinTucDao;
		}

		public void setLoaiTinTucDao(LoaiTinTucDao loaiTinTucDao) {
			this.loaiTinTucDao = loaiTinTucDao;
		}

		public List<TinTuc> getDanhSach() {
			return DanhSach;
		}

		public void setDanhSach(List<TinTuc> danhSach) {
			DanhSach = danhSach;
		}

		public List<TinTuc> getFilteredDanhSach() {
			return filteredDanhSach;
		}

		public void setFilteredDanhSach(List<TinTuc> filteredDanhSach) {
			this.filteredDanhSach = filteredDanhSach;
		}

		public TinTuc getSelectedTinTuc() {
			return selectedTinTuc;
		}

		public void setSelectedTinTuc(TinTuc selectedTinTuc) {
			this.selectedTinTuc = selectedTinTuc;
		}

		public LoaiTinTuc getSelectedLoaiTinTuc() {
			return selectedLoaiTinTuc;
		}

		public void setSelectedLoaiTinTuc(LoaiTinTuc selectedLoaiTinTuc) {
			this.selectedLoaiTinTuc = selectedLoaiTinTuc;
		}	
		
		
		
		
		
		public String hienThiTin(TinTuc x){
			selectedTinTuc=x;
			return "newsContent?faces-redirect=true";
		}
		

			
	}
