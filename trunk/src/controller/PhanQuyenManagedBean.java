package controller;

import java.util.List;
import model.PhanQuyen;
import dao.PhanQuyenDao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.*;
import javax.faces.model.*;


@ManagedBean(name= "phanQuyenMBean")
@SessionScoped
public class PhanQuyenManagedBean {
	
	private  PhanQuyen phanQuyen=new PhanQuyen();
	private PhanQuyenDao phanQuyenDao=new PhanQuyenDao();
	private List<SelectItem> selectOneItemPQ;
	
	public PhanQuyen getPhanQuyen() {
		return phanQuyen;
	}
	public void setPhanQuyen(PhanQuyen phanQuyen) {
		this.phanQuyen = phanQuyen;
	}
	public PhanQuyenDao getPhanQuyenDao() {
		return phanQuyenDao;
	}
	public void setPhanQuyenDao(PhanQuyenDao phanQuyenDao) {
		this.phanQuyenDao = phanQuyenDao;
	}
	
	//tra ve danh sach phan quyen
	public List<SelectItem> getSelectOneItemPQ() {
		this.selectOneItemPQ=new ArrayList<SelectItem>();
		List<PhanQuyen> phanQuyens=phanQuyenDao.selectItems();
		for(PhanQuyen pq:phanQuyens){
			SelectItem selectItem=new SelectItem(pq.getMaPQ(),pq.getQuyen());
			this.selectOneItemPQ.add(selectItem);
		}
		return selectOneItemPQ;
	}
}
