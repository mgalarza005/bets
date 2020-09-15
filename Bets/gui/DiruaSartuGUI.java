package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DiruaSartuGUI extends JFrame {

	private JPanel contentPane;
	private JTextField diruKop;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiruaSartuGUI frame = new DiruaSartuGUI(args[1]);
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
	public DiruaSartuGUI(String usr) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		diruKop = new JTextField();
		diruKop.setBounds(286, 102, 116, 22);
		contentPane.add(diruKop);
		diruKop.setColumns(10);

		JLabel mezua = new JLabel(" ");
		mezua.setBounds(12, 179, 261, 21);
		contentPane.add(mezua);

		JLabel lblZenbatDiruSartu = new JLabel("Zenbat diru sartu nahi duzu?");
		lblZenbatDiruSartu.setBounds(12, 94, 180, 39);
		contentPane.add(lblZenbatDiruSartu);

		JLabel label = new JLabel("\u20AC");
		label.setBounds(408, 105, 24, 16);
		contentPane.add(label);

		JButton btnAdos = new JButton("Ados");
		btnAdos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				try {
					BLFacade negozioLogika=MainGUI.getBusinessLogic();
					double dirua=Double.parseDouble(diruKop.getText());
					if (dirua>0) {
					negozioLogika.diruaSartu(usr, dirua);
					mezua.setText("Dirua sartu duzu.");
					diruKop.setText("");
					}else {
						mezua.setText("Kopuru positibo bat sartu!");
						diruKop.setText("");
					}
					

				}catch(NumberFormatException ee) {
					mezua.setText("Zenbaki bat sartu behar da!");

				}catch (Exception ex){
					System.out.println("Errore bat egon da.");
				}





			}
		});
		btnAdos.setBounds(305, 175, 97, 25);
		contentPane.add(btnAdos);


	}
}
