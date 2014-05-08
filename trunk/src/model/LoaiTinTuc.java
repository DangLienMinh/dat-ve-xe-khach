package model;

import java.io.Serializable;

public class LoaiTinTuc implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int MaLTT;
	 private String TenLTT; 
	 
	 public LoaiTinTuc(){}
	 
	 public LoaiTinTuc(int MaLTT,String TenLTT){
		 this.MaLTT=MaLTT;
		 this.TenLTT=TenLTT;
	 }

	public int getMaLTT() {
		return MaLTT;
	}

	public void setMaLTT(int maLTT) {
		MaLTT = maLTT;
	}

	public String getTenLTT() {
		return TenLTT;
	}

	public void setTenLTT(String tenLTT) {
		TenLTT = tenLTT;
	}
	@Override
	public String toString(){
		return Integer.toString(MaLTT);
	}
}
