package dao;

import hibernateUtil.HibernateUtil;

import java.util.Date;
import java.util.List;
import model.Ghe;
import org.hibernate.Query;
import org.hibernate.Session;

public class GheDao {
	
	//danh sach ghe
	public List<Ghe> danhSachGhe(int maLoaiXe){
		Session session = HibernateUtil.getSessionFactory().openSession();
	    String hql = "from Ghe  where MaLoaiXe = :maLoaiXe";
	    List result = session.createQuery(hql)
	    .setParameter("maLoaiXe", maLoaiXe)
	    .list();

	    return result;
 	}
	
	//thong tin tinh trang ghe 
	public List<Ghe> tinhTrangGhe(int maChuyen,Date ngayGD){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createSQLQuery(
				"select * from Ghe where MaGhe in(select MaGhe from HoaDon where MaChuyen= :machuyen and NgayGD= :ngayGD) and MaLoaiXe=(select MaLoaiXe from Xe where BienSo=(select BienSo from Chuyen where MaChuyen= :machuyen))")
				.addEntity(Ghe.class)
				.setParameter("ngayGD", ngayGD)
				.setParameter("machuyen", maChuyen);
		List result=query.list();
	    return result;
 	}
	
	
}
