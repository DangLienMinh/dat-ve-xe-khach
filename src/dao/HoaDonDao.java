package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import hibernateUtil.HibernateUtil;
import model.Chuyen;
import model.HoaDon;
import oracle.jdbc.OracleTypes;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;

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
	
	//Khach hang dat ve
	public String themHoaDon(HoaDon hoaDon,Chuyen chuyenTemp,String gheTemp) {
		Connection conn;
		SessionFactoryImplementor sessionFactoryImplementation = (SessionFactoryImplementor)  HibernateUtil.getSessionFactory();
	    ConnectionProvider connectionProvider = sessionFactoryImplementation.getConnectionProvider();
	    int ketQua=-1;
	    String MaHD="";
	    try {
	    	conn=connectionProvider.getConnection();
	 	    CallableStatement st = conn.prepareCall("{ call DATVE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
	 	    st.setString(1, chuyenTemp.getMaChuyen());
	 	    st.setString(2, hoaDon.getHo());
	 	    st.setString(3, hoaDon.getTen());
	 	    st.setString(4, hoaDon.getCMND());
	 	    st.setString(5, hoaDon.getDiaChi());
	 	    st.setString(6, hoaDon.getDienThoai());
	 	    st.setString(7, hoaDon.getEmail());
	 	    st.setInt(8, hoaDon.getTongTien());
	 	    st.setDate(9, new java.sql.Date(hoaDon.getNgayGD().getTime()));
	 	   	st.setString(10, hoaDon.getHinhThucTT());
	 	   	st.setString(11,gheTemp);
	 	    st.registerOutParameter(13, OracleTypes.NUMBER);
	 	    st.registerOutParameter(12,OracleTypes.VARCHAR);
	 	    st.executeUpdate();
	 	    ketQua = st.getInt(13);
	 	    MaHD  = st.getString(12);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	    if(ketQua==0){
	    	return "0";
	    }
	    else
	   return MaHD;
    }
	
		//Nhan vien dat ve
		public String banVe(HoaDon hoaDon,Chuyen chuyenTemp,String gheTemp) {
			Connection conn;
			SessionFactoryImplementor sessionFactoryImplementation = (SessionFactoryImplementor)  HibernateUtil.getSessionFactory();
		    ConnectionProvider connectionProvider = sessionFactoryImplementation.getConnectionProvider();
		    int ketQua=-1;
		    String MaHD="";
		    try {
		    	conn=connectionProvider.getConnection();
		 	    CallableStatement st = conn.prepareCall("{ call DATVE_NV(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		 	    st.setString(1, chuyenTemp.getMaChuyen());
		 	    st.setString(2, hoaDon.getHo());
		 	    st.setString(3, hoaDon.getTen());
		 	    st.setString(4, hoaDon.getCMND());
		 	    st.setString(5, hoaDon.getDiaChi());
		 	    st.setString(6, hoaDon.getDienThoai());
		 	    st.setString(7, hoaDon.getEmail());
		 	    st.setInt(8, hoaDon.getTongTien());
		 	    st.setDate(9, new java.sql.Date(hoaDon.getNgayGD().getTime()));
		 	   	st.setString(10, hoaDon.getHinhThucTT());
		 	   	st.setString(11,gheTemp);
		 	    st.registerOutParameter(13, OracleTypes.NUMBER);
		 	    st.registerOutParameter(12,OracleTypes.VARCHAR);
		 	    st.executeUpdate();
		 	    ketQua = st.getInt(13);
		 	    MaHD  = st.getString(12);
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
		    if(ketQua==0){
		    	return "0";
		    }
		    else
		   return MaHD;
	    }
	
	//cap nhat ngay dat ve (qua trinh kahch hang lay  ve)
	public int capNhatNgayNhanVe(HoaDon x){
		int returnResult = -10;
		Connection conn;
		SessionFactoryImplementor sessionFactoryImplementation = (SessionFactoryImplementor)  HibernateUtil.getSessionFactory();
	    ConnectionProvider connectionProvider = sessionFactoryImplementation.getConnectionProvider();
	    try {
	    	conn=connectionProvider.getConnection();
	 	    CallableStatement st = conn.prepareCall("{ call LAYVE(?, ?) }");
	 	    st.setString(1, x.getMaHD());
	 	   st.registerOutParameter(2, OracleTypes.NUMBER);

	 	   st.executeUpdate();
	 	   returnResult = st.getInt(2);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	    return returnResult;
 	}
	
	 //lay hoa don theo MaHD
 	public HoaDon layHD(String maHD){		
 		Session session = HibernateUtil.getSessionFactory().openSession();
 		HoaDon hd = (HoaDon)session.get(HoaDon.class,maHD); 
 		if(hd!=null){
		    String hql = "from HoaDon  where MaHD = :maHD";
		    List result = session.createQuery(hql)
		    .setParameter("maHD", maHD)
		    .list();
		//tra ve 1 doi tuong hoadon => lay phan tu dau tien cua result list
		    HoaDon x=(HoaDon) result.get(0);
	 		return x; 
 		}
 		else{
 			return (new HoaDon());
 		}
 	}
 	
 	//lay thong tin hoa don dua vao CMND va ngaygd
 	public List<HoaDon> layHDTheoTTKH(Date ngayGD,String cmnd){
 		Session session = HibernateUtil.getSessionFactory().openSession();
 		String hql="";
 		List result=null;
 		if(cmnd!=""){
 			 hql = "from HoaDon  where NgayGD = :ngaygd and CMND= :cmnd";
 			 result = session.createQuery(hql)
 					.setDate("ngaygd", ngayGD).setString("cmnd", cmnd)
 					 .list();
 		}
 		else{
 			 hql = "from HoaDon  where NgayGD = :ngaygd";
 			 result = session.createQuery(hql)
 					.setDate("ngaygd", ngayGD)
 					 .list();
 		}
		//tra ve 1 doi tuong HoaDon => lay phan tu dau tien cua result list
	    return result;
 	}
 	
 	public int demGheConTrong(String maChuyen,Date NgayGD){
 		int returnResult = -10;
		Connection conn;
		SessionFactoryImplementor sessionFactoryImplementation = (SessionFactoryImplementor)  HibernateUtil.getSessionFactory();
	    ConnectionProvider connectionProvider = sessionFactoryImplementation.getConnectionProvider();
	    try {
	    	conn=connectionProvider.getConnection();
	 	    CallableStatement st = conn.prepareCall("{ call demSoGheTrong(?, ?, ?) }");
	 	    st.setString(1, maChuyen);
	 	    st.setDate(2,  new java.sql.Date(NgayGD.getTime()));
	 	   st.registerOutParameter(3, OracleTypes.NUMBER);

	 	   st.executeUpdate();
	 	   returnResult = st.getInt(3);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	    return returnResult;
 	}
}
