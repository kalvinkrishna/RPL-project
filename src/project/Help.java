package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JList;
import java.awt.Label;
import java.awt.Font;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyEvent;

public class Help extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Help frame = new Help();
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
	public Help() {
		setTitle("Help");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		panel_1.setBounds(0, 37, 173, 403);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		panel_1.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel Help = new JPanel();
		tabbedPane.addTab("Help", null, Help, null);
		Help.setLayout(new BorderLayout(0, 0));
		
		JTree tree = new JTree();
		
		Help.add(tree, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Contact Support", null, panel_4, null);
		panel_4.setLayout(null);
		
		Label label = new Label("\r\nRPL UKDW ");
		label.setAlignment(Label.CENTER);
		label.setBounds(10, 184, 144, 78);
		panel_4.add(label);
		

		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		panel_2.setBounds(175, 37, 329, 403);
		panel.add(panel_2);
		
		Label label_1 = new Label("THE LAST PLACE");
		panel_2.add(label_1);
		label_1.setFont(new Font("David", Font.PLAIN, 19));
		label_1.setAlignment(Label.CENTER);
	}
}
