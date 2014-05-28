package dao;

import java.util.List;
import hibernateUtil.HibernateUtil;
import model.TaiXe;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TaiXeDao {
	
	private TaiXe x;

	//lay thong tin tai xe theo doi tuong tai xe
	public TaiXe layThongTin(TaiXe temp){
		TaiXe taiXe=new TaiXe();
		taiXe.setHo(temp.getHo());
		taiXe.setTen(temp.getTen());
		taiXe.setDiaChi(temp.getDiaChi());
		taiXe.setCMND(temp.getCMND());
		taiXe.setDienThoai(temp.getDienThoai());
		taiXe.setEmail(temp.getEmail());
		taiXe.setNgaySinh(temp.getNgaySinh());
		taiXe.setMaTX(temp.getMaTX());
		return taiXe;
	}
	
	//them  1 tai xe 
	public void themTaiXe(TaiXe taiXe) {
		x = new TaiXe();
		x=layThongTin(taiXe);
		x.setMaTX("");
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
	
	//sua 1 tai xe
	 public void suaTaiXe(TaiXe taiXe) { 
		 x = new TaiXe();
		 x=layThongTin(taiXe);
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
	
	 //xoa 1 tai xe
	public void xoaTaiXe(TaiXe taiXe) {
		 x = new TaiXe();
		 x=layThongTin(taiXe);
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
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
	
	//tra ve danh sach tai xe
	public List<TaiXe> danhSachTX(){
 		List<TaiXe> x;
 		Session session = HibernateUtil.getSessionFactory().openSession();
 		session.beginTransaction();
 		Criteria cri=session.createCriteria(TaiXe.class);
 		x=cri.list();
 		return x;
 	}
	
	//xoa thong tin trong text box
	public void reset(TaiXe taiXe) {
		taiXe.setHo(" ");
		taiXe.setTen(" ");
		taiXe.setCMND(" ");
		taiXe.setDiaChi(" ");
		taiXe.setDienThoai(" ");
		taiXe.setEmail(" ");
	}
	
	//tra ve combobox taixe tren giao dien chuyen
	public List<TaiXe> selectItems(){
		List<TaiXe> list=null;
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql="From TaiXe";
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
	
	//lay thong tin tai xe dua vao matx
 	public TaiXe layTX(String maTX){
 		Session session = HibernateUtil.getSessionFactory().openSession();
	    String hql = "from TaiXe  where MaTX = :matx";
	    List result = session.createQuery(hql)
	    .setParameter("matx", maTX)
	    .list();
 		TaiXe x=(TaiXe) result.get(0);
 		return x; 
 	}

}
