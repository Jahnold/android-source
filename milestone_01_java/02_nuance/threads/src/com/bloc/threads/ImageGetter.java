package com.bloc.threads;

import java.lang.Boolean;
import java.lang.String;
import java.net.URL;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

public class ImageGetter extends Thread {

	private String mImageURL;
	private Boolean mDisplayAferLoad;

	public void setImageURL(String url) {
		mImageURL = url;
	}

	public void setDisplayAferLoad(Boolean displayAferLoad) {
		mDisplayAferLoad = displayAferLoad;
	}

	public ImageGetter() {

		// defaults
		mImageURL = "https://www.google.com/images/srpr/logo11w.png";
		mDisplayAferLoad = true;

	}

	@Override
	public void run() {

		try {
			File existingImage = new File("google_logo.png");
			if (existingImage.exists()) {
				existingImage.delete();
			}
			URL url = new URL(mImageURL);
			BufferedImage bufferedImage = ImageIO.read(url);
			File outputfile = new File("google_logo.png");
			ImageIO.write(bufferedImage, "png", outputfile);

			// only open the image if asked
			if (mDisplayAferLoad) {
				if ("/".equals(System.getProperties().getProperty("file.separator"))) {
					// nix
					Runtime.getRuntime().exec("open google_logo.png");
				} else {
					// windows
					Runtime.getRuntime().exec("mspaint google_logo.png");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

	}
}