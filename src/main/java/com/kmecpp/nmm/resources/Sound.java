package com.kmecpp.nmm.resources;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public enum Sound {

	BUTTON_HOVER,

	;

	static {
		for (Sound sound : values()) {
			try {
				sound.player = new MediaPlayer(new Media("sound/" + sound.getFileName()));
			} catch (Exception e) {
				System.err.println("Could not load sound: " + sound.getFileName());
			}
		}
	}

	public String getFileName() {
		return this.name().toLowerCase().replace('_', '-') + ".ogg";
	}

	private MediaPlayer player;

	public void play() {
		player.play();
	}

	//	private static final String PARENT_PATH = "sound/";

	//
	//	private static MediaPlayer getSound(String relativePath) {
	//		return new MediaPlayer(new Media(PARENT_PATH + relativePath));
	//	}

}
