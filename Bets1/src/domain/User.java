package domain;

import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class User {
	@Id
	@XmlID
	private String log;
	private String pass;
	private String NAN;
	private String korreoa;
	private String KZ;
	private String adina;
	private double dirua;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Apustua> erabiltzailearenApustuak;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<ApustuAnizkoitza> erabiltzailearenApustuAnizkoitzak;

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Mugimenduak gertatukoMugimendua;
	
	public User() {
		
	}
	public User(String log, String pass, String nAN, String korreoa, String kZ, String adina) {
		super();
		this.log = log;
		this.pass = pass;
		this.NAN = nAN;
		this.korreoa = korreoa;
		this.KZ = kZ;
		this.adina = adina;
		this.gertatukoMugimendua= new Mugimenduak();
		this.erabiltzailearenApustuak= new Vector<Apustua>();
		this.erabiltzailearenApustuAnizkoitzak = new Vector<ApustuAnizkoitza>();
}

	
	public Vector<ApustuAnizkoitza> getErabiltzailearenApustuAnizkoitzak() {
		return erabiltzailearenApustuAnizkoitzak;
	}
	public void setErabiltzailearenApustuAnizkoitzak(Vector<ApustuAnizkoitza> erabiltzailearenApustuAnizkoitzak) {
		this.erabiltzailearenApustuAnizkoitzak = erabiltzailearenApustuAnizkoitzak;
	}
	public Vector<Apustua> getErabiltzailearenApustuak() {
		return erabiltzailearenApustuak;
	}


	public void setErabiltzailearenApustuak(Vector<Apustua> erabiltzailearenApustuak) {
		this.erabiltzailearenApustuak = erabiltzailearenApustuak;
	}


	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	public String getNAN() {
		return NAN;
	}
	public void setNAN(String nAN) {
		NAN = nAN;
	}
	public String getKorreoa() {
		return korreoa;
	}
	public void setKorreoa(String korreoa) {
		this.korreoa = korreoa;
	}
	public String getKZ() {
		return KZ;
	}
	public void setKZ(String kZ) {
		KZ = kZ;
	}
	public String getAdina() {
		return adina;
	}
	public void setAdina(String adina) {
		this.adina = adina;
	}

	public double getDirua() {
		return dirua;
	}

	public void setDirua(double dirua) {
		this.dirua = dirua;
	}


	public Mugimenduak getGertatukoMugimendua() {
		return gertatukoMugimendua;
	}


	public void setGertatukoMugimendua(Mugimenduak gertatukoMugimendua) {
		this.gertatukoMugimendua = gertatukoMugimendua;
	}
	
}
