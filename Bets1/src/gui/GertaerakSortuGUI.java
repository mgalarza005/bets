package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import exceptions.EventFinished;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class GertaerakSortuGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GertaerakSortuGUI frame = new GertaerakSortuGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private JTextField kirolText;
	private JTextField taldeText;

	public GertaerakSortuGUI() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JLabel emaitza = new JLabel("");
		emaitza.setBounds(20, 177, 242, 34);
		contentPane.add(emaitza);

		final JCalendar calendar = new JCalendar();
		calendar.setBounds(10, 11, 184, 153);
		contentPane.add(calendar);

		kirolText = new JTextField();
		kirolText.setBounds(361, 37, 116, 22);
		contentPane.add(kirolText);
		kirolText.setColumns(10);

		JLabel ZeKirol = new JLabel("Kirola");
		ZeKirol.setBounds(249, 40, 56, 16);
		contentPane.add(ZeKirol);

		JLabel zeTalde = new JLabel("Taldeak");
		zeTalde.setBounds(249, 88, 56, 16);
		contentPane.add(zeTalde);

		taldeText = new JTextField();
		taldeText.setBounds(361, 85, 116, 22);
		contentPane.add(taldeText);
		taldeText.setColumns(10);


		JButton btnNewButton = new JButton("Gertaera sortu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emaitza.setText(" ");
				try {

					Date date = trim(calendar.getDate());
					/*System.out.println(date.getYear());
					System.out.println(date.getMonth());
					System.out.println(date.getDay());*/

					
					
					
					System.out.println(date);
					String kir=(String) kirolText.getText();
					System.out.println(kir);
					String tal=(String) taldeText.getText();
					System.out.println(tal);

					BLFacade negozioLogika=MainGUI.getBusinessLogic();
					if (negozioLogika.gertaeraSortu(kir, tal, date)) {
						emaitza.setText("Gertaera gorde da");						
					}else emaitza.setText("Gertaera errepikatua da");
				}
				catch (EventFinished e1) {

					emaitza.setText( ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));


				}
				catch (Exception e2) {
					emaitza.setText("Gelaxka guztiak ongi bete behar dituzu.");


				}
			}
		});
		btnNewButton.setBounds(244, 230, 203, 23);
		contentPane.add(btnNewButton);

		


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
