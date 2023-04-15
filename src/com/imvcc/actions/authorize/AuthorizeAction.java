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
	
	//�洢������Ϣ
	private Map<String, String> errors = new HashMap<String, String>();



	/**
	 *��Ӵ���
	 * 
	 * @param key
	 * @param value
	 */
	public void addError(String key, String value) {
		errors.put(key, value);
	}

	/**
	 * ���ݹؼ��ֵõ�����
	 * 
	 * @param key
	 * @return
	 */
	@JSON(serialize = false)//����ΪJSON���ݷ���
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
	 * ע�᷽����ִ�� 
	 * @return
	 */
	public String regist() {
		if (!validateRegist()) {
			return "input";
		}
		// save user session,validateCode session, activeCodesession
		user.setLastLoginIp(request.getRemoteAddr());
		log.debug("Dang:����UserServcie����û�");
		user=userService.addUser(user);
		log.debug("Dang:�û������ɣ�׼��д��Session-" + user.getEmail());
		
		log.debug("Dang:����Email������");
		String verifyCode = user.getEmailVerifyCode();
		session.put("testCode", verifyCode);
		
		// send email
		log.info("Dang:�����ʼ�����" + user.getEmail());
		EmailUtil.sendEmail(user.getEmail(), verifyCode, "");

		session.put(SessionConstant.SESS_USER_AUTHORIZE, user);
		return "success";
	}

	/**
	 * ajax������������
	 * 
	 * @return
	 * @throws InterruptedException 
	 */
	public String verifyEmail() throws InterruptedException {
		log.debug("Dang:Ajax��֤����Ŀ�����:" + email);
		//ģ�������ӳٺ������ӳ�
		Thread.sleep(2000);
		ok = isEmailUsed(email);
		log.debug("Dang:Ajax������֤�����" + ok);
		return "success";
	}

	/**
	 * ��֤����Ŀ�����
	 * 
	 * @param email����֤���ʼ���ַ
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
	 * �û�ע��
	 * @return
	 */
	public String logout() {//���û���web��Ϊ
		log.debug("Dang:�û�ע�� >>  ");
		if (session.containsKey(SessionConstant.SESS_USER_AUTHORIZE)) {
			session.remove(SessionConstant.SESS_USER_AUTHORIZE);
		}
		log.debug("Dang:ע���ɹ���");
		return "success";
	}
	
	/**
	 * ��֤regist,��չ-0-��װÿ�ֱ�����֤
	 */
	public boolean validateRegist() {
		boolean result = true;
		String tmpStr;
		log.debug("======Dang:��ʼ��֤Regist========");
		log.debug("Dang:�ʼ���ַ����֤");
		tmpStr = user.getEmail();
		if (!(ValidatorUtil.requiredString(tmpStr, true))) {
			this.addError("user.email", "�ʼ���ַ����Ϊ�գ�������");
			result = false;
		} else if (!ValidatorUtil.email(tmpStr)) {
			this.addError("user.email", "�ʼ���ַ��ʽ����ȷ����������д");
			result = false;
		}
		// ���˸���Bug ,heihei��session����������⣬���ڵĲ��Ҿ�û�йر�session
		else if (!isEmailUsed(tmpStr)) {
			this.addError("user.email", "�ʼ��Ѿ���ʹ�ã���������д");
			result = false;
		}
		
		log.debug("Dang:������֤ͨ��-"+result+"-,��ʼ�ǳƵ���֤");
		tmpStr = user.getNickname();
		if (!ValidatorUtil.requiredString(tmpStr, true)) {
			this.addError("user.nickname", "�ǳƲ���Ϊ�գ�");
			result = false;
		} else if (0 != ValidatorUtil.stringLength(tmpStr,
				FormConstant.MIN_NICK_NAME_LENGTH,
				FormConstant.MAX_NICK_NAME_LENGTH, true)) {
			this.addError("user.nickname", "�ǳƵĳ��Ȳ���ȷ������������");
			result = false;
		}
		
		log.debug("Dang:�ǳ���֤ͨ��-"+result+"-,��ʼ�������֤");
		tmpStr = user.getPassword();
		String tmpStr1 = this.getPassword1();
		if (!ValidatorUtil.requiredString(tmpStr, true)) {
			this.addError("user.password", "���벻��Ϊ��");
			result = false;
		} else if (0 != ValidatorUtil.stringLength(tmpStr,
				FormConstant.MIN_PASSWORD_LENGTH,
				FormConstant.MAX_PASSWORD_LENGTH, true)) {
			this.addError("user.password", "���볤�Ȳ���ȷ");
			result = false;
		} else if (!ValidatorUtil.requiredString(tmpStr1, true)) {
			this.addError("password1", "����ȷ�ϲ���Ϊ��");
			result = false;
		} else if (0 != ValidatorUtil.stringLength(tmpStr1,
				FormConstant.MIN_PASSWORD_LENGTH,
				FormConstant.MAX_PASSWORD_LENGTH, true)) {
			this.addError("password1", "����ȷ�ϳ��Ȳ���ȷ");
			result = false;
		} else if (!ValidatorUtil.stringEquals(tmpStr, tmpStr1, true, true)) {
			this.addError("user.password", "������������벻ͬ������������");
			result = false;
		}
		
		log.debug("Dang:������֤ͨ��-"+result+"-����֤У����");
		tmpStr = this.getValidateCode();
		tmpStr1 = (String) session.get(SessionConstant.SESS_VALIDATE_CODE);
		if (tmpStr1 == null || tmpStr1 == "") {
			this.addError("validateCode", "�Ƿ�ע��");
			result = false;
		} else if (!ValidatorUtil.requiredString(tmpStr, true)) {
			this.addError("validateCode", "��֤�벻��Ϊ��");
			result = false;
		} else if (!tmpStr1.equalsIgnoreCase(tmpStr)) {
			this.addError("validateCode", "��֤���������");
			result = false;
		}
		log.debug("======Dang:�����֤Regist======-"+result+"-==");
		return result;
	}
}
