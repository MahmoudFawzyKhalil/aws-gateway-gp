package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.TrainingProgram;
import eg.gov.iti.jets.persistence.entity.aws.Instance;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface TrainingProgramRepo extends JpaRepository<TrainingProgram,Integer> {
}
