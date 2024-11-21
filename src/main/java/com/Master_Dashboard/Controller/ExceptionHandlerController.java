package com.Master_Dashboard.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.Master_Dashboard.request.*;
import com.Master_Dashboard.request.BaseResponse;

/**
 * Handle all Exceptions in for All controllers
 */

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errors = new ArrayList<String>();

		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + " -> " + error.getDefaultMessage());
		}

		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + " -> " + error.getDefaultMessage());
		}

		BaseResponse apiError = new BaseResponse();
		apiError.setCode(ResponseMessage.BAD_REQUEST);
		apiError.setDescription(errors);
		apiError.setStatus(HttpStatus.BAD_REQUEST);

		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}

	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BaseResponse baseResponse = new BaseResponse(HttpStatus.BAD_REQUEST,
				Arrays.asList(ResponseMessage.INVALID_JSON_FORMATE), ResponseMessage.FAILED);
		return handleExceptionInternal(ex, baseResponse, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BaseResponse baseResponse = new BaseResponse(HttpStatus.BAD_REQUEST,
				Arrays.asList(ResponseMessage.HEADERS_MISSING), ResponseMessage.FAILED);
		return handleExceptionInternal(ex, baseResponse, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			org.springframework.web.bind.MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BaseResponse baseResponse = new BaseResponse(HttpStatus.BAD_REQUEST,
				Arrays.asList(ResponseMessage.HEADERS_PARAM), ResponseMessage.FAILED);
		return handleExceptionInternal(ex, baseResponse, headers, status, request);
	}
	
}