package businessLogic;

import java.util.Vector;
import java.util.Date;





//import domain.Booking;

import domain.*;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	

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
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	
	/**
     * This method check if log user exists in the database with given password
     * 
     * @param log user name
     * @param pass user password
     * @return user exists or not in the database with the given password
     */
	@WebMethod public boolean isLoginUser(String log, String pass);
	@WebMethod public boolean isLoginWorker(String log, String pass);
	@WebMethod public boolean isLoginAdmin(String log, String pass);
	
	
	
	@WebMethod public boolean adinaOndo (String adina);
	@WebMethod public boolean passOndo(String pass1, String pass2);
	@WebMethod public boolean izenaOndo (String izena);
	@WebMethod public boolean workerIzenaOndo(String izena);
	@WebMethod public void erregistratu(String izena, String pass, String NAN, String korreoa, String KZ, String adina)throws NullPointerException;
	@WebMethod public boolean gertaeraSortu(String p1, String p2, Date d)throws EventFinished, NullPointerException;
	@WebMethod public void langileBerriaSortu(String l, String pass);
	@WebMethod public void emaitzaIpini(String emaitza, Question g);

	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();


	@WebMethod public Kuota kuotaSortu(String emaitza, String onura, Question g);


	@WebMethod void diruaSartu(String usr, double dirua);

	
}
