package org.springframework.social.kakao.api.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.social.kakao.api.AccessTokenInfo;
import org.springframework.social.kakao.api.ForApns;
import org.springframework.social.kakao.api.ForGcm;
import org.springframework.social.kakao.api.Kakao;
import org.springframework.social.kakao.api.KakaoFriends;
import org.springframework.social.kakao.api.KakaoFriends.Element;
import org.springframework.social.kakao.api.KakaoIds;
import org.springframework.social.kakao.api.KakaoProfile;
import org.springframework.social.kakao.api.KakaoStoryProfile;
import org.springframework.social.kakao.api.KakaoTalkProfile;
import org.springframework.social.kakao.api.MyStory;
import org.springframework.social.kakao.api.StoryLinkInfo;
import org.springframework.social.kakao.api.StoryLinkPosting;
import org.springframework.social.kakao.api.StoryNotePosting;
import org.springframework.social.kakao.api.StoryPhotoPosting;
import org.springframework.social.kakao.api.StoryPhotoUpload;
import org.springframework.social.kakao.api.StoryPostingResult;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class KakaoTemplateTest {
	static final String ACCESS_TOKEN = ""; //insert access token
	static final String ADMIN_KEY = ""; //insert admin key
	static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	Kakao kakao;
	
	StoryPhotoUpload storyPhotoUpload = new StoryPhotoUpload();
	
	@Before
	public void before() {
		kakao = new KakaoTemplate(ACCESS_TOKEN, ADMIN_KEY);
	
		List<String> photoFileList = new ArrayList<String>();
		photoFileList.add("D:\\sample_photo\\1.jpg");
		photoFileList.add("D:\\sample_photo\\2.jpg");
		photoFileList.add("D:\\sample_photo\\3.jpg");
		photoFileList.add("D:\\sample_photo\\4.jpg");
		photoFileList.add("D:\\sample_photo\\5.jpg");
		storyPhotoUpload.setFilePathList(photoFileList);
	}
	
	@Test
	public void getFriendList() {
		//kakao
		System.out.println("********************************************************");
		System.out.println("** Kakao operation");
		System.out.println("********************************************************");
		KakaoFriends friends = kakao.friendsOperation().getFriendList();
		System.out.println(friends.getTotal_count());
		System.out.println("********************************************************");
		System.out.println("friend list ");
		for(Element ele : friends.getElements()) {
			System.out.println(ele.getId());
			System.out.println(ele.getProfile_nickname());
			System.out.println(ele.getProfile_thumbnail_image());
		}
		System.out.println("********************************************************");
	}
	
	@Test
	public void getFriendListCustom() {
		//kakao
		System.out.println("********************************************************");
		System.out.println("** Kakao operation");
		System.out.println("********************************************************");
		KakaoFriends friends = kakao.friendsOperation().getFriendList(true, 0, 10, "desc");
		System.out.println(friends.getTotal_count());
		System.out.println("********************************************************");
		System.out.println("friend list ");
		for(Element ele : friends.getElements()) {
			System.out.println(ele.getId());
			System.out.println(ele.getProfile_nickname());
			System.out.println(ele.getProfile_thumbnail_image());
		}
		System.out.println("********************************************************");
	}
	
	@Test
	public void getKakaoProfile() {
		//kakao
		System.out.println("********************************************************");
		System.out.println("** Kakao operation");
		System.out.println("********************************************************");
		long id = kakao.userOperation().getProfileId();
		System.out.println(String.format("** userOperation -> getProfileId : %s", id));
		
		String nickname = kakao.userOperation().getNickname();
		System.out.println(String.format("** userOperation -> getNickname : %s", nickname));
		
		KakaoProfile profile = kakao.userOperation().getUserProfile();
		System.out.println(String.format("** userOperation -> getUserProfile -> id : %s", profile.getId()));
		System.out.println(String.format("** userOperation -> getUserProfile -> properties -> nickanme : %s", profile.getProperties().getNickname()));
		System.out.println(String.format("** userOperation -> getUserProfile -> properties -> thumbnail_image : %s", profile.getProperties().getThumbnail_image()));
		System.out.println(String.format("** userOperation -> getUserProfile -> properteis -> profile_image : %s", profile.getProperties().getProfile_image()));
		System.out.println(profile.toJsonString(true));
		System.out.println("********************************************************");
		System.out.println("********************************************************");
		System.out.println("** Kakao user profile properties extra data (custom field)");
		System.out.println("********************************************************");
		System.out.println(profile.getProperties().getExtraData());
		System.out.println("********************************************************");
	}
	
	@Test
	public void getStoryProfile() {
		//kakao story
		System.out.println("********************************************************");
		System.out.println("** Story operation");
		System.out.println("********************************************************");
		KakaoStoryProfile storyProfile = kakao.storyOperation().isStoryUser();
		System.out.println(String.format("** storyOperation -> isStoryUser : %b", storyProfile.getIsStoryUser()));
		System.out.println(storyProfile.toJsonString(true));
		storyProfile = kakao.storyOperation().getUserProfile();
		System.out.println(String.format("** storyOperation -> getUserProfile -> nickName : %s", storyProfile.getNickName()));
		System.out.println(String.format("** storyOperation -> getUserProfile -> profileImageURL : %s", storyProfile.getProfileImageURL()));
		System.out.println(String.format("** storyOperation -> getUserProfile -> thumbnailURL : %s", storyProfile.getThumbnailURL()));
		System.out.println(String.format("** storyOperation -> getUserProfile -> bgImageURL : %s", storyProfile.getBgImageURL()));
		System.out.println(String.format("** storyOperation -> getUserProfile -> permalink : %s", storyProfile.getPermalink()));
		System.out.println(String.format("** storyOperation -> getUserProfile -> birthday : %s", storyProfile.getBirthday()));
		System.out.println(String.format("** storyOperation -> getUserProfile -> birthdayType : %s", storyProfile.getBirthdayType()));
		System.out.println(storyProfile.toJsonString(true));
		System.out.println("********************************************************");
	}
	
	@Test
	public void getTalkProfile() {
		//kakao talk
		System.out.println("********************************************************");
		System.out.println("** Talk operation");
		System.out.println("********************************************************");
		KakaoTalkProfile talkProfile = kakao.talkOperation().getUserProfile();
		System.out.println(String.format("** talkOperation -> getUserProfile -> nickName : %s", talkProfile.getNickName()));
		System.out.println(String.format("** talkOperation -> getUserProfile -> profileImageURL : %s", talkProfile.getProfileImageURL()));
		System.out.println(String.format("** talkOperation -> getUserProfile -> thumbnailURL : %s", talkProfile.getThumbnailURL()));
		System.out.println(String.format("** talkOperation -> getUserProfile -> countryISO : %s", talkProfile.getCountryISO()));
		System.out.println(talkProfile.toJsonString(true));
		System.out.println("********************************************************");
		
	}
	
	@Test
	public void notePostingTest() {
		//kakao story posting
		System.out.println("********************************************************");
		System.out.println("** Story note posting operation");
		System.out.println("********************************************************");
		StoryNotePosting storyNotePosting = new StoryNotePosting();
		StringBuilder sbNoteContent = new StringBuilder("Kakao rest api library ���� �׽�Ʈ(spring social kakao).\r\n")
			.append("\r\n�׽�Ʈ �ð� : ").append(DATE_FORMAT.format(new Date()));
		storyNotePosting.setContent(sbNoteContent.toString());
		
		StoryPostingResult notePostingResult = kakao.storyOperation().postNote(storyNotePosting);
		System.out.println(String.format("** posting article id : %s", notePostingResult.getId()));
		System.out.println("********************************************************");
	}
	
	@Test
	public void linkPostingTest() {
		//kakao story linkInfo
		System.out.println("********************************************************");
		System.out.println("** Story link info");
		System.out.println("********************************************************");
		StoryLinkInfo storyLinkInfo = kakao.storyOperation().linkInfo("https://github.com/bongki/spring-social-kakao");
		System.out.println(String.format("** storyOperation -> linkInfo -> url : %s", storyLinkInfo.getUrl()));
		System.out.println(String.format("** storyOperation -> linkInfo -> requested_url : %s", storyLinkInfo.getRequested_url()));
		System.out.println(String.format("** storyOperation -> linkInfo -> host : %s", storyLinkInfo.getHost()));
		System.out.println(String.format("** storyOperation -> linkInfo -> title : %s", storyLinkInfo.getTitle()));
		System.out.println(String.format("** storyOperation -> linkInfo -> image : %s", storyLinkInfo.getImage()));
		System.out.println(String.format("** storyOperation -> linkInfo -> description : %s", storyLinkInfo.getDescription()));
		System.out.println(String.format("** storyOperation -> linkInfo -> section : %s", storyLinkInfo.getSection()));
		System.out.println(String.format("** storyOperation -> linkInfo -> site_name : %s", storyLinkInfo.getSite_name()));
		System.out.println("********************************************************");
		
		System.out.println("********************************************************");
		System.out.println("** Story link info object to json string");
		System.out.println("********************************************************");
		System.out.println("** Story link info object to json string (pretty print)");
		System.out.println(storyLinkInfo.toJsonString(true));
		System.out.println("** Story link info object to json string");
		System.out.println(storyLinkInfo.toJsonString(false));
		System.out.println("********************************************************");
		
		System.out.println("********************************************************");
		System.out.println("** Story link posting operation (use storyLinkInfo object)");
		System.out.println("********************************************************");
		StoryLinkPosting storyLinkPosting = new StoryLinkPosting();
		
		storyLinkPosting.setStoryLinkInfo(storyLinkInfo);
		
		StringBuilder sbLinkContent = new StringBuilder("Kakao rest api library ���� �׽�Ʈ(spring social kakao).\r\n")
			.append("kakao linkinfo rest api ȣ�� ��� ��ü ���� �׽�Ʈ\r\n")
			.append("\r\n�׽�Ʈ �ð� : ").append(DATE_FORMAT.format(new Date()));
		storyLinkPosting.setContent(sbLinkContent.toString());
		
		StoryPostingResult linkResult = kakao.storyOperation().postLink(storyLinkPosting);
		System.out.println(String.format("** link article id : %s", linkResult.getId()));
		System.out.println("********************************************************");
		
	}
	
	@Test
	public void linkWithURLPostingTest() {
		System.out.println("********************************************************");
		System.out.println("** Story link posting operation (use url)");
		System.out.println("********************************************************");
		StoryLinkPosting storyLinkPostingWithURL = new StoryLinkPosting();
		
		storyLinkPostingWithURL.setUrl("https://github.com/bongki/spring-social-kakao");
		
		StringBuilder sbLinkWithURLContent = new StringBuilder("Kakao rest api library ���� �׽�Ʈ(spring social kakao).\r\n")
			.append("url ����(kakao linkinfo rest api ȣ�� ���� ó��) �׽�Ʈ\r\n")
			.append("\r\n�׽�Ʈ �ð� : ").append(DATE_FORMAT.format(new Date()));
		storyLinkPostingWithURL.setContent(sbLinkWithURLContent.toString());
		
		StoryPostingResult linkWithURLResult = kakao.storyOperation().postLink(storyLinkPostingWithURL);
		System.out.println(String.format("** link(with url) article id : %s", linkWithURLResult.getId()));
		System.out.println("********************************************************");
	}
	
	@Test
	public void uploadPhotoTest() {
		System.out.println("********************************************************");
		System.out.println("** Story photo upload operation");
		System.out.println("********************************************************");
		List<String> imageUrlList = kakao.storyOperation().uploadPhoto(storyPhotoUpload);
		for (String photoUrl : imageUrlList) {
			System.out.println(String.format("** image url : %s", photoUrl));
		}
		System.out.println("********************************************************");
		
		System.out.println("********************************************************");
		System.out.println("** Story photo posting operation (use imageUrlList object)");
		System.out.println("********************************************************");
		StoryPhotoPosting storyPhotoPosting = new StoryPhotoPosting();
		storyPhotoPosting.setImageUrlList(imageUrlList);
		StringBuilder sbPhotoContent = new StringBuilder("Kakao rest api library ���� �׽�Ʈ(spring social kakao).\r\n")
			.append("kakao multi rest api ȣ�� ��� ��ü ���� �׽�Ʈ\r\n")
			.append("\r\n�׽�Ʈ �ð� : ").append(DATE_FORMAT.format(new Date()));
		storyPhotoPosting.setContent(sbPhotoContent.toString());
		StoryPostingResult photoResult = kakao.storyOperation().postPhoto(storyPhotoPosting);
		System.out.println(String.format("** photo article id : %s", photoResult.getId()));
		System.out.println("********************************************************");
	}
	
	@Test
	public void uploadPhotoTestWithFileList() {
		System.out.println("********************************************************");
		System.out.println("** Story photo posting operation (use storyPhotoUpload object)");
		System.out.println("********************************************************");
		StoryPhotoPosting storyPhotoPosting = new StoryPhotoPosting();
		storyPhotoPosting.setStoryPhotoUpload(storyPhotoUpload);
		StringBuilder sbPhotoContent = new StringBuilder("Kakao rest api library ���� �׽�Ʈ(spring social kakao).\r\n")
			.append("kakao multi rest api ���� ó�� �׽�Ʈ\r\n")
			.append("\r\n�׽�Ʈ �ð� : ").append(DATE_FORMAT.format(new Date()));
		storyPhotoPosting.setContent(sbPhotoContent.toString());
		StoryPostingResult photoResult = kakao.storyOperation().postPhoto(storyPhotoPosting);
		System.out.println(String.format("** photo article id : %s", photoResult.getId()));
		System.out.println("********************************************************");
	}
	
	@Test
	public void myStory() {
		System.out.println("********************************************************");
		System.out.println("** Story my story operation");
		System.out.println("********************************************************");
		MyStory myStory = kakao.storyOperation().myStory("_91mz53.6AoavE1Izn7");
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(myStory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("********************************************************");
	}
	
	@Test
	public void myStories() {
		System.out.println("********************************************************");
		System.out.println("** Story my stories operation");
		System.out.println("********************************************************");
		List<MyStory> myStories = kakao.storyOperation().myStories(null);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(myStories));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("********************************************************");
	}
	
	@Test
	public void deleteMyStory() {
		System.out.println("********************************************************");
		System.out.println("** Story delete my story operation");
		System.out.println("********************************************************");
		kakao.storyOperation().deleteMyStory("_91mz53.f91bcVG6zX9");
		System.out.println("********************************************************");
	}
	
	@Test
	public void accessTokenInfo() {
		System.out.println("********************************************************");
		System.out.println("** Kakao rest api access token infomation check");
		System.out.println("********************************************************");
		try {
			ResponseEntity<AccessTokenInfo> accessTokenInfo = kakao.userOperation().accessTokenInfo(ACCESS_TOKEN);
			System.out.println(accessTokenInfo.getStatusCode());
			System.out.println(accessTokenInfo.getStatusCode() == HttpStatus.OK);
			System.out.println(accessTokenInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("********************************************************");
	}
	
//	@Test
//	public void unlink() {
//		System.out.println("********************************************************");
//		System.out.println("** Kakao rest api access token infomation check");
//		System.out.println("********************************************************");
//		try {
//			
//			KakaoProfile unlink = kakao.userOperation().unlink("892232457");
//			System.out.println(unlink);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("********************************************************");
//	}
	
	@Test
	public void unlink() {
		System.out.println("********************************************************");
		System.out.println("** Kakao rest api access token infomation check");
		System.out.println("********************************************************");
		try {
			
			KakaoProfile unlink = kakao.userOperation().unlink("915319450");
			System.out.println(unlink);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("********************************************************");
	}
	
	@Test
	public void updateProfileCustomField() {
		Map<String, Object> properties = new LinkedHashMap<String, Object>();
		properties.put("description", "Field update by API - " + DATE_FORMAT.format(new Date()));
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		System.out.println("********************************************************");
		System.out.println("** Kakao rest api profile update");
		System.out.println("********************************************************");
		try {
			KakaoProfile kakaoProfile = kakao.userOperation().updateProfile(objectMapper.writeValueAsString(properties));
			System.out.println(kakaoProfile.toJsonString(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("********************************************************");
	}
	
	@Test
	public void ids() {
		KakaoIds kakaoIds = kakao.userOperation().ids();
		System.out.println(kakaoIds.toJsonString(true));
	}
	
	@Test
	public void getKakaoProfileByAdmin() {
		String userId = "8746385";
		KakaoProfile profile = kakao.userOperation().getUserProfile(userId);
		System.out.println(profile.toJsonString(true));
	}
	
	@Test
	public void pushSendMessageTest() {
		List<String> uuids = new ArrayList<String>();
		uuids.add("uuid_1");
		uuids.add("uuid_2");
		uuids.add("uuid_3");
		
		ForApns forApns = new ForApns(1, "default", true, "my message. blah~blah~");
		forApns.addCustom_field("apns_custom1", "apns_value1");
		forApns.addCustom_field("apns_custom2", "apns_value2");
		
		ForGcm forGcm = new ForGcm("123", true, "");
		forGcm.addCustom_field("gcm_custom1", "gcm_value1");
		forGcm.addCustom_field("gcm_custom2", "gcm_value2");
		
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
		param.set("uuids", uuids);
		param.set("for_apns", forApns);
		param.set("for_gcm", forGcm);
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			System.out.println("********************************************************");
			System.out.println("** apns message");
			System.out.println("********************************************************");
			System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(param));
			System.out.println("********************************************************");
			System.out.println("********************************************************");
			System.out.println("** apns message loc");
			System.out.println("********************************************************");
			forApns.setMessage("locKey", Arrays.asList(new String[]{"arg1", "arg2"}));
			System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(param));
			System.out.println("********************************************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void plainRestTemplateTest() {
		RestTemplate restTemplate = new RestTemplate(ClientHttpRequestFactorySelector.getRequestFactory());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setAccept(Arrays.asList(new MediaType[]{MediaType.ALL}));
		headers.set("Authorization", "KakaoAK my-admin-key");
		
		MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
		param.set("limit", "");
		param.set("fromId", "");
		param.set("order", "");
		
		
		KakaoIds kakaoIds = restTemplate.postForObject(URIBuilder.fromUri("https://kapi.kakao.com/v1/user/ids").queryParams(param).build(), new HttpEntity<Object>(headers), KakaoIds.class);
		
		System.out.println(kakaoIds.toJsonString(true));
	}
}
