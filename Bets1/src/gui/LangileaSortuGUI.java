package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;

public class LangileaSortuGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LangileaSortuGUI frame = new LangileaSortuGUI();
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
	public LangileaSortuGUI() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sortu ezazu langile berri bat");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(98, 13, 257, 24);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(187, 64, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblIzena = new JLabel("Izena:");
		lblIzena.setBounds(34, 71, 56, 16);
		contentPane.add(lblIzena);
		
		JLabel lblPasahitza = new JLabel("Pasahitza:");
		lblPasahitza.setBounds(34, 103, 88, 16);
		contentPane.add(lblPasahitza);
		JLabel lblPasahitzaErrepikatu = new JLabel("Pasahitza errepikatu:");
		lblPasahitzaErrepikatu.setBounds(34, 153, 141, 16);
		contentPane.add(lblPasahitzaErrepikatu);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(187, 99, 116, 22);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(187, 150, 116, 22);
		contentPane.add(passwordField_1);
		
		JLabel emaitzaLabel_1 = new JLabel("");
		emaitzaLabel_1.setEnabled(false);
		emaitzaLabel_1.setBounds(34, 202, 224, 38);
		contentPane.add(emaitzaLabel_1);
		
		JButton btnLangileaGorde = new JButton("Langilea gorde");
		btnLangileaGorde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BLFacade negozioLogika=MainGUI.getBusinessLogic();
				boolean a = negozioLogika.workerIzenaOndo(textField.getText());
				if(a) {
					if(negozioLogika.passOndo(new String(passwordField.getPassword()), new String(passwordField_1.getPassword()))) {
						negozioLogika.langileBerriaSortu(textField.getText(),new String(passwordField.getPassword()));
						emaitzaLabel_1.setEnabled(true);
						emaitzaLabel_1.setText("Langilea sartu duzu");
					}else {
						emaitzaLabel_1.setEnabled(true);
						emaitzaLabel_1.setText("Pasahitzak ez dira berdinak");
					}
				}else {
					emaitzaLabel_1.setEnabled(true);
					emaitzaLabel_1.setText("Dagoeneko, existitzen da langile bat izen horrekin");
				}
				
				
			}
		});
		btnLangileaGorde.setBounds(270, 202, 128, 38);
		contentPane.add(btnLangileaGorde);
		
		
	}
}
