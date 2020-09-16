package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErregistratuGUI extends JFrame {

	private JPanel contentPane;
	private JTextField izenaText;
	private JTextField NANText;
	private JTextField korreoaText;
	private JTextField KZText;
	private JTextField adinaText;
	private JPasswordField pass1;
	private JPasswordField pass2;
	private JTextField emaitza;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErregistratuGUI frame = new ErregistratuGUI();
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
	public ErregistratuGUI() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIzena = new JLabel("Izena");
		lblIzena.setBounds(36, 32, 65, 14);
		contentPane.add(lblIzena);

		JLabel lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setBounds(36, 57, 103, 14);
		contentPane.add(lblPasahitza);

		JLabel lblPasahitzaErrepikatu = new JLabel("Pasahitza errepikatu");
		lblPasahitzaErrepikatu.setBounds(36, 82, 182, 14);
		contentPane.add(lblPasahitzaErrepikatu);

		JLabel lblNan = new JLabel("NAN");
		lblNan.setBounds(36, 107, 103, 14);
		contentPane.add(lblNan);

		JLabel lblKorreoa = new JLabel("Korreoa");
		lblKorreoa.setBounds(36, 132, 123, 14);
		contentPane.add(lblKorreoa);

		JLabel lblKontuZenbakia = new JLabel("Kontu zenbakia");
		lblKontuZenbakia.setBounds(36, 157, 149, 14);
		contentPane.add(lblKontuZenbakia);

		JLabel lblAdina = new JLabel("Adina");
		lblAdina.setBounds(36, 181, 103, 14);
		contentPane.add(lblAdina);

		izenaText = new JTextField();
		izenaText.setBounds(361, 32, 123, 20);
		contentPane.add(izenaText);
		izenaText.setColumns(10);

		NANText = new JTextField();
		NANText.setBounds(361, 107, 123, 20);
		contentPane.add(NANText);
		NANText.setColumns(10);

		korreoaText = new JTextField();
		korreoaText.setBounds(361, 132, 123, 20);
		contentPane.add(korreoaText);
		korreoaText.setColumns(10);

		KZText = new JTextField();
		KZText.setBounds(361, 157, 123, 20);
		contentPane.add(KZText);
		KZText.setColumns(10);

		adinaText = new JTextField();
		adinaText.setBounds(361, 181, 123, 20);
		contentPane.add(adinaText);
		adinaText.setColumns(10);

		pass1 = new JPasswordField();
		pass1.setBounds(361, 57, 123, 20);
		contentPane.add(pass1);

		pass2 = new JPasswordField();
		pass2.setBounds(361, 82, 123, 20);
		contentPane.add(pass2);

		emaitza = new JTextField();
		emaitza.setBounds(36, 286, 265, 20);
		contentPane.add(emaitza);
		emaitza.setColumns(10);

		JButton btnNewButton = new JButton("Erregistratu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				emaitza.setText("");
				BLFacade negozioLogika=MainGUI.getBusinessLogic();
				try {
					if(negozioLogika.adinaOndo(adinaText.getText())){
						if(negozioLogika.izenaOndo(izenaText.getText())){
							if (negozioLogika.passOndo(new String(pass1.getPassword()), new String(pass2.getPassword()))){
								negozioLogika.erregistratu(izenaText.getText(), new String(pass1.getPassword()), NANText.getText(), korreoaText.getText(), KZText.getText(), adinaText.getText());
								emaitza.setText("Erregistratu zara.");
							}
							else emaitza.setText("Pasahitza ezberdinak sartu dituzu.");
						}
						else emaitza.setText("Izen horrekin badago erabiltzaile bat.");
					}
					else emaitza.setText("+18 urte behar dira!!!!");
				}
				catch(Exception ee) {
					ee.printStackTrace();
					emaitza.setText("Gelaxka guztiak ongi bete behar dituzu.");
				}
			}
		});
		btnNewButton.setBounds(361, 211, 123, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Log in");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(361, 238, 123, 23);
		contentPane.add(btnNewButton_1);
	}
}
