package com.jusfoun.modbus;

import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Test;

import com.jusfoun.common.util.modbus.ModbusUtils;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.sero.util.queue.ByteQueue;

/**
 * 描述 :传感器数据采集接口测试. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年10月20日 下午1:41:31
 */
public class ModbusClientTest {

	@Test
	public void test() throws ModbusInitException, ModbusTransportException {
		ByteQueue byteQueue = ModbusUtils.modbusTCP("60.15.167.154", 3001, 1, 3, 30);
		int size = byteQueue.size();
		String format = null;
		BigInteger b1 = null;
		String b2 = null;
		for (int i = 0; i < size; i++) {
			byte b = byteQueue.pop();
			format = String.format("%02X", new Object[] { Byte.valueOf(b) });
			b1 = new BigInteger(format, 16);
			b2 = b1.toString(10);
			System.out.println(b2);
		}
	}

	@Test
	public void test1() throws ModbusInitException, ModbusTransportException {
		ByteQueue byteQueue = ModbusUtils.modbusTCP("60.15.167.154", 3001, 1, 3, 30);
		byte[] buf = new byte[4];
		int peek = byteQueue.pop(buf, 10, 2);
		System.out.println(Arrays.toString(buf) + " " + peek);
	}

	@Test
	public void test2() {
		BigInteger b1 = new BigInteger("0187", 16);
		String b2 = b1.toString(10);
		System.out.println(b2);
	}

	@Test
	public void test3() throws ModbusInitException, ModbusTransportException {
		ByteQueue byteQueue = ModbusUtils.modbusTCP("60.15.167.154", 3001, 1, 3, 30);
		byte[] buf = byteQueue.peekAll();

		String format = null;
		BigInteger b1 = null;
		String b2 = null;
		for (byte b : buf) {
			format = String.format("%02X", new Object[] { Byte.valueOf(b) });
			b1 = new BigInteger(format, 16);
			b2 = b1.toString(10);
			System.out.println(b2);
		}
	}

	@Test
	public void test4() throws ModbusInitException, ModbusTransportException {
		ByteQueue byteQueue = ModbusUtils.modbusTCP("60.15.167.154", 3001, 1, 3, 30);
		byte[] buf = byteQueue.peekAll();

		String[] hexBuf = new String[buf.length];
		for (int i = 0; i < buf.length; i++) {
			hexBuf[i] = String.format("%02X", new Object[] { Byte.valueOf(buf[i]) });
		}
		System.out.println(Arrays.toString(hexBuf));
	}

	@Test
	public void test5() throws ModbusInitException, ModbusTransportException {
		String[] hexBuf = ModbusUtils.hexTCP("60.15.167.154", 3001, 1, 3, 30);
		System.out.println(Arrays.toString(hexBuf));
	}

	@Test
	public void test6() throws ModbusInitException, ModbusTransportException {
		String[] hexBuf = ModbusUtils.hexTCP("60.15.167.154", 3001, 1, 3, 30);
		if (hexBuf != null && hexBuf.length > 3) {
			BigInteger b = new BigInteger(hexBuf[2], 16);
			int len = b.intValue();
			String[] buf = new String[len];
			System.arraycopy(hexBuf, 3, buf, 0, len);
			// System.out.println(len + " " + hexBuf.length + "\n" +
			// Arrays.toString(buf));

			int len1 = len / 2;
			String[] buf1 = new String[len1];
			for (int i = 0; i < len1; i++) {
				buf1[i] = buf[2 * i] + buf[2 * i + 1];
			}
			// System.out.println(Arrays.toString(buf1));

			float[] fbuf = new float[buf1.length];
			for (int i = 0; i < fbuf.length; i++) {
				fbuf[i] = (new BigInteger(buf1[i], 16).floatValue()) / 10.0f;
			}
			// System.out.println(Arrays.toString(fbuf));
		}
	}

	@Test
	public void test7() throws ModbusInitException, ModbusTransportException {
		float[] hexBuf = ModbusUtils.floatTCP("60.15.167.154", 3001, 1, 3, 40);
		System.out.println(Arrays.toString(hexBuf));
	}
}
