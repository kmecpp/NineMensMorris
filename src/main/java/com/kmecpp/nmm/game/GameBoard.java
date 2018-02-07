package com.kmecpp.nmm.game;

import java.util.HashMap;

public class GameBoard {

	private HashMap<Integer, GamePiece> pieces = new HashMap<>();
	private HashMap<GamePosition, Integer> ids = new HashMap<>();

	public GamePiece getPiece(int id) {
		return pieces.get(id);
	}

	public boolean isEmpty(int id) {
		return pieces.containsKey(id);//pieces.contains(new GamePosition(x, y));
	}

	public void setPosition(int pieceId, int xCoord, int yCoord) {
		//		GamePiece piece = new GamePiece(x, y);
		//		pieces.put(new GamePosition(x, y), new GamePiece(x, y))
	}

	public void reset() {
		pieces.clear();
	}

}
