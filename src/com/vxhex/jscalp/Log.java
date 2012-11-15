/**
 * Storage object for a log entry.
 */

package com.vxhex.jscalp;

public class Log {
	private String ip;
	private String date;
	private String time;
	private String method;
	private String url;
	private String agent;
	private String raw;
	
	public String toString() {
		return "IP: " + ip
			+ "\nDate: " + date
			+ "\nTime: " + time
			+ "\nMethod: " + method
			+ "\nURL: " + url
			+ "\nAgent: " + agent
			+ "\nRaw:\n" + raw;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getRaw() {
		return raw;
	}
	public void setRaw(String raw) {
		this.raw = raw;
	}
}
