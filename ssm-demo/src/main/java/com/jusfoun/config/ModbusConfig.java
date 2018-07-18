package com.jusfoun.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 描述 : Modbus地址配置. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年10月19日 下午5:35:42
 */
@Configuration
@ConfigurationProperties(prefix = ModbusConfig.MODBUS_PREFIX)
public class ModbusConfig {
	public static final String MODBUS_PREFIX = "system.modbus";

	private String ip;// IP地址
	private int port;// 端口
	private int slaveId;// 模块地址，出厂默认1
	private int start;// 功能码，读取测量值
	private int readLenth;// 数据长度

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getSlaveId() {
		return slaveId;
	}

	public void setSlaveId(int slaveId) {
		this.slaveId = slaveId;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getReadLenth() {
		return readLenth;
	}

	public void setReadLenth(int readLenth) {
		this.readLenth = readLenth;
	}
}
