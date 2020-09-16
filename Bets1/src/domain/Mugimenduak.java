package domain;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Mugimenduak {
	
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer mugiNumber;
	private ArrayList<String> gertatutakoMugimendua;
	

	public Mugimenduak() {
		super();
		this.gertatutakoMugimendua=new ArrayList<String>();
		
	}

	public ArrayList<String> getGertatutakoMugimenduak() {
		return gertatutakoMugimendua;
	}

	public void addGertatera(String s) {
		gertatutakoMugimendua.add(s);
	}
	
}
