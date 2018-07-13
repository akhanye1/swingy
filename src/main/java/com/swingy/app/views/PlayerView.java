/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.views;

import com.swingy.app.controllers.PlayerController;
import com.swingy.app.models.PlayerModel;
import java.util.List;

public abstract class PlayerView {
	public abstract int choosePlayer(PlayerController controller);
	public abstract void createPlayer(PlayerModel playerModel);
	public abstract void selectPlayer(List<PlayerModel> players);
}
