package eg.gov.iti.jets.api.resource.InstanceLog;

import eg.gov.iti.jets.api.resource.ResponseMessage;
import eg.gov.iti.jets.persistence.dao.InstanceLogsDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping("user/{id}")
    public ResponseEntity<ResponseMessage> getAllByUserAndDateTime(@RequestParam(required = false, defaultValue = "0") int pn, @RequestParam(required = false, defaultValue = "30") int ps,
                                                                   @RequestParam(required = false, name = "from") LocalDateTime from,
                                                                   @RequestParam(required = false, name = "to") LocalDateTime to,
                                                                   @PathVariable int id) {
        List<InstanceLogProjection> instanceLogProjections;
        if (from != null && to != null) {
            instanceLogProjections = instanceLogsDao.findAllByActionMakerAndDateTime(id, from, to, pn, ps, InstanceLogProjection.class);
        } else {
            instanceLogProjections = instanceLogsDao.findAllByActionMaker(id, pn, ps, InstanceLogProjection.class);
        }
        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), instanceLogProjections, null);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }


}
