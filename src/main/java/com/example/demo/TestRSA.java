package com.example.demo;
 
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Random;

import javax.sound.midi.SysexMessage;

import org.junit.Test;
 
public class TestRSA {
	
	@Test
	public void testBase64() {
		System.out.println(RSAUtil.byte2Base64("Hello World!".getBytes()));
	}
 
	@Test
	public void testRSA(){
		try {
			//===============生成公钥和私钥，公钥传给客户端，私钥服务端保留==================
			//生成RSA公钥和私钥，并Base64编码
			KeyPair keyPair = RSAUtil.getKeyPair();
			String publicKeyStr = RSAUtil.getPublicKey(keyPair);
			String privateKeyStr = RSAUtil.getPrivateKey(keyPair);
			System.out.println("RSA公钥Base64编码:" + publicKeyStr);
			System.out.println("RSA私钥Base64编码:" + privateKeyStr);
			
			//=================客户端=================
			//hello, i am infi, good night!加密
			String message = "123456789";
			/*Random random = new Random();
			for(int i = 0; i< 1; i++) {
				message+=random.nextInt(7);
			}*/
			//将Base64编码后的公钥转换成PublicKey对象
			PublicKey publicKey = RSAUtil.string2PublicKey(publicKeyStr);
			//用公钥加密
			byte[] publicEncrypt = RSAUtil.publicEncrypt(message.getBytes(), publicKey);
			//加密后的内容Base64编码
			String byte2Base64 = RSAUtil.byte2Base64(publicEncrypt);
			System.out.println("公钥加密并Base64编码的结果：" + byte2Base64);
			
			
			//##############	网络上传输的内容有Base64编码后的公钥 和 Base64编码后的公钥加密的内容     #################
			
			
			
			//===================服务端================
			//将Base64编码后的私钥转换成PrivateKey对象
			PrivateKey privateKey = RSAUtil.string2PrivateKey(privateKeyStr);
			//加密后的内容Base64解码
			byte[] base642Byte = RSAUtil.base642Byte(byte2Base64);
			//用私钥解密
			byte[] privateDecrypt = RSAUtil.privateDecrypt(base642Byte, privateKey);
			//解密后的明文
			System.err.println("加密之前："+message);
			System.out.println("解密后的明文: " + new String(privateDecrypt));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDecode() throws Exception{
		String privateKeyStr = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCBX1Olatpn/OUfRWSfXPHWse8x\r\n" + 
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
		String codeStr = "UnCYVewlrxrMrzhjO6GiU7kKq2p508FgLFScCZUqbTZeacQIZ/pfx0y7hKBE1AOEqd5v0pxyE/oUYYGAFnH6aI48zk8KrXySFkDtif8Qn630lDWvHWBysdtZ/9Aeq/ePNHin89kTN61uBWcVzFS+fKzjQ8bpK6VYyIibSYXdGrUKkxpQxCrDtv2hxaXHCzJSNDjDuhOtnCvB9DfUWXLh9elFkypXycv5GlcbyApIbI5Hz540lQ/RK7eOr1IlEzyVw2FttqpJbqxh54DZVS0Y/1UX0ASxCphaMOXRqDG3sVLBiq3dH41yTcuxB0glOcFwouYsD7+srMggz5+Ws4XDxw==";
		PrivateKey privateKey = RSAUtil.string2PrivateKey(privateKeyStr);
		//加密后的内容Base64解码
		byte[] base642Byte = RSAUtil.base642Byte(codeStr);
		//用私钥解密
		byte[] privateDecrypt = RSAUtil.privateDecrypt(base642Byte, privateKey);
		//解密后的明文
		System.out.println("解密后的明文: " + new String(privateDecrypt));
	}
 
}
