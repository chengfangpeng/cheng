package com.zhensan.client.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.zhensan.client.R;

public class LeftmenuAdater extends BaseAdapter {

	private Context mContext;
	private String[] leftmenu_Title_text;
	private String[] leftmenu_Title_pic;
	private LayoutInflater inflater;
	private BitmapUtils bitmapUtils;

	public LeftmenuAdater(Context mContext, String[] leftmenu_Title_text,
			String[] leftmenu_Title_pic) {
		super();
		this.mContext = mContext;
		this.leftmenu_Title_text = leftmenu_Title_text;
		this.leftmenu_Title_pic = leftmenu_Title_pic;
		inflater = LayoutInflater.from(mContext);
		bitmapUtils = new BitmapUtils(mContext);
		Log.d("TAG", "String[]的大小" + leftmenu_Title_text.length );
	}

	@Override
	public int getCount() {
		return leftmenu_Title_text.length;
	}

	@Override
	public Object getItem(int position) {
		return leftmenu_Title_text[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (position == 0) {
			if (convertView == null) {
				viewHolder = new ViewHolder();

				convertView = inflater.inflate(R.layout.leftmenu_home_item, null);
				viewHolder.title_text = (TextView) convertView
						.findViewById(R.id.leftmenu_item_title_home);
				viewHolder.title_pic = (ImageView) convertView
						.findViewById(R.id.leftmenu_item_icon_home);
				convertView.setTag(viewHolder);
			}else{
				viewHolder = (ViewHolder) convertView.getTag();
			}
			if(viewHolder.title_pic == null){
				Log.d("TAG", "viewHold == null");
			}
//			Log.d("TAG", viewHolder.title_pic.toString());
			if(viewHolder.title_pic != null && viewHolder.title_text != null){
				viewHolder.title_pic.setBackgroundResource(R.drawable.leftmenu_home);
				viewHolder.title_text.setText(leftmenu_Title_text[position]);
			}
			
		} else {
			if (convertView == null) {
				viewHolder = new ViewHolder();

				convertView = inflater.inflate(R.layout.leftmenu_item, null);
				viewHolder.title_text = (TextView) convertView
						.findViewById(R.id.leftmenu_item_title_TextView);
				convertView.setTag(viewHolder);
			}else{
				viewHolder = (ViewHolder) convertView.getTag();
			}
			viewHolder.title_text.setText(leftmenu_Title_text[position]);

		}
		return convertView;
	}

	private class ViewHolder {

		public TextView title_text;
		public ImageView title_pic;

	}

}
