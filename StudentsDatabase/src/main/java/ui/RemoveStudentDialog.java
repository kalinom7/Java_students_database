package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RemoveStudentDialog extends JDialog {
	private boolean confirmed = false;

	RemoveStudentDialog(JFrame parent, String albumNumber, String name, String surname) {
		super(parent, "Remove student", true);
		this.setSize(400, 150);
		this.setLocationRelativeTo(parent);
		this.setLayout(new BorderLayout(10, 10));

		// Message
		JLabel messageLabel = new JLabel("Are you sure to remove: " + albumNumber + " " + name + " " + surname + "?");
		messageLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
		this.add(messageLabel, BorderLayout.CENTER);

		// Buttons
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setFocusable(false);
		cancelBtn.addActionListener(e -> dispose());

		JButton proceedBtn = new JButton("Remove");
		proceedBtn.setFocusable(false);
		proceedBtn.addActionListener(e -> {
			confirmed = true;
			dispose();
		});

		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.add(cancelBtn, BorderLayout.WEST);
		buttonsPanel.add(proceedBtn, BorderLayout.EAST);
		this.add(buttonsPanel, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	public boolean isConfirmed() {
		return confirmed;
	}
}
