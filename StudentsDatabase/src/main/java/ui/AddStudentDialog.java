package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddStudentDialog extends JDialog{
	private JTextField nameField;
    private JTextField surnameField;
    private JTextField albumField;
    private boolean confirmed = false;
    
    AddStudentDialog(JFrame parent) {
        super(parent, "Dodaj studenta", true);
        this.setSize(300, 200);
        this.setLocationRelativeTo(parent);
        this.setLayout(new BorderLayout(10, 10));

        // Pola formularza
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Imię:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Nazwisko:"));
        surnameField = new JTextField();
        formPanel.add(surnameField);

        formPanel.add(new JLabel("Nr albumu:"));
        albumField = new JTextField();
        formPanel.add(albumField);

        this.add(formPanel, BorderLayout.CENTER);

        // Przyciski
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton cancelBtn = new JButton("Anuluj");
        cancelBtn.setFocusable(false);
        cancelBtn.addActionListener(e -> dispose());

        JButton confirmBtn = new JButton("Dodaj");
        confirmBtn.setFocusable(false);
        confirmBtn.addActionListener(e -> {
            confirmed = true;
            dispose();
        });

        buttonsPanel.add(cancelBtn);
        buttonsPanel.add(confirmBtn);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }
    public boolean isConfirmed() { 
    	return confirmed; 
    	}
    
	public JTextField getNameField() {
		return nameField;
	}
	public JTextField getSurnameField() {
		return surnameField;
	}
	public JTextField getAlbumField() {
		return albumField;
	}
    
    
}
