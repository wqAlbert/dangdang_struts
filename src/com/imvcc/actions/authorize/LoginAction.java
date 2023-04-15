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
 * ����Login��ת����֤��
 * 
 * @author Mr.rong
 * 
 */
public class LoginAction extends BaseAction {
	private static final long serialVersionUID = -6132027319709991737L;
	/**
	 * ת�����
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
	 * �����¼
	 * 
	 * @return
	 */

	public String login() {
		if(!validateLogin()) {
			return "input";
		}
		
		//����û��Ƿ�����Ч���û�
		log.debug("Dang:��ʼ��֤�û�");
		System.out.println("user��:"+user.getPassword());
		user=userService.validateUser(user);
		if(user==null) {
			System.out.println("Dang:�û���¼ʧ��");
			log.debug("Dang:�û���¼ʧ��");
			this.error = "Email��������������������µ�¼";
			return "error";
		}
		else if("N".equals(user.getIsEmailVerify())) {
			log.debug("Dang:�û���¼�ɹ�,�û�û�м����˻�");
			return "unactived";
		}
		
		log.debug("Dang:�ɹ���¼:"+user.getEmail()+"-"+user.getLastLoginIp());
		//�������Ч�û��������û�
		log.debug("Dang:�����û���IP");
		user.setLastLoginIp(request.getRemoteAddr());
		user.setLastLoginTime(System.currentTimeMillis());
		userService.updateUser(user);
		session.put(SessionConstant.SESS_USER_AUTHORIZE,user);
		
		//����Ƿ�����ת��־������н��뵽toInputAddress.action(OrderAction#toInputAddress())
		if(isRedirctURL(session)) {
			System.out.println("redirect");
			log.debug("Dang:����ת��"+session.get(SessionConstant.SESS_REDIRECT));
			return "redirect";
		}
		return "success";
	}

	/**
	 * Login У����
	 */
	public boolean validateLogin() {//�ֶ���֤
		log.debug("Dang:=======��ʼvalidateLogin=======");
		log.debug("Dang:Email����Password�ǿ���֤");
		if (!ValidatorUtil.requiredString(user.getEmail(),
				ValidatorUtil.DO_TRIM)
				|| !ValidatorUtil.requiredString(user.getPassword(),
						ValidatorUtil.DO_TRIM)) {
			
			this.error = "Email���ӻ������벻��Ϊ�գ�������";
			return false;
		}
		log.debug("Dang:�����ʼ���ʽ��֤");
		if (!ValidatorUtil.email(user.getEmail())) {
			this.error = "�����ʼ���ʽ���� ,����������";
			return false;

		}
		log.debug("Dang:���볤����֤");
		if (ValidatorUtil.stringLength(user.getPassword(),
				FormConstant.MIN_PASSWORD_LENGTH,
				FormConstant.MAX_PASSWORD_LENGTH, ValidatorUtil.DO_TRIM) != 0) {
			this.error = "Email��������������������µ�¼...";
			return false;
		}
		log.debug("Dang:=======validateLogin�Ѿ�ͨ��=======");
		return true;
	}
	
	/**
	 * �Ƿ���Ҫת��
	 */
	public boolean isRedirctURL(Map<String, Object> session) {
		String url = (String) session.get(SessionConstant.SESS_REDIRECT);
		if (url == null)
			return false;
		else
			return true;
	}
}
