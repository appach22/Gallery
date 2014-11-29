package com.test.gallery;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class MySquareLayout extends FrameLayout {

	public MySquareLayout(Context context) {
		super(context);
	}

	public MySquareLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MySquareLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // Make height equal to width
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}
