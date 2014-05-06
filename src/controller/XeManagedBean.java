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
	private List<SelectItem> selectOneItemXe;
	//Trả về danh sách nhân viên trên giao diện xhtml
	private List<Xe> DanhSach;
	//Trả về danh sách nhân viên theo kiểu lọc thuộc tính
	private List<Xe> filteredDanhSach;  
	//đối tượng nhân viên được chọn để cập nhật thông tin
	private Xe selectedXe=new Xe();
	//đối tượng phân quyền được chọn để cập nhật thông tin
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
		//sửa nhân viên dựa vào đối tượng nhân viên được chọn
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
	
	//hàm khi bấm vào icon găng cưa sẽ lưu thông tin đối tượng nhân viên được chọn
	public void capNhat(Xe x,String maLX){
		selectedXe=x;
		int ma=Integer.parseInt(maLX);
		setSelectedLoaiXe(xeDao.layLX(ma));
	}
	
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
