package com.kmecpp.nmm.game;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Team {

	//	private static int teamCount;

	private String name;
	private Color color;
	private int teamNumber;

	//	private int setupPieces = 9;
	//	private GamePiece[] setupPieces = new GamePiece[9];

	private ArrayList<GamePiece> pieces = new ArrayList<>();
	private int piecesLeft;

	public Team(String name, Color color, int teamNumber) {
		this.name = name;
		this.color = color;
		this.teamNumber = teamNumber;

		for (int i = 0; i < 9; i++) {
			pieces.add(new GamePiece(new BoardPosition(-1, -1, -1), this));
		}

		//		teamCount++;
		//		if (teamCount > 2) {
		//			throw new IllegalStateException("Cannot create more than two teams!");
		//		}

	}

	public ArrayList<GamePiece> getPieces() {
		return pieces;
	}

	public int getPiecesLeft() {
		return piecesLeft;
	}

	public void addPiece() {
		piecesLeft++;
	}

	public void removePiece(GamePiece piece) {
		if (pieces.remove(piece)) {
			piecesLeft--;
		}
	}

	public boolean isLeftTeam() {
		return teamNumber == 1;
	}

	public boolean isRightTeam() {
		return teamNumber == 2;
	}

	public Team getOtherTeam() {
		return Game.getInstance().getTeams()[teamNumber % 2];
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	//	public void placePiece() {
	//		//		setupPieces--;
	//		activePieces++;
	//	}
	//
	//	public GamePiece[] getSetupPieces() {
	//		return setupPieces;
	//	}
	//
	//	public boolean hasSetupPiece(int id) {
	//		return setupPieces[id] != null;
	//	}
	//
	//	public GamePiece getSetupPiece(int id) {
	//		return setupPieces[id];
	//	}
	//
	//	public GamePiece alignSetupPiece(int id, int x, int y) {
	//		GamePiece piece = setupPieces[id];
	//		piece.alignCoords(x, y);
	//		return piece;
	//
	//		//		GamePiece piece = setupPieces[id];
	//		//		if (piece != null) {
	//		//			piece.alignCoords(x, y);
	//		//			//			piece.setPosition(new BoardPosition(x, y));
	//		//		} else {
	//		//			piece = setupPieces[id] = new GamePiece(new BoardPosition(x, y), this);
	//		//		}
	//		//		return piece;
	//	}
	//
	//	public int getActivePieces() {
	//		return activePieces;
	//	}
}
