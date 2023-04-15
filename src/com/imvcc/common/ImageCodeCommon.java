package com.imvcc.common;

import java.awt.image.BufferedImage;

public class ImageCodeCommon {
	private BufferedImage bufferedImage;
	private String validateCode;
	
	
	public ImageCodeCommon(BufferedImage bufferedImage, String validateCode) {
		this.bufferedImage = bufferedImage;
		this.validateCode = validateCode;
	}
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
	public String getValidateCode() {
		return validateCode;
	}
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	
}
