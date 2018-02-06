package com.kmecpp.nmm;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameController implements Initializable {

	@FXML
	private AnchorPane pane;

	@FXML
	private Canvas canvas;

	private Stage stage;
	private GraphicsContext gc;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stage = Game.getStage();

		//Start scene maximized and stay maximized
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		stage.setWidth(bounds.getWidth());
		stage.setHeight(bounds.getHeight());
		stage.setMaximized(true);

		gc = canvas.getGraphicsContext2D();
		drawStage((int) stage.getWidth(), (int) stage.getHeight());

		Game.getStage().widthProperty().addListener((val, oldVal, newVal) -> drawStage(newVal.intValue(), (int) stage.getHeight()));
		Game.getStage().heightProperty().addListener((val, oldVal, newVal) -> drawStage((int) stage.getWidth(), newVal.intValue()));
	}

	private void drawStage(int screenWidth, int screenHeight) {

		//		gc.bacsetFill(Color);
		//		gc.setFill(Color.);
		//		gc.setFill(Color.BLACK);
		//		gc.fillRect(50, 50, 100, 100);
		pane.setStyle("-fx-background-color: whitesmoke");
		gc.clearRect(0, 0, screenWidth, screenHeight);

		int height = (int) (screenHeight / 3);
		int spacing = height / 3;
		//		System.out.println(height);

		for (int i = 0; i < 3; i++) {
			//			for (int j = 0; j < 3; j++) {
			int y = 80 + height * i;
			gc.fillOval(300, y, 30, 30);
			System.out.println("TEST: " + y);
			//			}
		}

		for (int y = height; y < 3 * height; y += spacing) {

		}
	}

}
