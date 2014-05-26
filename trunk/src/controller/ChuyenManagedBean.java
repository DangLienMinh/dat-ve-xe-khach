package controller;


import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Chuyen;
import model.TaiXe;
import model.Tuyen;
import model.Xe;
import dao.ChuyenDao;
import dao.TaiXeDao;
import dao.TuyenDao;
import dao.XeDao;

@ManagedBean(name= "chuyenMBean")
@ViewScoped
public class ChuyenManagedBean {
	
	private Chuyen chuyen=new Chuyen();
	private ChuyenDao chuyenDao=new ChuyenDao();
	private Xe xe=new Xe();
	private XeDao xeDao=new XeDao();
	private Tuyen tuyen=new Tuyen();
	private TuyenDao tuyenDao=new TuyenDao();
	private TaiXe taiXe=new TaiXe();
	private TaiXeDao taiXeDao=new TaiXeDao();
	private Xe selectedXe=new Xe();
	
	//Luu thong tin cac doi tuong duoc chon tren view
	private Tuyen selectedTuyen=new Tuyen();
	private TaiXe selectedTaiXe=new TaiXe();
	private Chuyen selectedChuyen=new Chuyen();
	 
	private List<Chuyen> DanhSach;
	
	//Tra ve danh sach chuyen theo kieu loc thuoc tinh
	private List<Chuyen> filteredDanhSach;  
	
	public ChuyenManagedBean(){
		DanhSach = new ArrayList<Chuyen>();
		DanhSach = chuyenDao.danhSachChuyen();
	}
	public Chuyen getChuyen() {
		return chuyen;
	}
	public void setChuyen(Chuyen chuyen) {
		this.chuyen = chuyen;
	}
	public ChuyenDao getChuyenDao() {
		return chuyenDao;
	}
	public void setChuyenDao(ChuyenDao chuyenDao) {
		this.chuyenDao = chuyenDao;
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
	public List<Chuyen> getDanhSach() {
		return DanhSach;
	}
	public void setDanhSach(List<Chuyen> danhSach) {
		DanhSach = danhSach;
	}
	public List<Chuyen> getFilteredDanhSach() {
		return filteredDanhSach;
	}
	public void setFilteredDanhSach(List<Chuyen> filteredDanhSach) {
		this.filteredDanhSach = filteredDanhSach;
	}
	public Chuyen getSelectedChuyen() {
		return selectedChuyen;
	}
	public void setSelectedChuyen(Chuyen selectedChuyen) {
		this.selectedChuyen = selectedChuyen;
	}
	public Xe getSelectedXe() {
		return selectedXe;
	}
	public void setSelectedXe(Xe selectedXe) {
		this.selectedXe = selectedXe;
	}
	public Tuyen getSelectedTuyen() {
		return selectedTuyen;
	}
	public void setSelectedTuyen(Tuyen selectedTuyen) {
		this.selectedTuyen = selectedTuyen;
	}
	public TaiXe getSelectedTaiXe() {
		return selectedTaiXe;
	}
	public void setSelectedTaiXe(TaiXe selectedTaiXe) {
		this.selectedTaiXe = selectedTaiXe;
	}	
	
	public String themChuyen(){
		chuyenDao.themChuyen(chuyen, taiXe, tuyen, xe);
		return "QLChuyen?faces-redirect=true";
	}
	
	public String suaChuyen(){
		//sua chuyen dua vao doi tuong chuyen duoc chon
		chuyenDao.suaChuyen(selectedChuyen, selectedTaiXe, selectedTuyen, selectedXe);
		return "QLChuyen?faces-redirect=true";
	}
	
	public String xoaChuyen(Chuyen x){
		chuyenDao.xoaChuyen(x);
		return "QLChuyen?faces-redirect=true";
	}
	
	//reset cac o input
	public void reset(){
		chuyenDao.reset(chuyen);
	}
	
	//ham khi bam icon gang cua se cap nhat thong tin dua vao doi tuong chuyen duoc chon
	public void capNhat(Chuyen x,String matx,String bienSo,String maTuyen){
		selectedChuyen=x;
		this.setSelectedXe(xeDao.layXeTheoBienSo(bienSo));
		this.setSelectedTuyen(tuyenDao.layTuyen(maTuyen));
		this.setSelectedTaiXe(taiXeDao.layTX(matx));
	}
}
