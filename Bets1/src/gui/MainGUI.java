package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Apustua;
import domain.Event;
import domain.User;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQueries = null;
	JButton btnMugimenduakIkusi = new JButton();
	JButton btnDiruaSartu = new JButton();
	JButton ApustuAnitza;

	
	//Singleton patroia
    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnItxi;
	
	private String usr;
	private JButton btnApuestuaEgin;
	private JButton btnApustuAnitza;
	
	/**
	 * This is the default constructor
	 */
	public MainGUI() {
		super();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public MainGUI(String U) {
		super();
		initialize();
		usr=U;
	}
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(770, 315);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridLayout(4, 1, 0, 0));
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getPanel());
			jContentPane.add(getBoton3());
			
			btnDiruaSartu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					try {
						DiruaSartuGUI frame = new DiruaSartuGUI(usr);
						frame.setVisible(true);
					}
					
					
					catch (Exception ex) {
						ex.printStackTrace();
					}
					
				
				}
			});
			btnDiruaSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnDiruaSartu.text")); //$NON-NLS-1$ //$NON-NLS-2$
			jContentPane.add(btnDiruaSartu);
			jContentPane.add(getBtnApuestuaEgin());
			
			
			btnMugimenduakIkusi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						MugimenduakGUI frame = new MugimenduakGUI(usr);
						frame.setVisible(true);
					}
					
					
					catch (Exception ex) {
						ex.printStackTrace();
					}	
					
				}
			});
			btnMugimenduakIkusi.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnMugimenduakIkusi.text")); //$NON-NLS-1$ //$NON-NLS-2$
			jContentPane.add(btnMugimenduakIkusi);
			jContentPane.add(getBtnApustuAnitza());
			jContentPane.add(getBtnItxi());
		}
		return jContentPane;
	}


	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	
	
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new FindQuestionsGUI();

					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}
	

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.setBounds(153, 5, 69, 25);
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.setBounds(50, 5, 73, 25);
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}
	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.setBounds(257, 5, 87, 25);
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);
		}
		return rdbtnNewRadioButton_2;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}
	
	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		btnItxi.setText((ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnItxi.text")));
		btnApuestuaEgin.setText((ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnApuestuaEgin.text")));
		btnMugimenduakIkusi.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnMugimenduakIkusi.text"));
		btnDiruaSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnDiruaSartu.text"));
		btnApustuAnitza.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnApustuAnitza.text"));
		
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	
	private JButton getBtnItxi() {
		if (btnItxi == null) {
			btnItxi = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnItxi.text")); 
			btnItxi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					itxiBotoi(e);
				}
			});
		}
		return btnItxi;
	}
	
	private void itxiBotoi(ActionEvent e) {
		this.setVisible(false);
	}
	private JButton getBtnApuestuaEgin() {
		if (btnApuestuaEgin == null) {
			btnApuestuaEgin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnApuestuaEgin.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnApuestuaEgin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					ApustuaEginGUI ap= new ApustuaEginGUI(usr);
					ap.setVisible(true);
				}
			});
		}
		return btnApuestuaEgin;
	}
	private JButton getBtnApustuAnitza() {
		if (btnApustuAnitza == null) {
			btnApustuAnitza = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnApustuAnitza.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnApustuAnitza.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Vector<Apustua> ap1 = new Vector<Apustua>();
					ApustuAnitzaGUI ap= new ApustuAnitzaGUI(usr, ap1);
					ap.setVisible(true);
				}
			});
		}
		return btnApustuAnitza;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

