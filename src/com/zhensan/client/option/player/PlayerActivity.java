package com.zhensan.client.option.player;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.youku.player.ApiManager;
import com.youku.player.VideoQuality;
import com.youku.player.base.YoukuBasePlayerActivity;
import com.youku.player.base.YoukuPlayer;
import com.youku.player.base.YoukuPlayerView;
import com.youku.player.module.PlayVideoInfo;
import com.zhensan.client.R;

public class PlayerActivity extends YoukuBasePlayerActivity{
	private YoukuPlayerView mYoukuPlayerView;
	private String vid;
	private Button btn_standard,btn_hight,btn_super,btn_1080;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.player);
		
		iniView();
		
		Intent i = getIntent();
		String id = i.getStringExtra("vid");
		
		if(TextUtils.isEmpty(id)){
			vid = "XODUyNDE3NTg4";
		}else{
			vid = id;
		}
		
		Log.d("sgh","vid = " + id) ;
		
		mYoukuPlayerView = (YoukuPlayerView) this.findViewById(R.id.full_holder);

		mYoukuPlayerView.initialize(this);
	}

	@Override
	public void setPadHorizontalLayout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInitializationSuccess(YoukuPlayer player) {
		// TODO Auto-generated method stub
		addPlugins();
		Log.d("sgh","vid2  = " + vid) ;
		player.playVideo(vid);
		//XNzQ3NjcyNDc2
		//XNzQ3ODU5OTgw
		//XNzUyMzkxMjE2
		//XNzU5MjMxMjcy  加密视频
		//XNzYxNzQ1MDAw 万万没想到
	}

	@Override
	public void onFullscreenListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSmallscreenListener() {
		// TODO Auto-generated method stub
		
	}

	
	private void iniView(){
		btn_standard = (Button)this.findViewById(R.id.biaoqing);
		btn_hight = (Button)this.findViewById(R.id.gaoqing);
		btn_super = (Button)this.findViewById(R.id.chaoqing);
		btn_1080 = (Button)this.findViewById(R.id.most);
		
		btn_standard.setOnClickListener(listener);
		btn_hight.setOnClickListener(listener);
		btn_super.setOnClickListener(listener);
		btn_1080.setOnClickListener(listener);
	}
	
	public View.OnClickListener listener = new View.OnClickListener() {
		
		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch(view.getId()){
			case R.id.biaoqing:
				change(VideoQuality.STANDARD);
				break;
			case R.id.gaoqing:
				change(VideoQuality.HIGHT);
				break;
			case R.id.chaoqing:
				change(VideoQuality.SUPER);
				break;
			case R.id.most:
				change(VideoQuality.P1080);
				break;
			}
			
		}
	};
	
	private void change(VideoQuality quality){
		try{
			int result = ApiManager.getInstance().changeVideoQuality(quality, PlayerActivity.this);
			if(result == 0) Toast.makeText(PlayerActivity.this, "不支持此清晰度", 2000).show();
		}catch(Exception e){
			Toast.makeText(PlayerActivity.this, e.getMessage(), 2000).show();
		}
	}



}
