package com.kmecpp.nmm.game;

import javafx.scene.paint.Color;

public class Team {

	private String name;
	private Color color;

	private int setupPieces = 9;
	private int activePieces = 0;

	public Team(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public void placePiece() {
		setupPieces--;
		activePieces++;
	}

	public int getSetupPieces() {
		return setupPieces;
	}

	public int getActivePieces() {
		return activePieces;
	}
}
