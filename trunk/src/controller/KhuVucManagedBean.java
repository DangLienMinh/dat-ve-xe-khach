package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import model.KhuVuc;
import dao.KhuVucDao;


@ManagedBean(name= "khuVucMBean")
@ViewScoped
public class KhuVucManagedBean {
	
	private  KhuVuc khuVuc=new KhuVuc();
	private KhuVucDao khuVucDao=new KhuVucDao();
	
	//TRA VE DANH SACH KHU VUC DE HIEN TREN COMBOBOX
	private List<SelectItem> selectOneItemKV;
	
	//Tra ve danh sach khuVuc tren datatable
	private List<KhuVuc> DanhSach;
	
	//Tra ve danh sach khuVuc theo kieu loc thuoc tinh
	private List<KhuVuc> filteredDanhSach;  
	
	//doi tuong khuVuc duoc chon de cap nhat thong tin
	private KhuVuc selectedKhuVuc=new KhuVuc();
	
	public KhuVucManagedBean(){
		DanhSach = new ArrayList<KhuVuc>();
		DanhSach = khuVucDao.danhSachKhuVuc();
	}
	
	public String themKhuVuc(){
		khuVucDao.themKhuVuc(khuVuc);
		return "QLKhuVuc?faces-redirect=true";
	}
	
	public String suaKhuVuc(){
		//sua khuVuc dua vao doi tuong khuVuc duoc chon
		khuVucDao.suaKhuVuc(selectedKhuVuc);
		return "QLKhuVuc?faces-redirect=true";
	}
	
	public String xoaKhuVuc(KhuVuc x){
		khuVucDao.xoaKhuVuc(x);
		return "QLKhuVuc?faces-redirect=true";
	}
	
	//reset các ô input
	public String reset(){
		khuVucDao.reset(khuVuc);
		return "QLKhuVuc?faces-redirect=true";
	}
	
	//ham khi bam vao icon gang cua se cap nhat doi tuong khuVuc
	public void capNhat(KhuVuc x){
		selectedKhuVuc=x;
	}
	
	


		public KhuVuc getKhuVuc() {
			return khuVuc;
		}

		public void setKhuVuc(KhuVuc khuVuc) {
			this.khuVuc = khuVuc;
		}

		public KhuVucDao getKhuVucDao() {
			return khuVucDao;
		}

		public void setKhuVucDao(KhuVucDao khuVucDao) {
			this.khuVucDao = khuVucDao;
		}

		

		public void setSelectOneItemKV(List<SelectItem> selectOneItemKV) {
			this.selectOneItemKV = selectOneItemKV;
		}

		public List<KhuVuc> getDanhSach() {
			return DanhSach;
		}

		public void setDanhSach(List<KhuVuc> danhSach) {
			DanhSach = danhSach;
		}

		public List<KhuVuc> getFilteredDanhSach() {
			return filteredDanhSach;
		}

		public void setFilteredDanhSach(List<KhuVuc> filteredDanhSach) {
			this.filteredDanhSach = filteredDanhSach;
		}

		public KhuVuc getSelectedKhuVuc() {
			return selectedKhuVuc;
		}


		public List<SelectItem> getSelectOneItemKV() {
			this.selectOneItemKV=new ArrayList<SelectItem>();
			List<KhuVuc> khuVucs=khuVucDao.selectItems();
			for(KhuVuc kv:khuVucs){
				SelectItem selectItem=new SelectItem(kv.getMaKV(),kv.getTenKV());
				this.selectOneItemKV.add(selectItem);
			}
			return selectOneItemKV;
		}

}
