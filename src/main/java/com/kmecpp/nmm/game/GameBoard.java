package com.kmecpp.nmm.game;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.paint.Color;

public class GameBoard extends Drawable {

	//	private Game game;
	public BoardPosition[] positions = new BoardPosition[24];

	private static final int[][] MILLS = {
			//Top
			{ 0, 1, 2 },
			{ 8, 9, 10 },
			{ 16, 17, 18 },
			{ 1, 9, 17 }, //Connector

			//Bottom
			{ 5, 6, 7 },
			{ 13, 14, 15 },
			{ 21, 22, 23 },
			{ 6, 14, 22 },

			//Left
			{ 0, 3, 5 },
			{ 8, 11, 13 },
			{ 16, 19, 21 },
			{ 3, 11, 19 },

			//Right
			{ 2, 4, 7 },
			{ 10, 12, 15 },
			{ 18, 20, 23 },
			{ 4, 12, 20 },
	};

	private static final HashMap<Integer, ArrayList<Integer[]>> millMap = new HashMap<>();
	private static final HashMap<Integer, ArrayList<Integer>> adjacencyMap = new HashMap<>();

	//Initialize board
	static {
		//		long start = System.nanoTime();
		for (int i = 0; i < MILLS.length; i++) {
			int[] mill = MILLS[i];

			for (int idIndex = 0; idIndex < mill.length; idIndex++) {
				int id = mill[idIndex];
				ArrayList<Integer[]> mills = millMap.getOrDefault(id, new ArrayList<>());
				mills.add(new Integer[] { mill[(idIndex + 1) % mill.length], mill[(idIndex + 2) % mill.length] });
				millMap.put(id, mills);

				ArrayList<Integer> adjacencies = adjacencyMap.getOrDefault(id, new ArrayList<>());
				if (idIndex == 1) {
					adjacencies.add(mill[0]);
					adjacencies.add(mill[2]);
				} else {
					adjacencies.add(mill[1]);
				}
				adjacencyMap.put(id, adjacencies);
			}
		}
		//		System.out.println("Initialization Time: " + ((System.nanoTime() - start) / 1000000F) + "ms");
	}

	//	public GameBoard(Game game) {
	//		this.game = game;
	//	}

	public BoardPosition getPosition(int x, int y) {
		for (BoardPosition position : positions) {
			if (position.distance(x, y) < SceneConstants.POSITION_SIZE * 1.2) {
				return position;
			}
		}
		return null;
	}

	public boolean isHeldBy(int id, Team team) {
		GamePiece piece = positions[id].getPiece();
		if (piece != null && piece.getTeam() == team) {
			return true;
		}
		return false;
	}

	public boolean isMill(BoardPosition position) {
		Team team = position.getPiece().getTeam();
		millSearch: for (Integer[] mill : millMap.get(position.getId())) {
			for (int i : mill) {
				if (!isHeldBy(i, team)) {
					continue millSearch;
				}
			}
			return true;
		}
		return false;
	}

	public void draw(int centerX, int centerY, int[] radii, int spacing) {
		//Draw connecting lines (should this be before positions are drawn?)
		int ringRange = radii[1];

		gc.setFill(Color.BLACK);
		hbar(centerX - ringRange, centerY, ringRange); //Left
		hbar(centerX + ringRange, centerY, ringRange); //Right
		vbar(centerX, centerY - ringRange, ringRange); //Top
		vbar(centerX, centerY + ringRange, ringRange); //Bottom

		int positionId = 0;
		for (int r = 0; r < 3; r++) {
			int ringSize = radii[r];

			walls(centerX, centerY, 2 * ringSize);

			for (int yPos = -1; yPos <= 1; yPos++) {
				for (int xPos = -1; xPos <= 1; xPos++) {
					if (yPos == 0 && xPos == 0) {
						continue;
					}
					int x = centerX + ringSize * xPos;
					int y = centerY + ringSize * yPos;

					BoardPosition position = positions[positionId];
					if (position == null) {
						positions[positionId] = new BoardPosition(positionId, x, y);
					} else {
						position.setCoords(x, y);
					}
					/*
					 * 1) Wait for init
					 * 2) Use circle node
					 * 3)
					 */

					positions[positionId].draw();

					positionId++;
				}
			}

			//			System.out.println(spacing);
			//			gc.rect(centerX - 2 * spacing, centerY, 2 * spacing, strokeWeight);

			//			gc.setTextAlign(TextAlignment.CENTER);
			//			gc.setTextBaseline(VPos.CENTER);
			//			gc.setFill(Color.RED);
			//			gc.setFont(Font.font("Verdana", FontWeight.BLACK, 500));
			//			gc.fillText("DON'T BE A DINGUS", centerX, centerY, 1800);
			//			gc.setFill(Color.BLACK);
		}
	}

}
