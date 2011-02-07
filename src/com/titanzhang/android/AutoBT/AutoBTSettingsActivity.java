package com.titanzhang.android.AutoBT;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.titanzhang.android.AutoBT.Services.MainService;
import com.titanzhang.android.AutoBT.common.AutoBTUtil;

public class AutoBTSettingsActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		logMessage("onCreate");
		setContentView(R.layout.main);

		CheckBox enableCheckBox = (CheckBox) findViewById(R.id.ServiceEnableCB);
		
		// Check Service Status
		boolean perfEnabled = AutoBTUtil.readPreference(this);
		if (perfEnabled) {
			enableCheckBox.setChecked(true);
			startMainService();
		} else {
			enableCheckBox.setChecked(false);
			stopMainService();
		}
		enableCheckBox
				.setOnCheckedChangeListener(new ServiceEnableCheckBoxChangeListener());
	}

	@Override
	protected void onDestroy() {
		logMessage("onDestroy");
		super.onDestroy();
	}

	private void logMessage(String message) {
		Log.d("===[AutoBTSettingsActivity]===", message);
	}

	private void startMainService() {
		Intent serviceIntent = new Intent(getApplicationContext(), MainService.class);
		getApplicationContext().startService(serviceIntent);
	}
	
	private void stopMainService() {
		Intent serviceIntent = new Intent(getApplicationContext(), MainService.class);
		getApplicationContext().stopService(serviceIntent);
	}
	
	private class ServiceEnableCheckBoxChangeListener implements
			OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			if (isChecked) {
				logMessage("register receiver");
				AutoBTUtil.savePreference(AutoBTSettingsActivity.this, true);
				startMainService();
			} else {
				logMessage("unregister receiver");
				AutoBTUtil.savePreference(AutoBTSettingsActivity.this, false);
				stopMainService();
			}
			AutoBTSettingsActivity.this.finish();
		}

	};

}