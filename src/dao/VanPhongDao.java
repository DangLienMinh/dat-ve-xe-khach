package dao;

import hibernateUtil.HibernateUtil;

import java.util.List;

import model.KhuVuc;
import model.VanPhong;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VanPhongDao {

	//tra ve thong tin doi tuong vanPhong
	public VanPhong layThongTin(VanPhong temp){
		VanPhong vanPhong=new VanPhong();
		vanPhong.setDiaChi(temp.getDiaChi());
		vanPhong.setDienThoai(temp.getDienThoai());
		vanPhong.setTenVP(temp.getTenVP());
		vanPhong.setMaVP(temp.getMaVP());
		return vanPhong;
	}
	
	//them mot doi tuong vanPhong
	public void themVanPhong(VanPhong vanPhong,KhuVuc khuVuc) {
		VanPhong x = new VanPhong();
		x=layThongTin(vanPhong);  
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            KhuVuc kv=(KhuVuc) session.load(KhuVuc.class, khuVuc.getMaKV());
            x.setMaKV(kv);
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
	
	//sua mot doi tuong vanPhong 
	 public void suaVanPhong(VanPhong vanPhong,KhuVuc khuVuc) {
			VanPhong x = new VanPhong();
			x=layThongTin(vanPhong);  
		    Transaction trns = null;
		    Session session = HibernateUtil.getSessionFactory().openSession();
		    try {
		        trns = session.beginTransaction();
		        KhuVuc kv=(KhuVuc) session.load(KhuVuc.class, khuVuc.getMaKV());
	            x.setMaKV(kv);
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
	 	
	 	//Tra ve doi tuong vanPhong theo MaVP 
	 	public VanPhong layVanPhongTheoMaVP(String mavp){
		    Session session = HibernateUtil.getSessionFactory().openSession();
		    String hql = "from VanPhong  where MaVP = :mavp";
		    List result = session.createQuery(hql)
		    .setParameter("mavp", mavp)
		    .list();
	 		//tra ve 1 doi tuong vanPhong => lay phan tu dau tien cua result list
	 		VanPhong x=(VanPhong) result.get(0);
	 		return x; 
	 	}
	 	
	 	//tra ve tat ca cac vanPhong trong csdl
	 	public List<VanPhong> danhSachVanPhong(){
	 		List<VanPhong> x;
	 		Session session = HibernateUtil.getSessionFactory().openSession();
	 		session.beginTransaction();
	 		Criteria cri=session.createCriteria(VanPhong.class);
	 		x=cri.list();
	 		return x;
	 	}
	 	
	 	//xoa mot doi tuong vanPhong
	 	public void xoaVanPhong(VanPhong vanPhong) {
	        Transaction trns = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        try {
	            trns = session.beginTransaction();
	            VanPhong x = (VanPhong) session.load(VanPhong.class, vanPhong.getMaVP());
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

	 
	 	//reset cac thuoc tinh cua vanPhong
	 public void reset(VanPhong vanPhong) {
		 vanPhong.setTenVP(" ");
		 vanPhong.setDienThoai(" ");
		 vanPhong.setDiaChi(" ");
	 }
}
