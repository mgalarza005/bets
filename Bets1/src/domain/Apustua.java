package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;



@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Apustua {
	
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer apustuNumber;
	private String usrAnitz;
	private String kuotaAnitzaNumber;
	private String kuotaAnitza;
	

	private double apostuDirua;
	@XmlIDREF
	private User u;
	@XmlIDREF
	private Kuota k;
	
	

	public Apustua() {
	}
	
	public Apustua(double dirukop, User u, Kuota k) {
		super();
		this.apostuDirua = dirukop;
		this.u=u;
		this.kuotaAnitza="";
		this.usrAnitz="";
		this.k=k;
		
	}
	public Apustua(double dirukop, User u, Kuota k, String ka, String ua, String kan) {
		super();
		this.apostuDirua = dirukop;
		this.u=u;
		this.kuotaAnitza=ka;
		this.usrAnitz=ua;
		this.kuotaAnitzaNumber=kan;
		this.k=k;
		
	}

	public String getKuotaAnitzaNumber() {
		return kuotaAnitzaNumber;
	}

	public void setKuotaAnitzaNumber(String kuotaAnitzaNumber) {
		this.kuotaAnitzaNumber = kuotaAnitzaNumber;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}
	public String getKuotaAnitza() {
		return kuotaAnitza;
	}

	public void setKuotaAnitza(String kuotaAnitza) {
		this.kuotaAnitza = kuotaAnitza;
	}
	public Kuota getK() {
		return k;
	}

	public void setK(Kuota k) {
		this.k = k;
	}
	public String getUsrAnitz() {
		return usrAnitz;
	}

	public void setUsrAnitz(String usrAnitz) {
		this.usrAnitz = usrAnitz;
	}

	public double getDirukop() {
		return apostuDirua;
	}

	public void setDirukop(double dirukop) {
		this.apostuDirua = dirukop;
	}
	
	public int getApustuNumber() {
		return this.apustuNumber;
	}
	
	
	
	

}
