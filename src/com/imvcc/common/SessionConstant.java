package com.imvcc.common;

public class SessionConstant {
	/* 用于跳转标志的存储：在订单生效前，检查用户是否登录、激活? */
	public static final String SESS_REDIRECT = "session.com.imvcc.authorize.redirect";
	
	/* 用于验证码的存储*/
	public static final String SESS_VALIDATE_CODE="session.com.imvcc.aciton.validateCode";
	/* 用于激活码的存储*/
	public static final String SESS_ACTIVE_CODE="session.com.imvcc.action.authorize.actionCode";
	
	/* 用于存储有效用户：①登录成功时设置，②注册成功时设置*/
	public static final String SESS_USER_AUTHORIZE="session.com.imvcc.action.user.authorize";
	
	/* 用于购物车的存储*/
	public static final String SESS_CART="session.com.imvcc.service.cartService";
	
	/* 用于分页*/
//	public static final String SESS_SC="session.com.imvcc.action.bookList.sc";
//	public static final String SESS_SSC="session.com.imvcc.action.bookList.ssc";
//	public static final String SESS_PAGE="session.com.imvcc.action.bookList.sc.page";
}
