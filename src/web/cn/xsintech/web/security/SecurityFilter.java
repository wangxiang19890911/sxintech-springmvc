package cn.xsintech.web.security;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ContextLoader;

import cn.xsintech.security.SecurityContext;
import cn.xsintech.security.SecurityContextHolder;
import cn.xsintech.service.AuthenticationService;
import cn.xsintech.utils.Dates;

public class SecurityFilter implements Filter {

	private FilterConfig config;

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;

		final ApplicationContext appContext = ContextLoader.getCurrentWebApplicationContext();
		final AuthenticationService service = (AuthenticationService) appContext.getBean("userService");
		final SecurityContext context = new HttpServletSecurityContext(request, response, service);
		context.addInterceptUrl("/components", false);
		context.addInterceptUrl("/image", false);
		context.addInterceptUrl("/css", false);
		context.addInterceptUrl("/apps", false);
		context.addInterceptUrl("/html", false);
		context.addInterceptUrl("/pdf", false);
		context.addInterceptUrl("/js", false);
		context.addInterceptUrl("/secure", true);
		context.addInterceptUrl("/", false);

		// 【ログイン制御】 ここから
		if (context.intercept()) {
			final String username = request.getParameter("username");
			final String password = request.getParameter("password");
			final String token = request.getParameter("appToken");
			if (!StringUtils.isEmpty(username) || !StringUtils.isEmpty(password)) {
				if (context.login()) {
					// login 処理で session-id が変更されているので再取得
					HttpSession session = request.getSession(false);
					session.setAttribute(BindingKeys.AUTHENTICATED_PRINCIPAL, context.getPrincipal());
					response.setHeader("app-login-result", "success");
					if (context.isRequiredPasswordChanges() && StringUtils.isEmpty(token)) {
						// TODO app側から初回登録する時、パスワードリセットが必要？
						final String requiredPasswordChanges = config.getInitParameter("password-change-required");
						RequestDispatcher dispatcher = request.getRequestDispatcher(requiredPasswordChanges);
						dispatcher.forward(request, response);
						return;
					}
				} else {
					if (StringUtils.isEmpty(token)) {
						final String loginPage = config.getInitParameter("login-page");
						RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
						dispatcher.forward(request, response);
						return;
					} else {
						response.setHeader("app-login-result", "password-error");
					}
				}
			} else {
				HttpSession session = request.getSession(false);
				final String oneTimeToken = request.getParameter("_");
				if (!StringUtils.isEmpty(oneTimeToken)) {
					if (context.login()) {
						session = request.getSession(false);
						session.setAttribute(BindingKeys.AUTHENTICATED_PRINCIPAL, context.getPrincipal());
						if (context.isRequiredPasswordChanges()) {
							final String requiredPasswordChanges = config.getInitParameter("password-change-required");
							RequestDispatcher dispatcher = request.getRequestDispatcher(requiredPasswordChanges);
							dispatcher.forward(request, response);
							return;
						}
					}
				} else if (session == null) {
					if (context.login()) {
						// login 処理で session-id が変更されているので再取得
						session = request.getSession(false);
						session.setAttribute(BindingKeys.AUTHENTICATED_PRINCIPAL, context.getPrincipal());
						if (context.isRequiredPasswordChanges()) {
							final String requiredPasswordChanges = config.getInitParameter("password-change-required");
							RequestDispatcher dispatcher = request.getRequestDispatcher(requiredPasswordChanges);
							dispatcher.forward(request, response);
							return;
						}
					} else {
						final String loginPage = config.getInitParameter("login-page");
						RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
						dispatcher.forward(request, response);
						return;
					}
				} else {
					Principal principal = (Principal) session.getAttribute(BindingKeys.AUTHENTICATED_PRINCIPAL);
					if (principal == null) {
						if (context.login()) {
							// login 処理で session-id が変更されているので再取得
							session = request.getSession(false);
							session.setAttribute(BindingKeys.AUTHENTICATED_PRINCIPAL, context.getPrincipal());
							if (context.isRequiredPasswordChanges()) {
								final String requiredPasswordChanges = config.getInitParameter("password-change-required");
								RequestDispatcher dispatcher = request.getRequestDispatcher(requiredPasswordChanges);
								dispatcher.forward(request, response);
								return;
							}
						} else {
							final String loginPage = config.getInitParameter("login-page");
							RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
							dispatcher.forward(request, response);
							return;
						}
					} else {
						((HttpServletSecurityContext) context).setPrincipal(principal);
					}
				}
			}
		}
		// 【ログイン制御】 ここまで

		try {
			SecurityContextHolder.setContext(context);
			chain.doFilter(request, response);
		} finally {
			SecurityContextHolder.setContext(null);
			Dates.resetThreadDate();
		}
	}

	@Override
	public void destroy() {
	}

}
