package eg.gov.iti.jets.service.exception;

public class ResourceExistException extends RuntimeException {
    public ResourceExistException(String message) {
        super(message);
    }
    public ResourceExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
