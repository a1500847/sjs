package pizzicato.model;

public class Tayte {
	int tayteId;
	String tNimi;
	//tama on testi voinko pushata
	public Tayte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tayte(int tayteId, String tNimi) {
		super();
		this.tayteId = tayteId;
		this.tNimi = tNimi;
	}
	
	
	public Tayte(String tNimi) {
		super();
		this.tNimi = tNimi;
	}

	public int getTayteId() {
		return tayteId;
	}

	public void setTayteId(int tayteId) {
		this.tayteId = tayteId;
	}

	public String gettNimi() {
		return tNimi;
	}

	public void settNimi(String tNimi) {
		this.tNimi = tNimi;
	}

	@Override
	public String toString() {
		return "Tayte [tayteId=" + tayteId + ", tNimi=" + tNimi + "]";
	}
	
	

}