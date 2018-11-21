package com.picture;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestGraphics {

	public static void main(String[] args) {
		for(int i=0;i<=15;i++){
			pi(i);
		}
			
		
}

private static void pi(int j){
	Long start = System.currentTimeMillis();
	int height = 500;
	int width = 500;
	int rows = 10;
	int cols = rows;
	BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	
    Graphics2D g = (Graphics2D) bi.getGraphics();
    Long start1 = System.currentTimeMillis();  
    System.out.println("=="+(start1-start));
    
    int rowHt = height / (rows);
    for (int i = 0; i < rows; i++)
      g.drawLine(0, i * rowHt, width, i * rowHt);

    // draw the columns
    int rowWid = width / (cols);
    for (int i = 0; i < cols; i++)
      g.drawLine(i * rowWid, 0, i * rowWid, height);
    
    try
    {
    	      	
        ImageIO.write(bi, "jpeg", new File("tableImage.jpeg"));
        Long  end = System.currentTimeMillis();
        System.out.println(j+"=="+(end-start));
    }
    catch(IOException ioe)
    {
        System.out.println("write: " + ioe.getMessage());
    }
   
	}
}
