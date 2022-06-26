package eg.gov.iti.jets.api.resource.statistics;

import eg.gov.iti.jets.service.management.StatisticsManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsManagement statisticsManagement;
    @GetMapping
    public ResponseEntity<?> getStatistics(){
        StatisticsResponse statistics = statisticsManagement.getStatistics();
        return new ResponseEntity<>( statistics , HttpStatus.OK);
    }
}
