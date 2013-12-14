package project;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Popup;
import javax.swing.ScrollPaneConstants;
import javax.swing.SingleSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuListener;
import javax.swing.ButtonGroup;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.calendar.*;
import com.google.api.services.calendar.model.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.Json;
import com.toedter.calendar.JDateChooser;

import javax.swing.JSplitPane;

import java.awt.Component;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.SystemColor;

import javax.swing.JRadioButton;

import java.awt.Insets;

import javax.swing.SpinnerListModel;

import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.Toolkit;
import java.awt.Choice;
import com.toedter.calendar.JCalendar;
import javax.swing.BoxLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;

public class DisplayTable extends JFrame {

	private static final long serialVersionUID = 9218939800337047629L;
	private JPanel contentPane;
	private DataBase data;
	private JTextField searchKata;
	private String order;
	private String sort = "ASC";
	private String nama = null;
	private JTable tableDisplay;
	private JTextField uaktivitas;
	private JTextField ulokasi;
	private JPanel updatePane = new JPanel();
	private JTextField uno;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable tablelog;
	private JLabel t;
	private JLabel Bul;
	private JLabel Tangg;
	private JTextField NameTask;
	private JTextField Location;
	private ImageIcon check;
	private int checking = 0;
	JComboBox<String> IDcek;
	private Date date = new Date();
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");;
	Stack<Stacked> st = new Stack<Stacked>();
	Stack<Stacked> redo = new Stack<Stacked>();
	SimpleDateFormat formati = new SimpleDateFormat("dd-MM-yyyy");
	Date convert = null;


	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayTable frame = new DisplayTable(null);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DisplayTable(String name) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				DisplayTable.class
						.getResource("/project/icon/black-bird-512.png")));
		try {
			for (UIManager.LookAndFeelInfo info : UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		setFocusableWindowState(true);
		setResizable(false);
		setAutoRequestFocus(false);
		setTitle("Activity");
		setType(Type.POPUP);

		this.nama = name;
		setBounds(350, 10, 755, 700);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		data = new DataBase();

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 749, 20);
		contentPane.add(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		final JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.setIcon(new ImageIcon(DisplayTable.class
				.getResource("/project/icon/Download-Folder-icon.png")));
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser file = new JFileChooser();
				file.showSaveDialog(null);

			}
		});
		mnFile.add(mntmOpen);

		JMenuItem mntmLogOu = new JMenuItem("Log out");
		mntmLogOu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login login = new Login();
				setVisible(false);
				login.setVisible(true);
			}
		});

		mntmLogOu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Login login = new Login();
				setVisible(false);
				login.setVisible(true);
			}
		});

		mntmLogOu.setIcon(new ImageIcon(DisplayTable.class
				.getResource("/project/icon/Apps-error-icon.png")));
		mnFile.add(mntmLogOu);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		final JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(mntmAbout,"Created By The Last Place");
			}
		});
		mntmAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
		mnHelp.add(mntmAbout);

		// Panel Display
		final JPanel PanelSideOne = new JPanel();
		PanelSideOne.setBounds(0, 0, 749, 644);
		contentPane.add(PanelSideOne);
		PanelSideOne.setBackground(SystemColor.inactiveCaption);
		PanelSideOne.setLayout(null);
		PanelSideOne.setVisible(false);
		// Sorting
		final JComboBox<Object> Sorting = new JComboBox<Object>(
				new DefaultComboBoxModel<Object>(new String[] { "NO",
						"deskripsi", "lokasi", "tanggal", "jenis" }));
		Sorting.setBounds(10, 359, 66, 20);
		PanelSideOne.add(Sorting);

		final JComboBox<String> SortBy = new JComboBox<String>(
				new DefaultComboBoxModel<String>(new String[] { "", "ASC",
						"DESC" }));
		SortBy.setBounds(147, 359, 80, 20);
		PanelSideOne.add(SortBy);
		SortBy.setEditable(true);
		SortBy.setSelectedIndex(1);

		JLabel lblSearch = new JLabel("");
		lblSearch.setBounds(706, 20, 24, 33);
		PanelSideOne.add(lblSearch);
		lblSearch.setIcon(new ImageIcon(DisplayTable.class
				.getResource("/project/icon/search-icon.png")));
		
				final JLabel label_1 = new JLabel("");
				label_1.setEnabled(false);
				label_1.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						if (!redo.empty()) {
							Stacked st2 = new Stacked();
							
							st2 = redo.peek();
							if (redo.peek().getEvent().equals("DELETE")) {
								data.Delete(st2.getNo() + 1);
								tableDisplay.setModel(DbUtils.resultSetToTableModel(data.Display(nama)));
								redo.pop();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Stop for Redo");
						}
					}

				});
				label_1.setIcon(new ImageIcon(DisplayTable.class
						.getResource("/project/icon/Redo-icon.png")));
				label_1.setBounds(432, 38, 33, 28);
				PanelSideOne.add(label_1);

		JLabel Logging = new JLabel("Log");
		Logging.setBounds(617, 50, 55, 25);
		PanelSideOne.add(Logging);
		Logging.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Logging.setHorizontalAlignment(SwingConstants.CENTER);
		final JLabel label = new JLabel("");
		label.setBounds(706, 369, 33, 45);
		PanelSideOne.add(label);
		label.addMouseListener(new MouseAdapter() {
			int row;
			int value;

			@Override
			public void mouseEntered(MouseEvent arg0) {
				label.setIcon(new ImageIcon(DisplayTable.class
						.getResource("/project/icon/Recycle-Bin-Full-icon.png")));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				label.setIcon(new ImageIcon(
						DisplayTable.class
								.getResource("/project/icon/Recycle-Bin-empty-icon.png")));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				row = tableDisplay.getSelectedRow();
				if (row >= 0) {
					value = Integer.parseInt(tableDisplay.getModel()
							.getValueAt(row, 0).toString());
					DeleteToStack(value);
					String values = tableDisplay.getModel().getValueAt(row, 2)
							.toString()
							+ " "
							+ tableDisplay.getModel().getValueAt(row, 3)
									.toString()
							+ " "
							+ tableDisplay.getModel().getValueAt(row, 4)
									.toString()
							+ " "
							+ tableDisplay.getModel().getValueAt(row, 5)
									.toString();

					data.LogView("Delete", values, nama);
					data.Delete(value);

					tablelog.setModel(DbUtils.resultSetToTableModel(data
							.Logging(nama)));
					tableDisplay.setModel(DbUtils.resultSetToTableModel(data
							.Display(nama)));

				} else {
					JOptionPane.showMessageDialog(PanelSideOne, "No data");
				}

			}

			void DeleteToStack(int number) {
				Stacked st1 = new Stacked();
				st1.setEvent("DELETE");
				st1.setNo(number);
				st1.setDeskripsi(tableDisplay.getModel().getValueAt(row, 1)
						.toString());
				st1.setTempat(tableDisplay.getModel().getValueAt(row, 2)
						.toString());
				st1.setTanggal(tableDisplay.getModel().getValueAt(row, 3)
						.toString());
				st1.setTanggals(tableDisplay.getModel().getValueAt(row, 5)
						.toString());
				st1.setStart(tableDisplay.getModel().getValueAt(row, 4)
						.toString());
				st1.setEnd(tableDisplay.getModel().getValueAt(row, 6)
						.toString());
				st1.setJenis(tableDisplay.getModel().getValueAt(row, 7)
						.toString());

				st.push(st1);

				System.out.println("Masuk dalam stack");
			}

		});
		label.setIcon(new ImageIcon(DisplayTable.class
				.getResource("/project/icon/Recycle-Bin-empty-icon.png")));

		JLabel Undo = new JLabel("");
		Undo.setToolTipText("Undo");
		Undo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				Stacked st1 = new Stacked();
				if (!st.empty()) {
					label_1.setEnabled(true);
					if (st.peek().getEvent().equals("INSERT")) {
						int value = Integer.parseInt(tableDisplay
								.getModel()
								.getValueAt((tableDisplay.getRowCount() - 1), 0)
								.toString());
						data.Delete(value);
						tableDisplay.setModel(DbUtils
								.resultSetToTableModel(data.Display(nama)));
						//redo.push(st.peek());
						st.pop();

					} else if (st.peek().getEvent().equals("DELETE")) {
						st1 = st.peek();
						redo.push(st1);
						if (st1.getJenis().equals("DeadLine")) {
							try {
								convert = formati.parse(st1.getTanggals());
								String change = format.format(convert);
								st1.setTanggals(change);
								st.pop();
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							data.InsertDataDeadLine(nama, st1.getDeskripsi(),
									st1.getTempat(), st1.getTanggals(),
									st1.getEnd(), st1.getJenis());
							tableDisplay.setModel(DbUtils
									.resultSetToTableModel(data.Display(nama)));

						} else if (st1.getJenis().equals("Time")) {

							try {
								convert = formati.parse(st1.getTanggals());
								String change = format.format(convert);
								st1.setTanggals(change);
								convert = formati.parse(st1.getTanggal());
								change = format.format(convert);
								st1.setTanggal(change);
								st.pop();
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							data.InsertDataTime(nama, st1.getDeskripsi(),
									st1.getTempat(), st1.getTanggal(),
									st1.getTanggals(), st1.getStart(),
									st1.getEnd(), st1.getJenis());
							tableDisplay.setModel(DbUtils
									.resultSetToTableModel(data.Display(nama)));

						} else if (st1.getJenis().equals("Float")) {

							data.InsertDataFloat(nama, st1.getDeskripsi(),
									st1.getTempat(), st1.getJenis());
							tableDisplay.setModel(DbUtils
									.resultSetToTableModel(data.Display(nama)));
							st.pop();
						}
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Stop For Undo");
				}

				if (!isVisible()) {
					st.clear();
				}
			}
		});
		Undo.setBounds(10, 39, 33, 29);
		PanelSideOne.add(Undo);
		Undo.setIcon(new ImageIcon(DisplayTable.class
				.getResource("/project/icon/Undo-icon.png")));
		searchKata = new JTextField();
		searchKata.setBounds(574, 20, 131, 33);
		PanelSideOne.add(searchKata);
		searchKata.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				searchKata.selectAll();
			}
		});
		searchKata.setText("Type Here");
		searchKata.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String kata = searchKata.getText().toString();
				TableModel model = DbUtils.resultSetToTableModel(data.Search(
						kata, nama));
				tableDisplay.setModel(model);
			}
		});
		searchKata.setColumns(10);

		final JSplitPane MainSplitPanel = new JSplitPane();
		MainSplitPanel.setBounds(0, 69, 739, 288);
		PanelSideOne.add(MainSplitPanel);
		MainSplitPanel.setResizeWeight(1.0);
		MainSplitPanel.setOneTouchExpandable(true);
		MainSplitPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		MainSplitPanel.setDividerLocation(500);
		JPanel LogPanel = new JPanel();
		MainSplitPanel.setRightComponent(LogPanel);
		LogPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneLog = new JScrollPane();
		scrollPaneLog
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneLog
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		LogPanel.add(scrollPaneLog, BorderLayout.NORTH);

		tablelog = new JTable();
		scrollPaneLog.setViewportView(tablelog);
		tablelog.setModel(DbUtils.resultSetToTableModel(data.Logging(nama)));

		JPanel DisplayPanel = new JPanel();
		MainSplitPanel.setLeftComponent(DisplayPanel);
		DisplayPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED,
				Color.DARK_GRAY, null, new Color(64, 64, 64), new Color(64, 64,
						64)));
		scrollPane.setBounds(6, 6, 725, 266);
		DisplayPanel.add(scrollPane);

		tableDisplay = new JTable(DbUtils.resultSetToTableModel(data
				.Display(nama)));

		tableDisplay.setDragEnabled(true);

		tableDisplay.setDragEnabled(true);
		tableDisplay.setDropMode(DropMode.ON_OR_INSERT);
		tableDisplay.setTransferHandler(new TransferHandler(null));
		tableDisplay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				checking = tableDisplay.getSelectedRow();// jika row yang di
															// klik
															// -1 atau ngak ada
				if (checking < 0) {
					JOptionPane.showMessageDialog(tableDisplay,
							"Sorry You selected has no choose");

				} else {
					uno.setText(tableDisplay.getValueAt(
							tableDisplay.getSelectedRow(), 0).toString());
					uaktivitas.setText(tableDisplay.getValueAt(
							tableDisplay.getSelectedRow(), 1).toString());
					ulokasi.setText(tableDisplay.getValueAt(
							tableDisplay.getSelectedRow(), 2).toString());
				}
			}
		});
		tableDisplay.setBackground(SystemColor.inactiveCaptionBorder);
		tableDisplay.setFillsViewportHeight(true);
		tableDisplay.setFont(new Font("Myriad Pro Light", Font.PLAIN, 14));
		tableDisplay
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(tableDisplay);
		final JDateChooser Updatetanggal = new JDateChooser();
		Updatetanggal.setBounds(74, 208, 122, 20);
		updatePane.add(Updatetanggal);
		final JButton btnNewButton = new JButton("Update");
		btnNewButton.setBounds(40, 39, 126, 29);
		PanelSideOne.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updatePane.setVisible(true);
				uaktivitas.setText(null);
				ulokasi.setText(null);
				Updatetanggal.setDate(null);
				uno.setText(null);
			}
		});

		buttonGroup.add(btnNewButton);
		btnNewButton.setBackground(new Color(255, 0, 0));

		JButton btnCleartable = new JButton("Clear Table");
		btnCleartable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checking = tableDisplay.getSelectedRow();
				if (checking < 0) {
					JOptionPane.showMessageDialog(tableDisplay,
							"No row selected");
				} else {
					data.clear(nama, "Table");
					tableDisplay.setModel(DbUtils.resultSetToTableModel(data
							.Display(nama)));
				}
			}
		});
		updatePane.setBounds(10, 380, 244, 264);
		PanelSideOne.add(updatePane);
		updatePane.setBorder(new LineBorder(null, 1, true));

		updatePane.setBackground(new Color(192, 192, 192));
		updatePane.setLayout(null);

		uaktivitas = new JTextField();
		uaktivitas.setBounds(74, 40, 122, 22);
		updatePane.add(uaktivitas);
		uaktivitas.setColumns(10);

		ulokasi = new JTextField();
		ulokasi.setBounds(74, 76, 122, 22);
		updatePane.add(ulokasi);
		ulokasi.setColumns(10);
		
				final JSpinner usjam = new JSpinner();
				usjam.setModel(new SpinnerListModel(new String[] { "00", "01", "02",
						"03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
						"13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
						"23", "24", "25", "26", "27", "28", "29", "30", "31", "32",
						"33", "34", "35", "36", "37", "38", "39", "40", "41", "42",
						"43", "44", "45", "46", "47", "48", "49", "50", "51", "52",
						"53", "54", "55", "56", "57", "58", "59", "60" }));
				usjam.setBounds(76, 135, 46, 23);
				updatePane.add(usjam);
		
				final JSpinner usmenit = new JSpinner();
				usmenit.setModel(new SpinnerListModel(new String[] { "00", "01", "02",
						"03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
						"13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
						"23", "24", "25", "26", "27", "28", "29", "30", "31", "32",
						"33", "34", "35", "36", "37", "38", "39", "40", "41", "42",
						"43", "44", "45", "46", "47", "48", "49", "50", "51", "52",
						"53", "54", "55", "56", "57", "58", "59", "60" }));
				usmenit.setBounds(134, 135, 46, 23);
				updatePane.add(usmenit);
		
				final JSpinner usdetik = new JSpinner();
				usdetik.setModel(new SpinnerListModel(new String[] { "00", "01", "02",
						"03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
						"13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
						"23", "24", "25", "26", "27", "28", "29", "30", "31", "32",
						"33", "34", "35", "36", "37", "38", "39", "40", "41", "42",
						"43", "44", "45", "46", "47", "48", "49", "50", "51", "52",
						"53", "54", "55", "56", "57", "58", "59", "60" }));
				usdetik.setBounds(192, 135, 46, 23);
				updatePane.add(usdetik);
		
				final JSpinner uejam = new JSpinner();
				uejam.setModel(new SpinnerListModel(new String[] { "00", "01", "02",
						"03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
						"13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
						"23", "24" }));
				uejam.setBounds(76, 167, 46, 20);
				updatePane.add(uejam);
		
				final JSpinner uemenit = new JSpinner();
				uemenit.setModel(new SpinnerListModel(new String[] { "00", "01",
						"02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
						"12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
						"22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
						"32", "33", "34", "35", "36", "37", "38", "39", "40", "41",
						"42", "43", "44", "45", "46", "47", "48", "49", "50", "51",
						"52", "53", "54", "55", "56", "57", "58", "59", "60" }));
				uemenit.setBounds(134, 167, 46, 20);
				updatePane.add(uemenit);
		
				final JSpinner uedeti = new JSpinner();
				uedeti.setModel(new SpinnerListModel(new String[] { "00", "01",
						"02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
						"12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
						"22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
						"32", "33", "34", "35", "36", "37", "38", "39", "40", "41",
						"42", "43", "44", "45", "46", "47", "48", "49", "50", "51",
						"52", "53", "54", "55", "56", "57", "58", "59", "60" }));
				uedeti.setBounds(192, 167, 46, 20);
				updatePane.add(uedeti);

		JLabel lblActivity = new JLabel("Activity");
		lblActivity.setHorizontalAlignment(SwingConstants.CENTER);
		lblActivity.setBounds(6, 40, 55, 16);
		updatePane.add(lblActivity);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocation.setBounds(6, 76, 55, 16);
		updatePane.add(lblLocation);
		final JComboBox<Object> ukind = new JComboBox<Object>();
		ukind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ukind.getSelectedIndex()==0){
					uejam.setVisible(true);
					uedeti.setVisible(true);
					uemenit.setVisible(true);
					usjam.setVisible(false);
					usdetik.setVisible(false);
					usmenit.setVisible(false);
				}
				else if(ukind.getSelectedIndex()==1){
					uejam.setVisible(false);
					uedeti.setVisible(false);
					uemenit.setVisible(false);
					usjam.setVisible(false);
					usdetik.setVisible(false);
					usmenit.setVisible(false);
				}
				else{
					uejam.setVisible(true);
					uedeti.setVisible(true);
					uemenit.setVisible(true);
					usjam.setVisible(true);
					usdetik.setVisible(true);
					usmenit.setVisible(true);
				}
			}
		});
		ukind.setModel(new DefaultComboBoxModel<Object>(new String[] {
				"DeadLine", "Float", "Time" }));
		ukind.setSelectedIndex(0);
		ukind.setBounds(74, 100, 122, 28);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new ImageIcon(DisplayTable.class
				.getResource("/project/icon/Tools-icon.png")));
		btnUpdate.addActionListener(new ActionListener() {
			private Date now = new Date();

				public void actionPerformed(ActionEvent arg0) {
				if (!Updatetanggal.equals("")&&(Updatetanggal.getDate().after(now) || Updatetanggal.getDate() == now) && tableDisplay.getRowCount() >= 0) {
					String kegiatan = uaktivitas.getText();
					String lokasi = ulokasi.getText();
					String tanggal = format.format(Updatetanggal.getDate());
					String tanggals= format.format(Updatetanggal.getDate());
					String start = usdetik+":"+usmenit+":"+usjam;
					String end   = uedeti+":"+uemenit+":"+uejam;
					String no = uno.getText();
					
					String kind = ukind.getSelectedItem().toString();
					data.Update(nama, no, kegiatan, lokasi, tanggal,tanggals,start,end,kind);
					
					
			
					
					data.LogView("Update", "Aktivitas", nama);
					tableDisplay.setModel(DbUtils.resultSetToTableModel(data
							.Display(nama)));
					tablelog.setModel(DbUtils.resultSetToTableModel(data
							.Logging(nama)));
					updatePane.setVisible(false);

				} else {
					JOptionPane.showMessageDialog(updatePane,
							"Please check your input Update");
				}

			}
		});
		btnUpdate.setBounds(68, 230, 107, 34);
		updatePane.add(btnUpdate);
		updatePane.add(ukind);

		JLabel lblJenis = new JLabel("Kind");
		lblJenis.setHorizontalAlignment(SwingConstants.CENTER);
		lblJenis.setBounds(13, 110, 45, 16);
		updatePane.add(lblJenis);

		JLabel lblNo = new JLabel("No");
		lblNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNo.setBounds(6, 12, 55, 16);
		updatePane.add(lblNo);

		uno = new JTextField();
		uno.setEditable(false);
		uno.setEnabled(false);
		uno.setBounds(74, 9, 121, 22);
		updatePane.add(uno);
		uno.setColumns(10);

		JLabel lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(6, 214, 46, 14);
		updatePane.add(lblDate);

		JLabel lblStart = new JLabel("start");
		lblStart.setBounds(20, 140, 29, 16);
		updatePane.add(lblStart);
		
		JLabel lblEnd = new JLabel("end");
		lblEnd.setBounds(20, 170, 21, 16);
		updatePane.add(lblEnd);
		updatePane.setVisible(false);

		btnCleartable.setBounds(170, 39, 126, 29);
		PanelSideOne.add(btnCleartable);
		btnCleartable.setBackground(Color.ORANGE);

		// button clear log
		JButton btnClearlog = new JButton("Clear log");
		btnClearlog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checking = tableDisplay.getRowCount();
				if (checking < 0) {
					JOptionPane.showMessageDialog(tableDisplay,
							"No row selected");
				} else {
					data.clear(nama, "Log");
					tablelog.setModel(DbUtils.resultSetToTableModel(data
							.Logging(nama)));
				}
			}
		});
		btnClearlog.setBounds(300, 39, 126, 29);
		PanelSideOne.add(btnClearlog);
		btnClearlog.setForeground(new Color(70, 130, 180));
		btnClearlog.setBackground(Color.GREEN);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.DARK_GRAY, null));
		panel_5.setBounds(608, 547, 131, 91);
		PanelSideOne.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6);
		panel_6.setLayout(null);
		
		Bul = new JLabel("DEC");
		Bul.setForeground(new Color(0, 0, 255));
		Bul.setFont(new Font("Victorian LET", Font.BOLD, 24));
		Bul.setBounds(0, 62, 130, 33);
		panel_6.add(Bul);
		Bul.setHorizontalAlignment(SwingConstants.CENTER);
		
		Tangg = new JLabel("");
		Tangg.setForeground(Color.BLUE);
		Tangg.setFont(new Font("Westwood LET", Font.PLAIN, 42));
		Tangg.setBounds(15, 6, 100, 45);
		panel_6.add(Tangg);
		Tangg.setHorizontalAlignment(SwingConstants.CENTER);
		SortBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort = (String) SortBy.getSelectedItem();
				tableDisplay.setModel(DbUtils.resultSetToTableModel(data.Sort(
						nama, order, sort)));
			}
		});
		Sorting.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				order = (String) Sorting.getSelectedItem();
				tableDisplay.setModel(DbUtils.resultSetToTableModel(data.Sort(
						nama, order, sort)));
				String value = "Sort " + order + " by " + sort;
				data.LogView("Sort", value, nama);
				tablelog.setModel(DbUtils.resultSetToTableModel(data
						.Logging(nama)));
				System.out.println(tableDisplay.getRowCount());
			}
		});
		PanelSideOne.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent Me) {
				if (Me.isPopupTrigger()) {
					PopUp pop = new PopUp();
					pop.show(Me.getComponent(), Me.getX(), Me.getY());
					
				}
			}
		});

		// Insert Panel
		final JPanel panelInsert = new JPanel();
		panelInsert.setBackground(SystemColor.inactiveCaption);
		panelInsert.setBounds(0, 11, 749, 633);
		contentPane.add(panelInsert);
		panelInsert.setLayout(null);

		NameTask = new JTextField();
		NameTask.setToolTipText("Its your task input");
		NameTask.setBounds(121, 53, 162, 28);
		panelInsert.add(NameTask);
		NameTask.setColumns(10);

		JLabel lblTask = new JLabel("Task");
		lblTask.setBounds(27, 59, 55, 16);
		panelInsert.add(lblTask);

		JLabel lblDatedayStart = new JLabel("Date/Day Start");
		lblDatedayStart.setBounds(27, 167, 99, 28);
		panelInsert.add(lblDatedayStart);

		final JDateChooser StartDate = new JDateChooser();
		StartDate.setToolTipText("choose this to have\r\nyour Start date");
		StartDate.setBounds(121, 167, 176, 28);
		panelInsert.add(StartDate);
		StartDate.setDate(date); // set tanggal sekarang saat run

		JLabel label_3 = new JLabel("Time");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(380, 134, 33, 14);
		panelInsert.add(label_3);

		final JDateChooser EndDate = new JDateChooser();
		EndDate.getCalendarButton().setToolTipText(
				"Choose this to have your\r\nEnd Date");
		EndDate.setBounds(121, 266, 176, 28);
		panelInsert.add(EndDate);
		EndDate.setDate(date);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Clear Your Data Above");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NameTask.setText(null);
				Location.setText(null);
				EndDate.setDate(null);
				StartDate.setDate(null);
			}
		});
		btnCancel.setIcon(new ImageIcon(DisplayTable.class
				.getResource("/project/icon/Apps-error-icon.png")));
		btnCancel.setBounds(321, 564, 107, 38);
		panelInsert.add(btnCancel);

		JButton btnLogOut = new JButton("Exit");
		btnLogOut.setToolTipText("Exit for Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnLogOut.setIcon(new ImageIcon(DisplayTable.class
				.getResource("/project/icon/delete-icon.png")));
		btnLogOut.setBounds(627, 564, 99, 38);
		panelInsert.add(btnLogOut);

		JLabel label_6 = new JLabel("Location");
		label_6.setBounds(27, 382, 67, 28);
		panelInsert.add(label_6);

		Location = new JTextField();
		Location.setBounds(121, 382, 131, 28);
		panelInsert.add(Location);
		Location.setColumns(10);

		IDcek = new JComboBox<String>();
		IDcek.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				IDcek.setToolTipText("Its your ID account");
			}
		});
		IDcek.setBounds(627, 22, 115, 26);
		panelInsert.add(IDcek);

		JLabel label_8 = new JLabel("Start");
		label_8.setBounds(554, 173, 55, 16);
		panelInsert.add(label_8);
		label_8.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel label_9 = new JLabel("End");
		label_9.setBounds(554, 272, 55, 16);
		panelInsert.add(label_9);
		label_9.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblDatedayEnd = new JLabel("Date/Day End");
		lblDatedayEnd.setBounds(27, 266, 99, 28);
		panelInsert.add(lblDatedayEnd);

		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setHorizontalAlignment(SwingConstants.CENTER);
		lblPriority.setBounds(413, 27, 55, 16);
		panelInsert.add(lblPriority);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.inactiveCaption);
		panel_2.setBounds(309, 160, 201, 38);
		panelInsert.add(panel_2);

		final JSpinner StartTime = new JSpinner();
		panel_2.add(StartTime);
		StartTime.setModel(new SpinnerListModel(new String[] { "00", "01",
				"02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
				"12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
				"22", "23", "24" }));

		JLabel label_5 = new JLabel(":");
		panel_2.add(label_5);
		label_5.setFont(new Font("Snap ITC", Font.BOLD, 12));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);

		final JSpinner StartTime1 = new JSpinner();
		StartTime1.setModel(new SpinnerListModel(new String[] { "00", "01",
				"02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
				"12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
				"22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
				"32", "33", "34", "35", "36", "37", "38", "39", "40", "41",
				"42", "43", "44", "45", "46", "47", "48", "49", "50", "51",
				"52", "53", "54", "55", "56", "57", "58", "59", "60" }));
		panel_2.add(StartTime1);

		JLabel label_7 = new JLabel(":");
		panel_2.add(label_7);
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Snap ITC", Font.BOLD, 12));

		final JSpinner StartTime2 = new JSpinner();
		StartTime2.setModel(new SpinnerListModel(new String[] { "00", "01",
				"02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
				"12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
				"22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
				"32", "33", "34", "35", "36", "37", "38", "39", "40", "41",
				"42", "43", "44", "45", "46", "47", "48", "49", "50", "51",
				"52", "53", "54", "55", "56", "57", "58", "59", "60" }));
		panel_2.add(StartTime2);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.inactiveCaption);
		panel_3.setBounds(309, 256, 201, 38);
		panelInsert.add(panel_3);

		final JSpinner EndTime = new JSpinner();
		EndTime.setModel(new SpinnerListModel(new String[] { " 00", "01", "02",
				"03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
				"23", "24", "25", "26", "27", "28", "29", "30", "31", "32",
				"33", "34", "35", "36", "37", "38", "39", "40", "41", "42",
				"43", "44", "45", "46", "47", "48", "49", "50", "51", "52",
				"53", "54", "55", "56", "57", "58", "59", "60" }));
		panel_3.add(EndTime);

		JLabel label_10 = new JLabel(":");
		panel_3.add(label_10);
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setFont(new Font("Snap ITC", Font.BOLD, 12));

		final JSpinner EndTime1 = new JSpinner();
		EndTime1.setModel(new SpinnerListModel(new String[] { "00", "01", "02",
				"03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
				"23", "24", "25", "26", "27", "28", "29", "30", "31", "32",
				"33", "34", "35", "36", "37", "38", "39", "40", "41", "42",
				"43", "44", "45", "46", "47", "48", "49", "50", "51", "52",
				"53", "54", "55", "56", "57", "58", "59", "60" }));
		panel_3.add(EndTime1);

		JLabel label_11 = new JLabel(":");
		panel_3.add(label_11);
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setFont(new Font("Snap ITC", Font.BOLD, 12));

		final JSpinner EndTime2 = new JSpinner();
		EndTime2.setModel(new SpinnerListModel(new String[] { "00", "01", "02",
				"03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
				"23", "24", "25", "26", "27", "28", "29", "30", "31", "32",
				"33", "34", "35", "36", "37", "38", "39", "40", "41", "42",
				"43", "44", "45", "46", "47", "48", "49", "50", "51", "52",
				"53", "54", "55", "56", "57", "58", "59", "60" }));
		panel_3.add(EndTime2);

		JPanel panel = new JPanel();
		panel.setToolTipText("What this ??\r\nthis is your Activity Type\r\n");
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY,
				Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY));
		panel.setBounds(613, 159, 136, 165);
		panelInsert.add(panel);
		panel.setLayout(null);

		final JRadioButton DeadLine = new JRadioButton("DeadLine");
		DeadLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StartDate.setVisible(true);
				StartTime.setVisible(true);
				StartTime1.setVisible(true);
				StartTime2.setVisible(true);
				StartDate.setVisible(false);
				StartTime.setVisible(false);
				StartTime1.setVisible(false);
				StartTime2.setVisible(false);
			}
		});
		DeadLine.setBounds(6, 6, 76, 18);
		panel.add(DeadLine);
		buttonGroup.add(DeadLine);

		final JRadioButton Time = new JRadioButton("Time");
		Time.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StartDate.setVisible(true);
				StartTime.setVisible(true);
				StartTime1.setVisible(true);
				StartTime2.setVisible(true);
				EndDate.setVisible(true);
				EndTime.setVisible(true);
				EndTime1.setVisible(true);
				EndTime2.setVisible(true);
			}
		});
		Time.setBounds(6, 126, 50, 18);
		panel.add(Time);
		buttonGroup.add(Time);

		final JRadioButton Float = new JRadioButton("Float");
		Float.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StartDate.setVisible(false);
				StartTime.setVisible(false);
				StartTime1.setVisible(false);
				StartTime2.setVisible(false);
				EndDate.setVisible(false);
				EndTime.setVisible(false);
				EndTime1.setVisible(false);
				EndTime2.setVisible(false);
			}
		});
		Float.setBounds(6, 63, 49, 18);
		panel.add(Float);
		buttonGroup.add(Float);

		final JLabel deadbirth = new JLabel("");
		deadbirth.setLabelFor(DeadLine);
		deadbirth.setBounds(0, 0, 136, 69);
		panel.add(deadbirth);
		deadbirth.setHorizontalAlignment(SwingConstants.RIGHT);
		deadbirth.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				deadbirth.setIcon(new ImageIcon(DisplayTable.class
						.getResource("/project/icon/red-bird-48.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				deadbirth.setIcon(new ImageIcon(DisplayTable.class
						.getResource("/project/icon/red-bird-32.png")));
			}
		});
		deadbirth.setIcon(new ImageIcon(DisplayTable.class
				.getResource("/project/icon/red-bird-32.png")));

		final JLabel timebird = new JLabel("");
		timebird.setLabelFor(Time);
		timebird.setBounds(0, 109, 136, 56);
		panel.add(timebird);
		timebird.setHorizontalAlignment(SwingConstants.RIGHT);
		timebird.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				timebird.setIcon(new ImageIcon(DisplayTable.class
						.getResource("/project/icon/green-bird-48.png")));
			}

			public void mouseExited(MouseEvent e) {

				timebird.setIcon(new ImageIcon(DisplayTable.class
						.getResource("/project/icon/green-bird-32.png")));
			}
		});
		timebird.setIcon(new ImageIcon(DisplayTable.class
				.getResource("/project/icon/green-bird-32.png")));

		final JLabel FloatBird = new JLabel("");
		FloatBird.setLabelFor(Float);
		FloatBird.setBounds(0, 57, 136, 56);
		panel.add(FloatBird);
		FloatBird.setHorizontalAlignment(SwingConstants.RIGHT);
		FloatBird.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				FloatBird.setIcon(new ImageIcon(DisplayTable.class
						.getResource("/project/icon/yellow-bird-48.png")));
			}

			public void mouseExited(MouseEvent e) {

				FloatBird.setIcon(new ImageIcon(DisplayTable.class
						.getResource("/project/icon/yellow-bird-32.png")));
			}
		});
		FloatBird.setIcon(new ImageIcon(DisplayTable.class
				.getResource("/project/icon/yellow-bird-32.png")));

		final JButton btnADD = new JButton("ADD");
		btnADD.setToolTipText("Add Your Task Here");
		btnADD.setIcon(new ImageIcon(DisplayTable.class
				.getResource("/project/icon/Add-icon.png")));

		btnADD.addActionListener(new ActionListener() {
			String task;
			String Loc;
			String start;
			String end;
			String tanggal;
			String tanggale;
			Date now = new Date();
			Stacked st1=new Stacked();

			public void actionPerformed(ActionEvent arg0) {
				task = NameTask.getText();
				Loc = Location.getText();
				start = StartTime.getValue().toString() + ":"
						+ StartTime1.getValue().toString() + ":"
						+ StartTime2.getValue().toString();
				end = EndTime.getValue().toString() + ":"
						+ EndTime1.getValue().toString() + ":"
						+ EndTime2.getValue().toString();
				tanggal = format.format(StartDate.getDate());
				tanggale = format.format(EndDate.getDate());
				if (nama != null) {
					if (DeadLine.isSelected()) {
						if (EndDate.getDate().after(date)
								|| EndDate.getDate().equals(date)) {
							// panggil fungsi untuk simpan kedalam stack
							InsertStack("DeadLine");
							st.push(st1);

							data.InsertDataDeadLine(nama, task, Loc, tanggale,
									end, "DeadLine");
							JOptionPane.showMessageDialog(panelInsert,
									"Done!!!!");
						}

					} else if (Float.isSelected()) {
						// panggil fungsi untuk simpan kedalam stack
						InsertStack("Float");
						st.push(st1);

						data.InsertDataFloat(nama, task, Loc, "Float");
						JOptionPane.showMessageDialog(panelInsert, "Done!!!!");
					} else if (Time.isSelected()) {

						if (StartDate.getDate().after(EndDate.getDate())
								|| StartDate.getDate().before(now)
								|| task.equals("") || Loc.equals("")) {
							JOptionPane.showMessageDialog(null,
									"Please cek your input again");

						} else if (StartDate.getDate()
								.before(EndDate.getDate())
								&& !StartDate.getDate().before(date)
								&& !EndDate.getDate().before(date)
								&& !task.equals("")
								&& !Loc.equals("")
								&& nama != null
								|| StartDate.getDate().equals(date)
								|| EndDate.getDate().equals(date)) {
							InsertStack("TIME");
							st.push(st1);

							data.InsertDataTime(nama, task, Loc, tanggal,
									tanggale, start, end, "Time");
							JOptionPane.showMessageDialog(panelInsert,
									"Done!!!!");
						}

					} else {
						JOptionPane.showMessageDialog(null,
								"Please chose the Type");
					}

					tableDisplay.setModel(DbUtils.resultSetToTableModel(data
							.Display(nama)));

				}

				else {
					JOptionPane.showMessageDialog(null,
							"Sorry Please check your ID");
				}

			}

			void InsertStack(String jenis) {
				st1.setEvent("INSERT");
				st1.setDeskripsi(task);
				st1.setTempat(Loc);
				st1.setTanggal(tanggal);
				st1.setTanggals(tanggale);
				st1.setStart(start);
				st1.setEnd(end);
				st1.setJenis(jenis);
			}

		});
		btnADD.setBounds(27, 564, 99, 38);
		panelInsert.add(btnADD);
		btnADD.setMargin(new Insets(0, 0, 0, 0));
		btnADD.setBorderPainted(false);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(10, 11, 60, 46);
		panelInsert.add(panel_1);

		final JButton btnBack = new JButton("Back");
		btnBack.setBounds(0, 647, 98, 26);
		contentPane.add(btnBack);

		final JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			private int count = 0;

			public void actionPerformed(ActionEvent arg0) {
				if (panelInsert.isVisible() && count == 1) {
					count = 0;
					btnNext.setToolTipText("Insert");
					panelInsert.setVisible(false);
					PanelSideOne.setVisible(true);

				} else if (PanelSideOne.isVisible() && count == 0) {
					count = 1;
					btnNext.setToolTipText("Display");
					PanelSideOne.setVisible(false);
					panelInsert.setVisible(true);
				}
			}
		});
		btnNext.setBounds(654, 649, 89, 23);
		contentPane.add(btnNext);

		t = new JLabel("");
		t.setBounds(274, 644, 201, 29);
		contentPane.add(t);
		t.setFont(new Font("Tahoma", Font.PLAIN, 22));
		t.setHorizontalAlignment(SwingConstants.CENTER);
		t.setForeground(Color.GRAY);
		t.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, null,
				null, Color.DARK_GRAY));

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 22, 749, 622);
		contentPane.add(panel_4);

		JLabel lblGoogleCalendar = new JLabel("GOOGLE CALENDAR");
		lblGoogleCalendar.setFont(new Font("Segoe Marker", Font.PLAIN, 17));
		lblGoogleCalendar.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblGoogleCalendar);

		btnBack.addActionListener(new ActionListener() {
			private int count = 1;

			public void actionPerformed(ActionEvent arg0) {
				if (panelInsert.isVisible() && count == 1) {
					count = 0;
					btnBack.setToolTipText("Insert");
					panelInsert.setVisible(false);
					PanelSideOne.setVisible(true);

				} else if (PanelSideOne.isVisible() && count == 0) {
					count = 1;
					btnBack.setToolTipText("Display");
					PanelSideOne.setVisible(false);
					panelInsert.setVisible(true);
				}
			}
		});
		Timer time = new Timer(1000, new Clock());

		time.start();
	}

	public ImageIcon getCheck() {
		check = new ImageIcon("icon/ok-icon.png");
		return check;
	}

	/*
	 * public void setUp() {
	 * 
	 * HttpTransport httpTransport = new NetHttpTransport(); JsonFactory
	 * jsonFactory = new JsonFactory(); String clientId =
	 * "910706557383-ld6tpuhdlcdr5ghvsqg7rejhcmqubssr.apps.googleusercontent.com"
	 * ; String clientScreet = "zfC-BPvt21hRxlRoAIx5JrSF";
	 * 
	 * String redirectUrl = "urn:ietf:wg:oauth:2.0:oob"; 
	 * String scope ="https://www.googleapis.com/auth/calendar";
	 * 
	 * String authorizationUrl = new GoogleAuthorizationRequestUrl(clientId,
	 * redirectUrl, scope).toString(); BufferedReader in = new
	 * BufferedReader(new InputStreamReader(System.in));
	 * System.out.println("What is the authorization code?"); String code =
	 * null; try { code = in.readLine(); } catch (IOException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * System.out.println(authorizationUrl);
	 * 
	 * AccessTokenResponse response = new GoogleAuthorizationCodeGrant(
	 * httpTransport, jsonFactory, clientId, clientScreet, code,
	 * redirectUrl).execute(); }
	 */

	class Clock implements ActionListener {

		private int hour;
		private int minute;
		private int second;
		private int tanggal;
		private String bulan;

		@Override
		public void actionPerformed(ActionEvent e) {
			Calendar cal = Calendar.getInstance();
		
			hour = cal.get(Calendar.HOUR_OF_DAY);
			minute = cal.get(Calendar.MINUTE);
			second = cal.get(Calendar.SECOND);
			tanggal= cal.get(Calendar.DAY_OF_MONTH);
	
			t.setText(getHour() + ":" + getMinute() + ":" + getSecond());
			Tangg.setText(""+tanggal);
			
			

		}

		public int getHour() {
			return hour;
		}

		public int getMinute() {
			return minute;
		}

		public int getSecond() {
			return second;
		}
	}

	class PopUp extends JPopupMenu {
		JPopupMenu menu = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("Delete");

		public PopUp() {

			JMenuItem menuItem = new JMenuItem("Delete");
			add(menuItem);
			menuItem = new JMenuItem("Close");
			add(menuItem);

		}

	}
}
