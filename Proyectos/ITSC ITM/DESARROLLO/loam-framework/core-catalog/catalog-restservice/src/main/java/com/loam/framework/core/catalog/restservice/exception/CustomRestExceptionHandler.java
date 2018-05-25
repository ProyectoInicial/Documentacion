package com.loam.framework.core.catalog.restservice.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.loam.framework.core.catalog.persistence.common.EstadoRespuestaVo;
import com.loam.framework.core.catalog.persistence.common.HeaderResponseVo;
import com.loam.framework.core.catalog.restservice.common.ErroresComunes;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

		final List<String> errors = new ArrayList<String>();

		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + " : " + error.getDefaultMessage());
		}

		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + " : " + error.getDefaultMessage());
		}

		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);

		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final List<String> errors = new ArrayList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type "
				+ ex.getRequiredType();

		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final String error = ex.getRequestPartName() + " part is missing";
		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final String error = ex.getParameterName() + " parameter is missing";
		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/*
	 * //
	 * 
	 * @ExceptionHandler({ MethodArgumentTypeMismatchException.class }) public
	 * ResponseEntity<Object> handleMethodArgumentTypeMismatch(final
	 * MethodArgumentTypeMismatchException ex, final WebRequest request) {
	 * logger.info(ex.getClass().getName()); // final String error = ex.getName() +
	 * " should be of type " + ex.getRequiredType().getName();
	 * 
	 * final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
	 * ex.getLocalizedMessage(), error); return new ResponseEntity<Object>(apiError,
	 * new HttpHeaders(), apiError.getStatus()); }
	 */
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex,
			final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final List<String> errors = new ArrayList<String>();
		for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
		}

		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/*
	 * // 404
	 * 
	 * @Override protected ResponseEntity<Object>
	 * handleNoHandlerFoundException(final NoHandlerFoundException ex, final
	 * HttpHeaders headers, final HttpStatus status, final WebRequest request) {
	 * logger.info(ex.getClass().getName()); // final String error =
	 * "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
	 * 
	 * final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,
	 * ex.getLocalizedMessage(), error); return new ResponseEntity<Object>(apiError,
	 * new HttpHeaders(), apiError.getStatus()); }
	 */
	// 405

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(" method is not supported for this request. Supported methods are ");
		// ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
		for (HttpMethod obj : ex.getSupportedHttpMethods()) {
			builder.append(obj + " ");
		}

		final ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(),
				builder.toString());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// 415

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		// ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));
		for (MediaType obj : ex.getSupportedMediaTypes()) {
			builder.append(obj + " ");
		}

		final ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getLocalizedMessage(),
				builder.substring(0, builder.length() - 2));
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// 500

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(final CustomHandlerException ex, final WebRequest request) {

		logger.info(ex.getClass().getName());
		logger.error("error", ex);
		Map<String, Object> json1 = new HashMap<String, Object>();
		try {
//			System.out.println(" :: " + ex.getEstatusMensage());
//			System.out.println(" :: " + ex.getIdMensage());
//			System.out.println(" :: " + ex.getLocalizedMessage());
//			System.out.println(" :: " + ex.getMessage());
//			System.out.println(" :: " + ex.getCause());
//			System.out.println(" :: " + ex.getExcepcionGenerica().getCodigo());
//			System.out.println(" :: " + ex.getExcepcionGenerica().getComponente());
//			System.out.println(" :: " + ex.getExcepcionGenerica().getDescripcion());
//			System.out.println(" :: " + ex.getExcepcionGenerica().getId());
//			System.out.println(" :: " + ex.getExcepcionGenerica().getMensaje());
//			System.out.println(" :: " + ex.getExcepcionGenerica().getMetodo());
//			System.out.println(" :: " + ExceptionUtils.getStackTrace(ex));

			HeaderResponseVo headerResponse = new HeaderResponseVo();
			EstadoRespuestaVo estadoRespuesta = new EstadoRespuestaVo();
			headerResponse.setId(String.valueOf(ex.getExcepcionGenerica().getId()));
			headerResponse.setEstatus(ex.getExcepcionGenerica().getCodigo());
			headerResponse.setMensaje(ex.getExcepcionGenerica().getDescripcion());

			estadoRespuesta.setIdOperacion(String.valueOf(ex.getCause().hashCode()));
			estadoRespuesta.setClase(ex.getExcepcionGenerica().getComponente());
			estadoRespuesta.setMetodo(ex.getExcepcionGenerica().getMetodo());
			estadoRespuesta.setNivelSegRequerido(ex.getStackTrace()[0].getLineNumber());
			estadoRespuesta.setMensajeDetallado(ExceptionUtils.getStackTrace(ex));
			
			headerResponse.setEstadoRespuesta(estadoRespuesta);
			headerResponse.setTokenOperacion(request.getSessionId());
			headerResponse.setFechaHora(ErroresComunes.FECHA.getDateValue());

			json1.put("headerResponse", headerResponse);
		} catch (Exception exe) {
			exe.printStackTrace();
		}
		return new ResponseEntity<Object>(json1, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

}
