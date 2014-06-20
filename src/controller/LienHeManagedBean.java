package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.LienHe;
import dao.LienHeDao;

@ManagedBean(name= "lienHeMBean")
@ViewScoped
public class LienHeManagedBean {
	private  LienHe lienHe=new LienHe();
	private LienHeDao lienHeDao=new LienHeDao();
	
	//Tra ve danh sach lienHe tren datatable
	private List<LienHe> DanhSach;
	
	//Tra ve danh sach lienHe theo kieu loc thuoc tinh
	private List<LienHe> filteredDanhSach;  
	
	//doi tuong lienHe duoc chon de cap nhat thong tin
	private LienHe selectedLienHe=new LienHe();
	
	private String noiDungTL;
	
	public LienHeManagedBean(){
		DanhSach = new ArrayList<LienHe>();
		DanhSach = lienHeDao.danhSachLienHe();
	}
	
	public String themLienHe(){
		lienHe.setTinhTrang(0);
		lienHe.setNgayGui(new Date());
		lienHeDao.themLienHe(lienHe);
		lienHeDao.reset(lienHe);
		return "";
	}
	
	//ham khi bam vao icon rang cua se luu thong tin doi tuong nhan vien duoc chon
		public void capNhat(LienHe x){
			selectedLienHe=x;
	}
	
	//reset các ô input
	public String reset(){
		lienHeDao.reset(lienHe);
		return "QLLienHe?faces-redirect=true";
	}
	
		public LienHe getLienHe() {
			return lienHe;
		}

		public void setLienHe(LienHe lienHe) {
			this.lienHe = lienHe;
		}

		public LienHeDao getLienHeDao() {
			return lienHeDao;
		}

		public void setLienHeDao(LienHeDao lienHeDao) {
			this.lienHeDao = lienHeDao;
		}

		public List<LienHe> getDanhSach() {
			return DanhSach;
		}

		public void setDanhSach(List<LienHe> danhSach) {
			DanhSach = danhSach;
		}

		public List<LienHe> getFilteredDanhSach() {
			return filteredDanhSach;
		}

		public void setFilteredDanhSach(List<LienHe> filteredDanhSach) {
			this.filteredDanhSach = filteredDanhSach;
		}

		public LienHe getSelectedLienHe() {
			return selectedLienHe;
		}
		
		public String guiMail(){
			lienHeDao.suaLienHe(selectedLienHe);
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
	 
			Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						//thong tin email dung de gui mail di noi khac
						return new PasswordAuthentication("danglienminh@gmail.com","danglienminh");
					}
				});
	 
			try {
	 
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("danglienminh@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(selectedLienHe.getEmail()));
				message.setSubject("Trả lời thư");
				message.setText(noiDungTL);
				Transport.send(message);
				System.out.println("Done");
				return "QLLienHe?faces-redirect=true";
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}

		public String getNoiDungTL() {
			return noiDungTL;
		}

		public void setNoiDungTL(String noiDungTL) {
			this.noiDungTL = noiDungTL;
		}
}
