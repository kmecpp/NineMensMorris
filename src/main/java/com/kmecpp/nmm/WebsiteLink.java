package com.kmecpp.nmm;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Border;

public class WebsiteLink extends Hyperlink {

	public WebsiteLink(String url) {
		setText(url);
		setBorder(Border.EMPTY);
		setOnAction((action) -> {
			try {
				Desktop.getDesktop().browse(new URI(url.trim()));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		});
	}

}
