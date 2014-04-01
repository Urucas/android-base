package com.urucas.utils;

/**
* @copyright Urucas
* @license   Copyright (C) 2013. All rights reserved
* @version   Release: 1.0.0
* @link       http://urucas.com
* @developers Bruno Alassia, Pamela Prosperi
*/

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import com.urucas.android.base.BaseApplication;
import com.urucas.android.base.R;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.ViewDebug.FlagToString;
import android.widget.Toast;

public abstract class Utils {

	public static int intIDFromResource(Context context, String name, String type) {
		return context.getResources().getIdentifier(name, type, context.getPackageName());		
	}
		
	public static String stringFromResource(Context context, String name) {
		int id = context.getResources().getIdentifier(name, "string", context.getPackageName());
		return context.getResources().getString(id);
	}
	
	public static boolean isConnected(Context context) {
		ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService (Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) return true;
        
        return false;
	}
	
	public static String capitalize(String s) {
	    if (s!=null && s.length() > 0) {
	        return s.substring(0, 1).toUpperCase() + s.substring(1);
	    }
	    else return s;
	}
	
	public static String capitalize(String s, boolean allWords) {
		String[] words = s.split("\\s+");
		for(int i=0; i< words.length;i++) {
			words[i] = Utils.capitalize(words[i]);			
		}
		return Utils.join(words, " ");
	}
	
	public static String join(String[] s, String glue) {
		String res = "";
		for(int j=0; j<s.length;j++) {
			res += s[j];
			res += glue;
		}	
		return res;
	}
	
	public static boolean ValidPhoneNumber(String pn) {
		return pn.matches("\\d{8,}");
	}
	
	public static void Toast(Context context, String text) {	
		Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
    	toast.show();
	}
	
	public static void Toast(Context context, int resourceID) {
		Toast toast = Toast.makeText(context, resourceID, Toast.LENGTH_LONG);
    	toast.show();
	}
	
	public static void Toast(Context context, String text, int length) {
		Toast toast = Toast.makeText(context, text, length);
    	toast.show();
	}
	
	public static void Toast(Context context, int resourceID, int length) {
		Toast toast = Toast.makeText(context, resourceID, length);
    	toast.show();
	}
	
	public static void showOKAlert(Context context, String message) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		builder.setMessage(message)
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    	dialog.cancel();                 
               }
         });         
		 AlertDialog alert = builder.create();
		 alert.show();		 
	}
	
	public static void showOKAlert(Context context, int messageResourceID) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		builder.setMessage(messageResourceID)
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    	dialog.cancel();                 
               }
         });         
		 AlertDialog alert = builder.create();
		 alert.show();		 
	}
	
	
	public static Dialog showPreloader(Context context, String title, String message) {
		Dialog dialog = ProgressDialog.show(context, title, message, true);
    	dialog.setCancelable(true); 
    	return dialog;
	}
	
	
	public static void Log(String tag, String message) {
		android.util.Log.i(tag, message);
	}
	
	public static void Log(int number) {
		android.util.Log.i("URUCAS_DEBUGGING >>>", String.valueOf(number));
	}
	
	public static void Log(String message) {
		android.util.Log.i("URUCAS_DEBUGGING >>>", message);
	}
	
	public static void Log(float f) {
		android.util.Log.i("URUCAS_DEBUGGING >>>", String.valueOf(f));
	}
	
	public static String getRegionCodeFromSIM(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getSimCountryIso();
	}
	
	public static String getUUID(Context context) {
		TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tManager.getDeviceId();
	}
	
	public static String getEmailAccount(Context context) {
		try {
		Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
		Account[] accounts = AccountManager.get(context).getAccounts();
		for (Account account : accounts) {
		    if (emailPattern.matcher(account.name).matches()) {
		        String possibleEmail = account.name;		        					
				return possibleEmail;			   
		    }
		}
		}catch(Exception e) {}
		return null;
	}
	
	public static String getNickFromEmail(String email) {
		if(email == null || email.length() == 0) return "";
		
		String[] nick = email.split("@");
		return nick.length == 0 ? null : nick[0];
		
	}
	
	public static float dp2pixel(Context context, int dp) {
		Resources r = context.getResources();
    	return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());    	
	}
	
	public static float distanceBetween2Points(float vectorX0, float vectorY0, float vectorXP, float vectorYP) {
		float x = Math.abs(vectorXP - vectorX0);
		float y = Math.abs(vectorYP - vectorY0);
		return (float) Math.sqrt((x*x) + (y*y));
	}
	
	public static Date string2Date(String sd,String formato){
		//formato = "yyyy-MM-dd'T'HH:mm:ss'Z'";
		//"Thu Jul 11 12:40:18 GMT-03:00 2013"
		//"EE MMM dd HH:mm:ss z YYYY"
		SimpleDateFormat format = new SimpleDateFormat(formato); 
		try {
		    Date date = (Date) format.parse(sd);
		    return date;
		} catch (ParseException e) {
		    // TODO Auto-generated catch block  
		    e.printStackTrace();
		}
		return null;
	}

	public static String timeAgo(Context context, String fecha) {
		
		Date date = Utils.string2Date(fecha, "yyyy-MM-dd HH:mm:ss");
		
		long diff = new Date().getTime() - date.getTime();

		Resources r = context.getResources();

		String prefix = r.getString(R.string.time_ago_prefix);
		String suffix = r.getString(R.string.time_ago_suffix);

		double seconds = Math.abs(diff) / 1000;
		double minutes = seconds / 60;
		double hours = minutes / 60;
		double days = hours / 24;
		double years = days / 365;

		String words;

		if (seconds < 45) {
			words = r.getString(R.string.time_ago_seconds, Math.round(seconds));
		} else if (seconds < 90) {
			words = r.getString(R.string.time_ago_minute, 1);
		} else if (minutes < 45) {
			words = r.getString(R.string.time_ago_minutes, Math.round(minutes));
		} else if (minutes < 90) {
			words = r.getString(R.string.time_ago_hour, 1);
		} else if (hours < 24) {
			words = r.getString(R.string.time_ago_hours, Math.round(hours));
		} else if (hours < 42) {
			words = r.getString(R.string.time_ago_day, 1);
		} else if (days < 30) {
			words = r.getString(R.string.time_ago_days, Math.round(days));
		} else if (days < 45) {
			words = r.getString(R.string.time_ago_month, 1);
		} else if (days < 365) {
			words = r.getString(R.string.time_ago_months, Math.round(days / 30));
		} else if (years < 1.5) {
			words = r.getString(R.string.time_ago_year, 1);
		} else {
			words = r.getString(R.string.time_ago_years, Math.round(years));
		}

		StringBuilder sb = new StringBuilder();

		if (prefix != null && prefix.length() > 0) {
			sb.append(prefix).append(" ");
		}

		sb.append(words);

		if (suffix != null && suffix.length() > 0) {
			sb.append(" ").append(suffix);
		}

		return sb.toString().trim();
	}
	
	public static String getAge(String fec_nac){
	    
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
	    Calendar dob = Calendar.getInstance();
	    
		Date d = null;
		try {
			d = df.parse(fec_nac);
			
		}catch(Exception e) {}
		
	    Calendar today = Calendar.getInstance();
	    dob.setTime(d);

	    int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

	    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
	        age--; 
	    }
	    return String.valueOf(age);  
	}
	
	public static void openLink(String url){
		if(url.equals("")){ return; };
		
		if (!url.startsWith("http://") && !url.startsWith("https://"))
			   url = "http://" + url;
		
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		BaseApplication.getInstance().startActivity(browserIntent);
	}
}
