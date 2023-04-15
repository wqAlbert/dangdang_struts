package com.imvcc.actions;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsException;
import org.apache.struts2.json.annotations.JSON;

import com.imvcc.common.ImageCodeCommon;
import com.imvcc.common.SessionConstant;
import com.imvcc.util.ImageValidateUtil;

public class ImageValidateAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7273590894188381002L;
	private InputStream is; //������
	private String validateCode; //������
	private boolean ok;
	
	private Logger log=Logger.getLogger(ImageValidateAction.class);
	
	@JSON(serialize=false)//���и�ע��ı�ʾ������
	public InputStream getIs() {
		return is;
	}


	public void setIs(InputStream is) {
		this.is = is;
	}
	
	@JSON(serialize=false)
	public String getValidateCode() {
		return validateCode;
	}
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}

	/**
	 * Ajax��֤
	 * @return
	 * @throws InterruptedException 
	 */
	public String verfiyValidateCode() throws InterruptedException {
		log.debug("Dang:Ajax��֤��֤����ȷ��-"+validateCode);
		//ģ�������ӳ�
		Thread.sleep(2000);
		String sessCode=(String) session.get(SessionConstant.SESS_VALIDATE_CODE);
		if(sessCode==null  || sessCode=="") {
			//�˴�������֤����
			log.debug("Dang:Ajax����֤�����ݣ��зǷ����������");
			ok=false;
			return "error";
		}
		else if(sessCode.equalsIgnoreCase(validateCode)) {
			log.debug("Dang:Ajax��֤��ͨ��");
			ok=true;
			return "success";
		}
		else {
			log.debug("Dang:Ajax��֤����֤ʧ��");
			ok=false;
			return "success";
		}
	}
	public String execute() {
		try {
			//create image validate code in memory
			log.debug("Dang:���ڴ���������֤��ͼƬ");
			ImageCodeCommon imageCode=ImageValidateUtil.memoryImage();
			// put the validate image to InputStream
			log.debug("Dang:��ͼƬ����Inputstream");
			is=ImageValidateUtil.writeToInputStream(imageCode.getBufferedImage());
			//save the validate code to session
			log.debug("Dang:��֤��д��Session:"+imageCode.getValidateCode());
			session.put(SessionConstant.SESS_VALIDATE_CODE, imageCode.getValidateCode());
		} catch (IOException e) {
			e.printStackTrace();
			throw new StrutsException();
		}
		return "success";
	}
}
