package eg.gov.iti.jets.api.resource.template;

import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.aws.KeyPair;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;

import javax.persistence.*;
import java.util.List;

public class TemplateRequest {
    private String amiId;
    private String subnetId;
    private String instanceType;
    private List<User> instructors;
    private List<SecurityGroup> securityGroups;
}
