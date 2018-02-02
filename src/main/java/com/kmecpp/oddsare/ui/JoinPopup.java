package com.kmecpp.oddsare.ui;

import com.kmecpp.oddsare.ServerInfo;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class JoinPopup {

	public static ServerInfo run() {
		Stage stage = new Stage();

		Scene scene = new Scene(new BorderPane());

		stage.setScene(scene);
		stage.show();
		return null;
	}

}
