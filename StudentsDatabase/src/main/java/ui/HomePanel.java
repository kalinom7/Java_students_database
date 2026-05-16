package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class HomePanel extends JPanel{
	HomePanel(){
		this.setLayout(new BorderLayout(10,10));
		this.setBackground(new Color(255, 246, 182));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		
		// TextBox
		JTextArea txtrWelcomeToStudents = new JTextArea();
		txtrWelcomeToStudents.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtrWelcomeToStudents.setText("Welcome to Students managment system,\n\nQuick guide how to use app:\nd\nd\nd\nd");
		txtrWelcomeToStudents.setEditable(false);
		txtrWelcomeToStudents.setBackground(new Color(100, 149, 237));
		this.add(txtrWelcomeToStudents, BorderLayout.CENTER);
		
		// Bottom buttons panel 
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(255, 246, 182));
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton importDataButton = new JButton("Zaimportuj dane z pliku");
		importDataButton.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File ("C:\\"));
			fileChooser.setFileFilter(new FileNameExtensionFilter("Pliki binarne (*.bin, *.dat)", "bin", "dat"));
			int response = fileChooser.showOpenDialog(HomePanel.this);
			if (response == JFileChooser.APPROVE_OPTION) {
		        File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
		        System.out.println(file);
		    }
		});
		importDataButton.setFocusable(false);
		importDataButton.setBackground(new Color(150, 230, 200));
		importDataButton.setMargin(new Insets(10, 10, 10, 10));
		
		bottomPanel.add(importDataButton);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}
}
