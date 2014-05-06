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
	private List<SelectItem> selectOneItemTX;
	//Trả về danh sách nhân viên trên giao diện xhtml
	private List<TaiXe> DanhSach;
	//Trả về danh sách nhân viên theo kiểu lọc thuộc tính
	private List<TaiXe> filteredDanhSach;  
	//đối tượng nhân viên được chọn để cập nhật thông tin
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
		//sửa nhân viên dựa vào đối tượng nhân viên được chọn
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
	
	//hàm khi bấm vào icon găng cưa sẽ lưu thông tin đối tượng nhân viên được chọn
	public void capNhat(TaiXe x){
		selectedTX=x;
	}	
	
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
