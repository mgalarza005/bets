package gui;

import businessLogic.BLFacade;
import com.toedter.calendar.JCalendar;

import domain.Apustua;
import domain.Event;
import domain.Kuota;
import domain.Question;
import domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;

public class ApustuAnitzaGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private static String etiketak = "Etiquetas";
	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle(etiketak).getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle(etiketak).getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle(etiketak).getString("Events"));
	private JButton kuotakIkusibutton = new JButton();
	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();

	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private Question g;

	private String usr;
	private Vector<Apustua> apustuLista;

	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle(etiketak).getString("EventN"), 
			ResourceBundle.getBundle(etiketak).getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle(etiketak).getString("QueryN"), 
			ResourceBundle.getBundle(etiketak).getString("Query")

	};
	
	private final JLabel lblKuotak = new JLabel(ResourceBundle.getBundle(etiketak).getString("KuotakIkusiGUI.lblKuotak.text")); 
	private final JLabel label = new JLabel();
	private final JTextField textField_1 = new JTextField();
	
	
	public ApustuAnitzaGUI()
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
	public ApustuAnitzaGUI(String u, Vector<Apustua> ap)
	{
		try
		{
			jbInit();
			usr= u;
			apustuLista = ap;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	@SuppressWarnings("serial")
	private void jbInit() throws Exception
	{
		
		JComboBox<Kuota> comboBox = new JComboBox<Kuota>();
		comboBox.setBounds(40, 461, 406, 41);
		getContentPane().add(comboBox);
		
		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(758, 644));
		this.setTitle(ResourceBundle.getBundle(etiketak).getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(40, 255, 78, 14);
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
		scrollPaneQueries.setBounds(new Rectangle(40, 282, 406, 83));

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
		kuotakIkusibutton.setText(ResourceBundle.getBundle(etiketak).getString("ApustuaEginGUI.kuotaGorde.text")); //$NON-NLS-1$ //$NON-NLS-2$
		kuotakIkusibutton.setText("Kuotak ikusi");
		kuotakIkusibutton.setBounds(new Rectangle(40, 378, 130, 30));
		getContentPane().add(kuotakIkusibutton);
		kuotakIkusibutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.removeAllItems();
				try {
					
						BLFacade negozioLogika=MainGUI.getBusinessLogic();
						int selectedRowGertaera = tableEvents.getSelectedRow();
						Event gertaera = (Event)tableModelEvents.getValueAt(selectedRowGertaera,2); // obtain ev object
						Vector<Question> galderak = gertaera.getQuestions() ;
						int selectedRowGaldera = tableQueries.getSelectedRow();						
						g = galderak.get(selectedRowGaldera);
						Vector<Kuota> kuotak = negozioLogika.kuotakLortu(g);
						
						String aux="";
						for(int a=0; a<kuotak.size();a++) {
							if(!kuotak.get(a).toString().contains("null")) {
								aux=aux + kuotak.get(a).toString() + "\n";			
								comboBox.addItem(kuotak.get(a));
							}	
						}
				}
				catch (NumberFormatException a) {
					System.out.println("Errorea zenbakien formatuan");
				}
				catch (Exception ee) {
					System.out.println("Errorea");
					ee.printStackTrace();
				}

			}
		});
		lblKuotak.setBounds(40, 418, 56, 16);
		
		getContentPane().add(lblKuotak);
		
		JLabel lblDirua = new JLabel(ResourceBundle.getBundle(etiketak).getString("ApustuaEginGUI.lblDirua.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblDirua.setBounds(487, 473, 56, 16);
		getContentPane().add(lblDirua);
		
		
		
		JButton btnApostatu = new JButton(ResourceBundle.getBundle(etiketak).getString("ApustuaEginGUI.btnApostatu.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnApostatu.setText("Apustua egin");
		btnApostatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				BLFacade negozioLogika=MainGUI.getBusinessLogic();
				if(textField_1.getText().equals("")) {
					Kuota k=(Kuota) comboBox.getSelectedItem();
					User u1 = negozioLogika.lortuUser(usr);
					Apustua a1 = new Apustua(0.0, u1, k, k.getEmaitza(), u1.getLog(), Integer.toString(k.getKNumber()));
					apustuLista.addElement(a1);
					double d = Double.parseDouble(textField_1.getText());
					
					boolean a = negozioLogika.diruaNahikorik(usr, d, k);
					if(a) {
						System.out.println(usr.toString());
						System.out.println(d);
						System.out.println(k.toString());
						negozioLogika.ApustuaKuotanGehitu(usr, k, a1);
						negozioLogika.apustuaAnitzaGorde(usr,d,apustuLista);
						label.setVisible(true);
						label.setText("Apustu anizkoitza gorde da"); 
						textField_1.setText("");
						negozioLogika.mugimenduaErabiltzaileariGehituApustuAnitza(usr,d,apustuLista);
						
					}else {
						label.setVisible(true);
						label.setText("Ez daukazu diru nahikorik");
					}
					
				}else {
					label.setVisible(true);
					label.setText("Sartu dirua");
				}
				
			}catch(Exception ee) {
				System.out.println("Errorea");
				ee.printStackTrace();
			}
			}
			
		});
		btnApostatu.setBounds(566, 540, 129, 30);
		getContentPane().add(btnApostatu);
		label.setBounds(41, 547, 203, 23);
		
		getContentPane().add(label);
		textField_1.setColumns(10);
		textField_1.setBounds(579, 470, 116, 22);
		
		getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("Apustua gehitu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(usr + "!!!!!");
				Kuota k=(Kuota) comboBox.getSelectedItem();
				BLFacade negozioLogika=MainGUI.getBusinessLogic();
				User u1 = negozioLogika.lortuUser(usr);
				System.out.println(u1.toString() + "userra!!");
				Apustua a1 = new Apustua(0.0, u1, k, k.getEmaitza(), u1.getLog(), Integer.toString(k.getKNumber()));
				negozioLogika.ApustuaKuotanGehitu(usr,k,a1);
				apustuLista.addElement(a1);
				System.out.println(apustuLista.toString() + "Amaia!!");
				ApustuAnitzaGUI ap= new ApustuAnitzaGUI(u1.getLog(), apustuLista);
				ap.setVisible(true);
				itxi(arg0);
				
				
			}
		});
		btnNewButton.setBounds(334, 543, 215, 25);
		getContentPane().add(btnNewButton);
		
	
	
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
	private void itxi(ActionEvent arg0) {
		this.setVisible(false);
	}
}
