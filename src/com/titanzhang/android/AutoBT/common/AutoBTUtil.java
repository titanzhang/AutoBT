package com.titanzhang.android.AutoBT.common;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.titanzhang.R;
import com.titanzhang.android.AutoBT.AutoBTSettingsActivity;
import com.titanzhang.android.AutoBT.BroadcastReceiver.Manager.CallAnswerManager;
import com.titanzhang.android.AutoBT.BroadcastReceiver.Manager.PhoneStateManager;

public class AutoBTUtil {
	private static final String PREFERENCE_NAME = "com.titanzhang.AutoBT";
	private static final String PREF_ENABLE_SERVICE = "EnableService";
	private static final int PREF_MODE = Context.MODE_WORLD_WRITEABLE;

	public static void savePreference(Context context, boolean bEnableService) {
		SharedPreferences pref = context.getApplicationContext().getSharedPreferences(PREFERENCE_NAME, PREF_MODE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean(PREF_ENABLE_SERVICE, bEnableService);
		editor.commit();

	}

	public static boolean readPreference(Context context) {
		SharedPreferences pref = context.getApplicationContext().getSharedPreferences(PREFERENCE_NAME, PREF_MODE);
		return pref.getBoolean(PREF_ENABLE_SERVICE, false);
	}
	
	public static void showNotifyMessage(Context context, CharSequence title, CharSequence message, boolean canClear) {
		NotificationManager notifyManager = (NotificationManager)context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//		Notification notification = new Notification(R.drawable.icon, message, System.currentTimeMillis());
		Notification notification = new Notification();
		notification.icon = canClear?R.drawable.ic_stat_notify_disabled:R.drawable.ic_stat_notify_enabled;
		notification.tickerText = message;
		notification.flags = canClear?0:Notification.FLAG_NO_CLEAR;
		Intent notificationIntent = new Intent(context.getApplicationContext(), AutoBTSettingsActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context.getApplicationContext(), canClear?0:1, notificationIntent, 0);
		
		notification.setLatestEventInfo(context.getApplicationContext(), title, message, contentIntent);
		
		notifyManager.notify(1, notification);
	}
	
	public static boolean enableBluetooth(boolean bEnable) {
		BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
		if (btAdapter == null) {
			// Bluetooth not supported
			return false;
		}
		
		return bEnable?btAdapter.enable():btAdapter.disable();
	}
	
	public static void registerPhoneReceivers(Context context) {
		PhoneStateManager.getInstance().register(context.getApplicationContext());
		CallAnswerManager.getInstance().register(context.getApplicationContext());
	}
	
	public static void unregisterPhoneReceivers(Context context) {
		PhoneStateManager.getInstance().unregister(context.getApplicationContext());
		CallAnswerManager.getInstance().unregister(context.getApplicationContext());
	}
	
	public static void showToast(Context context, CharSequence message) {
		Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.show();
	}

}
