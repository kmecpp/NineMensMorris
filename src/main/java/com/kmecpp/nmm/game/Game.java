package com.kmecpp.nmm.game;

import java.util.HashMap;

public class Game {

	public static final int POSITION_SIZE = 40;
	public static final int PIECE_SIZE = 50;

	private HashMap<Integer, GamePosition> positions = new HashMap<>();

	//	private HashMap<Integer, GamePiece> ids = new HashMap<>();
	//	private HashMap<GamePosition, GamePiece> pieces = new HashMap<>();

	//	private GameState state = GameState.SETUP;
	private boolean active;

	private Team team1;
	private Team team2;
	private boolean turn;

	public boolean isActive() {
		return active;
	}

	public Team getTeam1() {
		return team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public Team getCurrentTeam() {
		return turn ? team1 : team2;
	}

	//	public GamePiece getPiece(int id) {
	//		return ids.get(id);
	//	}

	//	public GamePiece getPiece(int x, int y) {
	//		return pieces.get(new GamePosition(x, y));
	//	}

	//	public boolean isEmpty(int id) {
	//		return !ids.containsKey(id);
	//		//		return pieces.containsKey(id);//pieces.contains(new GamePosition(x, y));
	//	}

	public void placePiece() {

	}

	public void movePiece() {

	}

	public boolean getPiece(int x, int y) {
		return false;
	}

	public GamePosition getPosition(int id) {
		return positions.get(id);
	}

	public GamePosition getPosition(int x, int y) {
		for (GamePosition position : positions.values()) {
			if (position.distance(x, y) < POSITION_SIZE / 2) {
				return position;
			}
		}
		return null;
	}

	public void setPosition(int id, int x, int y) {
		GamePosition position = positions.get(id);
		if (position == null) {
			positions.put(id, new GamePosition(x, y));
		} else {
			position.setCoords(x, y);
		}
		//		GamePiece piece = new GamePiece(x, y);
		//		pieces.put(new GamePosition(x, y), new GamePiece(x, y))
	}

	//	public void reset() {
	//		ids.clear();
	//		pieces.clear();
	//	}

}
