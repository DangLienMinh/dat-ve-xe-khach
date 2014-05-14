package controller;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name= "gheMBean")
@SessionScoped
public class GheManagedBean {
	//tinh trang
	private String []ttNgoi=new String[45];
	private String []ttNam=new String[40];
	private String selectedGhe;
//	//dai dien ghe
//	private int []gheNgoi=new int[45];
//	
//	public GheManagedBean(){
//		int x=1;
//		for (int i = 0; i < gheNgoi.length; i++) {
//			gheNgoi[i]=x;
//			++x;
//		}
//	}

	public String [] getTtNgoi() {
		return ttNgoi;
	}

	public void setTtNgoi(String [] ttNgoi) {
		this.ttNgoi = ttNgoi;
	}

//	public int [] getGheNgoi() {
//		return gheNgoi;
//	}
//
//	public void setGheNgoi(int [] gheNgoi) {
//		this.gheNgoi = gheNgoi;
//	}
	
	public String giaTriButton(ActionEvent event){
		selectedGhe=(String)event.getComponent().getAttributes().get("ngoi");
		return "thongTinDatVe";
	}

	public String getSelectedGhe() {
		return selectedGhe;
	}

	public void setSelectedGhe(String selectedGhe) {
		this.selectedGhe = selectedGhe;
	}

	public String [] getTtNam() {
		return ttNam;
	}

	public void setTtNam(String [] ttNam) {
		this.ttNam = ttNam;
	}
}
