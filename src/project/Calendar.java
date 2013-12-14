 package project;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class Calendar extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calendar frame = new Calendar();
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
	public Calendar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JCalendar calendar = new JCalendar();
		calendar.getDayChooser().getDayPanel().addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				System.out.println("asda");
			}
			public void ancestorMoved(AncestorEvent arg0) {
				System.out.println("asfsdfs");
			}
			public void ancestorRemoved(AncestorEvent arg0) {
				System.out.println("asdasdsfs");
			}
		});
		calendar.getDayChooser().getDayPanel().addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent arg0) {
				
			}
		});
	
		
	
		calendar.getDayChooser().setWeekOfYearVisible(false);
		calendar.getDayChooser().setMaxDayCharacters(10);

		contentPane.add(calendar, BorderLayout.CENTER);
	}

}
