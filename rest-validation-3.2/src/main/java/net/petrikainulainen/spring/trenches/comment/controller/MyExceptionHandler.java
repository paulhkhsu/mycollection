package net.petrikainulainen.spring.trenches.comment.controller;

import net.petrikainulainen.spring.trenches.comment.dto.CommentDTO;
import net.petrikainulainen.spring.trenches.comment.dto.ValidationErrorDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.List;
import java.util.Locale;

import net.petrikainulainen.spring.trenches.comment.exception.MyException;
/**
 * @author Paul Hsu
 */
@ControllerAdvice
public class MyExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyExceptionHandler.class);

    public MyExceptionHandler() {
    }

    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommentDTO processValidationError(MyException ex) {
        LOGGER.debug("Handling form validation error");
        CommentDTO dto = new CommentDTO();
        dto.setText(ex.getMessage());
        return dto;
    }

}
