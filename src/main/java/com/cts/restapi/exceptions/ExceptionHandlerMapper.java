
package com.cts.restapi.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandlerMapper {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> getException(WebRequest wr, Exception ex) {
		String uri = ((ServletWebRequest) wr).getRequest().getRequestURI().toString();
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.toString(), null, uri);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<HashMap> getMethodArgumentNotValidException1(MethodArgumentNotValidException e) {
//		List<FieldError> allErrors = e.getBindingResult().getFieldErrors();
//		HashMap<String, String> hashMap = new HashMap<>();
//		for (FieldError fieldError : allErrors) {
//			hashMap.put(fieldError.getField(), fieldError.getDefaultMessage());
//		}
//		return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
//	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ArrayList<Modelvalidations>> getMethodArgumentNotValidException(
			MethodArgumentNotValidException e) {
		List<FieldError> allErrors = e.getBindingResult().getFieldErrors();
		ArrayList<Modelvalidations> arrayList = new ArrayList<>();
		allErrors.stream().forEach(fieldError -> {
			Modelvalidations modelvalidations = new Modelvalidations(fieldError.getDefaultMessage(),
					fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(),
					fieldError.getCode());
			arrayList.add(modelvalidations);
		});
		return new ResponseEntity<>(arrayList, HttpStatus.BAD_REQUEST);
	}
}

class ErrorResponse {

	private String errMsg;
	private String statusCode;
	private String errCode;
	private String urlPath;

	public ErrorResponse(String errMsg, String statusCode, String errCode, String urlPath) {
		this.errMsg = errMsg;
		this.statusCode = statusCode;
		this.errCode = errCode;
		this.urlPath = urlPath;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
}

class Modelvalidations {

	private String defaultMessage;
	private String objectName;
	private String field;
	private Object rejectedValue;
	private String code;

	public Modelvalidations(String defaultMessage, String objectName, String field, Object rejectedValue, String code) {
		this.defaultMessage = defaultMessage;
		this.objectName = objectName;
		this.field = field;
		this.rejectedValue = rejectedValue;
		this.code = code;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(Object rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
