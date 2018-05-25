package com.loam.framework.core.security.jaxb.exception;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;

import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;

import com.loam.framework.core.security.jaxb.ws.common.excepciongeneral.ExcepcionGenericaType;
import com.loam.framework.core.security.jaxb.ws.common.excepciongeneral.ObjectFactory;

public class CustomSoapFaultMappingExceptionResolver extends SoapFaultMappingExceptionResolver {

	private ObjectFactory factory = new ObjectFactory();
	
	private JAXBContext context;
		
		public CustomSoapFaultMappingExceptionResolver() throws JAXBException {
			super();
			context = JAXBContext.newInstance("com.loam.framework.core.security.jaxb.ws.common.excepciongeneral");
		}
		
		@Override
		protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
			if(ex instanceof ServiceFaultException) {
				try {
					addFaultDetail(endpoint, (ServiceFaultException)ex, fault);
				} catch (JAXBException e) {
					e.printStackTrace();
				}
			}
		}
		
		private void addFaultDetail(Object endpoint, ServiceFaultException exception, SoapFault fault) throws JAXBException { 
			
			final SoapFaultDetail detail = fault.addFaultDetail();
			final Marshaller marshaller = context.createMarshaller();
			final Result result = detail.getResult();
			 
			final ExcepcionGenericaType type = factory.createExcepcionGenericaType();
			
			type.setCodigo(exception.getMessage());
			type.setDescripcion(exception.getCause().toString());
			type.setComponente(exception.getStackTrace()[0].getClassName());
			type.setMetodo(exception.getStackTrace()[0].getMethodName());
			
			marshaller.marshal(factory.createExcepcion(type), result);
		} 

}
