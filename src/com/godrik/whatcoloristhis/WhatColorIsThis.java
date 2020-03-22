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




	
	c = getCameraInstance();
	// Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, c);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);

	RegionView overlay = new RegionView(this);
	preview.addView(overlay);
	
	PreviewCallback mPicture = new MyPreviewCallback(this, overlay);
	c.setPreviewCallback(mPicture);
	
    }

    public void thisIsTheColor(String coloris) {
	color_display.setText(coloris);
    }
    
    /** A safe way to get an instance of the Camera object. */
    public Camera getCameraInstance(){
	Camera c = null;
	try {
	    c = Camera.open(); // attempt to get a Camera instance
	    Camera.Parameters param = c.getParameters();
	    //param.setPictureFormat(ImageFormat.RGB_565);

	    param.setFlashMode("torch");
	    c.setParameters(param);
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
