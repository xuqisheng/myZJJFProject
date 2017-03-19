package com.corner.core.beans.pay.wx;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
* @ClassName: WXGZPTBACKBean
* @Description: TODO(这里用一句话描述这个类的作用)
* @author luke
* @email   luke@mibodoctor.com  
* @date 2015年3月18日 下午8:46:06
*
 */
public class WXGZPTBACKBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/************************base******************************/
	private  String MsgId;
	private  String FromUserName;
	private  String ToUserName;
	private  Long CreateTime;
	private  String MsgType;
	
	/************************text******************************/
	private  String Content;
	
	/************************img******************************/
	private  Map<String ,String> Image;
	//MediaId
	/************************Voice******************************/
	private  Map<String ,String> Voice;
	//MediaId
	/************************Voice******************************/
	class Video{
		private String MediaId;
		private String Title;
		private String Description;
		public String getMediaId() {
			return MediaId;
		}
		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}
		public String getTitle() {
			return Title;
		}
		public void setTitle(String title) {
			Title = title;
		}
		public String getDescription() {
			return Description;
		}
		public void setDescription(String description) {
			Description = description;
		}
	}
	private  Video Video;
	/************************Music******************************/
	class Music{
		private String Title;
		private String Description;
		private String MusicUrl;
		private String HQMusicUrl;
		private String ThumbMediaId;
		public String getTitle() {
			return Title;
		}
		public void setTitle(String title) {
			Title = title;
		}
		public String getDescription() {
			return Description;
		}
		public void setDescription(String description) {
			Description = description;
		}
		public String getMusicUrl() {
			return MusicUrl;
		}
		public void setMusicUrl(String musicUrl) {
			MusicUrl = musicUrl;
		}
		public String getHQMusicUrl() {
			return HQMusicUrl;
		}
		public void setHQMusicUrl(String hQMusicUrl) {
			HQMusicUrl = hQMusicUrl;
		}
		public String getThumbMediaId() {
			return ThumbMediaId;
		}
		public void setThumbMediaId(String thumbMediaId) {
			ThumbMediaId = thumbMediaId;
		}
		
	}
	private  Music Music;
	
	/************************ArticleCount******************************/
	private  int ArticleCount;
	private  List<Item> Articles;
	class Item{
		private String Title;
		private String Description;
		private String PicUrl;
		private String Url;
		public String getTitle() {
			return Title;
		}
		public void setTitle(String title) {
			Title = title;
		}
		public String getDescription() {
			return Description;
		}
		public void setDescription(String description) {
			Description = description;
		}
		public String getPicUrl() {
			return PicUrl;
		}
		public void setPicUrl(String picUrl) {
			PicUrl = picUrl;
		}
		public String getUrl() {
			return Url;
		}
		public void setUrl(String url) {
			Url = url;
		}
		
	}
	//=============================//
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public Long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public Map<String, String> getImage() {
		return Image;
	}
	public void setImage(Map<String, String> image) {
		Image = image;
	}
	public Map<String, String> getVoice() {
		return Voice;
	}
	public void setVoice(Map<String, String> voice) {
		Voice = voice;
	}
	public Video getVideo() {
		return Video;
	}
	public void setVideo(Video video) {
		Video = video;
	}
	public Music getMusic() {
		return Music;
	}
	public void setMusic(Music music) {
		Music = music;
	}
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<Item> getArticles() {
		return Articles;
	}
	public void setArticles(List<Item> articles) {
		Articles = articles;
	}

}