package com.titanzhang;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.titanzhang.BroadcastReceiver.Manager.PhoneStateManager;
import com.titanzhang.common.AutoBTUtil;

public class AutoBTBootActivity extends Activity {
	public static final String ACTION_BOOT_LOAD = "com.titanzhang.autobt.ACTION_BOOT_LOAD";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        boolean enableService = AutoBTUtil.readPreference(this);
    	if (enableService) {
    		AutoBTUtil.registerPhoneReceivers(getApplicationContext());
			AutoBTUtil.showNotifyMessage(this, getText(R.string.app_name), getText(R.string.MsgServiceRunning), false);
			
			Toast toast = Toast.makeText(this, getText(R.string.MsgBootEnable), Toast.LENGTH_SHORT);
			toast.show();
    	}
        finish();
	}

}
