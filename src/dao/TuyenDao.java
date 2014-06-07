package dao;

import hibernateUtil.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import model.Tuyen;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

public class TuyenDao {
	private Tuyen x;
	
	//lay thong tin tuyen theo 1 doi tuong tuyen
	public Tuyen layThongTin(Tuyen temp){
		Tuyen tuyen=new Tuyen();
		tuyen.setGia(temp.getGia());
		tuyen.setBenCuoi(temp.getBenCuoi());
		tuyen.setBenDau(temp.getBenDau());
		tuyen.setTenTuyen(temp.getTenTuyen());
		tuyen.setMaTuyen(temp.getMaTuyen());
		return tuyen;
	}
	
	//them tuyen
	public void themTuyen(Tuyen tuyen) {
		x = new Tuyen();
		x=layThongTin(tuyen);
		x.setMaTuyen("");
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
	
	//sua tuyen
	 public void suaTuyen(Tuyen tuyen) { 
		 x = new Tuyen();
		 x=layThongTin(tuyen);
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
	
	 //xoa tuyen
	public void xoaTuyen(Tuyen tuyen) {
		 x = new Tuyen();
		 x=layThongTin(tuyen);
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
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
	
	//tra ve danh sach tueyn
	public List<Tuyen> danhSachTuyen(){
 		List<Tuyen> x;
 		Session session = HibernateUtil.getSessionFactory().openSession();
 		session.beginTransaction();
 		Criteria cri=session.createCriteria(Tuyen.class).addOrder(Order.asc("TenTuyen"));
 		x=cri.list();
 		return x;
 	}
	
	//xoa text box 
	public void reset(Tuyen tuyen) {
		tuyen.setGia(0);
		tuyen.setBenCuoi(" ");
		tuyen.setBenDau(" ");
		tuyen.setTenTuyen(" ");
	}
	
	//tra ve mot combo box chon tuyen tren giao dien chuyen
	public List<Tuyen> selectItems(){
		List<Tuyen> list=null;
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql="From Tuyen";
        try {
        	trns=session.beginTransaction();
        	list = session.createQuery(sql).list();
			
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
		return list;
	}

	//lay ten tuyen theo ma tuyen
	public String tenTuyen(String maTuyen) {
		Session session = HibernateUtil.getSessionFactory().openSession();
	    String hql = "from Tuyen  where MaTuyen = :matuyen";
	    List result = session.createQuery(hql)
	    .setParameter("matuyen", maTuyen)
	    .list();
	  //tra ve 1 doi tuong Tuyen => lay phan tu dau tien cua result list
 		Tuyen x=(Tuyen) result.get(0);
 		return x.getBenDau()+" - "+x.getBenCuoi();
	}
	
	//lay thong tin tuyen dua vao ma tuyen
 	public Tuyen layTuyen(String maTuyen){
 		Session session = HibernateUtil.getSessionFactory().openSession();
	    String hql = "from Tuyen  where MaTuyen = :matuyen";
	    List result = session.createQuery(hql)
	    .setParameter("matuyen", maTuyen)
	    .list();
	  //tra ve 1 doi tuong Tuyen => lay phan tu dau tien cua result list
	    Tuyen x=(Tuyen) result.get(0);
 		return x;
 	}
 	
 	
 	
 	//lay thong tin tien ve dua vao matuyen
 	public int tienVe(String maTuyen){
 		Session session = HibernateUtil.getSessionFactory().openSession();
	    String hql = "from Tuyen  where MaTuyen = :matuyen";
	    List result = session.createQuery(hql)
	    .setParameter("matuyen", maTuyen)
	    .list();
	  //tra ve 1 doi tuong Tuyen => lay phan tu dau tien cua result list
	    Tuyen x=(Tuyen) result.get(0);
 		return x.getGia(); 
 	}

}
