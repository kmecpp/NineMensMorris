package com.kmecpp.nmm.game;

public enum GameState {

	SETUP,
	MOVE,
	REMOVE,
	END,

	;

	public boolean isBoardInteractable() {
		return this == MOVE || this == REMOVE;
	}

}
