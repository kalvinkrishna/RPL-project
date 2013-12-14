package project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.LayerUI;
import java.awt.SystemColor;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Register extends JFrame {

	private static final long serialVersionUID = 7458686332627481348L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JPanel contentPane;
	private JTextField setID;
	private JTextField setPassword;
	private JTextField Filecs;
	private JTextField SetNamafield;
	private JFormattedTextField umur;
	private JTextField SetAlamat;
	private JLabel Foto ;
	private JLabel lblStatus;
	

	public Register() {
		setAutoRequestFocus(true);
		this.pack();
		setTitle("RegisterForm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 404, 440);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0,
				128, 0), new Color(128, 128, 128)));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel lblIdentitas = new JLabel("Identity");

		final JButton btnRegist = new JButton("Register");
		btnRegist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!setID.getText().equals("")
						&& !setPassword.getText().equals("")) {
					String ID = setID.getText();
					String pass = setPassword.getText();
					String nama = SetNamafield.getText();
					double usia = Double.parseDouble(umur.getText());
					String alamat = SetAlamat.getText();

					if (ID.equals("") || pass.equals("") || nama.equals("")
							|| alamat.equals("")) {
						JOptionPane.showMessageDialog(btnRegist,"Register Gagal");
					} else {
						RegistIntoDataBase(ID, pass, nama, usia, alamat);
						JOptionPane.showMessageDialog(btnRegist,"Register Berhasil");
						setVisible(false);
				
					}
				}
			}
		});

		JLabel lblFotoprofile = new JLabel("Profile");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane.createSequentialGroup()
										.addComponent(lblIdentitas)
										.addContainerGap(205, Short.MAX_VALUE))
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGap(82)
										.addComponent(btnRegist,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE).addGap(91))
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 246,
								Short.MAX_VALUE)
						.addGroup(
								gl_contentPane.createSequentialGroup()
										.addComponent(lblFotoprofile)
										.addContainerGap())
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 246,
								Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 246,
								Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane
						.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 60,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblIdentitas)
						.addGap(5)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 88,
								GroupLayout.PREFERRED_SIZE)
						.addGap(5)
						.addComponent(lblFotoprofile)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 154,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnRegist)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel_1.add(lblNewLabel);

		JLabel lblUmur = new JLabel("Age");
		lblUmur.setBounds(10, 36, 46, 14);
		panel_1.add(lblUmur);

		JLabel lblAlamat = new JLabel("Address");
		lblAlamat.setBounds(10, 61, 46, 14);
		panel_1.add(lblAlamat);

		SetAlamat = new JTextField();
		SetAlamat.setText("jl.");
		SetAlamat.setBounds(66, 58, 147, 20);
		panel_1.add(SetAlamat);
		SetAlamat.setColumns(10);

		SetNamafield = new JTextField();
		SetNamafield.setBounds(66, 8, 147, 20);
		panel_1.add(SetNamafield);
		SetNamafield.setColumns(10);

		NumberFormat numberFormat = NumberFormat.getInstance();
		umur = new JFormattedTextField(numberFormat);
		umur.setText("1");
		umur.setBounds(66, 33, 148, 21);
		umur.setColumns(16);
		umur.setFocusLostBehavior(JFormattedTextField.PERSIST);
		umur.setValue(1);

		panel_1.add(umur);
		panel_2.setLayout(null);

		Filecs = new JTextField();
		Filecs.setBounds(106, 121, 130, 20);
		panel_2.add(Filecs);
		Filecs.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(10, 33, 75, 82);
		panel_2.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		Foto= new JLabel("50 X 50");
		panel_3.add(Foto);
		Foto.setForeground(Color.LIGHT_GRAY);
		Foto.setFont(new Font("Tahoma", Font.PLAIN, 10));
		Foto.setHorizontalAlignment(SwingConstants.CENTER);
		Foto.setBackground(Color.GRAY);
		panel.setLayout(null);

		JButton btnBrowser = new JButton("Browser");
		btnBrowser.addActionListener(new ActionListener() {
			String name=null;
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				
				File file = chooser.getSelectedFile();
				name=chooser.getSelectedFile().getPath();
				Filecs.setText(name);
			
				try {
					Foto.setIcon(new ImageIcon(ImageIO.read(file)));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		btnBrowser.setBounds(10, 120, 83, 23);
		panel_2.add(btnBrowser);
		
		lblStatus = new JLabel("Alert");
		lblStatus.setForeground(Color.RED);
		lblStatus.setBounds(214, 39, 60, 14);
		panel.add(lblStatus);
		
		

		setID = new JTextField();
		setID.setBounds(61, 5, 152, 20);
		panel.add(setID);
		setID.setColumns(10);

		setPassword = new JPasswordField();
		setPassword.addKeyListener(new KeyAdapter() {
			private int count=0;
			@Override
			public void keyPressed(KeyEvent arg0) {
				count++;
				if(count>10){
					lblStatus.setText("Strength");
					lblStatus.setForeground(Color.GREEN);
				}
				else if(count==6){
					lblStatus.setText("Low");
					lblStatus.setForeground(Color.cyan);
				}
			}
		});
		setPassword.setBounds(61, 36, 152, 20);
		panel.add(setPassword);
		setPassword.setColumns(10);

		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(10, 3, 46, 14);
		panel.add(lblId);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(0, 39, 73, 14);
		panel.add(lblPassword);
		contentPane.setLayout(gl_contentPane);
	}

	public  void RegistIntoDataBase(String id, String pass, String nama,double umur, String address) {
		DataBase data = new DataBase();
		
		data.Register(id, pass, nama, umur, address);
	}
}

class ValidationLayerUI extends LayerUI<JFormattedTextField> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g, JComponent c) {
		super.paint(g, c);
		JLayer<?> jlayer = (JLayer<?>) c;
		JFormattedTextField ftf = (JFormattedTextField) jlayer.getView();
		if (!ftf.isEditValid()) {
			Graphics2D g2 = (Graphics2D) g.create();

			// Paint the red X.
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			int w = c.getWidth();
			int h = c.getHeight();
			int s = 8;
			int pad = 4;
			int x = w - pad - s;
			int y = (h - s) / 2;
			g2.setPaint(Color.red);
			g2.fillRect(x, y, s + 1, s + 1);
			g2.setPaint(Color.white);
			g2.drawLine(x, y, x + s, y + s);
			g2.drawLine(x, y + s, x + s, y);
			g2.dispose();
		}

	}
}
