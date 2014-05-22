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

	//tra ve thong tin chuyebn
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
	 
	
//	 	//lay thong tin tai xe dua vao matx???????????????????
//	 	public TaiXe layTX(int maTX){
//	 		Session session = HibernateUtil.getSessionFactory().openSession();
//		    String hql = "from TaiXe  where MaTX = :matx";
//		    List result = session.createQuery(hql)
//		    .setParameter("matx", maTX)
//		    .list();
//	 		//you should return list of LoaiXe object from this method, so need to create one
//	 		TaiXe x=(TaiXe) result.get(0);
//	 		return x; //return the list we created
//	 	}
	 	
//	 	//lay thong tin tuyen dua vao ma tuyen?>>>>>>>>>>>>???????
//	 	public Tuyen layTuyen(int maTuyen){
//	 		Session session = HibernateUtil.getSessionFactory().openSession();
//		    String hql = "from Tuyen  where MaTuyen = :matuyen";
//		    List result = session.createQuery(hql)
//		    .setParameter("matuyen", maTuyen)
//		    .list();
//		  //tra ve 1 doi tuong Tuyen => lay phan tu dau tien cua result list
//		    Tuyen x=(Tuyen) result.get(0);
//	 		return x; //return the list we created
//	 	}
	 	
//	 	//lay thong tin tien ve dua vao matuyen????????????
//	 	public int tienVe(int maTuyen){
//	 		Session session = HibernateUtil.getSessionFactory().openSession();
//		    String hql = "from Tuyen  where MaTuyen = :matuyen";
//		    List result = session.createQuery(hql)
//		    .setParameter("matuyen", maTuyen)
//		    .list();
//		  //tra ve 1 doi tuong Tuyen => lay phan tu dau tien cua result list
//		    Tuyen x=(Tuyen) result.get(0);
//	 		return x.getGia(); 
//	 	}
	 	
	 	
//	 	//lay xe dua vao bien so xe ??????????????????????????
//	 	public Xe layBienSo(String bienSo){
//	 		Session session = HibernateUtil.getSessionFactory().openSession();
//		    String hql = "from Xe  where BienSo = :bs";
//		    List result = session.createQuery(hql)
//		    .setParameter("bs", bienSo)
//		    .list();
//		  //tra ve 1 doi tuong Xe => lay phan tu dau tien cua result list
//		    Xe x=(Xe) result.get(0);
//	 		return x; //return the list we created
//	 	}
	 	
	 	//tra ve danh sach chuyen
	 	public List<Chuyen> danhSachChuyen(){
	 		List<Chuyen> x;
	 		Session session = HibernateUtil.getSessionFactory().openSession();
	 		session.beginTransaction();
	 		Criteria cri=session.createCriteria(Chuyen.class);
	 		x=cri.list();
	 		return x;
	 		
	 	}
	 	
	 	//xoa mot chuyebn
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
}
