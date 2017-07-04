package cn.xsintech.web.security;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import cn.xsintech.exception.InternalServerException;
import cn.xsintech.exception.LockedUserException;
import cn.xsintech.model.User;
import cn.xsintech.security.SecurityContext;
import cn.xsintech.service.AuthenticationService;

public class HttpServletSecurityContext implements SecurityContext {

	private static final Log log = LogFactory.getLog(HttpServletSecurityContext.class);

	private Principal principal;

	private HttpServletRequest request;

	// private HttpServletResponse response;

	private UserAgent ua;

	private AuthenticationService service;

	private Map<String, Boolean> interceptUrls;

	public HttpServletSecurityContext(HttpServletRequest request, HttpServletResponse response, AuthenticationService service) {
		this.request = request;
		// this.response = response;
		this.service = service;
		this.ua = UserAgent.parse(request);

		this.interceptUrls = new LinkedHashMap<String, Boolean>();
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	@Override
	public Principal getPrincipal() {
		return this.principal;
	}

	@Override
	public UserAgent getUserAgent() {
		return this.ua;
	}

	@Override
	public String getContextPath() {
		return this.request.getContextPath();
	}

	@Override
	public void addInterceptUrl(String pattern, boolean interceptable) {
		this.interceptUrls.put(pattern, interceptable);
	}

	@Override
	public boolean intercept() {
		String contextPath = request.getContextPath();
		if ("/".equals(contextPath)) {
			contextPath = "";
		}

		String path = this.request.getRequestURI();
		path = path.substring(contextPath.length());
		for (Map.Entry<String, Boolean> entry : this.interceptUrls.entrySet()) {
			if (path.startsWith(entry.getKey())) {
				if (log.isDebugEnabled()) {
					log.debug("[SECURITY INTERCEPTOR] Unmatch interceptors. path=[" + path + "]");
				}
				return entry.getValue();
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("[SECURITY INTERCEPTOR] Match an interceptor. path=[" + path + "]");
		}
		return true;
	}

	@Override
	public boolean login() {
		final String username = request.getParameter("username");
		final String password = request.getParameter("password");

		final boolean authWithUsernnameAndPassword = !StringUtils.isEmpty(username) && !StringUtils.isEmpty(password);
		if (!authWithUsernnameAndPassword) {
			return false;
		}
		
		// セッションをクリア
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			session = null;
		}

		session = request.getSession(true);

		User user = null;
		try {
			if (authWithUsernnameAndPassword) {
				user = (User) service.login(username, password);
			}
		} catch (LockedUserException e) {
			session.setAttribute("errors", Arrays.asList(e.getMessage()));

			this.principal = null;
			return false;
		} catch (InternalServerException e) {
			log.error("[INTERNAL-SERVER-EXCEPTION]", e);
			session.setAttribute("errors", Arrays.asList("システム障害が発生しました。管理者へ連絡してください。"));

			this.principal = null;
			return false;
		}

		if (user == null) {
			session.setAttribute("errors", Arrays.asList("ユーザーIDもしくはパスワードが違うか、アカウントの有効期限が切れています。"));

			this.principal = null;
			return false;
		}

		session.setAttribute("errors", Collections.emptyList());

		this.principal = user;
		return true;
	}

	@Override
	public boolean isRequiredPasswordChanges() {
		return false;
	}

	@Override
	public void logout() {

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}

}
