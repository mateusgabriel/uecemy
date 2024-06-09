package br.uece.spring.api.pagamento.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.uece.spring.api.pagamento.domain.exception.*;

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

    @ExceptionHandler(CursoNaoExistenteException.class)
    public ResponseEntity<Object> handleContaNaoExistenteException(
        CursoNaoExistenteException exception, WebRequest request) {
        return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PagamentoNaoRealizadoException.class)
    public ResponseEntity<Object> handleContaNaoExistenteException(
        PagamentoNaoRealizadoException exception, WebRequest request) {
        return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(
      Exception exception, WebRequest request) {
        return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
