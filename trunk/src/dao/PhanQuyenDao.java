package dao;

import java.util.List;

import hibernateUtil.HibernateUtil;
import model.PhanQuyen;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class PhanQuyenDao {


	//tra ve combo box phan quyen
	public List<PhanQuyen> selectItems(){
		List<PhanQuyen> list=null;
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql="From PhanQuyen";
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
	
	public PhanQuyen layPQ(int maPQ){
 		Session session = HibernateUtil.getSessionFactory().openSession();
	    String hql = "from PhanQuyen  where MaPQ = :mapq";
	    List result = session.createQuery(hql)
	    .setParameter("mapq", maPQ)
	    .list();
	  //tra ve 1 doi tuong PhanQuyen => lay phan tu dau tien cua result list
 		PhanQuyen x=(PhanQuyen) result.get(0);
 		return x; //return the list we created
 	}
}
