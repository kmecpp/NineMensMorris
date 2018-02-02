package com.kmecpp.oddsare;

import javafx.scene.image.Image;

public class Images {

	public static final Image ICON = new Image("image/icon.png");

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
