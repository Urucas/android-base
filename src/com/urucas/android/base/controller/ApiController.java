package com.urucas.android.base.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.util.Log;

import com.urucas.android.base.BaseApplication;
import com.urucas.android.base.R;
import com.urucas.android.base.callbacks.RankingCallback;
import com.urucas.android.base.model.Radio;
import com.urucas.android.base.parser.RadioParser;
import com.urucas.android.base.parser.RadiosParser;
import com.urucas.android.base.parser.RankingParser;
import com.urucas.services.JSONRequestTask;
import com.urucas.services.JSONRequestTaskHandler;
import com.urucas.services.RequestTask;
import com.urucas.services.RequestTaskHandler;
import com.urucas.utils.Utils;

public class ApiController {

	private static String BASE_URL = "http://m.raddios.com/api";
	
	public void getRanking(int order, int page, final RankingCallback callback) {
	
		if(!isConnected()) {
			Utils.Toast(BaseApplication.getInstance(), R.string.no_connection);
			return;
		}
		
		String url = BASE_URL + "/getranking";
		try {
			new JSONRequestTask(new JSONRequestTaskHandler() {

				@Override
				public void onSuccess(JSONObject result) {
					try {
						if(result.has("error")) {
							callback.onError(result.getString("error"));
							return;
						}
						// parseo el json del ranking
						ArrayList<Radio> ranking = RankingParser.parse(result.getJSONArray("radios"));
						callback.onSuccess(ranking);
						
					} catch (JSONException e) {
						callback.onError("error parseando");
					}
				}

				@Override
				public void onError(String message) {
					callback.onError(message);
				}

				@Override
				public void onSuccess(JSONArray result) {
				}

			}).execute(url);
			
		} catch (Exception e) {
			callback.onError("error llamando a la api");
		}
	}
	
	private boolean isConnected() {
		return Utils.isConnected(BaseApplication.getInstance().getApplicationContext());
	}

	

}
