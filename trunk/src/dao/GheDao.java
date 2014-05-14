package dao;

import hibernateUtil.HibernateUtil;

import java.util.List;

import model.Ghe;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

public class GheDao {
	public List<Ghe> danhSachGhe(int maLoaiXe){
		Session session = HibernateUtil.getSessionFactory().openSession();
	    String hql = "from Ghe  where MaLoaiXe = :maLoaiXe";
	    List result = session.createQuery(hql)
	    .setParameter("maLoaiXe", maLoaiXe)
	    .list();
 		//you should return list of LoaiXe object from this method, so need to create one
	    return result;
 	}
	
	public List<Ghe> tinhTrangGhe(int maChuyen){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createSQLQuery(
				"select * from Ghe where MaGhe in(select MaGhe from HoaDon where MaChuyen= :machuyen) and MaLoaiXe=(select MaLoaiXe from Xe where BienSo=(select BienSo from Chuyen where MaChuyen= :machuyen))")
				.addEntity(Ghe.class)
				.setParameter("machuyen", maChuyen);
	    //String hql = "select * from Ghe where MaGhe not in(select MaGhe from HoaDon where MaChuyen= :machuyen) and MaLoaiXe=(select MaLoaiXe from Xe where BienSo=(select BienSo from Chuyen where MaChuyen= :machuyen))";
	   // List result = session.createQuery(hql)
		 List result=query.list();

 		//you should return list of LoaiXe object from this method, so need to create one
	    return result;
 	}
	
	
}
