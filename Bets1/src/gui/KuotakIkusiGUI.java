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

public class KuotakIkusiGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private static String etiketak = "Etiquetas";
	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle(etiketak).getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle(etiketak).getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle(etiketak).getString("Events"));
	private JButton kuotakIkusi = new JButton();
	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();

	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	//private Question q;



	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle(etiketak).getString("EventN"), 
			ResourceBundle.getBundle(etiketak).getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle(etiketak).getString("QueryN"), 
			ResourceBundle.getBundle(etiketak).getString("Query")

	};
	private final JLabel lblNewLabelMezuak = new JLabel("");
	private final JLabel lblKuotak = new JLabel(ResourceBundle.getBundle(etiketak).getString("KuotakIkusiGUI.lblKuotak.text")); //$NON-NLS-1$ //$NON-NLS-2$
	
	
	public KuotakIkusiGUI()
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
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(106, 443, 426, 41);
		getContentPane().add(comboBox);
		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(758, 644));
		this.setTitle(ResourceBundle.getBundle(etiketak).getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(106, 276, 68, 14);
		jLabelEvents.setBounds(295, 19, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
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

		scrollPaneEvents.setBounds(new Rectangle(305, 36, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(134, 303, 406, 83));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				Vector<Question> queries=ev.getQuestions();

				try {
					
				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableModelQueries.setColumnCount(3); // another column added to allocate q objects

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle(etiketak).getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle(etiketak).getString("SelectedEvent")+" "+ev.getDescription());

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();

					if (!(q.getQuestion() == null)) {
					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					row.add(q);
					tableModelQueries.addRow(row);	
					}
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
				tableQueries.getColumnModel().removeColumn(tableQueries.getColumnModel().getColumn(2)); // not shown in JTable
			} catch (Exception e1) {

				jLabelQueries.setText(e1.getMessage());
			}
			}
		});

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents){
		    public boolean isCellEditable(int row, int column){
		      return false;//This causes all cells to be not editable
		    }
		  };

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);

		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries){
		    public boolean isCellEditable(int row, int column){
		      return false;//This causes all cells to be not editable
		    }
		  };

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
		
this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		kuotakIkusi.setText(ResourceBundle.getBundle(etiketak).getString("KuotakIkusiGUI.kuotakIkusi.text")); //$NON-NLS-1$ //$NON-NLS-2$
		//kuotakIkusi.setText("Kuotak ikusi");
		kuotakIkusi.setBounds(new Rectangle(499, 214, 130, 30));
		getContentPane().add(kuotakIkusi);
		kuotakIkusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.removeAllItems();
				try {
						int selectedRowGertaera = tableEvents.getSelectedRow();
						Event gertaera = (Event)tableModelEvents.getValueAt(selectedRowGertaera,2); // obtain ev object
						Vector<Question> galderak = gertaera.getQuestions() ;
						int selectedRowGaldera = tableQueries.getSelectedRow();
						
						Question g = galderak.get(selectedRowGaldera);						
						//negozioLogika.kuotaSortu(emaitza.getText(), onura.getText(), g);
						String aux="";
						
						BLFacade negozioLogika=MainGUI.getBusinessLogic();
							
						Vector<Kuota> kuotak = negozioLogika.kuotakLortu(g);
				
						System.out.println(kuotak.toString());
				
						for(int a=0; a<kuotak.size();a++) {
							
						
							aux=aux + kuotak.get(a).toString() + "\n";
							if(!aux.contains("aux")) {
								comboBox.addItem(kuotak.get(a).toString());
							}	
						}		
				}
				catch (NumberFormatException a) {
					System.out.println("Errorea zenbakien formatuan");
				}catch (Exception aa) {
					System.out.println("2.Errorea");
					aa.printStackTrace();
				}

			}
		});
		
		lblNewLabelMezuak.setBounds(419, 341, 239, 14);
		getContentPane().add(lblNewLabelMezuak);
		lblKuotak.setBounds(106, 399, 56, 16);
		getContentPane().add(lblKuotak);

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
