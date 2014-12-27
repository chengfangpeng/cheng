package com.zhensan.client.service;


import com.youku.player.YoukuPlayerApplication;
import com.zhensan.client.util.CacheUtils;


public class MobileApplication extends YoukuPlayerApplication{
	
	
	private static MobileApplication mobileApplication;
	
	
	public static String TAG = "ZhenSan";
	public static CacheUtils cacheUtils;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mobileApplication = this;
		cacheUtils = CacheUtils.get(this);
	}



	
	
	
	

}
