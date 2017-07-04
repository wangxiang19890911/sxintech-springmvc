package cn.xsintech.web.security;

import javax.servlet.http.HttpServletRequest;

public class UserAgent {

	private String os;

	private String browser;

	private String ip;

	private UserAgent() {
	}

	public static UserAgent parse(HttpServletRequest request) {
		UserAgent ua = new UserAgent();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}

		ua.ip = ipAddress;

		final String userAgent = request.getHeader("User-Agent");
		final String user = userAgent.toLowerCase();

		if (userAgent.toLowerCase().indexOf("windows") >= 0) {
			ua.os = "Windows";
		} else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
			ua.os = "Mac";
		} else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
			ua.os = "Unix";
		} else if (userAgent.toLowerCase().indexOf("android") >= 0) {
			ua.os = "Android";
		} else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
			ua.os = "iPhone";
		} else {
			ua.os = "UnKnown, More-Info: " + userAgent;
		}

		if (user.contains("msie")) {
			String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
			ua.browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
		} else if (user.contains("safari") && user.contains("version")) {
			ua.browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-"
					+ (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if (user.contains("opr") || user.contains("opera")) {
			if (user.contains("opera")) {
				ua.browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-"
						+ (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
			} else if (user.contains("opr")) {
				ua.browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
			}
		} else if (user.contains("chrome")) {
			ua.browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
		} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf("mozilla/4.7") != -1)
				|| (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
			ua.browser = "Netscape-?";
		} else if (user.contains("firefox")) {
			ua.browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
		} else {
			ua.browser = "UnKnown, More-Info: " + userAgent;
		}

		return ua;
	}

	public String getOS() {
		return this.os;
	}

	public String getBrowser() {
		return this.browser;
	}

	public String getIP() {
		return this.ip;
	}

	public String toString() {
		return (this.os + " - " + this.browser + " from " + this.ip);
	}

}
