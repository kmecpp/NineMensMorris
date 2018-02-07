package com.kmecpp.nmm.game;

import java.net.URL;
import java.util.ResourceBundle;

import com.kmecpp.nmm.Game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameController implements Initializable {

	@FXML
	private AnchorPane pane;

	@FXML
	private Canvas canvas;

	private Stage stage;
	private GraphicsContext gc;

	private int strokeWeight = 4;

	private GameBoard gameBoard;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stage = Game.getStage();

		//Start scene maximized and stay maximized
		Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
		stage.setWidth(bounds.getWidth());
		stage.setHeight(bounds.getHeight());
		stage.setMaximized(true);
		stage.setMinWidth(800);
		stage.setMinHeight(500);

		pane.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));

		gc = canvas.getGraphicsContext2D();

		canvas.widthProperty().bind(pane.widthProperty());
		canvas.heightProperty().bind(pane.heightProperty());
		Game.getStage().widthProperty().addListener((val, oldVal, newVal) -> drawStage(newVal.intValue(), (int) stage.getHeight()));
		Game.getStage().heightProperty().addListener((val, oldVal, newVal) -> drawStage((int) stage.getWidth(), newVal.intValue()));
	}

	private void drawStage(int screenWidth, int screenHeight) {
		pane.resize(screenWidth, screenHeight - 40);
		gc.clearRect(0, 0, screenWidth, screenHeight);
		gameBoard = new GameBoard();

		int centerX = (int) canvas.getWidth() / 2;
		int centerY = (int) canvas.getHeight() / 2;

		int marginX = 0;
		int marginY = 0;

		//		System.out.println(height);

		int space = Math.min(screenWidth, screenHeight) / 9;
		int minRadius = space;

		for (int t = 0; t < 3; t++) {

			//			int spacingX = (screenWidth - 2 * marginX) / 3;
			//			int spacingY = (screenHeight - 2 * marginY) / 3;
			int ringSize = minRadius + space * t;

			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (i == 0 && j == 0) {
						continue;
					}
					int x = centerX + ringSize * i;
					int y = centerY + ringSize * j;
					//					System.out.println("TEST: " + y);
					circle(x, y, 30);
				}
			}

			walls(centerX, centerY, 2 * ringSize);

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

	@FXML
	private void onMouseClicked(MouseEvent e) {
		System.out.println(e);
	}

	//	private void strokeWeight(int strokeWeight) {
	//		this.strokeWeight = strokeWeight;
	//	}

	private void walls(int x, int y, int size) {
		int radius = size / 2;
		int xMin = x - radius;
		int xMax = x + radius;
		int yMin = y - radius;
		int yMax = y + radius;
		gc.fillRect(xMin, yMin, strokeWeight, size);
		gc.fillRect(xMax, yMin, strokeWeight, size);
		gc.fillRect(xMin, yMin, size, strokeWeight);
		gc.fillRect(xMin, yMax, size + strokeWeight, strokeWeight);
	}

	//	private void line(int x1, int y1, int x2, int y2) {
	//		//		gc.fillRect()
	//	}

	private void circle(int x, int y, int size) {
		int radius = size / 2;
		gc.fillOval(x - radius, y - radius, size, size);
	}

}
