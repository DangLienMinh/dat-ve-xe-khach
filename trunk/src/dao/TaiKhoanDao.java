package dao;

import hibernateUtil.HibernateUtil;

import java.util.List;

import model.NhanVien;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TaiKhoanDao {
	
	public NhanVien ttCaNhan(String tendn){
 		Session session = HibernateUtil.getSessionFactory().openSession();
	    String hql = "from NhanVien  where TenDN = :tendn";
	    List result = session.createQuery(hql)
	    .setParameter("tendn", tendn)
	    .list();
 		//you should return list of LoaiXe object from this method, so need to create one
	    NhanVien x=(NhanVien) result.get(0);
 		return x; //return the list we created
 	}
	
	 public void suaTaiKhoan(NhanVien nhanVien) {
		    Transaction trns = null;
		    Session session = HibernateUtil.getSessionFactory().openSession();
		    try {
		        trns = session.beginTransaction();
//		        NhanVien nv=(NhanVien) session.load(NhanVien.class, nhanVien.getMaNV());
		        String hql = "from NhanVien  where MaNV = :manv";
			    List result = session.createQuery(hql)
			    .setParameter("manv", nhanVien.getMaNV())
			    .list();
		 		//you should return list of LoaiXe object from this method, so need to create one
			    NhanVien nv=(NhanVien) result.get(0);
	            nv.setCMND(nhanVien.getCMND());
	            nv.setDiaChi(nhanVien.getDiaChi());
	            nv.setDienThoai(nhanVien.getDienThoai());
	            nv.setEmail(nhanVien.getEmail());
	            nv.setHo(nhanVien.getHo());
	            nv.setTen(nhanVien.getTen());
	            nv.setNgaySinh(nhanVien.getNgaySinh());
	            
		        session.update(nv);
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

	public void suaMatKhau(NhanVien nhanVien, String matKhauMoi) {
		Transaction trns = null;
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        trns = session.beginTransaction();
//	        NhanVien nv=(NhanVien) session.load(NhanVien.class, nhanVien.getMaNV());
	        String hql = "from NhanVien  where MaNV = :manv";
		    List result = session.createQuery(hql)
		    .setParameter("manv", nhanVien.getMaNV())
		    .list();
	 		//you should return list of LoaiXe object from this method, so need to create one
		    NhanVien nv=(NhanVien) result.get(0);
            
            nv.setMatKhau(matKhauMoi);
            
	        session.update(nv);
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
}
