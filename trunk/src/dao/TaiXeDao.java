package dao;

import java.util.Iterator;
import java.util.List;

import hibernateUtil.HibernateUtil;
import model.TaiXe;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TaiXeDao {
	
	private TaiXe x;

	public TaiXe layThongTin(TaiXe temp){
		TaiXe taiXe=new TaiXe();
		taiXe.setHo(temp.getHo());
		taiXe.setTen(temp.getTen());
		taiXe.setDiaChi(temp.getDiaChi());
		taiXe.setCMND(temp.getCMND());
		taiXe.setDienThoai(temp.getDienThoai());
		taiXe.setEmail(temp.getEmail());
		taiXe.setNgaySinh(temp.getNgaySinh());
		taiXe.setMaTX(temp.getMaTX());
		return taiXe;
	}
	
	public void themTaiXe(TaiXe taiXe) {
		x = new TaiXe();
		x=layThongTin(taiXe);
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(x);
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
	
	 public void suaTaiXe(TaiXe taiXe) { 
		 x = new TaiXe();
		 x=layThongTin(taiXe);
	    Transaction trns = null;
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        trns = session.beginTransaction();
	        session.update(x);
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
	
	public void xoaTaiXe(TaiXe taiXe) {
		 x = new TaiXe();
		 x=layThongTin(taiXe);
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            //TaiXe cust = (TaiXe) session.load(TaiXe.class, new String(maTX));
            session.delete(x);
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
	
//	public void listEmployees(){
//		Session session = HibernateUtil.getSessionFactory().openSession();
//	      Transaction tx = null;
//	      try{
//	         tx = session.beginTransaction();
//	         List employees = session.createQuery("FROM TaiXe").list(); 
//	         for (Iterator iterator1 = 
//	                           employees.iterator(); iterator1.hasNext();){
//	        	TaiXe taiXe = (TaiXe) iterator1.next(); 
//	            System.out.print("First Name: " + taiXe.getHoTen()); 
//	            System.out.print("  Last Name: " + taiXe.getDiaChi()); 
//	            System.out.println("  Salary: " + taiXe.getMaTX());  
//	         }
//	         tx.commit();
//	      }catch (HibernateException e) {
//	         if (tx!=null) tx.rollback();
//	         e.printStackTrace(); 
//	      }finally {
//	    	  session.flush();
//	         session.close(); 
//	      }
//	   }
	
	public void reset(TaiXe taiXe) {
		taiXe.setHo(" ");
		taiXe.setTen(" ");
		taiXe.setCMND(" ");
		taiXe.setDiaChi(" ");
		taiXe.setDienThoai(" ");
		taiXe.setEmail(" ");
	}

}
