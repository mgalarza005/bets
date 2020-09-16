package domain;

import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class ApustuAnizkoitza {
	
	
	@Id @GeneratedValue @XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	private Integer apustuAnizkoitzNumber;
	private Integer zenbatAsmatu;
	private double apostuDirua;
	@XmlIDREF
	private User u;
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	//@XmlIDREF
	private Vector<Apustua> bets;
	
	
	public ApustuAnizkoitza(Double aDirua, User u1, Vector<Apustua> b) {
		
		super();
		this.apostuDirua = aDirua;
		this.u = u1;
		this.bets = b;
		this.zenbatAsmatu = 0;
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ApustuAnizkoitza() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	public Integer getApustuAnizkoitzNumber() {
		return apustuAnizkoitzNumber;
	}
	public void setApustuAnizkoitzNumber(Integer apustuAnizkoitzNumber) {
		this.apustuAnizkoitzNumber = apustuAnizkoitzNumber;
	}
	public Integer getZenbatAsmatu() {
		return zenbatAsmatu;
	}
	public void setZenbatAsmatu(Integer zenbatAsmatu) {
		this.zenbatAsmatu = zenbatAsmatu;
	}
	public double getApostuDirua() {
		return apostuDirua;
	}
	public void setApostuDirua(double apostuDirua) {
		this.apostuDirua = apostuDirua;
	}
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}
	public Vector<Apustua> getBets() {
		return bets;
	}
	public void setBets(Vector<Apustua> bets) {
		this.bets = bets;
	}
	
	
	
}
