package com.kmecpp.nmm.resources;

import java.net.URL;

import com.kmecpp.nmm.NineMensMorris;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public enum Sound {

	MILL2,
	PLACE,
	CAPTURE,
	ERROR,
	BUTTON_CLICK,

	;

	static {
		for (Sound sound : values()) {
			URL path = NineMensMorris.getResource("sounds/" + sound.name().toLowerCase().replace('_', '-') + ".wav");
			try {
				sound.media = new Media(path.toURI().toString());
			} catch (Exception e) {
				System.err.println("Could not load sound: " + path);
				e.printStackTrace();
			}
		}
	}

	private Media media;

	public void play() {
		new MediaPlayer(media).play();
	}

	//	private static final String PARENT_PATH = "sound/";

	//
	//	private static MediaPlayer getSound(String relativePath) {
	//		return new MediaPlayer(new Media(PARENT_PATH + relativePath));
	//	}

}
