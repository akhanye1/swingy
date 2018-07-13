/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.views;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.swingy.app.controllers.PlayerController;
import com.swingy.app.models.PlayerModel;
import com.swingy.app.models.ValidationErrorModel;
import java.util.List;
import java.util.ArrayList;

public class PlayerViewGui extends PlayerView implements Display {

	private PlayerController playerController;
	private PlayerModel playerModel;
	private JFrame 	frame;
	private JPanel 	pnl = new JPanel();
	private int 	returnNum;
	private JTextField	nameTxt;
	private JComboBox	classType;

	public PlayerViewGui() {
		super();
		returnNum = 3;
		frame = new JFrame("Simple Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*public Jpanel	namePanel() {
		JLabel		nameLabel;
		Jlabel		classLabel;
		JTextBox	nameTxt;
		JPanel		panel;

		nameLabel = new JLabel("Name");
		classLabel = new JLabel("Class");
		nameTxt = new JTextBox(20);
		nameLabel.setBounds(50, 50, 200, 30);
		nameTxt.setBounds(200, 50, 200, 30);
		classLabel.setBounds(50, 90, 200, 30);
		panel.setSize(400, 400);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(nameLabel);
		panel.add(classLabel);
		return(panel);
	}*/

	public void	createPlayer(PlayerModel playerModel) {
		frame = new JFrame("New Player");
		JLabel		nameLabel;
		JLabel		classLabel;
		JButton		saveBtn;
		String		options[] = {"Knight", "Elf", "Viking"};
		
		this.playerModel = playerModel;
		nameLabel = new JLabel("Name");
		classLabel = new JLabel("Class");
		nameTxt = new JTextField(20);
		nameLabel = new JLabel("Name");
		classLabel = new JLabel("Class");
		nameTxt = new JTextField(20);
		saveBtn = new JButton("SAVE");
		classType = new JComboBox(options);
		classType.setSelectedIndex(0);
		nameLabel.setBounds(10, 50, 200, 30);
		nameTxt.setBounds(200, 50, 200, 30);
		classLabel.setBounds(10, 90, 200, 30);
		saveBtn.setBounds(50, 150, 100, 30);
		classType.setBounds(200, 90, 200, 30);
		frame.add(nameLabel);
		frame.add(nameTxt);
		frame.add(classLabel);
		frame.add(saveBtn);
		frame.add(classType);
		saveBtn.addActionListener(new btnAction());
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500, 300));
		frame.setLayout(null);
		frame.pack();
		frame.setVisible(true);
	}

	public int	choosePlayer(PlayerController controller) {
		this.playerController = controller;
		pnl.setLayout(new BoxLayout(pnl, BoxLayout.PAGE_AXIS));
		JButton btnNew = new JButton("Create Player");
		JButton btnSelect = new JButton("Select Player");
		JButton btnExit = new JButton("Exit");
		btnNew.addActionListener(new btnEvent());
		btnSelect.addActionListener(new btnEvent());
		btnExit.addActionListener(new btnEvent());
		btnNew.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSelect.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnl.add(Box.createRigidArea(new Dimension(10, 10)));
		pnl.add(btnNew);
		pnl.add(Box.createRigidArea(new Dimension(10, 10)));
		pnl.add(btnSelect);
		pnl.add(Box.createRigidArea(new Dimension(10, 10)));
		pnl.add(btnExit);
		frame.add(pnl);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		return (returnNum);
	}

	private class btnEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
				case "Create Player":
					frame.setVisible(false);
					frame.dispose();
					playerController.newPlayer();
					break ;
				case "Select Player":
					break ;
				case "Exit":
					frame.setVisible(false);
					frame.dispose();
					break ;
			}
		}
	}

	private void savePlayerFields() {
		List<ValidationErrorModel> errors;

		System.out.println("Btn Pressed");
		playerModel.setName(nameTxt.getText());
		playerModel.setPClass(classType.getSelectedItem().toString());
		errors = new ArrayList<ValidationErrorModel>();
		if (!this.playerController.validatePlayer(errors)) {
			System.out.println("Errors Found");
			return ;
		}
		this.playerController.savePlayer();
	}

	public class btnAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			savePlayerFields();
		}
	}

	public void refresh() {}

	public void	selectPlayer(List<PlayerModel> players) {

	}
}
