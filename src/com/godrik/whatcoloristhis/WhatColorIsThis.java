package com.godrik.whatcoloristhis;

import android.app.Activity;
import java.util.ArrayList;
import android.os.Bundle;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.LinearLayout;
import android.os.Handler;
import android.os.Message;

public class WhatColorIsThis extends Activity 
{
    private Handler h;

    private ViewGroup layout;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
	super.onCreate(savedInstanceState);
	LinearLayout lay = new LinearLayout (this);
	lay.setOrientation(LinearLayout.VERTICAL);
	layout = lay;
	setContentView(lay);
	

	h = new Handler(new Handler.Callback() {
		
		@Override
		    public boolean handleMessage(Message msg) {
		    if (msg.what == 0) {
			
		    }
		    else if (msg.what == 1) {
		    }
		    return false;
		}
	    });

    }

    @Override
    public void onResume()
    {
	super.onResume();
    }

    @Override
    public void onPause() {
	super.onPause();
	clearDisplay();
    } 

    private void clearDisplay() {
	layout.removeAllViews();
    }

    public synchronized void clear()
    {
	h.sendEmptyMessage(1);
    }
}
