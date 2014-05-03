package controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.TaiXe;
import dao.TaiXeDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.TaiXe;
import hibernateUtil.HibernateUtil;
@ManagedBean(name= "taiXeMBean")
@SessionScoped
public class TaiXeManagedBean {
	
	private TaiXe taiXe=new TaiXe();
	private TaiXeDao taiXeDao=new TaiXeDao();
	
	public TaiXeManagedBean(){
	}
	public TaiXe getTaiXe() {
		return taiXe;
	}
	public void setTaiXe(TaiXe taiXe) {
		this.taiXe = taiXe;
	}
	public TaiXeDao getTaiXeDao() {
		return taiXeDao;
	}
	public void setTaiXeDao(TaiXeDao taiXeDao) {
		this.taiXeDao = taiXeDao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taiXe == null) ? 0 : taiXe.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiXeManagedBean other = (TaiXeManagedBean) obj;
		if (taiXe == null) {
			if (other.taiXe != null)
				return false;
		} else if (!taiXe.equals(other.taiXe))
			return false;
		return true;
	}
	
	public void themTaiXe(){
		taiXeDao.themTaiXe(taiXe);
	}
	
	public void xoaTaiXe(){
		taiXeDao.xoaTaiXe(taiXe);
	}
	
	public void suaTaiXe(){
		taiXeDao.suaTaiXe(taiXe);
	}
	
//	public void danhSachTaiXe(){
//		taiXeDao.listEmployees();
//	}
	

	public void reset() {
		taiXeDao.xoaTaiXe(taiXe);
	}
}
