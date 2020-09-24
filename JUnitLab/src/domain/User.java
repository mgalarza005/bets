package domain;

import java.util.ArrayList;

public class User {
	public User(int id, int telefonoa, ArrayList<PurchasedArticle> purArt) {
		super();
		this.id = id;
		this.telefonoa = telefonoa;
		this.purArt = purArt;
	}
	private int id;
	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public int getTelefonoa() {
		return telefonoa;
	}
	public void setTelefonoa(int telefonoa) {
		this.telefonoa = telefonoa;
	}
	public ArrayList<PurchasedArticle> getPurArt() {
		return purArt;
	}
	public void setPurArt(ArrayList<PurchasedArticle> purArt) {
		this.purArt = purArt;
	}
	private int telefonoa;
	private ArrayList<PurchasedArticle> purArt;
}
