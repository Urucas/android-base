package com.urucas.android.base.parser;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.urucas.android.base.model.Radio;

public abstract class RadiosParser {

	public static ArrayList<Radio> parse(JSONArray result) {
		
		ArrayList<Radio> radios = new ArrayList<Radio>();
		for(int i=0; i < result.length(); i++) {
			try {
				JSONObject jsonObject = result.getJSONObject(i);
				Radio radio = RadioParser.parse(jsonObject);
				if(radio != null) {
					radios.add(radio);
				}
			} catch (JSONException e) { }
		}
		return radios;
	}
}
