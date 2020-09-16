package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import exceptions.QuestionAlreadyExist;
@SuppressWarnings("serial")

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Event implements Serializable {
	
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer eventNumber;
	private String description; 
	private Date eventDate;
	private String kirola;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Question> questions=new Vector<Question>();

	public Vector<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Vector<Question> questions) {
		this.questions = questions;
	}

	public Event() {
		super();
	}
	

	public Event(Integer eventNumber, String description,Date eventDate, String kirola) {
		this.eventNumber = eventNumber;
		this.description = description;
		this.eventDate=eventDate;
		this.kirola = kirola;
	}
	public Event(String description,Date eventDate, String kirola, Vector<Question> q) {
		this.description = description;
		this.eventDate=eventDate;
		this.kirola = kirola;
		for(int i=0; i<q.size();i++) {
			
			Question q1= new Question(q.get(i).getQuestion(),q.get(i).getBetMinimum(),q.get(i).getEvent());
			Vector<Kuota> aux= q.get(i).getKuotak();
			Vector<Kuota> aux2=new Vector<Kuota>();
			for(int w=0;w<aux.size();w++) {
				Kuota k1=new Kuota(aux.get(w).getEmaitza(), aux.get(w).getOnura(),q1);
				aux2.add(k1);
			}
			q1.setKuotak(aux2);		
			this.questions.addElement(q1);
		}
	}
	
	public Event( String description,Date eventDate, String kirola) {
		this.description = description;
		this.eventDate=eventDate;
		this.kirola = kirola;
	}

	public String getKirola() {
		return kirola;
	}

	public void setKirola(String kirola) {
		this.kirola = kirola;
	}

	public Integer getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(Integer eventNumber) {
		this.eventNumber = eventNumber;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	
	public String toString(){
		return eventNumber+";"+description+this.questions.toString();
	}
	
	/**
	 * This method creates a bet with a question, minimum bet ammount and percentual profit
	 * 
	 * @param question to be added to the event
	 * @param betMinimum of that question
	 * @return Bet
	 */
	public Question addQuestion(String question, float betMinimum)  {
        Question q=new Question(question,betMinimum, this);
        questions.add(q);
        return q;
	}

	
	/**
	 * This method checks if the question already exists for that event
	 * 
	 * @param question that needs to be checked if there exists
	 * @return true if the question exists and false in other case
	 */
	public boolean DoesQuestionExists(String question)  {	
		for (Question q:this.getQuestions()){
			if (q.getQuestion().compareTo(question)==0)
				return true;
		}
		return false;
	}
		

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eventNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (eventNumber != other.eventNumber)
			return false;
		return true;
	}
	
	
	
	

}
