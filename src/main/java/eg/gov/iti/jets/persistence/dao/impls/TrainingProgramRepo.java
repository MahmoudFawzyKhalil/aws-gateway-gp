package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.TrainingProgram;
import org.springframework.data.jpa.repository.JpaRepository;

interface TrainingProgramRepo extends JpaRepository<TrainingProgram,Integer> {
}
