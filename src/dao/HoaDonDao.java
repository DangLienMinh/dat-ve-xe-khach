package dao;

import hibernateUtil.HibernateUtil;
import model.Chuyen;
import model.Ghe;
import model.HoaDon;


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
}
