package controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.KhuVuc;
import model.VanPhong;
import dao.KhuVucDao;
import dao.VanPhongDao;

@ManagedBean(name= "vanPhongMBean")
@ViewScoped
public class VanPhongManagedBean {
	private VanPhong vanPhong=new VanPhong();
	private VanPhongDao vanPhongDao=new VanPhongDao();
	private KhuVuc khuVuc=new KhuVuc();
	private KhuVucDao khuVucDao=new KhuVucDao();
	
	//Tra ve danh sach vanPhong tren datatable
	private List<VanPhong> DanhSach;
	
	//Tra ve danh sach tuyen tren map 
	private List<VanPhong> DanhSachMap;
	
	//Tra ve danh sach vanPhong theo kieu loc thuoc tinh
	private List<VanPhong> filteredDanhSach;  
	
	//doi tuong vanPhong duoc chon de cap nhat thong tin
	private VanPhong selectedVanPhong=new VanPhong();
	
	//doi tuong loai vanPhong duoc chon de cap nhat thong tin
	private KhuVuc selectedKhuVuc=new KhuVuc();
	
	private boolean showDialog;
	

	public VanPhongManagedBean(){
		DanhSach = new ArrayList<VanPhong>();
		DanhSach = vanPhongDao.danhSachVanPhong();
	}
	
	public VanPhong getVanPhong() {
		return vanPhong;
	}

	public void setVanPhong(VanPhong vanPhong) {
		this.vanPhong = vanPhong;
	}

	public VanPhongDao getVanPhongDao() {
		return vanPhongDao;
	}

	public void setVanPhongDao(VanPhongDao vanPhongDao) {
		this.vanPhongDao = vanPhongDao;
	}

	public KhuVuc getKhuVuc() {
		return khuVuc;
	}

	public void setKhuVuc(KhuVuc khuVuc) {
		this.khuVuc = khuVuc;
	}

	public KhuVucDao getKhuVucDao() {
		return khuVucDao;
	}

	public void setKhuVucDao(KhuVucDao khuVucDao) {
		this.khuVucDao = khuVucDao;
	}

	public List<VanPhong> getDanhSach() {
		return DanhSach;
	}

	public void setDanhSach(List<VanPhong> danhSach) {
		DanhSach = danhSach;
	}

	public List<VanPhong> getFilteredDanhSach() {
		return filteredDanhSach;
	}

	public void setFilteredDanhSach(List<VanPhong> filteredDanhSach) {
		this.filteredDanhSach = filteredDanhSach;
	}

	public VanPhong getSelectedVanPhong() {
		return selectedVanPhong;
	}

	public void setSelectedVanPhong(VanPhong selectedVanPhong) {
		this.selectedVanPhong = selectedVanPhong;
	}

	public KhuVuc getSelectedKhuVuc() {
		return selectedKhuVuc;
	}

	public void setSelectedKhuVuc(KhuVuc selectedKhuVuc) {
		this.selectedKhuVuc = selectedKhuVuc;
	}	
	
	public String themVanPhong(){
		vanPhongDao.themVanPhong(vanPhong, khuVuc);
		return "QLVanPhong?faces-redirect=true";
	}
	
	public String suaVanPhong(){
		//sua vanPhong dua vao doi tuong vanPhong duoc chon
		vanPhongDao.suaVanPhong(selectedVanPhong,selectedKhuVuc);
		return "QLVanPhong?faces-redirect=true";
	}
	
	public String xoaVanPhong(VanPhong x){
		vanPhongDao.xoaVanPhong(x);
		return "QLVanPhong?faces-redirect=true";
	}
	
	//reset các ô input
	public String reset(){
		vanPhongDao.reset(vanPhong);
		return "QLVanPhong?faces-redirect=true";
	}
	
	//ham khi bam vao icon gang cua se cap nhat doi tuong vanPhong
	public void capNhat(VanPhong x,String maLX){
		selectedVanPhong=x;
		int ma=Integer.parseInt(maLX);
		setSelectedKhuVuc(khuVucDao.layKhuVucTheoMaKV(ma));
	}

	public List<VanPhong> getDanhSachMap() {
		return DanhSachMap;
	}

	public void viewCars(int makv) {
		KhuVuc x=khuVucDao.layKhuVucTheoMaKV(makv);
		DanhSachMap=vanPhongDao.layVPTheoKV(x);
		setShowDialog(true);
       // RequestContext.getCurrentInstance().openDialog("thongTinTuyen");
    }

	public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}
}
