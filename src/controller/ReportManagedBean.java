package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import model.Tuyen;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

@ManagedBean(name= "reportMBean")
@SessionScoped
public class ReportManagedBean {
	
	//dung cho doanhThuNam
	private String Nam;
	
	//Dung cho doanh thu tuyen theo chuyen
	private String Nam1;
	
	//Dung cho doanh thu tuyen
	private String Nam2;
		
		
	private Date SelectedDate;
	private Tuyen tuyen=new Tuyen();
	
	private Connection connectDatabase() throws ClassNotFoundException, SQLException{
		Connection connection;
		Class.forName("oracle.jdbc.driver.OracleDriver"); 
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","DatVeXeKhach","Minh11520232");
		return connection;
	}

	public String doanhThuNam() throws ClassNotFoundException, SQLException, IOException,JRException
	{
		Connection connection;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)
		context.getExternalContext().getResponse();
		InputStream reportStream = context.getExternalContext().
		getResourceAsStream("/admin/reports/doanhThuNam.jasper");
		response.setContentType("application/pdf"); 
		
		ServletOutputStream servletOutputStream = 
				response.getOutputStream();
		connection=connectDatabase();
		HashMap map = new HashMap();
		map.put("Nam", getNam());
		try {
			JasperRunManager.runReportToPdfStream(reportStream,
					servletOutputStream,map, connection);
			FacesContext.getCurrentInstance().responseComplete();
			connection.close();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		servletOutputStream.flush();
		servletOutputStream.close();
		return "";
	}
	
	public String doanhThuTuyen() throws ClassNotFoundException, SQLException, IOException,JRException
	{
		Connection connection;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)
		context.getExternalContext().getResponse();
		InputStream reportStream = context.getExternalContext().
		getResourceAsStream("/admin/reports/doanhThuTuyen.jasper");
		response.setContentType("application/pdf"); 
		
		ServletOutputStream servletOutputStream = 
				response.getOutputStream();
		connection=connectDatabase();
		HashMap map = new HashMap();
		map.put("Nam", getNam2());
		try {
			JasperRunManager.runReportToPdfStream(reportStream,
					servletOutputStream,map, connection);
			FacesContext.getCurrentInstance().responseComplete();
			connection.close();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		servletOutputStream.flush();
		servletOutputStream.close();
		return "";
	}
	
	public String doanhThuChuyen() throws ClassNotFoundException, SQLException, IOException,JRException
	{
		Connection connection;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)
		context.getExternalContext().getResponse();
		InputStream reportStream = context.getExternalContext().
		getResourceAsStream("/admin/reports/doanhThuChuyen.jasper");
		response.setContentType("application/pdf"); 
		
		ServletOutputStream servletOutputStream = 
				response.getOutputStream();
		connection=connectDatabase();
		
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(this.SelectedDate);
	    String year = Integer.toString(cal.get(Calendar.YEAR));
	    String month = formatMonth(cal.get(Calendar.MONTH));	    		
		HashMap map = new HashMap();
		map.put("Nam", year);
		map.put("Thang", month);
		try {
			JasperRunManager.runReportToPdfStream(reportStream,
					servletOutputStream,map, connection);
			FacesContext.getCurrentInstance().responseComplete();
			connection.close();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		servletOutputStream.flush();
		servletOutputStream.close();
		return "";
	}
	
	public String doanhThuChuyenTheoTuyen() throws ClassNotFoundException, SQLException, IOException,JRException
	{
		Connection connection;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)
		context.getExternalContext().getResponse();
		InputStream reportStream = context.getExternalContext().
		getResourceAsStream("/admin/reports/doanhThuChuyenTheoTuyen.jasper");
		response.setContentType("application/pdf"); 
		
		ServletOutputStream servletOutputStream = 
				response.getOutputStream();
		connection=connectDatabase();
		
	        		
		HashMap map = new HashMap();
		map.put("Nam", getNam1());
		map.put("Tuyen",tuyen.getMaTuyen());
		try {
			JasperRunManager.runReportToPdfStream(reportStream,
					servletOutputStream,map, connection);
			FacesContext.getCurrentInstance().responseComplete();
			connection.close();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		servletOutputStream.flush();
		servletOutputStream.close();
		return "";
	}
	
	public String inVe(String maHoaDon) throws ClassNotFoundException, SQLException, IOException,JRException
	{
		Connection connection;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)
		context.getExternalContext().getResponse();
		InputStream reportStream = context.getExternalContext().
		getResourceAsStream("/admin/reports/veChinhThuc.jasper");
		response.setContentType("application/pdf"); 
		
		ServletOutputStream servletOutputStream = 
				response.getOutputStream();
		connection=connectDatabase();
		
		HashMap map = new HashMap();
		map.put("MaHoaDon", maHoaDon);
		try {
			JasperRunManager.runReportToPdfStream(reportStream,
					servletOutputStream,map, connection);
			FacesContext.getCurrentInstance().responseComplete();
			connection.close();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		servletOutputStream.flush();
		servletOutputStream.close();
		return "";
	}
	
	public String reportNhanVien() throws ClassNotFoundException, SQLException, IOException,JRException
	{
		Connection connection;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)
		context.getExternalContext().getResponse();
		InputStream reportStream = context.getExternalContext().getResourceAsStream("/admin/reports/nhanVien.jasper");
		response.setContentType("application/pdf"); 
		
		ServletOutputStream servletOutputStream = 
				response.getOutputStream();
		connection=connectDatabase();
		try {
			
			JasperRunManager.runReportToPdfStream(reportStream,
			servletOutputStream, new HashMap(), connection);
			FacesContext.getCurrentInstance().responseComplete();
			connection.close();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		servletOutputStream.flush();
		servletOutputStream.close();
		return "";
	}
	
	public String reportTaiXe() throws ClassNotFoundException, SQLException, IOException,JRException
	{
		Connection connection;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)
		context.getExternalContext().getResponse();		
		InputStream reportStream = context.getExternalContext().getResourceAsStream("/admin/reports/taiXe.jasper");
		response.setContentType("application/pdf"); 
		
		ServletOutputStream servletOutputStream = 
				response.getOutputStream();
		connection=connectDatabase();
		try {
			
			JasperRunManager.runReportToPdfStream(reportStream,
			servletOutputStream, new HashMap(), connection);
			FacesContext.getCurrentInstance().responseComplete();
			connection.close();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		servletOutputStream.flush();
		servletOutputStream.close();
		return "";
	}
	
	public String reportXe() throws ClassNotFoundException, SQLException, IOException,JRException
	{
		Connection connection;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)
		context.getExternalContext().getResponse();
		
		InputStream reportStream = context.getExternalContext().getResourceAsStream("/admin/reports/xe.jasper");
		response.setContentType("application/pdf"); 
		
		ServletOutputStream servletOutputStream = 
				response.getOutputStream();
		connection=connectDatabase();
		try {
			
			JasperRunManager.runReportToPdfStream(reportStream,
			servletOutputStream, new HashMap(), connection);
			FacesContext.getCurrentInstance().responseComplete();
			connection.close();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		servletOutputStream.flush();
		servletOutputStream.close();
		return "";
	}
	
	public String reportTuyen() throws ClassNotFoundException, SQLException, IOException,JRException
	{
		Connection connection;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)
		context.getExternalContext().getResponse();
		
		InputStream reportStream = context.getExternalContext().getResourceAsStream("/admin/reports/tuyen.jasper");
		response.setContentType("application/pdf"); 
		
		ServletOutputStream servletOutputStream = 
				response.getOutputStream();
		connection=connectDatabase();
		try {
			
			JasperRunManager.runReportToPdfStream(reportStream,
			servletOutputStream, new HashMap(), connection);
			FacesContext.getCurrentInstance().responseComplete();
			connection.close();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		servletOutputStream.flush();
		servletOutputStream.close();
		return "";
	}
	
	public String reportChuyen() throws ClassNotFoundException, SQLException, IOException,JRException
	{
		Connection connection;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)
		context.getExternalContext().getResponse();
		
		InputStream reportStream = context.getExternalContext().getResourceAsStream("/admin/reports/chuyen.jasper");
		response.setContentType("application/pdf"); 
		
		ServletOutputStream servletOutputStream = 
				response.getOutputStream();
		connection=connectDatabase();
		try {
			
			JasperRunManager.runReportToPdfStream(reportStream,
			servletOutputStream, new HashMap(), connection);
			FacesContext.getCurrentInstance().responseComplete();
			connection.close();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		servletOutputStream.flush();
		servletOutputStream.close();
		return "";
	}

	public String getNam() {
		return Nam;
	}

	public void setNam(String nam) {
		Nam = nam;
	}

	public Date getSelectedDate() {
		return SelectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		SelectedDate = selectedDate;
	}
	
	public String formatMonth(int month){
		switch (month) {
		case 0:
			return "01";
		case 1:
			return "02";
		case 2:
			return "03";
		case 3:
			return "04";
		case 4:
			return "05";
		case 5:
			return "06";
		case 6:
			return "07";
		case 7:
			return "08";
		case 8:
			return "09";
		case 9:
			return "10";
		case 10:
			return "11";
		case 11:
			return "12";

		default:
			return "";
		}
	}

	public Tuyen getTuyen() {
		return tuyen;
	}

	public void setTuyen(Tuyen tuyen) {
		this.tuyen = tuyen;
	}

	public String getNam1() {
		return Nam1;
	}

	public void setNam1(String nam1) {
		Nam1 = nam1;
	}

	public String getNam2() {
		return Nam2;
	}

	public void setNam2(String nam2) {
		Nam2 = nam2;
	}
}
