package com.urucas.android.base;

import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.urucas.android.base.activities.HomeActivity;
import com.urucas.android.base.controller.ApiController;
import com.urucas.android.base.model.Radio;
import com.urucas.utils.Utils;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.UriMatcher;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

public class BaseApplication extends Application {

	
	private static BaseApplication _instance;
	private static ApiController _apicontroller;
	
	public BaseApplication() {
		super();
		_instance = this;
	}

	public static BaseApplication getInstance() {
		return _instance;
	}
	
	public static ApiController getAPIController() {
		if(_apicontroller == null) {
			_apicontroller = new ApiController();
		}
		return _apicontroller;
	}
}
