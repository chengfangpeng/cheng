package com.zhensan.client.option.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhensan.client.R;
import com.zhensan.client.domain.UserInfo;
import com.zhensan.client.parentclass.ParentFragment;

public class UserInfoDetailFragment extends ParentFragment{
	private TextView info;
	private UserInfo userInfo;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		userInfo = (UserInfo) params[0];
		Log.d("TAG", userInfo.getName());
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	protected int getLayoutId() {
		return R.layout.user_info_detail_layout;
	}

	@Override
	protected void setupViews(View parentView) {
		info = (TextView) parentView.findViewById(R.id.info);
	}

	@Override
	protected void initialized() {
		info.setText(userInfo.toString());
		
	}

	@Override
	protected void threadTask() {
		
	}

}
