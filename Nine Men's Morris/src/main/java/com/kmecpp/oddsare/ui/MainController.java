package com.kmecpp.oddsare.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class MainController {

	public static MainController instance;

	@FXML
	private TabPane tabPane;

	public MainController() {
		if (instance == null) {
			MainController.instance = this;
		} else {
			throw new IllegalStateException("Controller already initialized!");
		}
	}

	@FXML
	private void onPlayGame() {
		System.out.println("Play Game!");
		//		Game.getStage().setScene(new Scene());
		tabPane.getSelectionModel().select(1);
	}

	@FXML
	private void onServer() {
		System.out.println("Server!");
		tabPane.getSelectionModel().select(2);
	}

	@FXML
	private void onSettings() {
		System.out.println("Settings!");
		tabPane.getSelectionModel().select(3);
	}

	@FXML
	private void onAbout() {
		System.out.println("About!");
		tabPane.getSelectionModel().select(4);
	}

	@FXML
	private void onExit() {
		if (tabPane.getSelectionModel().getSelectedIndex() > 0) {
			tabPane.getSelectionModel().select(0);
		} else {
			System.exit(0);
		}

	}

}
