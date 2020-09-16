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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(30, 55, 103, 16);
		contentPane.add(lblUser);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(30, 95, 127, 16);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(198, 52, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(198, 92, 116, 22);
		contentPane.add(passwordField);
		
		textField_1 = new JTextField();
		textField_1.setBounds(30, 216, 455, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		final JRadioButton UserButton = new JRadioButton("User");
		UserButton.setSelected(true);
		buttonGroup.add(UserButton);
		UserButton.setBounds(48, 143, 109, 23);
		contentPane.add(UserButton);
		
		final JRadioButton WorkerButton = new JRadioButton("Worker");
		buttonGroup.add(WorkerButton);
		WorkerButton.setBounds(173, 143, 109, 23);
		contentPane.add(WorkerButton);
		
		final JRadioButton AdminButton = new JRadioButton("Admin");
		buttonGroup.add(AdminButton);
		AdminButton.setBounds(306, 143, 109, 23);
		contentPane.add(AdminButton);
		
		JButton btnSartu = new JButton("Sartu");
		btnSartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BLFacade negozioLogika=MainGUI.getBusinessLogic();
				
				
				boolean b=false;
				
				if(UserButton.isSelected()){
					b=negozioLogika.isLoginUser(textField.getText(),new String(passwordField.getPassword()));
					if (b) {
						MainGUI a =new MainGUI(textField.getText());
						a.setVisible(true);
						textField_1.setText("Sartu zara");
						textField.setText("");
						passwordField.setText("");
					}else {
						textField_1.setText("Ez duzu ondo sartu erabiltzailea edo pasahitza edo ez daukazu baimena egokia");
					}
				}
					
				else if(WorkerButton.isSelected()){
					b=negozioLogika.isLoginWorker(textField.getText(),new String(passwordField.getPassword()));
					if (b) {
						WorkerAukerakGUI a =new WorkerAukerakGUI();
						a.setVisible(true);
						textField_1.setText("Sartu zara");
						textField.setText("");
						passwordField.setText("");
					}else {
						textField_1.setText("Ez duzu ondo sartu erabiltzailea edo pasahitza edo ez daukazu baimena egokia");
					}
				}

				else if(AdminButton.isSelected()){
					b=negozioLogika.isLoginAdmin(textField.getText(),new String(passwordField.getPassword()));
					if (b) {
						AdminAukerakGUI a =new AdminAukerakGUI();
						a.setVisible(true);
						textField_1.setText("Sartu zara");
						textField.setText("");
						passwordField.setText("");
					}else {
						textField_1.setText("Ez duzu ondo sartu erabiltzailea edo pasahitza edo ez daukazu baimena egokia");
					}
				}
			}
	
		});
		btnSartu.setBounds(173, 178, 123, 25);
		contentPane.add(btnSartu);	
	}
}
