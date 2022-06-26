package eg.gov.iti.jets.api.resource.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsResponse {
    @NotNull(message = "this field can't be null")
    private Long numberOfInstance;
    @NotNull(message = "this field can't be null")
    private Long numberOfOnInstance;
    @NotNull(message = "this field can't be null")
    private Long numberOfOffInstance;
    @NotNull(message = "this field can't be null")
    private Long numberOfBranches;
    @NotNull(message = "this field can't be null")
    private Long numberOfUsers;
    @NotBlank(message = "this field can't be blank")
    @NotEmpty(message = "this field can't be empty")
    private List<String> nameOfIntakes;
    @NotEmpty(message = "this field can't be empty")
    @NotBlank(message = "this field can't be blank")
    private List<String> nameOfTrainingPrograms;
}
