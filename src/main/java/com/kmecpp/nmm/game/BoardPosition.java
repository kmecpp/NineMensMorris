package com.kmecpp.nmm.game;

public class BoardPosition {

	private int x;
	private int y;

	public BoardPosition(int x, int y) {
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

}
