package controller;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.TaiXe;
import hibernateUtil.HibernateUtil;

@ManagedBean(name= "taiXeMBean")
@SessionScoped
public class TaiXeManagedBean {
	private String MaTX;
	private String HoTen; 
	private String CMND;   
	private String DiaChi;  
	private String Email;  
	private String DienThoai;  
	private Date NgaySinh;
	
	public String getMaTX() {
		return MaTX;
	}
	public void setMaTX(String maTX) {
		MaTX = maTX;
	}
	public String getHoTen() {
		return HoTen;
	}
	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}
	public String getCMND() {
		return CMND;
	}
	public void setCMND(String cMND) {
		CMND = cMND;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getDienThoai() {
		return DienThoai;
	}
	public void setDienThoai(String dienThoai) {
		DienThoai = dienThoai;
	}
	public Date getNgaySinh() {
		return NgaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}
	
//	public String them() {
//		  String result = null;
//		  Session session = HibernateUtil.getSessionFactory().openSession();
//
//		  TaiXe taiXe = new TaiXe();
//		  taiXe.setHoTen(this.getHoTen());
//		  taiXe.setDiaChi(this.getDiaChi());
//		  taiXe.setCMND(this.getCMND());
//		  taiXe.setDienThoai(this.getDienThoai());
//		  this.setEmail(this.getEmail());
//		  this.setNgaySinh(this.getNgaySinh());
//		  this.setMaTX(this.getMaTX());
//
//		  Transaction tx = null;
//
//		  try {
//			   tx = session.beginTransaction();
//			   session.save(taiXe);
//			   tx.commit();
//			   result = "Thêm thành công";
//		  } catch (Exception e) {
//			  if (tx != null) {
//			    tx.rollback();
//			    result = "Lỗi";
//			    e.printStackTrace();
//			  }
//		  } finally {
//			  	session.close();
//		  }
//		  return result;
//	}
//	
	public TaiXe layThongTin(){
		TaiXe taiXe=new TaiXe();
		taiXe.setHoTen(this.getHoTen());
		taiXe.setDiaChi(this.getDiaChi());
		taiXe.setCMND(this.getCMND());
		taiXe.setDienThoai(this.getDienThoai());
		taiXe.setEmail(this.getEmail());
		taiXe.setNgaySinh(this.getNgaySinh());
		taiXe.setMaTX(this.getMaTX());
		return taiXe;
	}
	
	public void themTaiXe() {
		TaiXe taiXe = new TaiXe();
		taiXe=layThongTin();  
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(taiXe);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
	
	 public void suaTaiXe() {
		TaiXe taiXe = new TaiXe();
		taiXe=layThongTin();  
	    Transaction trns = null;
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        trns = session.beginTransaction();
	        session.update(taiXe);
	        session.getTransaction().commit();
	    } catch (RuntimeException e) {
	        if (trns != null) {
	            trns.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        session.flush();
	        session.close();
	    }
	}
	
	public void xoaTaiXe() {
		String maTX=this.getMaTX();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            TaiXe cust = (TaiXe) session.load(TaiXe.class, new String(maTX));
            session.delete(cust);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

	public List<TaiXe> getTaiXe() {
		  Session session = HibernateUtil.getSessionFactory().openSession();
		  List<TaiXe>  userList = session.createCriteria(TaiXe.class).list();
		  return userList;
	}

	public void reset() {
		  this.setHoTen(" ");
		  this.setCMND(" ");
		  this.setDiaChi(" ");
		  this.setDienThoai(" ");
		  this.setEmail(" ");
	}
}
