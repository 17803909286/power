package com.power.home.jpush;

public class MessageReceiveBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msgId;
	private String pushMsgChannel;
	private String pushMsgUservip;
	private String pushMsgType;
	private String thirdKey;
	private String hasTitle;
	private String pushMsgTitle;
	private String buttonUrl;
	private String isShare;
	public String getPushMsgChannel() {
		return pushMsgChannel;
	}
	public void setPushMsgChannel(String pushMsgChannel) {
		this.pushMsgChannel = pushMsgChannel;
	}
	public String getPushMsgUservip() {
		return pushMsgUservip;
	}
	public void setPushMsgUservip(String pushMsgUservip) {
		this.pushMsgUservip = pushMsgUservip;
	}
	public String getPushMsgType() {
		return pushMsgType;
	}
	public void setPushMsgType(String pushMsgType) {
		this.pushMsgType = pushMsgType;
	}
	public String getThirdKey() {
		return thirdKey;
	}
	public void setThirdKey(String thirdKey) {
		this.thirdKey = thirdKey;
	}
	public String getHasTitle() {
		return hasTitle;
	}
	public void setHasTitle(String hasTitle) {
		this.hasTitle = hasTitle;
	}
	public String getButtonUrl() {
		return buttonUrl;
	}
	public void setButtonUrl(String buttonUrl) {
		this.buttonUrl = buttonUrl;
	}
	public String getIsShare() {
		return isShare;
	}
	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getPushMsgTitle() {
		return pushMsgTitle;
	}
	public void setPushMsgTitle(String pushMsgTitle) {
		this.pushMsgTitle = pushMsgTitle;
	}
}
