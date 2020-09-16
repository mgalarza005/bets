package domain;

import java.io.*;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Question implements Serializable {
	
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id  @GeneratedValue
	private Integer questionNumber;
	private String question; 
	private float betMinimum;
	private String result;  
	@XmlIDREF
	private Event event;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Kuota> kuotak = new Vector<Kuota>();
	
	
	public Question(){
		super();
	}
	
	public Question(Integer queryNumber, String query, float betMinimum, Event event) {
		super();
		this.questionNumber = queryNumber;
		this.question = query;
		this.betMinimum=betMinimum;
		this.event = event;
	}
	public Question(String query, float betMinimum, Event event, Vector<Kuota> k) {
		super();
		this.question = query;
		this.betMinimum=betMinimum;
		this.event = event;
		for(int i=0; i<k.size();i++) {
			Kuota k1= new Kuota(k.get(i).getOnura(),k.get(i).getEmaitza());
					
			this.kuotak.addElement(k1);
		}
	}
	public Question(String query, float betMinimum,  Event event) {
		super();
		this.question = query;
		this.betMinimum = betMinimum;

		//this.event = event;
	}
	
	public Vector<Kuota> getKuotak() {
		return kuotak;
	}
	
	public void setKuotak(Vector<Kuota> kuotak) {
		this.kuotak = kuotak;
	}

	/**
	 * Get the  number of the question
	 * 
	 * @return the question number
	 */
	public Integer getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * Set the bet number to a question
	 * 
	 * @param questionNumber to be setted
	 */
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}
	/**
	 * Get the question description of the bet
	 * 
	 * @return the bet question
	 */

	public String getQuestion() {
		return question;
	}

	/**
	 * Set the question description of the bet
	 * 
	 * @param question to be setted
	 */	
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * Get the minimun ammount of the bet
	 * 
	 * @return the minimum bet ammount
	 */
	
	public float getBetMinimum() {
		return betMinimum;
	}

	/**
	 * Get the minimun ammount of the bet
	 * 
	 * @param  betMinimum minimum bet ammount to be setted
	 */

	public void setBetMinimum(float betMinimum) {
		this.betMinimum = betMinimum;
	}

	/**
	 * Get the result of the  query
	 * 
	 * @return the the query result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Get the result of the  query
	 * 
	 * @param result of the query to be setted
	 */
	
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * Get the event associated to the bet
	 * 
	 * @return the associated event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * Set the event associated to the bet
	 * 
	 * @param event to associate to the bet
	 */
	public void setEvent(Event event) {
		this.event = event;
	}
	
	public Kuota addKuota(String kuota)  {
        Kuota k=new Kuota();
        kuotak.add(k);
        return k;
	}

	public String toString(){
		return questionNumber+";"+question+";"+Float.toString(betMinimum);
	}
	public String toString1(){
		return questionNumber+ "." +question+ " ";
	}
	public Kuota addKuota(String emaitza, String onura, Question g) {
		Kuota k= new Kuota(emaitza, onura,g);
		kuotak.add(k);
		return k;
	}
	
}