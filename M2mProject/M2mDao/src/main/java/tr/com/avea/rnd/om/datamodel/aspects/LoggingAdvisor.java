package tr.com.avea.rnd.om.datamodel.aspects;

import javassist.bytecode.SignatureAttribute.MethodSignature;

import org.springframework.core.annotation.Order;

@Aspect
@Order(value = 10)
public class LoggingAdvisor {

	private static final String ENTERING_STRING = "Entering ";
	private static final String LEAVING_STRING = "Leaving ";
	private static final String METHOD_PARAM_DELIM_START = "{";
	private static final String METHOD_PARAM_DELIM_END = "}";

	private static org.apache.log4j.Logger logger = OmLoggerFactory.getLogger(LoggingAdvisor.class);

	@Pointcut("execution(* tr.com.avea.rnd.om.datamodel..*(..))"
			+ "&& !execution(@tr.com.avea.rnd.om.common.aspect.NotAspectLogged * *.*(..))"
			+ "&& !execution(* tr.com.avea.rnd.om.datamodel.aspects..*(..))"
			+ "&& !execution(* tr.com.avea.rnd.om.datamodel.model.context..*(..))"
			+ "&& !execution(* tr.com.avea.rnd.om.datamodel.model.enums..*(..))"
			+ "&& !execution(* tr.com.avea.rnd.om.datamodel.model..set*(..))"
			+ "&& !execution(* tr.com.avea.rnd.om.datamodel.model..get*(..))"
			+ "&& !execution(* tr.com.avea.rnd.om.datamodel.model..is*(..))"
			+ "&& !execution(* tr.com.avea.rnd.om.datamodel..hashCode())"
			+ "&& !execution(* tr.com.avea.rnd.om.datamodel..equals(Object))"
			+ "&& !execution(* tr.com.avea.rnd.om.datamodel.constant..*(..))")
	public void loggingPointCut() {
	}

	@Around("loggingPointCut()")
	public Object loggingAdvice(ProceedingJoinPoint joinpoint) throws Throwable {
		Object retval;
		if (logger.isDebugEnabled()) {
			Signature sig = joinpoint.getSignature();
			Object[] paramValues = joinpoint.getArgs();
			String[] paramNames = ((CodeSignature) sig).getParameterNames();

			logMethodEntry(paramValues, paramNames, sig);
		}
		try {
			retval = joinpoint.proceed();
		}
		catch (OmBusinessException e) {
			logger.warn("Business exception received: " + e.getClass() + " Message: " + e.getMessage());
			throw (e);

		}
		catch (OmException e) {
			if (e.isStackTracePrinted() == false) {
				e.setStackTracePrinted(true);
				logger.error("OmException received: " + e.getClass() + " Message: " + e.getMessage(), e);
			}
			else {
				logger.error("OmException received: " + e.getClass() + " Message: " + e.getMessage()
						+ " - Stack trace already printed");
			}
			throw (e);

		}
		catch (Exception e) {
			logger.error("Exception received: " + e.getClass() + " Message: " + e.getMessage());
			e.printStackTrace();
			OmException omEx = new OmException(e.getMessage());
			omEx.setStackTrace(e.getStackTrace());
			omEx.setStackTracePrinted(true);
			throw (omEx);
		}
		if (logger.isDebugEnabled()) {
			logMethodExit(joinpoint, retval);
		}
		return retval;
	}

	private void logMethodEntry(Object[] methodParamVals, String[] methodParamNames, Signature sig) {
		String sessionId = InheritableThreadLocalContext.instance.get().getSessionId();
		String userName = InheritableThreadLocalContext.instance.get().getUsername();

		StringBuffer logString = new StringBuffer((sessionId + "_" + userName + " " + ENTERING_STRING));

		appendMethodName(sig, logString);
		logString.append(" with ");
		appendMethodParameterNamesAndValues(methodParamVals, methodParamNames, logString);

		logger.debug(logString.toString());
	}

	private void appendMethodName(Signature sig, StringBuffer logStr) {
		logStr.append("[" + sig.getDeclaringType().getName() + "." + sig.getName() + "]");
	}

	private void appendMethodParameterNamesAndValues(Object[] methodParamVals, String[] methodParamNames,
			StringBuffer logString) {

		logString.append(METHOD_PARAM_DELIM_START);
		for (int i = 0; i < methodParamVals.length; i++) {
			logString.append(methodParamNames[i] + " = " + paramValInString(methodParamVals[i]) + " - ");
		}
		logString.append(METHOD_PARAM_DELIM_END);

		// Replace last method param separator with log end separator
		if (methodParamVals.length > 0) {
			logString.replace(logString.indexOf(" - " + METHOD_PARAM_DELIM_END), logString.length(),
					METHOD_PARAM_DELIM_END);
		}
	}

	private void logMethodExit(ProceedingJoinPoint joinpoint, Object retval) {
		String sessionId = InheritableThreadLocalContext.instance.get().getSessionId();
		String userName = InheritableThreadLocalContext.instance.get().getUsername();
		StringBuffer logString = new StringBuffer(sessionId + "_" + userName + " " + LEAVING_STRING);

		appendMethodName(joinpoint.getSignature(), logString);
		logString.append(" with ");
		logString.append(METHOD_PARAM_DELIM_START);

		MethodSignature methodSignature = (MethodSignature) (joinpoint.getSignature());
		if (retval == null) {
			if (methodSignature.getReturnType() == void.class) {
				logString.append("void");
			}
			else {
				logString.append("null");
			}
		}
		else {
			logString.append(paramValInString(retval));
		}
		logString.append(METHOD_PARAM_DELIM_END);

		logger.debug(logString.toString());
	}

	private String paramValInString(Object parameter) {
		if (parameter == null) {
			return "NULL";
		}
		else if (parameter instanceof ShortStringDescription) {
			return ((ShortStringDescription) parameter).createShortStringDescription();
		}
		else if (parameter instanceof Long) {
			return "" + ((Long) parameter).longValue();
		}
		else if (parameter instanceof Integer) {
			return "" + ((Integer) parameter).intValue();
		}
		else if (parameter instanceof Float) {
			return "" + ((Float) parameter).floatValue();
		}
		else if (parameter instanceof StringBuffer) {
			return new String((StringBuffer) parameter);
		}
		else {
			return parameter.toString();
		}
	}
}
