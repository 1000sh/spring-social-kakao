package org.springframework.social.kakao.api;

import java.io.Serializable;

public class KakaoProfile extends KakaoObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String kaccount_email;
	
	private KakaoProfileProperties properties;
	
	public long getId() {
		return this.id;
	}
	
	public KakaoProfileProperties getProperties() {
		return this.properties;
	}
	
	public String getKaccount_email() {
		return kaccount_email;
	}

	public void setKaccount_email(String kaccount_email) {
		this.kaccount_email = kaccount_email;
	}
	
	KakaoProfile(){}
}
