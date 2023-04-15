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
 * �ṩע�ἤ��Ŀ����߼�
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
	 * ������
	 * @return
	 */
	public String verify() {
		if(!validateVerfify()) return "input";

		boolean actived=true;
		log.debug("Dang:��ʼ����������������User Email Verfify code-"+emailVerifyCode+"�ȴ�session�м���");

		//actived=userService.activeUser(emailVerifyCode,session);
		Integer userId = EncoderUtil.getIDFromUUID(emailVerifyCode);
		System.out.println("userId="+userId);

		User user = (User) session.get(SessionConstant.SESS_USER_AUTHORIZE);
		System.out.println("user="+user);

		if (user != null && user.getId().equals(userId)) {
			System.out.println("------" + user.getId() + "++" + userId);
			System.out.println("------" + user.getEmailVerifyCode() + "++"
					+ emailVerifyCode);
			log.debug("Dang:���Լ����˻�");
			user.setIsEmailVerify(true);
			userService.updateUser(user);
			log.debug("Dang:����ɹ�");
		} else {
			this.addError("emailVerifyCode", "����ʧ��");
			return "input";
		}
		
		if(isRedirctURL(session)) {
			log.debug("Dang:����ת��:"+session.get(SessionConstant.SESS_REDIRECT));
			return "redirect";
		}
		return "success";
	}
	/**
	 * �������ı�У��
	 * @return
	 */
	public boolean validateVerfify() {
		String verifyCode=EncoderUtil.getUUID(emailVerifyCode);
		log.debug("Dang:��֤������ķǿ�");
		if(!ValidatorUtil.requiredString(emailVerifyCode, false)) {
			this.addError("emailVerifyCode", "�����벻��Ϊ��");
			return false;
		}
		log.debug("Dang:��֤������ĺϷ���");
		if(verifyCode==null) {
			this.addError("emailVerifyCode", "�Ƿ��ļ�����");
			return false;
		}
		Integer userId=EncoderUtil.getIDFromUUID(emailVerifyCode);
		if(userId==null) {
			this.addError("emailVerifyCode", "�Ƿ��ļ�����...");
			return false;
		}
		log.debug("Dang:��������֤ͨ��");
		return true;
	}
	
	/**
	 * �Ƿ���Ҫת��:���ܻᱻ����Action����
	 */
	public boolean isRedirctURL(Map<String, Object> session) {
		String url = (String) session.get(SessionConstant.SESS_REDIRECT);
		if (url == null)
			return false;
		else
			return true;
	}
}
