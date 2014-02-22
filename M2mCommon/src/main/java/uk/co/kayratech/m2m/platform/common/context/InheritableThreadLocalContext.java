package uk.co.kayratech.m2m.platform.common.context;


public class InheritableThreadLocalContext extends InheritableThreadLocal<BaseContext> {
	
	public static InheritableThreadLocalContext instance = new InheritableThreadLocalContext();
	
	protected synchronized BaseContext initialValue() {
	      return new BaseContextImpl();
	}

}
