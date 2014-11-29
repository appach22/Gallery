package com.test.gallery;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

public class MyGridView extends GridView {

    private GestureDetectorCompat mDetector; 
    private Button mFadingButton;
    private int mCurrentAlpha;
    
    private void Init(Context context)
    {
		mDetector = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener(){
	        @Override
	        public boolean onScroll(MotionEvent event1, MotionEvent event2, 
	                float distanceX, float distanceY) {
	        	if (mFadingButton != null)
	        	{
	        		// Doesn't work for some unknown reason
	        		//mFadingView.setAlpha(currentAlpha + offset);
	        		
	        		// Make changes in alpha-channel of background and text depending on scrolling offset
	        		float offset = -distanceY;
	        		// If we're going to show the button - make it visible
	        		if (mCurrentAlpha == 0 && offset > 0)
	        			mFadingButton.setVisibility(View.VISIBLE);
	        		mCurrentAlpha += offset;
	        		if (mCurrentAlpha < 0)
	        			mCurrentAlpha = 0;
	        		if (mCurrentAlpha > 0xFF)
	        			mCurrentAlpha = 0xFF;
	        		mFadingButton.getBackground().setAlpha(mCurrentAlpha);
	        		int textColor = mFadingButton.getTextColors().getDefaultColor();
	        		textColor = Color.argb(mCurrentAlpha, Color.red(textColor), Color.green(textColor), Color.blue(textColor));
	        		mFadingButton.setTextColor(textColor);
	        		// If the button is fully transparent - remove it from the screen
	        		if (mCurrentAlpha == 0)
	        			mFadingButton.setVisibility(View.GONE);
	        	}
	            return true;
	        }
		});    
		mCurrentAlpha = 0xFF;
    }

	public MyGridView(Context context) {
		super(context);
		Init(context);
	}

	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Init(context);
	}

	public MyGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		Init(context);
	}
	
	public boolean onTouchEvent(MotionEvent event) 
	{
		// Process touch event
		mDetector.onTouchEvent(event);
	    return super.onTouchEvent(event);
	}	

	public void setFadingButton(Button button)
	{
		mFadingButton = button;
	}
}
