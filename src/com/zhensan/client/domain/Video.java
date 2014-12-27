package com.zhensan.client.domain;

import java.io.Serializable;

public class Video implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;	 	//视频唯一ID
	private String title;	//	 	视频标题
	private String link;		 	//视频播放链接
	private String thumbnail;			 	//视频截图
	private String duration;			 	//视频时长，单位：秒
	private String category;	//	 	视频分类
	private String state;		 	//视频状态 normal: 正常 encoding: 转码中 fail: 转码失败 in_review: 审核中 blocked: 已屏蔽
	private int  view_count;		 	//总播放数
	private int favorite_count;			 	//总收藏数
	private int comment_count;		 	//总评论数
	private int up_count;		 	//总顶数
	private int down_count;		 	//总踩数
	private String published;			 	//发布时间
	private User user;			 	//上传用户对象
	
	
	
	public Video(String id, String title, String link, String thumbnail,
			String duration, String category, String state, int view_count,
			int favorite_count, int comment_count, int up_count,
			int down_count, String published, User user) {
		super();
		this.id = id;
		this.title = title;
		this.link = link;
		this.thumbnail = thumbnail;
		this.duration = duration;
		this.category = category;
		this.state = state;
		this.view_count = view_count;
		this.favorite_count = favorite_count;
		this.comment_count = comment_count;
		this.up_count = up_count;
		this.down_count = down_count;
		this.published = published;
		this.user = user;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	public int getFavorite_count() {
		return favorite_count;
	}
	public void setFavorite_count(int favorite_count) {
		this.favorite_count = favorite_count;
	}
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}
	public int getUp_count() {
		return up_count;
	}
	public void setUp_count(int up_count) {
		this.up_count = up_count;
	}
	public int getDown_count() {
		return down_count;
	}
	public void setDown_count(int down_count) {
		this.down_count = down_count;
	}
	public String getPublished() {
		return published;
	}
	public void setPublished(String published) {
		this.published = published;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

}
