package eg.gov.iti.jets.api.exceptionhandler;

import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceNotFoundExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleException(ResourceNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMsg("Resource with id not found");
        errorResponse.setErrorCode(404);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
