package com.titanzhang.android.AutoBT.BroadcastReceiver;

import android.content.Context;
import android.content.Intent;

import com.titanzhang.android.AutoBT.Services.MainService;
import com.titanzhang.android.AutoBT.common.AutoBTUtil;

public class BootReceiver extends AbstractBaseReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
			// Check Service Status
			boolean perfEnabled = AutoBTUtil.readPreference(context.getApplicationContext());
			if (perfEnabled) {
				Intent serviceIntent = new Intent(context, MainService.class);
				context.startService(serviceIntent);
			}
		}
	}

	@Override
	protected String getClassName() {
		return BootReceiver.class.getName();
	}
	
	
}
