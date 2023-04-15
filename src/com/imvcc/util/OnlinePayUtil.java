package com.imvcc.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.imvcc.domain.Order;

public class OnlinePayUtil {
	/**  
     * ���ݶ�������֧�����ӿ�URL.  
     * @param httpRequest  
     * @param order ����ʵ��  
     * @return  
     * @throws Exception  
     */  
    public static String makeOrderAlipayUrl(HttpServletRequest httpRequest,Order order) throws Exception {   
        HashMap hm = new HashMap();   
        hm.put("_input_charset",httpRequest.getCharacterEncoding());//������ͬ�ı��뷽ʽ   
        hm.put("body","����www.xxx.com�ϵĶ���,�۸�"+order.getTotalPrice());//��д������֧����ҳ������ʾ�ĸ���������Ϣ   
        hm.put("discount","-5");//��д�ۿ���Ϣ -5��ʾ�ֿ�5Ԫ   
        hm.put("logistics_fee","10");//��������   
        hm.put("logistics_payment","BUYER_PAY");//��������֧���� BUYER_PAY=���֧����������   
        hm.put("logistics_type","EXPRESS");//������ʽ   
        hm.put("notify_url","http://www.xxx.com/payref.jsp");//�ͻ������,֧�������õ�ҳ��   
        hm.put("out_trade_no",order.getId()+"");//�ⲿ���׺�,��þ���Ψһ��,�ڻ�ȡ֧���������ĸ�����Ϣʱʹ��.   
        hm.put("partner","2088102123456789");//�����̻���(��Ҫ����)
        hm.put("agent","2088102123456789");//֧������������id(��Ҫ����)
        hm.put("payment_type","1");//֧������ 1=��Ʒ����,2=������,...   
        hm.put("price",order.getTotalPrice()+"");//���������Ϣ   
        hm.put("quantity","1");//������Ʒ����,һ�㶼��д1,���ǰ�������������������   
        hm.put("return_url","http://www.xxx.com/payref.jsp");//�ͻ�����ɹ���,��ʾ���ͻ���ҳ��   
        hm.put("seller_email","alipay@xxx.com");//���֧�����˻�email (��Ҫ����)  
        hm.put("service","create_direct_pay_by_user");//create_direct_pay_by_user=ֱ�Ӹ���,trade_create_by_buyer ��������    
        hm.put("subject","www.xxx.com�Ķ���");//��д������֧����ҳ������ʾ�ĸ��������Ϣ   
        String payGateway = "https://www.alipay.com/cooperate/gateway.do?";//��ת��֧������urlͷ   
        String url = makeUrl(hm,"100000000",httpRequest.getCharacterEncoding(),payGateway);//securityCode(��ȫ��) (��Ҫ����)   
        return url;
    }   
       
       
    /**  
     * ���ݴ���Ĳ�������alipay��֧��URL  
     * @param hm ����ֵ  
     * @param securityCode ��ȫ��  
     * @param charset ����  
     * @param payGateway ֧����gateway  
     * @return  
     */  
    public static String makeUrl(HashMap hm,String securityCode,String charset,String payGateway) throws Exception{   
        List keys = new ArrayList(hm.keySet());   
        Collections.sort(keys);//֧����Ҫ��������밴��ĸ����   
        StringBuffer content = new StringBuffer();   
        for (int i = 0; i < keys.size(); i++) {   
            content.append((String) keys.get(i));   
            content.append("=");   
            content.append((String) hm.get((String) keys.get(i)));   
            if (i != keys.size() - 1) {   
                content.append("&");   
            }   
        }   
        content.append(securityCode);   
        String sign = md5(content.toString(),charset);   
        content.delete(0,content.length());   
        content.append(payGateway);   
        for (int i = 0; i < keys.size(); i++) {   
            content.append(keys.get(i));
            content.append("=");
            content.append(URLEncoder.encode((String) hm.get(keys.get(i)), charset));
            content.append("&");   
        }   
        content.append("sign=");   
        content.append(sign);   
        content.append("&sign_type=MD5");   
        keys.clear();   
        keys = null;   
        return content.toString();   
    }   
       
    /**  
     * ����md5�����ַ���.  
     * @param str Դ�ַ���  
     * @param charset ���뷽ʽ  
     * @return  
     *  
     */  
    public static String md5(String str,String charset) {   
        if (str == null)   
            return null;   
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',   
                'a', 'b', 'c', 'd', 'e', 'f' };   
           
        MessageDigest md5MessageDigest = null;   
        byte[] md5Bytes = null;   
        char md5Chars[] = null;   
        byte[] strBytes = null;   
        try {   
            strBytes = str.getBytes(charset);   
            md5MessageDigest = MessageDigest.getInstance("MD5");   
            md5MessageDigest.update(strBytes);   
            md5Bytes = md5MessageDigest.digest();   
            int j = md5Bytes.length;   
            md5Chars = new char[j * 2];   
            int k = 0;   
            for (int i = 0; i < j; i++) {   
                byte md5Byte = md5Bytes[i];   
                md5Chars[k++] = hexDigits[md5Byte >>> 4 & 0xf];   
                md5Chars[k++] = hexDigits[md5Byte & 0xf];   
            }   
            return new String(md5Chars);   
        } catch (NoSuchAlgorithmException e) {   
            //Log.output(e.toString(), Log.STD_ERR);   
            return null;   
        } catch (UnsupportedEncodingException e) {   
            //Log.output(e.toString(), Log.STD_ERR);   
            return null;   
        } finally {   
            md5MessageDigest = null;   
            strBytes = null;   
            md5Bytes = null;   
        }   
    }  

}
