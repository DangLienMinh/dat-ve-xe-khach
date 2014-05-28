package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import model.TaiXe;
import dao.TaiXeDao;

@ManagedBean(name= "taiXeMBean")
@SessionScoped
public class TaiXeManagedBean {
	
	private TaiXe taiXe=new TaiXe();
	private TaiXeDao taiXeDao=new TaiXeDao();
	
	//danh sach tai xe dung de hien thi trong combobox
	private List<SelectItem> selectOneItemTX;
	
	//Tra ve danh sach tai xe tren giao dien
	private List<TaiXe> DanhSach;
	
	//danh sach tai xe theo kieu loc thuoc tinh
	private List<TaiXe> filteredDanhSach;  
	
	//doi tuong tai xe duoc chon
	private TaiXe selectedTX=new TaiXe();
	
	public TaiXeManagedBean(){
		DanhSach = new ArrayList<TaiXe>();
		DanhSach = taiXeDao.danhSachTX();
	}
	public TaiXe getTaiXe() {
		return taiXe;
	}
	public void setTaiXe(TaiXe taiXe) {
		this.taiXe = taiXe;
	}
	public TaiXeDao getTaiXeDao() {
		return taiXeDao;
	}
	public void setTaiXeDao(TaiXeDao taiXeDao) {
		this.taiXeDao = taiXeDao;
	}
	
	public List<TaiXe> getDanhSach() {
		return DanhSach;
	}
	public void setDanhSach(List<TaiXe> danhSach) {
		DanhSach = danhSach;
	}
	public List<TaiXe> getFilteredDanhSach() {
		return filteredDanhSach;
	}
	public void setFilteredDanhSach(List<TaiXe> filteredDanhSach) {
		this.filteredDanhSach = filteredDanhSach;
	}
	public TaiXe getSelectedTX() {
		return selectedTX;
	}
	public void setSelectedTX(TaiXe selectedTX) {
		this.selectedTX = selectedTX;
	}
	
	public String themTaiXe(){
		taiXeDao.themTaiXe(taiXe);
		return "QLTaiXe?faces-redirect=true";
	}
	
	public String suaTaiXe(){
		//sua tai xe dua vao doi tuong tai xe duoc chon
		taiXeDao.suaTaiXe(selectedTX);
		return "QLTaiXe?faces-redirect=true";
	}
	
	public String xoaTaiXe(TaiXe x){
		taiXeDao.xoaTaiXe(x);
		return "QLTaiXe?faces-redirect=true";
	}
	
	//reset các ô input
	public void reset(){
		taiXeDao.reset(taiXe);
	}
	
	//ham khi bam vao icon gang cua se luu doi tuong nhan vien duoc chon
	public void capNhat(TaiXe x){
		selectedTX=x;
	}	
	
	//tra ve danh sach tai xe dung cho combobox tai xe
	public List<SelectItem> getSelectOneItemTX() {
		this.selectOneItemTX=new ArrayList<SelectItem>();
		List<TaiXe> txs=taiXeDao.selectItems();
		for(TaiXe x:txs){
			SelectItem selectItem=new SelectItem(x.getMaTX(),x.getHo()+x.getTen());
			this.selectOneItemTX.add(selectItem);
		}
		return selectOneItemTX;
	}
}
