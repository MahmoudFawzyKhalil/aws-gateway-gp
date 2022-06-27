package eg.gov.iti.jets.api.resource.InstanceLog;

import javax.persistence.Column;

public interface LogMakerProjection {
    Integer getId();

    String getUsername();

    String getEmail();
}
