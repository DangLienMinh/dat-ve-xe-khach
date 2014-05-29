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
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import model.LoaiTinTuc;
import model.NhanVien;
import model.PhanQuyen;
import model.TinTuc;
import dao.LoaiTinTucDao;
import dao.NhanVienDao;
import dao.TinTucDao;

@ManagedBean(name= "tinTucMBean")
@ViewScoped
public class TinTucManagedBean implements Serializable {
	
		private static final long serialVersionUID = 1L;
		
		private TinTuc tinTuc=new TinTuc();
		private TinTucDao tinTucDao=new TinTucDao();
		private LoaiTinTuc loaiTinTuc=new LoaiTinTuc();
		private LoaiTinTucDao loaiTinTucDao=new LoaiTinTucDao();
		
		//Tra ve danh sach tin tuc
		private List<TinTuc> DanhSach;
		
		//danh sach tin tuc theo kieu loc thuoc tinh
		private List<TinTuc> filteredDanhSach;  
		
		//doi tuong tin tuc duoc chon
		private TinTuc selectedTinTuc=new TinTuc();
		
		//doi tuong loai tin tuc duoc chon
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
		
		//kiem tra quyen dn de forward ve dung trang cho nguoi dung
		public int kiemTraquyenDN(){
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		     NhanVien x= new NhanVien();
		     x.setMaNV(session.getAttribute("manv").toString());
		     NhanVienDao nvDao=new NhanVienDao();
		     NhanVien nv=nvDao.layNhanVien(x);
			 PhanQuyen pq=nv.getMaPQ();
			 return pq.getMaPQ();
		}
		
		//them tin tuc moi
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
			
			int kt= kiemTraquyenDN();
			if(kt==1){
				  return "QLTinTuc_Admin?faces-redirect=true";
			  }
			else{
				  return "QLTinTuc_NVDH?faces-redirect=true";
			  } 
		}
		
		//sua tin tuc
		public String suaTinTuc(){
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date today;
			try {
				today = dateFormat.parse(dateFormat.format(new Date()));
				selectedTinTuc.setNgayDang(today);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//sua tin tuc dua vao doi tuong tin tuc+loai tin tuc duoc chon
			tinTucDao.suaTinTuc(selectedTinTuc,selectedLoaiTinTuc);
			
			int kt= kiemTraquyenDN();
			 
			  if(kt==1){
				  return "QLTinTuc_Admin?faces-redirect=true";
			  }
			  else{
				  return "QLTinTuc_NVDH?faces-redirect=true";
			  }
		}
		
		//xoa doi tuong tin tuc
		public String xoaTinTuc(TinTuc x){
			tinTucDao.xoaTinTuc(x);
			int kt= kiemTraquyenDN();
			 
			  if(kt==1){
				  return "QLTinTuc_Admin?faces-redirect=true";
			  }
			  else{
				  return "QLTinTuc_NVDH?faces-redirect=true";
			  }
		}
		
		//reset cac o input
		public String reset(){
			tinTucDao.reset(tinTuc);
			
			int kt= kiemTraquyenDN();
			 
			  if(kt==1){
				  return "QLTinTuc_Admin?faces-redirect=true";
			  }
			  else{
				  return "QLTinTuc_NVDH?faces-redirect=true";
			  }
		}
		
		//ham khi bam vao se cap nhat doi tuong tin tuc 
		public void capNhat(TinTuc x,String maLTT){
			selectedTinTuc=x;
			int ma=Integer.parseInt(maLTT);
			setSelectedLoaiTinTuc(loaiTinTucDao.layLTT(ma));
		}
		
		//upload image
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

		//copy hinh anh vao thu muc pics
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
