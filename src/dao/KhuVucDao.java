package dao;

import hibernateUtil.HibernateUtil;

import java.util.List;

import model.KhuVuc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

public class KhuVucDao {
	//tra ve thong tin doi tuong khuVuc
		public KhuVuc layThongTin(KhuVuc temp){
			KhuVuc khuVuc=new KhuVuc();
			khuVuc.setTenKV(temp.getTenKV());
			khuVuc.setMaKV(temp.getMaKV());
			return khuVuc;
		}
		
		//them mot doi tuong khuVuc
		public void themKhuVuc(KhuVuc khuVuc) {
			KhuVuc x = new KhuVuc();
			x=layThongTin(khuVuc);  
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
		
		//sua mot doi tuong khuVuc 
		 public void suaKhuVuc(KhuVuc khuVuc) {
				KhuVuc x = new KhuVuc();
				x=layThongTin(khuVuc);  
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
		 	
		 	//Tra ve doi tuong khuVuc theo MaKV khuVuc
		 	public KhuVuc layKhuVucTheoMaKV(int maKV){
			    Session session = HibernateUtil.getSessionFactory().openSession();
			    String hql = "from KhuVuc  where MaKV = :maKV";
			    List result = session.createQuery(hql)
			    .setParameter("maKV", maKV)
			    .list();
		 		//tra ve 1 doi tuong khuVuc => lay phan tu dau tien cua result list
		 		KhuVuc x=(KhuVuc) result.get(0);
		 		return x; 
		 	}
		 	
//		 	//Tra ve doi tuong khuVuc theo MaKV khuVuc
//		 	public KhuVuc layKhuVucTheoTenKV(String tenKV){
//			    Session session = HibernateUtil.getSessionFactory().openSession();
//			    String hql = "from KhuVuc  where TenKV = :tenkv";
//			    List result = session.createQuery(hql)
//			    .setParameter("tenkv", tenKV)
//			    .list();
//		 		//tra ve 1 doi tuong khuVuc => lay phan tu dau tien cua result list
//		 		KhuVuc x=(KhuVuc) result.get(0);
//		 		return x; 
//		 	}
		 	
		 	//tra ve tat ca cac khuVuc trong csdl
		 	public List<KhuVuc> danhSachKhuVuc(){
		 		List<KhuVuc> x;
		 		Session session = HibernateUtil.getSessionFactory().openSession();
		 		session.beginTransaction();
		 		Criteria cri=session.createCriteria(KhuVuc.class).addOrder(Order.asc("TenKV"));
		 		x=cri.list();
		 		return x;
		 	}
		 	
		 	//xoa mot doi tuong khuVuc
		 	public void xoaKhuVuc(KhuVuc khuVuc) {
		        Transaction trns = null;
		        Session session = HibernateUtil.getSessionFactory().openSession();
		        try {
		            trns = session.beginTransaction();
		            KhuVuc x = (KhuVuc) session.load(KhuVuc.class, khuVuc.getMaKV());
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

		 
		 	//reset cac thuoc tinh cua khuVuc
		 public void reset(KhuVuc khuVuc) {
			 khuVuc.setTenKV(" ");
		 }
		 
		 //combobox khuVuc luc chon chuyen khuVuc
		 public List<KhuVuc> selectItems(){
			List<KhuVuc> list=null;
			Transaction trns = null;
		    Session session = HibernateUtil.getSessionFactory().openSession();
		        String sql="From KhuVuc";
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
