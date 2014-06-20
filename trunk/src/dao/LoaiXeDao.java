package dao;

import hibernateUtil.HibernateUtil;

import java.util.List;

import model.LoaiXe;
import model.PhanQuyen;
import model.TinTuc;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoaiXeDao {

	//Combobox loaixe
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
 		LoaiXe x=(LoaiXe) result.get(0);
 		return x;
 	}
 	
 	//lay ten loai xe theo  bien so
 	public String layTenLoaiXe(String bienSo){
 		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createSQLQuery(
				"select tenloaixe from loaixe,xe where xe.maloaixe=loaixe.maloaixe and bienSo= :bienso")
				.setParameter("bienso", bienSo);
		List result=query.list();
	    return (String) result.get(0);
 	}
}
