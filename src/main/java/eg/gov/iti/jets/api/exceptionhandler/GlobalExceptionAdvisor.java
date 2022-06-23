package eg.gov.iti.jets.api.exceptionhandler;

import eg.gov.iti.jets.service.exception.AwsGatewayException;
import eg.gov.iti.jets.service.exception.ResourceConstraintsViolationException;
import eg.gov.iti.jets.service.exception.ResourceExistException;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionAdvisor {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleException(ResourceNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setCode(404);
        errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorResponse.setMsg(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ResourceExistException.class, ResourceConstraintsViolationException.class})
    public ResponseEntity<?> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setCode(406);
        errorResponse.setMsg(exception.getMessage());
        errorResponse.setError(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(AwsGatewayException.class)
    public ResponseEntity<?> handleException(AwsGatewayException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setCode(500);
        errorResponse.setMsg(exception.getMessage());
        errorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        ErrorResponse errorResponse = new ErrorResponse();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        errorResponse.setTimestamp(new Date());
        errorResponse.setCode(400);
        errorResponse.setMsg(errors.toString());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
