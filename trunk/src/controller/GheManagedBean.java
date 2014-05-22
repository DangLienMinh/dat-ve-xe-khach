package controller;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name= "gheMBean")
@SessionScoped
public class GheManagedBean {
	
	//tinh trang ghe ngoi va giuong nam
	private String []ttNgoi=new String[45];
	private String []ttNam=new String[40];
	
	//luu thong tin doi tuong ghe duoc chon
	private String selectedGhe;

	public String [] getTtNgoi() {
		return ttNgoi;
	}

	public void setTtNgoi(String [] ttNgoi) {
		this.ttNgoi = ttNgoi;
	}
	
	//luu ma ghe duoc chon va chuyen ve trang thongtindatve
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
