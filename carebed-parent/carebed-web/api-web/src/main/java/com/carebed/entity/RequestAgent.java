package com.carebed.entity;

public class RequestAgent implements java.io.Serializable {

	private static final long serialVersionUID = -8160823805450039949L;
	
	/**
	 * 终端类型
	 */
	private String terminal;

	/**
	 * 终端版本
	 */
	private String version;
	
	/**
	 * 设备ID
	 */
	private String device;

	public RequestAgent(String terminal, String version,String ip,String device) {
		setTerminal(terminal);
		setVersion(version);
		setDevice(device);
	}

	public RequestAgent() {
		//
	}

	public RequestAgent(String terminal) {
		this.terminal = terminal;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}
}
