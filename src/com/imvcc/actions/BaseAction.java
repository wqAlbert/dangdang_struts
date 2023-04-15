package com.imvcc.actions;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;


public class BaseAction implements SessionAware,ServletRequestAware,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5146075388546477793L;
	
	protected HttpServletRequest request;
	protected Map<String,Object> session;
	public void setSession(Map<String, Object> arg0) {
		this.session=arg0;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
	}
	
}
