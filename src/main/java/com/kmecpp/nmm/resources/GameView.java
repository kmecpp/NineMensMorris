package com.kmecpp.nmm.resources;

import java.io.IOException;
import java.net.URL;

import com.kmecpp.nmm.Game;
import com.kmecpp.nmm.GameController;
import com.kmecpp.nmm.MenuController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public enum GameView {

	MENU(MenuController.class),
	GAME(GameController.class),

	;

	private Class<?> controller;
	private URL path;

	private GameView(Class<?> controller) {
		this.controller = controller;
	}

	static {
		for (GameView gameView : values()) {
			try {
				if (gameView.controller.getConstructor() == null) {
					System.err.println("Game view '" + gameView.controller + "' has no public default constructor!");
				}
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			try {
				gameView.path = Game.getResource("scene/" + gameView.getFileName());
			} catch (Exception e) {
				System.err.println("Could not load message: " + gameView.getFileName() + "  (" + e.getMessage() + ")");
				//				e.printStackTrace();
			}
		}
	}

	public String getFileName() {
		return this.name().toLowerCase().replace('_', '-') + ".fxml";
	}

	public Object getController() {
		try {
			return controller.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException();
		}
	}

	public URL getPath() {
		return path;
	}

	public void load() {
		try {
			System.out.println(name());
			FXMLLoader loader = new FXMLLoader(path);
			loader.setController(controller.newInstance());
			Game.getStage().setScene(new Scene(loader.load()));
		} catch (IOException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
