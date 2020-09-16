package businessLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.*;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
		}

	}


	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	@WebMethod
	public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{

		//The minimum bed must be greater than 0
		DataAccess dBManager=new DataAccess();
		Question qry=null;


		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));


		qry=dBManager.createQuestion(event,question,betMinimum);		

		dBManager.close();

		return qry;
	};

	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod	
	public Vector<Event> getEvents(Date date)  {
		DataAccess dbManager=new DataAccess();
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

	/**
	 * This method check if log user exists in the database with given password
	 * 
	 * @param log user name
	 * @param pass user password
	 * @return user exists or not in the database with the given password
	 */
	@WebMethod
	public boolean isLoginUser(String log, String pass) {
		DataAccess dbmanager= new DataAccess();
		boolean b= dbmanager.isLoginUser(log, pass);
		dbmanager.close();
		return b;
	}

	@WebMethod
	public boolean isLoginWorker(String log, String pass) {
		DataAccess dbmanager= new DataAccess();
		boolean b= dbmanager.isLoginWorker(log, pass);
		dbmanager.close();
		return b;
	}
	@WebMethod
	public boolean isLoginAdmin(String log, String pass) {
		DataAccess dbmanager= new DataAccess();
		boolean b= dbmanager.isLoginAdmin(log, pass);
		dbmanager.close();
		return b;
	}



	public boolean adinaOndo (String adina){

		int ad = Integer.parseInt(adina);
		if (ad>=18)return true;
		return false;
	}

	public boolean passOndo(String pass1, String pass2){
		//System.out.println(pass1 + " " + pass2);
		if (pass1.equals(pass2)) return true;
		return false;
	}

	public boolean izenaOndo (String izena){
		DataAccess dbmanager= new DataAccess();
		boolean b = dbmanager.izenaOndo(izena);
		dbmanager.close();
		return b;
	}
	@Override
	public boolean workerIzenaOndo(String izena) {
		DataAccess dbmanager= new DataAccess();
		boolean b = dbmanager.workerIzenaOndo(izena);
		dbmanager.close();
		return b;
		
	}

	public void erregistratu(String izena, String pass, String NAN, String korreoa, String KZ, String adina) throws NullPointerException{
		if(NAN.isEmpty()||korreoa.isEmpty()||KZ.isEmpty()) {
			throw new NullPointerException();
		}

		User erab = new User(izena, pass, NAN, korreoa, KZ, adina);
		DataAccess dbmanager = new DataAccess();
		dbmanager.erregistratu(erab);
		dbmanager.close();
	}

	public boolean gertaeraSortu(String kir, String tal, Date d) throws EventFinished, NullPointerException{
		Event e=new Event(0,tal,d,kir);
		DataAccess dbmanager= new DataAccess();
		boolean emaitza=true;

		if(new Date().compareTo(e.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));

		if(kir.isEmpty()||tal.isEmpty()) {
			throw new NullPointerException();
		}

		Vector<Event> gertaerak = new Vector<Event>(dbmanager.getEvents(d).size());
		
			gertaerak.addAll(dbmanager.getEvents(d));
		
		System.out.println(gertaerak);

		if (gertaerak.size()!=0) {
			//boolean ondo= true;
			System.out.println(gertaerak);
			for (int i=0; i<gertaerak.size(); i++) {
				if (gertaerak.get(i).getDescription().equals(tal)) {
					emaitza=false;
					System.out.println("errepikatua");
				}
			}
			if (emaitza) {
				dbmanager.gertaeraSortu(e);
				dbmanager.close();
				
			}
		}
		else {
			dbmanager.gertaeraSortu(e);
			dbmanager.close();
		}
		return emaitza;
	}
	public void langileBerriaSortu(String l, String pass) {
		if(l.isEmpty()||pass.isEmpty()) {
			throw new NullPointerException();
		}

		DataAccess dbmanager = new DataAccess();
		Worker w = new Worker(l, pass);
		dbmanager.langileBerriaSortu(w);
		dbmanager.close();
		
		
	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod
	public void initializeBD(){
		DataAccess dBManager=new DataAccess();
		dBManager.initializeDB();
		dBManager.close();
	}

	@WebMethod
	@Override
	public Kuota kuotaSortu(String emaitza, String onura, Question g) {
		DataAccess dbmanager= new DataAccess();
		Kuota k =null;

		k= dbmanager.kuotaSortu(emaitza, onura, g);
		dbmanager.close();
		return k;

	}
	

	@Override
	public void emaitzaIpini(String emaitza, Question g) {
		DataAccess dbmanager= new DataAccess();
		dbmanager.emaitzaIpini(emaitza, g);
		dbmanager.close();
		
	}


	@Override
	public void diruaSartu(String usr, double dirua) {
		DataAccess dbmanager= new DataAccess();
		dbmanager.diruaSartu(usr, dirua);
		dbmanager.close();
		
		
		
	}


	@Override
	public void KuotaEzabatu(Kuota ezabatzekoKuota) {
		DataAccess dbmanager= new DataAccess();
		dbmanager.KuotaEzabatu(ezabatzekoKuota);
		dbmanager.close();
		
		
		
	}


	@Override
	public boolean diruaNahikorik(String usr, double d, Kuota k) {
		DataAccess dbmanager= new DataAccess();
		
		boolean a=dbmanager.diruaNahikorik(usr, d, k);
		dbmanager.close();

		return a;
	}


	@Override
	public void apustuaGorde(String usr, double d, Kuota k) {
		DataAccess dbmanager= new DataAccess();
		dbmanager.apustuaGorde(usr, d, k);
		dbmanager.close();
		
	}


	@Override
	public void mugimenduaErabiltzaileariGehitu(String usr, double d, Kuota k, Question g) {
		DataAccess dbmanager= new DataAccess(); 
		dbmanager.mugimenduaErabiltzaileariGehitu(usr, d, k, g);
		dbmanager.close();
	}


	@Override
	public User lortuErabiltzailea(String usr) {
		DataAccess dbmanager= new DataAccess();
		User u=dbmanager.lortuErabiltzailea(usr);
		dbmanager.close();
		return u;
	}


	@Override
	public ArrayList<String> lortuMugimenduak(String usr) {
		DataAccess dbmanager= new DataAccess();
		ArrayList<String> u=dbmanager.lortuMugimenduak(usr);
		dbmanager.close();
		
		return u;
	}


	@Override
	public void mugimenduaErabiltzaileariGehituDirua(String usr, double dirua) {
		DataAccess dbmanager= new DataAccess();
		dbmanager.mugimenduaErabiltzaileariGehituDirua(usr,dirua);
		dbmanager.close();
		
		
	}


	@Override
	public Kuota lortuKuotaIrabzalea(Question g) {
		DataAccess dbmanager= new DataAccess();
		Kuota k = dbmanager.lortuKuotaIrabzalea(g);
		dbmanager.close();
		
		return k;
	}


	@Override
	public void kalkulatuDirua(Kuota k, Question q) {
		DataAccess dbmanager= new DataAccess();
		dbmanager.kalkulatuDirua(k, q);
		dbmanager.close();		
	}


	@Override
	public void diruaErabiltzaileeiItzuli(Kuota k) {
		DataAccess dbmanager= new DataAccess();
		dbmanager.diruaErabiltzaileeiItzuli(k);
		//onarte egiten du
		dbmanager.close();		
		
	}


	@Override
	public void galderaEzabatu(Question ezabatzekoGaldera) {
		DataAccess dbmanager= new DataAccess();
		
		/*
		Event gert = ezabatzekoGaldera.getEvent();
		
		gert.setQuestions(gert.getQuestions().remove(ezabatzekoGaldera));		
		*/
		
		dbmanager.galderaEzabatu(ezabatzekoGaldera);

		
		
		

		
		
		dbmanager.close();	
		
	}


	@Override
	public void gertaeraEzabatu(Event g) {
		DataAccess dbmanager= new DataAccess();
		dbmanager.gertaeraEzabatu(g);
		dbmanager.close();		
		
	}


	@Override
	public void erabiltzaileaEzabatu(String text) {
		DataAccess dbmanager= new DataAccess();
		dbmanager.erabiltzaileaEzabatu(text);
		dbmanager.close();	
		
	}


	@Override
	public void gertaeraBikoiztu(Event g, Date data) {
		
		DataAccess dbmanager= new DataAccess();
		dbmanager.gertaeraBikoiztu(g,data);
		dbmanager.close();
	}


	@Override
	public KuotaContainer getKuotaContainer(Kuota k) {
		KuotaContainer kc= new KuotaContainer(k);
		return kc;
	}


	@Override
	public Vector<Kuota> kuotakLortu(Question g) {
		DataAccess dbmanager= new DataAccess();
		Vector<Kuota> k = dbmanager.kuotakLortu(g);
		dbmanager.close();
		return k;
	}


	@Override
	public User lortuUser(String usr) {
		DataAccess dbmanager= new DataAccess();
		User u1 = dbmanager.lortuUser(usr);
		dbmanager.close();
		return u1;
	}


	@Override
	public void apustuaAnitzaGorde(String usr, double d, Vector<Apustua> apustuLista) {
		DataAccess dbmanager= new DataAccess();
		dbmanager.apustuaAnitzaGorde(usr, d, apustuLista);
		dbmanager.close();
		
	}


	@Override
	public void mugimenduaErabiltzaileariGehituApustuAnitza(String usr, double d, Vector<Apustua> apustuLista) {
		DataAccess dbmanager= new DataAccess();
		dbmanager.mugimenduaErabiltzaileariGehituApustuAnitza(usr, d, apustuLista);
		dbmanager.close();
		
	}




	@Override
	public void ApustuaKuotanGehitu(String usr, Kuota k, Apustua a1) {
		DataAccess dbmanager= new DataAccess();
		dbmanager.ApustuaKuotanGehitu(usr, k, a1);
		dbmanager.close();
		
	}


}