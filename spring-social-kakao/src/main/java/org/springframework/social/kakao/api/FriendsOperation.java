package org.springframework.social.kakao.api;

public interface FriendsOperation {
	
	/**
	 * <pre>
	 * 친구목록 조회하기
	 * </pre>
	 * @param secure_resource
	 * @param offset
	 * @param limit
	 * @param order
	 * @return
	 */
	KakaoFriends getFriendList(boolean secure_resource, int offset, int limit, String order);

	/**
	 * <pre>
	 * 친구목록 조회하기
	 * </pre>
	 * @return
	 */
	KakaoFriends getFriendList();
}