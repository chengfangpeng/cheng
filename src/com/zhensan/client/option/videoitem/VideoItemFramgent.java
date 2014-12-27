package com.zhensan.client.option.videoitem;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.zhensan.client.R;
import com.zhensan.client.adapter.VideoListViewAdapter;
import com.zhensan.client.domain.PlayList;
import com.zhensan.client.domain.Video;
import com.zhensan.client.option.SlidingMenuControlActivity;
import com.zhensan.client.option.player.PlayerActivity;
import com.zhensan.client.parentclass.ParentFragment;
import com.zhensan.client.service.ClientTask;

public class VideoItemFramgent extends ParentFragment{
	
	private TextView title_TextView;
	private String title;
	private PlayList playList;
	private AbPullListView video_Item_ListView;
	private int video_count;
	private int video_page;
	private List<Video> videoData;
	private VideoListViewAdapter adapter;
	private int max_page;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		title = (String) params[0];
		playList = (PlayList) params[1];
		video_count = Integer.parseInt((String)playList.getVideo_count());
		video_page = (video_count / 10) + 1;
		max_page = video_page;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("TAG", "天梯高端局");
		View contextView = super.onCreateView(inflater, container,
				savedInstanceState);
		
//		threadTask();
		return contextView;
		
		
		
	}
	

	@Override
	public void onStart() {
		super.onStart();
		threadTask();
		
		video_Item_ListView.setAbOnListViewListener(new AbOnListViewListener() {
			
			@Override
			public void onRefresh() {
				
				if(video_page == max_page){
					
					video_Item_ListView.stopRefresh();
				}else{
//					videoData.clear();
					video_page = max_page;
					threadTask();
					
				}
				
				
			}
			
			@Override
			public void onLoadMore() {
				
				Log.d("TAG", video_page + "");
				
				if(video_page == 0){
					video_Item_ListView.stopLoadMore();
					Toast.makeText(SlidingMenuControlActivity.activity,
							"已经是最后一页了", Toast.LENGTH_SHORT).show();
					
				}else{
					video_page--;
					
					threadTask();
					
				}
				
			}
		});
		
		
	}
	@Override
	protected int getLayoutId() {
		return R.layout.video_item;
	}

	@Override
	protected void setupViews(View parentView) {
		
		video_Item_ListView = (AbPullListView) parentView.findViewById(R.id.video_Item_ListView);
		video_Item_ListView.setPullLoadEnable(true);
		video_Item_ListView.setOnItemClickListener(onItemClickListener);
	}

	@Override
	protected void initialized() {
		
		videoData = new ArrayList<Video>();
		adapter = new VideoListViewAdapter(getActivity(), videoData);
		video_Item_ListView.setAdapter(adapter);
		
	}

	@Override
	protected void threadTask() {
		String url = "https://openapi.youku.com/v2/playlists/videos.json?client_id=79e4f5b64c881b20&playlist_id=" +
	playList.getId() + "&page=" + video_page;
		JsonObjectRequest jsonRequest = new JsonObjectRequest(url, null, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				Log.d("TAG", response.toString());
				Gson gson = new Gson();
				try {
				JSONArray jsonArray = (JSONArray) response.get("videos");
				for(int i = jsonArray.length() - 1; i >= 0; i--){
					Video video = gson.fromJson(jsonArray.get(i).toString(), Video.class);
					videoData.add(video);
					
				}
				adapter.notifyDataSetChanged();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				
			}
		});
		ClientTask.getInstance(getActivity()).addToRequestQueue(jsonRequest);
	}
	
	private OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
			Intent i = new Intent(SlidingMenuControlActivity.activity,PlayerActivity.class);
			i.putExtra("vid", videoData.get(position).getId());
			startActivity(i);
			
		}
	};

}
