package dao;

import hibernateUtil.HibernateUtil;

import java.util.List;

import model.Xe;
import model.LoaiXe;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class XeDao {
	
	//tra ve thong tin doi tuong xe
	public Xe layThongTin(Xe temp){
		Xe xe=new Xe();
		xe.setBienSo(temp.getBienSo());
		return xe;
	}
	
	//them mot doi tuong xe
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
	
	//sua mot doi tuong xe 
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
	 	
	 	//Tra ve doi tuong xe theo BienSo xe
	 	public Xe layXeTheoBienSo(String bienSo){
		    Session session = HibernateUtil.getSessionFactory().openSession();
		    String hql = "from Xe  where BienSo = :bienso";
		    List result = session.createQuery(hql)
		    .setParameter("bienso", bienSo)
		    .list();
	 		//tra ve 1 doi tuong xe => lay phan tu dau tien cua result list
	 		Xe x=(Xe) result.get(0);
	 		return x; 
	 	}
	 	
	 	//tra ve tat ca cac xe trong csdl
	 	public List<Xe> danhSachXe(){
	 		List<Xe> x;
	 		Session session = HibernateUtil.getSessionFactory().openSession();
	 		session.beginTransaction();
	 		Criteria cri=session.createCriteria(Xe.class);
	 		x=cri.list();
	 		return x;
	 	}
	 	
	 	//xoa mot doi tuong xe
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

	 
	 	//reset cac thuoc tinh cua xe
	 public void reset(Xe xe) {
		 xe.setBienSo(" ");
	 }
	 
	 //combobox xe luc chon chuyen xe
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
