package com.titanzhang.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.titanzhang.R;
import com.titanzhang.common.AutoBTUtil;

public class MainService extends Service {
	private boolean m_bStarted = false;
	private IBinder m_binder = new MainServiceBind();
	
	public MainService() {
		debugMessage("new instance");
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return m_binder;
	}

	public class MainServiceBind extends Binder {
		public MainService getService() {
			return MainService.this;
		}
	}

	public boolean isStarted() {
		return m_bStarted;
	}
	
	@Override
	public void onCreate() {
		debugMessage("register receiver");

		// Start receiving phone broadcasts
		AutoBTUtil.registerPhoneReceivers(getApplicationContext());
		
		AutoBTUtil.showNotifyMessage(getApplicationContext(), getText(R.string.app_name), getText(R.string.MsgServiceRunning), false);
		AutoBTUtil.showToast(getApplicationContext(), getText(R.string.MsgServiceEnabled));
		
		m_bStarted = true;
	}

	@Override
	public void onDestroy() {
		debugMessage("unregister receiver");

		// Stop receiving phone broadcasts
		AutoBTUtil.unregisterPhoneReceivers(getApplicationContext());
		AutoBTUtil.showNotifyMessage(getApplicationContext(), getText(R.string.app_name), getText(R.string.MsgServiceStopped), true);
		AutoBTUtil.showToast(getApplicationContext(), getText(R.string.MsgServiceDisabled));

		m_bStarted = false;
	}
	
	private void debugMessage(String message) {
		Log.d(MainService.class.getName(), message);
	}
}
