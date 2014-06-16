package dao;

import hibernateUtil.HibernateUtil;

import java.util.List;

import model.LienHe;
import model.Tuyen;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LienHeDao {
	//tra ve thong tin doi tuong lienHe
		public LienHe layThongTin(LienHe temp){
			LienHe lienHe=new LienHe();
			lienHe.setEmail(temp.getEmail());
			lienHe.setId(temp.getId());
			lienHe.setNgayGui(temp.getNgayGui());
			lienHe.setNoiDung(temp.getNoiDung());
			lienHe.setTinhTrang(temp.getTinhTrang());
			return lienHe;
		}
		
		//them mot doi tuong lienHe
		public void themLienHe(LienHe lienHe) {
			LienHe x = new LienHe();
			x=layThongTin(lienHe);  
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
		
		 	//Tra ve doi tuong lienHe theo BienSo lienHe
		 	public LienHe layLienHeTheoMa(int id){
			    Session session = HibernateUtil.getSessionFactory().openSession();
			    String hql = "from LienHe  where ID = :id";
			    List result = session.createQuery(hql)
			    .setParameter("id", id)
			    .list();
		 		//tra ve 1 doi tuong lienHe => lay phan tu dau tien cua result list
		 		LienHe x=(LienHe) result.get(0);
		 		return x; 
		 	}
		 	
		 	public List<LienHe> danhSachLienHe(){
		 		Session session = HibernateUtil.getSessionFactory().openSession();
			    String hql = "from LienHe  where TinhTrang=0";
			    List result = session.createQuery(hql)
			    .list();
			    return result;
		 	}
		 	
		 	
		 	//tra ve tat ca cac lienHe trong csdl
//		 	public List<LienHe> danhSachLienHe(){
//		 		List<LienHe> x;
//		 		Session session = HibernateUtil.getSessionFactory().openSession();
//		 		session.beginTransaction();
//		 		Criteria cri=session.createCriteria(LienHe.class);
//		 		x=cri.list();
//		 		return x;
//		 	}
		 	
		 	//xoa mot doi tuong lienHe
//		 	public void xoaLienHe(LienHe lienHe) {
//		        Transaction trns = null;
//		        Session session = HibernateUtil.getSessionFactory().openSession();
//		        try {
//		            trns = session.beginTransaction();
//		            LienHe x = (LienHe) session.load(LienHe.class, lienHe.getId());
//		            session.delete(x);
//		            session.getTransaction().commit();
//		        } catch (RuntimeException e) {
//		            if (trns != null) {
//		                trns.rollback();
//		            }
//		            e.printStackTrace();
//		        } finally {
//		            session.flush();
//		            session.close();
//		        }
//		    }
		 	
		 	public void suaLienHe(LienHe lh) { 
				 LienHe x = new LienHe();
				 x=layThongTin(lh);
				 x.setTinhTrang(1);
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

		 
		 	//reset cac thuoc tinh cua lienHe
		 public void reset(LienHe lienHe) {
			 lienHe.setEmail(" ");
			 lienHe.setNoiDung(" ");
		 }

}
