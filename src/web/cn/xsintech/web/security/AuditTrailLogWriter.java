package cn.xsintech.web.security;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.Principal;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import cn.xsintech.security.SecurityContext;
import cn.xsintech.security.SecurityContextHolder;
import cn.xsintech.utils.SimpleDateFormatUtil;

@Component
@Aspect
public class AuditTrailLogWriter {

	private static final Log log = LogFactory.getLog(AuditTrailLogWriter.class);

	@Pointcut(value = "execution(public * *(..))")
	public void anyPublicMethod() {
	}

	@Around(value = "anyPublicMethod() && @annotation(auditTrail)")
	public Object invokeMethod(final ProceedingJoinPoint joinPoint, final AuditTrail auditTrail) throws Throwable {
		final SecurityContext context = SecurityContextHolder.getContext();
		final Principal principal = context.getPrincipal();
		final String userid = context != null ? principal.getName() : "anonymous";

		Object returnValue = null;
		Throwable t = null;
		try {
			returnValue = joinPoint.proceed(joinPoint.getArgs());
			return returnValue;
		} catch (Throwable e) {
			t = e;
			throw e;
		} finally {
			StringWriter writer = new StringWriter();
			final PrintWriter out = new PrintWriter(writer);
			out.print("#AUDIT-TRAIL {");
			out.print(" WHEN: " + SimpleDateFormatUtil.format(new Date(), "yyyyMMddHHmmss"));
			out.print(" WHO: " + userid);
			out.print(" WHAT: params=(" + ObjectUtils.nullSafeToString(joinPoint.getArgs()) + ") returnValue=(" + ObjectUtils.nullSafeToString(returnValue)
					+ ")");
			if (t != null) {
				out.print(" exception=(" + t.getLocalizedMessage() + ")");
			}
			out.print(" APPLICATION: PartnersCompass");
			out.print(" ACTION: " + auditTrail.category() + ":" + auditTrail.description());
			out.print(" USER-AGENT: " + context.getUserAgent());
			out.println("}");

			log.info(writer.toString());
		}
	}

}
