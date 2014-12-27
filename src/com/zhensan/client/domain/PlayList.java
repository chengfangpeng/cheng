package com.zhensan.client.domain;

import java.io.Serializable;

public class PlayList implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;	
	private String link;		 	//专辑链接
	private String play_link;		 	//专辑播放链接
	private String thumbnail;		 	//专辑截图
	private String video_count;		 	//专辑视频数量
	private String view_count;		 	//专辑总播放数
	private String duration;		 	//专辑总时长，单位：秒
	private String published;		 	//发布时间
	private User user;	 	//发布用户对象
	
	
	
	public PlayList(String id, String name, String link, String play_link,
			String thumbnail, String video_count, String view_count,
			String duration, String published, User user) {
		super();
		this.id = id;
		this.name = name;
		this.link = link;
		this.play_link = play_link;
		this.thumbnail = thumbnail;
		this.video_count = video_count;
		this.view_count = view_count;
		this.duration = duration;
		this.published = published;
		this.user = user;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getPlay_link() {
		return play_link;
	}
	public void setPlay_link(String play_link) {
		this.play_link = play_link;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getVideo_count() {
		return video_count;
	}
	public void setVideo_count(String video_count) {
		this.video_count = video_count;
	}
	public String getView_count() {
		return view_count;
	}
	public void setView_count(String view_count) {
		this.view_count = view_count;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
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
