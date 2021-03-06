package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import model.Tuyen;
import dao.TuyenDao;

@ManagedBean(name= "tuyenMBean")
@ViewScoped
public class TuyenManagedBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tuyen tuyen=new Tuyen();
	private TuyenDao tuyenDao=new TuyenDao();
	
	//danh sach tuyen dung de hien thi trong combobox
	private List<SelectItem> selectOneItemTuyen;

	//Tra ve danh sach tuyen 
	private List<Tuyen> DanhSach;
	
	//Tra ve danh sach tuyen kieu loc danh sach
	private List<Tuyen> filteredDanhSach;  
	
	//đoi tuong tuyen duoc chon de cap nhat thong tin
	private Tuyen selectedTuyen=new Tuyen();
	
	public TuyenManagedBean(){
		DanhSach = new ArrayList<Tuyen>();
		DanhSach = tuyenDao.danhSachTuyen();		
	}
	public Tuyen getTuyen() {
		return tuyen;
	}
	public void setTuyen(Tuyen tuyen) {
		this.tuyen = tuyen;
	}
	public TuyenDao getTuyenDao() {
		return tuyenDao;
	}
	public void setTuyenDao(TuyenDao tuyenDao) {
		this.tuyenDao = tuyenDao;
	}
	
	public List<Tuyen> getDanhSach() {
		return DanhSach;
	}
	public void setDanhSach(List<Tuyen> danhSach) {
		DanhSach = danhSach;
	}
	public List<Tuyen> getFilteredDanhSach() {
		return filteredDanhSach;
	}
	public void setFilteredDanhSach(List<Tuyen> filteredDanhSach) {
		this.filteredDanhSach = filteredDanhSach;
	}
	public Tuyen getSelectedTuyen() {
		return selectedTuyen;
	}
	public void setSelectedTuyen(Tuyen selectedTuyen) {
		this.selectedTuyen = selectedTuyen;
	}
	
	//them tuyen moi
	public String themTuyen(){
		tuyenDao.themTuyen(tuyen);
		return "QLTuyen?faces-redirect=true";
	}
	
	//sua tuyen
	public String suaTuyen(){
		//sua tuyen dua vao doi tuong tuyen duoc chon
		tuyenDao.suaTuyen(selectedTuyen);
		return "QLTuyen?faces-redirect=true";
	}
	
	//xoa tuyen
	public String xoaTuyen(Tuyen x){
		tuyenDao.xoaTuyen(x);
		return "QLTuyen?faces-redirect=true";
	}
	
	//reset cac o input
	public String reset(){
		tuyenDao.reset(tuyen);
		return "QLTuyen?faces-redirect=true";
	}
	
	//ham khi bam vao icon gang cua se cap nhat doi tuong tuyen
	public void capNhat(Tuyen x){
		selectedTuyen=x;
	}	
	
	//tra ve combobox tuyen tren view
	public List<SelectItem> getSelectOneItemTuyen() {
		this.selectOneItemTuyen=new ArrayList<SelectItem>();
		List<Tuyen> tuyens=tuyenDao.selectItems();
		for(Tuyen t:tuyens){
			SelectItem selectItem=new SelectItem(t.getMaTuyen(),t.getBenDau()+" - "+t.getBenCuoi());
			this.selectOneItemTuyen.add(selectItem);
		}
		return selectOneItemTuyen;
	}
	
	//tra ve ten tuyen
	public String tenTuyen(String maTuyen){
		return tuyenDao.tenTuyen(maTuyen);
	}
}
