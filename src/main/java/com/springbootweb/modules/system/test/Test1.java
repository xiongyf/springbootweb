package com.springbootweb.modules.system.test;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class Test1 {
	
	@Test
	public void test1() {

		String algorithmName = "md5";
		RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		String salt = randomNumberGenerator.nextBytes().toHex();
		System.out.println(salt);
		String newPassword = new SimpleHash(algorithmName, "123", salt).toHex();
		System.out.println(newPassword);

	}

	public void test2() {
		
	}
}
