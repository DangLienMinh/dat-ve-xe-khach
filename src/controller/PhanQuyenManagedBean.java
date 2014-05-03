package controller;

import java.util.List;

import hibernateUtil.HibernateUtil;
import model.PhanQuyen;
import dao.PhanQuyenDao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
	
	public void themPhanQuyen(){
		phanQuyenDao.themPhanQuyen(phanQuyen);
	}
	public void suaPhanQuyen(){
		phanQuyenDao.suaPhanQuyen(phanQuyen);
	}
	public List<SelectItem> getSelectOneItemPQ() {
		this.selectOneItemPQ=new ArrayList<SelectItem>();
		List<PhanQuyen> phanQuyens=phanQuyenDao.selectItems();
		for(PhanQuyen pq:phanQuyens){
			SelectItem selectItem=new SelectItem(pq.getMaPQ(),pq.getQuyen());
			this.selectOneItemPQ.add(selectItem);
		}
		return selectOneItemPQ;
	}


	
//	public PhanQuyen layThongTin(){
//		PhanQuyen phanQuyen=new PhanQuyen();
//		phanQuyen.setQuyen(this.getQuyen());
//		return phanQuyen;
//	}
	
//	public void themPhanQuyen() {
//		PhanQuyen phanQuyen=new PhanQuyen();
//		phanQuyen=layThongTin();  
//        Transaction trns = null;
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        try {
//            trns = session.beginTransaction();
//            session.save(phanQuyen);
//            session.getTransaction().commit();
//        } catch (RuntimeException e) {
//            if (trns != null) {
//                trns.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            session.flush();
//            session.close();
//        }
//    }
//	
//	public void suaPhanQuyen() {
//		PhanQuyen phanQuyen = new PhanQuyen();
//		phanQuyen=layThongTin();  
//	    Transaction trns = null;
//	    Session session = HibernateUtil.getSessionFactory().openSession();
//	    try {
//	        trns = session.beginTransaction();
//	        session.update(phanQuyen);
//	        session.getTransaction().commit();
//	    } catch (RuntimeException e) {
//	        if (trns != null) {
//	            trns.rollback();
//	        }
//	        e.printStackTrace();
//	    } finally {
//	        session.flush();
//	        session.close();
//	    }
//	}
}
