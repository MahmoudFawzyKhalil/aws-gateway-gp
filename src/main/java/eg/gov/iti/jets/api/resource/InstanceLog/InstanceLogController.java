package eg.gov.iti.jets.api.resource.InstanceLog;

import eg.gov.iti.jets.api.resource.ResponseMessage;
import eg.gov.iti.jets.persistence.dao.InstanceLogsDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/instancelogs")
public class InstanceLogController {
    private final InstanceLogsDao instanceLogsDao;

    public InstanceLogController(InstanceLogsDao instanceLogsDao) {
        this.instanceLogsDao = instanceLogsDao;
    }

    @GetMapping
    public ResponseEntity<ResponseMessage> getAll(@RequestParam(required = false, defaultValue = "0") int pn, @RequestParam(required = false, defaultValue = "30") int ps) {
        List<InstanceLogProjection> instanceLogProjections = instanceLogsDao.findAll(pn, ps, InstanceLogProjection.class);
        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), instanceLogProjections, null);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
