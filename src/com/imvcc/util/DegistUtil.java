package com.imvcc.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;
/**
 * Degist Util
 * @author Mr.rong
 *
 */
public class DegistUtil {
	
	private static final String HEX_NUMS_STR="0123456789ABCDEF";
	
	/**
	 * produce encryption code（加密）
	 * @param seq
	 * @return
	 */
	public static String produceDegistCode(String seq) {
		try {
			MessageDigest md5Code=MessageDigest.getInstance("md5");
			BASE64Encoder base64=new BASE64Encoder();
			byte[] bTmp=md5Code.digest(seq.getBytes());
			base64.encode(bTmp);
			seq=base64.encode(bTmp);
			return seq;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * MD5加密算法
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getMD5(String str) throws NoSuchAlgorithmException {
		 // 生成一个MD5加密计算摘要
		 MessageDigest md = MessageDigest.getInstance("MD5");
		 // 计算md5函数
		 md.update(str.getBytes());
		 // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
		 // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
		 return new BigInteger(1, md.digest()).toString(16);
	}
	
	/**
	 * 将16进制字符串转换成字节数组
	 * @param hex
	 * @return
	 */
	public static byte[] hexStringToByte(String hex) {
		 int len = (hex.length() / 2);
		 byte[] result = new byte[len];
		 //toCharArray将此字符串转换为一个新的字符数组
		 char[] hexChars = hex.toCharArray();
		 for (int i = 0; i < len; i++) {
			 int pos = i * 2;
			 result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4 | HEX_NUMS_STR.indexOf(hexChars[pos + 1]));
		 }
		 return result;
	}
		
	public static void main(String[] args) {
		String pass="111111";
		System.out.println("MM:"+produceDegistCode(pass));
	}
	
	
}
