package com.loam.framework.core.security.jaxb.handler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.InvalidXmlException;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.ws.support.DefaultStrategiesHelper;
import org.springframework.ws.transport.WebServiceConnection;
import org.springframework.ws.transport.WebServiceMessageReceiver;
import org.springframework.ws.transport.http.HttpServletConnection;
import org.springframework.ws.transport.http.HttpTransportConstants;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.transport.http.WebServiceMessageReceiverHandlerAdapter;
import org.springframework.ws.FaultAwareWebServiceMessage;
import org.springframework.ws.NoEndpointFoundException;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.context.DefaultMessageContext;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.springframework.ws.transport.EndpointAwareWebServiceConnection;
import org.springframework.ws.transport.FaultAwareWebServiceConnection;
import org.springframework.ws.transport.context.DefaultTransportContext;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.support.TransportUtils;

import com.loam.framework.core.security.commons.properties.CommonProperties;
import com.loam.framework.core.security.commons.utils.date.DateUtilsCommons;
import com.loam.framework.core.security.jaxb.common.SoapCommonElement;
import com.loam.framework.core.security.jaxb.exception.CodigosError;
import com.loam.framework.core.security.jaxb.ws.general.headers.EstadoRespuestaType;
import com.loam.framework.core.security.jaxb.ws.general.headers.HeaderResponseType;

public class CustomWebServiceMessageReceiverHandlerAdapter extends WebServiceMessageReceiverHandlerAdapter
implements HandlerAdapter, Ordered, InitializingBean, ApplicationContextAware {

	@Autowired
	private CommonProperties commonProperties;
	
	 private ApplicationContext context;
	    @Override
	    public void setApplicationContext(ApplicationContext applicationContext)
	            throws BeansException {
	        this.context = applicationContext;
	    }

	    @Override
	    public void afterPropertiesSet() {
	        DefaultStrategiesHelper defaultStrategiesHelper = new DefaultStrategiesHelper(MessageDispatcherServlet.class);
	        WebServiceMessageFactory factory = defaultStrategiesHelper
	                .getDefaultStrategy(WebServiceMessageFactory.class, context);
	        setMessageFactory(factory);
	    }

	    public ModelAndView handle(HttpServletRequest httpServletRequest,
	                               HttpServletResponse httpServletResponse,
	                               Object handler) throws Exception {
	        if (HttpTransportConstants.METHOD_POST.equals(httpServletRequest.getMethod())) {
	            WebServiceConnection connection = new MyWebServiceConnection(httpServletRequest, httpServletResponse);
	            try {
//	                handleConnection(connection, (WebServiceMessageReceiver) handler);
	            	customHandleConnection(connection, (WebServiceMessageReceiver) handler);
	            } catch (InvalidXmlException ex) {
	                handleInvalidXmlException(httpServletRequest, httpServletResponse, handler, ex);
	            } catch (Exception ex) {
	                handleGeneralException(httpServletRequest, httpServletResponse, handler, ex);
	            }
	        }
	        else {
	            handleNonPostMethod(httpServletRequest, httpServletResponse, handler);
	        }
	        return null;
	    }

	    protected void customHandleConnection(WebServiceConnection connection, WebServiceMessageReceiver receiver)
	            throws Exception {
	        logUri(connection);
	        TransportContext previousTransportContext = TransportContextHolder.getTransportContext();
	        TransportContextHolder.setTransportContext(new DefaultTransportContext(connection));

	        try {
	            WebServiceMessage request = connection.receive(getMessageFactory());
	            MessageContext messageContext = new DefaultMessageContext(request, getMessageFactory());
	            receiver.receive(messageContext);
	            if (messageContext.hasResponse()) {
	                WebServiceMessage response = messageContext.getResponse();
	                if (response instanceof FaultAwareWebServiceMessage &&
	                        connection instanceof FaultAwareWebServiceConnection) {
	                    FaultAwareWebServiceMessage faultResponse = (FaultAwareWebServiceMessage) response;
	                    FaultAwareWebServiceConnection faultConnection = (FaultAwareWebServiceConnection) connection;
	                    faultConnection.setFault(faultResponse.hasFault());
	                    
	                    SaajSoapMessage soapResponse = (SaajSoapMessage) messageContext.getResponse();
	                    SoapHeader soapHeader = soapResponse.getSoapHeader();
	                    Iterator<SoapHeaderElement> itr = soapHeader.examineAllHeaderElements();
	                    System.out.println("itr.hasNext() :: " + itr.hasNext());
	                    
	                   if(!itr.hasNext()){
		                    HeaderResponseType headerResponseVo = new HeaderResponseType();
		                    EstadoRespuestaType estadoRespuestaVo = new EstadoRespuestaType();
		                    estadoRespuestaVo.setIdOperacion(commonProperties.getIdEstatusValidarRequestCodigo());
		                    estadoRespuestaVo.setMetodo("overriddenHandleConnection");
		                    estadoRespuestaVo.setClase(CustomWebServiceMessageReceiverHandlerAdapter.class.getName());
		                    estadoRespuestaVo.setMensajeDetallado(commonProperties.getIdEstatusValidarRequestMsg());
		                    estadoRespuestaVo.setNivelSegRequerido(commonProperties.getIdNivelRequeridoInterceptorValidar());
		                    headerResponseVo.setEstadoRespuesta(estadoRespuestaVo);
		                    
		                    headerResponseVo.setId(String.valueOf(commonProperties.getIdEstatusValidarRequest()));
		                    headerResponseVo.setEstatus(commonProperties.getIdEstatusValidarRequestCodigo());
		                    headerResponseVo.setMensaje(commonProperties.getIdEstatusValidarRequestMsg());
		                    headerResponseVo.setFechaHora(DateUtilsCommons.toXMLGregorianCalendar(new Date()));
		                    headerResponseVo.setTokenOperacion(String.valueOf(commonProperties.getIdNivelRequeridoInterceptorValidar()));
		                    SoapCommonElement.addHeaderResponse(messageContext, headerResponseVo);
	                    }
	                }
	                connection.send(messageContext.getResponse());
	            }
	        }
	        catch (NoEndpointFoundException ex) {
	            if (connection instanceof EndpointAwareWebServiceConnection) {
	                ((EndpointAwareWebServiceConnection) connection).endpointNotFound();
	            }
	            throw ex;
	        }
	        finally {
	            TransportUtils.closeConnection(connection);
	            TransportContextHolder.setTransportContext(previousTransportContext);
	        }
	    }

	    private void logUri(WebServiceConnection connection) {
	        if (logger.isDebugEnabled()) {
	            try {
	                logger.debug("Accepting incoming [" + connection + "] at [" + connection.getUri() + "]");
	            }
	            catch (URISyntaxException e) {
	                // ignore
	            }
	        }
	    }
	    
	    private void handleGeneralException(
	            HttpServletRequest httpServletRequest,
	            HttpServletResponse response, Object handler,
	            Exception ex) throws IOException {
	    	writeErrorResponseWithMessage(httpServletRequest, response, ex, ex.getClass().getName() + ": " + ex.getMessage());
//	        writeErrorResponseWithMessage(httpServletRequest, response, ex.getClass().getName() + ": " + ex.getMessage());
	    }

	    /**
	     * By default, sets SC_BAD_REQUEST as response in Spring, so overwritten to
	     * provide HTTP_OK and reasonable SOAP fault response.
	     */
	    protected void handleInvalidXmlException(
	            HttpServletRequest httpServletRequest,
	            HttpServletResponse response, Object handler, InvalidXmlException ex)
	            throws IOException {
	        writeErrorResponseWithMessage(httpServletRequest, response, ex, ex.getClass().getName() + ": " + ex.getMessage());
//	        writeErrorResponseWithMessage(httpServletRequest, response, ex.getClass().getName() + ": " + ex.getMessage());
	    }

	    /**
	     * By default, sets SC_METHOD_NOT_ALLOWED as response in Spring, so overwritten to
	     * provide HTTP_OK and reasonable SOAP fault response.
	     */
	    protected void handleNonPostMethod(HttpServletRequest httpServletRequest,
	                                       HttpServletResponse response,
	                                       Object handler) throws Exception {
	    	writeErrorResponseWithMessage(httpServletRequest, response, new Exception(), "HTTP Method not allowed");
//	        writeErrorResponseWithMessage(httpServletRequest, response, "HTTP Method not allowed");
	    }

	    private void writeErrorResponseWithMessage(HttpServletRequest httpServletRequest, HttpServletResponse response, Exception ex, String val)
	            throws IOException {
	    	String errorXml = null;
	    	if(ex.getMessage() != null){
		      errorXml = String.format(
		                 "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">"
		                         +"          <ns2:HeaderRes fechaHora=\""+new Date()+"\" xmlns:ns2=\"http://www.allshop.com/framework/core/ws/general/Headers\">"
		                         +"          <Id>-3</Id>"
						         +"          <Estatus>PANIC</Estatus>"
						         +"          <Mensaje>Please contact technical support, this thing may explode.</Mensaje>"
						         +"          <EstadoRespuesta>"
						         +"             <IdOperacion>PANIC</IdOperacion>"
						         +"             <Metodo>writeErrorResponseWithMessage</Metodo>"
						         +"             <Clase>CustomWebServiceMessageReceiverHandlerAdapter</Clase>"
						         +"             <MensajeDetallado>Please contact technical support, this thing may explode.</MensajeDetallado>"
						         +"             <NivelSegRequerido>-3</NivelSegRequerido>"
						         +"          </EstadoRespuesta>"
						         +"          <TokenOperacion>-3</TokenOperacion>"
						         +"          </ns2:HeaderRes>"
						         +"          </SOAP-ENV:Header>"
		                         +"    <SOAP-ENV:Body>"
		                         +"        <SOAP-ENV:Fault>"
		                         +"            <faultcode>SOAP-ENV:Server</faultcode>"
		                         +"            <faultstring xml:lang=\"en\">FaultMsg</faultstring>"
		                         +"			   <detail>"
		                         +"            		<ns3:Excepcion xmlns:ns3=\"http://www.allshop.com/framework/core/ws/common/ExcepcionGeneral\">"
		                         +"            			<Codigo>"+CodigosError.PANIC.toString()+"</Codigo>"
		                         +"            			<Componente>"+ex.getCause().toString().replaceAll("\"", "").replaceAll("<", "").replaceAll(">", "")+"</Componente>"
		                         +"            			<Metodo>"+ex.getStackTrace()[0].getClassName().replaceAll("\"", "").replaceAll("<", "").replaceAll(">", "")+"</Metodo>"
		                         +"            			<Descripcion>"+ex.getStackTrace()[0].getMethodName().replaceAll("\"", "").replaceAll("<", "").replaceAll(">", "")+"</Descripcion>"
		                         +"            		</ns3:Excepcion>"
		                         +"            </detail>"
		                         +"        </SOAP-ENV:Fault>"
		                         +"    </SOAP-ENV:Body>"
		                         +"</SOAP-ENV:Envelope>"
		                );
	    	}else{
		        errorXml = String.format(
		                 "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">"
		                         +"          <ns2:HeaderRes fechaHora=\""+new Date()+"\" xmlns:ns2=\"http://www.allshop.com/framework/core/ws/general/Headers\">"
		                         +"          <Id>-3</Id>"
						         +"          <Estatus>PANIC</Estatus>"
						         +"          <Mensaje>Please contact technical support, this thing may explode.</Mensaje>"
						         +"          <EstadoRespuesta>"
						         +"             <IdOperacion>PANIC</IdOperacion>"
						         +"             <Metodo>writeErrorResponseWithMessage</Metodo>"
						         +"             <Clase>CustomWebServiceMessageReceiverHandlerAdapter</Clase>"
						         +"             <MensajeDetallado>Please contact technical support, this thing may explode.</MensajeDetallado>"
						         +"             <NivelSegRequerido>-3</NivelSegRequerido>"
						         +"          </EstadoRespuesta>"
						         +"          <TokenOperacion>-3</TokenOperacion>"
						         +"          </ns2:HeaderRes>"
						         +"          </SOAP-ENV:Header>"
		                         +"    <SOAP-ENV:Body>"
		                         +"        <SOAP-ENV:Fault>"
		                         +"            <faultcode>SOAP-ENV:Server</faultcode>"
		                         +"            <faultstring xml:lang=\"en\">%s</faultstring>"
		                         +"        </SOAP-ENV:Fault>"
		                         +"    </SOAP-ENV:Body>"
		                         +"</SOAP-ENV:Envelope>",
		                StringEscapeUtils.escapeXml("PANIC ERROR : HTTP Method not allowed - Spin in circles")
		                );
	    	}
	        
	        response.setStatus(HttpServletResponse.SC_OK);
	        response.setContentType("text/xml");
	        response.getWriter().write(errorXml);
	        response.getWriter().flush();
	    }
	    @Override
	    public int getOrder() {
	        return 1;
	    }

	    /**
	     * This class is needed as org.springframework.ws.transport.http.HttpServletConnection will throw an
	     * exception if it is used outside Spring framework files. However, extending it and using the same
	     * implementation seems to be fine.
	     *
	     */
	    static class MyWebServiceConnection extends HttpServletConnection {
	        protected MyWebServiceConnection(HttpServletRequest httpServletRequest,
	                HttpServletResponse httpServletResponse) {
	            super(httpServletRequest, httpServletResponse);
	        }
	    }    

	    
}
