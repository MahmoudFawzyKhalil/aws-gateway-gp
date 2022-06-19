package eg.gov.iti.jets.api.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private Date timestamp;
    private int code;
    private String error;
    private String msg;
}
