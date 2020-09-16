package domain;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD )
public class KuotaContainer {
	private Kuota kuota;
	private ArrayList<Apustua> apustuLista;
	
	public KuotaContainer(Kuota k1) {
		this.kuota = k1;
		this.apustuLista=kuota.getApuestuLista(); 
		}
	public KuotaContainer() {
		kuota = null;
		apustuLista = null; 
	}
	public Kuota getKuota(){
		return kuota;
	}
	public ArrayList<Apustua> getApustuLista(){
		return apustuLista; }
	public String toString(){
		return kuota+"/"+apustuLista; 
	}
	
}
