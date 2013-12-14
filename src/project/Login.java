package project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

	private static final long serialVersionUID = 5621335400130660640L;

	private JPanel contentPane;
	private JPanel panel;
	private JTextField Password;
	private JTextField UserName;
	private JButton btnRegister;
	private Register regist;
	private JButton btnConnecttofacebook;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getClassName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, 402, 348);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		panel = new JPanel();
		panel.setBounds(3, 59, 390, 44);
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setForeground(new Color(47, 79, 79));
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY,
				null, null, null));

		Password = new JPasswordField();
		Password.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addGap(51)
						.addComponent(lblPassword)
						.addGap(18)
						.addComponent(Password, GroupLayout.DEFAULT_SIZE, 146,
								Short.MAX_VALUE).addGap(115)));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						Alignment.LEADING,
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														Password,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblPassword))
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(1, 10, 395, 44);
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY,
				null, null, null));

		UserName = new JTextField();
		UserName.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		UserName.setColumns(30);

		JLabel lblUsername = new JLabel("UserName");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_1
						.createSequentialGroup()
						.addGap(46)
						.addComponent(lblUsername)
						.addGap(18)
						.addComponent(UserName, GroupLayout.DEFAULT_SIZE, 146,
								Short.MAX_VALUE).addGap(120)));
		gl_panel_1
				.setVerticalGroup(gl_panel_1
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_panel_1
										.createSequentialGroup()
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblUsername)
														.addComponent(
																UserName,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		panel_1.setLayout(gl_panel_1);
		contentPane.add(panel_1);
		contentPane.add(panel);

		JButton LoginButton = new JButton("Login");
		LoginButton.setBounds(13, 114, 75, 28);
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DataBase login = new DataBase();
				String id = UserName.getText();
				String pass = Password.getText();
				login.Login(id, pass);
				if (id.equals("") || pass.equals("")) {
					JOptionPane.showMessageDialog(null,"Please Insert Your Password & ID");
				} else if (id.equals(login.getID())
						&& pass.equals(login.getPass())) {
					JOptionPane.showMessageDialog(null, "Login Success");
					
					DisplayTable tt = new DisplayTable(id);
					tt.IDcek.addItem(id);
					tt.setVisible(true);
					setVisible(false);

				} else if (id.equals("") || pass.equals("")) {
					JOptionPane.showMessageDialog(null,
							"ID and Password is Empty");
				} else {
					JOptionPane.showMessageDialog(null,
							"ID and Password not Match");
				}
			}
		});

		contentPane.add(LoginButton);

		btnRegister = new JButton("Register");
		btnRegister.setBounds(121, 114, 93, 28);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				regist = new Register();
				regist.setVisible(true);
				regist.setFocusTraversalKeysEnabled(true);
				regist.setAutoRequestFocus(true);
				setAutoRequestFocus(false);
			}
		});
		contentPane.add(btnRegister);

		btnConnecttofacebook = new JButton("ConnectToFacebook");
		btnConnecttofacebook.setBounds(224, 114, 162, 28);
		btnConnecttofacebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		contentPane.add(btnConnecttofacebook);
	}
}
