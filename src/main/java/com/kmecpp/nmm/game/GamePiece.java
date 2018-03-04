package com.kmecpp.nmm.game;

import com.kmecpp.nmm.resources.Sound;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class GamePiece extends Drawable {

	private BoardPosition position;
	private Team team;
	private boolean placed;
	private boolean selected;

	private int originalX, originalY;

	private int id;
	private static int count;

	//	private static GamePiece selectedPiece;

	public GamePiece(BoardPosition position, Team team) {
		this.position = position;
		this.team = team;

		id = count++;
	}

	public BoardPosition getPosition() {
		return position;
	}

	public GamePiece alignCoords(int x, int y) {
		position.setCoords(x, y);
		return this;
	}

	//	public static GamePiece getSelectedPiece() {
	//		return selectedPiece;
	//	}
	//
	//	public boolean isSelected() {
	//		return selected;
	//	}

	public boolean isPlaced() {
		return placed;
	}

	public Team getTeam() {
		return team;
	}

	public boolean isClicked(MouseEvent e) {
		return position.distance((int) e.getX(), (int) e.getY()) < SceneConstants.PIECE_SIZE / 2;
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

	public void select() {
		selected = true;
		originalX = position.getX();
		originalY = position.getY();
	}

	public void place() {
		BoardPosition boardPosition = Game.getInstance().getBoard().getPosition(position.getX(), position.getY());
		if (boardPosition != null) {
			this.position = boardPosition;
			boardPosition.setPiece(this);
			this.placed = true;

			if (boardPosition.isMill()) {
				Sound.MILL2.play();
				Game.getInstance().setState(GameState.REMOVE);
				System.out.println("MILL!");

				if (team.getOtherTeam().getPiecesLeft() == 3) {
					System.out.println("GAME OVER!");
				}
			} else {
				Sound.PLACE.play();
				Game.getInstance().endTurn();
			}

		} else {
			position.setCoords(originalX, originalY);
		}
		selected = false;
	}

	public void remove() {
		team.removePiece(this);
		Game.getInstance().endTurn();
		Game.getInstance().setState(GameState.MOVE);
		Sound.CAPTURE.play();
	}

	public void draw() {
		gc.setFill(selected ? new Color(team.getColor().getRed(), team.getColor().getGreen(), team.getColor().getBlue(), 0.4) : team.getColor());
		circle(position.getX(), position.getY(), SceneConstants.PIECE_SIZE);
		gc.setFill(Color.BLACK);
		//		circle(position.getX(), position.getY(), SceneConstants.PIECE_SIZE);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GamePiece) {
			return ((GamePiece) obj).id == id;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return id;
	}

}
