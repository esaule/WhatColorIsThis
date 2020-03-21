package com.godrik.whatcoloristhis;

import android.app.Activity;
import java.util.ArrayList;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.ViewGroup;
import android.view.View;
import android.widget.SeekBar;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.os.Handler;
import android.os.Message;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PreviewCallback;

import android.hardware.Camera;

public class WhatColorIsThis extends Activity 
{
    private Handler h;
    private TextView color_display;

    private Camera c = null;
    private CameraPreview mPreview = null;

    private ColorTeller ct;
    
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

    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
	super.onCreate(savedInstanceState);
	this.setContentView(R.layout.main);

	color_display = (TextView) findViewById(R.id.color_display);

	h = new Handler(new Handler.Callback() {
		
		@Override
		    public boolean handleMessage(Message msg) {
		    
		    return false;
		}
	    });


	ct = new ColorTeller();

	
	c = getCameraInstance();
	// Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, c);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);

	final PreviewCallback mPicture = new PreviewCallback() {


		@Override
		public void onPreviewFrame(byte[] data, Camera camera) {
		     
		     Camera.Parameters param = camera.getParameters();

		     String dafuq = " dafuq";

		     if (param.getPreviewFormat() == ImageFormat.NV21)
			 dafuq="";

		     
		     
		    // Bitmap b = mPreview.getBitmap();
		    // int w = b.getWidth();
		    // int h = b.getHeight();
		    // int col = b.getPixel(w/2, h/2);
		     int w = param.getPreviewSize().width;
		     int h = param.getPreviewSize().height;

		     int[] rgb = new int [w*h];
		     decodeYUV420SP(rgb, data, w, h);
		     
		     int col = rgb[w/2+h/2*w];
		     int r = (col & 0x00FF0000)>>16;
		     int g = (col & 0x0000FF00)>>8;
		     int b = (col & 0x000000FF);
		     String coloris= "Color is "+col+ "("+r+","+g+","+b+":"+ct.tell(col)+")"+ ". Size was "+w+","+h+dafuq;
		     color_display.setText(coloris);
		 }
	    };

	c.setPreviewCallback(mPicture);
	
	//final Button button = findViewById(R.id.snap_button);
	// button.setOnClickListener(new View.OnClickListener() {
	// 	public void onClick(View v) {
	// 	    // Code here executes on main thread after user presses button
	// 	    c.takePicture(null, mPicture, null);
	// 	}
	//     });
    }

    /** A safe way to get an instance of the Camera object. */
    public Camera getCameraInstance(){
	Camera c = null;
	try {
	    c = Camera.open(); // attempt to get a Camera instance
	    Camera.Parameters param = c.getParameters();
	    param.setPictureFormat(ImageFormat.RGB_565);
	}
	catch (Exception e){
	    // Camera is not available (in use or does not exist)
	    color_display.setText(R.string.no_camera + e.getMessage());
	}
	return c; // returns null if camera is unavailable
    }
    
    @Override
    public void onResume()
    {
	super.onResume();
    }

    @Override
    public void onPause() {
	super.onPause();
    } 

    public synchronized void clear()
    {
	h.sendEmptyMessage(1);
    }
}
