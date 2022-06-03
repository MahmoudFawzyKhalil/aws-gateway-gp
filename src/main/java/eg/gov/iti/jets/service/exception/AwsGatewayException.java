package eg.gov.iti.jets.service.exception;

public class AwsGatewayException extends RuntimeException{
    public AwsGatewayException( String message ) {
        super( message );
    }

    public AwsGatewayException( String message, Throwable cause ) {
        super( message, cause );
    }
}
