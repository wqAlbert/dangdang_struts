package com.imvcc.actions.authorize;

import java.util.Map;

import org.apache.log4j.Logger;

import com.imvcc.actions.BaseAction;
import com.imvcc.common.FormConstant;
import com.imvcc.common.SessionConstant;
import com.imvcc.domain.User;
import com.imvcc.service.UserService;
import com.imvcc.service.ServiceFacotry;
import com.imvcc.util.ValidatorUtil;

/**
 * 处理Login的转向，验证等
 * 
 * @author Mr.rong
 * 
 */
public class LoginAction extends BaseAction {
	private static final long serialVersionUID = -6132027319709991737L;
	/**
	 * 转向服务
	 * 
	 * @return
	 */
	private User user;
	private Logger log = Logger.getLogger(LoginAction.class);
	private UserService userService = ServiceFacotry.getUserService();
	private String error = "";

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 处理登录
	 * 
	 * @return
	 */

	public String login() {
		if(!validateLogin()) {
			return "input";
		}
		
		//检查用户是否是有效的用户
		log.debug("Dang:开始验证用户");
		System.out.println("user①:"+user.getPassword());
		user=userService.validateUser(user);
		if(user==null) {
			System.out.println("Dang:用户登录失败");
			log.debug("Dang:用户登录失败");
			this.error = "Email或者密码输入错误，请重新登录";
			return "error";
		}
		else if("N".equals(user.getIsEmailVerify())) {
			log.debug("Dang:用户登录成功,用户没有激活账户");
			return "unactived";
		}
		
		log.debug("Dang:成功登录:"+user.getEmail()+"-"+user.getLastLoginIp());
		//如果是有效用户，更新用户
		log.debug("Dang:更新用户的IP");
		user.setLastLoginIp(request.getRemoteAddr());
		user.setLastLoginTime(System.currentTimeMillis());
		userService.updateUser(user);
		session.put(SessionConstant.SESS_USER_AUTHORIZE,user);
		
		//检查是否有跳转标志，如果有进入到toInputAddress.action(OrderAction#toInputAddress())
		if(isRedirctURL(session)) {
			System.out.println("redirect");
			log.debug("Dang:发生转向"+session.get(SessionConstant.SESS_REDIRECT));
			return "redirect";
		}
		return "success";
	}

	/**
	 * Login 校验器
	 */
	public boolean validateLogin() {//手动验证
		log.debug("Dang:=======开始validateLogin=======");
		log.debug("Dang:Email或者Password非空验证");
		if (!ValidatorUtil.requiredString(user.getEmail(),
				ValidatorUtil.DO_TRIM)
				|| !ValidatorUtil.requiredString(user.getPassword(),
						ValidatorUtil.DO_TRIM)) {
			
			this.error = "Email电子或者密码不能为空，请输入";
			return false;
		}
		log.debug("Dang:电子邮件格式验证");
		if (!ValidatorUtil.email(user.getEmail())) {
			this.error = "电子邮件格式错误 ,请重新输入";
			return false;

		}
		log.debug("Dang:密码长度验证");
		if (ValidatorUtil.stringLength(user.getPassword(),
				FormConstant.MIN_PASSWORD_LENGTH,
				FormConstant.MAX_PASSWORD_LENGTH, ValidatorUtil.DO_TRIM) != 0) {
			this.error = "Email或者密码输入错误，请重新登录...";
			return false;
		}
		log.debug("Dang:=======validateLogin已经通过=======");
		return true;
	}
	
	/**
	 * 是否需要转向
	 */
	public boolean isRedirctURL(Map<String, Object> session) {
		String url = (String) session.get(SessionConstant.SESS_REDIRECT);
		if (url == null)
			return false;
		else
			return true;
	}
}
