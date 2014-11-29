package com.test.gallery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	
	private final Context mContext;
	
	private int mCount;
	private LayoutInflater mInflater;
	
    private List<String> items = new ArrayList<String>();

    public ImageAdapter(Context context) 
    {
    	mContext = context;
    	mInflater = LayoutInflater.from(context);
    	// Add all URL's to our list
    	Collections.addAll(items, Urls.URLS);
    	mCount = items.size();
    }

    @Override 
    public View getView(int position, View convertView, ViewGroup parent) 
    {
    	MySquareLayout view = (MySquareLayout) convertView;
    	if (view == null) 
    	{
    		// Inflate the view if showing it first time
    		view = (MySquareLayout)mInflater.inflate(R.layout.item, parent, false);
    		// Tag ImageView for future access
    		view.setTag(R.id.image, view.findViewById(R.id.image));
    	}
    	// Get corresponding URL
    	String url = items.get(position);
    	// Download image and show it in corresponding ImageView
    	Picasso.with(mContext).load(url).placeholder(R.drawable.downloading).error(R.drawable.error).into((ImageView)view.getTag(R.id.image));
    	return view;
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    
    public void Reset()
    {
    	// Shuffle URL's and reload AdapterView's items
    	Collections.shuffle(items);
    	notifyDataSetChanged();
    }
    
    public void SetImageCount(int count)
    {
    	// Restrict items count
    	if (items.size() < count)
    		mCount = items.size();
    	else
    		mCount = count;
    }

}
