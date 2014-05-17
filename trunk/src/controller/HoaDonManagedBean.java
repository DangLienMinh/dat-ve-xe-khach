package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import dao.ChuyenDao;
import dao.GheDao;
import dao.HoaDonDao;
import dao.TuyenDao;
import dao.XeDao;
import model.Chuyen;
import model.Ghe;
import model.HoaDon;
import model.LoaiXe;
import model.Tuyen;
import model.Xe;
@ManagedBean(name= "hoaDonMBean")
@SessionScoped
public class HoaDonManagedBean {
	private Date currentDate;
	private Date maxDate;
	private Tuyen tuyen=new Tuyen();
	private Chuyen chuyen=new Chuyen();
	private ChuyenDao chuyenDao=new ChuyenDao();
	private List<Chuyen> danhSach;
	private Tuyen dsTuyen=new Tuyen();
	private HoaDon hoaDon=new HoaDon();
	private String option;
	private Xe xe=new Xe();
	private LoaiXe loaiXe=new LoaiXe();
	private XeDao xeDao=new XeDao();
	@ManagedProperty(value="#{gheMBean}")
	private GheManagedBean gheMB;
	private GheDao gheDao=new GheDao();
	private HoaDonDao hoaDonDao=new HoaDonDao();
	private int dieuKienLayVe=-10;

	public Date getMaxDate() {
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.MONTH, 1);
	    return cal.getTime();
	}
	public Date getCurrentDate() {
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.DATE, 1);
	    return cal.getTime();
	}
	
	public void hienThiChuyen(){
		danhSach=chuyenDao.dsChonChuyen(tuyen);
		dsTuyen=chuyenDao.layTuyen(tuyen.getMaTuyen());
		
	}
	public Tuyen getTuyen() {
		return tuyen;
	}
	public void setTuyen(Tuyen tuyen) {
		this.tuyen = tuyen;
	}
	public List<Chuyen> getDanhSach() {
		return danhSach;
	}
	public void setDanhSach(List<Chuyen> danhSach) {
		this.danhSach = danhSach;
	}
	public Tuyen getDsTuyen() {
		return dsTuyen;
	}
	public void setDsTuyen(Tuyen dsTuyen) {
		this.dsTuyen = dsTuyen;
	}
	
	//tnh trang ghe ngoi
	public String datVe(Chuyen ds){
		chuyen=ds;
		Xe x=chuyen.getBienSo();
		xe=xeDao.layXeTheoBienSo(x.getBienSo());
		LoaiXe malx=xe.getMaLoaiXe();
		loaiXe=xeDao.layLX(malx.getMaLoaiXe());
		
		if(loaiXe.getMaLoaiXe()==1){
			List<Ghe> ttGhe=gheDao.tinhTrangGhe(chuyen.getMaChuyen(),hoaDon.getNgayGD());
			String []ttNgoi=new String[45];
			for(Ghe ghe:ttGhe){
				ttNgoi[ghe.getMaGhe()-1]="true";
			}
			gheMB.setTtNgoi(ttNgoi);
			return "gheNgoi";
		}
		else{
			List<Ghe> ttGhe=gheDao.tinhTrangGhe(chuyen.getMaChuyen(),hoaDon.getNgayGD());
			String []ttNam=new String[40];
			for(Ghe ghe:ttGhe){
				ttNam[ghe.getMaGhe()-1]="true";
			}
			gheMB.setTtNam(ttNam);
			return "giuongNam";
		}	
	}
	
	public String datve(){
		if(option.equalsIgnoreCase("1")){
			hoaDon.setHinhThucTT("Trực tiếp");
		}else{
			hoaDon.setHinhThucTT("Chuyển khoản");
		}
		Tuyen tuyen=new Tuyen();
		tuyen=chuyen.getMaTuyen();
		hoaDon.setTongTien(chuyenDao.tienVe(tuyen.getMaTuyen()));
		int mahd=hoaDonDao.themHoaDon(hoaDon, chuyen, gheMB.getSelectedGhe());
		hoaDon.setMaHD(mahd);
		return "kqDatVe.xhtml";
	}
	
	public String layVeChinhThuc(){
		//kiem tra ma hoa don co ton tai hay ko
		HoaDon x=hoaDonDao.layHD(hoaDon.getMaHD());
		//neu ton tai
		if(x.getMaHD()!=0){
			//kiem tra da in ve chua neu chua in thi ngaynhanve se la null
			if(x.getNgayNhanVe()==null){
				Date ngayNhanVe=Calendar.getInstance().getTime();
				x.setNgayNhanVe(ngayNhanVe);
				hoaDonDao.capNhatNgayNhanVe(x);
				hoaDon=x;
				chuyen=hoaDon.getMaChuyen();
				xe=chuyen.getBienSo();
				dieuKienLayVe=1;
				return "layVe?faces-redirect=true";
			}
			//neu da in roi thi bao loi
			else{
				hoaDon=x;
				dieuKienLayVe=0;
				return "layVe?faces-redirect=true";
				
			}
		}
		//neu khong ton tai hoa don tren thi bao loi
		else{
			dieuKienLayVe=-1;
			return "layVe?faces-redirect=true";
		}
		
		
	}
	
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	public Chuyen getChuyen() {
		return chuyen;
	}
	public void setChuyen(Chuyen chuyen) {
		this.chuyen = chuyen;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public LoaiXe getLoaiXe() {
		return loaiXe;
	}
	public void setLoaiXe(LoaiXe loaiXe) {
		this.loaiXe = loaiXe;
	}
	public Xe getXe() {
		return xe;
	}
	public void setXe(Xe xe) {
		this.xe = xe;
	}
	public GheManagedBean getGheMB() {
		return gheMB;
	}
	public void setGheMB(GheManagedBean gheMB) {
		this.gheMB = gheMB;
	}
	public GheDao getGheDao() {
		return gheDao;
	}
	public void setGheDao(GheDao gheDao) {
		this.gheDao = gheDao;
	}
	public int getDieuKienLayVe() {
		return dieuKienLayVe;
	}
	public void setDieuKienLayVe(int dieuKienLayVe) {
		this.dieuKienLayVe = dieuKienLayVe;
	}
	
	
	
}
