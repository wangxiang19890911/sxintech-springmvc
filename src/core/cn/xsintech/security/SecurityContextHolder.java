package cn.xsintech.security;

public class SecurityContextHolder {

	private static final ThreadLocal<SecurityContext> instance = new ThreadLocal<SecurityContext>() {

		@Override
		protected SecurityContext initialValue() {
			return null;
		}

	};

	public static void setContext(SecurityContext context) {
		instance.set(context);
	}

	public static SecurityContext getContext() {
		return instance.get();
	}

}
