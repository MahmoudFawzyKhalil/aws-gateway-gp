package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.aws.Ami;

import java.util.Optional;

public interface AmiAws {
    Optional<Ami> describeAmi( String amiId);
}
