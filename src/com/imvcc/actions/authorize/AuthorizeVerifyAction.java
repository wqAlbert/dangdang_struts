package com.imvcc.actions.authorize;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.imvcc.actions.BaseAction;
import com.imvcc.common.SessionConstant;
import com.imvcc.domain.User;
import com.imvcc.service.UserService;
import com.imvcc.service.ServiceFacotry;
import com.imvcc.util.EncoderUtil;
import com.imvcc.util.ValidatorUtil;
/**
 * 提供注册激活的控制逻辑
 * @author Administrator
 *
 */
public class AuthorizeVerifyAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1125899523242545127L;
	private String emailVerifyCode;
	private Logger log=Logger.getLogger(AuthorizeVerifyAction.class);
	private UserService userService=ServiceFacotry.getUserService();
	
	private Map<String,String> errors=new HashMap<String,String>();

	
	public Map<String, String> getErrors() {
		return errors;
	}
	public void addError(String key,String value) {
		errors.put(key, value);
	}
	
	public String getEmailVerifyCode() {
		return emailVerifyCode;
	}
	public void setEmailVerifyCode(String emailVerifyCode) {
		this.emailVerifyCode = emailVerifyCode;
	}
	/**
	 * 处理激活
	 * @return
	 */
	public String verify() {
		if(!validateVerfify()) return "input";

		boolean actived=true;
		log.debug("Dang:开始处理激活，解析激活码和User Email Verfify code-"+emailVerifyCode+"先从session中激活");

		//actived=userService.activeUser(emailVerifyCode,session);
		Integer userId = EncoderUtil.getIDFromUUID(emailVerifyCode);
		System.out.println("userId="+userId);

		User user = (User) session.get(SessionConstant.SESS_USER_AUTHORIZE);
		System.out.println("user="+user);

		if (user != null && user.getId().equals(userId)) {
			System.out.println("------" + user.getId() + "++" + userId);
			System.out.println("------" + user.getEmailVerifyCode() + "++"
					+ emailVerifyCode);
			log.debug("Dang:可以激活账户");
			user.setIsEmailVerify(true);
			userService.updateUser(user);
			log.debug("Dang:激活成功");
		} else {
			this.addError("emailVerifyCode", "激活失败");
			return "input";
		}
		
		if(isRedirctURL(session)) {
			log.debug("Dang:发生转向:"+session.get(SessionConstant.SESS_REDIRECT));
			return "redirect";
		}
		return "success";
	}
	/**
	 * 激活界面的表单校验
	 * @return
	 */
	public boolean validateVerfify() {
		String verifyCode=EncoderUtil.getUUID(emailVerifyCode);
		log.debug("Dang:验证激活码的非空");
		if(!ValidatorUtil.requiredString(emailVerifyCode, false)) {
			this.addError("emailVerifyCode", "激活码不能为空");
			return false;
		}
		log.debug("Dang:验证激活码的合法性");
		if(verifyCode==null) {
			this.addError("emailVerifyCode", "非法的激活码");
			return false;
		}
		Integer userId=EncoderUtil.getIDFromUUID(emailVerifyCode);
		if(userId==null) {
			this.addError("emailVerifyCode", "非法的激活码...");
			return false;
		}
		log.debug("Dang:激活码验证通过");
		return true;
	}
	
	/**
	 * 是否需要转向:可能会被其它Action调用
	 */
	public boolean isRedirctURL(Map<String, Object> session) {
		String url = (String) session.get(SessionConstant.SESS_REDIRECT);
		if (url == null)
			return false;
		else
			return true;
	}
}
