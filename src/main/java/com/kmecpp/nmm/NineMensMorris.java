package com.kmecpp.nmm;

import java.io.IOException;
import java.net.URL;

import com.kmecpp.nmm.resources.GameView;
import com.kmecpp.nmm.resources.Images;
import com.kmecpp.nmm.resources.MusicPlayer;

import javafx.application.Application;
import javafx.stage.Stage;

public class NineMensMorris extends Application {

	private static Stage stage;
	public static final String VERSION = "1.0";

	/*
	 * RULES
	 * Can move anywhere with three pieces left
	 * Cannot take pieces from a mill
	 * Can only move one square at a time?
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		long start = System.currentTimeMillis();
		NineMensMorris.stage = stage;
		stage.setTitle("Nine Men's Morris");
		stage.getIcons().add(Images.ICON);

		GameView.MENU.load();
		//		setPage(Page.HOME);
		stage.show();

		new MusicPlayer().shufflePlay();
		System.out.println("Application started in " + (System.currentTimeMillis() - start) + "ms");
	}

	public static Stage getStage() {
		return stage;
	}

	public static URL getResource(String relativePath) {
		return NineMensMorris.class.getResource("/" + relativePath);
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
