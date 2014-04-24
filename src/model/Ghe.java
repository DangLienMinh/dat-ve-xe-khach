package model;

import java.util.*;

public class Ghe {
	 private String MaGhe;
	 private String TenGhe; 
	 private Set<DatVe> DatVe = new HashSet<DatVe>(0);
	 
	 public Ghe(){}
	 
	 public Ghe(String MaGhe,String TenGhe){
		 this.MaGhe=MaGhe;
		 this.TenGhe=TenGhe;
	 }

	public String getMaGhe() {
		return MaGhe;
	}

	public void setMaGhe(String maGhe) {
		MaGhe = maGhe;
	}

	public String getTenGhe() {
		return TenGhe;
	}

	public void setTenGhe(String tenGhe) {
		TenGhe = tenGhe;
	}

	public Set<DatVe> getDatVe() {
		return DatVe;
	}

	public void setDatVe(Set<DatVe> datVe) {
		DatVe = datVe;
	}
}
