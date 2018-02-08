package com.kmecpp.nmm.resources;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import com.kmecpp.nmm.NineMensMorris;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {

	private ArrayList<Media> songs = new ArrayList<>();
	private int index;

	private static MediaPlayer player;

	public MusicPlayer() {
		File musicFolder = new File(NineMensMorris.getResource("sounds/music").getFile());
		for (File file : musicFolder.listFiles()) {
			songs.add(new Media(file.toURI().toString()));
		}
	}

	public void playNext() {
		index = index < songs.size() - 1 ? index + 1 : 0;
		player = new MediaPlayer(songs.get(index));
		player.setVolume(0.2);

		player.setOnEndOfMedia(() -> playNext());
		//		player.play();
	}

	public void shufflePlay() {
		Collections.shuffle(songs);
		playNext();
	}

}
