package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import model.Tuyen;
import dao.TuyenDao;

@ManagedBean(name= "tuyenMBean")
@ViewScoped
public class TuyenManagedBean {
	private Tuyen tuyen=new Tuyen();
	private TuyenDao tuyenDao=new TuyenDao();
	private List<SelectItem> selectOneItemTuyen;
	//Trả về danh sách nhân viên trên giao diện xhtml
	private List<Tuyen> DanhSach;
	//Trả về danh sách nhân viên theo kiểu lọc thuộc tính
	private List<Tuyen> filteredDanhSach;  
	//đối tượng nhân viên được chọn để cập nhật thông tin
	private Tuyen selectedTuyen=new Tuyen();
	
	public TuyenManagedBean(){
		DanhSach = new ArrayList<Tuyen>();
		DanhSach = tuyenDao.danhSachTX();
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
	
	public String themTuyen(){
		tuyenDao.themTuyen(tuyen);
		return "QLTuyen??faces-redirect=true";
	}
	
	public String suaTuyen(){
		//sửa nhân viên dựa vào đối tượng nhân viên được chọn
		tuyenDao.suaTuyen(selectedTuyen);
		return "QLTuyen??faces-redirect=true";
	}
	
	public String xoaTuyen(Tuyen x){
		tuyenDao.xoaTuyen(x);
		return "QLTuyen?faces-redirect=true";
	}
	
	//reset các ô input
	public void reset(){
		tuyenDao.reset(tuyen);
	}
	
	//hàm khi bấm vào icon găng cưa sẽ lưu thông tin đối tượng nhân viên được chọn
	public void capNhat(Tuyen x){
		selectedTuyen=x;
	}	
	
	public List<SelectItem> getSelectOneItemTuyen() {
		this.selectOneItemTuyen=new ArrayList<SelectItem>();
		List<Tuyen> tuyens=tuyenDao.selectItems();
		for(Tuyen t:tuyens){
			SelectItem selectItem=new SelectItem(t.getMaTuyen(),t.getBenDau()+" - "+t.getBenCuoi());
			this.selectOneItemTuyen.add(selectItem);
		}
		return selectOneItemTuyen;
	}
	
	public String tenTuyen(String maTuyen){
		int ma=Integer.parseInt(maTuyen);
		return tuyenDao.tenTuyen(ma);
	}
}
