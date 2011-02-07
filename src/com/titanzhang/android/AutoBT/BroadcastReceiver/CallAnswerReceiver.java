package com.titanzhang.android.AutoBT.BroadcastReceiver;

import android.content.Context;
import android.content.Intent;

import com.titanzhang.android.AutoBT.common.AutoBTUtil;

public class CallAnswerReceiver extends AbstractBaseReceiver {
	private static CallAnswerReceiver s_instance = new CallAnswerReceiver();
	
	private CallAnswerReceiver() {}
	
	public static CallAnswerReceiver getInstance() {
		return s_instance;
	}
	
	@Override
	protected String getClassName() {
		return CallAnswerReceiver.class.getName();
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (action.equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
			logMessage("Making Call: Enable Bluetooth");
			AutoBTUtil.enableBluetooth(true);
		}
	}

}
