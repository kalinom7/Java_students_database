package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class MyFrame extends JFrame{
	MyFrame(){
		
		this.setTitle("Students Data Base");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setSize(800, 600);
        this.getContentPane().setBackground(new Color(200,200,200));
        // this.setLayout(new BorderLayout(10, 0)); // margins for panels
        
        
        // Panel
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(130, 130, 130));
        //formPanel.setLayout(new GridLayout(1, 4));
        formPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10,5));
        
        JPanel formPanel1 = new JPanel();
        formPanel1.setBackground(new Color(180, 180, 180));
        
        JPanel formPanel2 = new JPanel();
        formPanel2.setBackground(new Color(250, 250, 250));
        formPanel2.setPreferredSize(new Dimension(800, 100));

        
        // Textbox
        JTextField textField = new JTextField();
        textField.setColumns(20);
        
        
        //Button
        JButton button = new JButton("Dodaj");//LanguageManager.get("add")
        button.addActionListener(e -> textField.setText(button.getText()));
        //button.setHorizontalTextPosition(JButton.CENTER);  do ikon 
        //button.setVerticalTextPosition(JButton.BOTTOM);
        
        
        JButton button1 = new JButton("Usuń");
        button1.addActionListener(e -> textField.setText(button1.getText()));
        
        JButton button2 = new JButton("Edytuj");
        button2.addActionListener(e -> textField.setText(button2.getText()));

        
        JButton button3 = new JButton("Nowe okno");//LanguageManager.get("add")
        button3.addActionListener(e -> new MyFrame1(this));
        
        
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel tab1 = new JPanel();
        tab1.setBackground(Color.RED);
        tab1.setLayout(new BorderLayout());
        
        JPanel tab1Buttons = new JPanel();
        tab1Buttons.add(new JLabel("Lista studentów"));
        tab1Buttons.add(new JButton("Dodaj studenta"));

        JPanel tab2 = new JPanel();
        tab2.setBackground(Color.GREEN);
        tab2.add(new JLabel("Lista grup"));
        tab2.add(new JButton("Dodaj grupę"));

        JPanel tab3 = new JPanel();
        tab3.setBackground(Color.BLUE);
        tab3.add(new JLabel("Lista przedmiotów"));
        tab3.add(new JButton("Dodaj przedmiot"));

        tabbedPane.addTab("Studenci", tab1);
        tabbedPane.addTab("Grupy", tab2);
        tabbedPane.addTab("Przedmioty", tab3);

        
        String[] studenci = {"Jan Kowalski", "Anna Nowak", "Piotr Wiśniewski"};

        JList<String> lista = new JList<>(studenci);
        JScrollPane scrollPane = new JScrollPane(lista);

        tab1.add(tab1Buttons, BorderLayout.NORTH);
        tab1.add(scrollPane, BorderLayout.CENTER);
        
        // Add
        formPanel.add(textField);
        formPanel.add(button);
        formPanel.add(button1);
        formPanel.add(button2);
        formPanel2.add(button3);
        
        this.add(formPanel, BorderLayout.NORTH);
        this.add(tabbedPane, BorderLayout.CENTER);
        this.add(formPanel2, BorderLayout.SOUTH);
        
        
        // Show form
        this.setVisible(true);


	}
}
