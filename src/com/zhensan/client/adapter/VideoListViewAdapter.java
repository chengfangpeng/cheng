package com.zhensan.client.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.zhensan.client.R;
import com.zhensan.client.domain.Video;

public class VideoListViewAdapter extends BaseAdapter{
	
	private Context mContext;
	private List<Video> listData;
	
	private LayoutInflater inflater;
	private BitmapUtils bitmapUtils;
	
	
	

	public VideoListViewAdapter(Context mContext, List<Video> listData) {
		super();
		this.mContext = mContext;
		this.listData = listData;
		inflater = LayoutInflater.from(mContext);
		bitmapUtils = new BitmapUtils(mContext);
	}

	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HoldView holdView = null;
		if(convertView == null){
			holdView = new HoldView();
			convertView = inflater.inflate(R.layout.video_listview_item, null);
			holdView.thumbnail = (ImageView) convertView.findViewById(R.id.video_item_thumbnail);
			holdView.title = (TextView) convertView.findViewById(R.id.video_item_title);
			convertView.setTag(holdView);
			
		}else{
			holdView = (HoldView) convertView.getTag();
			
		}
		bitmapUtils.display(holdView.thumbnail, listData.get(position).getThumbnail());
		holdView.title.setText(listData.get(position).getTitle());
		return convertView;
	}
	private class HoldView{
		
		public TextView title, published, duration;
		
		public ImageView thumbnail;
		
		
	}

}
