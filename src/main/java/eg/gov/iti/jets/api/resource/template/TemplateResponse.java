package eg.gov.iti.jets.api.resource.template;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TemplateResponse {
    private int id;
    private AmiResponse ami;
    private String subnetId;
    private String instanceType;
    private List<String> securityGroup;
}