package controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import model.LoaiXe;
import dao.LoaiXeDao;

@ManagedBean(name= "loaiXeMBean")
@SessionScoped
public class LoaiXeManagedBean{
	private  LoaiXe loaiXe=new LoaiXe();
	private LoaiXeDao loaiXeDao=new LoaiXeDao();
	private List<SelectItem> selectOneItemLX;
	
	

	public LoaiXe getLoaiXe() {
		return loaiXe;
	}

	public void setLoaiXe(LoaiXe loaiXe) {
		this.loaiXe = loaiXe;
	}

	public LoaiXeDao getLoaiXeDao() {
		return loaiXeDao;
	}

	public void setLoaiXeDao(LoaiXeDao loaiXeDao) {
		this.loaiXeDao = loaiXeDao;
	}

	public List<SelectItem> getSelectOneItemLX() {
		this.selectOneItemLX=new ArrayList<SelectItem>();
		List<LoaiXe> loaiXes=loaiXeDao.selectItems();
		for(LoaiXe lx:loaiXes){
			SelectItem selectItem=new SelectItem(lx.getMaLoaiXe(),lx.getTenLoaiXe());
			this.selectOneItemLX.add(selectItem);
		}
		return selectOneItemLX;
	}

	

}
