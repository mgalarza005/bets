package gui;

import businessLogic.BLFacade;
import com.toedter.calendar.JCalendar;
import domain.Question;
import exceptions.EventFinished;
import domain.Event;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;


public class GertaeraBikoiztuGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JTable tableEvents= new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;


	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private final JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("GertaeraBikoiztuGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$

	public GertaeraBikoiztuGUI()
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
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		getContentPane().setLayout(null);
		jLabelEventDate.setBounds(40, 15, 140, 25);

		this.getContentPane().add(jLabelEventDate);
		jLabelEvents.setBounds(295, 19, 259, 16);
		this.getContentPane().add(jLabelEvents);
		jButtonClose.setBounds(445, 410, 130, 30);
		
		JLabel lblAukeratuDataBerria = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("GertaeraBikoiztuGUI.lblAukeratuDataBerria.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblAukeratuDataBerria.setBounds(36, 223, 207, 16);
		getContentPane().add(lblAukeratuDataBerria);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("GertaeraBikoiztuGUI.lblNewLabel.text"));
		lblNewLabel.setVisible(false);
		lblNewLabel.setBounds(64, 417, 156, 16);
		getContentPane().add(lblNewLabel);


		JCalendar calendar2 = new JCalendar();
		calendar2.setBounds(40, 252, 225, 150);
		getContentPane().add(calendar2);
		
		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose);
		jCalendar1.setBounds(40, 50, 225, 150);


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

		this.getContentPane().add(jCalendar1);

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				Vector<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);

			}
		});

		scrollPaneEvents.setBounds(292, 50, 346, 150);

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		this.getContentPane().add(scrollPaneEvents);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedRowGertaera = tableEvents.getSelectedRow();
				Event gertaera = (Event)tableModelEvents.getValueAt(selectedRowGertaera,2);
				BLFacade negozioLogika=MainGUI.getBusinessLogic();
				Date data= trim(new Date(calendar2.getCalendar().getTime().getTime()));
				negozioLogika.gertaeraBikoiztu(gertaera, data);
				lblNewLabel.setVisible(true);
				System.out.println(gertaera.toString());
				
			}
		});
		btnNewButton.setBounds(325, 334, 141, 48);
		
		getContentPane().add(btnNewButton);
		
		
		
		
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
}
