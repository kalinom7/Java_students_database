package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import domain.student.Student;
import domain.student.StudentService;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;

public class StudentsPanel extends JPanel{
	
	private StudentService studentService;
	private ArrayList<Student> students;
	private JList<String> studentsList;
	
	StudentsPanel(StudentService studentService) {
		this.studentService = studentService;
	    students = new ArrayList<>();

        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(new Color(255, 246, 182));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        // Top label
        JLabel studentsLabel = new JLabel("Students:");
        this.add(studentsLabel, BorderLayout.NORTH);

        // List of students
        studentsList = new JList<>();
        studentsList.setBackground(new Color(255, 246, 182));
        JScrollPane scrollPane = new JScrollPane(studentsList);
        //scrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.add(scrollPane, BorderLayout.CENTER);

        // Students bottom buttons panel
        JPanel studentsBottomPanel = new JPanel();
        studentsBottomPanel.setBackground(new Color(255, 246, 182));
        studentsBottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Students remove button
        JButton removeBtn = new JButton("remove");
        
        removeBtn.setBackground(new Color(150, 230, 200));
        removeBtn.setFocusable(false);
        removeBtn.setMargin(new Insets(10, 10, 10, 10));
        
        // Students add button
        JButton addBtn = new JButton("add");
        
        addBtn.setBackground(new Color(150, 230, 200));
        addBtn.setFocusable(false);
        addBtn.setMargin(new Insets(10, 10, 10, 10));

        // Students edit button
        JButton editBtn = new JButton("Edit");
        
        editBtn.setBackground(new Color(150, 230, 200));
        editBtn.setFocusable(false);
        editBtn.setMargin(new Insets(10, 10, 10, 10));

        studentsBottomPanel.add(removeBtn);
        studentsBottomPanel.add(editBtn);
        studentsBottomPanel.add(addBtn);
        this.add(studentsBottomPanel, BorderLayout.SOUTH);
        
        // WORK
        
        editBtn.setEnabled(false);
        removeBtn.setEnabled(false);
        
        addBtn.addActionListener(e -> {
        	JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
        	AddStudentDialog dialog = new AddStudentDialog(parent);
            if (dialog.isConfirmed()) {
                Student student = new Student(
                    dialog.getNameField().getText(),
                    dialog.getSurnameField().getText(),
                    dialog.getAlbumField().getText()
                );
                students.add(student);
                createStudentList();
            }
        });
        
        editBtn.addActionListener(e -> {
            int index = studentsList.getSelectedIndex();
            Student student = students.get(index);
            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
            EditStudentDialog dialog = new EditStudentDialog(
                parent,
                student.getName(),
                student.getSurname(),
                student.getAlbumNumber()
            );
            if (dialog.isConfirmed()) {
                student.setName(dialog.getNameField().getText());
                student.setSurname(dialog.getSurnameField().getText());
                student.setAlbumNumber(dialog.getAlbumField().getText());
                students.set(index, student);
                createStudentList();
                studentsList.setSelectedIndex(index);
            }
        });
        
        removeBtn.addActionListener(e -> {
            int index = studentsList.getSelectedIndex();
            Student student = students.get(index);
            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
            RemoveStudentDialog dialog = new RemoveStudentDialog(
                parent,
                student.getName(),
                student.getSurname(),
                student.getAlbumNumber()
            );
            if (dialog.isConfirmed()) {
                studentService.remove(student.getId());
                students.remove(index);
                createStudentList();
                editBtn.setEnabled(false);
                removeBtn.setEnabled(false);
                if (!students.isEmpty()) {
                    studentsList.setSelectedIndex(students.size() - 1);
                }
            }
        });
        
        studentsList.addListSelectionListener(e -> {
            if (!studentsList.isSelectionEmpty()) {
                editBtn.setEnabled(true);
                removeBtn.setEnabled(true);
            }else {
                editBtn.setEnabled(false);
                removeBtn.setEnabled(false);
            }
        });
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                studentsList.clearSelection();
            }
        });
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                studentsList.clearSelection();
            }
        });
    }
	
	private String studentToString(Student student) {

	    StringBuilder sb = new StringBuilder();

	    sb.append(student.getAlbumNumber());
	    sb.append(" ");
	    sb.append(student.getName());
	    sb.append(" ");
	    sb.append(student.getSurname());

	    return sb.toString();
	}
	
	private void createStudentList() {

	    String[] data = new String[students.size()];
	    for (int i = 0; i < students.size(); i++) {
	        Student student = students.get(i);
	        data[i] = studentToString(student);
	    }
	    studentsList.setListData(data);
	}
	
}
