package pizzicato.model;

import java.util.ArrayList;
import java.util.Date;


public class Tilaus {
	private int tilausId;
	private String status;
	private Date tilAjankohta = new Date();
	private ArrayList<PizzaTilaus> pizzatilaukset = new ArrayList<PizzaTilaus>();
	private String aEtunimi;
	private String aSukunimi;
	private String aPuh;
	private String aOsoite;
	private int aPostiNro;
	private String aPostiTmp;

	public Tilaus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tilaus(int tilausId, String status, Date tilAjankohta,
			ArrayList<PizzaTilaus> pizzatilaukset, String aEtunimi,
			String aSukunimi, String aPuh, String aOsoite, int aPostiNro,
			String aPostiTmp) {
		super();
		this.tilausId = tilausId;
		this.status = status;
		this.tilAjankohta = tilAjankohta;
		this.pizzatilaukset = pizzatilaukset;
		this.aEtunimi = aEtunimi;
		this.aSukunimi = aSukunimi;
		this.aPuh = aPuh;
		this.aOsoite = aOsoite;
		this.aPostiNro = aPostiNro;
		this.aPostiTmp = aPostiTmp;
	}
	
	public Tilaus(int tilausId, String status, Date tilAjankohta,
			String aEtunimi, String aSukunimi, String aPuh, String aOsoite,
			int aPostiNro, String aPostiTmp) {
		super();
		this.tilausId = tilausId;
		this.status = status;
		this.tilAjankohta = tilAjankohta;
		this.aEtunimi = aEtunimi;
		this.aSukunimi = aSukunimi;
		this.aPuh = aPuh;
		this.aOsoite = aOsoite;
		this.aPostiNro = aPostiNro;
		this.aPostiTmp = aPostiTmp;
	}

	public int getTilausId() {
		return tilausId;
	}

	public void setTilausId(int tilausId) {
		this.tilausId = tilausId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTilAjankohta() {
		return tilAjankohta;
	}

	public void setTilAjankohta(Date tilAjankohta) {
		this.tilAjankohta = tilAjankohta;
	}

	public ArrayList<PizzaTilaus> getPizzatilaukset() {
		return pizzatilaukset;
	}

	public void setPizzatilaukset(ArrayList<PizzaTilaus> pizzatilaukset) {
		this.pizzatilaukset = pizzatilaukset;
	}
	
	public void addPizzaTilaus(PizzaTilaus pizzatil) {
		this.pizzatilaukset.add(pizzatil);
	}
	
	public PizzaTilaus getPizzaTilaus(int idx) {
		return this.pizzatilaukset.get(idx);		
	}
	
	public int getPizzaTilLkm() {
		int PizzaTilLkm = this.pizzatilaukset.size();
		return PizzaTilLkm;
	}

	public String getaEtunimi() {
		return aEtunimi;
	}

	public void setaEtunimi(String aEtunimi) {
		this.aEtunimi = aEtunimi;
	}

	public String getaSukunimi() {
		return aSukunimi;
	}

	public void setaSukunimi(String aSukunimi) {
		this.aSukunimi = aSukunimi;
	}

	public String getaPuh() {
		return aPuh;
	}

	public void setaPuh(String aPuh) {
		this.aPuh = aPuh;
	}

	public String getaOsoite() {
		return aOsoite;
	}

	public void setaOsoite(String aOsoite) {
		this.aOsoite = aOsoite;
	}

	public int getaPostiNro() {
		return aPostiNro;
	}

	public void setaPostiNro(int aPostiNro) {
		this.aPostiNro = aPostiNro;
	}

	public String getaPostiTmp() {
		return aPostiTmp;
	}

	public void setaPostiTmp(String aPostiTmp) {
		this.aPostiTmp = aPostiTmp;
	}

	
		
	@Override
	public String toString() {
		return "Tilaus [tilausId=" + tilausId + ", status=" + status
				+ ", tilAjankohta=" + tilAjankohta + ", pizzatilaukset="
				+ pizzatilaukset + ", aEtunimi=" + aEtunimi + ", aSukunimi="
				+ aSukunimi + ", aPuh=" + aPuh + ", aOsoite=" + aOsoite
				+ ", aPostiNro=" + aPostiNro + ", aPostiTmp=" + aPostiTmp + "]";
	}
	
	
	
}
