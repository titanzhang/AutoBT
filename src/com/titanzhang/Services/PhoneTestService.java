package com.titanzhang.Services;

import com.titanzhang.AutoBTSettingsActivity;
import com.titanzhang.R;
import com.titanzhang.BroadcastReceiver.PhoneStateReceiver;
import com.titanzhang.R.drawable;
import com.titanzhang.R.string;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.telephony.TelephonyManager;
import android.util.Log;

public class PhoneTestService extends Service {
	private PhoneStateReceiver m_phoneStateReceiver;
	private boolean m_bStarted = false;
	private IBinder m_binder = new PhoneTestServiceBind();
	
	public PhoneTestService() {
		m_phoneStateReceiver = PhoneStateReceiver.getInstance();
		debugMessage("new instance");
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return m_binder;
	}

	public class PhoneTestServiceBind extends Binder {
		public PhoneTestService getService() {
			return PhoneTestService.this;
		}
	}

	public boolean isStarted() {
		return m_bStarted;
	}
	
	@Override
	public void onCreate() {
		// Start receiving phone broadcasts
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
		
		debugMessage("register receiver");
		this.getApplicationContext().registerReceiver(m_phoneStateReceiver, intentFilter);
		showNotifyMessage(this.getText(R.string.MsgServiceRunning), true);
		m_bStarted = true;
	}

	@Override
	public void onDestroy() {
		// Stop receiving phone broadcasts
		debugMessage("unregister receiver");
		this.getApplicationContext().unregisterReceiver(m_phoneStateReceiver);
		showNotifyMessage(this.getText(R.string.MsgServiceStopped), false);
		m_bStarted = false;
	}
	
	private void showNotifyMessage(CharSequence message, boolean isStart) {
		NotificationManager notifyManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.icon, message, System.currentTimeMillis());
		notification.flags = isStart?Notification.FLAG_NO_CLEAR:0;
		Intent notificationIntent = new Intent(this, AutoBTSettingsActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, isStart?1:0, notificationIntent, 0);
		
		notification.setLatestEventInfo(this.getApplicationContext(), this.getText(R.string.app_name), message, contentIntent);
		
		notifyManager.notify(1, notification);
	}
	
	private void debugMessage(String message) {
		Log.d("===[PhoneTestService]===", message);
	}
}
