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
	private InputStream is; //不发送
	private String validateCode; //不发送
	private boolean ok;
	
	private Logger log=Logger.getLogger(ImageValidateAction.class);
	
	@JSON(serialize=false)//含有该注解的表示不发送
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
	 * Ajax验证
	 * @return
	 * @throws InterruptedException 
	 */
	public String verfiyValidateCode() throws InterruptedException {
		log.debug("Dang:Ajax验证验证码正确性-"+validateCode);
		//模拟网络延迟
		Thread.sleep(2000);
		String sessCode=(String) session.get(SessionConstant.SESS_VALIDATE_CODE);
		if(sessCode==null  || sessCode=="") {
			//此处交给验证处理
			log.debug("Dang:Ajax无验证码数据！有非法进入的嫌疑");
			ok=false;
			return "error";
		}
		else if(sessCode.equalsIgnoreCase(validateCode)) {
			log.debug("Dang:Ajax验证码通过");
			ok=true;
			return "success";
		}
		else {
			log.debug("Dang:Ajax验证码验证失败");
			ok=false;
			return "success";
		}
	}
	public String execute() {
		try {
			//create image validate code in memory
			log.debug("Dang:在内存中生成验证码图片");
			ImageCodeCommon imageCode=ImageValidateUtil.memoryImage();
			// put the validate image to InputStream
			log.debug("Dang:将图片放在Inputstream");
			is=ImageValidateUtil.writeToInputStream(imageCode.getBufferedImage());
			//save the validate code to session
			log.debug("Dang:验证码写入Session:"+imageCode.getValidateCode());
			session.put(SessionConstant.SESS_VALIDATE_CODE, imageCode.getValidateCode());
		} catch (IOException e) {
			e.printStackTrace();
			throw new StrutsException();
		}
		return "success";
	}
}
