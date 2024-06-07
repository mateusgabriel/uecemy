package br.uece.spring.api.usuario.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.uece.spring.api.usuario.domain.exception.*;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
          HttpHeaders headers, HttpStatusCode status, WebRequest request) {

          Map<String, String> errors = new HashMap<>();
          ex.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
          });

        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsuarioNaoExistenteException.class)
    public ResponseEntity<Object> handleContaNaoExistenteException(
        UsuarioNaoExistenteException exception, WebRequest request) {
        return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailVinculadoUsuarioException.class)
    public ResponseEntity<Object> handleContaNaoExistenteException(
        EmailVinculadoUsuarioException exception, WebRequest request) {
        return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.CONFLICT);
    }
}
