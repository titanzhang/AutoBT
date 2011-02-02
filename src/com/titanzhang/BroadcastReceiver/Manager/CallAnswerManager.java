package com.titanzhang.BroadcastReceiver.Manager;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;

import com.titanzhang.BroadcastReceiver.CallAnswerReceiver;

public class CallAnswerManager extends AbstractBaseManager {
	private static CallAnswerManager s_instance = new CallAnswerManager();
	
	private CallAnswerManager() {}
	
	public static CallAnswerManager getInstance() {
		return s_instance;
	}
	
	@Override
	protected String getClassName() {
		return CallAnswerManager.class.getName();
	}

	@Override
	protected IntentFilter createIntentFilter() {
		IntentFilter intentFilter = new IntentFilter();
//		intentFilter.addAction(Intent.ACTION_ANSWER);
		intentFilter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
		
		return intentFilter;
	}

	@Override
	protected BroadcastReceiver createReceiver() {
		return CallAnswerReceiver.getInstance();
	}

}
