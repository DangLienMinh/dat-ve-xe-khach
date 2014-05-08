package dao;

import hibernateUtil.HibernateUtil;

import java.io.Serializable;
import java.util.List;

import model.LoaiTinTuc;
import model.TinTuc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TinTucDao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public TinTuc layThongTin(TinTuc temp){
		TinTuc tinTuc=new TinTuc();
		tinTuc.setTomTat(temp.getTomTat());
		tinTuc.setHinhAnh(temp.getHinhAnh());
		tinTuc.setNgayDang(temp.getNgayDang());
		tinTuc.setTieuDe(temp.getTieuDe());
		tinTuc.setNoiDung(temp.getNoiDung());
		return tinTuc;
	}
	
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
	 	
	 	public LoaiTinTuc layLTT(int maLoaiTinTuc){		
		    Session session = HibernateUtil.getSessionFactory().openSession();
		    String hql = "from LoaiTinTuc  where MaLTT = :maLoaiTinTuc";
		    List result = session.createQuery(hql)
		    .setParameter("maLoaiTinTuc", maLoaiTinTuc)
		    .list();
	 		//you should return list of LoaiTinTuc object from this method, so need to create one
	 		LoaiTinTuc x=(LoaiTinTuc) result.get(0);
	 		return x; //return the list we created
	 	}
	 	
	 	public List<TinTuc> danhSachTinTuc(){
	 		List<TinTuc> x;
	 		Session session = HibernateUtil.getSessionFactory().openSession();
	 		session.beginTransaction();
	 		Criteria cri=session.createCriteria(TinTuc.class);
	 		x=cri.list();
	 		return x;
	 	}
	 	
	 	public void xoaTinTuc(TinTuc tinTuc) {
	        Transaction trns = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        try {
	            trns = session.beginTransaction();
	            TinTuc x = (TinTuc) session.load(TinTuc.class, tinTuc.getMaTT());
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

	 
	 public void reset(TinTuc tinTuc) {
		 tinTuc.setNoiDung(" ");
		 tinTuc.setTieuDe(" ");
		 tinTuc.setTomTat(" ");
		 tinTuc.setHinhAnh(" ");
	 }
}
