package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Kuota {
	
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private int kuotaNumber;
	private String onura;
	private String emaitza;
	private Question galdera;
	
	public Kuota(){
		super();
	}
	public Kuota(String emaitza, String onura) {
		super();
		this.emaitza = emaitza;
		this.onura = onura;

		//this.event = event;
	}
	
	
	public Kuota(int kuotaNumber, String emaitza, String onura, Question galdera){
		this.kuotaNumber = kuotaNumber;
		this.emaitza = emaitza;   
		this.onura = onura;
		this.galdera = galdera;
	}
	public String toString(){
		return "Onura: "+onura+" eta emaitza: "+emaitza;
	}
}
