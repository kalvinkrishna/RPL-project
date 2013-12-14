package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;

import javax.swing.border.EmptyBorder;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Color;
import java.awt.Font;

import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JLabel;


public class SplashScreen{
	private JWindow frame;
	private JPanel contentPane;
	private int progr=0;
	private JProgressBar progressBar;
	Timer time = new Timer(1000,new Clock());
	Login login=new Login();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try{
					
					SplashScreen window= new SplashScreen();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the application.
		 */
	public SplashScreen() {
		initialize();
	}
	
	private void initialize() {
		
		frame = new JWindow();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Hacking\\Pictures\\LifeFrame\\2.jpg"));
		frame.setFont(new Font("Dialog", Font.PLAIN, 13));
		frame.setBackground(Color.CYAN);
		frame.setOpacity(0.8f);
		frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frame.setBounds(500, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 51, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(102, 0, 255));
		progressBar.setBackground(Color.WHITE);
		progressBar.setFont(new Font("SansSerif", Font.PLAIN, 16));
		progressBar.setStringPainted(true);
		progressBar.setOpaque(true);
		
		progressBar.setBounds(63, 195, 322, 35);
		panel.add(progressBar);
		
		time.start();
		if(progressBar.getValue()>=100){
			time.stop();
		}
		JLabel lblStudentActivityManager = new JLabel("STUDENT ACTIVITY MANAGER");
		lblStudentActivityManager.setForeground(new Color(153, 204, 0));
		lblStudentActivityManager.setFont(new Font("Nueva Std", lblStudentActivityManager.getFont().getStyle() & ~Font.BOLD, 27));
		lblStudentActivityManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentActivityManager.setBounds(48, 126, 352, 57);
		panel.add(lblStudentActivityManager);
		
		JLabel lblTheLastPalace = new JLabel("THE LAST PLACE");
		lblTheLastPalace.setForeground(new Color(102, 255, 102));
		lblTheLastPalace.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
		lblTheLastPalace.setHorizontalAlignment(SwingConstants.CENTER);
		lblTheLastPalace.setBounds(48, 90, 344, 66);
		panel.add(lblTheLastPalace);
	
		

	}
	class Clock implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (progr < 100) {
				progr+=13;
				progressBar.setValue(progr);
			}
			else if(progr>=100){
				frame.setVisible(false);
				login.setVisible(true);
				time.stop();
			}
		}
		
	}
}
