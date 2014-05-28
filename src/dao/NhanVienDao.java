package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import hibernateUtil.HibernateUtil;
import oracle.jdbc.OracleTypes;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;

import model.NhanVien;
import model.PhanQuyen;
import model.TinTuc;


public class NhanVienDao {

	//tra ve thong tin doi tuong nhan vien 
	public NhanVien layThongTin(NhanVien temp){
		NhanVien nhanVien=new NhanVien();
		nhanVien.setMaNV(temp.getMaNV());
		nhanVien.setCMND(temp.getCMND());
		nhanVien.setDiaChi(temp.getDiaChi());
		nhanVien.setDienThoai(temp.getDienThoai());
		nhanVien.setEmail(temp.getEmail());
		nhanVien.setNgaySinh(temp.getNgaySinh());
		nhanVien.setHo(temp.getHo());
		nhanVien.setTen(temp.getTen());
		nhanVien.setMatKhau(temp.getMatKhau());
		return nhanVien;
	}
	
	//them 1 doi tuong nhan vien
	public void themNhanVien(NhanVien nhanVien,PhanQuyen phanQuyen) {
		NhanVien x = new NhanVien();
		x=layThongTin(nhanVien);
		//thiet lap MaNV và MatKhau la null do qua trinh tu tao ma va amt khau trong Stored procedure
		x.setMaNV("");
		x.setMatKhau("");
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            PhanQuyen pq=(PhanQuyen) session.load(PhanQuyen.class, phanQuyen.getMaPQ());
            x.setMaPQ(pq);
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
	
	 //sua 1 doi tuong nhan vien
	 public void suaNhanVien(NhanVien nhanVien,PhanQuyen phanQuyen) {
			NhanVien x = new NhanVien();
			x=layThongTin(nhanVien);  
		    Transaction trns = null;
		    Session session = HibernateUtil.getSessionFactory().openSession();
		    try {
		        trns = session.beginTransaction();
		        PhanQuyen pq=(PhanQuyen) session.load(PhanQuyen.class, phanQuyen.getMaPQ());
	            x.setMaPQ(pq);
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
	 
	 //dang nhap 
	 public int dangNhap(NhanVien nhanVien){
			int returnResult = -1;
			Connection conn;
			//Session session = HibernateUtil.getSessionFactory().openSession();
			SessionFactoryImplementor sessionFactoryImplementation = (SessionFactoryImplementor)  HibernateUtil.getSessionFactory();
		    ConnectionProvider connectionProvider = sessionFactoryImplementation.getConnectionProvider();
		    try {
		    	conn=connectionProvider.getConnection();
		 	    CallableStatement st = conn.prepareCall("{ call sp_Dangnhap(?, ?, ?) }");
		 	    st.setString(1, nhanVien.getMaNV());
		 	    st.setString(2, nhanVien.getMatKhau());
		 	    st.registerOutParameter(3, OracleTypes.NUMBER);
		 	    st.execute();
		 	   returnResult = st.getInt(3);
		 	  
			} catch (Exception e) {
				System.out.print("Không tìm thấy tên đăng nhập trên");
			}
		   
		    
			if(returnResult==1){
				return 1;
			}
			else{
				return 0;
			}
		}
	 
	 //lay he ten nhan vien
	 	public String layHoTen(NhanVien nhanVien){
	 		Session session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from NhanVien where MaNV = :tendn and MatKhau = :matkhau");
			query.setParameter("tendn", nhanVien.getMaNV());
			query.setParameter("matkhau", nhanVien.getMatKhau());
		      try{
		    	 List list = query.list();
		    	 for (Iterator iterator1 = list.iterator(); iterator1.hasNext();){
		        	 NhanVien x = (NhanVien) iterator1.next(); 
		        	 return x.getHo()+" "+x.getTen();
		         }    
		         
		      }catch (Exception e) {
		    	  System.out.print(e.toString());
		      }finally {
		    	 session.flush();
		         session.close(); 
		      }
		      return "";
	 	}
	 	
	 	//lay doi tuong nhan vien dua vao tendn
	 	public NhanVien layNhanVien(NhanVien nhanVien){
	 		Session session = HibernateUtil.getSessionFactory().openSession();
		    String hql = "from NhanVien  where MaNV = :tendn";
		    List result = session.createQuery(hql)
		    .setParameter("tendn", nhanVien.getMaNV())
		    .list();
	 		//tra ve 1 doi tuong TinTuc => lay phan tu dau tien cua result list
		    NhanVien x=(NhanVien)result.get(0);
		    return x;
	 	}
	 	
	 	//tra ve danh sach nhan vien
	 	public List<NhanVien> danhSachNV(){
	 		List<NhanVien> x;
	 		Session session = HibernateUtil.getSessionFactory().openSession();
	 		session.beginTransaction();
	 		Criteria cri=session.createCriteria(NhanVien.class);
	 		x=cri.list();
	 		return x;
	 	}
	 	
	 	//xoa nhan vien
	 	public void xoaNhanVien(NhanVien nhanVien) {
	        Transaction trns = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        try {
	            trns = session.beginTransaction();
	            NhanVien x = (NhanVien) session.load(NhanVien.class, nhanVien.getMaNV());
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

	 
	 public void reset(NhanVien nhanVien) {
		 nhanVien.setHo(" ");
		 nhanVien.setTen(" ");
		 nhanVien.setCMND(" ");
		 nhanVien.setDiaChi(" ");
		 nhanVien.setDienThoai(" ");
		 nhanVien.setEmail(" ");
		 nhanVien.setMatKhau(" ");
	 }
}
