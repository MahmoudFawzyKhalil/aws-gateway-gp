package eg.gov.iti.jets.api.resource.InstanceLog;

import com.fasterxml.jackson.annotation.JsonFormat;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.persistence.entity.enums.UserAction;

import java.time.LocalDateTime;

public interface InstanceLogProjection {
    Long getId();

    LoggedInstanceProjection getInstance();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    LocalDateTime getDateTime();

    UserAction getAction();

    LogMakerProjection getActionMaker();
}
