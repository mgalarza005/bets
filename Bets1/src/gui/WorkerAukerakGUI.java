package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WorkerAukerakGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkerAukerakGUI frame = new WorkerAukerakGUI();
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
	public WorkerAukerakGUI() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Gertaerak Sortu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GertaerakSortuGUI b= new GertaerakSortuGUI();
				b.setVisible(true);
			}
		});
		btnNewButton.setBounds(98, 57, 232, 43);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Kuotak Ipini");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KuotakIpiniGUI c= new KuotakIpiniGUI();
				c.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(98, 144, 232, 43);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Aukeratu zer egin nahi duzun:");
		lblNewLabel.setBounds(98, 13, 232, 43);
		contentPane.add(lblNewLabel);
		
		JButton btnGalderakSortu = new JButton("Galderak Sortu");
		btnGalderakSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateQuestionGUI q = new CreateQuestionGUI(null);
				q.setVisible(true);
			}
		});
		btnGalderakSortu.setBounds(98, 102, 232, 40);
		contentPane.add(btnGalderakSortu);
		
		JButton btnNewButton_2 = new JButton("Emaitzak ipini");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmaitzaIpiniGUI em= new EmaitzaIpiniGUI();
				em.setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(98, 189, 232, 43);
		contentPane.add(btnNewButton_2);
		
		JButton KuotakEzabatubtn = new JButton("Kuotak ezabatu");
		KuotakEzabatubtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try {
					KuotakEzabatuGUI frame = new KuotakEzabatuGUI();
					frame.setVisible(true);
				}
				
				
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		KuotakEzabatubtn.setBounds(98, 233, 232, 43);
		contentPane.add(KuotakEzabatubtn);
		
		JButton btnNewButton_3 = new JButton("Etekinak ikusi");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					EtekinakIkusiGUI frame1 = new EtekinakIkusiGUI();
					frame1.setVisible(true);
				}
				
				
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
		});
		btnNewButton_3.setBounds(98, 405, 232, 40);
		contentPane.add(btnNewButton_3);
		
		JButton btnGalderakEzabatu = new JButton("Galderak ezabatu");
		btnGalderakEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					GalderakEzabatuGUI frame = new GalderakEzabatuGUI();
					frame.setVisible(true);
				}
				
				
				catch (Exception ex) {
					ex.printStackTrace();
				}
				
				
			}
		});
		btnGalderakEzabatu.setBounds(98, 273, 232, 43);
		contentPane.add(btnGalderakEzabatu);
		
		JButton btnNewButton_4 = new JButton("Gertaera ezabatu");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					GertaeraEzabatuGUI frame = new GertaeraEzabatuGUI();
					frame.setVisible(true);
				}
				
				
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(98, 314, 232, 43);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Erabiltzailea ezabatu");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ErabiltzaileaEzabatuGUI frame = new ErabiltzaileaEzabatuGUI();
					frame.setVisible(true);
				}
				
				
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_5.setBounds(98, 356, 232, 49);
		contentPane.add(btnNewButton_5);
		
		JButton bikoiztu = new JButton("Gertaera bikoiztu");
		bikoiztu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GertaeraBikoiztuGUI frame = new GertaeraBikoiztuGUI();
					frame.setVisible(true);
				}
				
				
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		bikoiztu.setBounds(96, 444, 234, 43);
		contentPane.add(bikoiztu);
	}
}
