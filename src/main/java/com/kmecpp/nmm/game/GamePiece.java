package com.kmecpp.nmm.game;

import javafx.scene.canvas.GraphicsContext;

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

	public void setPosition(GamePosition position) {
		this.position = position;
	}

	public Team getTeam() {
		return team;
	}

	public void draw(GraphicsContext gc) {
		circle(position.getX(), position.getY(), SceneConstants.PIECE_SIZE);
	}

}
