package com.titanzhang;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.titanzhang.common.AutoBTUtil;

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
//			AutoBTUtil.registerPhoneReceivers(getApplicationContext());
		} else {
			enableCheckBox.setChecked(false);
			AutoBTUtil.unregisterPhoneReceivers(getApplicationContext());
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

	private class ServiceEnableCheckBoxChangeListener implements
			OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			if (isChecked) {
				logMessage("register receiver");
				AutoBTUtil.registerPhoneReceivers(getApplicationContext());
				AutoBTUtil.savePreference(AutoBTSettingsActivity.this, true);
				AutoBTUtil.showNotifyMessage(AutoBTSettingsActivity.this,
						getText(R.string.app_name),
						getText(R.string.MsgServiceRunning),
						false);
			} else {
				logMessage("unregister receiver");
				AutoBTUtil.unregisterPhoneReceivers(getApplicationContext());
				AutoBTUtil.savePreference(AutoBTSettingsActivity.this, false);
				AutoBTUtil.showNotifyMessage(AutoBTSettingsActivity.this,
						getText(R.string.app_name),
						getText(R.string.MsgServiceStopped),
						true);
			}
			AutoBTSettingsActivity.this.finish();
		}

	};

}