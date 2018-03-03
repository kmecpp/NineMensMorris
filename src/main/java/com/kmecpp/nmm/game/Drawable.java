package com.kmecpp.nmm.game;

import javafx.scene.canvas.GraphicsContext;

public abstract class Drawable {

	protected static GraphicsContext gc;
	protected int strokeWeight = 5;

	//	public Drawable(GraphicsContext gc) {
	//		this.gc = gc;
	//	}

	public static void setGraphicsContext(GraphicsContext gc) {
		Drawable.gc = gc;
	}

	public void strokeWeight(int strokeWeight) {
		this.strokeWeight = strokeWeight;
	}

	public void rectCenter(int x, int y, int width, int height) {
		gc.fillRect(x - width / 2, y - height / 2, width, height);
	}

	public void walls(int x, int y, int size) {
		int radius = size / 2;
		int halfStroke = strokeWeight / 2;
		int xMin = x - radius - halfStroke;
		int xMax = x + radius - halfStroke;
		int yMin = y - radius - halfStroke;
		int yMax = y + radius - halfStroke;
		gc.fillRect(xMin, yMin, strokeWeight, size); //Left
		gc.fillRect(xMax, yMin, strokeWeight, size); //Right
		gc.fillRect(xMin, yMin, size, strokeWeight); //Top
		gc.fillRect(xMin, yMax, size + strokeWeight, strokeWeight); //Bottom
	}

	public void hbar(int x, int y, int length) {
		gc.fillRect(x - length / 2, y, length, strokeWeight);
	}

	public void vbar(int x, int y, int length) {
		gc.fillRect(x, y - length / 2, strokeWeight, length);
	}

	//	private void line(int x1, int y1, int x2, int y2) {
	//		gc.fill
	//	}

	public void circle(int x, int y, int size) {
		int radius = size / 2;
		gc.fillOval(x - radius, y - radius, size, size);
	}

	//	protected abstract void draw();

}
