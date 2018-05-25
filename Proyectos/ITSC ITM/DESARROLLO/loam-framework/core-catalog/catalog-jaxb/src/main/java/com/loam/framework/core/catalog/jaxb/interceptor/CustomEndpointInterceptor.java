package com.loam.framework.core.catalog.jaxb.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.WebServiceIOException;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;

import com.loam.framework.core.logs.persistence.add.LoggingAddDao;
import com.loam.framework.core.logs.persistence.add.LoggingAddVo;

@Component
public class CustomEndpointInterceptor implements EndpointInterceptor {

    private static final Log LOG = LogFactory.getLog(CustomEndpointInterceptor.class);

	@Autowired
	protected LoggingAddDao loggingAddDao;
	private LoggingAddVo loggingAddVo = null;
	
    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
        LOG.info("Endpoint Request Handling");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            messageContext.getRequest().writeTo(os);
        } catch (IOException e) {
            throw new WebServiceIOException(e.getMessage(), e);
        }

        String request = new String(os.toByteArray());
        LOG.trace("Request Envelope: " + request);
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
        LOG.info("Endpoint Response Handling");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            messageContext.getResponse().writeTo(os);
        } catch (IOException e) {
            throw new WebServiceIOException(e.getMessage(), e);
        }

        String request = new String(os.toByteArray());
        LOG.trace("Response Envelope: " + request);
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
        LOG.info("Endpoint Exception Handling");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            messageContext.getResponse().writeTo(os);
        } catch (IOException e) {
            throw new WebServiceIOException(e.getMessage(), e);
        }

        String request = new String(os.toByteArray());
        LOG.trace("Exception Envelope: " + request);
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) throws Exception {
        LOG.info("Execute code after completion");
    }

}
