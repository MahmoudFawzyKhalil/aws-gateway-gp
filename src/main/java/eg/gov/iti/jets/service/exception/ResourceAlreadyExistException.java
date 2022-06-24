package eg.gov.iti.jets.service.exception;

public class ResourceAlreadyExistException extends RuntimeException {
    public ResourceAlreadyExistException( String message) {
        super(message);
    }
    public ResourceAlreadyExistException( String message, Throwable cause) {
        super(message, cause);
    }
}
