package com.jusfoun.test.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JasyptTest {

	@Autowired
	StringEncryptor stringEncryptor;

	/**
	 * 说明：测试加密
	 * 
	 * <pre>
	 * 【命令使用说明】
	 * cmd在这个包下执行如下命令，它会返回你加密后的密码：
	 *	java -cp jasypt-1.9.2.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="root" password=zhang algorithm=PBEWithMD5AndDES
	 * 其中：
	 *	input:是数据库的明文密码
	 *	password：是机密的盐
	 *	algorithm：是加密的方式(默认)
	 *
	 * 然后在配置文件中配置盐值：
	 *	jasypt.encryptor.password=nmyswls
	 * </pre>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年9月13日 下午1:40:11
	 */
	@Test
	public void encryptPwd() {
		String result = stringEncryptor.encrypt("root");
//		String result = stringEncryptor.encrypt("jusfoun");
		System.out.println(result);
	}
}
