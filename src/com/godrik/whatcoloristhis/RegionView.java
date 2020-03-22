package com.godrik.whatcoloristhis;

import android.view.View;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.content.Context;
import android.util.AttributeSet ;

//this RegionView draws two rectangle around the center of the canvas
//of different colors and of size relative to the size of the
//canvas. The size can be obtained with getRelativeSize()
public class RegionView extends View {

    Paint mypaint1;
    Paint mypaint2;

    double relativeSize;
    
    public RegionView(Context context) {
        super(context);

	mypaint1 = new Paint();
	mypaint1.setARGB(255, 0, 0, 0);

	mypaint2 = new Paint();
	mypaint2.setARGB(255, 255, 255, 255);
	
	relativeSize = .02;
    }

    public double getRelativeSize() {
	return relativeSize;
    }
    
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

	int height = canvas.getHeight();
	int width = canvas.getWidth();

	float xleft =(float)(width/2.0 - relativeSize/2.0*width);
	float xright=(float)(width/2.0 + relativeSize/2.0*width);

	float ytop    = (float)(height/2.0 - relativeSize/2.0*height);
	float ybottom = (float)(height/2.0 + relativeSize/2.0*height);
	
	//top line
	canvas.drawLine(xleft, ytop,
			xright, ytop,
			mypaint1);
	canvas.drawLine(xleft-1, ytop-1,
			xright+1, ytop-1,
			mypaint2);
	
	//bottom line
	canvas.drawLine(xleft, ybottom,
			xright, ybottom,
			mypaint1);
	canvas.drawLine(xleft-1, ybottom+1,
			xright+1, ybottom+1,
			mypaint2);

	
	//left line
	canvas.drawLine(xleft, ytop,
			xleft, ybottom,
			mypaint1);
	canvas.drawLine(xleft-1, ytop-1,
			xleft-1, ybottom+1,
			mypaint2);

	//right line
	canvas.drawLine(xright, ytop,
			xright, ybottom,
			mypaint1);
	canvas.drawLine(xright+1, ytop-1,
			xright+1, ybottom+1,
			mypaint2);
	
    }
}
