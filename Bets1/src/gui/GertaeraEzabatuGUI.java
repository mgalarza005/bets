package gui;

import businessLogic.BLFacade;
import com.toedter.calendar.JCalendar;

import domain.Event;
import domain.Kuota;
import domain.Question;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;

public class GertaeraEzabatuGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events"));
	private JButton gertaeraEzabatu = new JButton();
	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JTable tableEvents= new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	//private Question q;



	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private final JLabel lblNewLabelMezuak = new JLabel("");
	
	
	public GertaeraEzabatuGUI()
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


	@SuppressWarnings("serial")
	private void jbInit() throws Exception
	{
		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(758, 644));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEvents.setBounds(329, 34, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelEvents);
		
		
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

						
						BLFacade negozioLogika=MainGUI.getBusinessLogic();

						Vector<domain.Event> events=negozioLogika.getEvents(firstDay);

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarMio.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarMio.getTime()));
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

						
					}

				}
				CreateQuestionGUI.paintDaysWithEvents(jCalendar1);
			} 
		});

		this.getContentPane().add(jCalendar1, null);

		scrollPaneEvents.setBounds(new Rectangle(329, 63, 346, 150));


		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents){
		    public boolean isCellEditable(int row, int column){
		      return false;//This causes all cells to be not editable
		    }
		  };

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries){
		    public boolean isCellEditable(int row, int column){
		      return false;//This causes all cells to be not editable
		    }
		  };
		
this.getContentPane().add(scrollPaneEvents, null);
		gertaeraEzabatu.setText("Ezabatu gertaera");
		
		gertaeraEzabatu.setBounds(new Rectangle(491, 298, 184, 30));
		getContentPane().add(gertaeraEzabatu);
		gertaeraEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int selectedRowGertaera = tableEvents.getSelectedRow();
					System.out.println(selectedRowGertaera);
					Event gertaera = (Event)tableModelEvents.getValueAt(selectedRowGertaera,2); 
					BLFacade negozioLogika=MainGUI.getBusinessLogic();
					
					Question ezabatzekoGaldera=null;
					

/*					Vector<Kuota> kuotak = negozioLogika.kuotakLortu(ezabatzekoGaldera);
					
					System.out.println(kuotak);
					
					for(int a=0; a<kuotak.size();a++) {
						if(!kuotak.get(a).toString().contains("null")) {
						negozioLogika.diruaErabiltzaileeiItzuli(kuotak.get(a));
						negozioLogika.KuotaEzabatu(kuotak.get(a));
						}
					}	
					negozioLogika.galderaEzabatu(ezabatzekoGaldera);*/
					
					
					for(int i=0;i<gertaera.getQuestions().size(); i++) {
						
						if(!gertaera.getQuestions().toString().contains("null")){
						
						System.out.println(gertaera.getQuestions().get(i));
						ezabatzekoGaldera=gertaera.getQuestions().get(i);
						
						Vector<Kuota> kuotak = negozioLogika.kuotakLortu(ezabatzekoGaldera);
						System.out.println(kuotak);
						
						for(int a=0; a<kuotak.size();a++) {
							if(!kuotak.get(a).toString().contains("null")) {
							negozioLogika.diruaErabiltzaileeiItzuli(kuotak.get(a));
							negozioLogika.KuotaEzabatu(kuotak.get(a));
							}
						}	
						
			
						negozioLogika.galderaEzabatu(gertaera.getQuestions().get(i));
						}
					}
					negozioLogika.gertaeraEzabatu(gertaera);
							
								
				}
				catch (NumberFormatException a) {
					System.out.println("Errorea zenbakien formatuan");
//				}catch (Exception aa) {
//					System.out.println("2.Errorea");
//				}

				}}
		});
		
		lblNewLabelMezuak.setBounds(419, 341, 239, 14);
		getContentPane().add(lblNewLabelMezuak);

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
}
