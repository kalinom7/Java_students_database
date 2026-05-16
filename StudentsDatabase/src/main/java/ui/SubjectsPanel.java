package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class SubjectsPanel extends JPanel {
	SubjectsPanel() {
		this.setLayout(new BorderLayout(10, 10));
		this.setBackground(new Color(255, 246, 182));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

		// Top label
		JLabel groupsLabel = new JLabel("Subjects:");
		this.add(groupsLabel, BorderLayout.NORTH);

		// Tree of subjects
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Subjects");

		DefaultMutableTreeNode subject1 = new DefaultMutableTreeNode("Java:");
		subject1.add(new DefaultMutableTreeNode("kryterium , max points"));

		DefaultMutableTreeNode subject2 = new DefaultMutableTreeNode("Csharp:");
		subject2.add(new DefaultMutableTreeNode("kryterium , max points"));

		root.add(subject1);
		root.add(subject2);

		JTree subjectsTree = new JTree(root);
		subjectsTree.setRootVisible(false);
		subjectsTree.setBackground(new Color(255, 246, 182));

		for (int i = 0; i < subjectsTree.getRowCount(); i++) {
			subjectsTree.expandRow(i);
		}

		JScrollPane subjectsScrollPane = new JScrollPane(subjectsTree);
		subjectsScrollPane.setBorder(BorderFactory.createEmptyBorder());
		subjectsScrollPane.setBackground(new Color(255, 246, 182));
		subjectsScrollPane.getViewport().setBackground(new Color(255, 246, 182));
		this.add(subjectsScrollPane, BorderLayout.CENTER);

		// Bottom buttons panel
		JPanel groupsBottomPanel = new JPanel();
		groupsBottomPanel.setBackground(new Color(255, 246, 182));
		groupsBottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// Remove button
		JButton removeGroupBtn = new JButton("remove");
		removeGroupBtn.setBackground(new Color(150, 230, 200));
		removeGroupBtn.setFocusable(false);
		removeGroupBtn.setMargin(new Insets(10, 10, 10, 10));

		// Add button
		JButton addGroupBtn = new JButton("add");
		addGroupBtn.setBackground(new Color(150, 230, 200));
		addGroupBtn.setFocusable(false);
		addGroupBtn.setMargin(new Insets(10, 10, 10, 10));

		// Edit button
		JButton editGroupBtn = new JButton("Edit");
		editGroupBtn.setBackground(new Color(150, 230, 200));
		editGroupBtn.setFocusable(false);
		editGroupBtn.setMargin(new Insets(10, 10, 10, 10));

		groupsBottomPanel.add(removeGroupBtn);
		groupsBottomPanel.add(addGroupBtn);
		groupsBottomPanel.add(editGroupBtn);
		this.add(groupsBottomPanel, BorderLayout.SOUTH);
	}
}
