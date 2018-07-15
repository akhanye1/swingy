
package com.swingy.app.views;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import com.swingy.app.models.PlayerModel;

public class PlayersTableGui extends JFrame {
	private List<PlayerModel> 	players;
	private PlayerViewGui		playerView;
	private JTable				playersTable;

	public PlayersTableGui(List<PlayerModel> players, PlayerViewGui playerView) {
		this.setTitle("Select Hero");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.players = players;
		this.playerView = playerView;
		this.addHeros();
	}

	private void	addHeros() {
		String col[] = {"Rec", "Name", "Class", "lvl", "exp", "Attck", "Def", "Hp"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		JScrollPane scrollPane;
		JPanel		fillPanel;
		JButton		btnSelect;

		btnSelect = new JButton("SELECT");
		btnSelect.addActionListener(new viewBtnClass());
		playersTable = new JTable(tableModel);
		for (PlayerModel tempModel : players) {
			Object[] data = {
				tempModel.getRec(),
				tempModel.getName(),
				tempModel.getPClass(),
				tempModel.getLevel(),
				tempModel.getExperience(),
				tempModel.getAttack(),
				tempModel.getDefence(),
				tempModel.getHitPoints()
			};
			tableModel.addRow(data);
		}
		scrollPane = new JScrollPane(playersTable);
		playersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fillPanel = new JPanel(new BorderLayout());
		fillPanel.add(scrollPane, BorderLayout.CENTER);
		fillPanel.add(btnSelect, BorderLayout.SOUTH);
		this.add(fillPanel);
		this.setPreferredSize(new Dimension(500, 300));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void selectPlayer() {
		int selRow;

		if (!playersTable.getSelectionModel().isSelectionEmpty()) {
			this.setVisible(false);
			this.dispose();
			selRow = playersTable.getSelectedRow();
			playerView.setPlayer(this.players.get(selRow));
		}
		else {
			JOptionPane.showMessageDialog(this, "No Hero Selected", "Selection Error",
					JOptionPane.ERROR_MESSAGE);
		}	
	}

	private class viewBtnClass implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
				case "SELECT" :
					selectPlayer();
					break;
			}
		}
	}

}
