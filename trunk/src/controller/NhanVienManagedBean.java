package controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
	//Họ tên nhân viên đã đăng nhập
	private String HoTen;
	//Trả về danh sách nhân viên trên giao diện xhtml
	private List<NhanVien> DanhSach;
	//Trả về danh sách nhân viên theo kiểu lọc thuộc tính
	private List<NhanVien> filteredDanhSach;  
	//đối tượng nhân viên được chọn để cập nhật thông tin
	private NhanVien selectedNV=new NhanVien();
	//đối tượng phân quyền được chọn để cập nhật thông tin
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
		//lấy thông tin họ tên nhân viên dựa vào session
		 HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	     return  session.getAttribute("user").toString();
	}
	public List<NhanVien> getDanhSach() {
		return DanhSach;
	}

	public String dangNhap(){
		int x=nhanVienDao.dangNhap(nhanVien);
		if(x==1){
			//lấy họ tên nhân viên
		  HoTen=nhanVienDao.layHoTen(nhanVien);
		  
		  //lưu họ teen vào session
		  FacesContext context = FacesContext.getCurrentInstance();
		  context.getExternalContext().getSessionMap().put("user", HoTen);
		  context.getExternalContext().getSessionMap().put("tendn", nhanVien.getTenDN());
		  //chuyển về trang QLNhanVien.xhtml
		  return "QLNhanVien?faces-redirect=true";
		}else{
			FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Đăng nhập thất bại!",
                    "Vui lòng thử lại!"));
			return "dangNhap";
		}
	}
	
	public String dangXuat() {
		//xoá biến lưu họ tên nhân viên trong session
		  FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	      return "dangNhap?faces-redirect=true";
	   }
	
	public String themNhanVien(){
		nhanVienDao.themNhanVien(nhanVien, phanQuyen);
		return "QLNhanVien?faces-redirect=true";
	}
	
	public String suaNhanVien(){
		//sửa nhân viên dựa vào đối tượng nhân viên được chọn
		nhanVienDao.suaNhanVien(selectedNV,selectedPhanQuyen);
		return "QLNhanVien?faces-redirect=true";
	}
	
	public String xoaNhanVien(NhanVien x){
		nhanVienDao.xoaNhanVien(x);
		return "QLNhanVien?faces-redirect=true";
	}
	
	//reset các ô input
	public void reset(){
		nhanVienDao.reset(nhanVien);
	}
	
	//hàm khi bấm vào icon găng cưa sẽ lưu thông tin đối tượng nhân viên được chọn
	public void capNhat(NhanVien x,String maPQ){
		selectedNV=x;
		int ma=Integer.parseInt(maPQ);
		this.setSelectedPhanQuyen(nhanVienDao.layPQ(ma));
	}

	
}
