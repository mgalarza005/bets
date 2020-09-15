package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class User {
	@Id
	private String log;
	private String pass;
	private String NAN;
	private String korreoa;
	private String KZ;
	private String adina;
	private double dirua;
	
	
	public User(String log, String pass, String nAN, String korreoa, String kZ, String adina) {
		super();
		this.log = log;
		this.pass = pass;
		this.NAN = nAN;
		this.korreoa = korreoa;
		this.KZ = kZ;
		this.adina = adina;
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
	
}
