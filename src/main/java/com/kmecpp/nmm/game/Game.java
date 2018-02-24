package com.kmecpp.nmm.game;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Game {

	//	public Game(GraphicsContext gc) {
	//		super(gc);
	//	}

	//	private BoardPosition[] boardPositions = new BoardPosition[24];
	private GameBoard board = new GameBoard(this);

	//	private HashMap<Integer, GamePiece> ids = new HashMap<>();
	//	private HashMap<GamePosition, GamePiece> pieces = new HashMap<>();

	//	private GameState state = GameState.SETUP;
	private boolean active;

	//	private Team[] teams = new Team[] { new Team("Red", Color.RED, 1), new Team("Blue", Color.BLUE, 2) };
	private Team leftTeam = new Team("Red", Color.RED, 1);
	private Team rightTeam = new Team("Blue", Color.BLUE, 2);
	private int turns;

	private static final Game instance = new Game();

	public Game() {
		if (instance != null) {
			throw new IllegalStateException("Game already initialized!");
		}
	}

	public static Game getInstance() {
		return instance;
	}

	public GameBoard getBoard() {
		return board;
	}

	public void endTurn() {
		turns++;
	}

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
		return turns % 2 == 0 ? getLeftTeam() : getRightTeam();
	}

	public ArrayList<GamePiece> getAllPieces() {

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

	public void placePiece(GamePiece piece, int positionId) {

	}

	public void movePiece() {

	}

	public boolean getPiece(int x, int y) {
		return false;
	}

	
	//TODO: REMOVE GET POSITION AND SET POSITION
	public BoardPosition getPosition(int id) {
		return board.positions[id];
	}

	public void setPosition(int id, int x, int y) {
		BoardPosition position = board.positions[id];
		if (position == null) {
			board.positions[id] = new BoardPosition(id, x, y);
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
