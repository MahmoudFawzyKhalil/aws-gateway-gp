package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.dao.InstanceLogsDao;
import eg.gov.iti.jets.persistence.entity.aws.Instance;

import java.util.List;
import java.util.Optional;

public class InstanceLogsDaoImpl implements InstanceLogsDao {
    @Override
    public Instance save(Instance entity) {
        return null;
    }

    @Override
    public Instance update(Instance entity) {
        return null;
    }

    @Override
    public Optional<Instance> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Instance> findAll() {
        return null;
    }

    @Override
    public List<Instance> findAll(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public List<Instance> findAllByExample(Instance example) {
        return null;
    }
}
