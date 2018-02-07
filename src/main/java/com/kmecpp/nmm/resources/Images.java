package com.kmecpp.nmm.resources;

import javafx.scene.image.Image;

public class Images {

	private static final String PARENT_PATH = "images/";

	public static final Image ICON = getImage("icon.png");
	public static final Image GAME_BACKGROUND = getImage("game-background4k.png");

	private static Image getImage(String relativePath) {
		return new Image(PARENT_PATH + relativePath);
	}

	public abstract static class Resource {

		private final String path;

		public Resource(String name) {
			this.path = name;
		}

		public String getPath() {
			return path;
		}

		public abstract Object get();

	}

}
