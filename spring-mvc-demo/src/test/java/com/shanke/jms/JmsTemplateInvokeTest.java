//package com.shanke.jms;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//
//import javax.annotation.Resource;
//import javax.jms.Queue;
//
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//
//import com.shanke.jms.sync.JmsTemplateInvoke;
//
///**
// * 〈一句话功能简述〉<br>
// * 〈功能详细描述〉
// * 
// * @see [相关类/方法]（可选）
// * @since [产品/模块版本] （可选）
// */
//@ContextConfiguration(locations = { "file:src/main/java/com/iteye/jms/send/bean.xml" })
//public class JmsTemplateInvokeTest extends AbstractTestNGSpringContextTests {
//
//	@Resource(name = "jmsTemplate")
//	private JmsTemplate jmsTemplate;
//
//	@Resource(name = "requestQueue")
//	private Queue requestQueue;
//
//	@Resource(name = "jmsTemplateInvoke")
//	private JmsTemplateInvoke jmsTemplateInvoke;
//
//	/**
//	 * 
//	 * 发送MQ消息 <br>
//	 * 〈功能详细描述〉
//	 * 
//	 * @throws Exception
//	 * @see [相关类/方法](可选)
//	 * @since [产品/模块版本](可选)
//	 */
//	// @Test
//	public void testSendMqMsg1() throws Exception {
//		String requestMessage = getEsbXml();
//		JmsTemplateInvoke jmsTemplateInvoke = new JmsTemplateInvoke();
//		jmsTemplateInvoke.setJmsTemplate(jmsTemplate);
//		jmsTemplateInvoke.setRequestQueue(requestQueue);
//		jmsTemplateInvoke.asynRequestProcessor(requestMessage);
//	}
//
//	/**
//	 * 发送MQ消息 <br>
//	 * 〈功能详细描述〉
//	 * 
//	 * @throws Exception
//	 * @see [相关类/方法](可选)
//	 * @since [产品/模块版本](可选)
//	 */
//	// @Test
//	public void testSendMqMsg2() throws Exception {
//		String requestMessage = getEsbXml();
//		jmsTemplateInvoke.asynRequestProcessor(requestMessage);
//	}
//
//	/**
//	 * 
//	 * 获取请求报文 <br>
//	 * 〈功能详细描述〉
//	 * 
//	 * @return
//	 * @throws Exception
//	 * @see [相关类/方法](可选)
//	 * @since [产品/模块版本](可选)
//	 */
//	private String getEsbXml() throws Exception {
//		StringBuilder esbXml = new StringBuilder(10000);
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(
//				new FileInputStream(
//						"D:/CReviewMgmt-syncOrderCommentInfoNew_in_zr.xml"),
//				"UTF-8"));
//		String line = null;
//		while ((line = br.readLine()) != null) {
//			esbXml.append(line);
//		}
//		br.close();
//		return esbXml.toString();
//	}
//}
