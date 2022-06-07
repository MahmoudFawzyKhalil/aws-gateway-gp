package eg.gov.iti.jets.api.resource.trainingProgram;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetTrainingProgramsResponse {
    List<TrainingProgramResponse> trainingPrograms;
}
