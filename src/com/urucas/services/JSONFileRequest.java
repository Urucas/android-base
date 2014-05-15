package com.urucas.services;

/**
 * @copyright Urucas
 * @license   Copyright (C) 2013. All rights reserved
 * @version   Release: 1.0.0
 * @link       http://urucas.com
 * @developers Bruno Alassia, Pamela Prosperi
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.Log;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;


public class JSONFileRequest extends RequestTask{

	public JSONFileRequest(JSONRequestTaskHandler _jrsh) {
		super(_jrsh);		
	}

	private File file;
	
	public RequestTask setFile(File file){
		this.file = file;
		return this;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected String doInBackground(String... uri) {

		HttpClient httpclient = new DefaultHttpClient();
		
		// add request params
		StringBuffer url = new StringBuffer(uri[0])
		.append("?")
		.append(URLEncodedUtils.format(getParams(), "UTF-8"));

		HttpPost _post = new HttpPost(url.toString());
		HttpContext localContext = new BasicHttpContext();
		
		MultipartEntity _entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
		for(int index=0; index < getParams().size(); index++) {
                try {
					_entity.addPart(
							getParams().get(index).getName(), 
							new StringBody(getParams().get(index).getValue())
					);
				} catch (UnsupportedEncodingException e) {
				}
        }
        
		_entity.addPart("imagen", new FileBody(file));
	 	
		_post.setEntity(_entity);
/*
 * 
		InputStreamEntity reqEntity;
		try {
			reqEntity = new InputStreamEntity(new FileInputStream(this.file), -1);
			reqEntity.setContentType("binary/octet-stream");
			reqEntity.setChunked(true); // Send in multiple parts if needed
			
			_post.setEntity(reqEntity);
			
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		Log.i("url---->", url.toString());
		// add request headers
		ArrayList<NameValuePair> _headers = getHeaders();
		for (NameValuePair pair : _headers) {
			_post.addHeader(pair.getName(), pair.getValue());
		}
*/
		HttpResponse response;
		
		String responseString = null;
		// execute
		try {
			response = httpclient.execute(_post, localContext);
			StatusLine statusLine = response.getStatusLine();
			if(statusLine.getStatusCode() == HttpStatus.SC_OK){
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				responseString = out.toString();
				if(isCancelled()) {
					rsh.onError("Task cancel");
				}

			} else{
				//Closes the connection.
				response.getEntity().getContent().close();
				Log.i("status", String.valueOf(statusLine.getStatusCode()));
				throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			//rsh.onError(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			//rsh.onError(e.getMessage());		
		}
		return responseString;
	}

	protected void onPostExecute(String result) {
		//Log.i("api result",result);
		if(result == null) {
			jrsh.onError("Cant parse JSON");
			return;
		}
		try {
			Object json = new JSONTokener(result).nextValue();
			if (json instanceof JSONObject) {
				JSONObject response = new JSONObject(result);
				jrsh.onSuccess(response);
				return;
			}
			else if (json instanceof JSONArray) {
				JSONArray response = new JSONArray(result);
				jrsh.onSuccess(response);
				return;
			}
			jrsh.onError("Cant parse JSON");

		} catch (JSONException e) {			
			jrsh.onError(e.getMessage());
		}
	}

}
