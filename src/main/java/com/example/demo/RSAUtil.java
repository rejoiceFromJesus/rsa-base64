package com.example.demo;
 
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
 
public class RSAUtil {
	
	public static String privateKeyStr = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCBX1Olatpn/OUfRWSfXPHWse8x\r\n" + 
			"lgOYCmKcmVS2X5Os362Sto/W9okg2iAmLq5Z1waNXBD7wKAVudpBGjzb506pp5LXjEhp6DkfGxgI\r\n" + 
			"QIMi7tiAbXdKlunqUoZuezoaTc9YnGVkkrJaMLsUNl5u4nSx0tbCsZzxCpzgBpKRMnnos8nEbcQw\r\n" + 
			"ugAN6GWyjIB7LkfqLqqwC/EE7LAyNiVWWPeFf6NY6I/wVsT1Hb/tZad/u7THVyhbLJiE0feoC5TX\r\n" + 
			"OmNJDgOIywDZthSkZXusVR70s3kdze7Yd0KCnktdCTsK7MgJAyXGUzgkpAA0801dhVbNqSQwbMSY\r\n" + 
			"ZS1dZ/Fgr+qrAgMBAAECggEAUmNq2YVa4a5kG64Njo+aU6etNF7cmw7CDgh3skzbttQbrbsljzgh\r\n" + 
			"zBIdNzOzRPNy8MMY7bqWOD/Lhno3F7QOnGxjT3G/C/MfrXeLrnWnDbAdoGwV9CJ3hQpZIjLHpci3\r\n" + 
			"qy6uQ4ZInrc57H1TVjpRJo2mxwZlc/vWO7Tz0hPbHx23Fhj+kaT48ul/bqGgXyEdCfglUpP+reWT\r\n" + 
			"FaJkTCwoqaQtBhw+S42Wo48BHmvjWQrvB5Y5GvfL4oDMJZZL1pbUM8aUJsTeJ3gv2hEAGYcZspnK\r\n" + 
			"K7oRbNEoZ/VkUzX3j/yt9Hq3oYt3Z9Ngscl6sEm7CAZMqIN+Hqu4dWSCEtX1QQKBgQDIhjLi+dbN\r\n" + 
			"D8s8d0nM0P87/Ey846os50rG2GW0iUnb8p+8lkJOdTzmnR8Apnlja9yn5wbAbHOXlBTG9tbjJlwy\r\n" + 
			"a4lZcpeeLlg9E8bIuspAjj0cWMEEwfUhvL+vptjDto0tdFA2/pCgbVL6OIa/eMblA9+P/MWp1RNW\r\n" + 
			"96NHPwsOXQKBgQClKeoU0fol8M0Dyj67tSqazZyMOtTHfXDVzRiP66CFxoQZVn2ZOErgWTx/gFqE\r\n" + 
			"Ihy7NSxu5KhgOIyebGTr6E0Snzsbv/pV4m8FVW+UA4HR7Qp5+1hm7gJqOXGYw4W7vwbAA7EkF/ic\r\n" + 
			"/tgP0UcinWroCXj6B69Ow6bhudgmFk78pwKBgQClDZElMvCTqcRn6OheJklwXqPrdpnEjD0In2ZX\r\n" + 
			"zAl3eaXx3NyHKAkQ6ZNzPZP9OfNOYOM10A71XXYcr2EBZ7nL5qgPcDtMgGkxHO4qm1WO/YVfp854\r\n" + 
			"1A2pz2sYcgVKUyCxoWPFYJQ6+MGsJIsFX6yXJTEHm9A3kqNwmjVTl5jKIQKBgCTWUWAtaQZ3S7KR\r\n" + 
			"tCY3hPYb79OS94w63STCZr0Kx7Wujh38nWzEEmcxX/3sCcQ4u4HBnVRqaWd0lWBsOqInILGLlCpf\r\n" + 
			"Sh1HsI1W/djcY2MH5HNKbPAgG6fHgh+ZVEWImJ/Q8vxi4E9bw6LbgWsmAwcXvcS+XApMNvdXdO8h\r\n" + 
			"wKI3AoGAdoHBhUxLQt3N0iTdwHEDWqe42oLtxsQIY8BMh+T9sWfQrJErO8byvLpZO0FIWPoKoUIK\r\n" + 
			"WgG54y8AfrT4pCudycNytCCVEvjWS/y6vuYY3dBfQEs3gmqe1LYa6+6wxW7KwN9RciPdPedxMdPO\r\n" + 
			"5RfRUwcVPcOj26p+2lInB5GH/hU=";
	
	public static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgV9TpWraZ/zlH0Vkn1zx1rHvMZYDmApi\r\n" + 
			"nJlUtl+TrN+tkraP1vaJINogJi6uWdcGjVwQ+8CgFbnaQRo82+dOqaeS14xIaeg5HxsYCECDIu7Y\r\n" + 
			"gG13Spbp6lKGbns6Gk3PWJxlZJKyWjC7FDZebuJ0sdLWwrGc8Qqc4AaSkTJ56LPJxG3EMLoADehl\r\n" + 
			"soyAey5H6i6qsAvxBOywMjYlVlj3hX+jWOiP8FbE9R2/7WWnf7u0x1coWyyYhNH3qAuU1zpjSQ4D\r\n" + 
			"iMsA2bYUpGV7rFUe9LN5Hc3u2HdCgp5LXQk7CuzICQMlxlM4JKQANPNNXYVWzakkMGzEmGUtXWfx\r\n" + 
			"YK/qqwIDAQAB";
	
	public static String decode(String codeByPublicKey) {
		try {
			PrivateKey privateKey = RSAUtil.string2PrivateKey(privateKeyStr);
			//加密后的内容Base64解码
			byte[] base642Byte = RSAUtil.base642Byte(codeByPublicKey);
			//用私钥解密
			byte[] privateDecrypt = RSAUtil.privateDecrypt(base642Byte, privateKey);
			//解密后的明文
			return new String(privateDecrypt);
		} catch(Exception e) {
			throw new RuntimeException("decode failed:",e);
		}
		
	}
	//生成秘钥对
	public static KeyPair getKeyPair() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		return keyPair;
	}
	
	//获取公钥(Base64编码)
	public static String getPublicKey(KeyPair keyPair){
		PublicKey publicKey = keyPair.getPublic();
		byte[] bytes = publicKey.getEncoded();
		return byte2Base64(bytes);
	}
	
	//获取私钥(Base64编码)
	public static String getPrivateKey(KeyPair keyPair){
		PrivateKey privateKey = keyPair.getPrivate();
		byte[] bytes = privateKey.getEncoded();
		return byte2Base64(bytes);
	}
	
	//将Base64编码后的公钥转换成PublicKey对象
	public static PublicKey string2PublicKey(String pubStr) throws Exception{
		byte[] keyBytes = base642Byte(pubStr);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}
	
	//将Base64编码后的私钥转换成PrivateKey对象
	public static PrivateKey string2PrivateKey(String priStr) throws Exception{
		byte[] keyBytes = base642Byte(priStr);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}
	
	//公钥加密
	public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception{
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] bytes = cipher.doFinal(content);
		return bytes;
	}
	
	//私钥解密
	public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception{
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] bytes = cipher.doFinal(content);
		return bytes;
	}
	
	//字节数组转Base64编码
	public static String byte2Base64(byte[] bytes){
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(bytes);
	}
	
	//Base64编码转字节数组
	public static byte[] base642Byte(String base64Key) throws IOException{
		BASE64Decoder decoder = new BASE64Decoder();
		return decoder.decodeBuffer(base64Key);
	}
}
