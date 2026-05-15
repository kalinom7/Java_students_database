package main;

import javax.swing.SwingUtilities;

import domain.student.InMemoStudentRepository;
import domain.student.StudentRepository;
import domain.student.StudentService;
import ui.MainFrame;


public class App {
	
	public static void main(String[] args) {
        StudentRepository studentRepository = new InMemoStudentRepository();
        StudentService studentService = new StudentService(studentRepository);
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame();
            }
        });
    }
}
