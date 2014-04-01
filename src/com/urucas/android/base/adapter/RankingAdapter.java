package com.urucas.android.base.adapter;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import com.urucas.android.base.BaseApplication;
import com.urucas.android.base.R;
import com.urucas.android.base.model.Radio;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RankingAdapter extends ArrayAdapter<Radio> {

	public RankingAdapter(Context context, int resource, ArrayList<Radio> radios) {
		super(context, resource, radios);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	
		LayoutInflater inflater = (LayoutInflater) this.getContext()
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.adapter_radio_rankingitem, parent, false);
		
		Radio radio = getItem(position);
		return rowView;
	}
	
	public void addAll(ArrayList<Radio> radios) {
		for(int i=0; i<radios.size(); i++) {
			insert(radios.get(i), getCount());
		}
	}
}
