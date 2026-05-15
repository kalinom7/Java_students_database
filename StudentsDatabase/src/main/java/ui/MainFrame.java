package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setTitle("Students managment system\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 746, 449);
		
		JPanel navigationButtonsPanel = new JPanel();
		getContentPane().add(navigationButtonsPanel, BorderLayout.NORTH);
		navigationButtonsPanel.setLayout(new BoxLayout(navigationButtonsPanel, BoxLayout.X_AXIS));
		
		
		JButton homeFrameButton = new JButton("Home");
		navigationButtonsPanel.add(homeFrameButton);
		
		JButton studentsFrameButton = new JButton("Students");
		navigationButtonsPanel.add(studentsFrameButton);
		
		JButton groupsFrame = new JButton("Groups");
		navigationButtonsPanel.add(groupsFrame);
		
		JButton subjectsFrameButton = new JButton("Subjects");
		navigationButtonsPanel.add(subjectsFrameButton);
		
		JButton pointsFrame = new JButton("Points");
		navigationButtonsPanel.add(pointsFrame);
		
		JPanel centerContentPanel = new JPanel();
		getContentPane().add(centerContentPanel, BorderLayout.CENTER);
		centerContentPanel.setLayout(new CardLayout(0, 0));
		
		JPanel homePanel = new JPanel();
		homePanel.setBackground(new Color(255, 0, 128));
		centerContentPanel.add(homePanel, "name_803779487436000");
		
		JPanel StudentsPanel = new JPanel();
		centerContentPanel.add(StudentsPanel, "name_803784959660700");
		
		JPanel groupsPanel = new JPanel();
		centerContentPanel.add(groupsPanel, "name_803802618644500");
		
		JPanel subjectsPanel = new JPanel();
		centerContentPanel.add(subjectsPanel, "name_803805353930000");
		
		JPanel pointsPanel = new JPanel();
		centerContentPanel.add(pointsPanel, "name_803808546027900");
		
		
		homeFrameButton.addActionListener( e -> ((CardLayout) centerContentPanel.getLayout()).show(centerContentPanel, "home"));

	}

}
