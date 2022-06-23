package eg.gov.iti.jets.service.exception;

public class ResourceConstraintsViolationException extends RuntimeException {
    public ResourceConstraintsViolationException(String message) {
        super(message);
    }
    public ResourceConstraintsViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
