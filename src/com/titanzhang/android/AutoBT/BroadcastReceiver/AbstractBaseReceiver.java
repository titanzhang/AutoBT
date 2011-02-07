package com.titanzhang.android.AutoBT.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.util.Log;

public abstract class AbstractBaseReceiver extends BroadcastReceiver {
	protected void logMessage(CharSequence message) {
		Log.d("==[ "+getClassName()+" ]==", message.toString());
	}

	protected abstract String getClassName();
}
