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

public class GroupsPanel extends JPanel{
	GroupsPanel() {
		this.setLayout(new BorderLayout(10, 10));
		this.setBackground(new Color(255, 246, 182));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

		// Top label
		JLabel groupsLabel = new JLabel("Groups:");
		this.add(groupsLabel, BorderLayout.NORTH);

		// Tree of groups
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Groups");

		DefaultMutableTreeNode group1 = new DefaultMutableTreeNode("TC1; groupCode:");
		group1.add(new DefaultMutableTreeNode("description: xxxxxxxxxxxxx"));
		group1.add(new DefaultMutableTreeNode("ID Jan Kowalski"));
		group1.add(new DefaultMutableTreeNode("ID Adam Kępczyk"));
		group1.add(new DefaultMutableTreeNode("ID Mada Kępczyk"));

		DefaultMutableTreeNode group2 = new DefaultMutableTreeNode("AI; groupCode:");
		group2.add(new DefaultMutableTreeNode("description: xxxxxxxxxxxxxxxxxx"));
		group2.add(new DefaultMutableTreeNode("ID Jan Kowalski"));
		group2.add(new DefaultMutableTreeNode("ID Mada Kępczyk"));
		group2.add(new DefaultMutableTreeNode("ID Adam Kępczyk"));

		root.add(group1);
		root.add(group2);

		JTree groupsTree = new JTree(root);
		groupsTree.setRootVisible(false);
		groupsTree.setBackground(new Color(255, 246, 182));

		// Rozwiń wszystkie węzły
		for (int i = 0; i < groupsTree.getRowCount(); i++) {
		    groupsTree.expandRow(i);
		}

		JScrollPane groupsScrollPane = new JScrollPane(groupsTree);
		groupsScrollPane.setBorder(BorderFactory.createEmptyBorder());
		groupsScrollPane.setBackground(new Color(255, 246, 182));
		this.add(groupsScrollPane, BorderLayout.CENTER);

		// Groups bottom buttons panel
		JPanel groupsBottomPanel = new JPanel();
		groupsBottomPanel.setBackground(new Color(255, 246, 182));
		groupsBottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// Groups remove button
		JButton removeGroupBtn = new JButton("remove");
		removeGroupBtn.setBackground(new Color(150, 230, 200));
		removeGroupBtn.setFocusable(false);
		removeGroupBtn.setMargin(new Insets(10, 10, 10, 10));

		// Groups add button
		JButton addGroupBtn = new JButton("add");
		addGroupBtn.setBackground(new Color(150, 230, 200));
		addGroupBtn.setFocusable(false);
		addGroupBtn.setMargin(new Insets(10, 10, 10, 10));

		// Groups edit button
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
