package org.example.clientservice.handlers.exceptionHandler;
import org.example.clientservice.handlers.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity<ResponseMessage> resourceNotFoundException(ResourcesNotFoundException ex, WebRequest request) {
        ResponseMessage message = new ResponseMessage(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(OperationsException.class)
    public ResponseEntity<ResponseMessage> operationException(OperationsException ex, WebRequest request) {
        ResponseMessage message = new ResponseMessage(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessage> notValid(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> erreurs = new ArrayList<>();
        ex.getAllErrors().forEach(err -> erreurs.add(err.getDefaultMessage()));
        Map<String, List<String>> result = new HashMap<>();
        result.put("erreur", erreurs);
        return ResponseMessage.badRequest("Erreur de validation", result);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        ResponseMessage message = new ResponseMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
