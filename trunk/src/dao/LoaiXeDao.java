package dao;

import hibernateUtil.HibernateUtil;

import java.util.List;

import model.LoaiXe;
import model.PhanQuyen;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoaiXeDao {

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
}
