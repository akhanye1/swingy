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
	private JPanel			btnPanel;
	private JPanel			btnHolder;
	private PlayerModel		fightEnemy;
	private JTextArea		txtArea;

	private JLabel	getHeading(String str) {
		JLabel	lblHeading;

		lblHeading = new JLabel(str);
		lblHeading.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblHeading.setPreferredSize(new Dimension(200, 50));
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

	private	JPanel	setMoveBtn() {
		JPanel	btnTempPanel;

		btnTempPanel = new JPanel();
		btnTempPanel.setLayout(new BorderLayout());
		btnTempPanel.setPreferredSize(new Dimension(200, 150));
		btnTempPanel.add(btnGiven("NORTH"), BorderLayout.NORTH);
		btnTempPanel.add(btnGiven("WEST"), BorderLayout.WEST);
		btnTempPanel.add(btnGiven("EAST"), BorderLayout.EAST);
		btnTempPanel.add(btnGiven("SOUTH"), BorderLayout.SOUTH);
		return (btnTempPanel);
	}

	private JPanel	setChoiceBtn() {
		JPanel	btnTempPanel;

		btnTempPanel = new JPanel();
		btnTempPanel.setLayout(new BorderLayout());
		btnTempPanel.setPreferredSize(new Dimension(200, 150));
		btnTempPanel.add(btnGiven("FIGHT"), BorderLayout.NORTH);
		btnTempPanel.add(btnGiven("RUN"), BorderLayout.SOUTH);
		return (btnTempPanel);
	}

	private JPanel	movePanel() {
		JPanel	flowPanel;

		flowPanel = new JPanel(new BorderLayout());
		btnPanel = setMoveBtn();
		flowPanel.add(btnPanel, BorderLayout.NORTH);
		flowPanel.add(btnGiven("Switch to Console"), BorderLayout.SOUTH);
		btnHolder = flowPanel;
		return (flowPanel);
	}

	private class btnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
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
				case "FIGHT":
					arenaController.simulateFight(fightEnemy);
					break ;
				case "RUN":
					arenaController.reverseChoice();
					break ;
				case "Switch to Console":
					frame.setVisible(false);
					frame.dispose();
					arenaController.setSelection(10);
					break ;
			}
		}
	}

	private void	viewMap() {
		JScrollPane	txtScroll;

		this.txtArea = new JTextArea(stringMap());
		this.txtArea.setFont(new Font("monospaced", Font.PLAIN, 12));
		this.txtArea.setEditable(false);
		this.mainPanel.add(playerInfo(), BorderLayout.WEST);
		this.mainPanel.add(movePanel(), BorderLayout.EAST);
		txtScroll = new JScrollPane(this.txtArea);
		this.mainPanel.add(txtScroll, BorderLayout.CENTER);	
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
	}

	public void	prepareFight(String prepareString) {
		this.btnHolder.removeAll();
		this.txtArea.setText(prepareString);
		this.txtArea.update(this.txtArea.getGraphics());
		this.btnHolder.revalidate();
		this.btnHolder.repaint();
		this.mainPanel.revalidate();
		this.mainPanel.repaint();
	}

	public void	updateFight(String updateString) {
		String currentString;

		currentString = this.txtArea.getText() + "\n";
		currentString += updateString;
		this.txtArea.setText(currentString);
		this.txtArea.update(this.txtArea.getGraphics());
	}

	public void	showMessage(String message, boolean exitProgram) {
		JOptionPane.showMessageDialog(this.frame, message, "Information",
				JOptionPane.INFORMATION_MESSAGE);
		if (exitProgram) {
			this.frame.setVisible(false);
			this.frame.dispose();
		}
	}

	private String	getEnemyStats() {
		String enm;

		enm = "Do you want to fight " + this.fightEnemy.getName() + "\n"+
			"Experience : " + this.fightEnemy.getExperience() + "\n" +
			"Level : " + this.fightEnemy.getLevel() + "\n" +
			"Attack : " + this.fightEnemy.getAttack() + "\n" +
			"Defence : " + this.fightEnemy.getDefence();
		return (enm);
	}

	public void	makeChoice(PlayerModel enemy, ArenaController arenaController) {
		this.arenaController = arenaController;
		this.fightEnemy = enemy;
		this.txtArea.setText(getEnemyStats());
		this.txtArea.update(this.txtArea.getGraphics());
		btnHolder.removeAll();
		btnPanel = setChoiceBtn();
		btnHolder.add(btnPanel, BorderLayout.NORTH);
		btnHolder.revalidate();
		btnHolder.repaint();
		this.mainPanel.revalidate();
		this.mainPanel.repaint();
	}

	public void refresh() {

	}
}
