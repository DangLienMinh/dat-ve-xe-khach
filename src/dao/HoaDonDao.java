package dao;

import java.util.List;

import hibernateUtil.HibernateUtil;
import model.Chuyen;
import model.Ghe;
import model.HoaDon;
import model.TinTuc;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HoaDonDao {
	private HoaDon x;
	
	//lay thong tin mot hoa don 
	public HoaDon layThongTin(HoaDon temp){
		HoaDon hoaDon=new HoaDon();
		hoaDon.setHo(temp.getHo());
		hoaDon.setTen(temp.getTen());
		hoaDon.setDiaChi(temp.getDiaChi());
		hoaDon.setCMND(temp.getCMND());
		hoaDon.setDienThoai(temp.getDienThoai());
		hoaDon.setEmail(temp.getEmail());
		hoaDon.setNgayGD(temp.getNgayGD());
		hoaDon.setHinhThucTT(temp.getHinhThucTT());
		hoaDon.setMaHD(temp.getMaHD());
		hoaDon.setTongTien(temp.getTongTien());

		return hoaDon;
	}
	
	//them mot hoa don
	public int themHoaDon(HoaDon hoaDon,Chuyen chuyenTemp,String gheTemp) {
		x = new HoaDon();
		x=layThongTin(hoaDon);
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Ghe ghe=(Ghe) session.load(Ghe.class, Integer.parseInt(gheTemp));
            x.setMaGhe(ghe);
            Chuyen chuyen=(Chuyen) session.load(Chuyen.class, chuyenTemp.getMaChuyen());
            x.setMaChuyen(chuyen);
            session.save(x);
            session.getTransaction().commit();
           
            
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } 
        session.flush();
        session.close();
        return x.getMaHD();
    }
	
	//cap nhat ngay dat ve
	public void capNhatNgayNhanVe(HoaDon x){
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        trns=session.beginTransaction();
		Query query = session.createSQLQuery(
			"update HoaDon set NgayNhanVe = :ngayNhanVe where MaHD = :mahd")
				.setParameter("ngayNhanVe", x.getNgayNhanVe())
				.setParameter("mahd", x.getMaHD());
		query.executeUpdate();
		trns.commit();
 	}
	
	 //lay hoa don thweo MaHD
 	public HoaDon layHD(int maHD){		
 		Session session = HibernateUtil.getSessionFactory().openSession();
 		HoaDon hd = (HoaDon)session.get(HoaDon.class,maHD); 
 		if(hd!=null){
		    String hql = "from HoaDon  where MaHD = :maHD";
		    List result = session.createQuery(hql)
		    .setParameter("maHD", maHD)
		    .list();
		//tra ve 1 doi tuong loaitintuc => lay phan tu dau tien cua result list
		    HoaDon x=(HoaDon) result.get(0);
	 		return x; 
 		}
 		else{
 			return (new HoaDon());
 		}
 	}
}
