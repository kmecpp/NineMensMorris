package com.kmecpp.nmm.game;

import java.net.URL;
import java.util.ResourceBundle;

import com.kmecpp.nmm.NineMensMorris;
import com.kmecpp.nmm.resources.Images;
import com.kmecpp.nmm.resources.Sound;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameController implements Initializable {

	@FXML
	private AnchorPane pane;

	@FXML
	private Canvas canvas;

	private Stage stage;
	private GraphicsContext gc;

	private int strokeWeight = 5;

	private Game game = new Game();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stage = NineMensMorris.getStage();

		//Start scene maximized and stay maximized
		Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
		stage.setWidth(bounds.getWidth());
		stage.setHeight(bounds.getHeight());
		stage.setMaximized(true);
		stage.setMinWidth(800);
		stage.setMinHeight(500);

		//		pane.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
		pane.setBackground(new Background(new BackgroundImage(Images.GAME_BACKGROUND, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

		gc = canvas.getGraphicsContext2D();

		canvas.widthProperty().bind(pane.widthProperty());
		canvas.heightProperty().bind(pane.heightProperty());
		NineMensMorris.getStage().widthProperty().addListener((val, oldVal, newVal) -> drawStage(newVal.intValue(), (int) stage.getHeight()));
		NineMensMorris.getStage().heightProperty().addListener((val, oldVal, newVal) -> drawStage((int) stage.getWidth(), newVal.intValue()));
		drawStage((int) stage.getWidth(), (int) stage.getHeight());
	}

	private void drawStage(int width, int height) {
		pane.resize(width, height - 40);
		gc.clearRect(0, 0, width, height);
		gc.rect(0, 0, 1000, 1000);
		System.out.println("DRAWWW: " + gc);

		int centerX = (int) canvas.getWidth() / 2;
		int centerY = (int) canvas.getHeight() / 2;

		int space = Math.min(width, height) / 9;
		int[] radii = new int[] { space, space * 2, space * 3 };

		int positionId = 0;
		for (int r = 0; r < 3; r++) {
			int ringSize = radii[r];

			walls(centerX, centerY, 2 * ringSize);

			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (i == 0 && j == 0) {
						continue;
					}
					int x = centerX + ringSize * i;
					int y = centerY + ringSize * j;

					game.setPosition(positionId, x, y);

					GamePosition position = game.getPosition(positionId);
					if (position.isAvailable()) {
						circle(x, y, Game.POSITION_SIZE);
						gc.setFill(Color.BLACK);
					} else {
						gc.setFill(Color.BLUE);
						circle(x, y, Game.PIECE_SIZE);
						gc.setFill(Color.BLACK);
					}

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

		//Draw connecting lines (should this be before positions are drawn?)
		int ringRange = radii[1];
		hbar(centerX - ringRange, centerY, ringRange); //Left
		hbar(centerX + ringRange, centerY, ringRange); //Right
		vbar(centerX, centerY - ringRange, ringRange); //Top
		vbar(centerX, centerY + ringRange, ringRange); //Bottom

		int textY = (int) (centerY * 0.6);
		int textSpace = centerX - radii[2];
		int textCenterLeft = textSpace / 2;
		int textCenterRight = width - textCenterLeft;

		int maxWidth = (int) (textSpace / 1.6);
		int textSize = textSpace / 10 + 5;
		gc.setFont(Font.font("Verdana", FontWeight.BLACK, textSize));

		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.BOTTOM);
		gc.setFill(Color.BLACK);
		gc.fillText("Player One", textCenterLeft, textY, maxWidth);
		hbar(textCenterLeft, textY + 3, maxWidth);
		gc.fillText("Player Two", textCenterRight, textY, maxWidth);
		hbar(textCenterRight, textY + 3, maxWidth);

		gc.setFill(Color.BLACK);
	}

	@FXML
	private void onMouseClicked(MouseEvent e) {
		int x = (int) e.getX();
		int y = (int) e.getY();
		GamePosition position = game.getPosition(x, y);
		if (position != null) {
			if (game.isActive()) {
				//Select or move piece
			} else {
				if (position.isAvailable()) {
					position.setPiece(new GamePiece(position, game.getCurrentTeam()));
				} else {
					Sound.ERROR.play();
				}
			}
		}
	}

	//	private void strokeWeight(int strokeWeight) {
	//		this.strokeWeight = strokeWeight;
	//	}

	private void walls(int x, int y, int size) {
		int radius = size / 2;
		int halfStroke = strokeWeight / 2;
		int xMin = x - radius - halfStroke;
		int xMax = x + radius - halfStroke;
		int yMin = y - radius - halfStroke;
		int yMax = y + radius - halfStroke;
		gc.fillRect(xMin, yMin, strokeWeight, size); //Left
		gc.fillRect(xMax, yMin, strokeWeight, size); //Right
		gc.fillRect(xMin, yMin, size, strokeWeight); //Top
		gc.fillRect(xMin, yMax, size + strokeWeight, strokeWeight); //Bottom
	}

	private void hbar(int x, int y, int length) {
		gc.fillRect(x - length / 2, y, length, strokeWeight);
	}

	private void vbar(int x, int y, int length) {
		gc.fillRect(x, y - length / 2, strokeWeight, length);
	}

	//	private void line(int x1, int y1, int x2, int y2) {
	//		gc.fill
	//	}

	private void circle(int x, int y, int size) {
		int radius = size / 2;
		gc.fillOval(x - radius, y - radius, size, size);
	}

}
