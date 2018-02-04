package com.kmecpp.nmm.resources;

import com.kmecpp.jlib.utils.IOUtil;
import com.kmecpp.nmm.Game;

public enum Message {

	LICENSE,
	VERSION,

	;

	static {
		for (Message sound : values()) {
			try {
				sound.text = IOUtil.readString(Game.getResource("messages/" + sound.getFileName()));
			} catch (Exception e) {
				System.err.println("Could not load message: " + sound.getFileName() + "  (" + e.getMessage() + ")");
				//				e.printStackTrace();
			}
		}
	}

	public String getFileName() {
		return this.name().toLowerCase().replace('_', '-') + ".txt";
	}

	private String text;

	public String getText() {
		return text;
	}

}
