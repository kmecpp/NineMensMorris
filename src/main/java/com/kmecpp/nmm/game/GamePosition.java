package com.kmecpp.nmm.game;

public class GamePosition {

	private int x;
	private int y;

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
