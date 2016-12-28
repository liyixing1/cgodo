package com.cgodo.verifycode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

import com.cgodo.verifycode.model.VerifyCodeModel;
import com.jhlabs.image.TwirlFilter;

/**
 * 
 * 
 * 描述:图形验证码生成器
 *
 * @author liyixing
 * @version 1.0
 * @since 2016年12月10日 下午11:31:05
 */
public class VerifyCodeGenerator {
	
	private static final Random RANDOM = new Random();

	/**
	 * 生成随机验证码文件,并返回验证码值
	 * 
	 * @param w
	 * @param h
	 * @param outputFile
	 * @param verifySize
	 * @return
	 * @throws IOException
	 */
	public static final String outputVerifyImage(int w, int h, File outputFile,
			int verifySize) throws IOException {
		VerifyCodeModel verifyCodeModel = new VerifyCodeModel();
		String verifyCode = verifyCodeModel.getCode();
		outputImage(w, h, outputFile, verifyCode);
		return verifyCode;
	}

	/**
	 * 输出随机验证码图片流,并返回验证码值
	 * 
	 * @param w
	 * @param h
	 * @param os
	 * @param verifySize
	 * @return
	 * @throws IOException
	 */
	public static final String outputVerifyImage(int w, int h, OutputStream os,
			int verifySize) throws IOException {
		VerifyCodeModel verifyCodeModel = new VerifyCodeModel();
		String verifyCode = verifyCodeModel.getCode();
		outputImage(w, h, os, verifyCode);
		return verifyCode;
	}

	/**
	 * 生成指定验证码图像文件
	 * 
	 * @param w
	 * @param h
	 * @param outputFile
	 * @param code
	 * @throws IOException
	 */
	public static final void outputImage(int w, int h, File outputFile, String code)
			throws IOException {
		if (outputFile == null) {
			return;
		}
		File dir = outputFile.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			outputFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(outputFile);
			outputImage(w, h, fos, code);
			fos.close();
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * 输出指定验证码图片流
	 * 
	 * @param w
	 * @param h
	 * @param os
	 * @param code
	 * @throws IOException
	 */
	public static final void outputImage(int w, int h, OutputStream os, String code)
			throws IOException {
		int verifySize = code.length();
		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		Random rand = new Random();
		Graphics2D g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Color[] colors = new Color[5];
		Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN,
				Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
				Color.PINK, Color.YELLOW };
		float[] fractions = new float[colors.length];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
			fractions[i] = rand.nextFloat();
		}
		Arrays.sort(fractions);

		g2.setColor(Color.GRAY);// 设置边框色
		g2.fillRect(0, 0, w, h);

		Color c = getRandColor(200, 250);
		g2.setColor(c);// 设置背景色
		g2.fillRect(0, 2, w, h - 4);

		// 绘制干扰线
		Random random = new Random();
		g2.setColor(getRandColor(160, 200));// 设置线条的颜色
		for (int i = 0; i < 20; i++) {
			int x = random.nextInt(w - 1);
			int y = random.nextInt(h - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			g2.drawLine(x, y, x + xl + 40, y + yl + 20);
		}

		// 添加噪点
		float yawpRate = 0.05f;// 噪声率
		int area = (int) (yawpRate * w * h);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(w);
			int y = random.nextInt(h);
			int rgb = getRandomIntColor();
			image.setRGB(x, y, rgb);
		}

		shear(g2, w, h, c);// 使图片扭曲

		g2.setColor(getRandColor(100, 160));
		// 计算字体大小
		int fontSize = w / code.length();

		if (fontSize % 2 != 0) {
			fontSize = fontSize - 1;
		}

		if (fontSize < 1) {
			fontSize = 2;
		}

		// 防止字体越界
		if (fontSize > 16) {
			fontSize = fontSize - 16;
		}

		Font font = new Font("KaiTi", Font.BOLD, fontSize);
		g2.setFont(font);
		char[] chars = code.toCharArray();
		for (int i = 0; i < verifySize; i++) {
			AffineTransform affine = new AffineTransform();
			affine.setToRotation(
					Math.PI / 4 * rand.nextDouble()
							* (rand.nextBoolean() ? 1 : -1), (w / verifySize)
							* i + fontSize / 2, h / 2);
			g2.setTransform(affine);
			g2.drawChars(chars, i, 1, ((w - 10) / verifySize) * i + 5, h / 2
					+ fontSize / 2 - 10);
		}
		
		//扭曲化
		ColorModel colorModel = new DirectColorModel(32, 0xff000000, 0xff00, 0xff);
		g2.dispose();
		TwirlFilter t = new TwirlFilter();
		//变形度
		t.setAngle(1.0f);
		ImageIO.write(t.filter(image,  
				t.createCompatibleDestImage(image,colorModel)), "jpg", os);
	}

	private static final Color getRandColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + RANDOM.nextInt(bc - fc);
		int g = fc + RANDOM.nextInt(bc - fc);
		int b = fc + RANDOM.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	private static final int getRandomIntColor() {
		int[] rgb = getRandomRgb();
		int color = 0;
		for (int c : rgb) {
			color = color << 8;
			color = color | c;
		}
		return color;
	}

	private static final int[] getRandomRgb() {
		int[] rgb = new int[3];
		for (int i = 0; i < 3; i++) {
			rgb[i] = RANDOM.nextInt(255);
		}
		return rgb;
	}

	private static final void shear(Graphics g, int w1, int h1, Color color) {
		shearX(g, w1, h1, color);
		shearY(g, w1, h1, color);
	}

	private static final void shearX(Graphics g, int w1, int h1, Color color) {

		int period = RANDOM.nextInt(2);

		boolean borderGap = true;
		int frames = 1;
		int phase = RANDOM.nextInt(2);

		for (int i = 0; i < h1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period
							+ (6.2831853071795862D * (double) phase)
							/ (double) frames);
			g.copyArea(0, i, w1, 1, (int) d, 0);
			if (borderGap) {
				g.setColor(color);
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}

	}

	private static final void shearY(Graphics g, int w1, int h1, Color color) {

		int period = RANDOM.nextInt(40) + 10; // 50;

		boolean borderGap = true;
		int frames = 20;
		int phase = 7;
		for (int i = 0; i < w1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period
							+ (6.2831853071795862D * (double) phase)
							/ (double) frames);
			g.copyArea(i, 0, 1, h1, 0, (int) d);
			if (borderGap) {
				g.setColor(color);
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + h1, i, h1);
			}

		}

	}

	public static final void main(String[] args) throws IOException {
		File dir = new File("E:/verifies");

		int w = 320, h = 80;
		for (int i = 0; i < 50; i++) {
			VerifyCodeModel verifyCodeModel = new VerifyCodeModel();
			String verifyCode = verifyCodeModel.getCode();
			File file = new File(dir, verifyCode + ".jpg");
			outputImage(w, h, file, verifyCode);
		}
	}
}