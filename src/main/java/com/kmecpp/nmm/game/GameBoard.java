package com.kmecpp.nmm.game;

import java.util.HashMap;

public class GameBoard {

	private HashMap<BoardPosition, GamePiece> pieces = new HashMap<>();

	public GamePiece getPiece(int x, int y) {
		return pieces.get(new BoardPosition(x, y));
	}

}
