package com.test.gallery;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	// This f...ing google api returns 403 error for unknown reason... so, will use simply an array of URL's
//	private final String GOOGLE_API_KEY = "AIzaSyDTRdZYjdrUxcID16ZKCUJ7Lqq2m0wbx9s";
//	private final String GOOGLE_URL_PREFIX = "https://www.googleapis.com/customsearch/v1?";
//	private final String GOOGLE_URL_PARAMS = "q=oak&searchType=image&start=0&cx=016147794571967403915:9ru6plnixdu&key=" + GOOGLE_API_KEY;

	private final int IMAGES_COUNT = 50;

	private ImageAdapter mAdapter;
	private Button btnReset;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// :(((
		//GoogleRequestTask request = new GoogleRequestTask(this);
		//request.execute(GOOGLE_URL_PREFIX + GOOGLE_URL_PARAMS);
		
		// Set it all up
		MyGridView gl = (MyGridView) findViewById(R.id.gvPictures);
		mAdapter = new ImageAdapter(this);
		mAdapter.SetImageCount(IMAGES_COUNT);
		gl.setAdapter(mAdapter);
		
		btnReset = (Button)findViewById(R.id.btnReset);
		btnReset.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				mAdapter.Reset();
			}
		});
		btnReset.bringToFront();
		
		// Tell our MyGridView to manage btnReset's transparency
		gl.setFadingButton(btnReset);		

	}
	
}
