package eg.gov.iti.jets.api.exceptionhandler;

import eg.gov.iti.jets.service.exception.AwsGatewayException;
import eg.gov.iti.jets.service.exception.ResourceExistException;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionAdvisor {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleException(ResourceNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMsg(exception.getMessage());
        errorResponse.setErrorCode(404);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceExistException.class)
    public ResponseEntity<?> handleException(ResourceExistException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMsg(exception.getMessage());
        errorResponse.setErrorCode(406);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(AwsGatewayException.class)
    public ResponseEntity<?> handleException(AwsGatewayException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMsg(exception.getMessage());
        errorResponse.setErrorCode(500);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
