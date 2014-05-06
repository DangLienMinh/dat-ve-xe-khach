package dao;

import java.util.Iterator;
import java.util.List;
import hibernateUtil.HibernateUtil;
import model.TaiXe;
import org.hibernate.Criteria;
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
	
	public List<TaiXe> danhSachTX(){
 		List<TaiXe> x;
 		Session session = HibernateUtil.getSessionFactory().openSession();
 		session.beginTransaction();
 		Criteria cri=session.createCriteria(TaiXe.class);
 		x=cri.list();
 		return x;
 	}
	
	public void reset(TaiXe taiXe) {
		taiXe.setHo(" ");
		taiXe.setTen(" ");
		taiXe.setCMND(" ");
		taiXe.setDiaChi(" ");
		taiXe.setDienThoai(" ");
		taiXe.setEmail(" ");
	}
	
	public List<TaiXe> selectItems(){
		List<TaiXe> list=null;
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql="From TaiXe";
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