package eg.gov.iti.jets.service.model;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private Integer id;
    private String name;
    private String address;
    private User trainingManager;
    private final List<TrainingProgram> trainingPrograms = new ArrayList<>();
}
