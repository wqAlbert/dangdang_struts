package com.imvcc.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import com.imvcc.common.ImageCodeCommon;

/**
 * Image validate code util
 * @author Mr.rong
 *
 */
public class ImageValidateUtil {
	public static final int HEIGHT = 30;
	public static final int WIDTH = 100;
	public static final int NUM = 4;
	public static final int LINENUM=3;
	/**
	 * draw image in memory
	 * @return
	 */
	public static ImageCodeCommon memoryImage() {
		char[] sq = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z'};
		int[] fontStyle = { Font.BOLD, Font.ITALIC, Font.BOLD | Font.ITALIC };
		String validateCode="";
		ImageCodeCommon imageCode;

		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random r = new Random();

		g.setColor(new Color(r.nextInt(50) + 175, r.nextInt(50) + 175, r
				.nextInt(50) + 175));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		for (int i = 0; i < NUM; i++) {
			//随机验证码
			char randomChar=sq[r.nextInt(sq.length)];
			validateCode+=randomChar;
			// 随机字体颜色
			g.setColor(new Color(r.nextInt(150), r.nextInt(150), r
							.nextInt(150)));
			// 随机字体大小
			int tmpFontSize = r.nextInt(HEIGHT - 16) + 16;
			// 随机字体样式
			g.setFont(new Font("SansSerif", fontStyle[r.nextInt(3)],
					tmpFontSize));
			g.drawString(randomChar + "", 8 + i * ((WIDTH - 10) / NUM),
					r.nextInt(HEIGHT - tmpFontSize * 7 / 8) + tmpFontSize * 7
							/ 8);
		}

		for (int i = 0; i < LINENUM; i++) {
			// 水平干扰线
			g.setColor(new Color(r.nextInt(50) + 175, r.nextInt(50) + 175, r
					.nextInt(50) + 175));
			g.drawLine(0, r.nextInt(HEIGHT), WIDTH, r.nextInt(HEIGHT));
			// 垂直干扰线
			g.setColor(new Color(r.nextInt(50) + 175, r.nextInt(50) + 175, r
					.nextInt(50) + 175));
			g.drawLine(r.nextInt(WIDTH), 0, r.nextInt(WIDTH), HEIGHT);
		}
		imageCode=new ImageCodeCommon(image, validateCode);
		return imageCode;
	}
	/**
	 * write to inputstream for struts2
	 * @param is
	 * @throws IOException
	 */
	public static InputStream writeToInputStream(BufferedImage bufferedImage) throws IOException {
		ByteArrayInputStream input=null;
		ByteArrayOutputStream output=new ByteArrayOutputStream();
		ImageOutputStream imgOut=ImageIO.createImageOutputStream(output);
		ImageIO.write(bufferedImage, "gif", imgOut);
		imgOut.close();
		input=new ByteArrayInputStream(output.toByteArray());
		return input;
	}
	
	public static boolean verifyImageCode(String sessionCode,String code) {
		if(sessionCode.equalsIgnoreCase(code)) {
			return true;
		}
		return false;
	}
}
