package dataAccess;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import domain.*;

import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c;

	public DataAccess(boolean initializeMode)  {
		
		c=ConfigXML.getInstance();
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode)
			fileName=fileName+";drop";
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
	}

	public DataAccess()  {	
		 new DataAccess(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		try {

			Event ev1=new Event(1, "Atletico-Athletic", newDate(2019
					
					,1,17), "Futbola");
			Event ev2=new Event(2, "Eibar-Barcelona", newDate(2019,1,17), "Futbola");
			Event ev3=new Event(3, "Getafe-Celta", newDate(2019,1,17), "Futbola");
			Event ev4=new Event(4, "Alav�s-Deportivo", newDate(2019,1,17), "Futbola");
			Event ev5=new Event(5, "Espa�ol-Villareal", newDate(2019,1,17), "Futbola");
			Event ev6=new Event(6, "Las Palmas-Sevilla", newDate(2019,1,17), "Futbola");
			Event ev7=new Event(7, "Malaga-Valencia", newDate(2019,1,17), "Futbola");
			Event ev8=new Event(8, "Girona-Legan�s", newDate(2019,1,17), "Futbola");
			Event ev9=new Event(9, "Real Sociedad-Levante", newDate(2019,1,17), "Futbola");
			Event ev10=new Event(10, "Betis-Real Madrid", newDate(2019,1,17), "Futbola");

			Event ev11=new Event(11, "Atletico-Athletic", newDate(2019,2,1), "Futbola");
			Event ev12=new Event(12, "Eibar-Barcelona", newDate(2019,2,1), "Futbola");
			Event ev13=new Event(13, "Getafe-Celta", newDate(2019,2,1), "Futbola");
			Event ev14=new Event(14, "Alav�s-Deportivo", newDate(2019,2,1), "Futbola");
			Event ev15=new Event(15, "Espa�ol-Villareal", newDate(2019,2,1), "Futbola");
			Event ev16=new Event(16, "Las Palmas-Sevilla", newDate(2019,2,1), "Futbola");
			

			Event ev17=new Event(17, "Malaga-Valencia", newDate(2019,2,31), "Futbola");
			Event ev18=new Event(18, "Girona-Legan�s", newDate(2019,2,31), "Futbola");
			Event ev19=new Event(19, "Real Sociedad-Levante", newDate(2019,2,31), "Futbola");
			Event ev20=new Event(20, "Betis-Real Madrid", newDate(2019,2,31), "Futbola");
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
					
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("�Quien ganar� el partido?",1);
				q2=ev1.addQuestion("�Quien meter� el primer gol?",2);
				q3=ev11.addQuestion("�Quien ganar� el partido?",1);
				q4=ev11.addQuestion("�Cu�ntos goles se marcar�n?",2);
				q5=ev17.addQuestion("�Quien ganar� el partido?",1);
				q6=ev17.addQuestion("�Habr� goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
			}
			User u = new User("Jon", "amaia", "", "", "", "");
			Worker w = new Worker ("Langile1","l1");
			Admin ad = new Admin ("Admin1","a1");
			db.persist(u);
			db.persist(w);
			db.persist(ad);
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);
	
	        
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);			
			
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
			Event ev = db.find(Event.class, event.getEventNumber());
			
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
			db.getTransaction().begin();
			Question q = ev.addQuestion(question, betMinimum);
			//db.persist(q);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
		
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;

	}
	/**
     * This method check if log user exists in the database with given password
     * 
     * @param log user name
     * @param pass user password
     * @return user exists or not in the database with the given password
     */
	public boolean isLoginUser(String log, String pass) {
			System.out.println(log +" "+ pass);
			User amaia= db.find(User.class, log);
			if(amaia == null) {
				return false;
			}else {
				if(amaia.getPass().compareTo(pass)==0)
					return true;
			}
			return false;		
	}
	
	public boolean isLoginWorker(String log, String pass) {
		System.out.println(log +" "+ pass);
		Worker amaia= db.find(Worker.class, log);
		if(amaia == null) {
			return false;
		}else {
			if(amaia.getPass().compareTo(pass)==0)
				return true;
		}
		return false;	
	}

	
	public boolean isLoginAdmin(String log, String pass) {
		System.out.println(log +" "+ pass);
		Admin amaia= db.find(Admin.class, log);
		if(amaia == null) {
			return false;
		}else {
			if(amaia.getPass().compareTo(pass)==0)
				return true;
		}
		return false;		
	}
	
	
	
	public boolean izenaOndo (String izena){
		
		User erab = db.find(User.class, izena);
		if(erab == null){
			System.out.println(izena);
			return true;
		}
		return false;
	}
	public boolean workerIzenaOndo(String izena) {
		Worker w= db.find(Worker.class, izena);
		if(w== null) {
			System.out.println(izena); 
			return true;
		}else {
			return false;
		}
		
	}
	public void emaitzaIpini(String emaitza, Question g) {
		db.getTransaction().begin();
		Question q= db.find(Question.class, g.getQuestionNumber());
		q.setResult(emaitza);
		db.getTransaction().commit();
		System.out.println("Emaitza gorde da");
	}
	
		
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	
	private Date newDate(int year,int month,int day) {

	     Calendar calendar = Calendar.getInstance();
	     calendar.set(year, month, day,0,0,0);
	     calendar.set(Calendar.MILLISECOND, 0);

	     return calendar.getTime();
	}

	public void erregistratu(User erab) {

		db.getTransaction().begin();
		db.persist(erab);
		db.getTransaction().commit();
		System.out.println("Erregistratu zara!!!!!!");
	}
	public void langileBerriaSortu(Worker w) {
		db.getTransaction().begin();
		db.persist(w);
		db.getTransaction().commit();
		System.out.println("Langile berria sortu duzu!!!!!!");
	}

	public void gertaeraSortu(Event e) {
		db.getTransaction().begin();
		db.persist(e);
		db.getTransaction().commit();
		System.out.println("Gertaera gordeta");
		
	}
	
	public Kuota kuotaSortu(String emaitza, String onura, Question g){
		
			
		Question q1= db.find(Question.class, g.getQuestionNumber());
			   	 
	   	 db.getTransaction().begin();
	   	 Kuota k= q1.addKuota(emaitza, onura);
	   	 db.persist(q1);
	   	 db.getTransaction().commit();
	   	 System.out.println("Kuota sartu da!!!!!");
	   	 return k;
	}

	public void diruaSartu(String usr, double dirua) {
		db.getTransaction().begin();
		User u= db.find(User.class, usr);
		u.setDirua(u.getDirua()+dirua);
		db.getTransaction().commit();
		System.out.println("Dirua gorde da.");
		
	}

}
