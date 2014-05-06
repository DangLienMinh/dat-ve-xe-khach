package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.TaiKhoanDao;
import model.NhanVien;

@ManagedBean(name= "taiKhoanMBean")
@ViewScoped 
public class TaiKhoanManagedBean {
	private NhanVien ttCaNhan=new NhanVien();
	private TaiKhoanDao taiKhoanDao=new TaiKhoanDao();
	private String matKhau;
	private String matKhauMoi;
	
	public TaiKhoanManagedBean(){
		FacesContext context = FacesContext.getCurrentInstance();
		String tendn=  context.getExternalContext().getSessionMap().get("tendn").toString();
		setTtCaNhan(taiKhoanDao.ttCaNhan(tendn));
	}
	
	public NhanVien getTtCaNhan() {
		return ttCaNhan;
	}

	public void setTtCaNhan(NhanVien ttCaNhan) {
		this.ttCaNhan = ttCaNhan;
	}	
	
	public String suaTaiKhoan(){
		//sửa nhân viên dựa vào đối tượng nhân viên được chọn
		taiKhoanDao.suaTaiKhoan(ttCaNhan);
		return "QLTaiKhoan?faces-redirect=true";
	}
	
	public String suaMatKhau(){
		if(matKhau.equalsIgnoreCase(ttCaNhan.getMatKhau())){
			taiKhoanDao.suaMatKhau(ttCaNhan,matKhauMoi);
		}
		//sửa nhân viên dựa vào đối tượng nhân viên được chọn
		
		return "QLTaiKhoan?faces-redirect=true";
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
