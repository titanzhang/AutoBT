package com.titanzhang.BroadcastReceiver;

import com.titanzhang.AutoBTBootActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends AbstractBaseReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
			Intent activityIntent = new Intent(context, AutoBTBootActivity.class);
			activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.getApplicationContext().startActivity(activityIntent);
		}
	}

	@Override
	protected String getClassName() {
		return BootReceiver.class.getName();
	}
	
	
}
