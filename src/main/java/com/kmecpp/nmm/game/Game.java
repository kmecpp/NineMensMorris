package com.kmecpp.nmm.game;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.paint.Color;

public class Game extends Drawable {

	//	public Game(GraphicsContext gc) {
	//		super(gc);
	//	}

	private HashMap<Integer, GamePosition> positions = new HashMap<>();

	//	private HashMap<Integer, GamePiece> ids = new HashMap<>();
	//	private HashMap<GamePosition, GamePiece> pieces = new HashMap<>();

	//	private GameState state = GameState.SETUP;
	private boolean active;

	//	private Team[] teams = new Team[] { new Team("Red", Color.RED, 1), new Team("Blue", Color.BLUE, 2) };
	private Team leftTeam = new Team("Red", Color.RED, 1);
	private Team rightTeam = new Team("Blue", Color.BLUE, 2);
	private boolean turn;

	public boolean isActive() {
		return active;
	}

	public Team[] getTeams() {
		return new Team[] { leftTeam, rightTeam };
	}

	public Team getLeftTeam() {
		return leftTeam;
	}

	public Team getRightTeam() {
		return rightTeam;
	}

	public Team getCurrentTeam() {
		return turn ? getLeftTeam() : getRightTeam();
	}

	public ArrayList<GamePiece> getPieces() {
		//		return teams[0].getP
		return new ArrayList<>();
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
			if (position.distance(x, y) < SceneConstants.POSITION_SIZE / 2) {
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
