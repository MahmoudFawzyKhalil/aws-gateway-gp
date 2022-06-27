package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.InstanceLogsDao;
import eg.gov.iti.jets.service.management.InstanceLogManagement;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InstanceLogManagementImpl implements InstanceLogManagement {
    private final InstanceLogsDao instanceLogsDao;

    public InstanceLogManagementImpl(InstanceLogsDao instanceLogsDao) {
        this.instanceLogsDao = instanceLogsDao;
    }


    @Override
    public <C> List<C> findAll(int pageNumber, int pageSize, Class<C> projection) {
        return instanceLogsDao.findAll(pageNumber, pageSize, projection);
    }

    @Override
    public <C> List<C> findAllByUser(int userId, int pageNumber, int pageSize, Class<C> projection) {
        return instanceLogsDao.findAllByActionMaker(userId, pageNumber, pageSize, projection);
    }

    @Override
    public <C> List<C> findAllByUserAndDate(int userId, int pageNumber, LocalDateTime dateTime1, LocalDateTime dateTime2, int pageSize, Class<C> projection) {
        return instanceLogsDao.findAllByActionMakerAndDateTime(userId, dateTime1, dateTime2, pageNumber, pageSize, projection);
    }
}
