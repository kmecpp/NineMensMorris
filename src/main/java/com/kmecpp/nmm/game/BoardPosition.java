package com.kmecpp.nmm.game;

import javafx.scene.paint.Color;

public class BoardPosition extends Drawable {

	int id;
	private int x;
	private int y;
	private GamePiece piece;

	public BoardPosition(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public GamePiece getPiece() {
		return piece;
	}

	public void setPiece(GamePiece piece) {
		this.piece = piece;
	}

	public double distance(int x, int y) {
		return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
	}

	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}

	//	public void setPiece(GamePiece piece) {
	//		this.piece = piece;
	//	}
	//
	//	public boolean isAvailable() {
	//		return piece == null;
	//	}

	//	public boolean hasPiece() {
	//		return piece != null;
	//	}

	public boolean isMill() {
		return Game.getInstance().getBoard().isMill(this);
	}

	@Override
	public int hashCode() {
		return x * y; //(a + b) * (a + b + 1) / 2;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BoardPosition) {
			BoardPosition pos = (BoardPosition) obj;
			return pos.x == x && pos.y == y;
		}
		return false;
	}

	public void draw() {
		circle(x, y, SceneConstants.POSITION_SIZE);
		gc.setFill(Color.BLACK);
	}

}
