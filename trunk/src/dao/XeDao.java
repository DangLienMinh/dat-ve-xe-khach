package dao;

import hibernateUtil.HibernateUtil;

import java.util.List;
import model.Xe;
import model.LoaiXe;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class XeDao {
	public Xe layThongTin(Xe temp){
		Xe xe=new Xe();
		xe.setBienSo(temp.getBienSo());
		xe.setSoGhe(temp.getSoGhe());
		return xe;
	}
	
	public void themXe(Xe xe,LoaiXe loaiXe) {
		Xe x = new Xe();
		x=layThongTin(xe);  
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            LoaiXe lx=(LoaiXe) session.load(LoaiXe.class, loaiXe.getMaLoaiXe());
            x.setMaLoaiXe(lx);
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
	
	 
	 public void suaXe(Xe xe,LoaiXe loaiXe) {
			Xe x = new Xe();
			x=layThongTin(xe);  
		    Transaction trns = null;
		    Session session = HibernateUtil.getSessionFactory().openSession();
		    try {
		        trns = session.beginTransaction();
		        LoaiXe lx=(LoaiXe) session.load(LoaiXe.class, loaiXe.getMaLoaiXe());
	            x.setMaLoaiXe(lx);
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
	 	
	 	public LoaiXe layLX(int maLoaiXe){		
		    Session session = HibernateUtil.getSessionFactory().openSession();
		    String hql = "from LoaiXe  where MaLoaiXe = :maLoaiXe";
		    List result = session.createQuery(hql)
		    .setParameter("maLoaiXe", maLoaiXe)
		    .list();
	 		//you should return list of LoaiXe object from this method, so need to create one
	 		LoaiXe x=(LoaiXe) result.get(0);
	 		return x; //return the list we created
	 	}
	 	
	 	public List<Xe> danhSachXe(){
	 		List<Xe> x;
	 		Session session = HibernateUtil.getSessionFactory().openSession();
	 		session.beginTransaction();
	 		Criteria cri=session.createCriteria(Xe.class);
	 		x=cri.list();
	 		return x;
	 		
	 	}
	 	
	 	public void xoaXe(Xe xe) {
	        Transaction trns = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        try {
	            trns = session.beginTransaction();
	            Xe x = (Xe) session.load(Xe.class, xe.getBienSo());
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

	 
	 public void reset(Xe xe) {
		 xe.setSoGhe(0);
		 xe.setBienSo(" ");
	 }
	 
	 public List<Xe> selectItems(){
			List<Xe> list=null;
			Transaction trns = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        String sql="From Xe";
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
