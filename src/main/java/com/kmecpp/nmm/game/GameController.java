package com.kmecpp.nmm.game;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.kmecpp.nmm.NineMensMorris;
import com.kmecpp.nmm.resources.Images;

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
	private int mouseX, mouseY;

	public static int pieceSize = 60;

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
		NineMensMorris.getStage().widthProperty().addListener((val, oldVal, newVal) -> refresh());
		NineMensMorris.getStage().heightProperty().addListener((val, oldVal, newVal) -> refresh());
		refresh();
	}

	public void refresh() {
		drawStage((int) stage.getWidth(), (int) stage.getHeight());
	}

	private void drawStage(int width, int height) {
		//		long start = System.nanoTime();

		//TODO: This is a hack. Weird stage bug makes the width 1938 when the window is maximized when the window size is only 1920. 
		Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
		if (stage.isMaximized()) {
			width = (int) bounds.getWidth();
			height = (int) bounds.getHeight();
		}

		gc.clearRect(0, 0, width, height);
		gc.rect(0, 0, 1000, 1000);

		int centerX = (int) canvas.getWidth() / 2;
		int centerY = (int) canvas.getHeight() / 2 - 30;

		int space = Math.min(width, height) / 9;
		int[] radii = new int[] { space, space * 2, space * 3 };

		game.getBoard().draw(centerX, centerY, radii, space);

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

			ArrayList<GamePiece> pieces = team.getPieces();
			for (int i = 0; i < pieces.size(); i++) {
				GamePiece piece = pieces.get(i);

				if (piece == selectedPiece) {
					piece.alignCoords(mouseX, mouseY);
				} else if (!piece.isPlaced()) {
					piece.alignCoords(pieceX, pieceY);
				}

				piece.draw();

				if ((i + 1) % 3 == 0) {
					pieceY += pieceSpace;
					pieceX -= 2 * pieceSpace;
				} else {
					pieceX += pieceSpace;
				}
			}
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

		gc.setFill(Color.rgb(0, 230, 0));
		int barWidth = 40;
		gc.fillRect(game.isLeftTurn() ? 0 : (width - barWidth), 0, barWidth, height);
		gc.setFill(Color.BLACK);

		//		long timeTaken = System.nanoTime() - start;
		//		String percentage = String.valueOf(timeTaken / 6945000D * 100); //6945000 is about the max frame time in nanoseconds for a 144Hz refresh rate
		//		System.out.println("Time Taken: " + timeTaken + "ns  " + percentage.substring(0, percentage.indexOf(".") + 2) + "%");
	}

	@FXML
	private void onMouseDragged(MouseEvent e) {
		mouseX = (int) e.getX();
		mouseY = (int) e.getY();
		if (selectedPiece != null) {
			selectedPiece.alignCoords((int) e.getX(), (int) e.getY());
			refresh();
		}
	}

	@FXML
	private void onMouseMoved(MouseEvent e) {
		mouseX = (int) e.getX();
		mouseY = (int) e.getY();
	}

	@FXML
	private void onMousePressed(MouseEvent e) {
		ArrayList<GamePiece> pieces = game.getState() == GameState.REMOVE
				? game.getCurrentTeam().getOtherTeam().getPieces()
				: game.getCurrentTeam().getPieces();

		for (GamePiece piece : pieces) {
			if (piece.isClicked(e)) {
				if (piece.isPlaced()) {
					if (game.getState() == GameState.REMOVE) {
						piece.remove();
						break;
					} else {
						continue;
					}
				} else if (game.getState() != GameState.REMOVE) {
					piece.select();
					selectedPiece = piece;
					break;
				}

				//					System.out.println(game.getCurrentTeam().getName());
				//					selectedPiece = piece;
			}
		}

		//		int x = (int) e.getX();
		//		int y = (int) e.getY();
		//		BoardPosition position = game.getBoardPosition(x, y);
		//		if (position != null) {
		//			if (game.isActive()) {
		//				//Select or move piece
		//			} else {
		//				//				if (position.isAvailable()) {
		//				//					position.setPiece(new GamePiece(position, game.getCurrentTeam()));
		//				//					redraw();
		//				//				} else {
		//				//					Sound.ERROR.play();
		//				//				}
		//			}
		//		}
	}

	@FXML
	private void onMouseReleased(MouseEvent e) {
		if (selectedPiece != null) {
			selectedPiece.place();
			selectedPiece = null;
		}
		refresh();
	}

}
