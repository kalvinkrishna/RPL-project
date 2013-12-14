package project;
public class Stacked {
	private String Event;
	private String deskripsi;
	private String  tanggal;
	private String tanggals; 
	private String tempat;
	private String start;
	private String end;
	private String jenis;
	private int No;
	
	
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getTanggals() {
		return tanggals;
	}
	public void setTanggals(String tanggals) {
		this.tanggals = tanggals;
	}
	public String getDeskripsi() {
		return deskripsi;
	}
	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}
	public String getTanggal() {
		return tanggal;
	}
	public String getJenis(){
		return jenis;
	}
	
	public void setTanggal(String tanggal2) {
		this.tanggal = tanggal2;
	}
	public String getTempat() {
		return tempat;
	}
	public void setTempat(String tempat) {
		this.tempat = tempat;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public void setJenis(String string) {
		this.jenis=string;
		
	}
	public void setNo(int no) {
		this.No=no;
	}	
	public int getNo(){
		return No;
	}
}
