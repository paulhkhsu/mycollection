package com.myrest.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.myrest.config.AppInitConfig;
import com.myrest.dto.RestErrorDto;
import com.myrest.exception.RecordNotFoundException;

/**
 * @author Paul Hsu
 */
@ControllerAdvice
public class RecordNotFoundHandler {

	static private final Logger log = LogManager.getLogger(AppInitConfig.class
			.getName());

	public RecordNotFoundHandler() {
	}

	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public RestErrorDto processValidationError(RecordNotFoundException ex) {
		log.debug("Handling RecordNotFoundException");
		RestErrorDto dto = new RestErrorDto();
		dto.setErrorCode("1000");
		dto.setText(ex.getMessage());
		return dto;
	}

}
