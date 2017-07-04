package cn.xsintech.security;

import java.security.Principal;

import cn.xsintech.web.security.UserAgent;

public interface SecurityContext {

	Principal getPrincipal();

	UserAgent getUserAgent();

	String getContextPath();

	boolean login();

	void logout();

	boolean isRequiredPasswordChanges();
	
	void addInterceptUrl(String pattern, boolean interceptable);

	boolean intercept();

}
