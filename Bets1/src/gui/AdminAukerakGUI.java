package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminAukerakGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAukerakGUI frame = new AdminAukerakGUI();
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
	public AdminAukerakGUI() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblZerNahiDuzu = new JLabel("Zer nahi duzu egin?");
		lblZerNahiDuzu.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblZerNahiDuzu.setBounds(128, 24, 137, 16);
		contentPane.add(lblZerNahiDuzu);
		
		JButton btnLangileBerriaSortu = new JButton("Langile berria sortu");
		btnLangileBerriaSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LangileaSortuGUI ls= new LangileaSortuGUI();
				ls.setVisible(true);
			}
		});
		btnLangileBerriaSortu.setBounds(53, 83, 182, 25);
		contentPane.add(btnLangileBerriaSortu);
	}
}
