package com.imvcc.actions.authorize;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.json.annotations.JSON;

import com.imvcc.actions.BaseAction;
import com.imvcc.common.FormConstant;
import com.imvcc.common.SessionConstant;
import com.imvcc.domain.User;
import com.imvcc.service.UserService;
import com.imvcc.service.ServiceFacotry;
import com.imvcc.util.EmailUtil;
import com.imvcc.util.EncoderUtil;
import com.imvcc.util.ValidatorUtil;

/**
 * action for authorize
 * 
 * @author Mr.Rong
 * 
 */
public class AuthorizeAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2087464023067813740L;
	private User user;
	private String password1;
	private String validateCode;

	private Logger log = Logger.getLogger(AuthorizeAction.class);
	private UserService userService =ServiceFacotry.getUserService();

	private boolean ok;
	private String email;
	
	//存储错误信息
	private Map<String, String> errors = new HashMap<String, String>();



	/**
	 *添加错误
	 * 
	 * @param key
	 * @param value
	 */
	public void addError(String key, String value) {
		errors.put(key, value);
	}

	/**
	 * 根据关键字得到错误
	 * 
	 * @param key
	 * @return
	 */
	@JSON(serialize = false)//不作为JSON数据发送
	public String getError(String key) {
		return errors.get(key);
	}

	@JSON(serialize = false)
	public Map<String, String> getErrors() {
		return errors;
	}
	
	@JSON(serialize = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JSON(serialize = false)
	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	@JSON(serialize = false)
	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	@JSON(serialize = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public boolean isOk() {
		return ok;
	}

	/**
	 * 注册方法的执行 
	 * @return
	 */
	public String regist() {
		if (!validateRegist()) {
			return "input";
		}
		// save user session,validateCode session, activeCodesession
		user.setLastLoginIp(request.getRemoteAddr());
		log.debug("Dang:生产UserServcie添加用户");
		user=userService.addUser(user);
		log.debug("Dang:用户添加完成，准备写入Session-" + user.getEmail());
		
		log.debug("Dang:产生Email激活码");
		String verifyCode = user.getEmailVerifyCode();
		session.put("testCode", verifyCode);
		
		// send email
		log.info("Dang:发送邮件到：" + user.getEmail());
		EmailUtil.sendEmail(user.getEmail(), verifyCode, "");

		session.put(SessionConstant.SESS_USER_AUTHORIZE, user);
		return "success";
	}

	/**
	 * ajax检测邮箱可用性
	 * 
	 * @return
	 * @throws InterruptedException 
	 */
	public String verifyEmail() throws InterruptedException {
		log.debug("Dang:Ajax验证邮箱的可用性:" + email);
		//模拟网络延迟和数据延迟
		Thread.sleep(2000);
		ok = isEmailUsed(email);
		log.debug("Dang:Ajax邮箱验证结果：" + ok);
		return "success";
	}

	/**
	 * 验证邮箱的可用性
	 * 
	 * @param email被验证的邮件地址
	 * @return
	 */
	private boolean isEmailUsed(String email) {
		User tmpUser = userService.findByEmail(email);
		if (tmpUser == null)
			return true;
		else
			return false;
	}
	/**
	 * 用户注销
	 * @return
	 */
	public String logout() {//是用户的web行为
		log.debug("Dang:用户注销 >>  ");
		if (session.containsKey(SessionConstant.SESS_USER_AUTHORIZE)) {
			session.remove(SessionConstant.SESS_USER_AUTHORIZE);
		}
		log.debug("Dang:注销成功！");
		return "success";
	}
	
	/**
	 * 验证regist,扩展-0-封装每种表单的验证
	 */
	public boolean validateRegist() {
		boolean result = true;
		String tmpStr;
		log.debug("======Dang:开始验证Regist========");
		log.debug("Dang:邮件地址的验证");
		tmpStr = user.getEmail();
		if (!(ValidatorUtil.requiredString(tmpStr, true))) {
			this.addError("user.email", "邮件地址不能为空，请输入");
			result = false;
		} else if (!ValidatorUtil.email(tmpStr)) {
			this.addError("user.email", "邮件地址格式不正确，请重新填写");
			result = false;
		}
		// 留了个个Bug ,heihei，session和事务的问题，想在的查找就没有关闭session
		else if (!isEmailUsed(tmpStr)) {
			this.addError("user.email", "邮件已经被使用，请重新填写");
			result = false;
		}
		
		log.debug("Dang:邮箱验证通过-"+result+"-,开始昵称的验证");
		tmpStr = user.getNickname();
		if (!ValidatorUtil.requiredString(tmpStr, true)) {
			this.addError("user.nickname", "昵称不能为空！");
			result = false;
		} else if (0 != ValidatorUtil.stringLength(tmpStr,
				FormConstant.MIN_NICK_NAME_LENGTH,
				FormConstant.MAX_NICK_NAME_LENGTH, true)) {
			this.addError("user.nickname", "昵称的长度不正确，请重新输入");
			result = false;
		}
		
		log.debug("Dang:昵称验证通过-"+result+"-,开始密码的验证");
		tmpStr = user.getPassword();
		String tmpStr1 = this.getPassword1();
		if (!ValidatorUtil.requiredString(tmpStr, true)) {
			this.addError("user.password", "密码不能为空");
			result = false;
		} else if (0 != ValidatorUtil.stringLength(tmpStr,
				FormConstant.MIN_PASSWORD_LENGTH,
				FormConstant.MAX_PASSWORD_LENGTH, true)) {
			this.addError("user.password", "密码长度不正确");
			result = false;
		} else if (!ValidatorUtil.requiredString(tmpStr1, true)) {
			this.addError("password1", "密码确认不能为空");
			result = false;
		} else if (0 != ValidatorUtil.stringLength(tmpStr1,
				FormConstant.MIN_PASSWORD_LENGTH,
				FormConstant.MAX_PASSWORD_LENGTH, true)) {
			this.addError("password1", "密码确认长度不正确");
			result = false;
		} else if (!ValidatorUtil.stringEquals(tmpStr, tmpStr1, true, true)) {
			this.addError("user.password", "两次输入的密码不同，请重新输入");
			result = false;
		}
		
		log.debug("Dang:密码验证通过-"+result+"-，验证校验码");
		tmpStr = this.getValidateCode();
		tmpStr1 = (String) session.get(SessionConstant.SESS_VALIDATE_CODE);
		if (tmpStr1 == null || tmpStr1 == "") {
			this.addError("validateCode", "非法注册");
			result = false;
		} else if (!ValidatorUtil.requiredString(tmpStr, true)) {
			this.addError("validateCode", "验证码不能为空");
			result = false;
		} else if (!tmpStr1.equalsIgnoreCase(tmpStr)) {
			this.addError("validateCode", "验证码输入错误");
			result = false;
		}
		log.debug("======Dang:完成验证Regist======-"+result+"-==");
		return result;
	}
}
