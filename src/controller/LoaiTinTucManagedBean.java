package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import model.LoaiTinTuc;
import dao.LoaiTinTucDao;

@ManagedBean(name= "loaiTTMBean")
@ViewScoped
public class LoaiTinTucManagedBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  LoaiTinTuc loaiTT=new LoaiTinTuc();
	private LoaiTinTucDao loaiTTDao=new LoaiTinTucDao();
	
	//tra ve dnh sach loai tin tuc hien thi tren combobox
	private List<SelectItem> selectOneItemLTT;
	
	//combobox loai tin tuc
	public List<SelectItem> getSelectOneItemLTT() {
		this.selectOneItemLTT=new ArrayList<SelectItem>();
		List<LoaiTinTuc> loaiTTs=loaiTTDao.selectItems();
		for(LoaiTinTuc ltt:loaiTTs){
			SelectItem selectItem=new SelectItem(ltt.getMaLTT(),ltt.getTenLTT());
			this.selectOneItemLTT.add(selectItem);
		}
		return selectOneItemLTT;
	}
	public LoaiTinTuc getLoaiTT() {
		return loaiTT;
	}
	public void setLoaiTT(LoaiTinTuc loaiTT) {
		this.loaiTT = loaiTT;
	}
	public LoaiTinTucDao getLoaiTTDao() {
		return loaiTTDao;
	}
	public void setLoaiTTDao(LoaiTinTucDao loaiTTDao) {
		this.loaiTTDao = loaiTTDao;
	}
	
	//tra ve ten loai tin tuc
	public String tenLoaiTT(String maLTT){
		int ma=Integer.parseInt(maLTT);
		return loaiTTDao.tenLoaiTT(ma);
	}
}
