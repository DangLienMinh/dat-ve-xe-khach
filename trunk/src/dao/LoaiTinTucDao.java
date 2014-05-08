package dao;

import hibernateUtil.HibernateUtil;

import java.io.Serializable;
import java.util.List;

import model.LoaiTinTuc;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoaiTinTucDao implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<LoaiTinTuc> selectItems(){
		List<LoaiTinTuc> list=null;
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql="From LoaiTinTuc";
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
