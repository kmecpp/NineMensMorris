package com.kmecpp.nmm;

import java.io.IOException;
import java.net.URL;

import com.kmecpp.nmm.resources.GameView;
import com.kmecpp.nmm.resources.Images;

import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {

	private static Stage stage;
	public static final String VERSION = "1.0";

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		Game.stage = stage;
		stage.setTitle("Nine Men's Morris");
		stage.getIcons().add(Images.ICON);

		GameView.MENU.load();
		//		setPage(Page.HOME);
		stage.show();
	}

	public static Stage getStage() {
		return stage;
	}

	public static URL getResource(String relativePath) {
		return Game.class.getResource("/" + relativePath);
	}

	public static Stage newStage(String title, int width, int height) {
		Stage stage = new Stage();
		stage.getIcons().add(Images.ICON);
		stage.setTitle(title);
		stage.setWidth(width);
		stage.setHeight(height);
		stage.centerOnScreen();
		return stage;
	}

}
