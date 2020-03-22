package com.godrik.whatcoloristhis;

import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera;


class MyPreviewCallback implements  PreviewCallback {
    private ColorTeller ct;

    RegionView rv;
    WhatColorIsThis wcis;
    
    public static void decodeYUV420SP(int[] rgb, byte[] yuv420sp, int width, int height) {
	final int frameSize = width * height;
		    
	for (int j = 0, yp = 0; j < height; j++) {
	    int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
	    for (int i = 0; i < width; i++, yp++) {
		int y = (0xff & ((int) yuv420sp[yp])) - 16;
		if (y < 0) y = 0;
		if ((i & 1) == 0) {
		    v = (0xff & yuv420sp[uvp++]) - 128;
		    u = (0xff & yuv420sp[uvp++]) - 128;
		}
		int y1192 = 1192 * y;
		int r = (y1192 + 1634 * v);
		int g = (y1192 - 833 * v - 400 * u);
		int b = (y1192 + 2066 * u);
			    
		if (r < 0) r = 0; else if (r > 262143) r = 262143;
		if (g < 0) g = 0; else if (g > 262143) g = 262143;
		if (b < 0) b = 0; else if (b > 262143) b = 262143;
			    
		rgb[yp] = 0xff000000 | ((r << 6) & 0xff0000) | ((g >> 2) & 0xff00) | ((b >> 10) & 0xff);
	    }
	}
    }


    public MyPreviewCallback(WhatColorIsThis wcis, RegionView overlay) {
	//super();

	this.wcis = wcis;
	this.rv = overlay;
	this.ct = new ColorTeller();
    }
    
    private int getRGBcenter(int[] rgb, int width, int height) {
	//return rgb[width/2+height/2*width];
	int r = 0;
	int g = 0;
	int b = 0;
	
	int windowsize = 30;
	int nbpixel = 0;
	//this will overflow if window is 16Mpixel
		    
	for (int row = width/2 - windowsize/2; row < width/2+windowsize/2 ; row++)
	    for (int col = height/2 - windowsize/2; col< height/2+windowsize/2 ; col++) {
		int color = rgb[col + row*width];
		r += ((color & 0x00FF0000)>> 16);
		g += ((color & 0x0000FF00)>> 8);
		b += ((color & 0x000000FF));
			    
		nbpixel++;
	    }

	r /= nbpixel;
	g /= nbpixel;
	b /= nbpixel;
		    
	return (r<<16) + (g<<8) + (b);
    }
		

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
		     
	Camera.Parameters param = camera.getParameters();

	int w = param.getPreviewSize().width;
	int h = param.getPreviewSize().height;

	int[] rgb = new int [w*h];
	decodeYUV420SP(rgb, data, w, h);
		     
	int col = getRGBcenter(rgb, w, h);

	int r = (col & 0x00FF0000)>>16;
	int g = (col & 0x0000FF00)>>8;
	int b = (col & 0x000000FF);
	String coloris= "Color is "+col+ "("+r+","+g+","+b+")"+ ". Size was "+w+","+h;
	coloris =coloris +"\n"+ct.tell(col);

	wcis.thisIsTheColor(coloris);

    }

}