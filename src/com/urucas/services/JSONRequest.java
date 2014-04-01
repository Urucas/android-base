package com.urucas.services;

/**
* @copyright Urucas
* @license   Copyright (C) 2013. All rights reserved
* @version   Release: 1.0.0
* @link       http://urucas.com
* @developers Bruno Alassia, Pamela Prosperi
*/

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONRequest {

	public static void execute(String uri, JSONRequestTaskHandler rsh) {
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		String responseString = null;
		
		try {
			response = httpclient.execute(new HttpGet(uri));
			StatusLine statusLine = response.getStatusLine();
			if(statusLine.getStatusCode() == HttpStatus.SC_OK){
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				responseString = out.toString();
				
			} else{
				//Closes the connection.
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (ClientProtocolException e) {
			rsh.onError(e.getMessage());
		} catch (IOException e) {
			rsh.onError(e.getMessage());		
		}
		try {
			JSONObject response1 = new JSONObject(responseString);
			rsh.onSuccess(response1);
			
		} catch (JSONException e) {			
			rsh.onError(e.getMessage());
		}		
	}
}
