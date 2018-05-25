package com.loam.framework.core.catalog.jaxb.interceptor;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.WebServiceTransformerException;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;

public class CustomValidatingInterceptor extends PayloadValidatingInterceptor{

	    @Override
	    protected Source getValidationRequestSource(WebServiceMessage request) {
	        return transformSourceToStreamSourceWithStringReader(request.getPayloadSource());
	    }

	    @Override
	    protected Source getValidationResponseSource(WebServiceMessage response) {
	        return transformSourceToStreamSourceWithStringReader(response.getPayloadSource());
	    }

	    protected Source transformSourceToStreamSourceWithStringReader(Source notValidatableSource) {
	        final Source source;
	        try {
	            Transformer transformer = TransformerFactory.newInstance().newTransformer();

	            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	            transformer.setOutputProperty(OutputKeys.INDENT, "no");
	            StringWriter writer = new StringWriter();
	            transformer.transform(notValidatableSource, new StreamResult(writer));

	            String transformed = writer.toString();
	            System.out.println("transformed :: " + transformed);
	            StringReader reader = new StringReader(transformed);
	            source = new StreamSource(reader);
	            source.toString();
	        } catch (TransformerException transformerException) {
	            throw new WebServiceTransformerException(
	                    "Could not convert the source to a StreamSource with a StringReader",
	                    transformerException);
	        }

	        return source;
	    }
}
