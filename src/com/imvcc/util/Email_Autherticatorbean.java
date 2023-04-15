package com.imvcc.util;

import javax.mail.Authenticator;

public class Email_Autherticatorbean extends Authenticator {
	private String m_username = null;
	private String m_userpass = null;

	public String getM_username() {
		return m_username;
	}

	public void setM_username(String m_username) {
		this.m_username = m_username;
	}

	public String getM_userpass() {
		return m_userpass;
	}

	public void setM_userpass(String m_userpass) {
		this.m_userpass = m_userpass;
	}

	public Email_Autherticatorbean(String username, String userpass) {
		super();
		setM_username(username);
		setM_userpass(userpass);
	}

}
