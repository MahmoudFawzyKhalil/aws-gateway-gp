package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.Intake;
import org.springframework.data.jpa.repository.JpaRepository;

interface IntakeRepo extends JpaRepository<Intake,Integer> {
}
