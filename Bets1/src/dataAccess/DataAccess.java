package dataAccess;


import java.util.ArrayList;
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
		Kuota k= q1.addKuota(emaitza, onura,g);
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

	public void KuotaEzabatu(Kuota ezabatzekoKuota) {
		db.getTransaction().begin();
		db.remove(db.find(Kuota.class, ezabatzekoKuota));
		int zk=ezabatzekoKuota.getKNumber();
		TypedQuery<Kuota> query= db.createQuery("DELETE FROM Kuota k WHERE k.kuotaNumber=?1",Kuota.class);
		query.setParameter(1, zk);
		query.executeUpdate();
//		Kuota aux= db.find(Kuota.class, ezabatzekoKuota.getKNumber());
//		db.remove(aux);
		db.getTransaction().commit();
		System.out.println("Kuota ezabatu da.");
	}

	public boolean diruaNahikorik(String usr, double d, Kuota k) { //Diru nahikorik duen konprobatu, eta badauka kendu egiten zaio
		db.getTransaction().begin();
		User u= db.find(User.class, usr);
		Kuota k1= db.find(Kuota.class, k);

		if(u.getDirua()>=d) {
			u.setDirua(u.getDirua()-d);
			k1.setLortutakoEtekina(d);
			db.getTransaction().commit();

			return true;
		}else {
			return false;
		}

	}



	public void mugimenduaErabiltzaileariGehitu(String usr, double d, Kuota k, Question g) {
		db.getTransaction().begin();
		User u = db.find(User.class, usr);
		String m= "Egindako apustua: " + "Galdera: " + g.toString1() + "eta  Kuota: " + k.toString() + " --> Zuk apustutako dirua:   " + Double.toString(d)+"€";
		Mugimenduak mu=u.getGertatukoMugimendua();
		mu.addGertatera(m);
		u.setGertatukoMugimendua(mu);
		db.persist(u);
		db.persist(mu);
		db.getTransaction().commit();
		System.out.println("Mugimendua sartu da!!!!!");	
	}

	public User lortuErabiltzailea(String usr) {
		db.getTransaction().begin();
		User u = db.find(User.class, usr);
		db.getTransaction().commit();
		return u;

	}

	public ArrayList<String> lortuMugimenduak(String usr) {
		ArrayList<String> emaitza=new ArrayList<String>();
		User u1 = db.find(User.class, usr);
		for(int i=0;i<u1.getGertatukoMugimendua().getGertatutakoMugimenduak().size();i++) {
			emaitza.add(u1.getGertatukoMugimendua().getGertatutakoMugimenduak().get(i));
		}
		return emaitza;
	}

	public void mugimenduaErabiltzaileariGehituDirua(String usr, double dirua) {
		db.getTransaction().begin();
		User u = db.find(User.class, usr);
		String m= "Sartutako dirua: " + Double.toString(dirua);
		Mugimenduak mu=u.getGertatukoMugimendua();
		mu.addGertatera(m);
		u.setGertatukoMugimendua(mu);
		db.persist(u);
		db.persist(mu);
		db.getTransaction().commit();
	}

	public Kuota lortuKuotaIrabzalea(Question g) {
		db.getTransaction().begin();
		Question q = db.find(Question.class, g);
		String emai=q.getResult();
		Vector<Kuota> listaK=q.getKuotak();
		for(int i=0; i<listaK.size();i++) {
			if(listaK.get(i).getEmaitza().equals(emai)){
				return listaK.get(i);
			}
		}

		return null;
	}

	

	public void diruaErabiltzaileeiItzuli(Kuota selectedItem) {
		db.getTransaction().begin();
		Kuota k1 = db.find(Kuota.class, selectedItem);
		User u;
		double dirua;
		System.out.println(k1);
		System.out.println(selectedItem);
		for(int i=0;i<k1.getApuestuLista().size();i++) {
			u=k1.getApuestuLista().get(i).getU();
			dirua=k1.getApuestuLista().get(i).getDirukop();
			u.getGertatukoMugimendua().addGertatera("Dirua bueltatu zaizu, kuota ezabatuko delako:           " +dirua +"€");
			System.out.println("Apuestuaren dirua hau da"+ dirua + u.toString());
			u.setDirua(u.getDirua() + dirua);
		}
		db.getTransaction().commit();
	}

	public void galderaEzabatu(Question ezabatzekoGaldera) {
		
		
		System.out.println("galderaEzabatuko objetua" + ezabatzekoGaldera);
		db.getTransaction().begin();
		db.remove(db.find(Question.class, ezabatzekoGaldera.getQuestionNumber()));
		int zk=ezabatzekoGaldera.getQuestionNumber();
		TypedQuery<Question> query= db.createQuery("DELETE FROM Question q WHERE q.questionNumber=?1",Question.class);
		query.setParameter(1, zk);
		query.executeUpdate();

		db.getTransaction().commit();
		System.out.println("Galdera ezabatu da.");
		
		
	}

	public void gertaeraEzabatu(Event g) {
		

		db.getTransaction().begin();
		db.remove(db.find(Event.class, g));
		int zk=g.getEventNumber();
		TypedQuery<Event> query= db.createQuery("DELETE FROM Event g WHERE g.eventNumber=?1",Event.class);
		query.setParameter(1, zk);
		query.executeUpdate();

		db.getTransaction().commit();
		System.out.println("Gertaera ezabatu da.");
		
	}
	private void apustuaEzabatu(Apustua a) {
		db.remove(db.find(Apustua.class, a));
		int an=a.getApustuNumber();
		TypedQuery<Apustua> query= db.createQuery("DELETE FROM Apustua a WHERE a.apustuNumber=?1",Apustua.class);
		query.setParameter(1, an);
		query.executeUpdate();
		
	}

	public void erabiltzaileaEzabatu(String text) {
		db.getTransaction().begin();
		User u= db.find(User.class, text);
		for(int i=0; i<u.getErabiltzailearenApustuak().size(); i++) {
			apustuaEzabatu(u.getErabiltzailearenApustuak().get(i));
		}
		db.remove(u);
		TypedQuery<User> query= db.createQuery("DELETE FROM User u WHERE u.log=?1",User.class);
		query.setParameter(1, text);
		query.executeUpdate();
		db.getTransaction().commit();
		System.out.println("Erabiltzailea ezabatu da.");
		
	}

	public void gertaeraBikoiztu(Event g, Date data) {
		db.getTransaction().begin();
		Event g1=new Event(g.getDescription(),data,g.getKirola(),g.getQuestions());
		db.persist(g1);
		db.getTransaction().commit();
		System.out.println("Gertaera gordeta");

		
	}

	public Vector<Kuota> kuotakLortu(Question g) {
		db.getTransaction().begin();		
		Question gald = db.find(Question.class, g);	
		Vector<Kuota> k = gald.getKuotak(); 
		db.getTransaction().commit();
		return k;
	}

	public User lortuUser(String usr) {
		db.getTransaction().begin();		
		User u1 = db.find(User.class, usr);
		//db.getTransaction().commit();
		return u1;
	}

	public void apustuaAnitzaGorde(String usr, double d, Vector<Apustua> apustuLista) {
		db.getTransaction().begin();		
		User u1 = db.find(User.class, usr);
		ApustuAnizkoitza aa1 = new ApustuAnizkoitza(d, u1, apustuLista);
		System.out.println(u1.getErabiltzailearenApustuAnizkoitzak().toString() + "  Apustua anizkoitzak!!!!!");
		System.out.println(aa1.toString() + "  Apustua");
		u1.getErabiltzailearenApustuAnizkoitzak().addElement(aa1);
		db.persist(u1);
		db.persist(aa1);
		db.getTransaction().commit();
		
	}

	public void mugimenduaErabiltzaileariGehituApustuAnitza(String usr, double d, Vector<Apustua> apustuLista) {
		db.getTransaction().begin();
		User u = db.find(User.class, usr);
		String m= "Egindako apustua anizkotza: " + "Apustu kop.: " + apustuLista.size() + " --> Zuk apustutako dirua:   " + Double.toString(d)+"€";
		Mugimenduak mu=u.getGertatukoMugimendua();
		mu.addGertatera(m);
		u.setGertatukoMugimendua(mu);
		db.persist(u);
		db.persist(mu);
		db.getTransaction().commit();
		System.out.println("Mugimendu anizkoitza sartu da!!!!!");	
		
	}
	public void kalkulatuDirua(Kuota k, Question q) {
		db.getTransaction().begin();
		Kuota k1 = db.find(Kuota.class, k);		
		User u;
		for(int i=0;i<k1.getApuestuLista().size();i++) {
			
			System.out.println(k1.getApuestuLista().get(i).toString() + "    geti");
			
			u=k1.getApuestuLista().get(i).getU();
			String user= k1.getApuestuLista().get(i).getUsrAnitz();
			System.out.println(user + "user parametroa");
			User u1= db.find(User.class, user);
			System.out.println(u1+ "user");
			Integer aux = 0;
			if(k.getApuestuLista().get(i).getDirukop() == 0.0) {
				//Apustua ap = k.getApuestuLista().get(i);
				//onarte ondo U''
				for(int j = 0; j<u1.getErabiltzailearenApustuAnizkoitzak().size(); j++) {
					for(int w=0; w<u1.getErabiltzailearenApustuAnizkoitzak().get(j).getBets().size(); w++) {
						if (u1.getErabiltzailearenApustuAnizkoitzak().get(j).getBets().get(w).getKuotaAnitza().equals(k.getEmaitza())) {
							aux = u1.getErabiltzailearenApustuAnizkoitzak().get(j).getZenbatAsmatu();
							aux++;
							u1.getErabiltzailearenApustuAnizkoitzak().get(j).setZenbatAsmatu(aux);
							if(aux == u1.getErabiltzailearenApustuAnizkoitzak().get(j).getBets().size()) {
								Double bider=1.0;
								Vector<Apustua> bek = u1.getErabiltzailearenApustuAnizkoitzak().get(j).getBets();
								for(int v=0; v<bek.size(); v++) {
									Integer b = Integer.parseInt(bek.get(v).getKuotaAnitzaNumber());
									System.out.println(b + " KuotzaAnitzaNuber");
									Kuota ku = db.find(Kuota.class, b);
									System.out.println(ku.toString() + "    !!!");
									System.out.println(bider + "bider");
									bider = bider * Double.parseDouble((ku.getOnura()));
									System.out.println(bider + "  Kuotaren akum 16");
									
								}
								Double aux2 = u1.getDirua();
								u1.setDirua(aux2 + (bider * u1.getErabiltzailearenApustuAnizkoitzak().get(j).getApostuDirua()));
								u1.getGertatukoMugimendua().addGertatera("Egindako apustua: Anizkoitza " + " ---> Zuk irabazitako dirua:           " + bider * u1.getErabiltzailearenApustuAnizkoitzak().get(j).getApostuDirua()) ;
							}
						}
					
					}
				}
				
			}else {				
			
				
				u.setDirua(u.getDirua()+(Double.parseDouble(k1.getOnura()) * k1.getApuestuLista().get(i).getDirukop()));
				k1.setLortutakoEtekina(k1.getLortutakoEtekina()-(Double.parseDouble(k1.getOnura()) * k1.getApuestuLista().get(i).getDirukop()));
				u.getGertatukoMugimendua().addGertatera("Egindako apustua: " + "Galdera: " + q.toString() + "eta  Kuota: " + k.toString() + " ---> Zuk irabazitako dirua:           " + Double.parseDouble(k1.getOnura()) * k1.getApuestuLista().get(i).getDirukop()+"€");
			}
		}
		db.getTransaction().commit();

	}
//	public void kalkulatuDiruaAnizkoitza(Kuota k1) {
//		db.getTransaction().begin();
//		Kuota k = db.find(Kuota.class, k1);
//		User u;
//		for(int i=0;i<k.getApuestuLista().size();i++) {
//			if(k.getApuestuLista().get(i).getDirukop() == 0.0) {
//				u=k1.getApuestuLista().get(i).getU();
//				
//							
//						}
//						
//					}
//					
//				}
//			}
//		}
		
	public void apustuaGorde(String usr, double d, Kuota k) {
		db.getTransaction().begin();
		User u = db.find(User.class, usr);
		Kuota k1=db.find(Kuota.class, k.getKNumber());
		Apustua ap = new Apustua(d, u, k1);	
		k1.getApuestuLista().add(ap);
		u.getErabiltzailearenApustuak().addElement(ap);
		ap.setUsrAnitz(usr);
		db.persist(ap);
		db.persist(k1);
		db.getTransaction().commit();
		System.out.println("Apustua sartu da!!!!!");

	}
	

	public void ApustuaKuotanGehitu(String usr, Kuota k1, Apustua a1) { //apustuAnitza
		db.getTransaction().begin();
		//Apustua a = db.find(Apustua.class, a1);
		Kuota k = db.find(Kuota.class, k1);
		k.getApuestuLista().add(a1);
		a1.setKuotaAnitza(k.getEmaitza());
	
		a1.setUsrAnitz(usr);
		System.out.println(a1.getUsrAnitz() + "  USER ANITZA");
		System.out.println(a1.getKuotaAnitza() + "  KUOTA ANITZA!");
		db.persist(k);
		db.persist(a1);
		db.getTransaction().commit();
	}


}
