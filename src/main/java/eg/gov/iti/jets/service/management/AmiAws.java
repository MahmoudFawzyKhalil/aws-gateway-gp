package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.aws.Ami;

public interface AmiAws {
    Ami describeAmi( String amiId);
}
