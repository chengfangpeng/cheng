package com.zhensan.client.option.homefragment;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.ab.view.slidingmenu.SlidingFragmentActivity;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.zhensan.client.R;
import com.zhensan.client.domain.UserInfo;
import com.zhensan.client.option.SlidingMenuControlActivity;
import com.zhensan.client.option.player.PlayerActivity;
import com.zhensan.client.parentclass.ParentFragment;
import com.zhensan.client.service.ClientTask;
import com.zhensan.client.service.MobileApplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class HomepageFragment extends ParentFragment{
	
	private ImageView home_avatar;
	private TextView home_name;
	private BitmapUtils bitmapUtils;
	private UserInfo userInfo;
	private TextView home_description;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		bitmapUtils = new BitmapUtils(getActivity());
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		Intent i = new Intent(SlidingMenuControlActivity.activity,PlayerActivity.class);
//		i.putExtra("vid", "XODUyNDE3NTg4");
//		startActivity(i);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	@Override
	public void onStart() {
		super.onStart();
		threadTask();
	}
	

	@Override
	protected int getLayoutId() {
		return R.layout.homepage;
	}

	@Override
	protected void setupViews(View parentView) {
		home_avatar = (ImageView) parentView.findViewById(R.id.home_avatar);
		home_name = (TextView) parentView.findViewById(R.id.home_name);
		home_description = (TextView) parentView.findViewById(R.id.home_description);
	}

	@Override
	protected void initialized() {
		SlidingMenuControlActivity.title_TextView.setText("天云首页");
		
	}

	@Override
	protected void threadTask() {
		
		Log.d("TAG", "进入post请求");
		String access_token = MobileApplication.cacheUtils.getAsString("access_token");
		String url = "https://openapi.youku.com/v2/users/show.json?client_id=79e4f5b64c881b20&user_id=120167468&access_token=" + access_token ;
		
		JsonObjectRequest jsonRequest = new JsonObjectRequest(
				url, 
				null, new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d("TAG", "post" + response.toString());
						Gson gson = new Gson();
						userInfo = gson.fromJson(response.toString(), UserInfo.class);
						bitmapUtils.display(home_avatar, userInfo.getAvatar_large());
						home_name.setText(userInfo.getName());
						home_description.setText(userInfo.getDescription());
						
					}
				},  new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.d("TAG", "post" + error.toString());
					}
				});
		
		ClientTask.getInstance(getActivity()).addToRequestQueue(jsonRequest);
		
	}

}
