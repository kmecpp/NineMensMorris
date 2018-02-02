package com.kmecpp.nmm;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class MenuController {

	public static MenuController instance;

	//	@FXML
	//	private TabPane tabPane;

	public MenuController() {
		if (instance == null) {
			MenuController.instance = this;
		} else {
			throw new IllegalStateException("Controller already initialized!");
		}
	}

	@FXML
	private void clickOnePlayer() {
		System.out.println("Play Game!");
		//		Game.getStage().setScene(new Scene());
		//		tabPane.getSelectionModel().select(1);
	}

	@FXML
	private void clickTwoPlayer() {
		//		System.out.println(tabPane);
		//		System.out.println(tabPane.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void clickOnline() {
		//		System.out.println("Play Game!");
	}

	@FXML
	private void clickSettings() {
		//		System.out.println("Play Game!");
	}

	@FXML
	private void clickAbout() {
		//		System.out.println("Play Game!");
		Stage stage = new Stage();
		stage.setTitle("About");
		stage.setWidth(500);
		stage.setHeight(600);
		stage.centerOnScreen();
		stage.show();
	}

	@FXML
	private void clickExit() {
		//		if (tabPane.getSelectionModel().getSelectedIndex() > 0) {
		//			tabPane.getSelectionModel().select(0);
		//		} else {
		System.exit(0);
		//		}
	}

	//	@FXML
	//	private void onServer() {
	//		System.out.println("Server!");
	//		tabPane.getSelectionModel().select(2);
	//	}
	//
	//	@FXML
	//	private void onSettings() {
	//		System.out.println("Settings!");
	//		tabPane.getSelectionModel().select(3);
	//	}
	//
	//	@FXML
	//	private void onAbout() {
	//		System.out.println("About!");
	//		tabPane.getSelectionModel().select(4);
	//	}
	//
	//	@FXML
	//	private void onExit() {
	//		if (tabPane.getSelectionModel().getSelectedIndex() > 0) {
	//			tabPane.getSelectionModel().select(0);
	//		} else {
	//			System.exit(0);
	//		}
	//
	//	}

}
