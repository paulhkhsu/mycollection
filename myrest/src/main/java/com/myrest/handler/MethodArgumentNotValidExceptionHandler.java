package com.myrest.handler;

import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.myrest.dto.FieldErrorDto;
import com.myrest.dto.RestArgumentNotValidDto;

/**
 * @author Paul Hsu
 */
@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

	static private final Logger log = LogManager
			.getLogger(MethodArgumentNotValidExceptionHandler.class.getName());

	private MessageSource messageSource;

	@Autowired
	public MethodArgumentNotValidExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RestArgumentNotValidDto processValidationError(
			MethodArgumentNotValidException ex) {
		log.debug("Handling form validation error");

		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		return processFieldErrors(fieldErrors);
	}

	private RestArgumentNotValidDto processFieldErrors(
			List<FieldError> fieldErrors) {
		RestArgumentNotValidDto dto = new RestArgumentNotValidDto();
		dto.setErrorCode("10001");
		dto.setText("Invalid Arguments");
		for (FieldError fieldError : fieldErrors) {
			String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
			log.debug("Adding error message: {} to field: {}",
					localizedErrorMessage, fieldError.getField());
			dto.addFieldError(new FieldErrorDto(fieldError.getField(),
					localizedErrorMessage));
		}

		return dto;
	}

	private String resolveLocalizedErrorMessage(FieldError fieldError) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(fieldError,
				currentLocale);

		// If a message was not found, return the most accurate field error code
		// instead.
		// You can remove this check if you prefer to get the default error
		// message.
		if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
			String[] fieldErrorCodes = fieldError.getCodes();
			localizedErrorMessage = fieldErrorCodes[0];
		}

		return localizedErrorMessage;
	}
}
