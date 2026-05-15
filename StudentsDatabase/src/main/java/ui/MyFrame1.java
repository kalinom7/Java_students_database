package ui;

import java.awt.Color;

import javax.swing.JDialog;

public class MyFrame1 extends JDialog{
	MyFrame1(MyFrame p){
		super(p, true);
		this.setTitle("New Card");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setSize(800, 600);
        this.getContentPane().setBackground(new Color(200,200,200));
        this.setVisible(true);
	}
}
