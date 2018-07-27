package com.jusfoun.common.utils.modbus;

import java.math.BigInteger;

import com.jusfoun.config.ModbusConfig;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.msg.ModbusRequest;
import com.serotonin.modbus4j.msg.ModbusResponse;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersRequest;
import com.serotonin.modbus4j.msg.WriteRegistersRequest;
import com.serotonin.modbus4j.msg.WriteRegistersResponse;
import com.serotonin.modbus4j.sero.util.queue.ByteQueue;

/**
 * 描述 : Modbus通信工具类. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年10月19日 下午3:31:50
 */
public class ModbusUtils {
	static ModbusFactory modbusFactory;
	static {
		if (modbusFactory == null) {
			modbusFactory = new ModbusFactory();
		}
	}

	/**
	 * 获取tcpMaster
	 *
	 * @return
	 * @throws ModbusInitException
	 */
	public static ModbusMaster getMaster(String ip, int port) throws ModbusInitException {
		IpParameters params = new IpParameters();
		params.setHost(ip);
		params.setPort(port);
		ModbusMaster tcpMaster = modbusFactory.createTcpMaster(params, false);
		tcpMaster.init();
		return tcpMaster;
	}

	public static void modbusWTCP(String ip, int port, int slaveId, int start, short[] values) {
		ModbusMaster tcpMaster = null;
		try {
			tcpMaster = getMaster(ip, port);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			WriteRegistersRequest request = new WriteRegistersRequest(slaveId, start, values);
			WriteRegistersResponse response = null;
			if (tcpMaster != null) {
				response = (WriteRegistersResponse) tcpMaster.send(request);
				if (response != null && response.isException()) {
					System.out.println("Exception response: message=" + response.getExceptionMessage());
				} else {
					System.out.println("Success");
				}
			}
		} catch (ModbusTransportException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 描述 : 返回数据队列 . <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月20日 上午10:38:36
	 * @param ip
	 *            IP地址
	 * @param port
	 *            端口
	 * @param slaveId
	 *            地址
	 * @param start
	 *            功能码
	 * @param readLenth
	 *            数据长度
	 * @return 数据队列
	 * @throws ModbusInitException
	 * @throws ModbusTransportException
	 */
	public static ByteQueue modbusTCP(String ip, int port, int slaveId, int start, int readLenth) throws ModbusInitException, ModbusTransportException {
		ModbusMaster tcpMaster = null;
		ModbusRequest modbusRequest = null;
		ModbusResponse modbusResponse = null;
		ByteQueue byteQueue = null;

		tcpMaster = getMaster(ip, port);
		modbusRequest = new ReadHoldingRegistersRequest(slaveId, start, readLenth);// 功能码03
		if (tcpMaster != null) {
			modbusResponse = tcpMaster.send(modbusRequest);
			if (modbusResponse != null) {
				byteQueue = new ByteQueue(12);
				modbusResponse.write(byteQueue);
			}
		}
		// System.out.println("功能码:" + modbusRequest.getFunctionCode());
		// System.out.println("从站地址:" + modbusRequest.getSlaveId());
		// System.out.println("收到的响应信息大小:" + byteQueue.size());
		// System.out.println("收到的响应信息值:" + byteQueue);
		return byteQueue;
	}

	/**
	 * 描述 :直接返回十六进制类型数据数组 . <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月20日 上午10:37:46
	 * @param ip
	 *            IP地址
	 * @param port
	 *            端口
	 * @param slaveId
	 *            地址
	 * @param start
	 *            功能码
	 * @param readLenth
	 *            数据长度
	 * @return 十六进制类型数据数组
	 * @throws ModbusTransportException
	 * @throws ModbusInitException
	 */
	public static String[] hexTCP(String ip, int port, int slaveId, int start, int readLenth) throws ModbusInitException, ModbusTransportException {
		ByteQueue byteQueue = ModbusUtils.modbusTCP(ip, port, slaveId, start, readLenth);
		byte[] buf = byteQueue.peekAll();
		if (buf == null || buf.length == 0) {
			return null;
		}
		String[] hexBuf = new String[buf.length];
		for (int i = 0; i < buf.length; i++) {
			hexBuf[i] = String.format("%02X", new Object[]{Byte.valueOf(buf[i])});
		}
		return hexBuf;
	}

	/**
	 * 描述 : 直接返回float类型数据数组. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月20日 上午10:34:49
	 * @param ip
	 *            IP地址
	 * @param port
	 *            端口
	 * @param slaveId
	 *            地址
	 * @param start
	 *            功能码
	 * @param readLenth
	 *            数据长度
	 * @return float类型数据数组
	 * @throws ModbusTransportException
	 * @throws ModbusInitException
	 */
	public static float[] floatTCP(String ip, int port, int slaveId, int start, int readLenth) throws ModbusInitException, ModbusTransportException {
		String[] hexBuf = hexTCP(ip, port, slaveId, start, readLenth);
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
			return fbuf;
		}
		return null;
	}

	/**
	 * 描述 :直接返回float类型数据数组 . <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月20日 上午10:33:42
	 * @param modbusConfig
	 *            配置属性
	 * @return float类型数据数组
	 * @throws ModbusTransportException
	 * @throws ModbusInitException
	 */
	public static float[] floatTCP(ModbusConfig modbusConfig) throws ModbusInitException, ModbusTransportException {
		return ModbusUtils.floatTCP(modbusConfig.getIp(), modbusConfig.getPort(), modbusConfig.getSlaveId(), modbusConfig.getStart(), modbusConfig.getReadLenth());
	}
}
