package com.kmecpp.nmm.game;

import javafx.scene.input.MouseEvent;

public class GamePiece extends Drawable {

	private GamePosition position;
	private Team team;

	public GamePiece(GamePosition position, Team team) {
		this.position = position;
		this.team = team;
	}

	public GamePosition getPosition() {
		return position;
	}

	public void setPosition(int x, int y) {
		position.setCoords(x, y);
	}

	public void setPosition(GamePosition position) {
		this.position = position;
	}

	public Team getTeam() {
		return team;
	}

	public boolean isClicked(MouseEvent e) {
		return position.distance((int) e.getX(), (int) e.getY()) < SceneConstants.PIECE_SIZE;
	}

	public void draw() {
		circle(position.getX(), position.getY(), SceneConstants.PIECE_SIZE);
	}

}
