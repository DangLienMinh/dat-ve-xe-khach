package dao;

import hibernateUtil.HibernateUtil;

import java.util.Date;
import java.util.List;
import model.Ghe;
import org.hibernate.Query;
import org.hibernate.Session;

public class GheDao {
	
	//thong tin tinh trang ghe (chon ra nhung ghe da co nguoi dat roi)
	public List<Ghe> tinhTrangGhe(String maChuyen,Date ngayGD){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createSQLQuery(
				"select * from Ghe where MaGhe in(select MaGhe from HoaDon where MaChuyen= :machuyen and NgayGD= :ngayGD)")
				.addEntity(Ghe.class)
				.setParameter("ngayGD", ngayGD)
				.setParameter("machuyen", maChuyen);
		List result=query.list();
	    return result;
 	}
}
