package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErabiltzaileaEzabatuGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErabiltzaileaEzabatuGUI frame = new ErabiltzaileaEzabatuGUI();
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
	public ErabiltzaileaEzabatuGUI() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setVisible(false);
		lblNewLabel_1.setBounds(36, 224, 56, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel erabilLabel = new JLabel("Erabiltzailea ezabatu duzu");
		erabilLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		erabilLabel.setVisible(false);
		erabilLabel.setBounds(36, 197, 186, 16);
		contentPane.add(erabilLabel);
		
		JLabel lblNewLabel = new JLabel("Sartu ezabatu nahi duzun erabiltzailearen izena:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(26, 48, 422, 16);
		contentPane.add(lblNewLabel);
		JLabel erroreaLabel = new JLabel("");
		erroreaLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		erroreaLabel.setBounds(26, 224, 285, 16);
		textField = new JTextField();
		textField.setBounds(26, 93, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Ezabatu erabiltzailea");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BLFacade negozioLogika=MainGUI.getBusinessLogic();
					negozioLogika.erabiltzaileaEzabatu(textField.getText());
					erabilLabel.setVisible(true);
				}catch(Exception e1){
					erroreaLabel.setText("Ezin izan da erabiltzailea ezabatu");
					erroreaLabel.setVisible(true);;
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(26, 159, 151, 25);
		contentPane.add(btnNewButton);
		
		
		contentPane.add(erroreaLabel);
		
	}
}
