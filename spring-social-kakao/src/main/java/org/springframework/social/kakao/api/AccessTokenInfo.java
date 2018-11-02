package org.springframework.social.kakao.api;

import java.io.Serializable;

public class AccessTokenInfo extends KakaoObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long expiresInMillis;
	
	private long appId;
	
	private long id;
	
	public long getExpiresInMillis() {
		return this.expiresInMillis;
	}
	
	public long getAppId() {
		return appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setExpiresInMillis(long expiresInMillis) {
		this.expiresInMillis = expiresInMillis;
	}



	AccessTokenInfo() {}
}
