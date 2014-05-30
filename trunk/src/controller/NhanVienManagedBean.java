package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRException;
import javax.faces.application.FacesMessage;
import javax.servlet.http.HttpSession;

import model.NhanVien;
import model.PhanQuyen;
import dao.NhanVienDao;
import dao.PhanQuyenDao;

@ManagedBean(name= "nhanVienMBean")
@SessionScoped 
public class NhanVienManagedBean {
	
	private NhanVien nhanVien=new NhanVien();
	private NhanVienDao nhanVienDao=new NhanVienDao();
	private PhanQuyen phanQuyen=new PhanQuyen();
	private PhanQuyenDao phanQuyenDao=new PhanQuyenDao();
	
	//inject controller reportMB vao hoa Don
	@ManagedProperty(value="#{reportMBean}")
	private ReportManagedBean reportMB;
	
	//ho ten nhan vien da dang nhap
	private String HoTen;
	
	//danh sach table nhan vien tren giao dien xhtml
	private List<NhanVien> DanhSach;
	
	//danh sach table nhan vien cho viec filter thuoc tinh
	private List<NhanVien> filteredDanhSach; 
	
	//doi tuong nhan vien duoc chon de cap nhat thong tin
	private NhanVien selectedNV=new NhanVien();
	
	//doi tuong phan quyen duoc chon de cap nhat thong tin
	private PhanQuyen selectedPhanQuyen=new PhanQuyen();
	
	//Ngay sinh nhan vien phai nho hon ngay hien tai
	private Date Today;
		
	public NhanVienManagedBean(){
		DanhSach = new ArrayList<NhanVien>();
		DanhSach = nhanVienDao.danhSachNV();
		reportMB=new ReportManagedBean();
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
	public String getHoTen() {
		//lay thong tin ho ten dua vao session
		 HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	     return  session.getAttribute("user").toString();
	}
	public List<NhanVien> getDanhSach() {
		return DanhSach;
	}

	//ham dang nhap 
	public String dangNhap(){
		int x=nhanVienDao.dangNhap(nhanVien);
		if(x==1){
			//lay ho nhan vien
		  HoTen=nhanVienDao.layHoTen(nhanVien);
		  
		  //lưu họ teen vào session
		  FacesContext context = FacesContext.getCurrentInstance();
		  context.getExternalContext().getSessionMap().put("user", HoTen);
		  context.getExternalContext().getSessionMap().put("manv", nhanVien.getMaNV());
		  //chuyen trang theo phan quyen
		  NhanVien nv=nhanVienDao.layNhanVien(nhanVien);
		  PhanQuyen pq=nv.getMaPQ();
		  if(pq.getMaPQ()==1){
			  return "QLNhanVien?faces-redirect=true";
		  }
		  else if(pq.getMaPQ()==2){
			  return "QLTuyen?faces-redirect=true";
		  }else{
			  return "layVe?faces-redirect=true";
		  }
		  
		}else{
			FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Đăng nhập thất bại!",
                    "Vui lòng thử lại!"));
			return "dangNhap";
		}
	}
	
	//ham dang xuat
	public String dangXuat() {
		//xoa vbien luu ho ten trong session
		  FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	      return "dangNhap?faces-redirect=true";
	   }
	
	public String themNhanVien(){
		nhanVienDao.themNhanVien(nhanVien, phanQuyen);
		return "QLNhanVien?faces-redirect=true";
	}
	
	public String suaNhanVien(){
		//sua nhan vien dua vao doi tuong nhan vien duoc chon
		nhanVienDao.suaNhanVien(selectedNV,selectedPhanQuyen);
		return "QLNhanVien?faces-redirect=true";
	}
	
	public String xoaNhanVien(NhanVien x){
		nhanVienDao.xoaNhanVien(x);
		return "QLNhanVien?faces-redirect=true";
	}
	
	//reset cac o input
	public String reset(){
		nhanVienDao.reset(nhanVien);
		//tabIndex=0;
		return "QLNhanVien?faces-redirect=true";
	}
	
	//Nhan nut back trong report thong tin nhan vien
//		public String back(){
//			nhanVienDao.reset(nhanVien);
//			tabIndex=2;
//			return "QLNhanVien?faces-redirect=true";
//		}
	
	//ham khi bam vao icon rang cua se luu thong tin doi tuong nhan vien duoc chon
	public void capNhat(NhanVien x,String maPQ){
		selectedNV=x;
		int ma=Integer.parseInt(maPQ);
		this.setSelectedPhanQuyen(phanQuyenDao.layPQ(ma));
	}

	public Date getToday() {
		return new Date();
	}
	
	public String inTTNV() throws ClassNotFoundException, SQLException, IOException,JRException{
		return reportMB.inTTNhanVien(selectedNV.getMaNV());
		//return reportMB.inVe("V140500006");
	}

	public ReportManagedBean getReportMB() {
		return reportMB;
	}

	public void setReportMB(ReportManagedBean reportMB) {
		this.reportMB = reportMB;
	}
}
