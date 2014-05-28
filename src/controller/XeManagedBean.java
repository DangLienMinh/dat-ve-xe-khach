package controller;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import dao.LoaiXeDao;
import dao.XeDao;
import model.LoaiXe;
import model.Xe;

@ManagedBean(name= "xeMBean")
@ViewScoped
public class XeManagedBean {
	
	private Xe xe=new Xe();
	private XeDao xeDao=new XeDao();
	private LoaiXe loaiXe=new LoaiXe();
	private LoaiXeDao loaiXeDao=new LoaiXeDao();
	
	//danh sach xe dung de hien thi trong combobox
	private List<SelectItem> selectOneItemXe;
	
	//Tra ve danh sach xe tren datatable
	private List<Xe> DanhSach;
	
	//Tra ve danh sach xe theo kieu loc thuoc tinh
	private List<Xe> filteredDanhSach;  
	
	//doi tuong xe duoc chon de cap nhat thong tin
	private Xe selectedXe=new Xe();
	
	//doi tuong loai xe duoc chon de cap nhat thong tin
	private LoaiXe selectedLoaiXe=new LoaiXe();

	public XeManagedBean(){
		DanhSach = new ArrayList<Xe>();
		DanhSach = xeDao.danhSachXe();
	}
	
	public Xe getXe() {
		return xe;
	}

	public void setXe(Xe xe) {
		this.xe = xe;
	}

	public XeDao getXeDao() {
		return xeDao;
	}

	public void setXeDao(XeDao xeDao) {
		this.xeDao = xeDao;
	}

	public LoaiXe getLoaiXe() {
		return loaiXe;
	}

	public void setLoaiXe(LoaiXe loaiXe) {
		this.loaiXe = loaiXe;
	}

	public LoaiXeDao getLoaiXeDao() {
		return loaiXeDao;
	}

	public void setLoaiXeDao(LoaiXeDao loaiXeDao) {
		this.loaiXeDao = loaiXeDao;
	}

	public List<Xe> getDanhSach() {
		return DanhSach;
	}

	public void setDanhSach(List<Xe> danhSach) {
		DanhSach = danhSach;
	}

	public List<Xe> getFilteredDanhSach() {
		return filteredDanhSach;
	}

	public void setFilteredDanhSach(List<Xe> filteredDanhSach) {
		this.filteredDanhSach = filteredDanhSach;
	}

	public Xe getSelectedXe() {
		return selectedXe;
	}

	public void setSelectedXe(Xe selectedXe) {
		this.selectedXe = selectedXe;
	}

	public LoaiXe getSelectedLoaiXe() {
		return selectedLoaiXe;
	}

	public void setSelectedLoaiXe(LoaiXe selectedLoaiXe) {
		this.selectedLoaiXe = selectedLoaiXe;
	}	
	
	public String themXe(){
		xeDao.themXe(xe, loaiXe);
		return "QLXe?faces-redirect=true";
	}
	
	public String suaXe(){
		//sua xe dua vao doi tuong xe duoc chon
		xeDao.suaXe(selectedXe,selectedLoaiXe);
		return "QLXe?faces-redirect=true";
	}
	
	public String xoaXe(Xe x){
		xeDao.xoaXe(x);
		return "QLXe?faces-redirect=true";
	}
	
	//reset các ô input
	public void reset(){
		xeDao.reset(xe);
	}
	
	//ham khi bam vao icon gang cua se cap nhat doi tuong xe
	public void capNhat(Xe x,String maLX){
		selectedXe=x;
		int ma=Integer.parseInt(maLX);
		setSelectedLoaiXe(loaiXeDao.layLX(ma));
	}
	
	//tra ve combobox danh  sach xe
	public List<SelectItem> getSelectOneItemXe() {
		this.selectOneItemXe=new ArrayList<SelectItem>();
		List<Xe> xes=xeDao.selectItems();
		for(Xe x:xes){
			SelectItem selectItem=new SelectItem(x.getBienSo());
			this.selectOneItemXe.add(selectItem);
		}
		return selectOneItemXe;
	}
}
