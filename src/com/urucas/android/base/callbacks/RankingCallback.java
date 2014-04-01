package com.urucas.android.base.callbacks;

import java.util.ArrayList;

import com.urucas.android.base.model.Radio;

public interface RankingCallback {

	public void onSuccess(ArrayList<Radio> radios);
	public void onError(String message);
}
