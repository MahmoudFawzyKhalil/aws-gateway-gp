package eg.gov.iti.jets.persistence.dao;

import eg.gov.iti.jets.persistence.entity.TrainingProgram;

import java.util.List;

public interface TrainingProgramDao extends GenericCrudDao<TrainingProgram,Integer>{
    <C> List<C> getByBranchId(int branchId, Class<C> projection);
}
