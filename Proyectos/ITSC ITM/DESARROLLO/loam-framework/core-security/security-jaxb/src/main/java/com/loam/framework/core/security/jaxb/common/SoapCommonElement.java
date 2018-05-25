package com.loam.framework.core.security.jaxb.common;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;

import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapEnvelope;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.w3c.dom.Document;

import com.loam.framework.core.security.jaxb.ws.general.headers.EstadoRespuestaType;
import com.loam.framework.core.security.jaxb.ws.general.headers.HeaderResponseType;
import com.loam.framework.core.security.jaxb.ws.general.headers.ObjectFactory;

public class SoapCommonElement {
	
	private static ObjectFactory factory = new ObjectFactory();
	
   private static void addNamespaceDeclaration(SoapEnvelope soapEnvelope)
            throws SOAPException {
    	soapEnvelope.addNamespaceDeclaration("head","http://www.loam.com/framework/core/security/jaxb/ws/general/Headers");
    	soapEnvelope.addNamespaceDeclaration("cat", "http://www.loam.com/framework/core/security/jaxb/ws/CorporateSecurity");
    	soapEnvelope.addNamespaceDeclaration("elem", "http://www.allshop.com/framework/core/ws/common/ElementosComunes");
    } 
    
   private static void addHeaderResponseDeclaration(SoapHeader soapHeader, HeaderResponseType headerResponseType)
            throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
    	
    	JAXBContext context = JAXBContext.newInstance(HeaderResponseType.class);
    	
        DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory1.newDocumentBuilder();

        Document headerDocument = builder.newDocument();

        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal( factory.createHeaderRes(headerResponseType), headerDocument );
        
        Transformer t = TransformerFactory.newInstance().newTransformer();

        DOMSource headerSource = new DOMSource( headerDocument );

        t.transform( headerSource, soapHeader.getResult() );
    } 
   
   public static void addHeaderResponse(MessageContext messageContext, HeaderResponseType headerResponseVo)
           throws SOAPException, JAXBException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		SoapMessage soapRequest = (SoapMessage) messageContext.getResponse();
        SoapEnvelope envelope = (SoapEnvelope) soapRequest.getEnvelope();
        
        final HeaderResponseType headerResponseType= factory.createHeaderResponseType();
        
        EstadoRespuestaType estadoRespuestaType = new EstadoRespuestaType();
        estadoRespuestaType.setIdOperacion(headerResponseVo.getEstadoRespuesta().getIdOperacion());
        estadoRespuestaType.setMetodo(headerResponseVo.getEstadoRespuesta().getMetodo());
        estadoRespuestaType.setClase(headerResponseVo.getEstadoRespuesta().getClase());
        estadoRespuestaType.setMensajeDetallado(headerResponseVo.getEstadoRespuesta().getMensajeDetallado());
        estadoRespuestaType.setNivelSegRequerido(headerResponseVo.getEstadoRespuesta().getNivelSegRequerido());
        
        headerResponseType.setEstadoRespuesta(estadoRespuestaType);
        
        headerResponseType.setId(headerResponseVo.getId());
        headerResponseType.setEstatus(headerResponseVo.getEstatus());
        headerResponseType.setMensaje(headerResponseVo.getMensaje());
        headerResponseType.setFechaHora(headerResponseVo.getFechaHora());
        headerResponseType.setTokenOperacion(headerResponseVo.getTokenOperacion());
        
        SoapCommonElement.addNamespaceDeclaration(envelope);
        SoapCommonElement.addHeaderResponseDeclaration(soapRequest.getSoapHeader(), headerResponseType);
   } 
}
