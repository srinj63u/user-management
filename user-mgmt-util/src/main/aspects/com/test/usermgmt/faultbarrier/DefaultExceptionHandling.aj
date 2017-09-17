package com.test.usermgmt.faultbarrier;

public aspect DefaultExceptionHandling extends AbstractExceptionHandling {
	
    public boolean isFaultBarrier(Object exceptionHandler) {
		
    	return true;
    }

   
}
