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
import org.hibernate.criterion.Order;


public class ChuyenDao {

	//tra ve thong tin chuyen
	public Chuyen layThongTin(Chuyen temp){
		Chuyen chuyen=new Chuyen();
		chuyen.setGioDi(temp.getGioDi());
		chuyen.setGioDen(temp.getGioDen());
		chuyen.setMaChuyen(temp.getMaChuyen());
		chuyen.setKhuyenMai(temp.getKhuyenMai());
		return chuyen;
	}
	
	//them 1 chuyen
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
            result.setMaChuyen("");
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
	
	 //sua thong tin chuyen
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
	 
	 	//tra ve danh sach chuyen
	 	public List<Chuyen> danhSachChuyen(){
	 		List<Chuyen> x;
	 		Session session = HibernateUtil.getSessionFactory().openSession();
	 		session.beginTransaction();
	 		Criteria cri=session.createCriteria(Chuyen.class).addOrder(Order.asc("MaChuyen"));
	 		x=cri.list();
	 		return x;
	 	}
	 	
	 	//xoa mot chuyen
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
	 	
	 	//ds chon chuyen theo ma tuyen
	 	public List<Chuyen> dsChonChuyen(Tuyen tuyen) {
	 		Session session = HibernateUtil.getSessionFactory().openSession();
		    String hql = "from Chuyen  where MaTuyen = :matuyen";
		    List result = session.createQuery(hql)
		    .setParameter("matuyen", tuyen)
		    .list();
	 		return result; 
	    }
 
		 public void reset(Chuyen chuyen) {
			 chuyen.setKhuyenMai(0);
		 }
		 
		//lay thong tin tien khuyen mai dua vao matuyen
		 	public int tienKhuyenMai(String maChuyen){
		 		Session session = HibernateUtil.getSessionFactory().openSession();
			    String hql = "from Chuyen  where MaChuyen = :machuyen";
			    List result = session.createQuery(hql)
			    .setParameter("machuyen", maChuyen)
			    .list();
			  //tra ve 1 doi tuong Chuyen => lay phan tu dau tien cua result list
			    Chuyen x=(Chuyen) result.get(0);
		 		return x.getKhuyenMai(); 
		 	}
}
