package com.kmecpp.nmm.game;

import com.kmecpp.nmm.resources.Sound;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class GamePiece extends Drawable {

	//	private static GamePiece selectedPiece;

	private BoardPosition position;
	private Team team;
	private boolean placed;
	private boolean selected;

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

	public boolean isPlaced() {
		return placed;
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

	//	public static GamePiece getSelectedPiece() {
	//		return selectedPiece;
	//	}
	//
	//	public boolean isSelected() {
	//		return selectedPiece == this;
	//	}
	//
	//	public void select() {
	//		selectedPiece = this;
	//	}

	public boolean place() {
		BoardPosition boardPosition = Game.getInstance().getBoard().getPosition(position.getX(), position.getY());
		if (boardPosition != null) {
			this.position = boardPosition;
			boardPosition.setPiece(this);
			this.placed = true;
			Sound.PLACE.play();

			if (boardPosition.isMill()) {
				System.out.println("MILL!");
				//TODO: MILL
			}

			return true;
		}
		return false;
		//		Game.getInstance().placePiece(this, positionId);
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void draw() {
		gc.setFill(selected ? new Color(team.getColor().getRed(), team.getColor().getGreen(), team.getColor().getBlue(), 0.5) : team.getColor());
		circle(position.getX(), position.getY(), SceneConstants.PIECE_SIZE);
		gc.setFill(Color.BLACK);
		//		circle(position.getX(), position.getY(), SceneConstants.PIECE_SIZE);
	}

}
