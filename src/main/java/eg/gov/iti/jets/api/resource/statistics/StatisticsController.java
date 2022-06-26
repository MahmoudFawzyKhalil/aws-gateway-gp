package eg.gov.iti.jets.api.resource.statistics;

import eg.gov.iti.jets.service.management.StatisticsManagement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    private final StatisticsManagement statisticsManagement;

    public StatisticsController( StatisticsManagement statisticsManagement ) {
        this.statisticsManagement = statisticsManagement;
    }

    @GetMapping
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).VIEW_STATISTICS.name())")
    public ResponseEntity<?> getStatistics(){
        StatisticsResponse statistics = statisticsManagement.getStatistics();
        return new ResponseEntity<>( statistics , HttpStatus.OK);
    }
}
