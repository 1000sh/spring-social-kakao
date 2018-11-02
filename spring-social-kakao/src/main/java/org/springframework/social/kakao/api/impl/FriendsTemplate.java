package org.springframework.social.kakao.api.impl;

import org.springframework.social.kakao.api.FriendsOperation;
import org.springframework.social.kakao.api.KakaoFriends;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class FriendsTemplate extends AbstractKakaoOperations implements FriendsOperation {
	private final RestTemplate restTemplate;
	private final RestTemplate adminRestTemplate;
	
	public FriendsTemplate(RestTemplate restTemplate, RestTemplate adminRestTemplate, boolean isAuthorized) {
		super(isAuthorized);
		this.restTemplate = restTemplate;
		this.adminRestTemplate = adminRestTemplate;
	}

	public KakaoFriends getFriendList(boolean secure_resource, int offset, int limit, String order) {
		requireAuthorization();
		MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
		param.set("secure_resource", String.valueOf(secure_resource));
		param.set("offset", String.valueOf(offset));
		param.set("limit", String.valueOf(limit));
		param.set("order", order);
		
		return restTemplate.getForObject(buildApiUri("/v1/friends", param), KakaoFriends.class);
	}

	public KakaoFriends getFriendList() {
		requireAuthorization();
		return restTemplate.getForObject(buildApiUri("/v1/friends"), KakaoFriends.class);
	}

}
