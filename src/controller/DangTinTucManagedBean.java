package controller;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.TinTuc;

import dao.TinTucDao;

@ManagedBean(name= "dangTinTucMBean")
@SessionScoped
public class DangTinTucManagedBean implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private TinTucDao tinTucDao=new TinTucDao();

		//Trả về danh sách nhân viên trên giao diện xhtml
		private List<TinTuc> DanhSach;

		//đối tượng nhân viên được chọn để cập nhật thông tin
		private TinTuc selectedTinTuc=new TinTuc();



		public DangTinTucManagedBean(){
			DanhSach = new ArrayList<TinTuc>();
			DanhSach = tinTucDao.danhSachTinTuc();
		}
		
		

		public TinTucDao getTinTucDao() {
			return tinTucDao;
		}

		public void setTinTucDao(TinTucDao tinTucDao) {
			this.tinTucDao = tinTucDao;
		}

		

		public List<TinTuc> getDanhSach() {
			DanhSach = new ArrayList<TinTuc>();
			DanhSach = tinTucDao.danhSachTinTuc();
			return DanhSach;
		}

		public void setDanhSach(List<TinTuc> danhSach) {
			DanhSach = danhSach;
		}

		

		public TinTuc getSelectedTinTuc() {
			return selectedTinTuc;
		}

		public void setSelectedTinTuc(TinTuc selectedTinTuc) {
			this.selectedTinTuc = selectedTinTuc;
		}

		
		
		
		
		public String hienThiTin(TinTuc x){
			selectedTinTuc=x;
			return "newsContent?faces-redirect=true";
		}
		

			
	}
