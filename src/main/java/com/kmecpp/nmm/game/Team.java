package com.kmecpp.nmm.game;

import javafx.scene.paint.Color;

public class Team {

	private String name;
	private Color color;

	private int pieces = 9;

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

	public int getPieces() {
		return pieces;
	}
}
