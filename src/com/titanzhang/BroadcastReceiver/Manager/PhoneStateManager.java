package com.titanzhang.BroadcastReceiver.Manager;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.telephony.TelephonyManager;

import com.titanzhang.BroadcastReceiver.PhoneStateReceiver;

public class PhoneStateManager extends AbstractBaseManager {
	private static PhoneStateManager s_instance = new PhoneStateManager();
	
	private PhoneStateManager() {}
	
	public static PhoneStateManager getInstance() {
		return s_instance;
	}
	
	@Override
	protected String getClassName() {
		return PhoneStateManager.class.getName();
	}

	@Override
	protected IntentFilter createIntentFilter() {
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
		
		return intentFilter;
	}

	@Override
	protected BroadcastReceiver createReceiver() {
		return PhoneStateReceiver.getInstance();
	}
	
}
