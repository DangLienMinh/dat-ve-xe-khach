package dao;

import hibernateUtil.HibernateUtil;
import java.util.List;
import model.NhanVien;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TaiKhoanDao {
	
	//tra ve doi tuong nhan vien theo ma nhan vien
	public NhanVien ttCaNhan(String manv){
 		Session session = HibernateUtil.getSessionFactory().openSession();
	    String hql = "from NhanVien  where MaNV = :manv";
	    List result = session.createQuery(hql)
	    .setParameter("manv", manv)
	    .list();
	    //tra ve 1 doi tuong NhanVien => lay phan tu dau tien cua result list
	    NhanVien x=(NhanVien) result.get(0);
 		return x;
 	}
	
	//sua thong tin tai khoan nhan vien
	 public void suaTaiKhoan(NhanVien nhanVien) {
		    Transaction trns = null;
		    Session session = HibernateUtil.getSessionFactory().openSession();
		    try {
		        trns = session.beginTransaction();
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

	 //sua mat khau dua vao ma nhan vien
	public void suaMatKhau(NhanVien nhanVien, String matKhauMoi) {
		Transaction trns = null;
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        trns = session.beginTransaction();
	        String hql = "from NhanVien  where MaNV = :manv";
		    List result = session.createQuery(hql)
		    .setParameter("manv", nhanVien.getMaNV())
		    .list();
		  //tra ve 1 doi tuong NhanVien => lay phan tu dau tien cua result list
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
