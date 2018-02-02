package com.kmecpp.oddsare.ui;

import java.io.IOException;

import com.kmecpp.oddsare.Images;
import com.kmecpp.oddsare.Game;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI {

	public static void start(Stage stage) throws IOException {

	}

	//	public static void setPage(Tab tab) {
	//		stage.getScene().getRoot().getChildrenUnmodifiable().
	//		stage.getScene().getRoot().g
	//		stage.setScene(page.getScene());
	//	}

	//	public static enum Page {
	//
	//		HOME("home", HomeController.class),
	//		HOST("host_game"),
	//
	//		;
	//
	//		private String name;
	//		private Scene scene;
	//		private Object controller;
	//
	//		private Page(String path) {
	//			this(path, null);
	//		}
	//
	//		private Page(String name, Class<?> controller) {
	//			this.name = name;
	//			this.controller = controller == null ? new Object() : Reflection.newInstance(controller);
	//		}
	//
	//		public String getPath() {
	//			return name;
	//		}
	//
	//		public Scene getScene() {
	//			return scene;
	//		}
	//
	//		static {
	//			for (Page page : values()) {
	//				URL location = Main.class.getResource("/scene/" + page.name + ".fxml");
	//				if (location == null) {
	//					System.err.println("Could not load FXML resource: /scene/" + page.name + ".fxml");
	//					continue;
	//				}
	//				try {
	//					FXMLLoader loader = new FXMLLoader(location);
	//					loader.setController(page.controller);
	//
	//					page.scene = new Scene(loader.load());
	//				} catch (IOException e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		}
	//
	//	}

}
