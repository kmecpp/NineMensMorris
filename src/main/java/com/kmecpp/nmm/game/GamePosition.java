package com.kmecpp.nmm.game;

public class GamePosition {

	private int x;
	private int y;
	private GamePiece piece;

	public GamePosition(int x, int y) {
		this.x = x;
		this.y = y;
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

	public double distance(int x, int y) {
		return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
	}

	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setPiece(GamePiece piece) {
		this.piece = piece;
	}

	public boolean isAvailable() {
		return piece == null;
	}

	//	public boolean hasPiece() {
	//		return piece != null;
	//	}

	@Override
	public int hashCode() {
		return x * y; //(a + b) * (a + b + 1) / 2;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GamePosition) {
			GamePosition pos = (GamePosition) obj;
			return pos.x == x && pos.y == y;
		}
		return false;
	}

}
