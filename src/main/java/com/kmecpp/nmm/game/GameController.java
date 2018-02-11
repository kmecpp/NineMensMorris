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

public class GameController extends Drawable implements Initializable {

	@FXML
	private AnchorPane pane;

	@FXML
	private Canvas canvas;

	private GraphicsContext gc;
	protected int strokeWeight = 5;

	private Stage stage;

	private Game game = new Game();

	private GamePiece selectedPiece;
	//	private int clickOriginX, clickOriginY;

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

		//		//		pane.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
		//		pane.setBackground(new Background(new BackgroundImage(Images.GAME_BACKGROUND, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		pane.setBackground(new Background(new BackgroundImage(Images.GAME_BACKGROUND, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

		gc = canvas.getGraphicsContext2D();
		Drawable.setGraphicsContext(canvas.getGraphicsContext2D());

		canvas.widthProperty().bind(stage.widthProperty());
		canvas.heightProperty().bind(stage.heightProperty());
		NineMensMorris.getStage().widthProperty().addListener((val, oldVal, newVal) -> drawStage(newVal.intValue(), (int) stage.getHeight()));
		NineMensMorris.getStage().heightProperty().addListener((val, oldVal, newVal) -> drawStage((int) stage.getWidth(), newVal.intValue()));
		redraw();
	}

	private void redraw() {
		drawStage((int) stage.getWidth(), (int) stage.getHeight());
	}

	private void drawStage(int width, int height) {
		//		pane.resize(width, height - 40);
		gc.clearRect(0, 0, width, height);
		gc.rect(0, 0, 1000, 1000);

		int centerX = (int) canvas.getWidth() / 2;
		int centerY = (int) canvas.getHeight() / 2 - 30;

		int space = Math.min(width, height) / 9;
		int[] radii = new int[] { space, space * 2, space * 3 };

		//Draw connecting lines (should this be before positions are drawn?)
		int ringRange = radii[1];
		hbar(centerX - ringRange, centerY, ringRange); //Left
		hbar(centerX + ringRange, centerY, ringRange); //Right
		vbar(centerX, centerY - ringRange, ringRange); //Top
		vbar(centerX, centerY + ringRange, ringRange); //Bottom

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
						circle(x, y, SceneConstants.POSITION_SIZE);
						gc.setFill(Color.BLACK);
					} else {
						gc.setFill(Color.BLUE);
						circle(x, y, SceneConstants.PIECE_SIZE);
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

		int textY = (int) (centerY * 0.6);
		int horizontalSpace = centerX - radii[2];
		int textCenterLeft = horizontalSpace / 2;
		int textCenterRight = width - textCenterLeft;

		int verticalSpace = height - textY;
		//		;
		int pieceSpace = (int) (SceneConstants.PIECE_SPACING + SceneConstants.PIECE_SIZE + ((Math.min(verticalSpace, horizontalSpace) - 4 * SceneConstants.PIECE_SIZE) / 10));
		//		System.out.println(pieceSpace);
		//		int pieceSpace = SceneConstants.PIECE_SIZE + SceneConstants.PIECE_SPACING;
		for (Team team : game.getTeams()) {
			int pieceX = (team.isLeftTeam() ? textCenterLeft : textCenterRight) - pieceSpace;
			int pieceY = centerY;

			for (int i = 0; i < 9; i++) {

				if (team.hasSetupPiece(i)) {
					//				team.alignSetupPiece(i, pieceX, pieceY);
					gc.setFill(team.getColor());
					team.alignSetupPiece(i, pieceX, pieceY).draw(gc);

					//					Circle piece = new Circle(pieceX, pieceY, SceneConstants.PIECE_SIZE / 2, team.getColor());
					//					piece.setPickOnBounds(true);
					//					piece.setOnDragDetected((e) -> {
					//					});
					//					piece.setOnMouseDragged((e) -> {
					//						//						piece.setFill
					//						Color c = (Color) piece.getFill();
					//						piece.setFill(new Color(c.getRed(), c.getGreen(), c.getBlue(), 0.5));
					//						System.out.println((int) e.getX() + ", " + (int) clickOriginX);
					//
					//						piece.setCenterX(e.getX());
					//						piece.setCenterY(e.getY());
					//					});
					//					piece.setOnMouseReleased((e) -> {
					//						Color c = (Color) piece.getFill();
					//						piece.setFill(new Color(c.getRed(), c.getGreen(), c.getBlue(), 1));
					//						System.out.println("HI");
					//					});

					//					pane.getChildren().add(piece);
					//					circle(pieceX, pieceY, SceneConstants.PIECE_SIZE);
					gc.setFill(Color.BLACK);
				}

				if ((i + 1) % 3 == 0) {
					pieceY += pieceSpace;
					pieceX -= 2 * pieceSpace;
				} else {
					pieceX += pieceSpace;
				}
			}
			//			for (int id i = textY + 30; i < 10; i++) {
			//				team.alignSetupPiece(id, x, y);
			//			}
		}

		int maxWidth = (int) (horizontalSpace / 1.6);
		int textSize = horizontalSpace / 10 + 5;
		gc.setFont(Font.font("Verdana", FontWeight.BLACK, textSize));

		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.BOTTOM);
		gc.setFill(Color.BLACK);
		gc.fillText(game.getLeftTeam().getName(), textCenterLeft, textY, maxWidth);
		hbar(textCenterLeft, textY + 3, maxWidth);

		gc.fillText(game.getRightTeam().getName(), textCenterRight, textY, maxWidth);
		hbar(textCenterRight, textY + 3, maxWidth);

		gc.setFill(Color.BLACK);
	}

	@FXML
	private void onMouseDragged(MouseEvent e) {

	}

	@FXML
	private void onMouseClicked(MouseEvent e) {
		//		clickOriginX = (int) e.getX();
		//		clickOriginY = (int) e.getY();

		for (GamePiece piece : game.getPieces()) {
			
		}

		int x = (int) e.getX();
		int y = (int) e.getY();
		GamePosition position = game.getPosition(x, y);
		if (position != null) {
			if (game.isActive()) {
				//Select or move piece
			} else {
				if (position.isAvailable()) {
					position.setPiece(new GamePiece(position, game.getCurrentTeam()));
					redraw();
				} else {
					Sound.ERROR.play();
				}
			}
		}
	}

}
