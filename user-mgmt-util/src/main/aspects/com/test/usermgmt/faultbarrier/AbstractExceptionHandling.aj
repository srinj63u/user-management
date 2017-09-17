package com.test.usermgmt.faultbarrier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract aspect AbstractExceptionHandling {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractExceptionHandling.class);

	/**
     * Pointcut that picks out all exception handlers in the application,
     * regardless of the Throwable class they are catching. The pointcut exposes
     * the object that the handler resides in and the Throwable object that is
     * being caught.
     */
    protected pointcut allHandlers(Object handlerType, Throwable throwable): handler(Throwable+) && args(throwable) && this(handlerType);

    /**
     * Advice that guarantees that FaultExceptions are caught by methods within
     * classes that are designated Fault Barriers. If an undesignated method
     * inadvertently catches a CpFaultException (by catching Exception, for
     * example) the CpFaultException will be re-thrown until it reaches a handler
     * in a class designated to contain a FaultBarrier.
     */
    before(Object handler, Throwable throwable): allHandlers(handler, throwable) {
        Throwable rootCause = getRootCause(throwable);
        
            if (!(isFaultBarrier(handler))) {
                LOG.info("Not yet the Fault Barrier" + handler);
                
            } else {
                LOG.info("Reached Fault Barrier" + handler);
               
            }
        
    }
    
    
    /**
     * Gets the rootCause for a throwable that is being handled
     */
    private Throwable getRootCause(Throwable throwable) {

        Throwable t = throwable.getCause();
        if (t == null)
            return throwable;
        return getRootCause(t);
    }




	/**
     * Integrates this aspect with a particular application by allowing the
     * application to judge whether an object catching a CpFaultException is a
     * designated Fault Barrier. Subaspects implement this method to integrate
     * operation with a particular application.
     * 
     * @param exceptionHandler
     *            The object about to catch a CpFaultException.
     * @return True if the object is a designated Fault Barrier for the
     *         application, otherwise false.
     */
    public abstract boolean isFaultBarrier(Object exceptionHandler);

    
}
