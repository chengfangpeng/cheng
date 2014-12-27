package com.zhensan.client.option.login;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.zhensan.client.R;
import com.zhensan.client.domain.UserInfo;
import com.zhensan.client.option.LeftMenuFragment;
import com.zhensan.client.option.homefragment.HomepageFragment;
import com.zhensan.client.parentclass.ParentFragment;
import com.zhensan.client.service.ClientTask;
import com.zhensan.client.service.MobileApplication;
import com.zhensan.client.util.CacheUtils;

public class LoginFragment extends ParentFragment {

	private WebView login_WebView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contextView = super.onCreateView(inflater, container,
				savedInstanceState);

		return contextView;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.login_fragment_layout;
	}

	@Override
	protected void setupViews(View parentView) {
		login_WebView = (WebView) parentView.findViewById(R.id.login_webview);
		login_WebView.getSettings().setJavaScriptEnabled(true);
		// login_WebView.getSettings().setBlockNetworkImage(false);
		// 设置自动加载图片
		// login_WebView.getSettings().setLoadsImagesAutomatically(true);
		// 增大webview中字体
		// 设置自动手动改变字体大小
		// login_WebView.getSettings().setBuiltInZoomControls(true);
		String url = "https://openapi.youku.com/v2/oauth2/authorize?client_id=79e4f5b64c881b20&response_type=token&redirect_uri=http://i.youku.com/zhongfaciji";
		login_WebView.loadUrl(url);
		login_WebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url); // 在当前的webview中跳转到新的url
				Log.d("TAG", url);

				String access_token = url.substring(
						url.indexOf("access_token=") + 13,
						url.indexOf("&state="));
				MobileApplication.cacheUtils.put("access_token", access_token,
						CacheUtils.TIME_DAY * 30);
				threadTask();
				

				return true;
			}
		});
		EditText text = (EditText) parentView.findViewById(R.id.edittext);
	}

	@Override
	protected void initialized() {

	}

	@Override
	protected void threadTask() {
		String access_token = MobileApplication.cacheUtils
				.getAsString("access_token");
		String url = "https://openapi.youku.com/v2/users/myinfo.json?client_id=79e4f5b64c881b20&access_token="
				+ access_token;
		JsonObjectRequest jsonRequest = new JsonObjectRequest(url, null,
				new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
					if(response != null){
						
						try {
							MobileApplication.cacheUtils.put("userInfo", response.toString(), CacheUtils.TIME_DAY*30);
							Gson gson = new Gson();
							Log.d("TAG", "用户的优酷信息" + response.toString());
							UserInfo userInfo = gson.fromJson(response.toString(), UserInfo.class);
							BitmapUtils bitmapUtils = new BitmapUtils(getActivity());
							bitmapUtils.display(LeftMenuFragment.leftmenu_user_avatar, userInfo.getAvatar());
							LeftMenuFragment.leftmenu_user_name.setText(userInfo.getName());
							ParentFragment fragment = new HomepageFragment();
							switchFragment(fragment, "", params);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else{
						
						Toast.makeText(getActivity(), "登陆失败", Toast.LENGTH_SHORT).show();
					}
						
					}

				
				},new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.d("TAG", error.getMessage());
					}
				});
		
		ClientTask.getInstance(getActivity()).addToRequestQueue(jsonRequest);
	}

}
