package eg.gov.iti.jets.service.management;

import java.util.Optional;

public interface AmiAws {
    Optional<eg.gov.iti.jets.persistence.entity.aws.Ami> describeAmi( String amiId);
}
