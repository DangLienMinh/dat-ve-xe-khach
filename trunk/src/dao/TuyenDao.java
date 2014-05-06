package dao;

import hibernateUtil.HibernateUtil;

import java.util.List;
import model.Tuyen;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TuyenDao {
	private Tuyen x;
	public Tuyen layThongTin(Tuyen temp){
		Tuyen tuyen=new Tuyen();
		tuyen.setGia(temp.getGia());
		tuyen.setBenCuoi(temp.getBenCuoi());
		tuyen.setBenDau(temp.getBenDau());
		tuyen.setTenTuyen(temp.getTenTuyen());
		tuyen.setMaTuyen(temp.getMaTuyen());
		return tuyen;
	}
	
	public void themTuyen(Tuyen tuyen) {
		x = new Tuyen();
		x=layThongTin(tuyen);
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
	
	public void xoaTuyen(Tuyen tuyen) {
		 x = new Tuyen();
		 x=layThongTin(tuyen);
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            //Tuyen cust = (Tuyen) session.load(Tuyen.class, new String(maTX));
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
	
	public List<Tuyen> danhSachTX(){
 		List<Tuyen> x;
 		Session session = HibernateUtil.getSessionFactory().openSession();
 		session.beginTransaction();
 		Criteria cri=session.createCriteria(Tuyen.class);
 		x=cri.list();
 		return x;
 	}
	
	public void reset(Tuyen tuyen) {
		tuyen.setGia(0);
		tuyen.setBenCuoi(" ");
		tuyen.setBenDau(" ");
		tuyen.setTenTuyen(" ");
	}
	
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

	public String tenTuyen(int maTuyen) {
		Session session = HibernateUtil.getSessionFactory().openSession();
	    String hql = "from Tuyen  where MaTuyen = :matuyen";
	    List result = session.createQuery(hql)
	    .setParameter("matuyen", maTuyen)
	    .list();
 		//you should return list of LoaiXe object from this method, so need to create one
 		Tuyen x=(Tuyen) result.get(0);
 		return x.getBenCuoi()+" - "+x.getBenCuoi(); //return the list we created
	}

}
