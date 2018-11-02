package org.springframework.social.kakao.api;

import java.io.Serializable;
import java.util.List;

public class KakaoFriends extends KakaoObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long total_count;
	private String before_url;
	private String after_url;
	
	private List<Element> elements;
	
	public static class Element implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private long id;
        private String profile_nickname;
        private String profile_thumbnail_image;
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getProfile_nickname() {
			return profile_nickname;
		}
		public void setProfile_nickname(String profile_nickname) {
			this.profile_nickname = profile_nickname;
		}
		public String getProfile_thumbnail_image() {
			return profile_thumbnail_image;
		}
		public void setProfile_thumbnail_image(String profile_thumbnail_image) {
			this.profile_thumbnail_image = profile_thumbnail_image;
		}
        
	}
	
	public long getTotal_count() {
		return total_count;
	}

	public void setTotal_count(long total_count) {
		this.total_count = total_count;
	}

	public String getBefore_url() {
		return before_url;
	}

	public void setBefore_url(String before_url) {
		this.before_url = before_url;
	}

	public String getAfter_url() {
		return after_url;
	}

	public void setAfter_url(String after_url) {
		this.after_url = after_url;
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	KakaoFriends(){}
}
