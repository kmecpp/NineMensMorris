package com.kmecpp.nmm.game;

import javafx.scene.paint.Color;

public class Team {

	//	private static int teamCount;

	private String name;
	private Color color;
	private int teamNumber;

	//	private int setupPieces = 9;
	private GamePiece[] setupPieces = new GamePiece[9];
	private int activePieces = 0;

	public Team(String name, Color color, int teamNumber) {
		this.name = name;
		this.color = color;
		this.teamNumber = teamNumber;

		for (int i = 0; i < setupPieces.length; i++) {
			setupPieces[i] = new GamePiece(new GamePosition(Integer.MAX_VALUE, Integer.MAX_VALUE), this);
		}

		//		teamCount++;
		//		if (teamCount > 2) {
		//			throw new IllegalStateException("Cannot create more than two teams!");
		//		}
	}

	public boolean isLeftTeam() {
		return teamNumber == 1;
	}

	public boolean isRightTeam() {
		return teamNumber == 2;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public void placePiece() {
		//		setupPieces--;
		activePieces++;
	}

	public GamePiece[] getSetupPieces() {
		return setupPieces;
	}

	public boolean hasSetupPiece(int id) {
		return setupPieces[id] != null;
	}

	public GamePiece alignSetupPiece(int id, int x, int y) {
		GamePiece piece = setupPieces[id];
		if (piece != null) {
			piece.setPosition(new GamePosition(x, y));
		} else {
			piece = setupPieces[id] = new GamePiece(new GamePosition(x, y), this);
		}
		return piece;
	}

	public int getActivePieces() {
		return activePieces;
	}
}
