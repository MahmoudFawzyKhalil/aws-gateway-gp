package eg.gov.iti.jets.api.resource.InstanceLog;

import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import eg.gov.iti.jets.persistence.entity.enums.UserAction;

import java.time.LocalDateTime;

public interface InstanceLogProjection {
    Long getId();

    LoggedInstanceProjection getInstance();

    LocalDateTime getDateTime();

    UserAction getAction();

    LogMakerProjection getActionMaker();
}
