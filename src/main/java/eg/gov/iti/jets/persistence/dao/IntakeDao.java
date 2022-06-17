package eg.gov.iti.jets.persistence.dao;

import eg.gov.iti.jets.persistence.entity.Intake;
import eg.gov.iti.jets.persistence.entity.Privilege;

import java.util.List;

public interface IntakeDao  extends GenericCrudDao<Intake,Integer>{
    <C> List<C> getByTrainingProgram(int trainingProgramId, Class<C> projection);
}
