package com.kryvokin.onlineshop.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoggingFilter extends CommonsRequestLoggingFilter {

    @Value("${isRequestLoggingEnabled}")
    private boolean isRequestLoggingEnabled;
    private Logger logger = LogManager.getRootLogger();
    private static final Marker REQUEST_MARKER = MarkerManager.getMarker("REQUEST");

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        logger.info(REQUEST_MARKER,message);
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return isRequestLoggingEnabled;
    }

}
