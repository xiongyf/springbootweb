package com.springbootweb.common.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class CommonUtils {

	public static String encodePassword(String password) {
		String algorithmName = "md5";
		RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		String salt = randomNumberGenerator.nextBytes().toHex();
		System.out.println(salt);
		String encodedPassword = new SimpleHash(algorithmName, password, salt).toHex();
		return encodedPassword;
	}

	public static void main(String[] args) {
		
		String encodedPassword = new SimpleHash("md5", "admin", "98c0217427bf2d45a4a03885f1787cc3").toHex();
		System.out.println(encodedPassword);
		//d933e56cfe088408f456db70550b67b2
	}
}
