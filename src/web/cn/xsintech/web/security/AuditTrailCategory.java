package cn.xsintech.web.security;

public enum AuditTrailCategory {

	AUTHENTICATION(1, "AUTHENTICATION"), DATA_DOWNLOAD(2, "DATA_DOWNLOAD");

	private int order;

	private String name;

	AuditTrailCategory(int order, String name) {
		this.order = order;
		this.name = name;
	}

	public int getOrder() {
		return this.order;
	}

	public String toString() {
		return this.name;
	}

}
