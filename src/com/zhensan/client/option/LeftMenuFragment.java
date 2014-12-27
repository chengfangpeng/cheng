package com.zhensan.client.option;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.zhensan.client.R;
import com.zhensan.client.adapter.LeftmenuAdater;
import com.zhensan.client.domain.UserInfo;
import com.zhensan.client.parentclass.ParentFragment;
import com.zhensan.client.service.MobileApplication;


public class LeftMenuFragment extends ParentFragment{
	
	private ListView leftmenu_ListView;
	private LeftmenuAdater adapter;
	private Callbacks callbacks = defaultCallbacks;
	private RelativeLayout userLogin_Layout;
	public static ImageView leftmenu_user_avatar;
	public static TextView leftmenu_user_name;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contextView = super.onCreateView(inflater, container,
				savedInstanceState);
		return contextView;
	}
	private OnClickListener leftmenu_click_Listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.leftmenu_user_login_layout:
				callbacks.onUserInfoClick(v);
				break;

			default:
				break;
			}
			
		}
	};
	private OnItemClickListener menuListViewListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> adapterView, View view, int i,
				long l) {
			switch (adapterView.getId()) {
			case R.id.leftmenu_ListView:
				callbacks.onLeftMenuClick(adapterView, view, i,
						adapterView.getItemAtPosition(i));
				Log.d("TAG", "点击的是第几个菜单" + i);
				break;
			default:
				break;
			}
		}

	};
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}
		callbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		callbacks = defaultCallbacks;
	}
	private static Callbacks defaultCallbacks = new Callbacks() {

		@Override
		public void onLeftMenuClick(AdapterView<?> adapterView, View view,
				int i, Object data) {
		}

		@Override
		public void onUserInfoClick(View v) {
			
		}

	};
	public interface Callbacks {

		/**
		 * CommonMenu列表项点击事件回掉接口
		 * 
		 * @param adapterView
		 * @param view
		 * @param i
		 */
		public void onLeftMenuClick(AdapterView<?> adapterView, View view,
				int i, Object data);
		
		/**
		 * 用户登录
		 * 
		 * @param v
		 */
		public void onUserInfoClick(View view);

	}

	@Override
	protected int getLayoutId() {
		return R.layout.leftmenu;
	}

	@Override
	protected void setupViews(View parentView) {
		leftmenu_ListView = (ListView) parentView.findViewById(R.id.leftmenu_ListView);
		userLogin_Layout = (RelativeLayout) parentView.findViewById(R.id.leftmenu_user_login_layout);
		leftmenu_user_avatar = (ImageView) parentView.findViewById(R.id.leftmenu_usericon_imageview);
		leftmenu_user_name = (TextView) parentView.findViewById(R.id.leftmenu_username_TextView);
	}

	@Override
	protected void initialized() {
		String[] titles = getResources().getStringArray(R.array.leftmenu_title_list);
		adapter = new LeftmenuAdater(getActivity(), titles, null);
		leftmenu_ListView.setAdapter(adapter);
		leftmenu_ListView.setOnItemClickListener(menuListViewListener);
		userLogin_Layout.setOnClickListener(leftmenu_click_Listener);
		String userStr = MobileApplication.cacheUtils.getAsString("userInfo");
		
		if(userStr != null){
			UserInfo userInfo = new Gson().fromJson(userStr.toString(), UserInfo.class);
			new BitmapUtils(getActivity()).display(leftmenu_user_avatar, userInfo.getAvatar());
			leftmenu_user_name.setText(userInfo.getName());
			
		}
		
	}

	@Override
	protected void threadTask() {
		
	}

}
