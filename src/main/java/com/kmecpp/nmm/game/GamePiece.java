package com.kmecpp.nmm.game;

public class GamePiece {

	private GamePosition position;
	private Team team;

	public GamePiece(GamePosition position, Team team) {
		this.position = position;
		this.team = team;
	}

	public GamePosition getPosition() {
		return position;
	}

	public Team getTeam() {
		return team;
	}

}
