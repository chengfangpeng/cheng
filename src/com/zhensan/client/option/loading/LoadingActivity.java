package com.zhensan.client.option.loading;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.ab.view.slidingmenu.SlidingFragmentActivity;
import com.zhensan.client.parentclass.ParentActivity;
import com.zhensan.client.service.MobileApplication;

public class LoadingActivity extends ParentActivity{
	
	private SharedPreferences sPreferences;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sPreferences = getSharedPreferences(MobileApplication.TAG, MODE_PRIVATE);
		threadTask();
		intentToMain();

	}

	private void intentToMain() {
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				
			}
		}, 2000);
		
		
		
	}

	@Override
	protected int getLayoutId() {
		return 0;
	}

	@Override
	protected void setupViews() {
		
	}

	@Override
	protected void initialized() {
		
	}

	@Override
	protected void threadTask() {
		
	}

}
