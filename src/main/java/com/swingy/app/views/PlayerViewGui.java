/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.views;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.swingy.app.controllers.PlayerController;
import com.swingy.app.models.PlayerModel;

public class PlayerViewGui extends PlayerView implements Display {

	private PlayerController playerController;
	private JFrame 	frame;
	private JPanel 	pnl = new JPanel();
	private int 	returnNum;


	public PlayerViewGui() {
		super();
		returnNum = 3;
		frame = new JFrame("Simple Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void	createPlayer(PlayerModel playerModel) {
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
					playerController.newPlayer();
					break ;
				case "Select Player":
					break ;
				case "Exit":
					break ;
			}
			frame.setVisible(false);
			frame.dispose();
		}
	}

	public void refresh() {}
}
