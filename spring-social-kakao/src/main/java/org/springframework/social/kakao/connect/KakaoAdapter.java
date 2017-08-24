package org.springframework.social.kakao.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.kakao.api.Kakao;
import org.springframework.social.kakao.api.KakaoProfile;
import org.springframework.social.kakao.api.KakaoProfileProperties;

public class KakaoAdapter implements ApiAdapter<Kakao> {
	@Deprecated
	public boolean test(Kakao api) {
		// do nothing
		return false;
	}
	
	public void setConnectionValues(Kakao kakao, ConnectionValues values) {
		KakaoProfile profile = kakao.userOperation().getUserProfile();
		values.setProviderUserId(Long.toString(profile.getId()));
		KakaoProfileProperties kakaoProperties = profile.getProperties();
		values.setProfileUrl("");
		if(kakaoProperties != null){
			values.setDisplayName(kakaoProperties.getNickname());
			values.setImageUrl(kakaoProperties.getProfile_image());
		}
	}
	
	public UserProfile fetchUserProfile(Kakao kakao) {
		KakaoProfile profile = kakao.userOperation().getUserProfile();
		final String email = profile.getKaccount_email();
		final String username = profile.getKaccount_email();
		
		KakaoProfileProperties kakaoProperties = profile.getProperties();
		UserProfileBuilder userProfileBuilder = new UserProfileBuilder();
		
		UserProfile userProfile = null;
		if(kakaoProperties != null){
			userProfile = userProfileBuilder
				.setName(kakaoProperties.getNickname())
				.setUsername(username)
				.setEmail(email)
				.build();
		}else{
			userProfile = userProfileBuilder
					.setName("")
					.setUsername(username)
					.setEmail(email)
					.build();
		}
		
		return userProfile;
	}
	
	@Deprecated
	public void updateStatus(Kakao kakao, String message) {
		// do nothing
	}
}
