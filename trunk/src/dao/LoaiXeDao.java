package dao;

import hibernateUtil.HibernateUtil;

import java.util.List;

import model.LoaiXe;
import model.PhanQuyen;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoaiXeDao {

	//danh sach loai xe
	public List<LoaiXe> selectItems(){
		List<LoaiXe> list=null;
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql="From LoaiXe";
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
	
	 //lay thong tin loai xe theo maLoaiXe
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
}
