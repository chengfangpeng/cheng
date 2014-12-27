package com.zhensan.client.option;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ab.view.slidingmenu.SlidingFragmentActivity;
import com.ab.view.slidingmenu.SlidingMenu;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.punchbox.report.ReportData;
import com.zhensan.client.R;
import com.zhensan.client.domain.PlayList;
import com.zhensan.client.domain.UserInfo;
import com.zhensan.client.option.LeftMenuFragment;
import com.zhensan.client.option.homefragment.HomepageFragment;
import com.zhensan.client.option.login.LoginFragment;
import com.zhensan.client.option.login.UserInfoDetailFragment;
import com.zhensan.client.option.videoitem.VideoItemFramgent;
import com.zhensan.client.parentclass.ParentFragment;
import com.zhensan.client.service.ClientTask;
import com.zhensan.client.service.MobileApplication;

public class SlidingMenuControlActivity extends SlidingFragmentActivity
		implements LeftMenuFragment.Callbacks {
	public static SlidingMenuControlActivity activity;
	private SlidingMenu slidingMenu;
	public static String KEY = "ParamsKey";
	private static long back_pressed;
	private ParentFragment contentFragment;
	private PlayList playList;
	private Map<String, PlayList> playLists;
	public static TextView title_TextView;
	private UserInfo userInfo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		activity = this;
		super.onCreate(savedInstanceState);

		setContentView(R.layout.slidingmenu_control_framelayout);
		
		title_TextView = (TextView) findViewById(R.id.main_header_text);
		// sliding menu
		slidingMenu = getSlidingMenu();
		slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		slidingMenu.setShadowDrawable(R.drawable.leftmenu_black_shadow_bg);
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

		slidingMenu.setSecondaryMenu(R.layout.rightmenu_framelayout);
		slidingMenu
				.setSecondaryShadowDrawable(R.drawable.rightmenu_black_shadow_bg);
		setBehindContentView(R.layout.leftmenu_framelayout);
		FragmentTransaction t = this.getSupportFragmentManager()
				.beginTransaction();
		Fragment leftMenuFragment = new LeftMenuFragment();
		Fragment rightMenuFragment = new RightMenuFragment();
		t.replace(R.id.leftmenu_FrameLayout, leftMenuFragment);
		t.replace(R.id.rightmenu_FrameLayout, rightMenuFragment);
		t.commit();
		getPlayList();
		Gson gson = new Gson();
		String userStr = MobileApplication.cacheUtils.getAsString("userInfo");
		
		if(userStr != null){
			userInfo = gson.fromJson(userStr.toString(), UserInfo.class);
			
		}
		contentFragment = new HomepageFragment();
		title_TextView.setText("天云首页");
		 switchContent(contentFragment, false,
		 "测试SlidingMenuControlActivity传来的数据");

	}

	private void getPlayList() {

		ProgressBar pb = new ProgressBar(activity);

		JsonObjectRequest jsonRequest = new JsonObjectRequest(
				"https://openapi.youku.com/v2/playlists/by_user.json?client_id=79e4f5b64c881b20&user_name=%E5%A4%A9%E4%BA%91%E8%A7%A3%E8%AF%B4",
				null, new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d("TAG", response.toString());
						Gson gson = new Gson();
						playLists = new HashMap<String, PlayList>();
						try {
							JSONArray jsonArray = (JSONArray) response
									.get("playlists");
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject jsonObject = (JSONObject) jsonArray
										.get(i);
								PlayList playList = gson.fromJson(jsonArray
										.get(i).toString(), PlayList.class);

								playLists.put(jsonObject.getString("id"),
										playList);
							}

						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {

					}
				});
		ClientTask.getInstance(activity).addToRequestQueue(jsonRequest);

	}

	public void switchContent(ParentFragment fragment, boolean addToBackStack,
			Object... params) {
		if (fragment != null) {
			if (params != null) {
				Bundle args = new Bundle();
				args.putSerializable(SlidingMenuControlActivity.KEY, params);
				if (fragment.getArguments() == null) {
					fragment.setArguments(args);
				}

			} else {
				Log.i(this.getClass().getSimpleName(), "目标ParentFragment未传递参数");
			}
			FragmentTransaction t = getSupportFragmentManager()
					.beginTransaction();
			t.replace(R.id.slidingmenu_control_FrameLayout, fragment);
			if (addToBackStack) {
				t.addToBackStack(fragment.getClass().getSimpleName());
			} else {
				// 给内容Fragment赋值，并在onSaveInstanceState时保存这个Fragment
				contentFragment = fragment;
			}
			t.commit();
		} else {
			Log.e(this.getClass().getSimpleName(), "目标ParentFragment为空");
		}
	}

	@Override
	public void onLeftMenuClick(AdapterView<?> adapterView, View view, int i,
			Object data) {

		slidingMenu.showContent();
		int backStackCount = getSupportFragmentManager()
				.getBackStackEntryCount();
		System.out.println("---onLeftMenuClick-backStackCount---"
				+ backStackCount);
		if (backStackCount > 0) {
			onBackKeyDown(backStackCount, null);
		}
		ParentFragment fragment = null;
		switch (i) {
		case 0:
			fragment = new HomepageFragment();
			switchContent(fragment, true);
			break;
		case 1:
			fragment = new VideoItemFramgent();
			title_TextView.setText("天梯高端局");
			switchContent(fragment, true, "天梯高端局", playLists.get("21300543"));
			break;
		case 2:
			fragment = new VideoItemFramgent();
			title_TextView.setText("百大经典战役");
			switchContent(fragment, true, "百大经典战役", playLists.get("22951546"));
			break;
		case 3:
			fragment = new VideoItemFramgent();
			title_TextView.setText("第一视角");
			switchContent(fragment, true, "第一视角", playLists.get("21300622"));
			break;
		case 4:
			fragment = new VideoItemFramgent();
			title_TextView.setText("最新比赛");
			switchContent(fragment, true, "最新比赛", playLists.get("21300555"));
			break;
		case 5:
			fragment = new VideoItemFramgent();
			title_TextView.setText("每周精彩镜头");
			switchContent(fragment, true, "每周精彩镜头", playLists.get("18603514"));
			break;
		case 6:
			fragment = new VideoItemFramgent();
			title_TextView.setText("dota版真三");
			switchContent(fragment, true, "dota版真三", playLists.get("23152329"));
			break;
		case 7:
			fragment = new VideoItemFramgent();
			title_TextView.setText("精彩镜头TOP5");
			switchContent(fragment, true, "精彩镜头TOP5", playLists.get("20472976"));
			break;
		case 8:
			fragment = new VideoItemFramgent();
			title_TextView.setText("谁的操作更诡异");
			switchContent(fragment, true, "谁的操作更诡异", playLists.get("19221093"));
			break;
		case 9:
			fragment = new VideoItemFramgent();
			title_TextView.setText("真三小技巧");
			switchContent(fragment, true, "真三小技巧", playLists.get("22228295"));
			break;
		default:
			break;
		}

	}

	@Override
	public void onUserInfoClick(View view) {
		slidingMenu.showContent();
		int backStackCount = getSupportFragmentManager()
				.getBackStackEntryCount();
		System.out.println("---onLeftMenuClick-backStackCount---"
				+ backStackCount);
		if (backStackCount > 0) {
//			onBackKeyDown(backStackCount, null);
		}
		ParentFragment fragment = null;
		switch (view.getId()) {
		case R.id.leftmenu_user_login_layout:
			
			
			
			if(userInfo == null){
				fragment = new LoginFragment();
				title_TextView.setText("优酷账户登陆");
				switchContent(fragment, true);
			}else{
				
				
				fragment = new UserInfoDetailFragment();
				title_TextView.setText("用户详情");
				switchContent(fragment, true, userInfo);
			}
			
			break;

		default:
			break;
		}

	}

	public boolean onBackKeyDown(int backStackCount, KeyEvent event) {
		return getSupportFragmentManager().popBackStackImmediate();
	}

}
