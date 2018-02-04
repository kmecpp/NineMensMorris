package com.kmecpp.nmm.resources;

import java.io.IOException;
import java.net.URL;

import com.kmecpp.nmm.Game;
import com.kmecpp.nmm.GameController;
import com.kmecpp.nmm.MenuController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public enum GameView {

	MENU(new MenuController()),
	GAME(new GameController()),

	;

	private Object controller;
	private URL path;

	private GameView(Object controller) {
		this.controller = controller;
	}

	static {
		for (GameView sound : values()) {
			try {
				sound.path = Game.getResource("scene/" + sound.getFileName());
			} catch (Exception e) {
				System.err.println("Could not load message: " + sound.getFileName() + "  (" + e.getMessage() + ")");
				//				e.printStackTrace();
			}
		}
	}

	public String getFileName() {
		return this.name().toLowerCase().replace('_', '-') + ".fxml";
	}

	public Object getController() {
		return controller;
	}

	public URL getPath() {
		return path;
	}

	public void load() {
		try {
			FXMLLoader loader = new FXMLLoader(path);
			loader.setController(controller);
			Game.getStage().setScene(new Scene(loader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
