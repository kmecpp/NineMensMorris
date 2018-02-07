package com.kmecpp.nmm;

import com.kmecpp.nmm.resources.GameView;
import com.kmecpp.nmm.resources.Message;
import com.kmecpp.nmm.resources.Sound;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class MenuController {

	public static MenuController instance;

	public MenuController() {
		if (instance == null) {
			MenuController.instance = this;
		} else {
			throw new IllegalStateException("Controller already initialized!");
		}
	}

	@FXML
	private void clickOnePlayer() {
		//		Game.getStage().setScene(new Scene());
		//		tabPane.getSelectionModel().select(1);
	}

	@FXML
	private void clickTwoPlayer() {
		Sound.BUTTON_CLICK.play(); //TODO: Figure out why this is necessary?
		GameView.GAME.load();
		NineMensMorris.getStage().centerOnScreen();
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
		@SuppressWarnings("restriction")
		ObservableList<Stage> stages = com.sun.javafx.stage.StageHelper.getStages();
		if (stages.size() > 1) {
			stages.get(1).toFront();;
			return;
		}
		Stage stage = NineMensMorris.newStage("About", 700, 400);

		ScrollPane scrollPane = new ScrollPane();
		Scene scene = new Scene(scrollPane);

		TextFlow textFlow = new TextFlow();
		textFlow.setPadding(new Insets(10, 10, 10, 10));

		Text title = new Text("Nine Mens Morris\n\n");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		Text version = new Text("Version: " + NineMensMorris.VERSION + "\n");
		//		Text version = new Text(Message.VERSION.getText() + "\n\n");
		VBox links = new VBox();
		TextFlow github = new TextFlow(new Text("GitHub:"), new WebsiteLink("https://github.com/kmecpp/NineMensMorris"));
		TextFlow website = new TextFlow(new Text("Website:"), new WebsiteLink("http://kmecpp.com"));
		links.getChildren().addAll(github, website);
		//		text.setFont(Font.font("Verdana", FontWeight.BOLD, 16));

		//		text.setText("Copyright (C) Kevin Phillips\n\n");

		Text licenseText = new Text();
		licenseText.setText("\n\n" + Message.LICENSE.getText());

		//		stage.minWidthProperty().bind(textFlow.widthProperty());

		textFlow.getChildren().addAll(title, version, links, licenseText);
		scrollPane.setContent(textFlow);
		scene.setRoot(scrollPane);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private void clickExit() {
		//		if (tabPane.getSelectionModel().getSelectedIndex() > 0) {
		//			tabPane.getSelectionModel().select(0);
		//		} else {
		NineMensMorris.getStage().hide();
		System.exit(0);
		//		}
	}

	@FXML
	private void buttonClicked() {
		Sound.BUTTON_CLICK.play();
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
