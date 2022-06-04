package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

interface TrackRepo extends JpaRepository<Track,Integer> {
}
