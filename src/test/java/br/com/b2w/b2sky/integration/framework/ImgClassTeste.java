package br.com.b2w.b2sky.integration.framework;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImgClassTeste {
	public static void main(String args[]) throws IOException{
		// open image
		 File imgPath = new File("C:/Users/Philip/Pictures/DSL_Types.png");
		 BufferedImage bufferedImage = ImageIO.read(imgPath);

		 // get DataBufferBytes from Raster
		 WritableRaster raster = bufferedImage .getRaster();
		 DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
		 data.getData();
	}
}
