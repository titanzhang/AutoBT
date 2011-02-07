package com.titanzhang.android.AutoBT.BroadcastReceiver.Manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;

public abstract class AbstractBaseManager {
	protected void logMessage(CharSequence message) {
		Log.d("==[ "+getClassName()+" ]==", message.toString());
	}

	protected abstract String getClassName();
	protected abstract IntentFilter createIntentFilter();
	protected abstract BroadcastReceiver createReceiver();
	
	public void register(Context context) {
		context.getApplicationContext().registerReceiver(createReceiver(), createIntentFilter());
		logMessage("Register Receiver");
	}
	
	public void unregister(Context context) {
		try {
			context.getApplicationContext().unregisterReceiver(createReceiver());
			logMessage("Unregister Receiver");
		} catch (Exception e) {
			logMessage("Failed Unregister Receiver");
		}
	}
}
