/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.views;

import	com.swingy.app.controllers.ArenaController;
import	com.swingy.app.models.PlayerModel;
import	java.awt.*;
import	javax.swing.*;
import	java.awt.event.*;
import	javax.swing.table.*;

public class ArenaViewGui extends ArenaView implements Display {
	private char			map[][];
	private PlayerModel		playerModel;
	private ArenaController	arenaController;
	private JFrame			frame;
	private JPanel			playerPanel;
	private JPanel			playPanel;
	private JPanel			mainPanel;

	private JLabel	getHeading(String str) {
		JLabel	lblHeading;

		lblHeading = new JLabel(str);
		lblHeading.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblHeading.setPreferredSize(new Dimension(200, 50));
		//lblHeading.setAlignmentX(Component.CENTER_ALIGNMENT);
		return (lblHeading);
	}

	private JLabel	getLabel(String lbl, String txt) {
		JLabel	lblGiven;

		lblGiven = new JLabel(lbl + " : " + txt);
		lblGiven.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblGiven.setPreferredSize(new Dimension(200, 40));
		return (lblGiven);
	}
	private JPanel	playerInfo() {
		JPanel		playerInfoPanel;

		playerInfoPanel = new JPanel();
		playerInfoPanel.setLayout(new BoxLayout(playerInfoPanel, BoxLayout.PAGE_AXIS));
		playerInfoPanel.setPreferredSize(new Dimension(200, 400));
		playerInfoPanel.setBackground(Color.white);
		playerInfoPanel.add(getHeading("HERO"));
		playerInfoPanel.add(getLabel("Name", this.playerModel.getName()));
		playerInfoPanel.add(getLabel("Player Class", this.playerModel.getPClass()));
		playerInfoPanel.add(getLabel("Experience", String.valueOf(this.playerModel.getExperience())));
		playerInfoPanel.add(getLabel("Level", String.valueOf(this.playerModel.getLevel())));
		playerInfoPanel.add(getLabel("Attack", String.valueOf(this.playerModel.getAttack())));
		playerInfoPanel.add(getLabel("Defence", String.valueOf(this.playerModel.getDefence())));
		return (playerInfoPanel);
	}

	private String	stringMap() {
		String	oneString;
		String	tempString;
		int 	height;

		height = map.length;
		oneString = "";
		tempString = "";
		for (int y = 0; y < height; y++) {
			tempString = String.valueOf(map[y]);
			oneString += tempString + "\n";
		}
		return (oneString);
	}

	private JButton	btnGiven(String btnString) {
		JButton	tempBtn;

		tempBtn = new JButton(btnString);
		tempBtn.addActionListener(new btnPressed());
		return (tempBtn);
	}

	private JPanel	movePanel() {
		JPanel	btnPanel;
		JPanel	flowPanel;

		flowPanel = new JPanel(new BorderLayout());
		btnPanel = new JPanel();
		btnPanel.setLayout(new BorderLayout());
		btnPanel.setPreferredSize(new Dimension(200, 150));
		btnPanel.add(btnGiven("NORTH"), BorderLayout.NORTH);
		btnPanel.add(btnGiven("WEST"), BorderLayout.WEST);
		btnPanel.add(btnGiven("EAST"), BorderLayout.EAST);
		btnPanel.add(btnGiven("SOUTH"), BorderLayout.SOUTH);
		flowPanel.add(btnPanel, BorderLayout.NORTH);
		return (flowPanel);
	}

	private class btnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("btn pressed");
			switch (e.getActionCommand()) {
				case "NORTH":
					arenaController.setSelection(1);
					break ;
				case "WEST":
					arenaController.setSelection(4);
					break ;
				case "EAST":
					arenaController.setSelection(2);
					break ;
				case "SOUTH":
					arenaController.setSelection(3);
					break ;
			}
		}
	}

	private void	viewMap() {
		JTextArea	mapTxt;

		mapTxt = new JTextArea(stringMap());
		mapTxt.setFont(new Font("monospaced", Font.PLAIN, 12));
		this.mainPanel.add(playerInfo(), BorderLayout.WEST);
		this.mainPanel.add(movePanel(), BorderLayout.EAST);
		this.mainPanel.add(mapTxt, BorderLayout.CENTER);	
	}

	private void	init() {
		this.frame = new JFrame("ARENA");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainPanel = new JPanel(new BorderLayout());
	}

	private void	setVisible() {
		this.frame.add(mainPanel);
		this.frame.setPreferredSize(new Dimension(800, 800));
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}

	public void		showMap(char[][] map, ArenaController arenaController, PlayerModel playerModel) {
		this.map = map;
		this.playerModel = playerModel;
		this.arenaController = arenaController;
		this.init();
		this.viewMap();
		this.setVisible();
	}

	public void	updateMap(char[][] map) {
		this.map = map;
		this.mainPanel.removeAll();
		this.viewMap();
		this.mainPanel.revalidate();
		this.mainPanel.repaint();
		/*this.viewMap();
		this.frame.revalidate();
		this.frame.repaint();*/
		System.out.println("Updating map");
	}

	public void	prepareFight(String prepareString) {
	}

	public void	updateFight(String updateString) {
	}

	public void	showMessage(String message) {
	}

	public void	makeChoice(PlayerModel enemy, ArenaController arenaController) {

	}

	public void refresh() {
	}
}
