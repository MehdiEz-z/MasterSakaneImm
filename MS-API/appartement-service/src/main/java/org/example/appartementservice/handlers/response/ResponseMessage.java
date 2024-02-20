package org.example.appartementservice.handlers.response;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
@Getter
public class ResponseMessage {
    private final int statusCode;
    private final LocalDateTime timestamp;
    private final String message;
    private Object data;
    public ResponseMessage(int statusCode,Object data,String message) {
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
        this.data = data;
        this.message = message;
    }
    public ResponseMessage(int statusCode,String message) {
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }
    public static ResponseEntity<ResponseMessage> ok(Object data, String message){
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.OK.value(),data,message),HttpStatus.OK);
    }
    public static ResponseEntity<ResponseMessage> notFound(String message){
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.NOT_FOUND.value(),message),HttpStatus.NOT_FOUND);
    }
    public static ResponseEntity<ResponseMessage> created(Object data, String message){
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.CREATED.value(),data,message),HttpStatus.CREATED);
    }
    public static ResponseEntity<ResponseMessage> badRequest(String message){
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.BAD_REQUEST.value(),message),HttpStatus.BAD_REQUEST);
    }
    public static ResponseEntity<ResponseMessage> badRequest(String message,Object data){
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.BAD_REQUEST.value(),data,message),HttpStatus.BAD_REQUEST);
    }
    public static ResponseEntity<ResponseMessage> unauthorized(String message){
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.UNAUTHORIZED.value(),message),HttpStatus.UNAUTHORIZED);
    }
    public static ResponseEntity<ResponseMessage> forbidden(String message){
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.FORBIDDEN.value(),message),HttpStatus.FORBIDDEN);
    }
    public static ResponseEntity<ResponseMessage> noContent(String message){
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.NO_CONTENT.value(),message),HttpStatus.NO_CONTENT);
    }
}