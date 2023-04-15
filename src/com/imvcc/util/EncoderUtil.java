package com.imvcc.util;

import java.io.IOException;
import java.util.UUID;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * Encode util, produce uuid ,encription code
 * @author Mr.rong
 *
 */
public class EncoderUtil {
	/**
	 * 生成激活码
	 * @param id
	 * @return
	 */
	public static String produceUUIDCode(Integer id) {
		UUID uuid=UUID.randomUUID();
		System.out.println("①uuid="+uuid);
		BASE64Encoder base64=new BASE64Encoder();
		
		String uuid_id=uuid.toString()+"-"+id;
//		base64.encode(uuidCode.getBytes());
		System.out.println("②UUID_id:"+uuid_id);
		String uuidCode=new String(base64.encode(uuid_id.getBytes()));
		System.out.println("③uuidCode(激活码)="+uuidCode);
		return uuidCode;
	}
	/**
	 * 从用户的激活码获取UUID
	 * @param uuidCode
	 * @return
	 */
	public static String getUUID(String uuidCode) {
		String strUUID=null;
		try {
			BASE64Decoder base64=new BASE64Decoder();
			byte[] b=base64.decodeBuffer(uuidCode);//字节数组
			strUUID=new String(b);
			if(strUUID.lastIndexOf("-")==-1) {
				return null;
			}
			strUUID=strUUID.substring(0,strUUID.lastIndexOf("-"));
			System.out.println("④strUUID="+strUUID);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return strUUID;
	}
	/**
	 * 从UUID获得用户id
	 * @param uuid
	 * @return
	 */
	public static Integer getIDFromUUID(String uuidCode) {
		String strUUID=null;
		Integer id;
		try {
			BASE64Decoder base64=new BASE64Decoder();
			byte[] b=base64.decodeBuffer(uuidCode);
			strUUID=new String(b);
			if(strUUID.lastIndexOf("-")==-1) {
				return null;
			}
			strUUID=strUUID.substring(strUUID.lastIndexOf("-")+1);
			id=new Integer(Integer.parseInt(strUUID));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		catch(Exception e) {
			return null;
		}
		return id;
	}

	public static void main(String[] args) {
		String uuidCode=produceUUIDCode(6);
		System.out.println("main1-uuidCode(激活码):"+uuidCode);
		System.out.println("main2-getUUID:"+getUUID(uuidCode));
		System.out.println("main3-UserID:"+getIDFromUUID(uuidCode));
	}
}
