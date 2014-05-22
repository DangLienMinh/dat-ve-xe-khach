package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRException;

import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.svcs.services.AdaptivePaymentsService;
import com.paypal.svcs.types.ap.PayRequest;
import com.paypal.svcs.types.ap.PayResponse;
import com.paypal.svcs.types.ap.Receiver;
import com.paypal.svcs.types.ap.ReceiverList;
import com.paypal.svcs.types.common.RequestEnvelope;

import dao.ChuyenDao;
import dao.GheDao;
import dao.HoaDonDao;
import dao.LoaiXeDao;
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
	
	//Ngay toi da, Ngay toi thieu de dat ve (1 thang)
	private Date currentDate;
	private Date maxDate;
	
	private Tuyen tuyen=new Tuyen();
	private Chuyen chuyen=new Chuyen();
	private ChuyenDao chuyenDao=new ChuyenDao();
	private Tuyen dsTuyen=new Tuyen();
	private HoaDon hoaDon=new HoaDon();
	private Xe xe=new Xe();
	private LoaiXe loaiXe=new LoaiXe();
	private XeDao xeDao=new XeDao();
	private LoaiXeDao loaiXeDao=new LoaiXeDao();
	private GheDao gheDao=new GheDao();
	private HoaDonDao hoaDonDao=new HoaDonDao();
	private TuyenDao tuyenDao=new TuyenDao();
	private List<Chuyen> danhSach;
	
	//luu thong tin chon radio button thanh toan truc tiep hay chuyen khoan
	private String option;
	
	//inject controller gheMB vao hoa Don
	@ManagedProperty(value="#{gheMBean}")
	private GheManagedBean gheMB;
	
	//inject controller reportMB vao hoa Don
	@ManagedProperty(value="#{reportMBean}")
	private ReportManagedBean reportMB;
	
	//luu dieu kien lay ve
	private int dieuKienLayVe=-10;

	//max date= thang hien tai+1
	public Date getMaxDate() {
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.MONTH, 1);
	    return cal.getTime();
	}
	
	//current date= ngay hien tai+1
	public Date getCurrentDate() {
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.DATE, 1);
	    return cal.getTime();
	}
	
	public void hienThiChuyen(){
		danhSach=chuyenDao.dsChonChuyen(tuyen);
		dsTuyen=tuyenDao.layTuyen(tuyen.getMaTuyen());		
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
	
	//tinh trang ghe ngoi
	public String datVe(Chuyen ds){
		chuyen=ds;
		Xe x=chuyen.getBienSo();
		xe=xeDao.layXeTheoBienSo(x.getBienSo());
		LoaiXe malx=xe.getMaLoaiXe();
		loaiXe=loaiXeDao.layLX(malx.getMaLoaiXe());
		
		//neu la xe ghe ngoi
		if(loaiXe.getMaLoaiXe()==1){
			
			//kiem tra tinh trang ghe dua vao machuyen+ngay giao dich
			List<Ghe> ttGhe=gheDao.tinhTrangGhe(chuyen.getMaChuyen(),hoaDon.getNgayGD());
			String []ttNgoi=new String[45];
			for(Ghe ghe:ttGhe){
				ttNgoi[ghe.getMaGhe()-1]="true";
			}
			gheMB.setTtNgoi(ttNgoi);
			return "gheNgoi";
		}
		
		//neu la xe giuong nam
		else{
			//kiem tra tinh trang ghe dua vao machuyen+ngay giao dich
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
		//neu chon hinh thuc thanh toan truc tiep
		if(option.equalsIgnoreCase("1")){
			hoaDon.setHinhThucTT("Trực tiếp");
			Tuyen tuyen=new Tuyen();
			tuyen=chuyen.getMaTuyen();
			hoaDon.setTongTien(tuyenDao.tienVe(tuyen.getMaTuyen()));
			int mahd=hoaDonDao.themHoaDon(hoaDon, chuyen, gheMB.getSelectedGhe());
			hoaDon.setMaHD(mahd);
			return "kqDatVe.xhtml";
		}else{//neu chon hinh thuc chuyen khoan
			hoaDon.setHinhThucTT("Chuyển khoản");
			Tuyen tuyen=new Tuyen();
			tuyen=chuyen.getMaTuyen();
			
			//lay tien ve dua vao ma tuyen
			double tienVe=tuyenDao.tienVe(tuyen.getMaTuyen());
			//goi ham paypal
			thanhToanPaypal(tienVe/20000);
			
			hoaDon.setTongTien(tuyenDao.tienVe(tuyen.getMaTuyen()));
			int mahd=hoaDonDao.themHoaDon(hoaDon, chuyen, gheMB.getSelectedGhe());
			hoaDon.setMaHD(mahd);
			return "";
		}
		
	}
	
	//ham thanh toan paypal 
	public void thanhToanPaypal(double amount){
		PayRequest payRequest = new PayRequest();
		  
		List<Receiver> receivers = new ArrayList<Receiver>();
		Receiver receiver = new Receiver();
		receiver.setAmount(amount);
		receiver.setEmail("merchant@QLXK.com");
		receivers.add(receiver);
		ReceiverList receiverList = new ReceiverList(receivers);

		payRequest.setReceiverList(receiverList);

		RequestEnvelope requestEnvelope = new RequestEnvelope("en_US");
		payRequest.setRequestEnvelope(requestEnvelope); 
		payRequest.setActionType("PAY");
		payRequest.setCancelUrl("http://localhost:8080/datVeXe/thongTinDatVe.xhtml");
		payRequest.setReturnUrl("http://localhost:8080/datVeXe/kqDatVe.xhtml");
		payRequest.setCurrencyCode("USD");
		payRequest.setIpnNotificationUrl("http://replaceIpnUrl.com");

		Map<String, String> sdkConfig = new HashMap<String, String>();
		sdkConfig.put("mode", "sandbox");
		sdkConfig.put("acct1.UserName", "merchant_api1.QLXK.com");
		sdkConfig.put("acct1.Password", "1400294553");
		sdkConfig.put("acct1.Signature","ArX6vf1XJgnyPK3-dxT5Ar.JyJD8Ax59Vl6NMBKeAO3-wYJc127l3g0v");
		sdkConfig.put("acct1.AppId","APP-80W284485P519543T");

		AdaptivePaymentsService adaptivePaymentsService = new AdaptivePaymentsService(sdkConfig);
		try {
			PayResponse payResponse = adaptivePaymentsService.pay(payRequest);
			
			String payKey = payResponse.getPayKey();
			String returnUrl="https://www.sandbox.paypal.com/webscr?cmd=_ap-payment&paykey="+payKey;
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

		    ec.redirect(returnUrl);
		} catch (SSLConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidCredentialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidResponseDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientActionRequiredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MissingCredentialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//lay ve chinh thuc
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
	
	public String inVe() throws ClassNotFoundException, SQLException, IOException,JRException{
		return reportMB.inVe(hoaDon.getMaHD());
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
	public ReportManagedBean getReportMB() {
		return reportMB;
	}
	public void setReportMB(ReportManagedBean reportMB) {
		this.reportMB = reportMB;
	}	
}
