package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.Branch;
import eg.gov.iti.jets.persistence.entity.Intake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntakeRepo extends JpaRepository<Intake,Integer> {
}
