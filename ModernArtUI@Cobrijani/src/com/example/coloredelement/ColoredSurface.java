package com.example.coloredelement;

import java.util.Random;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

public class ColoredSurface {

	// this is a list of rgb colors that are being ignored in color changing
	// process (white and all shades of gray)
	private static int[] forbiddenColorsRGB = { Color.rgb(255, 255, 255), Color.rgb(224, 224, 224),
			Color.rgb(192, 192, 192), Color.rgb(160, 160, 160), Color.rgb(128, 128, 128), Color.rgb(96, 96, 96),
			Color.rgb(64, 64, 64), Color.rgb(32, 32, 32) };

	private ImageView coloredImageView;
	private int[] colors;

	public ColoredSurface(ImageView coloredImageView, int[] colors) {
		this.coloredImageView = coloredImageView;
		this.colors = colors;

	}

	public boolean changeColor(int progress) {

		try {
			ColorDrawable background = (ColorDrawable) coloredImageView.getBackground();
			int color = background.getColor();

			if (checkIfForbiden(color)) {
				return false;
			} else {
				int nextColor = colors[progress];
				coloredImageView.setBackgroundColor(nextColor);
			}

		} catch (ClassCastException e) {
			e.printStackTrace();
		}

		return true;
	}

	private boolean checkIfForbiden(int color) {
		for (int i = 0; i < forbiddenColorsRGB.length; ++i) {
			if (forbiddenColorsRGB[i] == color) {
				return true;
			}
		}
		return false;
	}

	public static int[] generateRandomColors(int num) {
		int[] colors = new int[num];
		Random rnd = new Random();

		for (int i = 0; i < colors.length; ++i) {
			colors[i] = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
		}

		return colors;
	}

}
