package com.kmecpp.nmm.game;

import javafx.scene.input.MouseEvent;

public class GamePiece extends Drawable {

	private BoardPosition position;
	private Team team;

	public GamePiece(BoardPosition position, Team team) {
		this.position = position;
		this.team = team;
	}

	public BoardPosition getPosition() {
		return position;
	}

	public GamePiece alignCoords(int x, int y) {
		position.setCoords(x, y);
		return this;
	}

	//	public void setPosition(BoardPosition position) {
	//		this.position = position;
	//	}

	public Team getTeam() {
		return team;
	}

	public boolean isClicked(MouseEvent e) {
		return position.distance((int) e.getX(), (int) e.getY()) < SceneConstants.PIECE_SIZE;
	}

	public void place() {
		Game.getInstance().getBoardPosition(position.getX(), position.getY());

		//		Game.getInstance().placePiece(this, positionId);
	}

	public void draw() {
		circle(position.getX(), position.getY(), SceneConstants.PIECE_SIZE);
	}

}
