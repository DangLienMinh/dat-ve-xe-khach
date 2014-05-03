package dao;

import java.util.List;

import hibernateUtil.HibernateUtil;
import model.PhanQuyen;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class PhanQuyenDao {
	
	private PhanQuyen x;
	
	public PhanQuyen layThongTin(PhanQuyen temp){
		PhanQuyen phanQuyen=new PhanQuyen();
		phanQuyen.setQuyen(temp.getQuyen());
		return phanQuyen;
	}
	
	public void themPhanQuyen(PhanQuyen phanQuyen) {
		x=new PhanQuyen();
		x=layThongTin(phanQuyen);  
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
	
	public void suaPhanQuyen(PhanQuyen phanQuyen) {

		
		x=new PhanQuyen();
		x=layThongTin(phanQuyen);  
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

	public List<PhanQuyen> selectItems(){
		List<PhanQuyen> list=null;
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql="From PhanQuyen";
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
}
