package com.test.gallery;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.AsyncTask;

// For now it is useless part of code 

public class GoogleRequestTask extends AsyncTask<String, Void, ArrayList> {

	private static final int CONN_TIMEOUT = 10000;
	
	Context mContext;
	
	GoogleRequestTask(Context context)
	{
		mContext = context;
	}
 
	@Override
	protected ArrayList doInBackground(String... params) {
		String url = params[0];
		HttpResponse response = null;
		int cnt = 0;
		boolean retry = true;
		while (retry)
		{
			try
			{
				HttpParams httpParams = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParams, CONN_TIMEOUT);
				HttpConnectionParams.setSoTimeout(httpParams, CONN_TIMEOUT);
				HttpClient client = new DefaultHttpClient(httpParams);
		
				HttpGet httpRequest = new HttpGet(url);
				response = client.execute(httpRequest);
				if (response == null)
					//return new ServerResponse(SERVER_RESPONSE_ERROR, Settings.dbid, null);
					return null;
				retry = false;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				if (cnt++ >=3)
					return null;
				else
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
			}
		}
		String res = "";
		try {
			res = EntityUtils.toString(response.getEntity());
			System.out.println(res);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
