package eg.gov.iti.jets.api.resource.template;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class TemplateAssignRequest {
    @NotNull(message = "Instructor ids can't be null")
    @NotEmpty(message = "Instructor ids can't be Empty")

    private List<Integer> instructorIds;
    @NotNull(message = "Template configuration ids can't be null")
    @NotEmpty(message = "Template configuration ids can't be Empty")

    private List<Integer> templateConfigurationIds;
}
