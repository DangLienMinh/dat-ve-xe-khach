package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

import model.LoaiTinTuc;
import model.TinTuc;
import dao.LoaiTinTucDao;
import dao.TinTucDao;

@ManagedBean(name= "tinTucMBean")
@ViewScoped
public class TinTucManagedBean implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private TinTuc tinTuc=new TinTuc();
		private TinTucDao tinTucDao=new TinTucDao();
		private LoaiTinTuc loaiTinTuc=new LoaiTinTuc();
		private LoaiTinTucDao loaiTinTucDao=new LoaiTinTucDao();
		//Trả về danh sách nhân viên trên giao diện xhtml
		private List<TinTuc> DanhSach;
		//Trả về danh sách nhân viên theo kiểu lọc thuộc tính
		private List<TinTuc> filteredDanhSach;  
		//đối tượng nhân viên được chọn để cập nhật thông tin
		private TinTuc selectedTinTuc=new TinTuc();
		//đối tượng phân quyền được chọn để cập nhật thông tin
		private LoaiTinTuc selectedLoaiTinTuc=new LoaiTinTuc();
		private UploadedFile file;

		public TinTucManagedBean(){
			DanhSach = new ArrayList<TinTuc>();
			DanhSach = tinTucDao.danhSachTinTuc();
		}
		
		public TinTuc getTinTuc() {
			return tinTuc;
		}

		public void setTinTuc(TinTuc tinTuc) {
			this.tinTuc = tinTuc;
		}

		public TinTucDao getTinTucDao() {
			return tinTucDao;
		}

		public void setTinTucDao(TinTucDao tinTucDao) {
			this.tinTucDao = tinTucDao;
		}

		public LoaiTinTuc getLoaiTinTuc() {
			return loaiTinTuc;
		}

		public void setLoaiTinTuc(LoaiTinTuc loaiTinTuc) {
			this.loaiTinTuc = loaiTinTuc;
		}

		public LoaiTinTucDao getLoaiTinTucDao() {
			return loaiTinTucDao;
		}

		public void setLoaiTinTucDao(LoaiTinTucDao loaiTinTucDao) {
			this.loaiTinTucDao = loaiTinTucDao;
		}

		public List<TinTuc> getDanhSach() {
			return DanhSach;
		}

		public void setDanhSach(List<TinTuc> danhSach) {
			DanhSach = danhSach;
		}

		public List<TinTuc> getFilteredDanhSach() {
			return filteredDanhSach;
		}

		public void setFilteredDanhSach(List<TinTuc> filteredDanhSach) {
			this.filteredDanhSach = filteredDanhSach;
		}

		public TinTuc getSelectedTinTuc() {
			return selectedTinTuc;
		}

		public void setSelectedTinTuc(TinTuc selectedTinTuc) {
			this.selectedTinTuc = selectedTinTuc;
		}

		public LoaiTinTuc getSelectedLoaiTinTuc() {
			return selectedLoaiTinTuc;
		}

		public void setSelectedLoaiTinTuc(LoaiTinTuc selectedLoaiTinTuc) {
			this.selectedLoaiTinTuc = selectedLoaiTinTuc;
		}	
		
		public String themTinTuc(){
			upload();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date today;
			try {
				today = dateFormat.parse(dateFormat.format(new Date()));
				tinTuc.setNgayDang(today);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			tinTucDao.themTinTuc(tinTuc, loaiTinTuc);
			return "QLTinTuc?faces-redirect=true";
		}
		
		public String suaTinTuc(){
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date today;
			try {
				today = dateFormat.parse(dateFormat.format(new Date()));
				selectedTinTuc.setNgayDang(today);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//sửa nhân viên dựa vào đối tượng nhân viên được chọn
			tinTucDao.suaTinTuc(selectedTinTuc,selectedLoaiTinTuc);
			return "QLTinTuc?faces-redirect=true";
		}
		
		public String xoaTinTuc(TinTuc x){
			tinTucDao.xoaTinTuc(x);
			return "QLTinTuc?faces-redirect=true";
		}
		
		//reset các ô input
		public void reset(){
			tinTucDao.reset(tinTuc);
		}
		
		//hàm khi bấm vào icon găng cưa sẽ lưu thông tin đối tượng nhân viên được chọn
		public void capNhat(TinTuc x,String maLTT){
			selectedTinTuc=x;
			int ma=Integer.parseInt(maLTT);
			setSelectedLoaiTinTuc(tinTucDao.layLTT(ma));
		}
		
		

		public void upload() {  
	        FacesMessage msg = new FacesMessage("Success! ", file.getFileName() + " is uploaded.");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        // Do what you want with the file        
	        try {
	            copyFile(file.getFileName(), file.getInputstream());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }  

	    public void copyFile(String fileName, InputStream in) {
	    	//String destination="E:\\eclipseProject\\datVeXe\\WebContent\\pics\\";
	    	ExternalContext extContext =
	    			FacesContext.getCurrentInstance().getExternalContext();
	    	String destination=extContext.getRealPath("//pics//" + fileName);
	    	tinTuc.setHinhAnh("/pics/"+fileName);
	           try {
	             
	             
	                // write the inputStream to a FileOutputStream
	                OutputStream out = new FileOutputStream(new File(destination));
	             
	                int read = 0;
	                byte[] bytes = new byte[1024];
	             
	                while ((read = in.read(bytes)) != -1) {
	                    out.write(bytes, 0, read);
	                }
	             
	                in.close();
	                out.flush();
	                out.close();
	             
	                System.out.println("New file created!");
	                } catch (IOException e) {
	                System.out.println(e.getMessage());
	                }
	    }

		
		public UploadedFile getFile() {
			return file;
		}

		public void setFile(UploadedFile file) {
			this.file = file;
		}	
	}
