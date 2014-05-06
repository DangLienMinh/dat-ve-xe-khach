package dao;

import hibernateUtil.HibernateUtil;
import java.util.List;
import model.Chuyen;
import model.TaiXe;
import model.Tuyen;
import model.Xe;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class ChuyenDao {

	public Chuyen layThongTin(Chuyen temp){
		Chuyen chuyen=new Chuyen();
		chuyen.setGioDi(temp.getGioDi());
		chuyen.setGioDen(temp.getGioDen());
		chuyen.setSoGheTrong(temp.getSoGheTrong());
		chuyen.setMaChuyen(temp.getMaChuyen());
		chuyen.setKhuyenMai(temp.getKhuyenMai());
		return chuyen;
	}
	
	public void themChuyen(Chuyen chuyen,TaiXe taiXe,Tuyen tuyen,Xe xe) {
		Chuyen result = new Chuyen();
		result=layThongTin(chuyen);  
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            TaiXe tx=(TaiXe) session.load(TaiXe.class, taiXe.getMaTX());
            Tuyen t=(Tuyen) session.load(Tuyen.class, tuyen.getMaTuyen());
            Xe x=(Xe) session.load(Xe.class, xe.getBienSo());
            result.setMaTX(tx);
            result.setMaTuyen(t);
            result.setBienSo(x);
            session.save(result);
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
	
	 
	 public void suaChuyen(Chuyen chuyen,TaiXe taiXe,Tuyen tuyen,Xe xe) {
		 	Chuyen result = new Chuyen();
			result=layThongTin(chuyen);  
	        Transaction trns = null;
		    Session session = HibernateUtil.getSessionFactory().openSession();
		    try {
		    	trns = session.beginTransaction();
		        TaiXe tx=(TaiXe) session.load(TaiXe.class, taiXe.getMaTX());
		        Tuyen t=(Tuyen) session.load(Tuyen.class, tuyen.getMaTuyen());
		        Xe x=(Xe) session.load(Xe.class, xe.getBienSo());
		        result.setMaTX(tx);
		        result.setMaTuyen(t);
		        result.setBienSo(x);
		        session.update(result);
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
	 
	
	 	
	 	public TaiXe layTX(int maTX){
	 		Session session = HibernateUtil.getSessionFactory().openSession();
		    String hql = "from TaiXe  where MaTX = :matx";
		    List result = session.createQuery(hql)
		    .setParameter("matx", maTX)
		    .list();
	 		//you should return list of LoaiXe object from this method, so need to create one
	 		TaiXe x=(TaiXe) result.get(0);
	 		return x; //return the list we created
	 	}
	 	
	 	public Tuyen layTuyen(int maTuyen){
	 		Session session = HibernateUtil.getSessionFactory().openSession();
		    String hql = "from Tuyen  where MaTuyen = :matuyen";
		    List result = session.createQuery(hql)
		    .setParameter("matuyen", maTuyen)
		    .list();
	 		//you should return list of LoaiXe object from this method, so need to create one
		    Tuyen x=(Tuyen) result.get(0);
	 		return x; //return the list we created
	 	}
	 	
	 	public Xe layBienSo(String bienSo){
	 		Session session = HibernateUtil.getSessionFactory().openSession();
		    String hql = "from Xe  where BienSo = :bs";
		    List result = session.createQuery(hql)
		    .setParameter("bs", bienSo)
		    .list();
	 		//you should return list of LoaiXe object from this method, so need to create one
		    Xe x=(Xe) result.get(0);
	 		return x; //return the list we created
	 	}
	 	
	 	public List<Chuyen> danhSachNV(){
	 		List<Chuyen> x;
	 		Session session = HibernateUtil.getSessionFactory().openSession();
	 		session.beginTransaction();
	 		Criteria cri=session.createCriteria(Chuyen.class);
	 		x=cri.list();
	 		return x;
	 		
	 	}
	 	
	 	public void xoaChuyen(Chuyen chuyen) {
	        Transaction trns = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        try {
	            trns = session.beginTransaction();
	            Chuyen x = (Chuyen) session.load(Chuyen.class, chuyen.getMaChuyen());
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

	 
	 public void reset(Chuyen chuyen) {
		 chuyen.setKhuyenMai(0);
	 }
}
