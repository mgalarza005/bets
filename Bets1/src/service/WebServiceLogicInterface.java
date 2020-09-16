package service;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.jws.WebMethod;
import javax.jws.WebService;
import domain.Event;
import domain.Kuota;
import domain.KuotaContainer;
import domain.Question;
import domain.User;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

@WebService

public interface WebServiceLogicInterface {
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	@WebMethod public Vector<Event> getEvents(Date date);

	@WebMethod public boolean isLoginUser(String log, String pass);
	@WebMethod public boolean isLoginWorker(String log, String pass);
	@WebMethod public boolean isLoginAdmin(String log, String pass);
	
	
	@WebMethod public KuotaContainer getKuotaContainer(Kuota k); 
	@WebMethod public boolean adinaOndo (String adina);
	@WebMethod public boolean passOndo(String pass1, String pass2);
	@WebMethod public boolean izenaOndo (String izena);
	@WebMethod public boolean workerIzenaOndo(String izena);
	@WebMethod public void erregistratu(String izena, String pass, String NAN, String korreoa, String KZ, String adina)throws NullPointerException;
	@WebMethod public boolean gertaeraSortu(String p1, String p2, Date d)throws EventFinished, NullPointerException;
	@WebMethod public void langileBerriaSortu(String l, String pass);
	@WebMethod public void emaitzaIpini(String emaitza, Question g);

	@WebMethod public void initializeBD();


	@WebMethod public Kuota kuotaSortu(String emaitza, String onura, Question g);


	@WebMethod void diruaSartu(String usr, double dirua);


	@WebMethod void KuotaEzabatu(Kuota ezabatzekoKuota);


	@WebMethod boolean diruaNahikorik(String usr, double d, Kuota k);


	@WebMethod void apustuaGorde(String usr, double d, Kuota k);


	@WebMethod void mugimenduaErabiltzaileariGehitu(String usr, double d, Kuota k, Question g);


	@WebMethod User lortuErabiltzailea(String usr);


	@WebMethod ArrayList<String> lortuMugimenduak(String usr);


	@WebMethod void mugimenduaErabiltzaileariGehituDirua(String usr, double dirua);


	@WebMethod Kuota lortuKuotaIrabzalea(Question g);


	@WebMethod void kalkulatuDirua(Kuota k,Question q);


	@WebMethod void diruaErabiltzaileeiItzuli(Kuota selectedItem);


	@WebMethod void galderaEzabatu(Question ezabatzekoGaldera);


	@WebMethod void gertaeraEzabatu(Event gertaera);


	@WebMethod void erabiltzaileaEzabatu(String text);


	@WebMethod void gertaeraBikoiztu(Event gertaera, Date data);

	
}