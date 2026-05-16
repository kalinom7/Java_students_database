package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.Color;
import domain.student.StudentRepository;
import domain.student.InMemoStudentRepository;
import domain.student.StudentService;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainFrame() {
		StudentRepository studentRepository = new InMemoStudentRepository();
		StudentService studentService = new StudentService(studentRepository);
		
		setTitle("Students managment system\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 746, 449);
		
		
		JPanel navigationButtonsPanel = new JPanel();
		getContentPane().add(navigationButtonsPanel, BorderLayout.NORTH);
		navigationButtonsPanel.setBackground(new Color(222,218,255));
		navigationButtonsPanel.setLayout(new BoxLayout(navigationButtonsPanel, BoxLayout.X_AXIS));
		navigationButtonsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JButton homeFrameButton = new JButton("Home");
		homeFrameButton.setFocusable(false);
		homeFrameButton.setBackground(new Color(248, 211, 175));
		navigationButtonsPanel.add(homeFrameButton);
		
		JButton studentsFrameButton = new JButton("Students");
		studentsFrameButton.setFocusable(false);
		studentsFrameButton.setBackground(new Color(248, 211, 175));
		navigationButtonsPanel.add(studentsFrameButton);
		
		JButton groupsFrameButton = new JButton("Groups");
		groupsFrameButton.setFocusable(false);
		groupsFrameButton.setBackground(new Color(248, 211, 175));
		navigationButtonsPanel.add(groupsFrameButton);
		
		JButton subjectsFrameButton = new JButton("Subjects");
		subjectsFrameButton.setFocusable(false);
		subjectsFrameButton.setBackground(new Color(248, 211, 175));
		navigationButtonsPanel.add(subjectsFrameButton);
		
		JButton pointsFrameButton = new JButton("Points");
		pointsFrameButton.setFocusable(false);
		pointsFrameButton.setBackground(new Color(248, 211, 175));
		navigationButtonsPanel.add(pointsFrameButton);
		
		JPanel centerContentPanel = new JPanel();
		getContentPane().add(centerContentPanel, BorderLayout.CENTER);
		centerContentPanel.setLayout(new CardLayout(0, 0));
		
		//////////////////////////////////////////////////////
		//////////////////////////////////////////////////////
		// HOME PANEL
		
		HomePanel homePanel = new HomePanel();
		centerContentPanel.add(homePanel, "homePanel");
		
		//////////////////////////////////////////////////////
		//////////////////////////////////////////////////////
		// STUDENTS PANEL
		
		StudentsPanel studentsPanel = new StudentsPanel(studentService);
		centerContentPanel.add(studentsPanel, "studentsPanel");
		
		//////////////////////////////////////////////////////
		//////////////////////////////////////////////////////
		// GROUPS PANEL

		GroupsPanel groupsPanel = new GroupsPanel();
		centerContentPanel.add(groupsPanel, "groupsPanel");
		
		//////////////////////////////////////////////////////
		//////////////////////////////////////////////////////
		// SUBJECTS PANEL
		SubjectsPanel subjectsPanel = new SubjectsPanel();
		centerContentPanel.add(subjectsPanel, "subjectsPanel");
		
		
		//////////////////////////////////////////////////////
		//////////////////////////////////////////////////////
		// POINTS PANEL
		PointsPanel pointsPanel = new PointsPanel();
		centerContentPanel.add(pointsPanel, "pointsPanel");
		
		
		
		
		homeFrameButton.addActionListener( e -> ((CardLayout) centerContentPanel.getLayout()).show(centerContentPanel, "homePanel"));
		studentsFrameButton.addActionListener( e -> ((CardLayout) centerContentPanel.getLayout()).show(centerContentPanel, "studentsPanel"));
		groupsFrameButton.addActionListener( e -> ((CardLayout) centerContentPanel.getLayout()).show(centerContentPanel, "groupsPanel"));
		subjectsFrameButton.addActionListener( e -> ((CardLayout) centerContentPanel.getLayout()).show(centerContentPanel, "subjectsPanel"));
		pointsFrameButton.addActionListener( e -> ((CardLayout) centerContentPanel.getLayout()).show(centerContentPanel, "pointsPanel"));
		setVisible(true);
		
		
		
	
	}

}
