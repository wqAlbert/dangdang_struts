package com.imvcc.util;

import java.util.Enumeration;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;

/**
 * ֧��������֪ͨ�ӿ�.
 * 
 * @version 1.0.0
 */
public final class NotifyUrlMgr {

	public static String insert(HttpServletRequest httpRequest) {
		// ��������ͽ��б�Ҫ�ĳ�ʼ������
		Enumeration parameterNames = null;
		String parameterName = null;
		String parameterValue = null;
		int count = 0;
		Vector[] params = null;
		Vector vParameterName = new Vector();
		Vector vParameterValue = new Vector();

		try {
			String orderId = httpRequest.getParameter("out_trade_no");// ������
			if (orderId == null || "".equals(orderId))
				orderId = "-1";
			parameterNames = httpRequest.getParameterNames();
			boolean isPrint = false;
			while (parameterNames.hasMoreElements()) {// ѭ����ȡ֧�������������в�����Ϣ
				parameterName = (String) parameterNames.nextElement();
				parameterValue = httpRequest.getParameter(parameterName);
				if (parameterValue == null)
					parameterValue = "";
				vParameterName.add(parameterName);
				vParameterValue.add(parameterValue);
				count++;
			}

			// ������Ӷ��յ���Ϣ�Ĵ���:һ���ǽ���Щ��Ϣ�������ݿ�,Ȼ��Կͻ��Ķ������д���.

			return null;
		} catch (Exception e) {
			return e.toString();
		} finally {
			//   
		}
	}

}
