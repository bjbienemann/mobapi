package br.com.dimed.mobapi;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.dimed.mobapi.exception.MessageException;
import br.com.dimed.mobapi.model.dto.ErroDto;

@ControllerAdvice
public class MobapiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		var body = new ErroDto(
				messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale()),
				ex.getCause() != null ? ex.getCause().toString() : ex.toString());
		
		return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var erroDto = new ArrayList<ErroDto>();
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = Objects.nonNull(((FieldError) error).getField())?((FieldError) error).getField() : "";
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			erroDto.add(new ErroDto(nome + " "+ mensagem, ((FieldError) error).toString()));		
		}

		return handleExceptionInternal(ex, erroDto, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(MessageException.class)
	public ResponseEntity<Object> handleMessagesException(MessageException ex, WebRequest request) {
		var message = messageSource.getMessage(ex.getKey(), ex.getArgs(), LocaleContextHolder.getLocale());
		var body = new ErroDto(message, ex.getCause() != null ? ex.getCause().toString() : ex.toString());
		
		return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
}
