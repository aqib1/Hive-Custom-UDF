package com.custom.udf.domain;

public class UDFOutput {

	private String device;
	private String osName;
	private String browser;
	private String UA;

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getUA() {
		return UA;
	}

	public void setUA(String uA) {
		UA = uA;
	}

	@Override
	public String toString() {
		return "UDFOutput [device=" + device + ", osName=" + osName + ", browser=" + browser + ", UA=" + UA + "]";
	}
}
