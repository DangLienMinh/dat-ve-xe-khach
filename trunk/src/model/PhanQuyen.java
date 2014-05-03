package model;

public class PhanQuyen {
	   private int MaPQ;
	   private String Quyen; 

	   public PhanQuyen() {}
	   
	   public PhanQuyen(int MaPQ,String Quyen) {
		   this.MaPQ=MaPQ;
		   this.Quyen=Quyen;
	   }

		public int getMaPQ() {
			return MaPQ;
		}
	
		public void setMaPQ(int maPQ) {
			MaPQ = maPQ;
		}
	
		public String getQuyen() {
			return Quyen;
		}
	
		public void setQuyen(String quyen) {
			Quyen = quyen;
		}
		@Override
		public String toString(){
			return Integer.toString(MaPQ);
			
		}
}

