package com.kmecpp.nmm;

import java.io.IOException;

import com.kmecpp.nmm.resources.Images;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application {

	private static Stage stage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		Game.stage = stage;
		stage.setTitle("Nine Men's Morris");
		stage.getIcons().add(Images.ICON);

		FXMLLoader loader = new FXMLLoader(Game.class.getResource("/scene/application.fxml"));
		loader.setController(new MenuController());
		stage.setScene(new Scene(loader.load()));
		//		setPage(Page.HOME);
		stage.show();
	}

	public static Stage getStage() {
		return stage;
	}

}
