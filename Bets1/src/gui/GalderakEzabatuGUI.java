package gui;

import businessLogic.BLFacade;
import com.toedter.calendar.JCalendar;
import domain.Question;
import domain.Event;
import domain.Kuota;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;


public class GalderakEzabatuGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private static String etiketak = "Etiquetas";
	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle(etiketak).getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle(etiketak).getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle(etiketak).getString("Events")); 

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle(etiketak).getString("Close"));

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();

	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;

	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle(etiketak).getString("EventN"), 
			ResourceBundle.getBundle(etiketak).getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle(etiketak).getString("QueryN"), 
			ResourceBundle.getBundle(etiketak).getString("Query")

	};
	private final JButton btnEzabatu = new JButton(ResourceBundle.getBundle(etiketak).getString("GalderakEzabatuGUI.btnEzabatu.text")); //$NON-NLS-1$ //$NON-NLS-2$

	public GalderakEzabatuGUI()
	{
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	private void jbInit() throws Exception
	{

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle(etiketak).getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(79, 247, 406, 14);
		jLabelEvents.setBounds(295, 19, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);

		jButtonClose.setBounds(new Rectangle(61, 423, 130, 30));

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);


		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));


		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					jCalendar1.setCalendar(calendarMio);
					Date firstDay=trim(new Date(jCalendar1.getCalendar().getTime().getTime()));



					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade=MainGUI.getBusinessLogic();

						Vector<domain.Event> events=facade.getEvents(firstDay);

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle(etiketak).getString("NoEvents")+ ": "+dateformat1.format(calendarMio.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle(etiketak).getString("Events")+ ": "+dateformat1.format(calendarMio.getTime()));
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							
							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);		
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
				CreateQuestionGUI.paintDaysWithEvents(jCalendar1);
			} 
		});

		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(89, 272, 406, 116));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				Vector<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle(etiketak).getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle(etiketak).getString("SelectedEvent")+" "+ev.getDescription());

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();

					if (!(q.getQuestion() == null)) {
					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					tableModelQueries.addRow(row);	
					}
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
			}
		});

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);


		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
		
		JLabel mezua = new JLabel(ResourceBundle.getBundle(etiketak).getString("GalderakEzabatuGUI.mezua.text")); //$NON-NLS-1$ //$NON-NLS-2$
		mezua.setBounds(295, 431, 99, 14);
		getContentPane().add(mezua);	
		

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		btnEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Selekzionatutako galdera ezabatu + bere kuotak ezabatu + apustuen dirua erabiltzaileei itzuli (KuotakEzabatun bezala)
				try {
								
				int selectedRowGertaera = tableEvents.getSelectedRow();
				Event gertaera = (Event)tableModelEvents.getValueAt(selectedRowGertaera,2); // obtain ev object
				Vector<Question> galderak = gertaera.getQuestions() ;
				int selectedRowGaldera = tableQueries.getSelectedRow();
				
				Question ezabatzekoGaldera = galderak.get(selectedRowGaldera);	
				BLFacade negozioLogika=MainGUI.getBusinessLogic();
				
				
				Vector<Kuota> kuotak = negozioLogika.kuotakLortu(ezabatzekoGaldera);
				
				System.out.println(kuotak);
				
				for(int a=0; a<kuotak.size();a++) {
					if(!kuotak.get(a).toString().contains("null")) {
					negozioLogika.diruaErabiltzaileeiItzuli(kuotak.get(a));
					negozioLogika.KuotaEzabatu(kuotak.get(a));
					}
				}	
				negozioLogika.galderaEzabatu(ezabatzekoGaldera);
				
				mezua.setText("Galdera ezabatu da");
				
				}catch (Exception e1) {
					e1.printStackTrace();
					
					mezua.setText("Ezin izan da galdera ezabatu");
				}
				
				
			}
		});
		btnEzabatu.setBounds(524, 323, 130, 39);
		
		getContentPane().add(btnEzabatu);
		
	

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}


	private Date trim(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}
	
	private Date newDate(int year,int month,int day) {

	     Calendar calendar = Calendar.getInstance();
	     calendar.set(year, month, day,0,0,0);
	     calendar.set(Calendar.MILLISECOND, 0);

	     return calendar.getTime();
	}
}
