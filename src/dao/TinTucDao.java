package dao;

import hibernateUtil.HibernateUtil;

import java.io.Serializable;
import java.util.List;
import model.LoaiTinTuc;
import model.TinTuc;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

public class TinTucDao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//lay thong tin mot doi tuong tin tuc
	public TinTuc layThongTin(TinTuc temp){
		TinTuc tinTuc=new TinTuc();
		tinTuc.setTomTat(temp.getTomTat());
		tinTuc.setHinhAnh(temp.getHinhAnh());
		tinTuc.setNgayDang(temp.getNgayDang());
		tinTuc.setTieuDe(temp.getTieuDe());
		tinTuc.setNoiDung(temp.getNoiDung());
		return tinTuc;
	}
	
	//them 1 doi tuong tin tuc
	public void themTinTuc(TinTuc tinTuc,LoaiTinTuc loaiTinTuc) {
		TinTuc x = new TinTuc();
		x=layThongTin(tinTuc);  
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            LoaiTinTuc ltt=(LoaiTinTuc) session.load(LoaiTinTuc.class, loaiTinTuc.getMaLTT());
            x.setMaLTT(ltt);
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
	
	 //sua mot doi tuong tin tuc
	 public void suaTinTuc(TinTuc tinTuc,LoaiTinTuc loaiTinTuc) {
			TinTuc x = new TinTuc();
			x=layThongTin(tinTuc);  
			x.setMaTT(tinTuc.getMaTT());
			
		    Transaction trns = null;
		    Session session = HibernateUtil.getSessionFactory().openSession();
		    try {
		        trns = session.beginTransaction();
		        LoaiTinTuc ltt=(LoaiTinTuc) session.load(LoaiTinTuc.class, loaiTinTuc.getMaLTT());
	            x.setMaLTT(ltt);
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
	 	
//	 //lay loai tin tuc theo maltt
//	 	public LoaiTinTuc layLTT(int maLoaiTinTuc){		
//		   Session session = HibernateUtil.getSessionFactory().openSession();
//		    String hql = "from LoaiTinTuc  where MaLTT = :maLoaiTinTuc";
//		    List result = session.createQuery(hql)
//		    .setParameter("maLoaiTinTuc", maLoaiTinTuc)
//		    .list();
//		//tra ve 1 doi tuong loaitintuc => lay phan tu dau tien cua result list
//	 		LoaiTinTuc x=(LoaiTinTuc) result.get(0);
//	 		return x; 
//	 	}
	 	
	 	//load danh sach tin tuc
	 	public List<TinTuc> danhSachTinTuc(){
	 		Session session = HibernateUtil.getSessionFactory().openSession();
	 		List<TinTuc> x;
	 		session.beginTransaction();
	 		Criteria cri=session.createCriteria(TinTuc.class);
	 		cri.addOrder(Order.desc("NgayDang"));
	 		x=cri.list();
	 		return x;
	 	}
	 	
	 	//Chi tiet mot tin tuc
	 	public TinTuc loadData(int maTT){
	 		Session session = HibernateUtil.getSessionFactory().openSession();
		    String hql = "from TinTuc  where MaTT = :matt";
		    List result = session.createQuery(hql)
		    .setParameter("matt", maTT)
		    .list();
	 		//tra ve 1 doi tuong TinTuc => lay phan tu dau tien cua result list
		    TinTuc x=(TinTuc)result.get(0);
		    return x;
	 	}
	 	
	 	
	 	
	 	//xoa tin tuc
	 	public void xoaTinTuc(TinTuc tinTuc) {
	 		TinTuc x=new TinTuc();
	        Transaction trns = null;
	       Session session = HibernateUtil.getSessionFactory().openSession();
	        try {
	            trns = session.beginTransaction();
	            x = (TinTuc) session.load(TinTuc.class, tinTuc.getMaTT());
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
	 	
	 	//danh sach tin tuc lien quan
	 	public List<TinTuc> danhSachTinLienQuan(){
	 		Session session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createSQLQuery(
					"SELECT * FROM ( SELECT * FROM TinTuc ORDER BY DBMS_RANDOM.RANDOM) WHERE rownum <6")
					.addEntity(TinTuc.class);
			List result=query.list();
		    return result;
	 	}

	 //lam trong tat ca cac textbox 
	 public void reset(TinTuc tinTuc) {
		 tinTuc.setNoiDung(" ");
		 tinTuc.setTieuDe(" ");
		 tinTuc.setTomTat(" ");
		 tinTuc.setHinhAnh(" ");
	 }
}
