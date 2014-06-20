package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.NhanVienDao;
import dao.TaiKhoanDao;
import model.NhanVien;
import model.PhanQuyen;

@ManagedBean(name= "taiKhoanMBean")
@ViewScoped 
public class TaiKhoanManagedBean {
	
	private NhanVien ttCaNhan;
	private TaiKhoanDao taiKhoanDao=new TaiKhoanDao();
	private String matKhau;
	private String matKhauMoi;
	
	public TaiKhoanManagedBean(){
		FacesContext context = FacesContext.getCurrentInstance();
		String manv=  context.getExternalContext().getSessionMap().get("manv").toString();
		setTtCaNhan(taiKhoanDao.ttCaNhan(manv));
	}
	
	public NhanVien getTtCaNhan() {
		return ttCaNhan;
	}

	public void setTtCaNhan(NhanVien ttCaNhan) {
		this.ttCaNhan = ttCaNhan;
	}	
	
	//kiem tra quyen dn de forward ve dung trang cho nguoi dung
	public int kiemTraquyenDN(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	     NhanVien x= new NhanVien();
	     x.setMaNV(session.getAttribute("manv").toString());
	     NhanVienDao nvDao=new NhanVienDao();
	     NhanVien nv=nvDao.layNhanVien(x);
		 PhanQuyen pq=nv.getMaPQ();
		 return pq.getMaPQ();
	}		
	
	public String suaTaiKhoan(){
		//sua nhan vien dua vao doi tuong nhan vien duoc chon
		taiKhoanDao.suaTaiKhoan(ttCaNhan);
		
		//kiem tra quyen dang nhap
		int kt=kiemTraquyenDN();
		if(kt==1){
			return "QLTaiKhoan_Admin?faces-redirect=true";
		}else if(kt==3){
			return "QLTaiKhoan_NV?faces-redirect=true";
		}else{
			return "QLTaiKhoan_NVDH?faces-redirect=true";
		}
	}
	
	public String suaMatKhau(){
		if(matKhau.equalsIgnoreCase(ttCaNhan.getMatKhau())){
			taiKhoanDao.suaMatKhau(ttCaNhan,matKhauMoi);
		}
		//sua mat khau nhan vien
		int kt=kiemTraquyenDN();
		if(kt==1){
			return "QLTaiKhoan_Admin?faces-redirect=true";
		}else if(kt==3){
			return "QLTaiKhoan_NV?faces-redirect=true";
		}else{
			return "QLTaiKhoan_NVDH?faces-redirect=true";
		}
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getMatKhauMoi() {
		return matKhauMoi;
	}

	public void setMatKhauMoi(String matKhauMoi) {
		this.matKhauMoi = matKhauMoi;
	}
}
