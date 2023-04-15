package com.imvcc.actions.authorize;

import java.util.Map;

import com.imvcc.actions.BaseAction;
import com.imvcc.common.SessionConstant;
import com.imvcc.service.ServiceFacotry;
import com.imvcc.service.UserService;

public class RedirectAction extends BaseAction{
	/**
	 * 
	 */
	private String url=null;
	private int type=0;
	private static final long serialVersionUID = 4890875936426441894L;
	private UserService userService=ServiceFacotry.getUserService();
	
	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String execute() {
		type=getRedirectType(session);
		System.out.println("type="+type);
		if(type!=0) {
			url=(String)session.get(SessionConstant.SESS_REDIRECT);
			System.out.println("url="+url);
			url=url.substring(0,url.lastIndexOf("$"));
		}
		return "success";
	}
	/**
	 * 获取转向的类型 0表示没有发生转向，1表示login的转向，2表示激活转向
	 */
	public int getRedirectType(Map<String, Object> session) {
		if (!session.containsKey(SessionConstant.SESS_REDIRECT)) {
			return 0;
		}
		String strPath = (String) session.get(SessionConstant.SESS_REDIRECT);
		strPath = strPath.substring(strPath.lastIndexOf("$") + 1);
		if ("login".equals(strPath)) {
			return 1;
		} else if ("verify".equals(strPath)) {
			return 2;
		}
		return 0;
	}
}
