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

	//combobox loai tin tuc
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
	
	//lay loai tin tuc theo maltt
 	public LoaiTinTuc layLTT(int maLoaiTinTuc){		
	   Session session = HibernateUtil.getSessionFactory().openSession();
	    String hql = "from LoaiTinTuc  where MaLTT = :maLoaiTinTuc";
	    List result = session.createQuery(hql)
	    .setParameter("maLoaiTinTuc", maLoaiTinTuc)
	    .list();
	//tra ve 1 doi tuong loaitintuc => lay phan tu dau tien cua result list
 		LoaiTinTuc x=(LoaiTinTuc) result.get(0);
 		return x; 
 	}
}
