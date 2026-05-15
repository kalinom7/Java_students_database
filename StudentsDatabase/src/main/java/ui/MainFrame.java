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
import javax.swing.JDesktopPane;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setVisible(true);
		setTitle("Students managment system\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 746, 449);
		
		JPanel navigationButtonsPanel = new JPanel();
		getContentPane().add(navigationButtonsPanel, BorderLayout.NORTH);
		navigationButtonsPanel.setLayout(new BoxLayout(navigationButtonsPanel, BoxLayout.X_AXIS));
		
		
		JButton homeFrameButton = new JButton("Home");
		homeFrameButton.setFocusable(false);
		navigationButtonsPanel.add(homeFrameButton);
		
		JButton studentsFrameButton = new JButton("Students");
		studentsFrameButton.setFocusable(false);
		
		navigationButtonsPanel.add(studentsFrameButton);
		
		JButton groupsFrame = new JButton("Groups");
		groupsFrame.setFocusable(false);
		navigationButtonsPanel.add(groupsFrame);
		
		JButton subjectsFrameButton = new JButton("Subjects");
		subjectsFrameButton.setFocusable(false);
		navigationButtonsPanel.add(subjectsFrameButton);
		
		JButton pointsFrame = new JButton("Points");
		pointsFrame.setFocusable(false);
		navigationButtonsPanel.add(pointsFrame);
		
		JPanel centerContentPanel = new JPanel();
		getContentPane().add(centerContentPanel, BorderLayout.CENTER);
		centerContentPanel.setLayout(new CardLayout(0, 0));
		
		JPanel homePanel = new JPanel();
		homePanel.setBackground(new Color(255, 0, 128));
		centerContentPanel.add(homePanel, "homePanel");
		
		JPanel studentsPanel = new JPanel();
		centerContentPanel.add(studentsPanel, "studentsPanel");
		
		JPanel groupsPanel = new JPanel();
		centerContentPanel.add(groupsPanel, "groupsPanel");
		
		JPanel subjectsPanel = new JPanel();
		centerContentPanel.add(subjectsPanel, "subjectsPanel");
		
		JPanel pointsPanel = new JPanel();
		centerContentPanel.add(pointsPanel, "pointsPanel");
		
		
		homeFrameButton.addActionListener( e -> ((CardLayout) centerContentPanel.getLayout()).show(centerContentPanel, "homePanel"));
		studentsFrameButton.addActionListener( e -> ((CardLayout) centerContentPanel.getLayout()).show(centerContentPanel, "studentsPanel"));

	}

}
