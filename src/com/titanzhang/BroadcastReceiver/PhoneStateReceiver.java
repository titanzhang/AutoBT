package com.titanzhang.BroadcastReceiver;

import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import com.titanzhang.common.AutoBTUtil;

public class PhoneStateReceiver extends AbstractBaseReceiver {
	private static PhoneStateReceiver s_instance = new PhoneStateReceiver();
	
	private PhoneStateReceiver() {}
	
	public static PhoneStateReceiver getInstance() {
		return s_instance;
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		TelephonyManager teleManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		int callState = teleManager.getCallState();

		switch (callState) {
		case TelephonyManager.CALL_STATE_IDLE:
			logMessage("CALL_STATE_IDLE: Disable Bluetooth");
			AutoBTUtil.enableBluetooth(false);
			break;
		case TelephonyManager.CALL_STATE_OFFHOOK:
			logMessage("CALL_STATE_OFFHOOK: Enable Bluetooth");
			AutoBTUtil.enableBluetooth(true);
			break;
		case TelephonyManager.CALL_STATE_RINGING:
			logMessage("CALL_STATE_RINGING: Enable Bluetooth");
			AutoBTUtil.enableBluetooth(true);
			break;
		default:
			logMessage("unknow state=" + callState);
			AutoBTUtil.enableBluetooth(false);
			break;
		}
	}

	@Override
	protected String getClassName() {
		return PhoneStateReceiver.class.getName();
	}
}
