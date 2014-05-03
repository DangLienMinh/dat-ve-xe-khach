package controller;

import hibernateUtil.HibernateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.NhanVien;
import model.PhanQuyen;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.SelectEvent;

import dao.NhanVienDao;
import dao.PhanQuyenDao;

@ManagedBean(name= "nhanVienMBean")
@SessionScoped 
public class NhanVienManagedBean {
	private NhanVien nhanVien=new NhanVien();
	private NhanVienDao nhanVienDao=new NhanVienDao();
	private PhanQuyen phanQuyen=new PhanQuyen();
	private PhanQuyenDao phanQuyenDao=new PhanQuyenDao();
	private String HoTen;
	private List<NhanVien> DanhSach;
	private List<NhanVien> filteredDanhSach;  
	private NhanVien selectedNV=new NhanVien();
	private PhanQuyen selectedPhanQuyen=new PhanQuyen();

	public NhanVienManagedBean(){
		DanhSach = new ArrayList<NhanVien>();
		DanhSach = nhanVienDao.danhSachNV();
	}
	
	
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public NhanVienDao getNhanVienDao() {
		return nhanVienDao;
	}
	public void setNhanVienDao(NhanVienDao nhanVienDao) {
		this.nhanVienDao = nhanVienDao;
	}
	public PhanQuyen getPhanQuyen() {
		return phanQuyen;
	}
	public void setPhanQuyen(PhanQuyen phanQuyen) {
		this.phanQuyen = phanQuyen;
	}
	public PhanQuyenDao getPhanQuyenDao() {
		return phanQuyenDao;
	}
	public void setPhanQuyenDao(PhanQuyenDao phanQuyenDao) {
		this.phanQuyenDao = phanQuyenDao;
	}
	public String dangNhap(){
		int x=nhanVienDao.dangNhap(nhanVien);
		if(x==1){
		  HoTen=nhanVienDao.layHoTen(nhanVien);
		  FacesContext context = FacesContext.getCurrentInstance();
		  context.getExternalContext().getSessionMap().put("user", HoTen);
		  return "QLNhanVien?faces-redirect=true";
		}else{
			FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Invalid Login!",
                    "Please Try Again!"));
			return "dangNhap";
		}
	}
	
	public String dangXuat() {
		  FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	      return "dangNhap";
	   }
	
	public String themNhanVien(){
		nhanVienDao.themNhanVien(nhanVien, phanQuyen);
		return "QLNhanVien?faces-redirect=true";
		
	}
	
	public String suaNhanVien(){
		
		nhanVienDao.suaNhanVien(selectedNV,selectedPhanQuyen);
		return "QLNhanVien?faces-redirect=true";
		
	}
	
	public String xoaNhanVien(NhanVien x){
		nhanVienDao.xoaNhanVien(x);
		return "QLNhanVien?faces-redirect=true";
	}
	
	public String getHoTen() {
		 HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	     return  session.getAttribute("user").toString();
	}
	
	public List<NhanVien> getDanhSach() {
		//DanhSach=nhanVienDao.danhSachNV();
		return DanhSach;
	}
	public void reset(){
		nhanVienDao.reset(nhanVien);
	}
	
	public void capNhat(NhanVien x,String maPQ){
		selectedNV=x;
		int ma=Integer.parseInt(maPQ);
		setSelectedPhanQuyen(nhanVienDao.layPQ(ma));

	}

	public NhanVien getSelectedNV() {
		return selectedNV;
	}
	public void setSelectedNV(NhanVien selectedNV) {
		this.selectedNV = selectedNV;
	}
	public PhanQuyen getSelectedPhanQuyen() {
		return selectedPhanQuyen;
	}
	public void setSelectedPhanQuyen(PhanQuyen selectedPhanQuyen) {
		this.selectedPhanQuyen = selectedPhanQuyen;
	}


	public List<NhanVien> getFilteredDanhSach() {
		return filteredDanhSach;
	}


	public void setFilteredDanhSach(List<NhanVien> filteredDanhSach) {
		this.filteredDanhSach = filteredDanhSach;
	}
	
	
}
