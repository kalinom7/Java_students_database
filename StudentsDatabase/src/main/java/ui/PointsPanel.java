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
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class PointsPanel extends JPanel {
	PointsPanel() {
		this.setLayout(new BorderLayout(10, 10));
		this.setBackground(new Color(255, 246, 182));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

		// Top search panel
		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(new Color(200, 220, 255));
		searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		searchPanel.setLayout(new BorderLayout());

		// Fields
		JTextField id = new JTextField(8);
		JTextField name = new JTextField(8);
		JTextField surname = new JTextField(8);
		JTextField group = new JTextField(8);

		JPanel searchFields = new JPanel();
		searchFields.setBackground(new Color(200, 220, 255));
		searchFields.setLayout(new FlowLayout(FlowLayout.LEFT));
		searchFields.add(new JLabel("ID"));
		searchFields.add(id);
		searchFields.add(new JLabel("Imie"));
		searchFields.add(name);
		searchFields.add(new JLabel("Nazwisko"));
		searchFields.add(surname);
		searchFields.add(new JLabel("Grupa"));
		searchFields.add(group);

		searchPanel.add(searchFields, BorderLayout.CENTER);
		
		// Search button
		JButton searchBtn = new JButton("szukaj");
		searchBtn.setBackground(new Color(150, 230, 200));
		searchBtn.setFocusable(false);
		searchPanel.add(searchBtn, BorderLayout.EAST);

		this.add(searchPanel, BorderLayout.NORTH);

		// Tree
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Studenci");

		DefaultMutableTreeNode student1 = new DefaultMutableTreeNode("Jan Kowlaski:");
		student1.add(new DefaultMutableTreeNode("ID"));
		student1.add(new DefaultMutableTreeNode("Grupa"));
		DefaultMutableTreeNode java1 = new DefaultMutableTreeNode("Java: kol1 x, kol2");
		DefaultMutableTreeNode cs1 = new DefaultMutableTreeNode("C$: obecność 0");
		student1.add(java1);
		student1.add(cs1);

		DefaultMutableTreeNode student2 = new DefaultMutableTreeNode("Mikołaj Krakowiak:");
		DefaultMutableTreeNode java2 = new DefaultMutableTreeNode("Java: kol1 0");
		DefaultMutableTreeNode cs2 = new DefaultMutableTreeNode("C$: obecność 10");
		student2.add(java2);
		student2.add(cs2);

		root.add(student1);
		root.add(student2);

		JTree pointsTree = new JTree(root);
		pointsTree.setRootVisible(false);
		pointsTree.setBackground(new Color(255, 246, 182));

		for (int i = 0; i < pointsTree.getRowCount(); i++) {
			pointsTree.expandRow(i);
		}

		JScrollPane pointsScrollPane = new JScrollPane(pointsTree);
		pointsScrollPane.setBorder(BorderFactory.createEmptyBorder());
		pointsScrollPane.setBackground(new Color(255, 246, 182));
		pointsScrollPane.getViewport().setBackground(new Color(255, 246, 182));
		this.add(pointsScrollPane, BorderLayout.CENTER);

		// Bottom buttons panel
		JPanel pointsBottomPanel = new JPanel();
		pointsBottomPanel.setBackground(new Color(255, 246, 182));
		pointsBottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// Edit button
		JButton editBtn = new JButton("edit");
		editBtn.setBackground(new Color(150, 230, 200));
		editBtn.setFocusable(false);
		editBtn.setMargin(new Insets(10, 10, 10, 10));

		// Add button
		JButton addBtn = new JButton("add");
		addBtn.setBackground(new Color(150, 230, 200));
		addBtn.setFocusable(false);
		addBtn.setMargin(new Insets(10, 20, 10, 20));

		pointsBottomPanel.add(editBtn);
		pointsBottomPanel.add(addBtn);
		this.add(pointsBottomPanel, BorderLayout.SOUTH);
	}
};